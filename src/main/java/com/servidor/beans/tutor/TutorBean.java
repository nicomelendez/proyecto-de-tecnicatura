package com.servidor.beans.tutor;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.cliente.dto.TutorDTO;
import com.servidor.dao.TutorDAO;
import com.servidor.exception.ServiciosException;

@Stateless
public class TutorBean implements TutorBeanRemote {

	@EJB
	private TutorDAO oTutorDAO;

	@Override
	public boolean crear(TutorDTO oTutorDTO) throws ServiciosException {
		return oTutorDAO.crear(oTutorDTO);
	}

	@Override
	public boolean actualizar(TutorDTO oTutorDTO) throws ServiciosException {
		return oTutorDAO.actualizar(oTutorDTO);
	}

	@Override
	public ArrayList<TutorDTO> listar() throws ServiciosException {
		return oTutorDAO.listar();
	}

	@Override
	public TutorDTO getByDocumento(String documento) throws ServiciosException {
		return oTutorDAO.getByDocumento(documento);
	}

	@Override
	public ArrayList<TutorDTO> listarActivoFiltro(String filtro) throws ServiciosException {
		return oTutorDAO.listarActivoFiltro(filtro);
	}

}
