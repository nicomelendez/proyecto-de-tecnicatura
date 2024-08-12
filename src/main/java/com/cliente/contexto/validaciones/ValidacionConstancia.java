package com.cliente.contexto.validaciones;

import javax.servlet.http.HttpSession;

public class ValidacionConstancia {
	public static boolean validar(String titulo, String detalle, String eventoSeleccionado, String tipoSeleccionado,
			HttpSession sesion) {
		boolean tituloValido = true;
		boolean detalleValido = true;
		boolean eventoValido = true;
		boolean tipoValido = true;

		if (titulo.equals("") || titulo.length() < 6 || titulo.length() > 40) {
			sesion.setAttribute("errorTituloConstancia", "El titulo debe ser entre 8 y 40 caracteres.");
			tituloValido = false;
		}

		if (detalle.equals("") || detalle.length() < 10 || detalle.length() > 120) {
			sesion.setAttribute("errorDetalleConstancia", "El detalle debe ser entre 10 y 120 caracteres.");
			detalleValido = false;
		}

		if (eventoSeleccionado == null || eventoSeleccionado.equals("default")
				|| eventoSeleccionado.equals("Seleccione un evento")) {
			sesion.setAttribute("errorEventoConstancia", "Debe seleccionar un evento para la constancia.");
			eventoValido = false;
		}

		if (tipoSeleccionado == null || tipoSeleccionado.equals("default")
				|| tipoSeleccionado.equals("Seleccione un tipo de constancia")) {
			sesion.setAttribute("errorTipoConstancia", "Debe seleccionar un tipo de constancia.");
			tipoValido = false;
		}

		if (tituloValido && detalleValido && eventoValido && tipoValido) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validarEstadoConstancia(String detalle, String estadoSeleccionado, HttpSession sesion) {
		boolean detalleValido = true;
		boolean estadoValido = true;

		if (detalle == null || detalle.trim().equals("") || detalle.length() < 10 || detalle.length() > 120) {
			sesion.setAttribute("errorDetalleConstancia", "El detalle debe ser entre 10 y 120 caracteres.");
			detalleValido = false;
		}

		if (estadoSeleccionado == null || detalle.equals("") || estadoSeleccionado.equals("default")
				|| estadoSeleccionado.equals("Seleccione un evento")) {
			sesion.setAttribute("errorEstadoConstancia", "Debe seleccionar un evento para la constancia.");
			estadoValido = false;
		}

		if (estadoValido && detalleValido) {
			return true;
		} else {
			return false;
		}
	}
}
