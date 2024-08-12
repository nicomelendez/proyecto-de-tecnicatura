package com.servidor.beans.localidad;

import java.util.ArrayList;
import javax.ejb.Remote;

import com.cliente.dto.LocalidadDTO;
import com.servidor.entidades.Localidad;
import com.servidor.exception.ServiciosException;

@Remote
public interface LocalidadBeanRemote {

	LocalidadDTO crear(LocalidadDTO oLocalidadDTO) throws ServiciosException;

	LocalidadDTO actualizar(LocalidadDTO oLocalidadDTO) throws ServiciosException;

	ArrayList<LocalidadDTO> listar() throws ServiciosException;

	ArrayList<LocalidadDTO> listarFiltro(String filtro) throws ServiciosException;
	
	ArrayList<LocalidadDTO> listarLocalidadesPorDepartamento(String nombreDepartamento) throws ServiciosException;
}
