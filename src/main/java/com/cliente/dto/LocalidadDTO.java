package com.cliente.dto;

import java.io.Serializable;

import com.servidor.entidades.Departamento;
import com.servidor.entidades.Localidad;

public class LocalidadDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idLocalidad;

	private String nombre;

	private DepartamentoDTO departamento;

	public LocalidadDTO(String nombre, DepartamentoDTO departamento) {
		super();
		this.nombre = nombre;
		this.departamento = departamento;
	}

	public LocalidadDTO(Localidad oLocalidad) {
		super();
		this.idLocalidad = oLocalidad.getIdLocalidad();
		this.nombre = oLocalidad.getNombre();
		this.departamento = new DepartamentoDTO(oLocalidad.getDepartamento());
	}

	public long getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return new Departamento(departamento);
	}

	public void setDepartamento(DepartamentoDTO departamento) {
		this.departamento = departamento;
	}

}
