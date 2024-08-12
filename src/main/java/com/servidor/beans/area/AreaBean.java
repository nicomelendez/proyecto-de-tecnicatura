package com.servidor.beans.area;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.cliente.dto.AreaDTO;
import com.servidor.dao.AreaDAO;
import com.servidor.exception.ServiciosException;

@Stateless
public class AreaBean implements AreaBeanRemote {

	@EJB
	private AreaDAO oAreaDAO;

	public AreaDTO crear(AreaDTO oAreaDTO) throws ServiciosException {
		return oAreaDAO.crear(oAreaDTO);
	}

	public boolean actualizar(AreaDTO oAreaDTO) throws ServiciosException {
		return oAreaDAO.actualizar(oAreaDTO);
	}

	public ArrayList<AreaDTO> listar() throws ServiciosException {
		return oAreaDAO.listar();
	}

	public ArrayList<AreaDTO> listarFiltro(String filtro) throws ServiciosException {
		return oAreaDAO.listarFiltro(filtro);
	}

}
