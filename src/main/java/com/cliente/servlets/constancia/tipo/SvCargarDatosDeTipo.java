package com.cliente.servlets.constancia.tipo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.services.ServiceTipo;
import com.servidor.entidades.Tipo;

@WebServlet(name = "ServletCargarDatosDeTipo", urlPatterns = "/SvCargarDatosDeTipo")
public class SvCargarDatosDeTipo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Fabrica.limpiarMensajesDeError(request.getSession());
		Long idTipo = Long.parseLong(request.getParameter("idTipo"));

		Tipo oTipoEditar = ServiceTipo.getById(idTipo);

		request.getSession().setAttribute("tipoEditar", oTipoEditar);
		response.sendRedirect("/Proyecto-PInfra/pages/constancias/tipos/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
