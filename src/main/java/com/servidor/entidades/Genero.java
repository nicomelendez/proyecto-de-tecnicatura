package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;

import com.cliente.dto.GeneroDTO;

import java.util.List;


/**
 * The persistent class for the GENEROS database table.
 * 
 */
@Entity
@Table(name="GENEROS")
@NamedQuery(name="Genero.findAll", query="SELECT g FROM Genero g")
public class Genero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GENEROS_IDGENERO_GENERATOR", sequenceName="SEQ_ID_GENERO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GENEROS_IDGENERO_GENERATOR")
	@Column(name="ID_GENERO", unique=true, nullable=false, precision=38)
	private long idGenero;

	@Column(nullable=false, length=25)
	private String nombre;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="genero")
	private List<Usuario> usuarios;

	public Genero() {
	}
	
	public Genero(GeneroDTO oGeneroDTO) {
		super();
		this.idGenero = oGeneroDTO.getIdGenero();
		this.nombre = oGeneroDTO.getNombre();
	}
	
	public long getIdGenero() {
		return this.idGenero;
	}

	public void setIdGenero(long idGenero) {
		this.idGenero = idGenero;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setGenero(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setGenero(null);

		return usuario;
	}

}