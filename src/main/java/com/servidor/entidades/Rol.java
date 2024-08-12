package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;

import com.cliente.dto.RolDTO;

import java.util.List;

/**
 * The persistent class for the "ROLES" database table.
 * 
 */
@Entity
@Table(name = "ROLES")
@NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ROLES_IDROL_GENERATOR", sequenceName = "SEQ_ID_ROL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLES_IDROL_GENERATOR")
	@Column(name = "ID_ROL", unique = true, nullable = false, precision = 38)
	private long idRol;

	@Column(nullable = false, length = 50)
	private String descripcion;

	// bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy = "rol")
	private List<Usuario> usuarios;

	public Rol() {
	}

	public Rol(RolDTO oRolDTO) {
		super();
		this.idRol = oRolDTO.getIdRol();
		this.descripcion = oRolDTO.getDescripcion();
	}

	public long getIdRol() {
		return this.idRol;
	}

	public void setIdRol(long idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setRole(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setRole(null);

		return usuario;
	}

}