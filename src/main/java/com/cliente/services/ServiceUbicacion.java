package com.cliente.services;

import java.util.ArrayList;
import javax.naming.InitialContext;

import com.cliente.dto.DepartamentoDTO;
import com.cliente.dto.LocalidadDTO;
import com.servidor.beans.departamento.DepartamentoBeanRemote;
import com.servidor.beans.localidad.LocalidadBeanRemote;

public class ServiceUbicacion {

	private static DepartamentoBeanRemote getServiceDepartamento() {
		try {
			DepartamentoBeanRemote departamentoBean = (DepartamentoBeanRemote) InitialContext.doLookup(
					"ejb:/Proyecto-PInfra/DepartamentoBean!com.servidor.beans.departamento.DepartamentoBeanRemote");
			return departamentoBean;
		} catch (Exception e) {
			return null;
		}

	}

	private static LocalidadBeanRemote getServiceLocalidad() {
		try {
			LocalidadBeanRemote departamentoLocalidad = (LocalidadBeanRemote) InitialContext
					.doLookup("ejb:/Proyecto-PInfra/LocalidadBean!com.servidor.beans.localidad.LocalidadBeanRemote");
			return departamentoLocalidad;
		} catch (Exception e) {
			return null;
		}
	}

	public static DepartamentoDTO crearDepartamento(DepartamentoDTO oDepartamentoDTO) {
		try {
			var departamentoBean = getServiceDepartamento();
			return departamentoBean.crear(oDepartamentoDTO);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<DepartamentoDTO> listarDepartamentos() {
		try {
			var departamentoBean = getServiceDepartamento();
			return departamentoBean.listar();
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<DepartamentoDTO> listarDepartamentosFiltro(String filtro) {
		try {
			var departamentoBean = getServiceDepartamento();
			return departamentoBean.listarFiltro(filtro);
		} catch (Exception e) {
			return null;
		}
	}

	public static LocalidadDTO crearLocalidad(LocalidadDTO oLocalidadDTO) {
		try {
			var localidadBean = getServiceLocalidad();
			return localidadBean.crear(oLocalidadDTO);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<LocalidadDTO> listarLocalidades() {
		try {
			var localidadBean = getServiceLocalidad();
			return localidadBean.listar();
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<LocalidadDTO> listarLocalidadesFiltro(String filtro) {
		try {
			var localidadBean = getServiceLocalidad();
			return localidadBean.listarFiltro(filtro);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ArrayList<LocalidadDTO> listarLocalidadesPorDepartamento(String nombreDepartamento) {
		try {
			var localidadBean = getServiceLocalidad();
			return localidadBean.listarLocalidadesPorDepartamento(nombreDepartamento);
		} catch (Exception e) {
			return null;
		}
	}
}
