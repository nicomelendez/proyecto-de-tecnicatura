package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;

import com.cliente.dto.LocalidadDTO;

import java.util.List;

/**
 * The persistent class for the LOCALIDADES database table.
 * 
 */
@Entity
@Table(name = "LOCALIDADES")
@NamedQuery(name = "Localidad.findAll", query = "SELECT l FROM Localidad l")
@NamedQuery(name = "Localidad.findByNombreDepartamento", query = "SELECT l FROM Localidad l WHERE l.departamento.nombre = :nombreDepartamento")
public class Localidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "LOCALIDADES_IDLOCALIDAD_GENERATOR", sequenceName = "SEQ_ID_LOCALIDAD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCALIDADES_IDLOCALIDAD_GENERATOR")
	@Column(name = "ID_LOCALIDAD", unique = true, nullable = false, precision = 38)
	private long idLocalidad;

	@Column(nullable = false, length = 25)
	private String nombre;

	// bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name = "ID_DEPARTAMENTO", nullable = false)
	private Departamento departamento;

	// bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy = "localidad")
	private List<Usuario> usuarios;

	public Localidad() {
	}

	public Localidad(LocalidadDTO oLocalidadDTO) {
		super();
		this.idLocalidad = oLocalidadDTO.getIdLocalidad();
		this.nombre = oLocalidadDTO.getNombre();
		this.departamento = oLocalidadDTO.getDepartamento();
	}

	public long getIdLocalidad() {
		return this.idLocalidad;
	}

	public void setIdLocalidad(long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setLocalidad(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setLocalidad(null);

		return usuario;
	}

}