package com.servidor.beans.usuario;

import java.util.ArrayList;
import javax.ejb.Remote;

import com.cliente.dto.UsuarioDTO;
import com.servidor.entidades.Usuario;
import com.servidor.exception.ServiciosException;
import com.servidor.utils.Respuesta;

@Remote
public interface UsuarioBeanRemote {

	Respuesta login(String nickname, String clave) throws ServiciosException;

	UsuarioDTO crear(UsuarioDTO oUsuario) throws ServiciosException;

	UsuarioDTO actualizar(UsuarioDTO oUsuario) throws ServiciosException;

	boolean eliminar(Long idUsuario) throws ServiciosException;

	ArrayList<UsuarioDTO> listar() throws ServiciosException;

	UsuarioDTO getById(Long idUsuario) throws ServiciosException;

	UsuarioDTO getByMailPersonal(String mailPersonal) throws ServiciosException;

	ArrayList<UsuarioDTO> listarFiltroRolById(Long idRol) throws ServiciosException;

	ArrayList<UsuarioDTO> listarFiltroRol(Long idRol) throws ServiciosException;

	ArrayList<UsuarioDTO> listarFiltroDocumento(String filtro) throws ServiciosException;

	ArrayList<UsuarioDTO> listarPorNombreApellido(String filtro1, String filtro2) throws ServiciosException;

	ArrayList<UsuarioDTO> listarFiltroConfirmar(String filtro) throws ServiciosException;

	boolean comprobarNombreUsuario(String nombreUsuario) throws ServiciosException;
}
