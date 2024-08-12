package com.cliente.dto;

import java.io.Serializable;

public class EstadoConstanciaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String analista;
	private String estado;
	private String fechaEmision;

	public EstadoConstanciaDTO() {
	}

	public EstadoConstanciaDTO(String analista, String estado, String fechaEmision) {
		super();
		this.analista = analista;
		this.estado = estado;
		this.fechaEmision = fechaEmision;
	}

	public String getAnalista() {
		return analista;
	}

	public void setAnalista(String analista) {
		this.analista = analista;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	@Override
	public String toString() {
		return "EstadoConstanciaDTO [analista=" + analista + ", estado=" + estado + ", fechaEmision=" + fechaEmision
				+ "]";
	}

}
