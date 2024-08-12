package com.cliente.servlets.usuario;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.cliente.services.ServiceAnalista;
import com.cliente.services.ServiceArea;
import com.cliente.services.ServiceEstudiante;
import com.cliente.services.ServiceGenero;
import com.cliente.services.ServiceItr;
import com.cliente.services.ServiceJWT;
import com.cliente.services.ServiceTutor;
import com.cliente.services.ServiceUbicacion;
import com.cliente.services.ServiceUsuario;

@WebServlet(name = "ServletEditarPerfil", urlPatterns = "/SvEditarPerfil")
public class SvEditarPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String nombres = null;
	private String apellidos = null;
	private String telefono = null;
	private String mailPersonal = null;
	private String clave = null;
	private String fechaNacimiento = null;
	private String generoTexto = null;
	private String itrTexto = null;
	private String departamentoTexto = null;
	private String localidadTexto = null;
	private String semestreTexto = null;
	private String generacionTexto = null;
	private String areaTexto = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!ServiceJWT.validarToken(request, response)) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}
		request.setCharacterEncoding("UTF-8");

		if (request.getSession().getAttribute("mostrarModal") == null) {
			nombres = request.getParameter("nombres");
			apellidos = request.getParameter("apellidos");
			telefono = request.getParameter("telefono");
			mailPersonal = request.getParameter("mailPersonal");
			clave = request.getParameter("clave");
			fechaNacimiento = request.getParameter("fechaNacimiento");
			generoTexto = request.getParameter("genero");
			itrTexto = request.getParameter("itr");
			departamentoTexto = request.getParameter("departamento");
			localidadTexto = request.getParameter("localidad");
			semestreTexto = request.getParameter("semestreOculto");
			generacionTexto = request.getParameter("generacionOculto");
			areaTexto = request.getParameter("areaOculto");

			Fabrica.generarModal(request, "/Proyecto-PInfra/SvEditarPerfil",
					"¿Está seguro de que desea modificar su perfil?", "Sus datos serán modificados", "POST");
			response.sendRedirect("/Proyecto-PInfra/pages/perfil/index.jsp");
			return;
		}

		String nombres2[] = nombres.split(" ");
		String apellidos2[] = apellidos.split(" ");

		String primerNombre = nombres2.length > 0 ? nombres2[0] : "";
		String segundoNombre = nombres2.length > 1 ? nombres2[1] : "";

		String primerApellido = apellidos2.length > 0 ? apellidos2[0] : "";
		String segundoApellido = apellidos2.length > 1 ? apellidos2[1] : "";

		UsuarioDTO oUsuarioEditado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogueado");
		UsuarioDTO oUsuarioAntiguo = ServiceUsuario.listarFiltroDocumento(oUsuarioEditado.getDocumento().toString())
				.get(0);

		String nombreUsuario = Fabrica.generarNombreUsuario(oUsuarioEditado.getMailInstitucional());
		// Le ponemos un rol por defecto para que en la validación no se rompa
		String rolValidacion = oUsuarioEditado.getRol().getDescripcion().equals("Estudiante") ? "estudiante"
				: "funcionario";

		String claveAValidar = clave == null || clave.trim().equals("") ? "12345678a" : clave;

		boolean validacionInputs = ValidacionUsuario.validarEditarPerfil(claveAValidar,
				oUsuarioEditado.getDocumento().toString(), Fabrica.getFechaDesdeString(fechaNacimiento),
				oUsuarioEditado.getMailInstitucional(), mailPersonal, primerApellido, primerNombre, segundoApellido,
				segundoNombre, telefono, rolValidacion, request.getSession());

		boolean validacionComboBoxes = ValidacionComboBoxes.validar(departamentoTexto, generoTexto, localidadTexto,
				itrTexto, generacionTexto, semestreTexto, areaTexto, oUsuarioEditado.getRol().getDescripcion(),
				request.getSession());

		if (!validacionInputs || !validacionComboBoxes) {
			limpiarCamposDelSevlet(request);
			response.sendRedirect("/Proyecto-PInfra/pages/perfil/index.jsp");
			return;
		}

		GeneroDTO genero = ServiceGenero.listarFiltro(generoTexto).get(0);
		ItrDTO itr = ServiceItr.listarFiltro(itrTexto).get(0);
		LocalidadDTO localidad = ServiceUbicacion.listarLocalidadesFiltro(localidadTexto).get(0);

		String claveFinal = clave == null || clave.trim().equals("") ? oUsuarioAntiguo.getClave()
				: Fabrica.getSHA256Hash(clave);

		UsuarioDTO oUsuarioNuevo = new UsuarioDTO(claveFinal, oUsuarioEditado.getDocumento(),
				Fabrica.getFechaDesdeString(fechaNacimiento), oUsuarioEditado.getMailInstitucional(), mailPersonal,
				nombreUsuario, primerApellido, primerNombre, segundoApellido, segundoNombre, telefono, "S", "S", genero,
				itr, localidad, oUsuarioEditado.getRol());

		oUsuarioNuevo.setIdUsuario(oUsuarioEditado.getIdUsuario());

		if (oUsuarioEditado.getRol().getDescripcion().equals("Analista")) {
			AnalistaDTO oAnalistaAntiguo = ServiceAnalista.getByDocumento(oUsuarioEditado.getDocumento().toString());

			boolean oAnalistaEditado = ServiceUsuario.actualizar(oUsuarioNuevo, oAnalistaAntiguo);
			request.getSession().removeAttribute("usuarioLogueado");

			var oUsuarioActualizado = ServiceUsuario.listarFiltroDocumento(oUsuarioEditado.getDocumento().toString())
					.get(0);

			request.getSession().setAttribute("usuarioLogueado", oUsuarioActualizado);
			limpiarCamposDelSevlet(request);
			Fabrica.limpiarMensajesDeError(request.getSession());
			response.sendRedirect("/Proyecto-PInfra/pages/perfil/index.jsp");
			return;
		}

		if (oUsuarioEditado.getRol().getDescripcion().equals("Tutor")
				|| oUsuarioEditado.getRol().getDescripcion().equals("Encargado")) {
			boolean oTutorEditado = ServiceUsuario.actualizar(oUsuarioNuevo,
					new TutorDTO(ServiceTutor.getByDocumento(oUsuarioEditado.getDocumento().toString()).getIdTutor(),
							ServiceArea.listarFiltro(areaTexto).get(0), oUsuarioNuevo));
			request.getSession().removeAttribute("usuarioLogueado");

			var oUsuarioActualizado = ServiceUsuario.listarFiltroDocumento(oUsuarioEditado.getDocumento().toString())
					.get(0);

			request.getSession().setAttribute("usuarioLogueado", oUsuarioActualizado);
			limpiarCamposDelSevlet(request);
			Fabrica.limpiarMensajesDeError(request.getSession());
			response.sendRedirect("/Proyecto-PInfra/pages/perfil/index.jsp");
			return;
		}

		if (oUsuarioEditado.getRol().getDescripcion().equals("Estudiante")) {
			boolean oEstudianteEditado = ServiceUsuario.actualizar(oUsuarioNuevo, new EstudianteDTO(
					ServiceEstudiante.getByDocumento(oUsuarioEditado.getDocumento().toString()).getIdEstudiante(),
					generacionTexto, new BigDecimal(semestreTexto), oUsuarioNuevo));
			request.getSession().removeAttribute("usuarioLogueado");

			var oUsuarioActualizado = ServiceUsuario.listarFiltroDocumento(oUsuarioEditado.getDocumento().toString())
					.get(0);

			request.getSession().setAttribute("usuarioLogueado", oUsuarioActualizado);
			limpiarCamposDelSevlet(request);
			Fabrica.limpiarMensajesDeError(request.getSession());
			response.sendRedirect("/Proyecto-PInfra/pages/perfil/index.jsp");
			return;
		}
	}

	private void limpiarCamposDelSevlet(HttpServletRequest request) {
		request.getSession().removeAttribute("mostrarModal");
		nombres = null;
		apellidos = null;
		telefono = null;
		mailPersonal = null;
		clave = null;
		fechaNacimiento = null;
		generoTexto = null;
		itrTexto = null;
		departamentoTexto = null;
		localidadTexto = null;
		semestreTexto = null;
		generacionTexto = null;
		areaTexto = null;
	}

}
