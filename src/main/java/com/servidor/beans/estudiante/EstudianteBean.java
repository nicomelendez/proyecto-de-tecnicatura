package com.servidor.beans.estudiante;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.cliente.dto.EstudianteDTO;
import com.servidor.dao.EstudianteDAO;
import com.servidor.exception.ServiciosException;

@Stateless
public class EstudianteBean implements EstudianteBeanRemote {

	@EJB
	private EstudianteDAO oEstudianteDAO;

	@Override
	public boolean crear(EstudianteDTO oEstudianteDTO) throws ServiciosException {
		return oEstudianteDAO.crear(oEstudianteDTO);
	}

	@Override
	public boolean actualizar(EstudianteDTO oEstudianteDTO) throws ServiciosException {
		return oEstudianteDAO.actualizar(oEstudianteDTO);
	}

	@Override
	public ArrayList<EstudianteDTO> listar() throws ServiciosException {
		return oEstudianteDAO.listar();
	}

	@Override
	public EstudianteDTO getByDocumento(String documento) throws ServiciosException {
		return oEstudianteDAO.getByDocumento(documento);
	}

	@Override
	public ArrayList<EstudianteDTO> listarActivoFiltro(String filtro) throws ServiciosException {
		return oEstudianteDAO.listarActivoFiltro(filtro);
	}

}
