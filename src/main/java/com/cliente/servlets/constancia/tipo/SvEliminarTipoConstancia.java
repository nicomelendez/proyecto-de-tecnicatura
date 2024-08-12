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

@WebServlet(name = "ServletEliminarTipoConstancia", urlPatterns = "/SvEliminarTipoConstancia")
public class SvEliminarTipoConstancia extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Long idTipo = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Fabrica.limpiarMensajesDeError(request.getSession());
		request.getSession().removeAttribute("tipoEditar");
		response.sendRedirect("/Proyecto-PInfra/pages/constancias/tipos/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}

		request.setCharacterEncoding("UTF-8");

		if (request.getSession().getAttribute("mostrarModal") == null) {
			idTipo = Long.parseLong(request.getParameter("idTipo"));
			Fabrica.generarModal(request, "/Proyecto-PInfra/SvEliminarTipoConstancia",
					"¿Está seguro que desea eliminar el tipo de constancia?",
					"El Tipo de Constancia se dará de baja lógica.", "POST");
			response.sendRedirect("/Proyecto-PInfra/pages/constancias/tipos/index.jsp");
			return;
		}

		ServiceTipo.eliminarTipoConstancia(idTipo);
		request.getSession().removeAttribute("mostrarModal");
		idTipo = null;
		response.sendRedirect("/Proyecto-PInfra/pages/constancias/tipos/index.jsp");
	}

}
