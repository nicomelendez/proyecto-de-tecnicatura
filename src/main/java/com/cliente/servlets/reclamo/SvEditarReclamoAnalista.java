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
import com.cliente.dto.UsuarioDTO;
import com.cliente.services.ServiceAnalista;
import com.cliente.services.ServiceEstado;
import com.cliente.services.ServiceEstadoReclamo;
import com.cliente.services.ServiceJWT;
import com.cliente.services.ServiceReclamo;
import com.servidor.entidades.Estado;
import com.servidor.entidades.EstadoReclamo;
import com.servidor.entidades.EstadoReclamoPK;
import com.servidor.entidades.Reclamo;

@WebServlet(name = "ServletEditarReclamoAnalista", urlPatterns = "/SvEditarReclamoAnalista")
public class SvEditarReclamoAnalista extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String detalle = null;
	private String idEstado = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Reclamo oReclamoAEditar = (Reclamo) request.getSession().getAttribute("reclamoAEditar");

		Estado oEstado = ServiceEstado.getById(Long.parseLong(idEstado));
		UsuarioDTO usuarioLogueado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogueado");
		Long idAnalista = ServiceAnalista.getByDocumento(usuarioLogueado.getDocumento().toString()).getIdAnalista();

		EstadoReclamoPK erPK = new EstadoReclamoPK(oReclamoAEditar.getIdReclamo(), idAnalista, new Date());

		EstadoReclamo nuevoEstadoReclamo = new EstadoReclamo(erPK, detalle, oEstado);

		var nuevoEstadoReclamoCreado = ServiceEstadoReclamo.crear(nuevoEstadoReclamo);
		request.getSession().removeAttribute("reclamoAEditar");
		request.getSession().removeAttribute("mostrarModal");
		Fabrica.limpiarMensajesDeError(request.getSession());
		detalle = null;
		idEstado = null;
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
		detalle = request.getParameter("detalle");
		idEstado = request.getParameter("estado");

		boolean esValido = ValidacionReclamo.validarEstadoReclamo(detalle, idEstado, request.getSession());

		if (!esValido) {
			response.sendRedirect("/Proyecto-PInfra/pages/reclamos/editar/index.jsp");
			return;
		}

		if (request.getSession().getAttribute("mostrarModal") == null) {

			Fabrica.generarModal(request, "/Proyecto-PInfra/SvEditarReclamoAnalista",
					"¿Está seguro que desea editar el estado del reclamo?", "El estado del reclamo se editara.", "GET");
			response.sendRedirect("/Proyecto-PInfra/pages/reclamos/editar/index.jsp");
			return;
		}

	}

}
