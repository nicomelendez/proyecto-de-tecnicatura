package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;

import com.cliente.dto.AreaDTO;
import com.cliente.dto.TutorDTO;
import com.cliente.dto.UsuarioDTO;

import java.util.List;

/**
 * The persistent class for the TUTORES database table.
 * 
 */
@Entity
@Table(name = "TUTORES")
@NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t JOIN FETCH t.usuario")
@NamedQuery(name = "Tutor.findByActivo", query = "SELECT t FROM Tutor t JOIN FETCH t.usuario WHERE t.usuario.activo = :filtro")
@NamedQuery(name = "Tutor.findByDocumento", query = "SELECT t FROM Tutor t JOIN FETCH t.usuario WHERE t.usuario.documento = :documento")
public class Tutor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TUTORES_IDTUTOR_GENERATOR", sequenceName = "SEQ_ID_TUTOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TUTORES_IDTUTOR_GENERATOR")
	@Column(name = "ID_TUTOR", unique = true, nullable = false, precision = 38)
	private long idTutor;

	// bi-directional many-to-many association to Evento
	@ManyToMany(mappedBy = "tutores")
	private List<Evento> eventos;

	// bi-directional many-to-one association to Area
	@ManyToOne
	@JoinColumn(name = "ID_AREA", nullable = false)
	private Area area;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO", nullable = false)
	private Usuario usuario;

	public Tutor() {
	}

	public Tutor(TutorDTO oTutorDTO) {
		super();
		this.idTutor = oTutorDTO.getIdTutor();
		this.area = oTutorDTO.getArea();
		this.usuario = new Usuario(oTutorDTO.getoUsuario());
	}

	public Tutor(long idTutor, Area area, Usuario usuario) {
		super();
		this.idTutor = idTutor;
		this.area = area;
		this.usuario = usuario;
	}

	public Tutor(Area area) {
		super();
		this.area = area;
	}

	public long getIdTutor() {
		return this.idTutor;
	}

	public void setIdTutor(long idTutor) {
		this.idTutor = idTutor;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public AreaDTO getArea() {
		return new AreaDTO(this.area);
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public UsuarioDTO getUsuario() {
		return new UsuarioDTO(this.usuario);
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}