package com.cliente.servlets.usuario;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.contexto.Fabrica;
import com.cliente.contexto.validaciones.ValidacionComboBoxes;
import com.cliente.contexto.validaciones.ValidacionUsuario;
import com.cliente.dto.AnalistaDTO;
import com.cliente.dto.DepartamentoDTO;
import com.cliente.dto.EstudianteDTO;
import com.cliente.dto.GeneroDTO;
import com.cliente.dto.ItrDTO;
import com.cliente.dto.LocalidadDTO;
import com.cliente.dto.RolDTO;
import com.cliente.dto.TutorDTO;
import com.cliente.dto.UsuarioDTO;
import com.cliente.services.ServiceArea;
import com.cliente.services.ServiceGenero;
import com.cliente.services.ServiceItr;
import com.cliente.services.ServiceRol;
import com.cliente.services.ServiceUbicacion;
import com.cliente.services.ServiceUsuario;

@WebServlet(name = "ServletCrearUsuario", urlPatterns = "/SvCrearUsuario")
public class SvCrearUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Fabrica.limpiarMensajesDeError(request.getSession());

		String nombres = request.getParameter("nombres");
		String apellidos = request.getParameter("apellidos");
		String cedula = request.getParameter("cedula");
		String telefono = request.getParameter("telefono");
		String mailPersonal = request.getParameter("mailPersonal");
		String mailInstitucional = request.getParameter("mailInstitucional");
		String clave = request.getParameter("clave");
		String fechaNacimiento = (String) request.getParameter("fechaNacimiento");
		String generoTexto = request.getParameter("genero");
		String itrTexto = request.getParameter("itr");
		String departamentoTexto = request.getParameter("departamento");
		String localidadTexto = request.getParameter("localidad");
		String rolTexto = request.getParameter("rol");
		String semestreTexto = request.getParameter("semestre");
		String generacionTexto = request.getParameter("generacion");
		String areaTexto = request.getParameter("area");

		String nombres2[] = nombres.split(" ");
		String apellidos2[] = apellidos.split(" ");

		String primerNombre = nombres2.length > 0 ? nombres2[0] : "";
		String segundoNombre = nombres2.length > 1 ? nombres2[1] : "";

		String primerApellido = apellidos2.length > 0 ? apellidos2[0] : "";
		String segundoApellido = apellidos2.length > 1 ? apellidos2[1] : "";

		// Le ponemos un rol por defecto para que en la validación no se rompa
		String rolValidacion = rolTexto.equals("Seleccione su rol") ? ""
				: rolTexto.equals("Estudiante") ? "estudiante" : "funcionario";

		boolean validacionInputs = ValidacionUsuario.validarUnUsuario(clave, cedula,
				Fabrica.getFechaDesdeString(fechaNacimiento), mailInstitucional, mailPersonal, primerApellido,
				primerNombre, segundoApellido, segundoNombre, telefono, rolValidacion, request.getSession());

		boolean validacionComboBoxes = ValidacionComboBoxes.validar(departamentoTexto, generoTexto, localidadTexto,
				itrTexto, generacionTexto, semestreTexto, areaTexto, rolTexto, request.getSession());

		if (!validacionInputs || !validacionComboBoxes) {
			request.getSession().setAttribute("rolSeleccionado", rolTexto);
			response.sendRedirect("/Proyecto-PInfra/pages/registro/index.jsp");
			return;
		}

		RolDTO rol = ServiceRol.listarFiltro(rolTexto).get(0);
		GeneroDTO genero = ServiceGenero.listarFiltro(generoTexto).get(0);
		ItrDTO itr = ServiceItr.listarFiltro(itrTexto).get(0);
		LocalidadDTO localidad = ServiceUbicacion.listarLocalidadesFiltro(localidadTexto).get(0);
		String nombreUsuario = Fabrica.generarNombreUsuario(mailInstitucional);
		BigDecimal documento = new BigDecimal(cedula);

		UsuarioDTO oUsuarioNuevo = new UsuarioDTO(Fabrica.getSHA256Hash(clave), documento,
				Fabrica.getFechaDesdeString(fechaNacimiento), mailInstitucional, mailPersonal, nombreUsuario,
				primerApellido, primerNombre, segundoApellido, segundoNombre, telefono, "S", "N", genero, itr,
				localidad, rol);

		if (rolTexto.equals("Analista")) {
			boolean oAnalistaCreado = ServiceUsuario.crear(oUsuarioNuevo, new AnalistaDTO());

			if (!oAnalistaCreado) {
				registroFallido(request, response, "/Proyecto-PInfra/pages/registro/index.jsp");
				return;
			}
		}

		if (rolTexto.equals("Tutor") || rolTexto.equals("Encargado")) {
			boolean oTutorCreado = ServiceUsuario.crear(oUsuarioNuevo,
					new TutorDTO(ServiceArea.listarFiltro(areaTexto).get(0)));

			if (!oTutorCreado) {
				registroFallido(request, response, "/Proyecto-PInfra/pages/registro/index.jsp");
				return;
			}
		}

		if (rolTexto.equals("Estudiante")) {
			boolean oEstudianteCreado = ServiceUsuario.crear(oUsuarioNuevo,
					new EstudianteDTO(generacionTexto, new BigDecimal(semestreTexto)));

			if (!oEstudianteCreado) {
				registroFallido(request, response, "/Proyecto-PInfra/pages/registro/index.jsp");
				return;
			}
		}

		registroExitoso(request, response, "/Proyecto-PInfra/pages/registro/index.jsp");
	}

	private void registroFallido(HttpServletRequest request, HttpServletResponse response, String ruta)
			throws IOException {
		response.sendRedirect("/Proyecto-PInfra/pages/registro/index.jsp");
		return;
	}

	private void registroExitoso(HttpServletRequest request, HttpServletResponse response, String ruta)
			throws IOException {
		request.getSession().setAttribute(("mostrarNotificacion"), true);
		request.getSession().setAttribute(("tituloNotificacion"), "¡Registro exitoso!");
		request.getSession().setAttribute(("descripcionNotificacion"),
				"Tu cuenta debe ser confirmada por un analista, cuando lo haga te llegará un mail.");
		Fabrica.limpiarMensajesDeError(request.getSession());
		response.sendRedirect("/Proyecto-PInfra/pages/registro/index.jsp");
		return;
	}

}
