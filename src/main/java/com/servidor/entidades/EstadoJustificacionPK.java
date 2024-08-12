package com.servidor.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the ESTADOS_JUSTIFICACIONES database table.
 * 
 */
@Embeddable
public class EstadoJustificacionPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_JUSTIFICACION", nullable = false)
	private long idJustificacion;

	@Column(name = "ID_ANALISTA", nullable = false)
	private long idAnalista;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA", unique = true, nullable = false)
	private java.util.Date fechaHora;

	public EstadoJustificacionPK() {
	}

	public EstadoJustificacionPK(long idJustificacion, long idAnalista, Date fechaHora) {
		super();
		this.idJustificacion = idJustificacion;
		this.idAnalista = idAnalista;
		this.fechaHora = fechaHora;
	}

	public long getIdJustificacion() {
		return this.idJustificacion;
	}

	public void setIdJustificacion(long idJustificacion) {
		this.idJustificacion = idJustificacion;
	}

	public long getIdAnalista() {
		return this.idAnalista;
	}

	public void setIdAnalista(long idAnalista) {
		this.idAnalista = idAnalista;
	}

	public java.util.Date getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(java.util.Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EstadoJustificacionPK)) {
			return false;
		}
		EstadoJustificacionPK castOther = (EstadoJustificacionPK) other;
		return (this.idJustificacion == castOther.idJustificacion) && (this.idAnalista == castOther.idAnalista)
				&& this.fechaHora.equals(castOther.fechaHora);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idJustificacion ^ (this.idJustificacion >>> 32)));
		hash = hash * prime + ((int) (this.idAnalista ^ (this.idAnalista >>> 32)));
		hash = hash * prime + this.fechaHora.hashCode();

		return hash;
	}
}