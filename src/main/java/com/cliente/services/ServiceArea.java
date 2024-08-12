package com.cliente.services;

import java.util.ArrayList;
import javax.naming.InitialContext;

import com.cliente.dto.AreaDTO;
import com.servidor.beans.area.AreaBeanRemote;

public class ServiceArea {

	private static AreaBeanRemote getService() {
		try {
			AreaBeanRemote areaBean = (AreaBeanRemote) InitialContext
					.doLookup("ejb:/Proyecto-PInfra/AreaBean!com.servidor.beans.area.AreaBeanRemote");
			return areaBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static AreaDTO crear(AreaDTO oAreaDTO) {
		try {
			var areaBean = getService();
			return areaBean.crear(oAreaDTO);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<AreaDTO> listar() {
		try {
			var areaBean = getService();
			return areaBean.listar();
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<AreaDTO> listarFiltro(String filtro) {
		try {
			var areaBean = getService();
			return areaBean.listarFiltro(filtro);
		} catch (Exception e) {
			return null;
		}
	}

}
