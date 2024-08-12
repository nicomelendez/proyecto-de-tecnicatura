package com.cliente.servlets.estado;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.contexto.validaciones.ValidacionEstado;
import com.cliente.contexto.validaciones.ValidacionTipoConstancia;
import com.cliente.services.ServiceEstado;
import com.cliente.services.ServiceJWT;
import com.servidor.entidades.Estado;

@WebServlet(name = "ServletCrearEstado", urlPatterns = "/SvCrearEstado")
public class SvCrearEstado extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}
		
		request.setCharacterEncoding("UTF-8");
		Fabrica.limpiarMensajesDeError(request.getSession());

		String descripcion = request.getParameter("descripcion");
		
		boolean esValido = ValidacionEstado.validar(descripcion, request.getSession());

		if (!esValido) {
			response.sendRedirect("/Proyecto-PInfra/pages/estados/index.jsp");
			return;
		}
		
		Estado oEstado = new Estado("S", descripcion);
		
		var oEstadoCreado = ServiceEstado.crearEstado(oEstado);
		
		response.sendRedirect("/Proyecto-PInfra/pages/estados/index.jsp");
	}

}
