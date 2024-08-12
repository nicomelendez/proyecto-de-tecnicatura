package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;

import com.cliente.dto.AreaDTO;

import java.util.List;


/**
 * The persistent class for the AREAS database table.
 * 
 */
@Entity
@Table(name="AREAS")
@NamedQuery(name="Area.findAll", query="SELECT a FROM Area a")
public class Area implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AREAS_IDAREA_GENERATOR", sequenceName="SEQ_ID_AREA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AREAS_IDAREA_GENERATOR")
	@Column(name="ID_AREA", unique=true, nullable=false, precision=38)
	private long idArea;

	@Column(nullable=false, length=50)
	private String descripcion;

	//bi-directional many-to-one association to Tutor
	@OneToMany(mappedBy="area")
	private List<Tutor> tutores;

	public Area() {
	}
	
	public Area(AreaDTO oAreaDTO) {
		super();
		this.idArea = oAreaDTO.getIdArea();
		this.descripcion = oAreaDTO.getDescripcion();
	}
	
	public long getIdArea() {
		return this.idArea;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Tutor> getTutores() {
		return this.tutores;
	}

	public void setTutores(List<Tutor> tutores) {
		this.tutores = tutores;
	}

	public Tutor addTutore(Tutor tutore) {
		getTutores().add(tutore);
		tutore.setArea(this);

		return tutore;
	}

	public Tutor removeTutore(Tutor tutore) {
		getTutores().remove(tutore);
		tutore.setArea(null);

		return tutore;
	}

}