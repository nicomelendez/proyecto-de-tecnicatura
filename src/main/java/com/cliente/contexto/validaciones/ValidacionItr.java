package com.cliente.contexto.validaciones;

import javax.servlet.http.HttpSession;

public class ValidacionItr {
	public static boolean validar(String nombre, String departamento, HttpSession sesion) {

		boolean nombreValido = true;
		boolean departamentoValido = true;

		if (nombre.equals("") || nombre.length() > 20 || nombre.length() < 3) {
			sesion.setAttribute("errorNombreItr", "El nombre debe ser entre 3 y 20 caracteres.");
			nombreValido = false;
		}

		if (departamento.equals("Seleccione un departamento")) {
			sesion.setAttribute("errorDepartamentoItr", "Debe seleccionar un departamento.");
			departamentoValido = false;
		}

		if (departamentoValido && nombreValido) {
			return true;
		} else {
			return false;
		}

	}
}
