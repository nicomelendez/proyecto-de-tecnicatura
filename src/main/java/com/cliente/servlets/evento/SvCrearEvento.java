package com.cliente.servlets.evento;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.cliente.contexto.Fabrica;
import com.cliente.contexto.validaciones.ValidacionEvento;
import com.cliente.services.ServiceEvento;
import com.cliente.services.ServiceItr;
import com.cliente.services.ServiceTutor;
import com.servidor.entidades.Evento;
import com.servidor.entidades.Itr;
import com.servidor.entidades.Tutor;

@WebServlet(name = "ServletCrearEvento", urlPatterns = "/SvCrearEvento")
public class SvCrearEvento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Fabrica.limpiarMensajesDeError(request.getSession());
		String tituloEvento = request.getParameter("tituloEvento");
		String fechaInicioStr = request.getParameter("fechaInicio") + ":00";
		String fechaFinStr = request.getParameter("fechaFin") + ":00";
		String itrSeleccionado = request.getParameter("itrEvento");

		ArrayList<String> listaDeDocumentos = request.getSession().getAttribute("tutoresSeleccionados") != null
				? (ArrayList) request.getSession().getAttribute("tutoresSeleccionados")
				: new ArrayList<String>();

		// Validar los datos del evento
		boolean esValido = ValidacionEvento.validar(tituloEvento, fechaInicioStr, fechaFinStr, listaDeDocumentos,
				itrSeleccionado, request.getSession());

		if (!esValido) {
			response.sendRedirect("/Proyecto-PInfra/pages/eventos/index.jsp");
			return;
		}
		// Formato de fecha y hora para analizar la cadena
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

		// Convertir las cadenas de fecha y hora en objetos LocalDateTime
		LocalDateTime fechaInicioDateTime = LocalDateTime.parse(fechaInicioStr, formatter);
		LocalDateTime fechaFinDateTime = LocalDateTime.parse(fechaFinStr, formatter);

		// Convertir los objetos LocalDateTime en objetos Timestamp
		Timestamp fechaInicio = Timestamp.valueOf(fechaInicioDateTime);
		Timestamp fechaFin = Timestamp.valueOf(fechaFinDateTime);

		Itr oItrEvento = new Itr(ServiceItr.getById(Long.valueOf(itrSeleccionado)));

		Evento oEvento = new Evento(fechaFin, fechaInicio, tituloEvento, oItrEvento);

		ArrayList<Tutor> listaDeTutores = new ArrayList<Tutor>();

		for (String documento : listaDeDocumentos) {

			Tutor oTutorNuevo = new Tutor(ServiceTutor.getByDocumento(documento));
			listaDeTutores.add(oTutorNuevo);
		}

		try {
			// Llamar al método para crear un evento
			boolean creado = crearUnEvento(oEvento, listaDeTutores);

			if (creado) {
				registroExitoso(request, response);
				return;
			} else {
				response.sendRedirect("/Proyecto-PInfra/pages/eventos/index.jsp");
			}
		} catch (Exception e) {
			response.sendRedirect("/Proyecto-PInfra/pages/eventos/index.jsp");
		}

	}

	protected boolean crearUnEvento(Evento oEvento, ArrayList<Tutor> listaDeTutores) {
		try {
			var oEventoCreado = ServiceEvento.crearEvento(oEvento);
			System.out.println(oEventoCreado.getIdEvento());
			if (oEventoCreado != null) {
				boolean valor = false;
				for (Tutor tutor : listaDeTutores) {
					valor = ServiceEvento.asignarTutorAEvento(oEventoCreado, tutor);
				}
				return valor;
			}

			return false;
		} catch (Exception e) {

			return false;
		}
	}

	private void registroExitoso(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Fabrica.limpiarMensajesDeError(request.getSession());
		request.getSession().setAttribute(("mostrarNotificacion"), true);
		request.getSession().setAttribute(("tituloNotificacion"), "¡Se creó con éxito!");
		request.getSession().setAttribute(("descripcionNotificacion"), "El evento ha sido creado correctamente.");
		request.getSession().removeAttribute("tutoresSeleccionados");
		response.sendRedirect("/Proyecto-PInfra/pages/eventos/index.jsp");
	}
}
