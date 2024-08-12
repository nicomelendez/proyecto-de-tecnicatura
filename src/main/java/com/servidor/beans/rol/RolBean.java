package com.servidor.beans.rol;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.cliente.dto.RolDTO;
import com.servidor.dao.RolDAO;
import com.servidor.exception.ServiciosException;

@Stateless
public class RolBean implements RolBeanRemote {

	@EJB
	private RolDAO oRolDAO;

	@Override
	public RolDTO crear(RolDTO oRolDTO) throws ServiciosException {
		return oRolDAO.crear(oRolDTO);
	}

	@Override
	public RolDTO actualizar(RolDTO oRolDTO) throws ServiciosException {
		return oRolDAO.actualizar(oRolDTO);
	}

	@Override
	public boolean eliminar(Long idRol) throws ServiciosException {
		return oRolDAO.eliminar(idRol);
	}

	@Override
	public ArrayList<RolDTO> listar() throws ServiciosException {
		return oRolDAO.listar();
	}

	@Override
	public ArrayList<RolDTO> listarFiltro(String filtro) throws ServiciosException {
		return oRolDAO.listarFiltro(filtro);
	}

}
