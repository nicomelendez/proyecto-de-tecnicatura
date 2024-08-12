package com.servidor.beans.itr;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.cliente.dto.ItrDTO;
import com.servidor.dao.ItrDAO;
import com.servidor.exception.ServiciosException;

@Stateless
public class ItrBean implements ItrBeanRemote {

	@EJB
	private ItrDAO oItrDAO;

	@Override
	public ItrDTO crear(ItrDTO oItrDTO) throws ServiciosException {
		return oItrDAO.crear(oItrDTO);
	}

	@Override
	public ItrDTO actualizar(ItrDTO oItrDTO) throws ServiciosException {
		return oItrDAO.actualizar(oItrDTO);
	}

	@Override
	public boolean eliminar(Long idITR) throws ServiciosException {
		return oItrDAO.eliminar(idITR);
	}

	@Override
	public ArrayList<ItrDTO> listar() throws ServiciosException {
		return oItrDAO.listar();
	}

	@Override
	public ArrayList<ItrDTO> listarFiltro(String filtro) throws ServiciosException {
		return oItrDAO.listarFiltro(filtro);
	}

	@Override
	public ItrDTO getById(Long idItr) throws ServiciosException {
		return oItrDAO.getById(idItr);
	}

	@Override
	public ArrayList<ItrDTO> listarActivoFiltro(String filtro) throws ServiciosException {
		return oItrDAO.listarActivoFiltro(filtro);
	}

}
