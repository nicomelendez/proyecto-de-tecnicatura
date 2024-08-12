package com.cliente.dto;

import java.io.Serializable;

import com.servidor.entidades.Departamento;
import com.servidor.entidades.Itr;

public class ItrDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idItr;

	private String activo;

	private String nombre;

	private DepartamentoDTO departamento;

	public ItrDTO(String nombre, DepartamentoDTO departamento, String activo) {
		super();
		this.activo = activo;
		this.nombre = nombre;
		this.departamento = departamento;
	}

	public ItrDTO(Itr oItr) {
		super();
		this.idItr = oItr.getIdItr();
		this.activo = oItr.getActivo();
		this.nombre = oItr.getNombre();
		this.departamento = oItr.getDepartamento();
	}

	public long getIdItr() {
		return idItr;
	}

	public void setIdItr(long idItr) {
		this.idItr = idItr;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
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
