package com.cliente.dto;

import java.io.Serializable;

import com.servidor.entidades.Departamento;

public class DepartamentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idDepartamento;

	private String nombre;

	public DepartamentoDTO(String nombre) {
		super();
		this.nombre = nombre;
	}

	public DepartamentoDTO(Departamento oDepartamento) {
		super();
		this.idDepartamento = oDepartamento.getIdDepartamento();
		this.nombre = oDepartamento.getNombre();
	}

	public long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
