package com.cliente.contexto.validaciones;

import javax.servlet.http.HttpSession;

public class ValidacionComboBoxes {
	public static boolean validar(String comboIndexDepartamento, String comboIndexGenero, String comboIndexLocalidad,
			String comboIndexItr, String comboIndexGeneracion, String comboIndexSemestre, String comboIndexArea,
			String comboIndexRol, HttpSession sesion) {

		boolean comboGenero = true;
		boolean comboDepartamento = true;
		boolean comboLocalidad = true;
		boolean comboItr = true;
		boolean comboGeneracion = true;
		boolean comboSemestre = true;
		boolean comboArea = true;
		boolean comboRol = true;

		if (comboIndexRol.equals("Seleccione su rol")) {
			sesion.setAttribute("errorRol", "Debe seleccionar un rol.");
			comboRol = false;
		}

		if (comboIndexRol.equals("Tutor") || comboIndexRol.equals("Encargado")) {

			if (comboIndexArea.equals("Seleccione su área")) {
				sesion.setAttribute("errorArea", "Debe seleccionar un área.");
				comboArea = false;
			}

		}

		if (comboIndexRol.equals("Estudiante")) {

			if (comboIndexGeneracion.equals("Seleccione su generación")) {
				sesion.setAttribute("errorGeneracion", "Debe seleccionar una generación.");
				comboGeneracion = false;
			}
			if (comboIndexSemestre.equals("Seleccione su semestre")) {
				sesion.setAttribute("errorSemestre", "Debe seleccionar un semestre.");
				comboSemestre = false;
			}
		}

		if (comboIndexDepartamento.equals("Selecciona un departamento")) {
			sesion.setAttribute("errorDepartamento", "Debe elegir un deparatamento");
			comboDepartamento = false;
		}
		if (comboIndexGenero.equals("Selecciona un género")) {
			sesion.setAttribute("errorGenero", "Debe elegir un género");
			comboGenero = false;
		}
		if (comboIndexLocalidad == null || comboIndexLocalidad.equals("Selecciona una localidad")) {
			sesion.setAttribute("errorLocalidad", "Debe elegir una localidad");
			comboLocalidad = false;
		}
		if (comboIndexItr.equals("Selecciona un ITR")) {
			sesion.setAttribute("errorItr", "Debe elegir un ITR");
			comboItr = false;
		}

		if (comboGeneracion && comboItr && comboLocalidad && comboGenero && comboDepartamento && comboSemestre
				&& comboArea && comboRol) {
			return true;
		} else {
			return false;
		}
	}
}
