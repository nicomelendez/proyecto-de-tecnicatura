package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the EVENTOS_ESTUDIANTES database table.
 * 
 */
@Entity
@Table(name="EVENTOS_ESTUDIANTES")
@NamedQuery(name="EventoEstudiante.findAll", query="SELECT e FROM EventoEstudiante e")
public class EventoEstudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EventoEstudiantePK id;

	@Column(nullable=false, length=1)
	private String asistencia;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal calificacion;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name="ID_ESTUDIANTE", nullable=false, insertable=false, updatable=false)
	private Estudiante estudiante;

	//bi-directional many-to-one association to Evento
	@ManyToOne
	@JoinColumn(name="ID_EVENTO", nullable=false, insertable=false, updatable=false)
	private Evento evento;

	public EventoEstudiante() {
	}
	
	public EventoEstudiante(EventoEstudiantePK id, String asistencia, BigDecimal calificacion) {
		super();
		this.id = id;
		this.asistencia = asistencia;
		this.calificacion = calificacion;
	}
	
	public EventoEstudiantePK getId() {
		return this.id;
	}

	public void setId(EventoEstudiantePK id) {
		this.id = id;
	}

	public String getAsistencia() {
		return this.asistencia;
	}

	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}

	public BigDecimal getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(BigDecimal calificacion) {
		this.calificacion = calificacion;
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