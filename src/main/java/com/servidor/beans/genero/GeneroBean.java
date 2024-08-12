package com.servidor.beans.genero;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.cliente.dto.GeneroDTO;
import com.servidor.dao.GeneroDAO;
import com.servidor.exception.ServiciosException;

@Stateless
public class GeneroBean implements GeneroBeanRemote {

	@EJB
	private GeneroDAO oGeneroDAO;

	public GeneroDTO crear(GeneroDTO oGeneroDTO) throws ServiciosException {
		return oGeneroDAO.crear(oGeneroDTO);
	}

	public GeneroDTO actualizar(GeneroDTO oGeneroDTO) throws ServiciosException {
		return oGeneroDAO.actualizar(oGeneroDTO);
	}

	public ArrayList<GeneroDTO> listar() throws ServiciosException {
		return oGeneroDAO.listar();
	}

	public ArrayList<GeneroDTO> listarFiltro(String filtro) throws ServiciosException {
		return oGeneroDAO.listarFiltro(filtro);
	}

}
