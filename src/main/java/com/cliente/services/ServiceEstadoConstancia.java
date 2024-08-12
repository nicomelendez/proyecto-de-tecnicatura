package com.cliente.services;

import java.util.ArrayList;

import javax.naming.InitialContext;

import com.cliente.dto.EstadoConstanciaDTO;
import com.servidor.beans.constancia.estado.EstadoConstanciaBeanRemote;
import com.servidor.entidades.EstadoConstancia;

public class ServiceEstadoConstancia {
	private static EstadoConstanciaBeanRemote getService() {
		try {
			EstadoConstanciaBeanRemote estadoConstanciaBean = (EstadoConstanciaBeanRemote) InitialContext.doLookup(
					"ejb:/Proyecto-PInfra/EstadoConstanciaBean!com.servidor.beans.constancia.estado.EstadoConstanciaBeanRemote");
			return estadoConstanciaBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean crearEstadoConstancia(EstadoConstancia oEstadoConstancia) {
		try {
			var estadoConstanciaBean = getService();
			return estadoConstanciaBean.crearEstadoConstancia(oEstadoConstancia);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean actualizarEstadoConstancia(EstadoConstancia oEstadoConstancia) {
		try {
			var estadoConstanciaBean = getService();
			return estadoConstanciaBean.actualizarEstadoConstancia(oEstadoConstancia);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<EstadoConstancia> listarEstadosConstanciasPorId(Long idConstancia) {
		try {
			var estadoConstanciaBean = getService();
			return estadoConstanciaBean.listarEstadosConstanciasPorId(idConstancia);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getFechaEmision(Long idConstancia) {
		try {
			var estadoConstanciaBean = getService();
			return estadoConstanciaBean.getFechaEmision(idConstancia);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getEstadoConstancia(Long idConstancia) {
		try {
			var estadoConstanciaBean = getService();
			return estadoConstanciaBean.getEstadoConstancia(idConstancia);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getNotaDelEstudiante(Long idConstancia) {
		try {
			var estadoConstanciaBean = getService();
			return estadoConstanciaBean.getNotaDelEstudiante(idConstancia);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getNotaDelAnalista(Long idConstancia) {
		try {
			var estadoConstanciaBean = getService();
			return estadoConstanciaBean.getNotaDelAnalista(idConstancia);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getNombreDelAnalista(Long idConstancia) {
		try {
			var estadoConstanciaBean = getService();
			return estadoConstanciaBean.getNombreDelAnalista(idConstancia);
		} catch (Exception e) {
			return null;
		}
	}
}
