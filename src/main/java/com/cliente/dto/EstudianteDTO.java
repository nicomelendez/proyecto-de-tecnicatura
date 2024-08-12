package com.cliente.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.servidor.entidades.Estudiante;

public class EstudianteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idEstudiante;

	private String generacion;

	private BigDecimal semestre;

	private UsuarioDTO oUsuario;

	public EstudianteDTO(String generacion, BigDecimal semestre) {
		super();
		this.generacion = generacion;
		this.semestre = semestre;
	}

	public EstudianteDTO(long idEstudiante, String generacion, BigDecimal semestre, UsuarioDTO oUsuarioDTO) {
		super();
		this.idEstudiante = idEstudiante;
		this.generacion = generacion;
		this.semestre = semestre;
		this.oUsuario = oUsuarioDTO;
	}

	public EstudianteDTO(Estudiante oEstudiante) {
		super();
		this.idEstudiante = oEstudiante.getIdEstudiante();
		this.generacion = oEstudiante.getGeneracion();
		this.semestre = oEstudiante.getSemestre();
		this.oUsuario = oEstudiante.getUsuario();
	}

	public long getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(long idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public String getGeneracion() {
		return generacion;
	}

	public void setGeneracion(String generacion) {
		this.generacion = generacion;
	}

	public BigDecimal getSemestre() {
		return semestre;
	}

	public void setSemestre(BigDecimal semestre) {
		this.semestre = semestre;
	}

	public UsuarioDTO getoUsuario() {
		return oUsuario;
	}

	public void setoUsuario(UsuarioDTO oUsuario) {
		this.oUsuario = oUsuario;
	}

}
