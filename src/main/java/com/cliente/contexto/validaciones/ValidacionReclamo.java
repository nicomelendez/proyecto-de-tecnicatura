package com.cliente.contexto.validaciones;

import javax.servlet.http.HttpSession;

public class ValidacionReclamo {

	static public boolean validar(String titulo, String detalle, String eventoSeleccionado, HttpSession sesion) {

		boolean tituloValido = true;
		boolean detalleValido = true;
		boolean eventoValido = true;

		if (titulo == null || titulo.equals("") || titulo.length() < 6 || titulo.length() > 40) {
			sesion.setAttribute("errorTituloReclamo", "El titulo debe ser entre 6 y 40 caracteres.");
			tituloValido = false;
		}

		if (detalle == null || detalle.equals("") || detalle.length() < 10 || detalle.length() > 120) {
			sesion.setAttribute("errorDetalleReclamo", "El detalle debe ser entre 10 y 120 caracteres.");
			detalleValido = false;
		}

		if (eventoSeleccionado == null || eventoSeleccionado.equals("default")
				|| eventoSeleccionado.equals("Seleccione un evento")) {
			sesion.setAttribute("errorEventoReclamo", "Debe seleccionar un evento para el reclamo.");
			eventoValido = false;
		}

		if (tituloValido && detalleValido && eventoValido) {
			return true;
		} else {
			return false;
		}

	}

	static public boolean validarEstadoReclamo(String detalle, String estadoSeleccionado, HttpSession sesion) {

		boolean detalleValido = true;
		boolean estadoValido = true;

		if (detalle == null || detalle.equals("") || detalle.length() < 10 || detalle.length() > 120) {
			sesion.setAttribute("errorDetalleReclamo", "El detalle debe ser entre 10 y 120 caracteres.");
			detalleValido = false;
		}

		if (estadoSeleccionado == null || estadoSeleccionado.equals("default")
				|| estadoSeleccionado.equals("Seleccione un evento")) {
			sesion.setAttribute("errorEstadoReclamo", "Debe seleccionar un estado para el reclamo.");
			estadoValido = false;
		}

		if (detalleValido && estadoValido) {
			return true;
		} else {
			return false;
		}

	}
}
