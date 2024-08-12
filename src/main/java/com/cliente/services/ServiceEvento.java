package com.cliente.services;

import java.util.ArrayList;

import javax.naming.InitialContext;

import com.servidor.beans.eventos.EventoBeanRemote;
import com.servidor.entidades.Evento;
import com.servidor.entidades.Tutor;

public class ServiceEvento {

	private static EventoBeanRemote getService() {
		try {
			EventoBeanRemote eventoBean = (EventoBeanRemote) InitialContext
					.doLookup("ejb:/Proyecto-PInfra/EventoBean!com.servidor.beans.eventos.EventoBeanRemote");
			return eventoBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static Evento crearEvento(Evento oEvento) {
		try {
			var eventoBean = getService();
			return eventoBean.crearEvento(oEvento);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<Evento> listarEventos() {
		try {
			var eventoBean = getService();
			return eventoBean.listarEventos();
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<Evento> listarEventosFiltro(String filtro) {
		try {
			var eventoBean = getService();
			return eventoBean.listarEventosFiltro(filtro);
		} catch (Exception e) {
			return null;
		}
	}

	public static Evento buscarEventoPorId(Long id) {
		try {
			var eventoBean = getService();
			return eventoBean.buscarEventoPorId(id);
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean asignarTutorAEvento(Evento oEvento, Tutor oTutor) {
		try {
			var eventoBean = getService();
			return eventoBean.asignarTutorAEvento(oEvento, oTutor);
		} catch (Exception e) {
			return false;
		}
	}
}
