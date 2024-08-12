package com.servidor.beans.reclamo;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.cliente.dto.ReclamoDTO;
import com.servidor.entidades.Reclamo;
import com.servidor.exception.ServiciosException;

@Stateless
public class ReclamoBean implements ReclamoBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public ReclamoBean() {

	}

	public Reclamo crearReclamo(Reclamo oReclamo) throws ServiciosException {
		try {
			entityManager.persist(oReclamo);
			entityManager.flush();
			return oReclamo;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo crear el reclamo.");
		}
	}

	public boolean actualizarReclamo(Reclamo oReclamo) throws ServiciosException {
		try {
			entityManager.merge(oReclamo);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar el reclamo.");
		}
	}

	public boolean eliminarReclamo(Long idReclamo) throws ServiciosException {
		try {
			Reclamo oReclamo = entityManager.find(Reclamo.class, idReclamo);
			oReclamo.setActivo("N");
			entityManager.merge(oReclamo);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar el reclamo.");
		}
	}

	@Transactional
	public ArrayList<ReclamoDTO> listarReclamos() throws ServiciosException {
		try {
			TypedQuery<Reclamo> query = entityManager.createQuery(
					"SELECT r FROM Reclamo r JOIN FETCH r.estudiante JOIN FETCH r.evento WHERE r.activo = 'S' ORDER BY r.idReclamo ASC",
					Reclamo.class);
			ArrayList<Reclamo> listaReclamos = (ArrayList<Reclamo>) query.getResultList();

			ArrayList<ReclamoDTO> lista = new ArrayList<ReclamoDTO>();

			for (Reclamo oReclamo : listaReclamos) {
				lista.add(new ReclamoDTO(oReclamo));
			}

			return lista;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los reclamos.");
		}
	}

	@Transactional
	public ArrayList<ReclamoDTO> listarReclamoFiltro(String filtro) throws ServiciosException {
		try {
			Long filtroId = Long.parseLong(filtro); // Convertir el filtro String a Long
			TypedQuery<Reclamo> query = entityManager.createQuery(
					"SELECT r FROM Reclamo r WHERE r.estudiante.idEstudiante  = :filtro AND r.activo = 'S' ORDER BY r.idReclamo ASC",
					Reclamo.class).setParameter("filtro", filtroId); // Usar el Long convertido como parámetro
			ArrayList<Reclamo> listaReclamoFiltro = (ArrayList<Reclamo>) query.getResultList();

			ArrayList<ReclamoDTO> lista = new ArrayList<ReclamoDTO>();

			for (Reclamo oReclamo : listaReclamoFiltro) {
				lista.add(new ReclamoDTO(oReclamo));
			}

			return lista;
		} catch (NumberFormatException e) {
			// Manejar la excepción si el filtro no es un número válido
			throw new ServiciosException("ERROR - El filtro debe ser un número válido.");
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los reclamos.");
		}
	}

	@Override
	public Reclamo getById(Long id) throws ServiciosException {
		try {
			TypedQuery<Reclamo> query = entityManager
					.createQuery("SELECT r FROM Reclamo r WHERE r.idReclamo = :filtro", Reclamo.class)
					.setParameter("filtro", id); // Usar el Long convertido como parámetro
			Reclamo oReclamo = (Reclamo) query.getSingleResult();

			return oReclamo;
		} catch (NumberFormatException e) {
			// Manejar la excepción si el filtro no es un número válido
			throw new ServiciosException("ERROR - El filtro debe ser un número válido.");
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los reclamos.");
		}
	}

}
