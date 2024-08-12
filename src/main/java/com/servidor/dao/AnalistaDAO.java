package com.servidor.dao;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cliente.dto.AnalistaDTO;
import com.servidor.entidades.Analista;
import com.servidor.exception.ServiciosException;

@Stateless
public class AnalistaDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public boolean crear(AnalistaDTO oAnalistaDTO) {
		try {
			entityManager.persist(new Analista(oAnalistaDTO));
			entityManager.flush();
			return true;

		} catch (PersistenceException e) {
			return false;
		}
	}

	public boolean actualizar(AnalistaDTO oAnalistaDTO) {
		try {
			entityManager.merge(new Analista(oAnalistaDTO));
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public ArrayList<AnalistaDTO> listar() {
		ArrayList<AnalistaDTO> listaDeAnalistas = new ArrayList<AnalistaDTO>();
		try {
			TypedQuery<Analista> query = entityManager.createNamedQuery("Analista.findAll", Analista.class);

			for (Analista oAnalista : query.getResultList()) {
				listaDeAnalistas.add(new AnalistaDTO(oAnalista));
			}

			return listaDeAnalistas;
		} catch (PersistenceException e) {
			return null;
		}
	}

	public ArrayList<AnalistaDTO> listarActivoFiltro(String filtro) {
		ArrayList<AnalistaDTO> listaDeAnalistas = new ArrayList<AnalistaDTO>();
		System.out.println("--------");
		System.out.println(filtro);
		System.out.println("--------");
		try {
			TypedQuery<Analista> query = entityManager.createNamedQuery("Analista.findByActivo", Analista.class)
					.setParameter("activo", filtro);
			System.out.println("--------");
			System.out.println(query.getResultList().size());
			System.out.println("--------");
			for (Analista oAnalista : query.getResultList()) {
				listaDeAnalistas.add(new AnalistaDTO(oAnalista));
			}

			return listaDeAnalistas;
		} catch (PersistenceException e) {
			return null;
		}
	}

	public AnalistaDTO getByDocumento(String documento) {
		try {
			TypedQuery<Analista> query = entityManager.createNamedQuery("Analista.findByDocumento", Analista.class)
					.setParameter("documento", new BigDecimal(documento));

			Analista oAnalista = query.getSingleResult();

			return new AnalistaDTO(oAnalista);
		} catch (PersistenceException e) {
			return null;
		}
	}

	public Analista getId(long id) throws ServiciosException {
		try {
			TypedQuery<Analista> query = entityManager.createNamedQuery("Analista.findById", Analista.class)
					.setParameter("id", id);

			Analista oAnalista = query.getSingleResult();

			return oAnalista;
		} catch (PersistenceException e) {
			// Otras excepciones de persistencia
			return null;
		}
	}
}
