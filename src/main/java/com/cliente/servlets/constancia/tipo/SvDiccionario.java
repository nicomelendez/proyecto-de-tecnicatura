package com.cliente.servlets.constancia.tipo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletDiccionario", urlPatterns = "/SvDiccionario")
public class SvDiccionario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urlAnterior = (String) request.getHeader("referer");

		request.getSession().removeAttribute("mostrarDiccionario");

		response.sendRedirect(urlAnterior);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urlAnterior = (String) request.getHeader("referer");

		request.getSession().setAttribute("mostrarDiccionario", true);

		response.sendRedirect(urlAnterior);
	}

}
