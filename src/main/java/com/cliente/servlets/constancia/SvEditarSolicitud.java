package com.cliente.servlets.constancia;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cliente.contexto.Fabrica;
import com.cliente.contexto.validaciones.ValidacionConstancia;
import com.cliente.services.ServiceConstancia;
import com.cliente.services.ServiceEstadoConstancia;
import com.cliente.services.ServiceEvento;
import com.cliente.services.ServiceJWT;
import com.cliente.services.ServiceTipo;
import com.servidor.entidades.Constancia;
import com.servidor.entidades.EstadoConstancia;
import com.servidor.entidades.Evento;
import com.servidor.entidades.Tipo;

@WebServlet(name = "ServletEditarSolicitud", urlPatterns = "/SvEditarSolicitud")
public class SvEditarSolicitud extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Long idSolicitud;
	private String titulo = null;
	private String detalleConstancia = null;
	private String evento = null;
	private String tipo =null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		Fabrica.limpiarMensajesDeError(request.getSession());
		idSolicitud = Long.parseLong(request.getParameter("idSolicitud"));
		String tipo = request.getParameter("tipo");

		var oSolicitudAEditar = ServiceConstancia.getById(idSolicitud);

		HttpSession sesion = request.getSession();

		if (tipo.equals("Ver")) {
			sesion.setAttribute("oSolicitudVer", oSolicitudAEditar);
			response.sendRedirect("/Proyecto-PInfra/pages/constancias/info/index.jsp");
		}
		if (tipo.equals("Solicitud")) {
			sesion.setAttribute("oSolicitudAEditar", oSolicitudAEditar);
			response.sendRedirect("/Proyecto-PInfra/pages/constancias/editar/index.jsp");
		}
		if (tipo.equals("Estado")) {
			sesion.setAttribute("oSolicitudAEditar", oSolicitudAEditar);
			response.sendRedirect("/Proyecto-PInfra/pages/constancias/estado/index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}
		
		if (request.getSession().getAttribute("mostrarModal") == null) {
		 titulo = request.getParameter("titulo");
		 detalleConstancia = request.getParameter("detalle");
		 evento = request.getParameter("evento");
		 tipo = request.getParameter("tipo");
		 boolean esValido = ValidacionConstancia.validar(titulo, detalleConstancia, evento, tipo, request.getSession());
		 
		 if (!esValido) {
			 System.out.println("--------------");
			 System.out.println("Holaaaaaaaaaaaa");
			response.sendRedirect("/Proyecto-PInfra/pages/constancias/editar/index.jsp");
			return;
		}else {
			Fabrica.generarModal(request, "/Proyecto-PInfra/SvEditarSolicitud", "¿Está seguro de que desea modificar su su solicitud de constancia?", "La solicitud será modificada", "POST");
			response.sendRedirect("/Proyecto-PInfra/pages/constancias/editar/index.jsp");
			return;	
		}
		}
		
		Constancia oSolicitudAEditar = (Constancia) request.getSession().getAttribute("oSolicitudAEditar");
		Evento oEvento = ServiceEvento.buscarEventoPorId(Long.valueOf(evento));
		Tipo oTipo = ServiceTipo.getById(Long.valueOf(tipo));

		oSolicitudAEditar.setDetalle(titulo);
		oSolicitudAEditar.setEvento(oEvento);
		oSolicitudAEditar.setTipo(oTipo);

		boolean constanciaEditada = ServiceConstancia.actualizarConstancia(oSolicitudAEditar);

		for (EstadoConstancia oEstadoConstancia : ServiceEstadoConstancia
				.listarEstadosConstanciasPorId(oSolicitudAEditar.getIdConstancia())) {
			oEstadoConstancia.setDetalle(detalleConstancia);
			boolean editado = ServiceEstadoConstancia.actualizarEstadoConstancia(oEstadoConstancia);
		}
		
		request.getSession().removeAttribute("mostrarModal");
		request.getSession().removeAttribute("oSolicitudAEditar");
		titulo = null;
		detalleConstancia = null;
		evento = null;
		tipo = null;
		response.sendRedirect("/Proyecto-PInfra/pages/constancias/listar/index.jsp");
	}

}
