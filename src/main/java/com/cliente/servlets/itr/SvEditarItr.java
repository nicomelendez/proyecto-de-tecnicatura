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

@WebServlet(name = "ServletEditarItr", urlPatterns = "/SvEditarItr")
public class SvEditarItr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Long idItr = null;
	private String nombreItr = null;
	private String departamentoTexto = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		request.getSession().removeAttribute("mostrarForm");
		Fabrica.limpiarMensajesDeError(request.getSession());
		idItr = Long.parseLong(request.getParameter("idItr"));

		ItrDTO oItrEditar = ServiceItr.getById(idItr);

		request.getSession().setAttribute("itrEditar", oItrEditar);
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
		if (request.getSession().getAttribute("mostrarModal") == null) {
			nombreItr = request.getParameter("nombreItrEditar");
			departamentoTexto = request.getParameter("departamentoEditar");

			if (!ValidacionItr.validar(nombreItr, departamentoTexto, request.getSession())) {
				response.sendRedirect("/Proyecto-PInfra/pages/itrs/index.jsp");
				return;
			}

			Fabrica.generarModal(request, "/Proyecto-PInfra/SvEditarItr",
					"¿Está seguro que desea editar los datos del ITR?", "Modificará los datos anteriores del ITR.",
					"POST");
			response.sendRedirect("/Proyecto-PInfra/pages/itrs/index.jsp");
			return;
		}
		if (!ValidacionItr.validar(nombreItr, departamentoTexto, request.getSession())) {
			response.sendRedirect("/Proyecto-PInfra/pages/itrs/index.jsp");
			return;
		}

		DepartamentoDTO departamento = ServiceUbicacion.listarDepartamentosFiltro(departamentoTexto).get(0);

		ItrDTO oItrAntiguo = (ItrDTO) request.getSession().getAttribute("itrEditar");

		ItrDTO oItrEditado = new ItrDTO(nombreItr, departamento, "S");
		oItrEditado.setIdItr(oItrAntiguo.getIdItr());

		ServiceItr.actualizar(oItrEditado);

		request.getSession().removeAttribute("itrEditar");
		nombreItr = null;
		departamentoTexto = null;
		idItr = null;
		request.getSession().removeAttribute("mostrarModal");
		response.sendRedirect("/Proyecto-PInfra/pages/itrs/index.jsp");

	}

}
