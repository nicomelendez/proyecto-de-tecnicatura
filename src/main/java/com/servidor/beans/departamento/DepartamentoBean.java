package com.servidor.beans.departamento;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.cliente.dto.DepartamentoDTO;
import com.servidor.dao.DepartamentoDAO;
import com.servidor.exception.ServiciosException;

@Stateless
public class DepartamentoBean implements DepartamentoBeanRemote {

	@EJB
	private DepartamentoDAO oDepartamentoDAO;

	public DepartamentoDTO crear(DepartamentoDTO oDepartamentoDTO) throws ServiciosException {
		return oDepartamentoDAO.crear(oDepartamentoDTO);
	}

	public DepartamentoDTO actualizar(DepartamentoDTO oDepartamentoDTO) throws ServiciosException {
		return oDepartamentoDAO.actualizar(oDepartamentoDTO);
	}

	public ArrayList<DepartamentoDTO> listar() throws ServiciosException {
		return oDepartamentoDAO.listar();
	}

	public ArrayList<DepartamentoDTO> listarFiltro(String filtro) throws ServiciosException {
		return oDepartamentoDAO.listarFiltro(filtro);
	}

}
