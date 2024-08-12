package com.cliente.services;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.naming.InitialContext;

import com.servidor.beans.constancia.ConstanciaBeanRemote;
import com.servidor.entidades.Constancia;

public class ServiceConstancia {

	private static ConstanciaBeanRemote getService() {
		try {
			ConstanciaBeanRemote constanciaBean = (ConstanciaBeanRemote) InitialContext
					.doLookup("ejb:/Proyecto-PInfra/ConstanciaBean!com.servidor.beans.constancia.ConstanciaBeanRemote");
			return constanciaBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static Constancia crearConstancia(Constancia oConstancia) {
		try {
			var constanciaBean = getService();
			return constanciaBean.crearConstancia(oConstancia);
		} catch (Exception e) {
			System.out.println("ERROR--------------------------------------------");
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static boolean actualizarConstancia(Constancia oConstancia) {
		try {
			var constanciaBean = getService();
			return constanciaBean.actualizarConstancia(oConstancia);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean eliminarConstancia(Long id) {
		try {
			var constanciaBean = getService();
			return constanciaBean.eliminarConstancia(id);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<Constancia> listarConstancias() {
		try {
			var constanciaBean = getService();
			return constanciaBean.listarConstancias();
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<Constancia> listarConstanciasConEstado() {
		try {
			var constanciaBean = getService();
			return constanciaBean.listarConstanciasConEstado();
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<Constancia> listarConstanciasEstudiante(Long idEstudiante) {
		try {
			var constanciaBean = getService();
			return constanciaBean.listarConstanciasEstudiante(idEstudiante);
		} catch (Exception e) {
			return null;
		}
	}

	public static Constancia getById(long id) {
		try {
			var constanciaBean = getService();
			return constanciaBean.getById(id);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<Constancia> listarConstanciasEstudiantePorMesYAnio(BigDecimal documento, int mes,
			int anio) {
		try {
			var constanciaBean = getService();
			return constanciaBean.listarConstanciasEstudiantePorMesYAnio(documento, mes, anio);
		} catch (Exception e) {
			return null;
		}
	}
}
