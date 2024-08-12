package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;

import com.cliente.dto.DepartamentoDTO;
import com.cliente.dto.ItrDTO;

import java.util.List;

/**
 * The persistent class for the ITRS database table.
 * 
 */
@Entity
@Table(name = "ITRS")
@NamedQuery(name = "Itr.findAll", query = "SELECT i FROM Itr i")
@NamedQuery(name = "Itr.getById", query = "SELECT i FROM Itr i WHERE i.idItr = :idItr")
public class Itr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ITRS_IDITR_GENERATOR", sequenceName = "SEQ_ID_ITR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITRS_IDITR_GENERATOR")
	@Column(name = "ID_ITR", unique = true, nullable = false, precision = 38)
	private long idItr;

	@Column(nullable = false, length = 1)
	private String activo;

	@Column(nullable = false, length = 50)
	private String nombre;

	// bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name = "ID_DEPARTAMENTO", nullable = false)
	private Departamento departamento;

	// bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy = "itr")
	private List<Usuario> usuarios;

	public Itr() {
	}

	public Itr(ItrDTO oItrDTO) {
		super();
		this.idItr = oItrDTO.getIdItr();
		this.nombre = oItrDTO.getNombre();
		this.departamento = oItrDTO.getDepartamento();
		this.activo = oItrDTO.getActivo();
	}

	public long getIdItr() {
		return this.idItr;
	}

	public void setIdItr(long idItr) {
		this.idItr = idItr;
	}

	public String getActivo() {
		return this.activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public DepartamentoDTO getDepartamento() {
		return new DepartamentoDTO(this.departamento);
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
		usuario.setItr(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setItr(null);

		return usuario;
	}

}