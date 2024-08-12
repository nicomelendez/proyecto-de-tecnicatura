package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ESTADOS database table.
 * 
 */
@Entity
@Table(name="ESTADOS")
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESTADOS_IDESTADO_GENERATOR", sequenceName="SEQ_ID_ESTADO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESTADOS_IDESTADO_GENERATOR")
	@Column(name="ID_ESTADO", unique=true, nullable=false, precision=38)
	private long idEstado;
	
	@Column(nullable = false, length = 1)
	private String activo;

	@Column(nullable=false, length=20)
	private String descripcion;

	//bi-directional many-to-one association to EstadosConstancia
	@OneToMany(mappedBy="estado")
	private List<EstadoConstancia> estadosConstancias;

	//bi-directional many-to-one association to EstadosJustificacion
	@OneToMany(mappedBy="estado")
	private List<EstadoJustificacion> estadosJustificaciones;

	//bi-directional many-to-one association to EstadosReclamo
	@OneToMany(mappedBy="estado")
	private List<EstadoReclamo> estadosReclamos;

	public Estado() {
	}
	
	public Estado(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public long getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(long idEstado) {
		this.idEstado = idEstado;
	}
	
	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<EstadoConstancia> getEstadosConstancias() {
		return this.estadosConstancias;
	}

	public void setEstadosConstancias(List<EstadoConstancia> estadosConstancias) {
		this.estadosConstancias = estadosConstancias;
	}

	public EstadoConstancia addEstadosConstancia(EstadoConstancia estadosConstancia) {
		getEstadosConstancias().add(estadosConstancia);
		estadosConstancia.setEstado(this);

		return estadosConstancia;
	}

	public EstadoConstancia removeEstadosConstancia(EstadoConstancia estadosConstancia) {
		getEstadosConstancias().remove(estadosConstancia);
		estadosConstancia.setEstado(null);

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
		estadosJustificacione.setEstado(this);

		return estadosJustificacione;
	}

	public EstadoJustificacion removeEstadosJustificacione(EstadoJustificacion estadosJustificacione) {
		getEstadosJustificaciones().remove(estadosJustificacione);
		estadosJustificacione.setEstado(null);

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
		estadosReclamo.setEstado(this);

		return estadosReclamo;
	}

	public Estado(String activo, String descripcion) {
		super();
		this.activo = activo;
		this.descripcion = descripcion;
	}

	public EstadoReclamo removeEstadosReclamo(EstadoReclamo estadosReclamo) {
		getEstadosReclamos().remove(estadosReclamo);
		estadosReclamo.setEstado(null);

		return estadosReclamo;
	}

}