package com.servidor.dao;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cliente.dto.EstudianteDTO;
import com.servidor.entidades.Estudiante;

@Stateless
public class EstudianteDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public boolean crear(EstudianteDTO oEstudianteDTO) {
		try {
			entityManager.persist(new Estudiante(oEstudianteDTO));
			entityManager.flush();
			return true;

		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean actualizar(EstudianteDTO oEstudianteDTO) {
		try {
			entityManager.merge(new Estudiante(oEstudianteDTO));
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public ArrayList<EstudianteDTO> listar() {
		ArrayList<EstudianteDTO> listaDeEstudiantes = new ArrayList<EstudianteDTO>();
		try {
			TypedQuery<Estudiante> query = entityManager.createNamedQuery("Estudiante.findAll", Estudiante.class);

			for (Estudiante oEstudiante : query.getResultList()) {
				listaDeEstudiantes.add(new EstudianteDTO(oEstudiante));
			}

			return listaDeEstudiantes;
		} catch (PersistenceException e) {
			return null;
		}
	}

	public ArrayList<EstudianteDTO> listarActivoFiltro(String filtro) {
		ArrayList<EstudianteDTO> listaDeEstudiantes = new ArrayList<EstudianteDTO>();
		try {
			TypedQuery<Estudiante> query = entityManager.createNamedQuery("Estudiante.findByActivo", Estudiante.class)
					.setParameter("filtro", filtro);

			for (Estudiante oEstudiante : query.getResultList()) {
				listaDeEstudiantes.add(new EstudianteDTO(oEstudiante));
			}

			return listaDeEstudiantes;
		} catch (PersistenceException e) {
			return null;
		}
	}

	public EstudianteDTO getByDocumento(String documento) {
		try {
			TypedQuery<Estudiante> query = entityManager
					.createNamedQuery("Estudiante.findByDocumento", Estudiante.class)
					.setParameter("documento", new BigDecimal(documento));

			Estudiante oEstudiante = query.getSingleResult();

			return new EstudianteDTO(oEstudiante);
		} catch (PersistenceException e) {
			return null;
		}
	}
}
