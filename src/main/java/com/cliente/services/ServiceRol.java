package com.cliente.services;

import java.util.ArrayList;
import javax.naming.InitialContext;

import com.cliente.dto.RolDTO;
import com.servidor.beans.rol.RolBeanRemote;

public class ServiceRol {

	private static RolBeanRemote getService() {
		try {
			RolBeanRemote rolBean = (RolBeanRemote) InitialContext
					.doLookup("ejb:/Proyecto-PInfra/RolBean!com.servidor.beans.rol.RolBeanRemote");
			return rolBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static RolDTO crear(RolDTO oRolDTO) {
		try {
			var rolBean = getService();
			return rolBean.crear(oRolDTO);
		} catch (Exception e) {
			System.out.println("-----------E");
			System.out.println(e.getMessage());
			System.out.println("-----------");
			return null;
		}
	}

	public static RolDTO actualizar(RolDTO oRolDTO) {
		try {
			var rolBean = getService();
			return rolBean.actualizar(oRolDTO);
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean eliminar(Long idRol) {
		try {
			var rolBean = getService();
			return rolBean.eliminar(idRol);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<RolDTO> listar() {
		try {
			var rolBean = getService();
			return rolBean.listar();
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<RolDTO> listarFiltro(String filtro) {
		try {
			var rolBean = getService();
			return rolBean.listarFiltro(filtro);
		} catch (Exception e) {
			return null;
		}
	}

}
