package com.cliente.servlets.itr;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.dto.ItrDTO;
import com.cliente.services.ServiceItr;

@WebServlet(name = "ServletReactivarItr", urlPatterns = "/SvReactivarItr")
public class SvReactivarItr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String idItr = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("mostrarModal") == null) {

			idItr = request.getParameter("idItr");

			Fabrica.generarModal(request, "/Proyecto-PInfra/SvReactivarItr", "¿Está seguro que desea reactivar el ITR?",
					"El ITR volverá a estar activo en la plataforma.", "POST");
			response.sendRedirect("/Proyecto-PInfra/pages/itrs/index.jsp");
			return;
		}
		ItrDTO oItr = ServiceItr.getById(Long.parseLong(idItr));
		oItr.setActivo("S");
		ItrDTO oItrReactivada = ServiceItr.actualizar(oItr);
		idItr = null;
		request.getSession().removeAttribute("mostrarModal");
		response.sendRedirect("/Proyecto-PInfra/pages/itrs/index.jsp");

	}

}
