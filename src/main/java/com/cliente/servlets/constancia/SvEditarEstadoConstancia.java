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
import com.cliente.dto.UsuarioDTO;
import com.cliente.services.ServiceAnalista;
import com.cliente.services.ServiceEstado;
import com.cliente.services.ServiceEstadoConstancia;
import com.cliente.services.ServiceJWT;
import com.servidor.entidades.Analista;
import com.servidor.entidades.Constancia;
import com.servidor.entidades.Estado;
import com.servidor.entidades.EstadoConstancia;
import com.servidor.entidades.EstadoConstanciaPK;

@WebServlet(name = "ServletEditarEstadoConstancia", urlPatterns = "/SvEditarEstadoConstancia")
public class SvEditarEstadoConstancia extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String detalle = null;
	private String estado = null;

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

		if (request.getSession().getAttribute("mostrarModal") == null) {
			detalle = request.getParameter("detalle");
			estado = request.getParameter("estado");
			if (!estado.equals("default")) {
				request.getSession().setAttribute("estado", estado);
			}
			request.getSession().setAttribute("detalle", detalle);

			boolean esValido = ValidacionConstancia.validarEstadoConstancia(detalle, estado, request.getSession());

			if (!esValido) {
				response.sendRedirect("/Proyecto-PInfra/pages/constancias/estado/index.jsp");
				return;
			} else {
				Fabrica.generarModal(request, "/Proyecto-PInfra/SvEditarEstadoConstancia",
						"¿Está seguro que desea editar el estado de la constancia?",
						"El estado de la constancia se editará.", "POST");
				response.sendRedirect("/Proyecto-PInfra/pages/constancias/estado/index.jsp");
				return;
			}
		}

		Constancia oConstancia = (Constancia) request.getSession().getAttribute("oSolicitudAEditar");
		UsuarioDTO oUsuarioLogueado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogueado");
		Analista oAnalista = new Analista(ServiceAnalista.getByDocumento(oUsuarioLogueado.getDocumento().toString()));
		Estado oEstado = ServiceEstado.getById(Long.valueOf(estado));

		EstadoConstanciaPK eConstanciaPK = new EstadoConstanciaPK(oConstancia.getIdConstancia(),
				oAnalista.getIdAnalista(), new Date());
		EstadoConstancia eConstancia = new EstadoConstancia(eConstanciaPK, detalle, oAnalista, oConstancia, oEstado);
		boolean creada = ServiceEstadoConstancia.crearEstadoConstancia(eConstancia);

		request.getSession().removeAttribute("mostrarModal");
		request.getSession().removeAttribute("detalle");
		request.getSession().removeAttribute("estado");
		Fabrica.limpiarMensajesDeError(request.getSession());
		request.getSession().removeAttribute("oSolicitudAEditar");
		response.sendRedirect("/Proyecto-PInfra/pages/constancias/solicitudes/index.jsp");
	}

}
