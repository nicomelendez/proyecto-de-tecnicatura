package com.servidor.beans.analista;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.cliente.dto.AnalistaDTO;
import com.servidor.dao.AnalistaDAO;
import com.servidor.entidades.Analista;
import com.servidor.exception.ServiciosException;

@Stateless
public class AnalistaBean implements AnalistaBeanRemote {

	@EJB
	private AnalistaDAO oAnalistaDAO;

	@Override
	public boolean crear(AnalistaDTO oAnalistaDTO) throws ServiciosException {
		return oAnalistaDAO.crear(oAnalistaDTO);
	}

	@Override
	public boolean actualizar(AnalistaDTO oAnalistaDTO) throws ServiciosException {
		return oAnalistaDAO.actualizar(oAnalistaDTO);
	}

	@Override
	public ArrayList<AnalistaDTO> listar() throws ServiciosException {
		return oAnalistaDAO.listar();
	}

	@Override
	public AnalistaDTO getByDocumento(String documento) throws ServiciosException {
		return oAnalistaDAO.getByDocumento(documento);
	}

	@Override
	public ArrayList<AnalistaDTO> listarActivoFiltro(String filtro) throws ServiciosException {
		return oAnalistaDAO.listarActivoFiltro(filtro);
	}

	@Override
	public Analista getId(long id) throws ServiciosException {
		return oAnalistaDAO.getId(id);
	}

}
