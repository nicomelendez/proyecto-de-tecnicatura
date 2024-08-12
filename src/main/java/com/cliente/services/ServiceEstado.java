package com.cliente.services;

import java.util.ArrayList;

import javax.naming.InitialContext;

import com.servidor.beans.constancia.tipo.TipoBeanRemote;
import com.servidor.beans.estado.EstadoBeanRemote;
import com.servidor.entidades.Estado;
import com.servidor.entidades.Tipo;
import com.servidor.exception.ServiciosException;

public class ServiceEstado {
	private static EstadoBeanRemote getService() {
		try {
			EstadoBeanRemote constanciaBean = (EstadoBeanRemote) InitialContext
					.doLookup("ejb:/Proyecto-PInfra/EstadoBean!com.servidor.beans.estado.EstadoBeanRemote");
			return constanciaBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean crearEstado(Estado oEstado) {
		try {
			var estadoBean = getService();
			return estadoBean.crearEstado(oEstado);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean actualizarEstado(Estado oEstado) {
		try {
			EstadoBeanRemote estadoBean = getService();
			return estadoBean.actualizarEstado(oEstado);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean eliminarEstado(Long idEstado) {
		try {
			EstadoBeanRemote estadoBean = getService();
			return estadoBean.eliminarEstado(idEstado);
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean reactivarEstado(Long idEstado) {
		try {
			EstadoBeanRemote estadoBean = getService();
			return estadoBean.reactivarEstado(idEstado);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<Estado> listarEstados() {
		try {
			var estadoBean = getService();
			return estadoBean.listarEstados();
		} catch (Exception e) {
			return null;
		}
	}
	public static ArrayList<Estado> listarPorActivo(String filtro) {
		try {
			EstadoBeanRemote estadoBean = getService();
			return estadoBean.listarPorActivo(filtro);
		} catch (Exception e) {
			return null;
		}
	}
	

	public static Estado getById(Long idEstado) {
		try {
			var estadoBean = getService();
			return estadoBean.getById(idEstado);
		} catch (Exception e) {
			return null;
		}
	}
}
