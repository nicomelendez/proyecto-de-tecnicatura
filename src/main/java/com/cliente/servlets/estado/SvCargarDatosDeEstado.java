package com.cliente.servlets.estado;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.services.ServiceEstado;
import com.servidor.entidades.Estado;

@WebServlet(name = "SvCargarDatosDeEstado", urlPatterns = "/SvCargarDatosDeEstado")
public class SvCargarDatosDeEstado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Fabrica.limpiarMensajesDeError(request.getSession());
		Long idEstado = Long.parseLong(request.getParameter("idEstado"));
		
		Estado oEstadoEditar = ServiceEstado.getById(idEstado);

		request.getSession().setAttribute("estadoEditar", oEstadoEditar);
		response.sendRedirect("/Proyecto-PInfra/pages/estados/index.jsp");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	}

}
