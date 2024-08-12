package com.cliente.services;

import java.util.ArrayList;

import javax.naming.InitialContext;

import com.servidor.beans.constancia.tipo.TipoBeanRemote;
import com.servidor.entidades.Tipo;

public class ServiceTipo {

	private static TipoBeanRemote getService() {
		try {
			TipoBeanRemote tipoBean = (TipoBeanRemote) InitialContext
					.doLookup("ejb:/Proyecto-PInfra/TipoBean!com.servidor.beans.constancia.tipo.TipoBeanRemote");
			return tipoBean;

		} catch (Exception e) {
			return null;
		}
	}

	public static boolean crearTipoConstancia(Tipo tipoConstancia) {
		try {
			TipoBeanRemote tipoBean = getService();
			return tipoBean.crearTipo(tipoConstancia);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean actualizarTipoConstancia(Tipo tipoConstancia) {
		try {
			TipoBeanRemote tipoBean = getService();
			return tipoBean.actualizarTipo(tipoConstancia);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean eliminarTipoConstancia(Long idTipoConstancia) {
		try {
			TipoBeanRemote tipoBean = getService();
			return tipoBean.eliminarTipo(idTipoConstancia);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean reactivarTipoConstancia(Long idTipoConstancia) {
		try {
			TipoBeanRemote tipoBean = getService();
			return tipoBean.reactivarTipo(idTipoConstancia);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<Tipo> listarTiposConstancias() {
		try {
			TipoBeanRemote tipoBean = getService();
			return tipoBean.listarTipos();
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<Tipo> listarTiposConstanciasFiltro(String filtro) {
		try {
			TipoBeanRemote tipoBean = getService();
			return tipoBean.listarTipos(filtro);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<Tipo> listarPorActivo(String filtro) {
		try {
			TipoBeanRemote tipoBean = getService();
			return tipoBean.listarPorActivo(filtro);
		} catch (Exception e) {
			return null;
		}
	}

	public static Tipo getById(Long idTipo) {
		try {
			TipoBeanRemote tipoBean = getService();
			return tipoBean.getById(idTipo);
		} catch (Exception e) {
			return null;
		}
	}
}
