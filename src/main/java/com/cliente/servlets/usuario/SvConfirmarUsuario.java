package com.cliente.servlets.usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.dto.UsuarioDTO;
import com.cliente.services.ServiceJWT;
import com.cliente.services.ServiceUsuario;


@WebServlet(name = "ServletConfirmarUsuario", urlPatterns = "/SvConfirmarUsuario")
public class SvConfirmarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String documentoDeUsuario = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}

		if (request.getSession().getAttribute("mostrarModal") == null) {
			documentoDeUsuario = request.getParameter("cedula");
			Fabrica.generarModal(request, "/Proyecto-PInfra/SvConfirmarUsuario",
					"¿Está seguro de que no desea confirmar el usuario?", "Esta acción no se puede revertir", "GET");
			response.sendRedirect("/Proyecto-PInfra/pages/confirmacion/index.jsp");
			return;
		}

		UsuarioDTO oUsuarioAConfirmar = ServiceUsuario.listarFiltroDocumento(documentoDeUsuario).get(0);
		oUsuarioAConfirmar.setActivo("N");

		var oUsuarioConfirmado = ServiceUsuario.actualizarUsuario(oUsuarioAConfirmar);

		request.getSession().removeAttribute("mostrarModal");
		documentoDeUsuario = null;

		response.sendRedirect("/Proyecto-PInfra/pages/confirmacion/index.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}

		if (request.getSession().getAttribute("mostrarModal") == null) {
			documentoDeUsuario = request.getParameter("cedula");
			Fabrica.generarModal(request, "/Proyecto-PInfra/SvConfirmarUsuario",
					"¿Está seguro de que desea confirmar el usuario?", "Esta acción no se puede revertir", "POST");
			response.sendRedirect("/Proyecto-PInfra/pages/confirmacion/index.jsp");
			return;
		}

		UsuarioDTO oUsuarioAConfirmar = ServiceUsuario.listarFiltroDocumento(documentoDeUsuario).get(0);
		oUsuarioAConfirmar.setConfirmado("S");

		var oUsuarioConfirmado = ServiceUsuario.actualizarUsuario(oUsuarioAConfirmar);
		request.getSession().removeAttribute("mostrarModal");
		documentoDeUsuario = null;
		response.sendRedirect("/Proyecto-PInfra/pages/confirmacion/index.jsp");
	}

}
