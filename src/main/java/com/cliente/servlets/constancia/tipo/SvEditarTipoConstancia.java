package com.cliente.servlets.constancia.tipo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.contexto.validaciones.ValidacionTipoConstancia;
import com.cliente.services.ServiceJWT;
import com.cliente.services.ServiceTipo;
import com.servidor.entidades.Tipo;

@WebServlet(name = "ServletEditarTipoConstancia", urlPatterns = "/SvEditarTipoConstancia")
public class SvEditarTipoConstancia extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String nombre = null;
	private String descripcion = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Tipo oTipoEditar = (Tipo) request.getSession().getAttribute("tipoEditar");

		oTipoEditar.setNombre(nombre);
		oTipoEditar.setDescripcion(descripcion);

		var tipoEditado = ServiceTipo.actualizarTipoConstancia(oTipoEditar);

		nombre = null;
		descripcion = null;
		Fabrica.limpiarMensajesDeError(request.getSession());
		request.getSession().removeAttribute("tipoEditar");
		request.getSession().removeAttribute("mostrarModal");
		response.sendRedirect("/Proyecto-PInfra/pages/constancias/tipos/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		Fabrica.limpiarMensajesDeError(request.getSession());
		nombre = request.getParameter("nombre");
		descripcion = request.getParameter("descripcion");
		boolean esValido = ValidacionTipoConstancia.validar(nombre, descripcion, request.getSession());

		if (!esValido) {
			response.sendRedirect("/Proyecto-PInfra/pages/constancias/tipos/index.jsp");
			return;
		}

		if (request.getSession().getAttribute("mostrarModal") == null) {

			Fabrica.generarModal(request, "/Proyecto-PInfra/SvEditarTipoConstancia",
					"¿Está seguro que desea editar los datos del Tipo de Constancia?",
					"Modificará los datos anteriores del Tipo de Constancia", "GET");
			response.sendRedirect("/Proyecto-PInfra/pages/constancias/tipos/index.jsp");
			return;
		}

	}

}
