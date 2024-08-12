package com.cliente.services;

import java.util.ArrayList;
import javax.naming.InitialContext;

import com.cliente.dto.TutorDTO;
import com.servidor.beans.tutor.TutorBeanRemote;

public class ServiceTutor {

	private static TutorBeanRemote getService() {
		try {
			TutorBeanRemote tutorBean = (TutorBeanRemote) InitialContext
					.doLookup("ejb:/Proyecto-PInfra/TutorBean!com.servidor.beans.tutor.TutorBeanRemote");
			return tutorBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean crear(TutorDTO oTutorDTO) {
		try {
			var tutorBean = getService();
			return tutorBean.crear(oTutorDTO);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean actualizar(TutorDTO oTutorDTO) {
		try {
			var tutorBean = getService();
			return tutorBean.actualizar(oTutorDTO);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<TutorDTO> listar() {
		try {
			var tutorBean = getService();
			return tutorBean.listar();
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<TutorDTO> listarActivoFiltro(String filtro) {
		try {
			var tutorBean = getService();
			return tutorBean.listarActivoFiltro(filtro);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static TutorDTO getByDocumento(String documento) {
		try {
			var tutorBean = getService();
			return tutorBean.getByDocumento(documento);
		} catch (Exception e) {
			return null;
		}
	}
}
