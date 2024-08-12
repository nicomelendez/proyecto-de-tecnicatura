package com.cliente.services;

import java.util.ArrayList;
import javax.naming.InitialContext;

import com.servidor.beans.eventos.eventoEstudiante.EventoEstudianteBeanRemote;
import com.servidor.entidades.EventoEstudiante;

public class ServiceEventoEstudiante {

	private static EventoEstudianteBeanRemote getService() {
		try {
			EventoEstudianteBeanRemote eventoEstudianteBean = (EventoEstudianteBeanRemote) InitialContext.doLookup(
					"ejb:/Proyecto-PInfra/EventoEstudianteBean!com.servidor.beans.eventos.eventoEstudiante.EventoEstudianteBeanRemote");
			return eventoEstudianteBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean crearEventoEstudiante(EventoEstudiante oEventoEstudiante) {
		try {
			var eventoEstudianteBean = getService();
			return eventoEstudianteBean.crearEventoEstudiante(oEventoEstudiante);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean actualizarEventoEstudiante(EventoEstudiante oEventoEstudiante) {
		try {
			var eventoEstudianteBean = getService();
			return eventoEstudianteBean.actualizarEventoEstudiante(oEventoEstudiante);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean eliminarEventoEstudiante(Long id) {
		try {
			var eventoEstudianteBean = getService();
			return eventoEstudianteBean.eliminarEventoEstudiante(id);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<EventoEstudiante> listarEventoEstudiante() {
		try {
			var eventoEstudianteBean = getService();
			return eventoEstudianteBean.listarEventosEstudiante();
		} catch (Exception e) {
			return null;
		}
	}

}
