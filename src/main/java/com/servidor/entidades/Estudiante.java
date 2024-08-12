package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;

import com.cliente.dto.EstudianteDTO;
import com.cliente.dto.UsuarioDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the ESTUDIANTES database table.
 * 
 */
@Entity
@Table(name = "ESTUDIANTES")
@NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e JOIN FETCH e.usuario")
@NamedQuery(name = "Estudiante.findByActivo", query = "SELECT e FROM Estudiante e JOIN FETCH e.usuario WHERE e.usuario.activo = :filtro")
@NamedQuery(name = "Estudiante.findByDocumento", query = "SELECT e FROM Estudiante e JOIN FETCH e.usuario WHERE e.usuario.documento = :documento")
public class Estudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ESTUDIANTES_IDESTUDIANTE_GENERATOR", sequenceName = "SEQ_ID_ESTUDIANTE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTUDIANTES_IDESTUDIANTE_GENERATOR")
	@Column(name = "ID_ESTUDIANTE", unique = true, nullable = false, precision = 38)
	private long idEstudiante;

	@Column(nullable = false, length = 4)
	private String generacion;

	@Column(nullable = false, precision = 38)
	private BigDecimal semestre;

	// bi-directional many-to-one association to Constancia
	@OneToMany(mappedBy = "estudiante")
	private List<Constancia> constancias;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO", nullable = false)
	private Usuario usuario;

	// bi-directional many-to-one association to EventosEstudiante
	@OneToMany(mappedBy = "estudiante")
	private List<EventoEstudiante> eventosEstudiantes;

	// bi-directional many-to-one association to Justificacion
	@OneToMany(mappedBy = "estudiante")
	private List<Justificacion> justificaciones;

	// bi-directional many-to-one association to Reclamo
	@OneToMany(mappedBy = "estudiante")
	private List<Reclamo> reclamos;

	public Estudiante() {

	}

	public Estudiante(String generacion, BigDecimal semestre) {
		super();
		this.generacion = generacion;
		this.semestre = semestre;
	}

	public Estudiante(EstudianteDTO oEstudianteDTO) {
		super();
		this.idEstudiante = oEstudianteDTO.getIdEstudiante();
		this.generacion = oEstudianteDTO.getGeneracion();
		this.semestre = oEstudianteDTO.getSemestre();
		this.usuario = new Usuario(oEstudianteDTO.getoUsuario());
	}

	public Estudiante(long idEstudiante, String generacion, BigDecimal semestre, Usuario usuario) {
		super();
		this.idEstudiante = idEstudiante;
		this.generacion = generacion;
		this.semestre = semestre;
		this.usuario = usuario;
	}

	public long getIdEstudiante() {
		return this.idEstudiante;
	}

	public void setIdEstudiante(long idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public String getGeneracion() {
		return this.generacion;
	}

	public void setGeneracion(String generacion) {
		this.generacion = generacion;
	}

	public BigDecimal getSemestre() {
		return this.semestre;
	}

	public void setSemestre(BigDecimal semestre) {
		this.semestre = semestre;
	}

	public List<Constancia> getConstancias() {
		return this.constancias;
	}

	public void setConstancias(List<Constancia> constancias) {
		this.constancias = constancias;
	}

	public Constancia addConstancia(Constancia constancia) {
		getConstancias().add(constancia);
		constancia.setEstudiante(this);

		return constancia;
	}

	public Constancia removeConstancia(Constancia constancia) {
		getConstancias().remove(constancia);
		constancia.setEstudiante(null);

		return constancia;
	}

	public UsuarioDTO getUsuario() {
		return new UsuarioDTO(this.usuario);
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<EventoEstudiante> getEventosEstudiantes() {
		return this.eventosEstudiantes;
	}

	public void setEventosEstudiantes(List<EventoEstudiante> eventosEstudiantes) {
		this.eventosEstudiantes = eventosEstudiantes;
	}

	public EventoEstudiante addEventosEstudiante(EventoEstudiante eventosEstudiante) {
		getEventosEstudiantes().add(eventosEstudiante);
		eventosEstudiante.setEstudiante(this);

		return eventosEstudiante;
	}

	public EventoEstudiante removeEventosEstudiante(EventoEstudiante eventosEstudiante) {
		getEventosEstudiantes().remove(eventosEstudiante);
		eventosEstudiante.setEstudiante(null);

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
		justificacione.setEstudiante(this);

		return justificacione;
	}

	public Justificacion removeJustificacione(Justificacion justificacione) {
		getJustificaciones().remove(justificacione);
		justificacione.setEstudiante(null);

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
		reclamo.setEstudiante(this);

		return reclamo;
	}

	public Reclamo removeReclamo(Reclamo reclamo) {
		getReclamos().remove(reclamo);
		reclamo.setEstudiante(null);

		return reclamo;
	}

}