package com.cliente.servlets.reclamo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.contexto.validaciones.ValidacionReclamo;
import com.cliente.services.ServiceEstadoReclamo;
import com.cliente.services.ServiceEvento;
import com.cliente.services.ServiceJWT;
import com.cliente.services.ServiceReclamo;
import com.servidor.entidades.EstadoConstancia;
import com.servidor.entidades.EstadoReclamo;
import com.servidor.entidades.Evento;
import com.servidor.entidades.Reclamo;

@WebServlet(name = "ServletEditarReclamoEstudiante", urlPatterns = "/SvEditarReclamoEstudiante")
public class SvEditarReclamoEstudiante extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String titulo = null;
	private String detalle = null;
	private Evento oEvento = null;
	private String idEvento = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Reclamo oReclamoAEditar = (Reclamo) request.getSession().getAttribute("reclamoAEditar");
		oReclamoAEditar.setDetalle(titulo);
		oReclamoAEditar.setEvento(oEvento);
		for (EstadoReclamo oEstadoReclamo : ServiceEstadoReclamo
				.listarEstadosReclamoPorIdReclamo(oReclamoAEditar.getIdReclamo())) {
			oEstadoReclamo.setDetalle(detalle);
			var estadoEditado = ServiceEstadoReclamo.actualizar(oEstadoReclamo);
		}
		var oReclamoEditado = ServiceReclamo.actualizar(oReclamoAEditar);

		request.getSession().removeAttribute("reclamoAEditar");
		request.getSession().removeAttribute("mostrarModal");
		Fabrica.limpiarMensajesDeError(request.getSession());
		titulo = null;
		detalle = null;
		oEvento = null;
		response.sendRedirect("/Proyecto-PInfra/pages/reclamos/index.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}

		request.setCharacterEncoding("UTF-8");
		Fabrica.limpiarMensajesDeError(request.getSession());

		titulo = request.getParameter("titulo");
		detalle = request.getParameter("detalle");
		idEvento = request.getParameter("evento");

		boolean esValido = ValidacionReclamo.validar(titulo, detalle, idEvento, request.getSession());

		if (!esValido) {
			response.sendRedirect("/Proyecto-PInfra/pages/reclamos/editar/index.jsp");
			return;
		}

		if (request.getSession().getAttribute("mostrarModal") == null) {
			Fabrica.limpiarMensajesDeError(request.getSession());
			oEvento = ServiceEvento.buscarEventoPorId(Long.valueOf(idEvento));
			Fabrica.generarModal(request, "/Proyecto-PInfra/SvEditarReclamoEstudiante",
					"¿Está seguro que desea editar la solicitud de reclamo?", "La solicitud de reclamo se editara.",
					"GET");
			response.sendRedirect("/Proyecto-PInfra/pages/reclamos/editar/index.jsp");
			return;
		}
	}

}
