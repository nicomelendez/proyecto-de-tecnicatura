package com.cliente.servlets.reclamo;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.contexto.validaciones.ValidacionReclamo;
import com.cliente.dto.AnalistaDTO;
import com.cliente.dto.EstudianteDTO;
import com.cliente.dto.UsuarioDTO;
import com.cliente.services.ServiceAnalista;
import com.cliente.services.ServiceEstado;
import com.cliente.services.ServiceEstadoReclamo;
import com.cliente.services.ServiceEstudiante;
import com.cliente.services.ServiceEvento;
import com.cliente.services.ServiceJWT;
import com.cliente.services.ServiceReclamo;
import com.servidor.entidades.EstadoReclamo;
import com.servidor.entidades.EstadoReclamoPK;
import com.servidor.entidades.Estudiante;
import com.servidor.entidades.Evento;
import com.servidor.entidades.Reclamo;

@WebServlet(name = "ServletCrearReclamo", urlPatterns = "/SvCrearReclamo")
public class SvCrearReclamo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}
		Fabrica.limpiarMensajesDeError(request.getSession());
		request.setCharacterEncoding("UTF-8");

		String titulo = request.getParameter("titulo");
		String detalle = request.getParameter("detalle");
		String idEvento = request.getParameter("evento");

		boolean esValido = ValidacionReclamo.validar(titulo, detalle, idEvento, request.getSession());

		if (!esValido) {
			response.sendRedirect("/Proyecto-PInfra/pages/reclamos/index.jsp");
			return;
		}

		UsuarioDTO oUsuarioLogueado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogueado");
		EstudianteDTO oEstudiante = ServiceEstudiante.getByDocumento(oUsuarioLogueado.getDocumento().toString());
		Evento oEvento = ServiceEvento.buscarEventoPorId(Long.valueOf(idEvento));

		Reclamo oReclamoNuevo = new Reclamo(titulo, new Estudiante(oEstudiante), oEvento, "S");
		var oReclamoCreado = ServiceReclamo.crear(oReclamoNuevo);

		for (AnalistaDTO oAnalista : ServiceAnalista.listar()) {

			long offset = oAnalista.getIdAnalista() * 1000;
			Date uniqueDate = new Date(new Date().getTime() + offset);

			EstadoReclamoPK ePK = new EstadoReclamoPK(oReclamoCreado.getIdReclamo(), oAnalista.getIdAnalista(),
					uniqueDate);
			EstadoReclamo eR = new EstadoReclamo(ePK, detalle, ServiceEstado.listarEstados().get(0));
			var EstadoReclamoCreado = ServiceEstadoReclamo.crear(eR);
		}
		response.sendRedirect("/Proyecto-PInfra/pages/reclamos/index.jsp");
	}

}
