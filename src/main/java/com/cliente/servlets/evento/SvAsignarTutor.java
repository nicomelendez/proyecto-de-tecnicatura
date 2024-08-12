package com.cliente.servlets.evento;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletAsignarTutor", urlPatterns = "/SvAsignarTutor")
public class SvAsignarTutor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tutorSeleccionado = request.getParameter("tutorSeleccionado");
		System.out.println(tutorSeleccionado + "------------------");
		ArrayList<String> listaDeTutores = (ArrayList<String>) request.getSession()
				.getAttribute("tutoresSeleccionados");

		if (listaDeTutores == null) {
			ArrayList<String> lista = new ArrayList<String>();

			lista.add(tutorSeleccionado);

			request.getSession().setAttribute("tutoresSeleccionados", lista);
			response.sendRedirect("/Proyecto-PInfra/pages/eventos/index.jsp");
			return;
		}

		listaDeTutores.add(tutorSeleccionado);
		request.getSession().setAttribute("tutoresSeleccionados", listaDeTutores);
		response.sendRedirect("/Proyecto-PInfra/pages/eventos/index.jsp");
	}

}
