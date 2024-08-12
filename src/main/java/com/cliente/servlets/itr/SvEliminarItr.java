package com.cliente.servlets.itr;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.services.ServiceItr;
import com.cliente.services.ServiceJWT;

@WebServlet(name = "ServletEliminarItr", urlPatterns = "/SvEliminarItr")
public class SvEliminarItr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Long idItr = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("itrEditar");
		request.getSession().removeAttribute("mostrarForm");
		Fabrica.limpiarMensajesDeError(request.getSession());
		response.sendRedirect("/Proyecto-PInfra/pages/itrs/index.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}

		request.setCharacterEncoding("UTF-8");
		if (request.getSession().getAttribute("mostrarModal") == null) {
			idItr = Long.parseLong(request.getParameter("idItr"));
			Fabrica.generarModal(request, "/Proyecto-PInfra/SvEliminarItr", "¿Está seguro que desea eliminar el ITR?",
					"El ITR se dará de baja lógica.", "POST");
			response.sendRedirect("/Proyecto-PInfra/pages/itrs/index.jsp");
			return;
		}

		ServiceItr.eliminar(idItr);
		request.getSession().removeAttribute("mostrarModal");
		idItr = null;
		response.sendRedirect("/Proyecto-PInfra/pages/itrs/index.jsp");

	}

}
