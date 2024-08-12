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

@WebServlet(name = "ServletCrearTipoConstancia", urlPatterns = "/SvCrearTipoConstancia")
public class SvCrearTipoConstancia extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		Fabrica.limpiarMensajesDeError(request.getSession());

		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");

		boolean esValido = ValidacionTipoConstancia.validar(nombre, descripcion, request.getSession());

		if (!esValido) {
			response.sendRedirect("/Proyecto-PInfra/pages/constancias/tipos/index.jsp");
			return;
		}

		Tipo oTipo = new Tipo(nombre, "S", descripcion);

		var oTipoCreado = ServiceTipo.crearTipoConstancia(oTipo);

		response.sendRedirect("/Proyecto-PInfra/pages/constancias/tipos/index.jsp");
	}
}
