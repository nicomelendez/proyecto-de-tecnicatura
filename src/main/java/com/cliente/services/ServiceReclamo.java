package com.cliente.services;

import java.util.ArrayList;

import javax.naming.InitialContext;

import com.cliente.dto.ReclamoDTO;
import com.servidor.beans.reclamo.ReclamoBeanRemote;
import com.servidor.beans.rol.RolBeanRemote;
import com.servidor.entidades.Reclamo;

public class ServiceReclamo {
	private static ReclamoBeanRemote getService() {
		try {
			ReclamoBeanRemote reclamoBean = (ReclamoBeanRemote) InitialContext
					.doLookup("ejb:/Proyecto-PInfra/ReclamoBean!com.servidor.beans.reclamo.ReclamoBeanRemote");
			return reclamoBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static Reclamo crear(Reclamo oReclamo) {
		try {
			var reclamoBean = getService();
			return reclamoBean.crearReclamo(oReclamo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static boolean actualizar(Reclamo oReclamo) {
		try {
			var reclamoBean = getService();
			return reclamoBean.actualizarReclamo(oReclamo);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean eliminar(Long idRol) {
		try {
			var reclamoBean = getService();
			return reclamoBean.eliminarReclamo(idRol);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<ReclamoDTO> listar() {
		try {
			var reclamoBean = getService();
			return reclamoBean.listarReclamos();
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<ReclamoDTO> listarFiltro(String filtro) {
		try {
			var reclamoBean = getService();
			return reclamoBean.listarReclamoFiltro(filtro);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Reclamo getById(Long id) {
		try {
			var reclamoBean = getService();
			return reclamoBean.getById(id);
		} catch (Exception e) {
			return null;
		}
	}
}
