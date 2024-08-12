package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ESTADOS_RECLAMOS database table.
 * 
 */
@Entity
@Table(name = "ESTADOS_RECLAMOS")
@NamedQuery(name = "EstadoReclamo.findAll", query = "SELECT e FROM EstadoReclamo e")
public class EstadoReclamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EstadoReclamoPK id;

	@Column(nullable = false, length = 255)
	private String detalle;

	// bi-directional many-to-one association to Analista
	@ManyToOne
	@JoinColumn(name = "ID_ANALISTA", nullable = false, insertable = false, updatable = false)
	private Analista analista;

	// bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name = "ID_ESTADO", nullable = false)
	private Estado estado;

	// bi-directional many-to-one association to Reclamo
	@ManyToOne
	@JoinColumn(name = "ID_RECLAMO", nullable = false, insertable = false, updatable = false)
	private Reclamo reclamo;

	public EstadoReclamo() {
	}

	public EstadoReclamo(EstadoReclamoPK id, String detalle, Estado estado) {
		super();
		this.id = id;
		this.detalle = detalle;
		this.estado = estado;
	}

	public EstadoReclamoPK getId() {
		return this.id;
	}

	public void setId(EstadoReclamoPK id) {
		this.id = id;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Analista getAnalista() {
		return this.analista;
	}

	public void setAnalista(Analista analista) {
		this.analista = analista;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Reclamo getReclamo() {
		return this.reclamo;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

}