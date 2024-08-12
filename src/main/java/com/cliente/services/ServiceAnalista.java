package com.cliente.services;

import java.util.ArrayList;
import javax.naming.InitialContext;

import com.cliente.dto.AnalistaDTO;
import com.servidor.beans.analista.AnalistaBeanRemote;
import com.servidor.entidades.Analista;

public class ServiceAnalista {

	private static AnalistaBeanRemote getService() {
		try {
			AnalistaBeanRemote analistaBean = (AnalistaBeanRemote) InitialContext
					.doLookup("ejb:/Proyecto-PInfra/AnalistaBean!com.servidor.beans.analista.AnalistaBeanRemote");
			return analistaBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean crear(AnalistaDTO oAnalistaDTO) {
		try {
			var analistaBean = getService();
			return analistaBean.crear(oAnalistaDTO);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean actualizar(AnalistaDTO oAnalistaDTO) {
		try {
			var analistaBean = getService();
			return analistaBean.actualizar(oAnalistaDTO);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<AnalistaDTO> listar() {
		try {
			var analistaBean = getService();
			return analistaBean.listar();
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<AnalistaDTO> listarActivoFiltro(String filtro) {
		try {
			var analistaBean = getService();
			return analistaBean.listarActivoFiltro(filtro);
		} catch (Exception e) {
			return null;
		}
	}

	public static AnalistaDTO getByDocumento(String documento) {
		try {
			var analistaBean = getService();
			return analistaBean.getByDocumento(documento);
		} catch (Exception e) {
			return null;
		}
	}

	public static Analista getById(long id) {
		try {
			var analistaBean = getService();
			return analistaBean.getId(id);
		} catch (Exception e) {
			return null;
		}
	}
}
