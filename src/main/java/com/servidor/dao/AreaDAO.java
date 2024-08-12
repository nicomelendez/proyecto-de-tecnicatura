package com.servidor.dao;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cliente.dto.AreaDTO;
import com.servidor.entidades.Area;

@Stateless
public class AreaDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public AreaDTO crear(AreaDTO oAreaDTO) {
		try {
			Area oArea = new Area(oAreaDTO);
			entityManager.persist(oArea);
			entityManager.flush();
			return new AreaDTO(oArea);
		} catch (PersistenceException e) {
			return null;
		}
	}

	public boolean actualizar(AreaDTO oAreaDTO) {
		try {
			Area oArea = new Area(oAreaDTO);
			entityManager.merge(oArea);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public ArrayList<AreaDTO> listar() {
		ArrayList<AreaDTO> listaDeAreas = new ArrayList<AreaDTO>();
		try {
			TypedQuery<Area> query = entityManager.createQuery("SELECT a FROM Area a", Area.class);
			for (Area oArea : query.getResultList()) {
				listaDeAreas.add(new AreaDTO(oArea));
			}
			return listaDeAreas;
		} catch (PersistenceException e) {
			return null;
		}
	}

	public ArrayList<AreaDTO> listarFiltro(String filtro) {
		ArrayList<AreaDTO> listaDeAreas = new ArrayList<AreaDTO>();
		try {
			TypedQuery<Area> query = entityManager
					.createQuery("SELECT a FROM Area a WHERE a.descripcion LIKE :filtro", Area.class)
					.setParameter("filtro", "%" + filtro + "%");
			for (Area oArea : query.getResultList()) {
				listaDeAreas.add(new AreaDTO(oArea));
			}
			return listaDeAreas;
		} catch (PersistenceException e) {
			return null;
		}
	}
}
