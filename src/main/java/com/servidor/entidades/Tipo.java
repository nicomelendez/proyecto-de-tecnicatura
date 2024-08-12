package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the TIPOS database table.
 * 
 */
@Entity
@Table(name = "TIPOS")
@NamedQuery(name = "Tipo.findAll", query = "SELECT t FROM Tipo t")
public class Tipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TIPOS_IDTIPO_GENERATOR", sequenceName = "SEQ_ID_TIPO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPOS_IDTIPO_GENERATOR")
	@Column(name = "ID_TIPO", unique = true, nullable = false, precision = 38)
	private long idTipo;

	@Column(nullable = false, length = 1)
	private String activo;

	@Lob
	@Column(nullable = false)
	private String descripcion;

	@Column(nullable = false, length = 50)
	private String nombre;

	// bi-directional many-to-one association to Constancia
	@OneToMany(mappedBy = "tipo")
	private List<Constancia> constancias;

	public Tipo() {
	}

	public Tipo(String nombre, String activo, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	public long getIdTipo() {
		return this.idTipo;
	}

	public void setIdTipo(long idTipo) {
		this.idTipo = idTipo;
	}

	public String getActivo() {
		return this.activo;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Constancia> getConstancias() {
		return this.constancias;
	}

	public void setConstancias(List<Constancia> constancias) {
		this.constancias = constancias;
	}

	public Constancia addConstancia(Constancia constancia) {
		getConstancias().add(constancia);
		constancia.setTipo(this);

		return constancia;
	}

	public Constancia removeConstancia(Constancia constancia) {
		getConstancias().remove(constancia);
		constancia.setTipo(null);

		return constancia;
	}

}