package com.servidor.beans.constancia.tipo;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.servidor.entidades.Tipo;
import com.servidor.exception.ServiciosException;

@Stateless
public class TipoBean implements TipoBeanRemote {
	@PersistenceContext
	private EntityManager entityManager;

	public TipoBean() {

	}

	@Override
	public boolean crearTipo(Tipo oTipo) throws ServiciosException {
		try {
			entityManager.persist(oTipo);
			entityManager.flush();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean actualizarTipo(Tipo oTipo) throws ServiciosException {
		try {
			entityManager.merge(oTipo);
			entityManager.flush();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean eliminarTipo(Long idTipo) throws ServiciosException {
		try {
			Tipo oTipo = entityManager.find(Tipo.class, idTipo);
			oTipo.setActivo("N");
			entityManager.merge(oTipo);
			entityManager.flush();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public ArrayList<Tipo> listarTipos() throws ServiciosException {
		try {
			TypedQuery<Tipo> query = entityManager.createQuery("SELECT t FROM Tipo t", Tipo.class);
			ArrayList<Tipo> listaTipos = (ArrayList<Tipo>) query.getResultList();
			return listaTipos;
		} catch (PersistenceException e) {
			throw new ServiciosException("ERROR - No se pudieron listar los tipos de constancia.");
		}
	}

	@Override
	public ArrayList<Tipo> listarTipos(String filtro) throws ServiciosException {
		try {
			TypedQuery<Tipo> query = entityManager
					.createQuery("SELECT t FROM Tipo t WHERE t.nombre LIKE :filtro", Tipo.class)
					.setParameter("filtro", "%" + filtro + "%");
			ArrayList<Tipo> listaTiposConstanciasFiltro = (ArrayList<Tipo>) query.getResultList();
			return listaTiposConstanciasFiltro;
		} catch (PersistenceException e) {
			throw new ServiciosException(
					"ERROR - No se pudieron listar los tipos de constancia según el filtro proporcionado.");
		}
	}

	@Override
	public ArrayList<Tipo> listarPorActivo(String filtro) throws ServiciosException {
		try {
			TypedQuery<Tipo> query = entityManager
					.createQuery("SELECT t FROM Tipo t WHERE t.activo = :filtro", Tipo.class)
					.setParameter("filtro", filtro);
			ArrayList<Tipo> listaTiposConstanciasFiltro = (ArrayList<Tipo>) query.getResultList();
			return listaTiposConstanciasFiltro;
		} catch (PersistenceException e) {
			throw new ServiciosException(
					"ERROR - No se pudieron listar los tipos de constancia según el filtro proporcionado.");
		}
	}

	@Override
	public boolean reactivarTipo(Long idTipo) throws ServiciosException {
		try {
			Tipo oTipo = entityManager.find(Tipo.class, idTipo);
			oTipo.setActivo("S");
			entityManager.merge(oTipo);
			entityManager.flush();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Tipo getById(Long idTipo) throws ServiciosException {
		try {
			TypedQuery<Tipo> query = entityManager
					.createQuery("SELECT t FROM Tipo t WHERE t.idTipo = :idTipo", Tipo.class)
					.setParameter("idTipo", idTipo);

			Tipo oTipo = query.getSingleResult();
			return oTipo;
		} catch (Exception e) {
			return null;
		}
	}

}
