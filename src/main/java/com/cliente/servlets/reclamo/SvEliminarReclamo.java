package com.cliente.servlets.reclamo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.services.ServiceJWT;
import com.cliente.services.ServiceReclamo;
import com.servidor.entidades.Reclamo;

@WebServlet(name = "ServletEliminarReclamo", urlPatterns = "/SvEliminarReclamo")
public class SvEliminarReclamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Long idReclamo = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("mostrarForm");
		response.sendRedirect("/Proyecto-PInfra/pages/reclamos/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}

		request.setCharacterEncoding("UTF-8");
		if (request.getSession().getAttribute("mostrarModal") == null) {
			idReclamo = Long.parseLong(request.getParameter("idReclamo"));
			Fabrica.generarModal(request, "/Proyecto-PInfra/SvEliminarReclamo",
					"¿Está seguro que desea eliminar la solicitud de reclamo?",
					"La solicitud de reclamo se dará de baja.", "POST");
			response.sendRedirect("/Proyecto-PInfra/pages/reclamos/index.jsp");
			return;
		}
		ServiceReclamo.eliminar(Long.valueOf(idReclamo));
		request.getSession().removeAttribute("mostrarModal");
		idReclamo = null;
		response.sendRedirect("/Proyecto-PInfra/pages/reclamos/index.jsp");

	}

}
