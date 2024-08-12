package com.cliente.contexto.validaciones;

import javax.servlet.http.HttpSession;

public class ValidacionTipoConstancia {
	public static boolean validar(String nombre, String descripcion, HttpSession sesion) {

		boolean nombreValido = true;
		boolean descripcionValido = true;

		if (nombre == null || nombre.equals("") || nombre.length() <= 5 || nombre.length() >= 40) {
			sesion.setAttribute("errorTipoConstanciaNombre", "El nombre debe ser entre 5 y 40 caracteres.");
			nombreValido = false;
		}

		if (descripcion == null || descripcion.equals("") || descripcion.length() < 50 || descripcion.length() > 2000) {
			sesion.setAttribute("errorTipoConstanciaDescripcion", "La descripción debe ser entre 50 y 2000 caracteres.");
			descripcionValido = false;
		}

		if (nombreValido && descripcionValido) {
			return true;
		} else {
			return false;
		}

	}
}
