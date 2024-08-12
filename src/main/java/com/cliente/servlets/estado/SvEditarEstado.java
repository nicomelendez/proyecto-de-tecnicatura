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
import com.servidor.entidades.Tipo;

@WebServlet(name = "ServletEditarEstado", urlPatterns = "/SvEditarEstado")
public class SvEditarEstado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private String descripcion = null;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Estado oEstadoEditar = (Estado) request.getSession().getAttribute("estadoEditar");
	
		oEstadoEditar.setDescripcion(descripcion);
		
		var estadoEditado = ServiceEstado.actualizarEstado(oEstadoEditar);
	
		descripcion = null;
		Fabrica.limpiarMensajesDeError(request.getSession());
		request.getSession().removeAttribute("estadoEditar");
		request.getSession().removeAttribute("mostrarModal");
		response.sendRedirect("/Proyecto-PInfra/pages/estados/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
	}
	request.setCharacterEncoding("UTF-8");
	Fabrica.limpiarMensajesDeError(request.getSession());
	descripcion = request.getParameter("descripcion");
	boolean esValido = ValidacionEstado.validar(descripcion, request.getSession());
	
	if (!esValido) {
		response.sendRedirect("/Proyecto-PInfra/pages/estados/index.jsp");
		return;
	}
	if (request.getSession().getAttribute("mostrarModal") == null) {

		Fabrica.generarModal(request, "/Proyecto-PInfra/SvEditarEstado",
				"¿Está seguro que desea editar los datos del Estado?",
				"Modificará los datos anteriores del Estado", "GET");
		response.sendRedirect("/Proyecto-PInfra/pages/estados/index.jsp");
		return;
	}
}
}
