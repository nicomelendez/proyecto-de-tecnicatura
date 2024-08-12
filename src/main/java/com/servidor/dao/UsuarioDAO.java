package com.servidor.dao;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.cliente.dto.UsuarioDTO;
import com.cliente.services.ServiceAnalista;
import com.cliente.services.ServiceEstudiante;
import com.cliente.services.ServiceTutor;
import com.servidor.entidades.Estudiante;
import com.servidor.entidades.Usuario;
import com.servidor.utils.Respuesta;

@Stateless
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public Respuesta login(String nombreUsuario, String clave) {
		try {
			TypedQuery<Usuario> query = entityManager.createNamedQuery("Usuario.login", Usuario.class)
					.setParameter("nombreUsuario", nombreUsuario).setParameter("clave", clave);

			ArrayList<Usuario> lista = (ArrayList<Usuario>) query.getResultList();

			if (!lista.isEmpty()) {
				Usuario oUsuario = lista.get(0);

				if (oUsuario.getConfirmado().equals("S") && !oUsuario.getActivo().equals("N")) {
					Long idRol = null;
					if (oUsuario.getRol().getDescripcion().equals("Estudiante")) {
						idRol = ServiceEstudiante.getByDocumento(oUsuario.getDocumento().toString()).getIdEstudiante();
					}
					if (oUsuario.getRol().getDescripcion().equals("Analista")) {
						idRol = ServiceAnalista.getByDocumento(oUsuario.getDocumento().toString()).getIdAnalista();
					}
					if (oUsuario.getRol().getDescripcion().equals("Tutor")
							|| oUsuario.getRol().getDescripcion().equals("Encargado")) {
						idRol = ServiceTutor.getByDocumento(oUsuario.getDocumento().toString()).getIdTutor();
					}
					UsuarioDTO oUsuarioDatos = new UsuarioDTO(oUsuario, idRol, "login");
					return new Respuesta("success", "Inicio de sesión exitoso", oUsuarioDatos);
				} else {
					return new Respuesta("error", "Tu cuenta aún no ha sido confirmada.", null);
				}
			} else {
				return new Respuesta("error", "Nombre de usuario o contraseña incorrecto.", null);
			}
		} catch (Exception e) {
			return new Respuesta("error", "Ocurrió un error al intentar iniciar sesión.", null);
		}
	}

	public UsuarioDTO crear(UsuarioDTO oUsuarioDTO) {
		try {
			Usuario oUsuario = new Usuario(oUsuarioDTO);
			entityManager.persist(oUsuario);
			entityManager.flush();

			return new UsuarioDTO(oUsuario);
		} catch (Exception e) {
			return null;
		}
	}

	public UsuarioDTO actualizar(UsuarioDTO oUsuarioDTO) {
		try {
			Usuario oUsuario = new Usuario(oUsuarioDTO);
			entityManager.merge(oUsuario);
			entityManager.flush();
			return new UsuarioDTO(oUsuario);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean eliminar(Long idUsuario) {
		try {
			Usuario oUsuario = entityManager.find(Usuario.class, idUsuario);
			oUsuario.setActivo("N");
			entityManager.flush();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public ArrayList<UsuarioDTO> listar() {
		ArrayList<UsuarioDTO> listaDeUsuarios = new ArrayList<UsuarioDTO>();
		try {
			TypedQuery<Usuario> query = entityManager.createNamedQuery("Usuario.findAll", Usuario.class);

			for (Usuario oUsuario : query.getResultList()) {
				listaDeUsuarios.add(new UsuarioDTO(oUsuario));
			}

			return listaDeUsuarios;
		} catch (Exception e) {
			return null;
		}
	}

	public UsuarioDTO getById(Long idUsuario) {
		try {
			TypedQuery<Usuario> query = entityManager.createNamedQuery("Usuario.findById", Usuario.class)
					.setParameter("idUsuario", idUsuario);

			Usuario oUsuario = query.getSingleResult();

			return new UsuarioDTO(oUsuario);
		} catch (Exception e) {
			return null;
		}
	}

	public ArrayList<UsuarioDTO> listarFiltroRol(Long idRol) {
		ArrayList<UsuarioDTO> listaDeUsuarios = new ArrayList<UsuarioDTO>();
		try {
			TypedQuery<Usuario> query = entityManager.createNamedQuery("Usuario.findByRol", Usuario.class)
					.setParameter("idRol", idRol);

			for (Usuario oUsuario : query.getResultList()) {
				listaDeUsuarios.add(new UsuarioDTO(oUsuario));
			}

			return listaDeUsuarios;
		} catch (Exception e) {
			return null;
		}
	}

	public ArrayList<UsuarioDTO> listarFiltroDocumento(String documento) {
		ArrayList<UsuarioDTO> listaDeUsuarios = new ArrayList<UsuarioDTO>();
		try {
			TypedQuery<Usuario> query = entityManager.createNamedQuery("Usuario.findByDocumento", Usuario.class)
					.setParameter("documento", new BigDecimal(documento));

			for (Usuario oUsuario : query.getResultList()) {
				listaDeUsuarios.add(new UsuarioDTO(oUsuario));
			}

			return listaDeUsuarios;
		} catch (Exception e) {
			return null;
		}
	}

	public ArrayList<UsuarioDTO> listarPorNombreApellido(String filtro1, String filtro2) {
		ArrayList<UsuarioDTO> listaDeUsuarios = new ArrayList<UsuarioDTO>();
		try {
			TypedQuery<Usuario> query = entityManager.createNamedQuery("Usuario.findByNombres", Usuario.class)
					.setParameter("filtro1", "%" + filtro1 + "%").setParameter("filtro2", "%" + filtro2 + "%");

			for (Usuario oUsuario : query.getResultList()) {
				listaDeUsuarios.add(new UsuarioDTO(oUsuario));
			}

			return listaDeUsuarios;
		} catch (Exception e) {
			return null;
		}
	}

	public ArrayList<UsuarioDTO> listarFiltroConfirmar(String filtro) {
		ArrayList<UsuarioDTO> listaDeUsuarios = new ArrayList<UsuarioDTO>();
		try {
			TypedQuery<Usuario> query = entityManager.createNamedQuery("Usuario.findByActivo", Usuario.class)
					.setParameter("filtro", filtro).setParameter("activo", "S");

			for (Usuario oUsuario : query.getResultList()) {
				listaDeUsuarios.add(new UsuarioDTO(oUsuario));
			}

			return listaDeUsuarios;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean comprobarNombreUsuario(String nombreUsuario) {
		try {
			TypedQuery<Usuario> query = entityManager.createNamedQuery("Usuario.findByNombreUsuario", Usuario.class)
					.setParameter("nombreUsuario", nombreUsuario);

			if (query.getResultList().size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public UsuarioDTO getByMailPersonal(String mailPersonal) {
		try {
			TypedQuery<Usuario> query = entityManager.createNamedQuery("Usuario.findByMailPersonal", Usuario.class)
					.setParameter("mailPersonal", mailPersonal);

			Usuario oUsuario = query.getSingleResult();

			return new UsuarioDTO(oUsuario);
		} catch (Exception e) {
			return null;
		}
	}

	public ArrayList<UsuarioDTO> listarFiltroRolById(Long idRol) {
		ArrayList<UsuarioDTO> listaDeUsuarios = new ArrayList<UsuarioDTO>();
		try {
			TypedQuery<Usuario> query = entityManager.createNamedQuery("Usuario.findByIdRol", Usuario.class)
					.setParameter("idRol", idRol);

			for (Usuario oUsuario : query.getResultList()) {
				listaDeUsuarios.add(new UsuarioDTO(oUsuario));
			}

			return listaDeUsuarios;
		} catch (Exception e) {
			return null;
		}
	}
}
