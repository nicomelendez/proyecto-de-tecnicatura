package com.cliente.contexto.validaciones;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

public class ValidacionEvento {
	public static boolean validar(String titulo, String fechaInicioStr, String fechaFinStr,
			ArrayList<String> listaDeTutores, String itrSeleccionado, HttpSession sesion) {
		boolean tituloValido = true;
		boolean fechaInicioValido = true;
		boolean fechaFinValido = true;
		boolean listaDeTutoresValido = true;
		boolean itrValido = true;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

		if (titulo.equals("") || titulo.length() < 5 || titulo.length() > 40) {
			sesion.setAttribute("errorTituloEvento", "El titulo debe ser entre 5 y 40 caracteres.");
			tituloValido = false;
		}
		if (itrSeleccionado == null || itrSeleccionado.equals("") || itrSeleccionado.equals("default")
				|| itrSeleccionado.equals("Seleccione un ITR")) {
			sesion.setAttribute("errorItrEvento", "Debe seleccionar un ITR.");
			itrValido = false;
		}
		LocalDateTime fechaInicio = null;
		LocalDateTime fechaFin = null;

		if (fechaInicioStr == null || fechaInicioStr.equals(":00")) {
			sesion.setAttribute("errorFechaInicioEvento", "La fecha de inicio es obligatoria.");
			fechaInicioValido = false;
		}

		if (fechaFinStr == null || fechaFinStr.equals(":00")) {
			sesion.setAttribute("errorFechaFinEvento", "La fecha de fin es obligatoria.");
			fechaInicioValido = false;
		}

		try {
			if (fechaInicioStr != null && !fechaInicioStr.isEmpty()) {
				fechaInicio = LocalDateTime.parse(fechaInicioStr, formatter);
			} else {
				fechaInicioValido = false;
				sesion.setAttribute("errorFechaInicioEvento", "La fecha de inicio es obligatoria.");
			}

			if (fechaFinStr != null && !fechaFinStr.isEmpty()) {
				fechaFin = LocalDateTime.parse(fechaFinStr, formatter);
			} else {
				fechaFinValido = false;
				sesion.setAttribute("errorFechaFinEvento", "La fecha de fin es obligatoria.");
			}

			if (fechaInicio != null && fechaFin != null && fechaInicio.isAfter(fechaFin)) {
				fechaInicioValido = false;
				fechaFinValido = false;
				sesion.setAttribute("errorFechaEvento", "La fecha de inicio no puede ser posterior a la fecha de fin.");
			}
		} catch (DateTimeParseException e) {
			fechaInicioValido = false;
			fechaFinValido = false;
			sesion.setAttribute("errorFechaParse",
					"Error al parsear las fechas. Asegúrese de que las fechas sean correctas.");
		}

		if (listaDeTutores == null || listaDeTutores.isEmpty()) {
			sesion.setAttribute("errorListaDeTutores", "Debe seleccionar al menos un tutor.");
			listaDeTutoresValido = false;
		}

		return tituloValido && fechaInicioValido && fechaFinValido && listaDeTutoresValido;
	}

}
