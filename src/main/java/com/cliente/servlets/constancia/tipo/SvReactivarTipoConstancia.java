package com.cliente.servlets.constancia.tipo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.services.ServiceJWT;
import com.cliente.services.ServiceTipo;

@WebServlet(name = "ServletReactivarTipoConstancia", urlPatterns = "/SvReactivarTipoConstancia")
public class SvReactivarTipoConstancia extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Long idTipo = null;

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
			idTipo = Long.parseLong(request.getParameter("idTipo"));
			Fabrica.generarModal(request, "/Proyecto-PInfra/SvReactivarTipoConstancia",
					"¿Está seguro que desea reactivar el Tipo de Constancia?",
					"El Tipo de Constancia volverá a estar activo en la plataforma.", "POST");
			response.sendRedirect("/Proyecto-PInfra/pages/constancias/tipos/index.jsp");
			return;
		}
		ServiceTipo.reactivarTipoConstancia(idTipo);
		idTipo = null;
		request.getSession().removeAttribute("mostrarModal");
		response.sendRedirect("/Proyecto-PInfra/pages/constancias/tipos/index.jsp");
	}

}
