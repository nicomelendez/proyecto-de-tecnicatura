package com.servidor.beans.estado;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.servidor.entidades.Estado;
import com.servidor.exception.ServiciosException;

@Remote
public interface EstadoBeanRemote {

	boolean crearEstado(Estado oEstado) throws ServiciosException;

	boolean actualizarEstado(Estado oEstado) throws ServiciosException;

	boolean eliminarEstado(Long idEstado) throws ServiciosException;
	
	boolean reactivarEstado (Long idEstado) throws ServiciosException;

	ArrayList<Estado> listarEstados() throws ServiciosException;
	
	ArrayList<Estado> listarPorActivo(String filtro) throws ServiciosException;

	Estado getById(Long idEstado) throws ServiciosException;
}
