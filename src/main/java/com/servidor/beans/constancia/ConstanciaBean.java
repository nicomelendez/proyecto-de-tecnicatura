package com.servidor.beans.constancia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.servidor.entidades.Constancia;
import com.servidor.exception.ServiciosException;

@Stateless
public class ConstanciaBean implements ConstanciaBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public ConstanciaBean() {

	}

	public Constancia crearConstancia(Constancia oConstancia) throws ServiciosException {
		try {
			entityManager.persist(oConstancia);
			entityManager.flush();
			return oConstancia;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo crear la constancia.");
		}
	}

	public boolean actualizarConstancia(Constancia oConstancia) throws ServiciosException {
		try {
			entityManager.merge(oConstancia);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo actualizar la constancia.");
		}
	}

	public boolean eliminarConstancia(Long idConstancia) throws ServiciosException {
		try {
			Constancia oConstancia = entityManager.find(Constancia.class, idConstancia);
			oConstancia.setActivo("N");
			entityManager.merge(oConstancia);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudo eliminar la constancia.");
		}
	}

	public ArrayList<Constancia> listarConstancias() throws ServiciosException {
		try {
			TypedQuery<Constancia> query = entityManager.createQuery(
					"SELECT c FROM Constancia c JOIN FETCH c.estudiante JOIN FETCH c.evento", Constancia.class);
			ArrayList<Constancia> listaConstancias = (ArrayList<Constancia>) query.getResultList();
			return listaConstancias;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar las constancias.");
		}
	}

	public ArrayList<Constancia> listarConstanciasFiltro(String filtro) throws ServiciosException {
		try {
			TypedQuery<Constancia> query = entityManager
					.createQuery("SELECT c FROM Constancia c WHERE c.detalle LIKE :filtro", Constancia.class)
					.setParameter("filtro", "%" + filtro + "%");
			ArrayList<Constancia> listaConstanciasFiltro = (ArrayList<Constancia>) query.getResultList();
			return listaConstanciasFiltro;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar las constancias según el filtro seleccionado.");
		}
	}

	@Override
	public ArrayList<Constancia> listarConstanciasConEstado() throws ServiciosException {
		try {
			TypedQuery<Constancia> query = entityManager.createQuery(
					"SELECT c FROM Constancia c JOIN FETCH c.estudiante JOIN FETCH c.evento JOIN FETCH c.estadosConstancias",
					Constancia.class);
			ArrayList<Constancia> listaConstancias = (ArrayList<Constancia>) query.getResultList();
			return listaConstancias;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar las constancias.");
		}
	}

	@Override
	public Constancia getById(long id) throws ServiciosException {
		try {
			TypedQuery<Constancia> query = entityManager.createQuery(
					"SELECT c FROM Constancia c JOIN FETCH c.estudiante JOIN FETCH c.evento WHERE c.id = :id",
					Constancia.class);
			query.setParameter("id", id);
			Constancia constancia = query.getSingleResult();
			return constancia;

		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar las constancias.");
		}
	}

	@Override
	public ArrayList<Constancia> listarConstanciasEstudiante(long idEstudiante) throws ServiciosException {
		try {
			TypedQuery<Constancia> query = entityManager.createQuery(
					"SELECT c FROM Constancia c JOIN FETCH c.estudiante JOIN FETCH c.evento WHERE c.estudiante.idEstudiante = :filtro AND c.activo = 'S'",
					Constancia.class).setParameter("filtro", idEstudiante);
			ArrayList<Constancia> listaConstanciasFiltro = (ArrayList<Constancia>) query.getResultList();
			return listaConstanciasFiltro;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar las constancias según el filtro seleccionado.");
		}
	}

	@Override
	public ArrayList<Constancia> listarConstanciasEstudiantePorMesYAnio(BigDecimal documento, int mes, int anio)
			throws ServiciosException {
		try {
			// Construir la fecha inicial (primer día del mes)
			Calendar inicioMes = Calendar.getInstance();
			inicioMes.set(Calendar.YEAR, anio);
			inicioMes.set(Calendar.MONTH, mes - 1); // Los meses en Calendar son 0-based
			inicioMes.set(Calendar.DAY_OF_MONTH, 1);
			inicioMes.set(Calendar.HOUR_OF_DAY, 0);
			inicioMes.set(Calendar.MINUTE, 0);
			inicioMes.set(Calendar.SECOND, 0);
			inicioMes.set(Calendar.MILLISECOND, 0);

			// Construir la fecha final (último día del mes)
			Calendar finMes = (Calendar) inicioMes.clone();
			finMes.add(Calendar.MONTH, 1);
			finMes.add(Calendar.MILLISECOND, -1);

			TypedQuery<Long> resultado = entityManager
					.createQuery("SELECT DISTINCT ec.id.idConstancia FROM EstadoConstancia ec "
							+ "WHERE ec.id.fechaHora >= :inicioMes AND ec.id.fechaHora <= :finMes", Long.class)
					.setParameter("inicioMes", inicioMes.getTime()).setParameter("finMes", finMes.getTime());

			List<Long> idsConstancia = resultado.getResultList();
			if (idsConstancia.isEmpty()) {
				return new ArrayList<>(); // Devuelve una lista vacía si no hay IDs
			}
			TypedQuery<Constancia> query = entityManager
					.createQuery("SELECT c FROM Constancia c JOIN FETCH c.estudiante e JOIN FETCH c.evento "
							+ "WHERE e.usuario.documento = :documento " + "AND c.activo = 'S' "
							+ "AND c.idConstancia IN :idsConstancia", Constancia.class)
					.setParameter("documento", documento).setParameter("idsConstancia", idsConstancia);

			ArrayList<Constancia> listaConstanciasFiltro = (ArrayList<Constancia>) query.getResultList();
			
			listaConstanciasFiltro.sort(Comparator.comparing(Constancia::getFecha));

			return listaConstanciasFiltro;
			
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar las constancias según el filtro seleccionado.");
		}
	}

}
