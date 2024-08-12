package com.cliente.services;

import java.util.ArrayList;
import javax.naming.InitialContext;

import com.cliente.dto.GeneroDTO;
import com.servidor.beans.genero.GeneroBeanRemote;

public class ServiceGenero {

	private static GeneroBeanRemote getService() {
		try {
			GeneroBeanRemote generoBean = (GeneroBeanRemote) InitialContext
					.doLookup("ejb:/Proyecto-PInfra/GeneroBean!com.servidor.beans.genero.GeneroBeanRemote");
			return generoBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static GeneroDTO crear(GeneroDTO oGeneroDTO) {
		try {
			var generoBean = getService();
			return generoBean.crear(oGeneroDTO);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<GeneroDTO> listar() {
		try {
			var generoBean = getService();
			return generoBean.listar();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ArrayList<GeneroDTO> listarFiltro(String filtro){
		try {
			var generoBean = getService();
			return generoBean.listarFiltro(filtro);			
		} catch (Exception e) {
			return null;
		}
	}

}
