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
import com.servidor.entidades.Estado;

@WebServlet(name = "ServletEliminarEstado", urlPatterns = "/SvEliminarEstado")
public class SvEliminarEstado extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Long idEstado = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("estadoEditar");
		Fabrica.limpiarMensajesDeError(request.getSession());
		response.sendRedirect("/Proyecto-PInfra/pages/estados/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}
		
		request.setCharacterEncoding("UTF-8");
		
		if (request.getSession().getAttribute("mostrarModal") == null) {
			idEstado = Long.parseLong(request.getParameter("idEstado"));
			Fabrica.generarModal(request, "/Proyecto-PInfra/SvEliminarEstado",
					"¿Está seguro que desea eliminar el Estado?",
					"El Estado se dará de baja lógica.", "POST");
			response.sendRedirect("/Proyecto-PInfra/pages/estados/index.jsp");
			return;
		}
		
		ServiceEstado.eliminarEstado(idEstado);
		request.getSession().removeAttribute("mostrarModal");
		idEstado = null;
		response.sendRedirect("/Proyecto-PInfra/pages/estados/index.jsp");
	}

}
