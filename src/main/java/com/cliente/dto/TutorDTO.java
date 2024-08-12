package com.cliente.dto;

import java.io.Serializable;

import com.servidor.entidades.Area;
import com.servidor.entidades.Tutor;

public class TutorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idTutor;

	private AreaDTO area;

	private UsuarioDTO oUsuario;

	public TutorDTO(AreaDTO area) {
		super();
		this.area = area;
	}

	public TutorDTO(long idTutor, AreaDTO oAreaDTO, UsuarioDTO oUsuarioDTO) {
		super();
		this.idTutor = idTutor;
		this.area = oAreaDTO;
		this.oUsuario = oUsuarioDTO;
	}

	public TutorDTO(Tutor oTutor) {
		super();
		this.idTutor = oTutor.getIdTutor();
		this.area = oTutor.getArea();
		this.oUsuario = oTutor.getUsuario();
	}

	public long getIdTutor() {
		return idTutor;
	}

	public void setIdTutor(long idTutor) {
		this.idTutor = idTutor;
	}

	public Area getArea() {
		return new Area(area);
	}

	public void setArea(AreaDTO area) {
		this.area = area;
	}

	public UsuarioDTO getoUsuario() {
		return oUsuario;
	}

	public void setoUsuario(UsuarioDTO usuario) {
		this.oUsuario = usuario;
	}

}
