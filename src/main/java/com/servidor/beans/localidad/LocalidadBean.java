package com.servidor.beans.localidad;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.cliente.dto.LocalidadDTO;
import com.servidor.dao.LocalidadDAO;
import com.servidor.exception.ServiciosException;

@Stateless
public class LocalidadBean implements LocalidadBeanRemote {

	@EJB
	private LocalidadDAO oLocalidadDAO;

	public LocalidadDTO crear(LocalidadDTO oLocalidadDTO) throws ServiciosException {
		return oLocalidadDAO.crear(oLocalidadDTO);
	}

	public LocalidadDTO actualizar(LocalidadDTO oLocalidadDTO) throws ServiciosException {
		return oLocalidadDAO.actualizar(oLocalidadDTO);
	}

	public ArrayList<LocalidadDTO> listar() throws ServiciosException {
		return oLocalidadDAO.listar();
	}

	public ArrayList<LocalidadDTO> listarFiltro(String filtro) throws ServiciosException {
		return oLocalidadDAO.listarFiltro(filtro);
	}

	@Override
	public ArrayList<LocalidadDTO> listarLocalidadesPorDepartamento(String nombreDepartamento)
			throws ServiciosException {
		return oLocalidadDAO.listarLocalidadesPorDepartamento(nombreDepartamento);
	}

}
