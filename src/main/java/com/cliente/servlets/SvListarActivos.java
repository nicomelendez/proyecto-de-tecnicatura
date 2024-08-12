package com.cliente.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SvListarActivos
 */

@WebServlet(name="ServletListarActivos", urlPatterns = "/SvListarActivos")
public class SvListarActivos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvListarActivos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtro = request.getParameter("filtro");
		String urlAnterior = (String) request.getHeader("referer");
		request.getSession().removeAttribute("errorFiltro");
		if (filtro.equals("D")) {
			request.getSession().setAttribute("errorFiltro", "Debe seleccionar un filtro.");
			response.sendRedirect(urlAnterior);
			return;
		}
		request.getSession().setAttribute("filtroActivo", filtro);
		response.sendRedirect(urlAnterior);
	}

}
