package com.servidor.beans.reclamo;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.cliente.dto.ReclamoDTO;
import com.servidor.entidades.Reclamo;
import com.servidor.exception.ServiciosException;

@Remote
public interface ReclamoBeanRemote {

	Reclamo crearReclamo(Reclamo oReclamo) throws ServiciosException;

	boolean actualizarReclamo(Reclamo oReclamo) throws ServiciosException;

	boolean eliminarReclamo(Long idReclamo) throws ServiciosException;

	ArrayList<ReclamoDTO> listarReclamos() throws ServiciosException;

	ArrayList<ReclamoDTO> listarReclamoFiltro(String filtro) throws ServiciosException;

	Reclamo getById(Long id) throws ServiciosException;

}
