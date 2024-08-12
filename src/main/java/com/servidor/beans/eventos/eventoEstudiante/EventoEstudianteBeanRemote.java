package com.servidor.beans.eventos.eventoEstudiante;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.servidor.entidades.EventoEstudiante;
import com.servidor.exception.ServiciosException;

@Remote
public interface EventoEstudianteBeanRemote {

	boolean crearEventoEstudiante(EventoEstudiante oEventoEstudiante) throws ServiciosException;

	boolean actualizarEventoEstudiante(EventoEstudiante oEventoEstudiante) throws ServiciosException;

	boolean eliminarEventoEstudiante(Long idEventoEstudiante) throws ServiciosException;

	ArrayList<EventoEstudiante> listarEventosEstudiante() throws ServiciosException;

	ArrayList<EventoEstudiante> listarEventosEstudiante(String filtro) throws ServiciosException;
}
