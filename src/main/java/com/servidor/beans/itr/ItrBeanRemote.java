package com.servidor.beans.itr;

import java.util.ArrayList;
import javax.ejb.Remote;

import com.cliente.dto.ItrDTO;
import com.servidor.entidades.Itr;
import com.servidor.exception.ServiciosException;

@Remote
public interface ItrBeanRemote {
	ItrDTO crear(ItrDTO oItrDTO) throws ServiciosException;

	ItrDTO actualizar(ItrDTO oItrDTO) throws ServiciosException;

	boolean eliminar(Long idITR) throws ServiciosException;

	ArrayList<ItrDTO> listar() throws ServiciosException;

	ItrDTO getById(Long idItr) throws ServiciosException;

	ArrayList<ItrDTO> listarActivoFiltro(String filtro) throws ServiciosException;

	ArrayList<ItrDTO> listarFiltro(String filtro) throws ServiciosException;
}
