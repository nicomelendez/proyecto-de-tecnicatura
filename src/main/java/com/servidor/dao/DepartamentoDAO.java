package com.servidor.dao;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.cliente.dto.DepartamentoDTO;
import com.servidor.entidades.Departamento;

@Stateless
public class DepartamentoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public DepartamentoDTO crear(DepartamentoDTO oDepartamentoDTO) {
		try {
			Departamento oDepartamento = new Departamento(oDepartamentoDTO);
			entityManager.persist(oDepartamento);
			entityManager.flush();
			return new DepartamentoDTO(oDepartamento);
		} catch (PersistenceException e) {
			return null;
		}
	}

	public DepartamentoDTO actualizar(DepartamentoDTO oDepartamentoDTO) {
		try {
			Departamento oDepartamento = new Departamento(oDepartamentoDTO);
			entityManager.merge(oDepartamento);
			entityManager.flush();
			return new DepartamentoDTO(oDepartamento);
		} catch (PersistenceException e) {
			return null;
		}
	}

	public ArrayList<DepartamentoDTO> listar() {
		ArrayList<DepartamentoDTO> listaDeDepartamentos = new ArrayList<DepartamentoDTO>();
		try {
			TypedQuery<Departamento> query = entityManager.createQuery("SELECT d FROM Departamento d",
					Departamento.class);
			for (Departamento oDepartamento : query.getResultList()) {
				listaDeDepartamentos.add(new DepartamentoDTO(oDepartamento));
			}
			return listaDeDepartamentos;
		} catch (PersistenceException e) {
			return null;
		}
	}

	public ArrayList<DepartamentoDTO> listarFiltro(String filtro) {
		ArrayList<DepartamentoDTO> listaDeDepartamentos = new ArrayList<DepartamentoDTO>();
		try {
			TypedQuery<Departamento> query = entityManager
					.createQuery("SELECT d FROM Departamento d WHERE d.nombre LIKE :filtro", Departamento.class)
					.setParameter("filtro", "%" + filtro + "%");
			for (Departamento oDepartamento : query.getResultList()) {
				listaDeDepartamentos.add(new DepartamentoDTO(oDepartamento));
			}
			return listaDeDepartamentos;
		} catch (PersistenceException e) {
			return null;
		}
	}
}
