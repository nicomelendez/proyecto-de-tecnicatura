package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.cliente.dto.EstadoReclamoDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the RECLAMOS database table.
 * 
 */
@Entity
@Table(name = "RECLAMOS")
@NamedQuery(name = "Reclamo.findAll", query = "SELECT r FROM Reclamo r")
public class Reclamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "RECLAMOS_IDRECLAMO_GENERATOR", sequenceName = "SEQ_ID_RECLAMO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECLAMOS_IDRECLAMO_GENERATOR")
	@Column(name = "ID_RECLAMO", unique = true, nullable = false, precision = 38)
	private long idReclamo;

	@Column(nullable = false, length = 255)
	private String detalle;

	@Column(nullable = false, length = 1)
	private String activo;

	// bi-directional many-to-one association to EstadosReclamo
	@OneToMany(mappedBy = "reclamo", fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private List<EstadoReclamo> estadosReclamos;

	// bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name = "ID_ESTUDIANTE", nullable = false)
	private Estudiante estudiante;

	// bi-directional many-to-one association to Evento
	@ManyToOne
	@JoinColumn(name = "ID_EVENTO", nullable = false)
	private Evento evento;

	public Reclamo() {
	}
	
	
	public Reclamo(String detalle, Estudiante estudiante, Evento evento, String activo) {
		super();
		this.detalle = detalle;
		this.estudiante = estudiante;
		this.evento = evento;
		this.activo = activo;
	}

	public long getIdReclamo() {
		return this.idReclamo;
	}

	public void setIdReclamo(long idReclamo) {
		this.idReclamo = idReclamo;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public List<EstadoReclamo> getEstadosReclamos() {
		return this.estadosReclamos;
	}

	public void setEstadosReclamos(List<EstadoReclamo> estadosReclamos) {
		this.estadosReclamos = estadosReclamos;
	}

	public EstadoReclamo addEstadosReclamo(EstadoReclamo estadosReclamo) {
		getEstadosReclamos().add(estadosReclamo);
		estadosReclamo.setReclamo(this);

		return estadosReclamo;
	}

	public EstadoReclamo removeEstadosReclamo(EstadoReclamo estadosReclamo) {
		getEstadosReclamos().remove(estadosReclamo);
		estadosReclamo.setReclamo(null);

		return estadosReclamo;
	}

	public Estudiante getEstudiante() {
		return this.estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Evento getEvento() {
		return this.evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

}