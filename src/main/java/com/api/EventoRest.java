package com.api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cliente.dto.EventoDTO;
import com.cliente.services.ServiceEvento;
import com.servidor.entidades.Evento;

@Path("eventos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventoRest {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<EventoDTO> getReclamos() {
		ArrayList<Evento> eventos = ServiceEvento.listarEventos();
		ArrayList<EventoDTO> listaDeEventos = new ArrayList<EventoDTO>();

		// Convertimos cada Evento a EventoDTO
		for (Evento evento : eventos) {
			EventoDTO eventoDTO = new EventoDTO(evento);
			listaDeEventos.add(eventoDTO);
		}

		return listaDeEventos;
	}
}
