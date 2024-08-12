package com.servidor.beans.genero;

import java.util.ArrayList;
import javax.ejb.Remote;

import com.cliente.dto.GeneroDTO;
import com.servidor.exception.ServiciosException;

@Remote
public interface GeneroBeanRemote {

	GeneroDTO crear(GeneroDTO oGeneroDTO) throws ServiciosException;

	GeneroDTO actualizar(GeneroDTO oGeneroDTO) throws ServiciosException;

	ArrayList<GeneroDTO> listar() throws ServiciosException;

	ArrayList<GeneroDTO> listarFiltro(String filtro) throws ServiciosException;

}
