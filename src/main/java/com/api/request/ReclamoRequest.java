package com.api.request;

public class ReclamoRequest {
	private Long idReclamo;
	private String titulo;
	private String detalle;
	private String documento;
	private Long idEvento;

	public Long getIdReclamo() {
		return idReclamo;
	}

	public void setIdReclamo(Long idReclamo) {
		this.idReclamo = idReclamo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	@Override
	public String toString() {
		return "ReclamoRequest [idReclamo=" + idReclamo + ", titulo=" + titulo + ", detalle=" + detalle + ", documento="
				+ documento + ", idEvento=" + idEvento + "]";
	}

}
