package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the EVENTOS database table.
 * 
 */
@Entity
@Table(name = "EVENTOS")
@NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e")
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "EVENTOS_IDEVENTO_GENERATOR", sequenceName = "SEQ_ID_EVENTO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENTOS_IDEVENTO_GENERATOR")
	@Column(name = "ID_EVENTO", unique = true, nullable = false, precision = 38)
	private long idEvento;

	@Column(name = "FECHA_HORA_FIN", nullable = false)
	private Timestamp fechaHoraFin;

	@Column(name = "FECHA_HORA_INICIO", nullable = false)
	private Timestamp fechaHoraInicio;

	@Column(nullable = false, length = 50)
	private String titulo;

	// bi-directional many-to-one association to ITR
	@ManyToOne
	@JoinColumn(name = "ID_ITR", nullable = false)
	private Itr itr;

	// bi-directional many-to-one association to Constancia
	@OneToMany(mappedBy = "evento")
	private List<Constancia> constancias;

	// bi-directional many-to-many association to Analista
	@ManyToMany
	@JoinTable(name = "EVENTOS_ANALISTAS", joinColumns = { @JoinColumn(name = "ID_EVENTO", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "ID_ANALISTA", nullable = false) })
	private List<Analista> analistas;

	// bi-directional many-to-many association to Tutor
	@ManyToMany
	@JoinTable(name = "EVENTOS_TUTORES", joinColumns = { @JoinColumn(name = "ID_EVENTO", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "ID_TUTOR", nullable = false) })
	private List<Tutor> tutores;

	// bi-directional many-to-one association to EventosEstudiante
	@OneToMany(mappedBy = "evento")
	private List<EventoEstudiante> eventosEstudiantes;

	// bi-directional many-to-one association to Justificacion
	@OneToMany(mappedBy = "evento")
	private List<Justificacion> justificaciones;

	// bi-directional many-to-one association to Reclamo
	@OneToMany(mappedBy = "evento")
	private List<Reclamo> reclamos;

	public Evento() {
	}

	public Evento(Timestamp fechaHoraFin, Timestamp fechaHoraInicio, String titulo, Itr itr) {
		super();
		this.fechaHoraFin = fechaHoraFin;
		this.fechaHoraInicio = fechaHoraInicio;
		this.titulo = titulo;
		this.itr = itr;
	}

	public long getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(long idEvento) {
		this.idEvento = idEvento;
	}

	public Timestamp getFechaHoraFin() {
		return this.fechaHoraFin;
	}

	public void setFechaHoraFin(Timestamp fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	public Timestamp getFechaHoraInicio() {
		return this.fechaHoraInicio;
	}

	public void setFechaHoraInicio(Timestamp fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Constancia> getConstancias() {
		return this.constancias;
	}

	public void setConstancias(List<Constancia> constancias) {
		this.constancias = constancias;
	}

	public Constancia addConstancia(Constancia constancia) {
		getConstancias().add(constancia);
		constancia.setEvento(this);

		return constancia;
	}

	public Constancia removeConstancia(Constancia constancia) {
		getConstancias().remove(constancia);
		constancia.setEvento(null);

		return constancia;
	}

	public List<Analista> getAnalistas() {
		return this.analistas;
	}

	public void setAnalistas(List<Analista> analistas) {
		this.analistas = analistas;
	}

	public List<Tutor> getTutores() {
		return this.tutores;
	}

	public void setTutores(List<Tutor> tutores) {
		this.tutores = tutores;
	}

	public List<EventoEstudiante> getEventosEstudiantes() {
		return this.eventosEstudiantes;
	}

	public void setEventosEstudiantes(List<EventoEstudiante> eventosEstudiantes) {
		this.eventosEstudiantes = eventosEstudiantes;
	}

	public EventoEstudiante addEventosEstudiante(EventoEstudiante eventosEstudiante) {
		getEventosEstudiantes().add(eventosEstudiante);
		eventosEstudiante.setEvento(this);

		return eventosEstudiante;
	}

	public EventoEstudiante removeEventosEstudiante(EventoEstudiante eventosEstudiante) {
		getEventosEstudiantes().remove(eventosEstudiante);
		eventosEstudiante.setEvento(null);

		return eventosEstudiante;
	}

	public List<Justificacion> getJustificaciones() {
		return this.justificaciones;
	}

	public void setJustificaciones(List<Justificacion> justificaciones) {
		this.justificaciones = justificaciones;
	}

	public Justificacion addJustificacione(Justificacion justificacione) {
		getJustificaciones().add(justificacione);
		justificacione.setEvento(this);

		return justificacione;
	}

	public Justificacion removeJustificacione(Justificacion justificacione) {
		getJustificaciones().remove(justificacione);
		justificacione.setEvento(null);

		return justificacione;
	}

	public List<Reclamo> getReclamos() {
		return this.reclamos;
	}

	public void setReclamos(List<Reclamo> reclamos) {
		this.reclamos = reclamos;
	}

	public Reclamo addReclamo(Reclamo reclamo) {
		getReclamos().add(reclamo);
		reclamo.setEvento(this);

		return reclamo;
	}

	public Reclamo removeReclamo(Reclamo reclamo) {
		getReclamos().remove(reclamo);
		reclamo.setEvento(null);

		return reclamo;
	}

}