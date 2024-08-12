package com.cliente.dto;

import java.io.Serializable;

import com.servidor.entidades.Analista;

public class AnalistaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idAnalista;

	private byte[] firma;

	private UsuarioDTO oUsuario;

	public AnalistaDTO() {
		super();

	}

	public AnalistaDTO(long idAnalista, byte[] firma, UsuarioDTO oUsuario) {
		super();
		this.idAnalista = idAnalista;
		this.firma = firma;
		this.oUsuario = oUsuario;
	}

	public AnalistaDTO(Analista oAnalista) {
		super();
		this.idAnalista = oAnalista.getIdAnalista();
		this.firma = oAnalista.getFirma();
		this.oUsuario = new UsuarioDTO(oAnalista.getUsuario());
	}

	public long getIdAnalista() {
		return idAnalista;
	}

	public void setIdAnalista(long idAnalista) {
		this.idAnalista = idAnalista;
	}

	public byte[] getFirma() {
		return firma;
	}

	public void setFirma(byte[] firma) {
		this.firma = firma;
	}

	public UsuarioDTO getoUsuario() {
		return oUsuario;
	}

	public void setoUsuario(UsuarioDTO oUsuario) {
		this.oUsuario = oUsuario;
	}

}
