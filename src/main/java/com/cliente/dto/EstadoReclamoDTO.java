package com.cliente.dto;

import java.io.Serializable;
import java.util.Date;

import com.servidor.entidades.EstadoReclamo;

public class EstadoReclamoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idReclamo;
	private long idAnalista;
	private Date fechaHora;
	private String detalle;
	private AnalistaDTO analista;
	private EstadoDTO estado;

	public EstadoReclamoDTO() {
		super();
	}

	public EstadoReclamoDTO(EstadoReclamo oEstadoReclamo) {
		this.idReclamo = oEstadoReclamo.getId().getIdReclamo();
		this.idAnalista = oEstadoReclamo.getId().getIdAnalista();
		this.fechaHora = oEstadoReclamo.getId().getFechaHora();
		this.detalle = oEstadoReclamo.getDetalle();
		this.analista = new AnalistaDTO(oEstadoReclamo.getAnalista());
		this.estado = new EstadoDTO(oEstadoReclamo.getEstado());

	}

	public long getIdReclamo() {
		return idReclamo;
	}

	public void setIdReclamo(long idReclamo) {
		this.idReclamo = idReclamo;
	}

	public long getIdAnalista() {
		return idAnalista;
	}

	public void setIdAnalista(long idAnalista) {
		this.idAnalista = idAnalista;
	}

	public java.util.Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(java.util.Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public AnalistaDTO getAnalista() {
		return analista;
	}

	public void setAnalista(AnalistaDTO analista) {
		this.analista = analista;
	}

	public EstadoDTO getEstado() {
		return estado;
	}

	public void setEstado(EstadoDTO estado) {
		this.estado = estado;
	}

}
