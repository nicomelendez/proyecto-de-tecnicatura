package com.cliente.servlets.constancia;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.contexto.validaciones.ValidacionConstancia;
import com.cliente.dto.AnalistaDTO;
import com.cliente.dto.EstudianteDTO;
import com.cliente.dto.UsuarioDTO;
import com.cliente.services.ServiceAnalista;
import com.cliente.services.ServiceConstancia;
import com.cliente.services.ServiceEstado;
import com.cliente.services.ServiceEstadoConstancia;
import com.cliente.services.ServiceEstudiante;
import com.cliente.services.ServiceEvento;
import com.cliente.services.ServiceJWT;
import com.cliente.services.ServiceTipo;
import com.servidor.entidades.Analista;
import com.servidor.entidades.Constancia;
import com.servidor.entidades.EstadoConstancia;
import com.servidor.entidades.EstadoConstanciaPK;
import com.servidor.entidades.Estudiante;
import com.servidor.entidades.Evento;
import com.servidor.entidades.Tipo;

@WebServlet(name = "ServletCrearConstancia", urlPatterns = "/SvCrearConstancia")
public class SvCrearConstancia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		Fabrica.limpiarMensajesDeError(request.getSession());
		String titulo = request.getParameter("titulo");
		String detalleConstancia = request.getParameter("detalle");
		String evento = request.getParameter("evento");
		String tipo = request.getParameter("tipo");

		boolean esValido = ValidacionConstancia.validar(titulo, detalleConstancia, evento, tipo, request.getSession());

		if (!esValido) {
			response.sendRedirect("/Proyecto-PInfra/pages/constancias/solicitar/index.jsp");
			return;
		}

		Evento oEvento = ServiceEvento.buscarEventoPorId(Long.valueOf(evento));
		Tipo oTipo = ServiceTipo.getById(Long.valueOf(tipo));
		UsuarioDTO oUsuarioLogueado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogueado");
		EstudianteDTO oEstudiante = ServiceEstudiante.getByDocumento(oUsuarioLogueado.getDocumento().toString());

		Constancia oConstanciaNueva = new Constancia(titulo, new Estudiante(oEstudiante), oEvento, oTipo, "S");

		Constancia oConstanciaCreada = ServiceConstancia.crearConstancia(oConstanciaNueva);
		System.out.println("-----CONSTANCIA-----");
		System.out.println(oConstanciaCreada.getIdConstancia());
		for (AnalistaDTO oAnalista : ServiceAnalista.listar()) {
			long offset = oAnalista.getIdAnalista() * 1000;
			Date uniqueDate = new Date(new Date().getTime() + offset);

			EstadoConstanciaPK eConstanciaPK = new EstadoConstanciaPK(oConstanciaCreada.getIdConstancia(),
					oAnalista.getIdAnalista(), uniqueDate);
			EstadoConstancia eConstancia = new EstadoConstancia(eConstanciaPK, detalleConstancia,
					new Analista(oAnalista), oConstanciaCreada, ServiceEstado.listarEstados().get(0));
			boolean creada = ServiceEstadoConstancia.crearEstadoConstancia(eConstancia);
		}

		if (oConstanciaCreada != null) {
			registroExitoso(request, response, "/Proyecto-PInfra/pages/constancias/solicitar/index.jsp");
		}

		response.sendRedirect("/Proyecto-PInfra/pages/constancias/solicitar/index.jsp");
	}

	private void registroExitoso(HttpServletRequest request, HttpServletResponse response, String ruta)
			throws IOException {
		request.getSession().setAttribute(("mostrarNotificacion"), true);
		request.getSession().setAttribute(("tituloNotificacion"), "¡Solicitud exitosa!");
		request.getSession().setAttribute(("descripcionNotificacion"),
				"Tu solicitud de constancia ha sido enviada correctamente.");
		Fabrica.limpiarMensajesDeError(request.getSession());
		response.sendRedirect(ruta);
		return;
	}
}
