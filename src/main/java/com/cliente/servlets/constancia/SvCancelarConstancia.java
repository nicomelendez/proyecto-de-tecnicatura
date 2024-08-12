package com.cliente.servlets.constancia;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;


@WebServlet(name = "ServletCancelarConstancia", urlPatterns = "/SvCancelarConstancia")
public class SvCancelarConstancia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ruta = request.getParameter("ruta");
		request.getSession().removeAttribute("detalle");
		request.getSession().removeAttribute("estado");
		Fabrica.limpiarMensajesDeError(request.getSession());
		if(ruta.equals("solicitud")) {
			request.getSession().removeAttribute("oSolicitudAEditar");
			response.sendRedirect("/Proyecto-PInfra/pages/constancias/listar/index.jsp");
		}
		
		if(ruta.equals("estado")) {
			request.getSession().removeAttribute("oSolicitudAEditar");
			response.sendRedirect("/Proyecto-PInfra/pages/constancias/solicitudes/index.jsp");	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
