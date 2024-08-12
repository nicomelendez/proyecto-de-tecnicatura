package com.cliente.dto;

import java.io.Serializable;

import com.servidor.entidades.Rol;

public class RolDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idRol;

	private String descripcion;

	public RolDTO(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public RolDTO(Rol oRol) {
		super();
		this.idRol = oRol.getIdRol();
		this.descripcion = oRol.getDescripcion();
	}

	public long getIdRol() {
		return idRol;
	}

	public void setIdRol(long idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
