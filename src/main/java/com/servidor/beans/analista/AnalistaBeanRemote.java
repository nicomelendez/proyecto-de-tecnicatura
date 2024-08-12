package com.servidor.beans.analista;

import java.util.ArrayList;
import javax.ejb.Remote;

import com.cliente.dto.AnalistaDTO;
import com.servidor.entidades.Analista;
import com.servidor.exception.ServiciosException;

@Remote
public interface AnalistaBeanRemote {

	boolean crear(AnalistaDTO oAnalistaDTO) throws ServiciosException;

	boolean actualizar(AnalistaDTO oAnalistaDTO) throws ServiciosException;

	ArrayList<AnalistaDTO> listar() throws ServiciosException;

	ArrayList<AnalistaDTO> listarActivoFiltro(String filtro) throws ServiciosException;

	AnalistaDTO getByDocumento(String documento) throws ServiciosException;

	Analista getId(long id) throws ServiciosException;
}
