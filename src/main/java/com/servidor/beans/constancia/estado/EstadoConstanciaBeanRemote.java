package com.servidor.beans.constancia.estado;

import java.util.ArrayList;
import javax.ejb.Remote;

import com.cliente.dto.EstadoConstanciaDTO;
import com.servidor.entidades.EstadoConstancia;
import com.servidor.exception.ServiciosException;

@Remote
public interface EstadoConstanciaBeanRemote {

	boolean crearEstadoConstancia(EstadoConstancia oEstadoConstancia) throws ServiciosException;

	boolean actualizarEstadoConstancia(EstadoConstancia oEstadoConstancia) throws ServiciosException;

	boolean eliminarEstadoConstancia(Long idEstadoConstancia) throws ServiciosException;

	ArrayList<EstadoConstancia> listarEstadosConstancias() throws ServiciosException;

	ArrayList<EstadoConstancia> listarEstadosConstanciasPorId(Long idConstancia) throws ServiciosException;

	String getFechaEmision(Long idConstancia) throws ServiciosException;

	String getEstadoConstancia(Long idConstancia) throws ServiciosException;

	String getNotaDelEstudiante(Long idConstancia) throws ServiciosException;

	String getNotaDelAnalista(Long idConstancia) throws ServiciosException;

	String getNombreDelAnalista(Long idConstancia) throws ServiciosException;
}
