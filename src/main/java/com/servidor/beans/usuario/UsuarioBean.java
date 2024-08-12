package com.servidor.beans.usuario;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.cliente.dto.UsuarioDTO;
import com.servidor.dao.UsuarioDAO;
import com.servidor.exception.ServiciosException;
import com.servidor.utils.Respuesta;

@Stateless
public class UsuarioBean implements UsuarioBeanRemote {

	@EJB
	private UsuarioDAO oUsuarioDAO;
	
	@Override
	public Respuesta login(String nombreUsuario, String clave) throws ServiciosException {
		return oUsuarioDAO.login(nombreUsuario, clave);
	}

	@Override
	public UsuarioDTO crear(UsuarioDTO oUsuarioDTO) throws ServiciosException {
		return oUsuarioDAO.crear(oUsuarioDTO);
	}

	@Override
	public UsuarioDTO actualizar(UsuarioDTO oUsuarioDTO) throws ServiciosException {
		return oUsuarioDAO.actualizar(oUsuarioDTO);
	}

	@Override
	public boolean eliminar(Long idUsuario) throws ServiciosException {
		return oUsuarioDAO.eliminar(idUsuario);
	}

	@Override
	public ArrayList<UsuarioDTO> listar() throws ServiciosException {
		return oUsuarioDAO.listar();
	}

	@Override
	public UsuarioDTO getById(Long idUsuario) throws ServiciosException {
		return oUsuarioDAO.getById(idUsuario);
	}

	@Override
	public ArrayList<UsuarioDTO> listarFiltroRol(Long idRol) throws ServiciosException {
		return oUsuarioDAO.listarFiltroRol(idRol);
	}

	@Override
	public ArrayList<UsuarioDTO> listarPorNombreApellido(String filtro1, String filtro2) throws ServiciosException {
		return oUsuarioDAO.listarPorNombreApellido(filtro1, filtro2);
	}

	@Override
	public ArrayList<UsuarioDTO> listarFiltroConfirmar(String filtro) throws ServiciosException {
		return oUsuarioDAO.listarFiltroConfirmar(filtro);
	}

	@Override
	public boolean comprobarNombreUsuario(String nombreUsuario) throws ServiciosException {
		return oUsuarioDAO.comprobarNombreUsuario(nombreUsuario);
	}

	@Override
	public ArrayList<UsuarioDTO> listarFiltroDocumento(String filtro) throws ServiciosException {
		return oUsuarioDAO.listarFiltroDocumento(filtro);
	}

	@Override
	public UsuarioDTO getByMailPersonal(String mailPersonal) throws ServiciosException {
		return oUsuarioDAO.getByMailPersonal(mailPersonal);
	}

	@Override
	public ArrayList<UsuarioDTO> listarFiltroRolById(Long idRol) throws ServiciosException {
		return oUsuarioDAO.listarFiltroRolById(idRol);
	}

}
