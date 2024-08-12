package com.servidor.beans.tutor;

import java.util.ArrayList;
import javax.ejb.Remote;

import com.cliente.dto.TutorDTO;
import com.servidor.exception.ServiciosException;

@Remote
public interface TutorBeanRemote {

	boolean crear(TutorDTO oTutorDTO) throws ServiciosException;

	boolean actualizar(TutorDTO oTutorDTO) throws ServiciosException;

	ArrayList<TutorDTO> listar() throws ServiciosException;

	ArrayList<TutorDTO> listarActivoFiltro(String filtro) throws ServiciosException;

	TutorDTO getByDocumento(String documento) throws ServiciosException;
}
