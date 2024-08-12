package com.servidor.beans.area;

import java.util.ArrayList;
import javax.ejb.Remote;

import com.cliente.dto.AreaDTO;
import com.servidor.exception.ServiciosException;

@Remote
public interface AreaBeanRemote {

	AreaDTO crear(AreaDTO oAreaDTO) throws ServiciosException;

	boolean actualizar(AreaDTO oAreaDTO) throws ServiciosException;

	ArrayList<AreaDTO> listar() throws ServiciosException;

	ArrayList<AreaDTO> listarFiltro(String filtro) throws ServiciosException;

}
