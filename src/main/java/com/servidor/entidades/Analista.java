package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;

import com.cliente.dto.AnalistaDTO;

import java.util.List;

/**
 * The persistent class for the ANALISTAS database table.
 * 
 */
@Entity
@Table(name = "ANALISTAS")
@NamedQuery(name = "Analista.findAll", query = "SELECT a FROM Analista a JOIN FETCH a.usuario")
@NamedQuery(name = "Analista.findById", query = "SELECT a FROM Analista a JOIN FETCH a.usuario WHERE a.idAnalista = :id")
@NamedQuery(name = "Analista.findByActivo", query = "SELECT a FROM Analista a WHERE a.usuario.activo = :activo")
@NamedQuery(name = "Analista.findByDocumento", query = "SELECT a FROM Analista a WHERE a.usuario.documento = :documento")
public class Analista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ANALISTAS_IDANALISTA_GENERATOR", sequenceName = "SEQ_ID_ANALISTA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANALISTAS_IDANALISTA_GENERATOR")
	@Column(name = "ID_ANALISTA", unique = true, nullable = false, precision = 38)
	private long idAnalista;

	@Lob
	private byte[] firma;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO", nullable = false)
	private Usuario usuario;

	// bi-directional many-to-one association to EstadosConstancia
	@OneToMany(mappedBy = "analista")
	private List<EstadoConstancia> estadosConstancias;

	// bi-directional many-to-one association to EstadosJustificacion
	@OneToMany(mappedBy = "analista")
	private List<EstadoJustificacion> estadosJustificaciones;

	// bi-directional many-to-one association to EstadosReclamo
	@OneToMany(mappedBy = "analista")
	private List<EstadoReclamo> estadosReclamos;

	// bi-directional many-to-many association to Evento
	@ManyToMany(mappedBy = "analistas")
	private List<Evento> eventos;

	public Analista() {
	}

	public Analista(AnalistaDTO oAnalistaDTO) {
		super();
		this.idAnalista = oAnalistaDTO.getIdAnalista();
		this.firma = oAnalistaDTO.getFirma();
		this.usuario = new Usuario(oAnalistaDTO.getoUsuario());
	}

	public Analista(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	public long getIdAnalista() {
		return this.idAnalista;
	}

	public void setIdAnalista(long idAnalista) {
		this.idAnalista = idAnalista;
	}

	public byte[] getFirma() {
		return this.firma;
	}

	public void setFirma(byte[] firma) {
		this.firma = firma;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<EstadoConstancia> getEstadosConstancias() {
		return this.estadosConstancias;
	}

	public void setEstadosConstancias(List<EstadoConstancia> estadosConstancias) {
		this.estadosConstancias = estadosConstancias;
	}

	public EstadoConstancia addEstadosConstancia(EstadoConstancia estadosConstancia) {
		getEstadosConstancias().add(estadosConstancia);
		estadosConstancia.setAnalista(this);

		return estadosConstancia;
	}

	public EstadoConstancia removeEstadosConstancia(EstadoConstancia estadosConstancia) {
		getEstadosConstancias().remove(estadosConstancia);
		estadosConstancia.setAnalista(null);

		return estadosConstancia;
	}

	public List<EstadoJustificacion> getEstadosJustificaciones() {
		return this.estadosJustificaciones;
	}

	public void setEstadosJustificaciones(List<EstadoJustificacion> estadosJustificaciones) {
		this.estadosJustificaciones = estadosJustificaciones;
	}

	public EstadoJustificacion addEstadosJustificacione(EstadoJustificacion estadosJustificacione) {
		getEstadosJustificaciones().add(estadosJustificacione);
		estadosJustificacione.setAnalista(this);

		return estadosJustificacione;
	}

	public EstadoJustificacion removeEstadosJustificacione(EstadoJustificacion estadosJustificacione) {
		getEstadosJustificaciones().remove(estadosJustificacione);
		estadosJustificacione.setAnalista(null);

		return estadosJustificacione;
	}

	public List<EstadoReclamo> getEstadosReclamos() {
		return this.estadosReclamos;
	}

	public void setEstadosReclamos(List<EstadoReclamo> estadosReclamos) {
		this.estadosReclamos = estadosReclamos;
	}

	public EstadoReclamo addEstadosReclamo(EstadoReclamo estadosReclamo) {
		getEstadosReclamos().add(estadosReclamo);
		estadosReclamo.setAnalista(this);

		return estadosReclamo;
	}

	public EstadoReclamo removeEstadosReclamo(EstadoReclamo estadosReclamo) {
		getEstadosReclamos().remove(estadosReclamo);
		estadosReclamo.setAnalista(null);

		return estadosReclamo;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

}