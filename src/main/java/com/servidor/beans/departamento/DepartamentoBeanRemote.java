package com.servidor.beans.departamento;

import java.util.ArrayList;
import javax.ejb.Remote;

import com.cliente.dto.DepartamentoDTO;
import com.servidor.exception.ServiciosException;

@Remote
public interface DepartamentoBeanRemote {

	DepartamentoDTO crear(DepartamentoDTO oDepartamentoDTO) throws ServiciosException;

	DepartamentoDTO actualizar(DepartamentoDTO oDepartamentoDTO) throws ServiciosException;

	ArrayList<DepartamentoDTO> listar() throws ServiciosException;

	ArrayList<DepartamentoDTO> listarFiltro(String filtro) throws ServiciosException;

}
