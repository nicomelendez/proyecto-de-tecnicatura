package com.servidor.dao;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cliente.dto.GeneroDTO;
import com.servidor.entidades.Genero;

@Stateless
public class GeneroDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public GeneroDTO crear(GeneroDTO oGeneroDTO) {
		try {
			Genero oGenero = new Genero(oGeneroDTO);
			entityManager.persist(oGenero);
			entityManager.flush();
			return new GeneroDTO(oGenero);
		} catch (PersistenceException e) {
			return null;
		}
	}

	public GeneroDTO actualizar(GeneroDTO oGeneroDTO) {
		try {
			Genero oGenero = new Genero(oGeneroDTO);
			entityManager.merge(oGenero);
			entityManager.flush();
			return new GeneroDTO(oGenero);
		} catch (PersistenceException e) {
			return null;
		}
	}

	public ArrayList<GeneroDTO> listar() {
		ArrayList<GeneroDTO> listaDeGeneros = new ArrayList<GeneroDTO>();
		try {
			TypedQuery<Genero> query = entityManager.createQuery("SELECT g FROM Genero g", Genero.class);
			for (Genero oGenero : query.getResultList()) {
				listaDeGeneros.add(new GeneroDTO(oGenero));
			}
			return listaDeGeneros;
		} catch (PersistenceException e) {
			return null;
		}
	}

	public ArrayList<GeneroDTO> listarFiltro(String filtro) {
		ArrayList<GeneroDTO> listaDeGeneros = new ArrayList<GeneroDTO>();
		try {
			TypedQuery<Genero> query = entityManager
					.createQuery("SELECT g FROM Genero g WHERE g.nombre LIKE :filtro", Genero.class)
					.setParameter("filtro", "%" + filtro + "%");
			for (Genero oGenero : query.getResultList()) {
				listaDeGeneros.add(new GeneroDTO(oGenero));
			}
			return listaDeGeneros;
		} catch (PersistenceException e) {
			return null;
		}
	}
}
