package com.servidor.beans.constancia.estado;

import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cliente.dto.EstadoConstanciaDTO;
import com.cliente.services.ServiceAnalista;
import com.servidor.entidades.Estado;
import com.servidor.entidades.EstadoConstancia;
import com.servidor.exception.ServiciosException;

@Stateless
public class EstadoConstanciaBean implements EstadoConstanciaBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public EstadoConstanciaBean() {

	}

	public boolean crearEstadoConstancia(EstadoConstancia oEstadoConstancia) throws ServiciosException {
		try {
			entityManager.persist(oEstadoConstancia);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo crear el estado constancia.");
		}
	}

	public boolean actualizarEstadoConstancia(EstadoConstancia oEstadoConstancia) throws ServiciosException {
		try {
			entityManager.merge(oEstadoConstancia);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar el estado constancia.");
		}
	}

	public boolean eliminarEstadoConstancia(Long idEstadoConstancia) throws ServiciosException {
		try {
			EstadoConstancia oEstadoConstancia = entityManager.find(EstadoConstancia.class, idEstadoConstancia);
			entityManager.remove(oEstadoConstancia);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar el estado constancia.");
		}
	}

	public ArrayList<EstadoConstancia> listarEstadosConstancias() throws ServiciosException {
		try {
			TypedQuery<EstadoConstancia> query = entityManager.createQuery("SELECT ec FROM EstadoConstancia ec",
					EstadoConstancia.class);
			ArrayList<EstadoConstancia> listaEstadosConstancias = (ArrayList<EstadoConstancia>) query.getResultList();
			return listaEstadosConstancias;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los estados constancias.");
		}
	}

	public ArrayList<EstadoConstancia> listarEstadosConstanciasPorId(Long idConstancia) throws ServiciosException {
		try {
			TypedQuery<EstadoConstancia> query = entityManager
					.createQuery("SELECT ec FROM EstadoConstancia ec WHERE ec.id.idConstancia = :filtro",
							EstadoConstancia.class)
					.setParameter("filtro", idConstancia);
			ArrayList<EstadoConstancia> listaEstadosConstanciasFiltro = (ArrayList<EstadoConstancia>) query
					.getResultList();
			return listaEstadosConstanciasFiltro;
		} catch (PersistenceException e) {
			throw new ServiciosException(
					"ERROR - No se pudieron listar los estados constancias según el filtro proporcionado.");
		}
	}

	@Override
	public String getFechaEmision(Long idConstancia) throws ServiciosException {
		try {
			TypedQuery<Date> query = entityManager.createQuery(
					"SELECT ec.id.fechaHora FROM EstadoConstancia ec WHERE ec.id.idConstancia = :filtro ORDER BY ec.id.fechaHora ASC",
					Date.class).setParameter("filtro", idConstancia).setMaxResults(1);
			Date fechaEmision = query.getSingleResult();
			// Crear una instancia de SimpleDateFormat que corresponda al formato deseado
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	        // Formatear la fecha y retornarla como String
	        return sdf.format(fechaEmision) + " hs";
	        
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo obtener la fecha de emisión para la constancia.");
		}
	}

	@Override
	public String getEstadoConstancia(Long idConstancia) throws ServiciosException {
		try {
			TypedQuery<Estado> query = entityManager.createQuery(
					"SELECT ec.estado FROM EstadoConstancia ec WHERE ec.id.idConstancia = :filtro ORDER BY ec.id.fechaHora DESC",
					Estado.class).setParameter("filtro", idConstancia).setMaxResults(1);
			Estado estadoActual = query.getSingleResult();
			return estadoActual.getDescripcion();
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo obtener el estado actual de la constancia.");
		}
	}

	@Override
	public String getNotaDelEstudiante(Long idConstancia) throws ServiciosException {
		try {
			TypedQuery<String> query = entityManager.createQuery(
					"SELECT ec.detalle FROM EstadoConstancia ec WHERE ec.id.idConstancia = :filtro ORDER BY ec.id.fechaHora ASC",
					String.class).setParameter("filtro", idConstancia).setMaxResults(1);
			String notaEstudiante = query.getSingleResult();
			return notaEstudiante;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo obtener la nota del estudiante para la constancia.");
		}
	}

	@Override
	public String getNotaDelAnalista(Long idConstancia) throws ServiciosException {
		try {
			TypedQuery<String> query = entityManager.createQuery(
					"SELECT ec.detalle FROM EstadoConstancia ec WHERE ec.id.idConstancia = :filtro ORDER BY ec.id.fechaHora DESC",
					String.class).setParameter("filtro", idConstancia).setMaxResults(1);
			String notaAnalista = query.getSingleResult();
			return notaAnalista;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo obtener la nota del analista para la constancia.");
		}
	}

	@Override
	public String getNombreDelAnalista(Long idConstancia) throws ServiciosException {
		try {
			TypedQuery<Long> query = entityManager.createQuery(
					"SELECT ec.analista.idAnalista FROM EstadoConstancia ec WHERE ec.id.idConstancia = :filtro ORDER BY ec.id.fechaHora DESC",
					Long.class).setParameter("filtro", idConstancia).setMaxResults(1);
			Long idAnalista = query.getSingleResult();

			String nombreAnalista = ServiceAnalista.getById(idAnalista).getUsuario().getNombreCompleto();

			return nombreAnalista;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo obtener el nombre del analista para la constancia.");
		}
	}

}
