package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ESTADOS_CONSTANCIAS database table.
 * 
 */
@Entity
@Table(name="ESTADOS_CONSTANCIAS")
@NamedQuery(name="EstadoConstancia.findAll", query="SELECT e FROM EstadoConstancia e")
public class EstadoConstancia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EstadoConstanciaPK id;

	@Column(nullable=false, length=255)
	private String detalle;

	//bi-directional many-to-one association to Analista
	@ManyToOne
	@JoinColumn(name="ID_ANALISTA", nullable=false, insertable=false, updatable=false)
	private Analista analista;

	//bi-directional many-to-one association to Constancia
	@ManyToOne
	@JoinColumn(name="ID_CONSTANCIA", nullable=false, insertable=false, updatable=false)
	private Constancia constancia;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="ID_ESTADO", nullable=false)
	private Estado estado;

	public EstadoConstancia() {
	}
	
	public EstadoConstancia(EstadoConstanciaPK id, String detalle, Analista analista, Constancia constancia, Estado estado) {
		this.id = id;
		this.detalle = detalle;
		this.analista = analista;
		this.constancia = constancia;
		this.estado = estado;
	}
	
	public EstadoConstanciaPK getId() {
		return this.id;
	}

	public void setId(EstadoConstanciaPK id) {
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

	public Constancia getConstancia() {
		return this.constancia;
	}

	public void setConstancia(Constancia constancia) {
		this.constancia = constancia;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}