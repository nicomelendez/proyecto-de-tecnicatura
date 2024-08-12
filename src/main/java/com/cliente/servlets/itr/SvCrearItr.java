package com.cliente.servlets.itr;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.contexto.validaciones.ValidacionItr;
import com.cliente.dto.DepartamentoDTO;
import com.cliente.dto.ItrDTO;
import com.cliente.services.ServiceItr;
import com.cliente.services.ServiceJWT;
import com.cliente.services.ServiceUbicacion;

@WebServlet(name = "ServletCrearItr", urlPatterns = "/SvCrearItr")
public class SvCrearItr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Fabrica.limpiarMensajesDeError(request.getSession());
		request.getSession().setAttribute("mostrarForm", true);
		response.sendRedirect("/Proyecto-PInfra/pages/itrs/index.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}

		request.setCharacterEncoding("UTF-8");

		Fabrica.limpiarMensajesDeError(request.getSession()); 

		String nombreItr = request.getParameter("nombreItr");
		String departamento = request.getParameter("departamento");

		if (!ValidacionItr.validar(nombreItr, departamento, request.getSession())) {
			response.sendRedirect("/Proyecto-PInfra/pages/itrs/index.jsp");
			return;
		}

		DepartamentoDTO oDepartamento = ServiceUbicacion.listarDepartamentosFiltro(departamento).get(0);

		ItrDTO oItrNuevo = new ItrDTO(nombreItr, oDepartamento, "S");

		ServiceItr.crear(oItrNuevo);

		request.getSession().removeAttribute("mostrarForm");
		response.sendRedirect("/Proyecto-PInfra/pages/itrs/index.jsp");

	}

}
