package com.cliente.services;

import java.util.ArrayList;
import javax.naming.InitialContext;

import com.cliente.dto.ItrDTO;
import com.servidor.beans.itr.ItrBeanRemote;

public class ServiceItr {

	private static ItrBeanRemote getService() {
		try {
			ItrBeanRemote itrBean = (ItrBeanRemote) InitialContext
					.doLookup("ejb:/Proyecto-PInfra/ItrBean!com.servidor.beans.itr.ItrBeanRemote");
			return itrBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static ItrDTO crear(ItrDTO oItrDTO) {
		try {
			var itrBean = getService();
			return itrBean.crear(oItrDTO);
		} catch (Exception e) {
			return null;
		}
	}

	public static ItrDTO actualizar(ItrDTO oItrDTO) {
		try {
			var itrBean = getService();
			return itrBean.actualizar(oItrDTO);
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean eliminar(Long id) {
		try {
			var itrBean = getService();
			return itrBean.eliminar(id);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<ItrDTO> listar() {
		try {
			var itrBean = getService();
			return itrBean.listar();
		} catch (Exception e) {
			return null;
		}
	}

	public static ItrDTO getById(Long idItr) {
		try {
			var itrBean = getService();
			return itrBean.getById(idItr);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<ItrDTO> listarFiltro(String filtro) {
		try {
			var itrBean = getService();
			return itrBean.listarFiltro(filtro);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<ItrDTO> listarActivoFiltro(String filtro) {
		try {
			var itrBean = getService();
			return itrBean.listarActivoFiltro(filtro);
		} catch (Exception e) {
			return null;
		}
	}

}
