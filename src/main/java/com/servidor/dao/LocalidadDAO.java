package com.servidor.dao;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cliente.dto.LocalidadDTO;
import com.servidor.entidades.Localidad;

@Stateless
public class LocalidadDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public LocalidadDTO crear(LocalidadDTO oLocalidadDTO) {
		try {
			Localidad oLocalidad = new Localidad(oLocalidadDTO);
			entityManager.persist(oLocalidad);
			entityManager.flush();
			return new LocalidadDTO(oLocalidad);
		} catch (PersistenceException e) {
			return null;
		}
	}

	public LocalidadDTO actualizar(LocalidadDTO oLocalidadDTO) {
		try {
			Localidad oLocalidad = new Localidad(oLocalidadDTO);
			entityManager.merge(oLocalidad);
			entityManager.flush();
			return new LocalidadDTO(oLocalidad);
		} catch (PersistenceException e) {
			return null;
		}
	}

	public ArrayList<LocalidadDTO> listar() {
		ArrayList<LocalidadDTO> listaDeLocalidades = new ArrayList<LocalidadDTO>();
		try {
			TypedQuery<Localidad> query = entityManager.createQuery("SELECT l FROM Localidad l", Localidad.class);
			for (Localidad oLocalidad : query.getResultList()) {
				listaDeLocalidades.add(new LocalidadDTO(oLocalidad));
			}

			return listaDeLocalidades;
		} catch (PersistenceException e) {
			return null;
		}
	}

	public ArrayList<LocalidadDTO> listarFiltro(String filtro) {
		ArrayList<LocalidadDTO> listaDeLocalidades = new ArrayList<LocalidadDTO>();
		try {
			TypedQuery<Localidad> query = entityManager
					.createQuery("SELECT l FROM Localidad l WHERE l.nombre LIKE :filtro", Localidad.class)
					.setParameter("filtro", "%" + filtro + "%");
			for (Localidad oLocalidad : query.getResultList()) {
				listaDeLocalidades.add(new LocalidadDTO(oLocalidad));
			}

			return listaDeLocalidades;
		} catch (PersistenceException e) {
			return null;
		}
	}

	public ArrayList<LocalidadDTO> listarLocalidadesPorDepartamento(String nombreDepartamento) {
		ArrayList<LocalidadDTO> listaDeLocalidades = new ArrayList<LocalidadDTO>();
		try {
			TypedQuery<Localidad> query = entityManager
					.createNamedQuery("Localidad.findByNombreDepartamento", Localidad.class)
					.setParameter("nombreDepartamento", nombreDepartamento);
			for (Localidad oLocalidad : query.getResultList()) {
				listaDeLocalidades.add(new LocalidadDTO(oLocalidad));
			}

			return listaDeLocalidades;
		} catch (PersistenceException e) {
			return null;
		}
	}
}
