package com.cliente.servlets.constancia;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.contexto.validaciones.ValidacionUsuario;
import com.cliente.services.ServiceConstancia;
import com.cliente.services.ServiceJWT;
import com.servidor.entidades.Constancia;

@WebServlet(name = "ServletBuscarSolicitudes", urlPatterns = "/SvBuscarSolicitudes")
public class SvBuscarSolicitudes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("solicitudesDeUnEstudiante");
		response.sendRedirect("/Proyecto-PInfra/pages/constancias/solicitudes/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		Fabrica.limpiarMensajesDeError(request.getSession());
		String documento = request.getParameter("documento");
		String mes = request.getParameter("mes");
		String anio = request.getParameter("anio");

		boolean esValido = ValidacionUsuario.validarCedula(documento, request.getSession());
		boolean esValidoComboBox = validarMesAnio(mes, anio, request);
		if (!esValido || !esValidoComboBox) {
			request.getSession().removeAttribute("solicitudesDeUnEstudiante");
			response.sendRedirect("/Proyecto-PInfra/pages/constancias/solicitudes/index.jsp");
			return;
		}

		ArrayList<Constancia> lista = ServiceConstancia.listarConstanciasEstudiantePorMesYAnio(
				new BigDecimal(documento), Integer.parseInt(mes), Integer.parseInt(anio));

		request.getSession().setAttribute("solicitudesDeUnEstudiante", "ahorasi");
		request.getSession().setAttribute("listaSolicitudesDeUnEstudiante", lista);
		response.sendRedirect("/Proyecto-PInfra/pages/constancias/solicitudes/index.jsp");
	}

	public boolean validarMesAnio(String mes, String anio, HttpServletRequest request) {
		boolean mesValido = true;
		boolean anioValido = true;

		if (mes == null || mes.equals("") || mes.equals("default") || mes.equals("Seleccione un mes")) {
			request.getSession().setAttribute("errorMes", "Debe seleccionar un mes");
			mesValido = false;
		}

		if (anio == null || anio.equals("") || anio.equals("default") || anio.equals("Seleccione un año")) {
			request.getSession().setAttribute("errorAnio", "Debe seleccionar un año");
			anioValido = false;
		}

		if (mesValido && anioValido) {
			return true;
		} else {
			return false;
		}
	}
}
