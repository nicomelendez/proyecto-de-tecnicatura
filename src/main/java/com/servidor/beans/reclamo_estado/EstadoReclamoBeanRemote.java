package com.servidor.beans.reclamo_estado;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.servidor.entidades.EstadoReclamo;
import com.servidor.exception.ServiciosException;

@Remote
public interface EstadoReclamoBeanRemote {

	boolean crearEstadoReclamo(EstadoReclamo oEstadoReclamo) throws ServiciosException;

	boolean actualizarEstadoReclamo(EstadoReclamo oEstadoReclamo) throws ServiciosException;

	boolean eliminarEstadoReclamo(Long idEstadoReclamo) throws ServiciosException;

	ArrayList<EstadoReclamo> listarEstadosReclamo() throws ServiciosException;

	ArrayList<EstadoReclamo> listarEstadosReclamo(String filtro) throws ServiciosException;

	ArrayList<EstadoReclamo> listarEstadosReclamoPorIdReclamo(Long idReclamo) throws ServiciosException;

	String getEstadoActual(Long idReclamo) throws ServiciosException;

	String getFechaEmision(Long idReclamo) throws ServiciosException;
	
	String getUltimaFecha(Long idReclamo) throws ServiciosException;

	String getDetalleDelEstudiante(Long idReclamo) throws ServiciosException;
}
