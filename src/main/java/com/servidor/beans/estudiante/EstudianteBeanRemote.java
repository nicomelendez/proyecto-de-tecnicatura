package com.servidor.beans.estudiante;

import java.util.ArrayList;
import javax.ejb.Remote;

import com.cliente.dto.EstudianteDTO;
import com.servidor.exception.ServiciosException;

@Remote
public interface EstudianteBeanRemote {

	boolean crear(EstudianteDTO oEstudianteDTO) throws ServiciosException;

	boolean actualizar(EstudianteDTO oEstudianteDTO) throws ServiciosException;

	ArrayList<EstudianteDTO> listar() throws ServiciosException;

	ArrayList<EstudianteDTO> listarActivoFiltro(String filtro) throws ServiciosException;

	EstudianteDTO getByDocumento(String documento) throws ServiciosException;
}
