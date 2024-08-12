package com.servidor.dao;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cliente.dto.RolDTO;
import com.servidor.entidades.Rol;
import com.servidor.exception.ServiciosException;

@Stateless
public class RolDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public RolDTO crear(RolDTO oRolDTO) {
		try {
			Rol oRol = new Rol(oRolDTO);
			entityManager.persist(oRol);
			entityManager.flush();
			System.out.println(oRol.getIdRol());
			return new RolDTO(oRol);
		} catch (PersistenceException e) {
			System.out.println("---------");
			System.out.println(e.getMessage());
			return null;
		}
	}

	public RolDTO actualizar(RolDTO oRolDTO) {
		try {
			Rol oRol = new Rol(oRolDTO);
			entityManager.merge(oRol);
			entityManager.flush();
			return new RolDTO(oRol);
		} catch (PersistenceException e) {
			return null;
		}
	}

	public boolean eliminar(Long idRol) {
		try {
			Rol oRol = entityManager.find(Rol.class, idRol);
			entityManager.remove(oRol);
			entityManager.flush();
			return true;
		} catch (PersistenceException e) {
			return false;
		}
	}

	public ArrayList<RolDTO> listar() {
		ArrayList<RolDTO> listaDeRoles = new ArrayList<RolDTO>();
		try {
			TypedQuery<Rol> query = entityManager.createQuery("SELECT r FROM Rol r", Rol.class);
			for (Rol oRol : query.getResultList()) {
				listaDeRoles.add(new RolDTO(oRol));
			}
			return listaDeRoles;
		} catch (Exception e) {
			return null;
		}
	}

	public ArrayList<RolDTO> listarFiltro(String filtro) throws ServiciosException {
		ArrayList<RolDTO> listaDeRoles = new ArrayList<RolDTO>();
		try {
			TypedQuery<Rol> query = entityManager
					.createQuery("SELECT r FROM Rol r WHERE r.descripcion LIKE :filtro", Rol.class)
					.setParameter("filtro", "%" + filtro + "%");
			for (Rol oRol : query.getResultList()) {
				listaDeRoles.add(new RolDTO(oRol));
			}
			return listaDeRoles;
		} catch (Exception e) {
			return null;
		}
	}
}
