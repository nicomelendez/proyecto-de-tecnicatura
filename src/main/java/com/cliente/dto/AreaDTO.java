package com.cliente.dto;

import java.io.Serializable;

import com.servidor.entidades.Area;

public class AreaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idArea;

	private String descripcion;

	public AreaDTO(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public AreaDTO(Area oArea) {
		super();
		this.idArea = oArea.getIdArea();
		this.descripcion = oArea.getDescripcion();
	}

	public long getIdArea() {
		return idArea;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
