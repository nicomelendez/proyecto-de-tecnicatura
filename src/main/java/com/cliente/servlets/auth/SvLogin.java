package com.cliente.servlets.auth;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cliente.contexto.Fabrica;
import com.cliente.dto.UsuarioDTO;
import com.cliente.services.ServiceJWT;
import com.cliente.services.ServiceUsuario;
import com.servidor.utils.Respuesta;

@WebServlet(name = "ServletLogin", urlPatterns = { "/Login" })

public class SvLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SvLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombreUsuario = request.getParameter("nombreUsuario");
		String clave = request.getParameter("clave");

		if (nombreUsuario == null || clave == null || nombreUsuario.trim().isEmpty() || clave.trim().isEmpty()) {
			String mensajeError = "Debe completar todos los campos para ingresar a su cuenta.";
			request.setAttribute("mensajeError", mensajeError);
			System.out.println(request.getContextPath());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/login/index.jsp");

			requestDispatcher.forward(request, response);

			return;
		}

		String hash = Fabrica.getSHA256Hash(clave);

		Respuesta oRespuesta = ServiceUsuario.login(nombreUsuario, hash);

		if (oRespuesta == null || oRespuesta.getStatus().equals("error")) {
			String mensajeError = oRespuesta != null ? oRespuesta.getMessage() : "Datos incorrectos";
			request.setAttribute("mensajeError", mensajeError);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/login/index.jsp");

			requestDispatcher.forward(request, response);

			return;
		}

		if (oRespuesta.getStatus().equals("success")) {
			UsuarioDTO oUsuario = (UsuarioDTO) oRespuesta.getData();
			String token = ServiceJWT.generarToken(oUsuario, getFechaExpiracion());

			HttpSession session = request.getSession(true);
			session.setAttribute("token", token);
			session.setAttribute("usuarioLogueado", oUsuario);
		}

		response.sendRedirect("index.jsp");
	}

	private Date getFechaExpiracion() {
		Date fechaInicio = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaInicio);
		calendar.add(Calendar.HOUR_OF_DAY, 24);
		Date fechaFin = calendar.getTime();
		return fechaFin;
	}
}
