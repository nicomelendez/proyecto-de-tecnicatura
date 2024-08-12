package com.cliente.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.servidor.entidades.Evento;

public class EventoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idEvento;

	private Timestamp fechaHoraFin;

	private Timestamp fechaHoraInicio;

	private String titulo;

	public EventoDTO(Timestamp fechaHoraFin, Timestamp fechaHoraInicio, String titulo) {
		super();
		this.fechaHoraFin = fechaHoraFin;
		this.fechaHoraInicio = fechaHoraInicio;
		this.titulo = titulo;
	}

	public EventoDTO(Evento oEvento) {
		super();
		this.idEvento = oEvento.getIdEvento();
		this.fechaHoraFin = oEvento.getFechaHoraFin();
		this.fechaHoraInicio = oEvento.getFechaHoraInicio();
		this.titulo = oEvento.getTitulo();
	}

	public long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(long idEvento) {
		this.idEvento = idEvento;
	}

	public Timestamp getFechaHoraFin() {
		return fechaHoraFin;
	}

	public void setFechaHoraFin(Timestamp fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	public Timestamp getFechaHoraInicio() {
		return fechaHoraInicio;
	}

	public void setFechaHoraInicio(Timestamp fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
