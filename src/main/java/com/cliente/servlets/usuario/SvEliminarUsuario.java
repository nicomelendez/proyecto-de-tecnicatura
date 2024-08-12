package com.cliente.servlets.usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.services.ServiceJWT;
import com.cliente.services.ServiceUsuario;

@WebServlet(name = "ServletEliminarUsuario", urlPatterns = "/SvEliminarUsuario")
public class SvEliminarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Long idUsuario = null;

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
		if (request.getSession().getAttribute("mostrarModal") == null) {
			idUsuario = Long.parseLong(request.getParameter("idUsuario"));

			Fabrica.generarModal(request, "/Proyecto-PInfra/SvEliminarUsuario",
					"¿Está seguro de que desea eliminar el usuario?", "Se dará de baja lógica el usuario.", "POST");
			response.sendRedirect(obtenerUrlAnterior(request, response));
			return;
		}
		boolean eliminado = ServiceUsuario.eliminar(idUsuario);
		if(eliminado) {
			eliminacionExitosa(request, response);
			idUsuario = null;
			return;
		} else {
			response.sendRedirect(obtenerUrlAnterior(request, response));
			return;
		}
	}
	
	private String obtenerUrlAnterior(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String urlAnterior = (String) request.getHeader("referer");
        return urlAnterior;
    }
	
	private void eliminacionExitosa(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Fabrica.limpiarMensajesDeError(request.getSession());
		request.getSession().removeAttribute("mostrarModal");
		request.getSession().setAttribute(("mostrarNotificacion"), true);
		request.getSession().setAttribute(("tituloNotificacion"), "¡Se eliminó con éxito!");
		request.getSession().setAttribute(("descripcionNotificacion"), "El usuario ha sido eliminado correctamente.");
		response.sendRedirect(obtenerUrlAnterior(request, response));
	}

}
