package com.servidor.beans.rol;

import java.util.ArrayList;
import javax.ejb.Remote;

import com.cliente.dto.RolDTO;
import com.servidor.entidades.Rol;
import com.servidor.exception.ServiciosException;

@Remote
public interface RolBeanRemote {

	RolDTO crear(RolDTO oRolDTO) throws ServiciosException;

	RolDTO actualizar(RolDTO oRolDTO) throws ServiciosException;

	boolean eliminar(Long idRol) throws ServiciosException;

	ArrayList<RolDTO> listar() throws ServiciosException;

	ArrayList<RolDTO> listarFiltro(String filtro) throws ServiciosException;

}
