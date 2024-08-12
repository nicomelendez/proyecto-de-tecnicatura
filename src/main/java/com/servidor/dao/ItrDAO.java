package com.servidor.dao;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.cliente.dto.ItrDTO;
import com.servidor.entidades.Itr;

@Stateless
public class ItrDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public ItrDTO crear(ItrDTO oItrDTO) {
		try {
			Itr oITR = new Itr(oItrDTO);
			entityManager.persist(oITR);
			entityManager.flush();
			return new ItrDTO(oITR);
		} catch (Exception e) {
			return null;
		}
	}

	public ItrDTO actualizar(ItrDTO oItrDTO) {
		try {
			Itr oITR = new Itr(oItrDTO);
			entityManager.merge(oITR);
			entityManager.flush();
			return new ItrDTO(oITR);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean eliminar(Long idITR) {
		try {
			Itr oITR = entityManager.find(Itr.class, idITR);
			oITR.setActivo("N");
			entityManager.flush();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public ArrayList<ItrDTO> listar() {
		ArrayList<ItrDTO> listaDeItrs = new ArrayList<ItrDTO>();
		try {
			TypedQuery<Itr> query = entityManager.createQuery("SELECT i FROM Itr i", Itr.class);
			for (Itr oItr : query.getResultList()) {
				listaDeItrs.add(new ItrDTO(oItr));
			}
			return listaDeItrs;
		} catch (Exception e) {
			return null;
		}
	}

	public ArrayList<ItrDTO> listarFiltro(String filtro) {
		ArrayList<ItrDTO> listaDeItrs = new ArrayList<ItrDTO>();
		try {
			TypedQuery<Itr> query = entityManager
					.createQuery("SELECT i FROM Itr i WHERE i.nombre LIKE :filtro", Itr.class)
					.setParameter("filtro", "%" + filtro + "%");
			for (Itr oItr : query.getResultList()) {
				listaDeItrs.add(new ItrDTO(oItr));
			}
			return listaDeItrs;
		} catch (Exception e) {
			return null;
		}
	}

	public ArrayList<ItrDTO> listarActivoFiltro(String filtro) {
		ArrayList<ItrDTO> listaDeItrs = new ArrayList<ItrDTO>();
		try {
			TypedQuery<Itr> query = entityManager.createQuery("SELECT i FROM Itr i WHERE i.activo = :filtro", Itr.class)
					.setParameter("filtro", filtro);
			for (Itr oItr : query.getResultList()) {
				listaDeItrs.add(new ItrDTO(oItr));
			}
			return listaDeItrs;
		} catch (Exception e) {
			return null;
		}
	}

	public ItrDTO getById(Long idItr) {
		try {
			TypedQuery<Itr> query = entityManager.createNamedQuery("Itr.getById", Itr.class).setParameter("idItr",
					idItr);

			Itr oItr = query.getSingleResult();
			return new ItrDTO(oItr);
		} catch (Exception e) {
			return null;
		}
	}
}
