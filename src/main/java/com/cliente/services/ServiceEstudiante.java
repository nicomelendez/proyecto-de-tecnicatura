package com.cliente.services;

import java.util.ArrayList;
import javax.naming.InitialContext;
import com.cliente.dto.EstudianteDTO;
import com.servidor.beans.estudiante.EstudianteBeanRemote;

public class ServiceEstudiante {

	private static EstudianteBeanRemote getService() {
		try {
			EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext
					.doLookup("ejb:/Proyecto-PInfra/EstudianteBean!com.servidor.beans.estudiante.EstudianteBeanRemote");
			return estudianteBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean crear(EstudianteDTO oEstudianteDTO) {
		try {
			var estudianteBean = getService();
			return estudianteBean.crear(oEstudianteDTO);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean actualizar(EstudianteDTO oEstudianteDTO) {
		try {
			var estudianteBean = getService();
			return estudianteBean.actualizar(oEstudianteDTO);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<EstudianteDTO> listar() {
		try {
			var estudianteBean = getService();
			return estudianteBean.listar();
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<EstudianteDTO> listarActivoFiltro(String filtro) {
		try {
			var estudianteBean = getService();
			return estudianteBean.listarActivoFiltro(filtro);
		} catch (Exception e) {
			return null;
		}
	}

	public static EstudianteDTO getByDocumento(String documento) {
		try {
			var estudianteBean = getService();
			return estudianteBean.getByDocumento(documento);
		} catch (Exception e) {
			return null;
		}
	}
}
