package com.cliente.dto;

import java.io.Serializable;

import com.servidor.entidades.Estado;

public class EstadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idEstado;

	private String descripcion;

	public EstadoDTO(Estado oEstado) {
		super();
		this.idEstado = oEstado.getIdEstado();
		this.descripcion = oEstado.getDescripcion();
	}

	public long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(long idEstado) {
		this.idEstado = idEstado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
