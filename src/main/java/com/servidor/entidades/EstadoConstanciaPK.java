package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ESTADOS_CONSTANCIAS database table.
 * 
 */
@Embeddable
public class EstadoConstanciaPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_CONSTANCIA", nullable = false)
	private long idConstancia;

	@Column(name = "ID_ANALISTA", nullable = false)
	private long idAnalista;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA", unique = true, nullable = false)
	private java.util.Date fechaHora;

	public EstadoConstanciaPK() {
	}

	public EstadoConstanciaPK(long idConstancia, long idAnalista, java.util.Date fechaHora) {
		this.idConstancia = idConstancia;
		this.idAnalista = idAnalista;
		this.fechaHora = fechaHora;
	}

	public long getIdConstancia() {
		return this.idConstancia;
	}

	public void setIdConstancia(long idConstancia) {
		this.idConstancia = idConstancia;
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
		if (!(other instanceof EstadoConstanciaPK)) {
			return false;
		}
		EstadoConstanciaPK castOther = (EstadoConstanciaPK) other;
		return (this.idConstancia == castOther.idConstancia) && (this.idAnalista == castOther.idAnalista)
				&& this.fechaHora.equals(castOther.fechaHora);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idConstancia ^ (this.idConstancia >>> 32)));
		hash = hash * prime + ((int) (this.idAnalista ^ (this.idAnalista >>> 32)));
		hash = hash * prime + this.fechaHora.hashCode();

		return hash;
	}
}