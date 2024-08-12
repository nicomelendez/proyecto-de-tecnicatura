package com.servidor.beans.constancia;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.ejb.Remote;

import com.servidor.entidades.Constancia;
import com.servidor.exception.ServiciosException;

@Remote
public interface ConstanciaBeanRemote {

	Constancia crearConstancia(Constancia oConstancia) throws ServiciosException;

	boolean actualizarConstancia(Constancia oConstancia) throws ServiciosException;

	boolean eliminarConstancia(Long idConstancia) throws ServiciosException;

	ArrayList<Constancia> listarConstancias() throws ServiciosException;

	ArrayList<Constancia> listarConstanciasFiltro(String filtro) throws ServiciosException;

	ArrayList<Constancia> listarConstanciasConEstado() throws ServiciosException;

	ArrayList<Constancia> listarConstanciasEstudiante(long idEstudiante) throws ServiciosException;

	Constancia getById(long id) throws ServiciosException;

	public ArrayList<Constancia> listarConstanciasEstudiantePorMesYAnio(BigDecimal documento, int mes, int anio)
			throws ServiciosException;
}
