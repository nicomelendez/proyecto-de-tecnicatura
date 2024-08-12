package com.cliente.servlets.constancia;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.services.ServiceConstancia;
import com.cliente.services.ServiceJWT;


@WebServlet(name = "ServletEliminarConstancia", urlPatterns = "/SvEliminarConstancia")
public class SvEliminarConstancia extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String idConstancia = null;    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		Fabrica.limpiarMensajesDeError(request.getSession());
		
		if (request.getSession().getAttribute("mostrarModal") == null) {
			idConstancia = request.getParameter("idConstancia");
			 Fabrica.generarModal(request, "/Proyecto-PInfra/SvEliminarConstancia", "¿Está seguro de que desea eliminar su su solicitud de constancia?", "La solicitud será eliminada", "POST");
				response.sendRedirect("/Proyecto-PInfra/pages/constancias/listar/index.jsp");
				return;
		}
		
		boolean constanciaEliminada = ServiceConstancia.eliminarConstancia(Long.valueOf(idConstancia));
		request.getSession().removeAttribute("mostrarModal");
		idConstancia = null;
		response.sendRedirect("/Proyecto-PInfra/pages/constancias/listar/index.jsp");
	}

}
