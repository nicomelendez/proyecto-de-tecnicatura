package com.servidor.beans.constancia.tipo;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.servidor.entidades.Tipo;
import com.servidor.exception.ServiciosException;

@Remote
public interface TipoBeanRemote {
	boolean crearTipo(Tipo oTipo) throws ServiciosException;

	boolean actualizarTipo(Tipo oTipo) throws ServiciosException;

	boolean eliminarTipo(Long idTipo) throws ServiciosException;

	boolean reactivarTipo(Long idTipo) throws ServiciosException;

	Tipo getById(Long idTipo) throws ServiciosException;

	ArrayList<Tipo> listarTipos() throws ServiciosException;

	ArrayList<Tipo> listarTipos(String filtro) throws ServiciosException;

	ArrayList<Tipo> listarPorActivo(String filtro) throws ServiciosException;
}
