package com.cliente.servlets.reclamo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.services.ServiceReclamo;
import com.servidor.entidades.Reclamo;

@WebServlet(name = "ServletNavegarEstadoReclamo", urlPatterns = "/SvNavegarEstadoReclamo")

public class SvNavegarEstadoReclamo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Fabrica.limpiarMensajesDeError(request.getSession());
		String idReclamo = request.getParameter("idReclamo");

		Reclamo oReclamo = ServiceReclamo.getById(Long.valueOf(idReclamo));

		request.getSession().setAttribute("reclamoAEditar", oReclamo);

		response.sendRedirect("/Proyecto-PInfra/pages/reclamos/editar/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
