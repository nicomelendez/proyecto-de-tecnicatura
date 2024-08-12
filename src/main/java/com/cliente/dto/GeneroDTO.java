package com.cliente.dto;

import java.io.Serializable;

import com.servidor.entidades.Genero;

public class GeneroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idGenero;

	private String nombre;

	public GeneroDTO(String nombre) {
		super();
		this.nombre = nombre;
	}

	public GeneroDTO(Genero oGenero) {
		super();
		this.idGenero = oGenero.getIdGenero();
		this.nombre = oGenero.getNombre();
	}

	public long getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(long idGenero) {
		this.idGenero = idGenero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
