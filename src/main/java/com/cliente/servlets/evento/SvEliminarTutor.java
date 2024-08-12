package com.cliente.servlets.evento;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletSvEliminarTutor", urlPatterns = "/SvEliminarTutor")
public class SvEliminarTutor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String documento = request.getParameter("documento");

		ArrayList<String> listaDeTutores = (ArrayList<String>) request.getSession()
				.getAttribute("tutoresSeleccionados");

		listaDeTutores.remove(documento);

		request.getSession().setAttribute("tutoresSeleccionados", listaDeTutores);
		response.sendRedirect("/Proyecto-PInfra/pages/eventos/index.jsp");
	}

}
