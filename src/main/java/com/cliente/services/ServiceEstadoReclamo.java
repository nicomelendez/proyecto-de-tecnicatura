package com.cliente.services;

import java.util.ArrayList;

import javax.naming.InitialContext;
import com.servidor.beans.reclamo_estado.EstadoReclamoBeanRemote;
import com.servidor.entidades.EstadoReclamo;

public class ServiceEstadoReclamo {
	private static EstadoReclamoBeanRemote getService() {
		try {
			EstadoReclamoBeanRemote estdoReclamoBean = (EstadoReclamoBeanRemote) InitialContext.doLookup(
					"ejb:/Proyecto-PInfra/EstadoReclamoBean!com.servidor.beans.reclamo_estado.EstadoReclamoBeanRemote");
			return estdoReclamoBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean crear(EstadoReclamo oEstadoReclamo) {
		try {
			var estdoReclamoBean = getService();
			return estdoReclamoBean.crearEstadoReclamo(oEstadoReclamo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean actualizar(EstadoReclamo oEstadoReclamo) {
		try {
			var estdoReclamoBean = getService();
			return estdoReclamoBean.actualizarEstadoReclamo(oEstadoReclamo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean eliminar(Long idEstdoReclamo) {
		try {
			var estdoReclamoBean = getService();
			return estdoReclamoBean.eliminarEstadoReclamo(idEstdoReclamo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static ArrayList<EstadoReclamo> listar() {
		try {
			var estdoReclamoBean = getService();
			return estdoReclamoBean.listarEstadosReclamo();
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<EstadoReclamo> listarFiltro(String filtro) {
		try {
			var estdoReclamoBean = getService();
			return estdoReclamoBean.listarEstadosReclamo(filtro);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<EstadoReclamo> listarEstadosReclamoPorIdReclamo(Long idReclamo) {
		try {
			var estdoReclamoBean = getService();
			return estdoReclamoBean.listarEstadosReclamoPorIdReclamo(idReclamo);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getEstadoActual(Long idReclamo) {
		try {
			var estdoReclamoBean = getService();
			return estdoReclamoBean.getEstadoActual(idReclamo);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getFechaEmision(Long idReclamo) {
		try {
			var estdoReclamoBean = getService();
			return estdoReclamoBean.getFechaEmision(idReclamo);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getUltimaFecha(Long idReclamo) {
		try {
			var estdoReclamoBean = getService();
			return estdoReclamoBean.getUltimaFecha(idReclamo);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getDetalleDelEstudiante(Long idReclamo) {
		try {
			var estdoReclamoBean = getService();
			return estdoReclamoBean.getDetalleDelEstudiante(idReclamo);
		} catch (Exception e) {
			return null;
		}
	}
}
