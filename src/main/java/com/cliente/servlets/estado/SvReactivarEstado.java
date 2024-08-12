package com.cliente.servlets.estado;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.services.ServiceEstado;
import com.cliente.services.ServiceJWT;
import com.cliente.services.ServiceTipo;

@WebServlet(name = "SvReactivarEstado", urlPatterns = "/SvReactivarEstado")
public class SvReactivarEstado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Long idEstado = null;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}
		
		request.setCharacterEncoding("UTF-8");
		Fabrica.limpiarMensajesDeError(request.getSession());
		if (request.getSession().getAttribute("mostrarModal") == null) {
			idEstado = Long.parseLong(request.getParameter("idEstado"));
			Fabrica.generarModal(request, "/Proyecto-PInfra/SvReactivarEstado",
					"¿Está seguro que desea reactivar el Estado?",
					"El Estado volverá a estar activo en la plataforma.", "POST");
			response.sendRedirect("/Proyecto-PInfra/pages/estados/index.jsp");
			return;
		}
		ServiceEstado.reactivarEstado(idEstado);
		idEstado = null;
		request.getSession().removeAttribute("mostrarModal");
		response.sendRedirect("/Proyecto-PInfra/pages/estados/index.jsp");
	}

}
