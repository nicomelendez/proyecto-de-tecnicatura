package com.servidor.beans.reclamo_estado;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.servidor.entidades.Estado;
import com.servidor.entidades.EstadoReclamo;
import com.servidor.exception.ServiciosException;

@Stateless
public class EstadoReclamoBean implements EstadoReclamoBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public EstadoReclamoBean() {

	}

	@Override
	public boolean crearEstadoReclamo(EstadoReclamo oEstadoReclamo) throws ServiciosException {
		try {
			entityManager.persist(oEstadoReclamo);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo crear el estado reclamo.");
		}
	}

	@Override
	public boolean actualizarEstadoReclamo(EstadoReclamo oEstadoReclamo) throws ServiciosException {
		try {
			entityManager.merge(oEstadoReclamo);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar el estado reclamo.");
		}
	}

	@Override
	public boolean eliminarEstadoReclamo(Long idEstadoReclamo) throws ServiciosException {
		try {
			EstadoReclamo oEstadoReclamo = entityManager.find(EstadoReclamo.class, idEstadoReclamo);
			entityManager.remove(oEstadoReclamo);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar el estado reclamo.");
		}
	}

	@Override
	public ArrayList<EstadoReclamo> listarEstadosReclamo() throws ServiciosException {
		try {
			TypedQuery<EstadoReclamo> query = entityManager.createQuery("SELECT er FROM EstadoReclamo er",
					EstadoReclamo.class);
			ArrayList<EstadoReclamo> listaEstadosReclamo = (ArrayList<EstadoReclamo>) query.getResultList();
			return listaEstadosReclamo;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los estados reclamo.");
		}
	}

	@Override
	public ArrayList<EstadoReclamo> listarEstadosReclamo(String filtro) throws ServiciosException {
		try {
			TypedQuery<EstadoReclamo> query = entityManager
					.createQuery("SELECT er FROM EstadoReclamo er WHERE er.detalle LIKE :filtro", EstadoReclamo.class);
			ArrayList<EstadoReclamo> listaEstadosReclamo = (ArrayList<EstadoReclamo>) query.getResultList();
			return listaEstadosReclamo;
		} catch (PersistenceException e) {
			throw new ServiciosException(
					"ERROR - No se pudieron listar los estados reclamo según el filtro seleccionado.");
		}
	}

	@Override
	public ArrayList<EstadoReclamo> listarEstadosReclamoPorIdReclamo(Long idReclamo) throws ServiciosException {
		try {
			TypedQuery<EstadoReclamo> query = entityManager
					.createQuery("SELECT er FROM EstadoReclamo er WHERE er.id.idReclamo = :filtro", EstadoReclamo.class)
					.setParameter("filtro", idReclamo);
			ArrayList<EstadoReclamo> listaEstadosReclamo = (ArrayList<EstadoReclamo>) query.getResultList();
			return listaEstadosReclamo;
		} catch (PersistenceException e) {
			throw new ServiciosException(
					"ERROR - No se pudieron listar los estados reclamo según el filtro seleccionado.");
		}
	}

	@Override
	public String getEstadoActual(Long idReclamo) throws ServiciosException {
		try {
			TypedQuery<Estado> query = entityManager.createQuery(
					"SELECT er.estado FROM EstadoReclamo er WHERE er.id.idReclamo = :filtro ORDER BY er.id.fechaHora DESC",
					Estado.class).setParameter("filtro", idReclamo).setMaxResults(1);
			Estado estado = query.getSingleResult();
			return estado.getDescripcion(); // Asegúrate de que Estado tiene un método getDescripcion
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo obtener el estado actual del reclamo.");
		}
	}

	@Override
	public String getFechaEmision(Long idReclamo) throws ServiciosException {
		try {
			TypedQuery<Date> query = entityManager.createQuery(
					"SELECT er.id.fechaHora FROM EstadoReclamo er WHERE er.id.idReclamo = :filtro ORDER BY er.id.fechaHora ASC",
					Date.class).setParameter("filtro", idReclamo).setMaxResults(1);
			Date fechaEmision = query.getSingleResult();
			// Crear una instancia de SimpleDateFormat que corresponda al formato deseado
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	        // Formatear la fecha y retornarla como String
	        return sdf.format(fechaEmision) + " hs";

		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo obtener la fecha de emisión del reclamo.");
		}
	}
	
	@Override
	public String getUltimaFecha(Long idReclamo) throws ServiciosException {
		try {
			TypedQuery<Date> query = entityManager.createQuery(
					"SELECT er.id.fechaHora FROM EstadoReclamo er WHERE er.id.idReclamo = :filtro ORDER BY er.id.fechaHora DESC",
					Date.class).setParameter("filtro", idReclamo).setMaxResults(1);
			Date fechaEmision = query.getSingleResult();
			// Crear una instancia de SimpleDateFormat que corresponda al formato deseado
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	        // Formatear la fecha y retornarla como String
	        return sdf.format(fechaEmision) + " hs";

		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo obtener la fecha de emisión del reclamo.");
		}
	}

	@Override
	public String getDetalleDelEstudiante(Long idReclamo) throws ServiciosException {
		try {
			TypedQuery<String> query = entityManager.createQuery(
					"SELECT er.detalle FROM EstadoReclamo er WHERE er.id.idReclamo = :filtro ORDER BY er.id.fechaHora ASC",
					String.class).setParameter("filtro", idReclamo).setMaxResults(1);
			String detalle = query.getSingleResult();
			return detalle;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo obtener el detalle del estudiante para el reclamo.");
		}
	}

}
