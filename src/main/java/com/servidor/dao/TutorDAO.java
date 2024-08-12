package com.servidor.dao;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cliente.dto.TutorDTO;
import com.servidor.entidades.Tutor;

@Stateless
public class TutorDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public boolean crear(TutorDTO oTutorDTO) {
		try {
			entityManager.persist(new Tutor(oTutorDTO));
			entityManager.flush();
			return true;

		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean actualizar(TutorDTO oTutorDTO) {
		try {
			entityManager.merge(new Tutor(oTutorDTO));
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public ArrayList<TutorDTO> listar() {
		ArrayList<TutorDTO> listaDeTutores = new ArrayList<TutorDTO>();
		try {

			TypedQuery<Tutor> query = entityManager.createNamedQuery("Tutor.findAll", Tutor.class);
			for (Tutor oTutor : query.getResultList()) {
				listaDeTutores.add(new TutorDTO(oTutor));
			}

			return listaDeTutores;
		} catch (PersistenceException e) {
			return null;
		}
	}

	public ArrayList<TutorDTO> listarActivoFiltro(String filtro) {
		ArrayList<TutorDTO> listaDeTutores = new ArrayList<TutorDTO>();
		try {

			TypedQuery<Tutor> query = entityManager.createNamedQuery("Tutor.findByActivo", Tutor.class)
					.setParameter("filtro", filtro);
			for (Tutor oTutor : query.getResultList()) {
				listaDeTutores.add(new TutorDTO(oTutor));
			}

			return listaDeTutores;
		} catch (PersistenceException e) {
			return null;
		}
	}

	public TutorDTO getByDocumento(String documento) {
		try {

			TypedQuery<Tutor> query = entityManager.createNamedQuery("Tutor.findByDocumento", Tutor.class)
					.setParameter("documento", new BigDecimal(documento));
			Tutor oTutor = query.getSingleResult();

			return new TutorDTO(oTutor);
		} catch (PersistenceException e) {
			return null;
		}
	}
}
