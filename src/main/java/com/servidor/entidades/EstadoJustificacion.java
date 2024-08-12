package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ESTADOS_JUSTIFICACIONES database table.
 * 
 */
@Entity
@Table(name="ESTADOS_JUSTIFICACIONES")
@NamedQuery(name="EstadoJustificacion.findAll", query="SELECT e FROM EstadoJustificacion e")
public class EstadoJustificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EstadoJustificacionPK id;

	@Column(nullable=false, length=255)
	private String detalle;

	//bi-directional many-to-one association to Analista
	@ManyToOne
	@JoinColumn(name="ID_ANALISTA", nullable=false, insertable=false, updatable=false)
	private Analista analista;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="ID_ESTADO", nullable=false)
	private Estado estado;

	//bi-directional many-to-one association to Justificacion
	@ManyToOne
	@JoinColumn(name="ID_JUSTIFICACION", nullable=false, insertable=false, updatable=false)
	private Justificacion justificacione;

	public EstadoJustificacion() {
	}
	
	public EstadoJustificacion(EstadoJustificacionPK id, String detalle, Analista analista, Estado estado,
			Justificacion justificacione) {
		super();
		this.id = id;
		this.detalle = detalle;
		this.analista = analista;
		this.estado = estado;
		this.justificacione = justificacione;
	}



	public EstadoJustificacionPK getId() {
		return this.id;
	}

	public void setId(EstadoJustificacionPK id) {
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

	public Justificacion getJustificacione() {
		return this.justificacione;
	}

	public void setJustificacione(Justificacion justificacione) {
		this.justificacione = justificacione;
	}

}