package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the JUSTIFICACIONES database table.
 * 
 */
@Entity
@Table(name="JUSTIFICACIONES")
@NamedQuery(name="Justificacion.findAll", query="SELECT j FROM Justificacion j")
public class Justificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="JUSTIFICACIONES_IDJUSTIFICACION_GENERATOR", sequenceName="SEQ_ID_JUSTIFICACION")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="JUSTIFICACIONES_IDJUSTIFICACION_GENERATOR")
	@Column(name="ID_JUSTIFICACION", unique=true, nullable=false, precision=38)
	private long idJustificacion;

	@Column(nullable=false, length=255)
	private String detalle;

	//bi-directional many-to-one association to EstadosJustificacion
	@OneToMany(mappedBy="justificacione")
	private List<EstadoJustificacion> estadosJustificaciones;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name="ID_ESTUDIANTE", nullable=false)
	private Estudiante estudiante;

	//bi-directional many-to-one association to Evento
	@ManyToOne
	@JoinColumn(name="ID_EVENTO", nullable=false)
	private Evento evento;

	public Justificacion() {
	}

	public long getIdJustificacion() {
		return this.idJustificacion;
	}

	public void setIdJustificacion(long idJustificacion) {
		this.idJustificacion = idJustificacion;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public List<EstadoJustificacion> getEstadosJustificaciones() {
		return this.estadosJustificaciones;
	}

	public void setEstadosJustificaciones(List<EstadoJustificacion> estadosJustificaciones) {
		this.estadosJustificaciones = estadosJustificaciones;
	}

	public EstadoJustificacion addEstadosJustificacione(EstadoJustificacion estadosJustificacione) {
		getEstadosJustificaciones().add(estadosJustificacione);
		estadosJustificacione.setJustificacione(this);

		return estadosJustificacione;
	}

	public EstadoJustificacion removeEstadosJustificacione(EstadoJustificacion estadosJustificacione) {
		getEstadosJustificaciones().remove(estadosJustificacione);
		estadosJustificacione.setJustificacione(null);

		return estadosJustificacione;
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

}