package com.cliente.services;

import java.util.ArrayList;
import javax.naming.InitialContext;

import com.cliente.dto.AnalistaDTO;
import com.cliente.dto.EstudianteDTO;
import com.cliente.dto.TutorDTO;
import com.cliente.dto.UsuarioDTO;
import com.servidor.beans.usuario.UsuarioBeanRemote;
import com.servidor.utils.Respuesta;

public class ServiceUsuario {

	private static UsuarioBeanRemote getService() {
		try {
			UsuarioBeanRemote usuarioBean = (UsuarioBeanRemote) InitialContext
					.doLookup("ejb:/Proyecto-PInfra/UsuarioBean!com.servidor.beans.usuario.UsuarioBeanRemote");
			return usuarioBean;
		} catch (Exception e) {
			return null;
		}
	}

	public static Respuesta login(String nombreUsuario, String clave) {
		try {
			var usuarioBean = getService();
			return usuarioBean.login(nombreUsuario, clave);
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean crear(UsuarioDTO oUsuarioDTO, Object oTipoUsuario) {
		try {
			var oUsuarioCreado = crearUsuario(oUsuarioDTO);

			if (oTipoUsuario instanceof EstudianteDTO) {

				var oEstudiate = (EstudianteDTO) oTipoUsuario;
				oEstudiate.setoUsuario(oUsuarioCreado);
				
				var oEstudianteCreado = ServiceEstudiante.crear(oEstudiate);
				return oEstudianteCreado;
			}

			if (oTipoUsuario instanceof AnalistaDTO) {
				var oAnalista = (AnalistaDTO) oTipoUsuario;
				oAnalista.setoUsuario(oUsuarioCreado);
				var oAnalistaCreado = ServiceAnalista.crear(oAnalista);
				return oAnalistaCreado;
			}

			if (oTipoUsuario instanceof TutorDTO) {
				var oTutor = (TutorDTO) oTipoUsuario;
				oTutor.setoUsuario(oUsuarioCreado);

				var oTutorCreado = ServiceTutor.crear(oTutor);
				return oTutorCreado;
			}

			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean actualizar(UsuarioDTO oUsuario, Object oTipoUsuario) {
		try {
			var usuarioEditado = ServiceUsuario.actualizarUsuario(oUsuario);

			if (oTipoUsuario instanceof EstudianteDTO) {

				var oEstudiate = (EstudianteDTO) oTipoUsuario;
				oEstudiate.setoUsuario(usuarioEditado);

				var oEstudianteCreado = ServiceEstudiante.actualizar(oEstudiate);
				return oEstudianteCreado;
			}

			if (oTipoUsuario instanceof AnalistaDTO) {
				var oAnalista = (AnalistaDTO) oTipoUsuario;
				oAnalista.setoUsuario(usuarioEditado);

				var oAnalistaCreado = ServiceAnalista.actualizar(oAnalista);
				return oAnalistaCreado;
			}

			if (oTipoUsuario instanceof TutorDTO) {
				var oTutor = (TutorDTO) oTipoUsuario;
				oTutor.setoUsuario(usuarioEditado);

				var oTutorCreado = ServiceTutor.actualizar(oTutor);
				return oTutorCreado;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	private static UsuarioDTO crearUsuario(UsuarioDTO oUsuarioDTO) {
		try {
			var usuarioBean = getService();
			return usuarioBean.crear(oUsuarioDTO);
		} catch (Exception e) {
			return null;
		}
	}

	public static UsuarioDTO actualizarUsuario(UsuarioDTO oUsuarioDTO) {
		try {
			var usuarioBean = getService();
			return usuarioBean.actualizar(oUsuarioDTO);
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean eliminar(Long idUsuario) {
		try {
			var usuarioBean = getService();
			return usuarioBean.eliminar(idUsuario);
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<UsuarioDTO> listar() {
		try {
			var usuarioBean = getService();
			return usuarioBean.listar();
		} catch (Exception e) {
			return null;
		}
	}

	public static UsuarioDTO getById(Long idUsuario) {
		try {
			var usuarioBean = getService();
			return usuarioBean.getById(idUsuario);
		} catch (Exception e) {
			return null;
		}
	}

	public static UsuarioDTO getByMailPersonal(String mailPersonal) {
		try {
			var usuarioBean = getService();
			return usuarioBean.getByMailPersonal(mailPersonal);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<UsuarioDTO> listarPorNombreApellido(String filtro1, String filtro2) {
		try {
			var usuarioBean = getService();
			return usuarioBean.listarPorNombreApellido(filtro1, filtro2);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<UsuarioDTO> listarFiltroRol(Long idRol) {
		try {
			var usuarioBean = getService();
			return usuarioBean.listarFiltroRol(idRol);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<UsuarioDTO> listarSinConfirmar(String filtro) {
		try {
			var usuarioBean = getService();
			return usuarioBean.listarFiltroConfirmar(filtro);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<UsuarioDTO> listarFiltroDocumento(String filtro) {
		try {
			var usuarioBean = getService();
			return usuarioBean.listarFiltroDocumento(filtro);
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean comprobarNombreUsuario(String nombreUsuario) {
		try {
			var usuarioBean = getService();
			return usuarioBean.comprobarNombreUsuario(nombreUsuario);
		} catch (Exception e) {
			return false;
		}
	}

}
