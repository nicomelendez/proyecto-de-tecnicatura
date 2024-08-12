package com.cliente.contexto.validaciones;

import javax.servlet.http.HttpSession;

public class ValidacionEstado {
	public static boolean validar(String descripcion, HttpSession sesion) {

		boolean descripcionValido = true;

		if (descripcion == null || descripcion.trim().equals("") || descripcion.length() > 30 || descripcion.length() < 1) {
			sesion.setAttribute("errorEstadoDescripcion", "El nombre debe ser entre 1 y 30 caracteres.");
			descripcionValido = false;
		} else {
			descripcionValido = true;
		}
		return descripcionValido;

	}
}
