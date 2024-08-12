package com.cliente.contexto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cliente.services.ServiceUsuario;
import com.servidor.entidades.Constancia;
import com.servidor.entidades.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Fabrica {

	// Atributos de apoyo para uso global de la app
	private static Usuario oUsuarioLogueado = new Usuario();
	private static List<String> meses = Arrays.asList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
			"Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
	private static List<String> anios = Arrays.asList("2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023",
			"2024");
	private static ArrayList<Constancia> listaConstancia = new ArrayList<Constancia>();

	public static Usuario getoUsuarioLogueado() {
		return oUsuarioLogueado;
	}

	public static void setoUsuarioLogueado(Usuario oUsuarioLogueado) {
		Fabrica.oUsuarioLogueado = oUsuarioLogueado;

	}

	public static List<String> getMeses() {
		return meses;
	}

	public static List<String> getAnios() {
		return anios;
	}

	public static ArrayList<Constancia> getListaConstancia() {
		return listaConstancia;
	}

	public static void setListaConstancia(ArrayList<Constancia> listaConstancia) {
		Fabrica.listaConstancia = listaConstancia;
	}

	public static String generarNombreUsuario(String emailInstitucional) {

		String[] partes = emailInstitucional.split("@");
		String nombreDeUsuario = partes[0];
		return nombreDeUsuario;

	}

	public static Date getFechaDesdeString(String fechaString) {

		if (fechaString.equals("") || fechaString == null) {
			return null;
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate localDate = LocalDate.parse(fechaString, formatter);

		Date fechaNacimiento = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return fechaNacimiento;
	}

	public static void limpiarMensajesDeError(HttpSession sesion) {
		sesion.removeAttribute("errorNombre");
		sesion.removeAttribute("errorApellido");
		sesion.removeAttribute("errorCedula");
		sesion.removeAttribute("errorMailPersonal");
		sesion.removeAttribute("errorMailInstitucional");
		sesion.removeAttribute("errorTelefono");
		sesion.removeAttribute("errorClave");
		sesion.removeAttribute("errorFechaNacimiento");
		sesion.removeAttribute("errorRol");
		sesion.removeAttribute("errorArea");
		sesion.removeAttribute("errorGeneracion");
		sesion.removeAttribute("errorSemestre");
		sesion.removeAttribute("errorDepartamento");
		sesion.removeAttribute("errorGenero");
		sesion.removeAttribute("errorLocalidad");
		sesion.removeAttribute("errorItr");
		sesion.removeAttribute("errorNombreItr");
		sesion.removeAttribute("errorDepartamentoItr");
		sesion.removeAttribute("errorListaDeTutores");
		sesion.removeAttribute("errorFechaParse");
		sesion.removeAttribute("errorFechaEvento");
		sesion.removeAttribute("errorFechaFinEvento");
		sesion.removeAttribute("errorFechaInicioEvento");
		sesion.removeAttribute("errorItrEvento");
		sesion.removeAttribute("errorTituloEvento");
		sesion.removeAttribute("errorEventoReclamo");
		sesion.removeAttribute("errorTituloReclamo");
		sesion.removeAttribute("errorDetalleReclamo");
		sesion.removeAttribute("errorEstadoReclamo");
		sesion.removeAttribute("errorTipoConstanciaDescripcion");
		sesion.removeAttribute("errorTipoConstanciaNombre");
		sesion.removeAttribute("errorTituloConstancia");
		sesion.removeAttribute("errorDetalleConstancia");
		sesion.removeAttribute("errorEventoConstancia");
		sesion.removeAttribute("errorTipoConstancia");
		sesion.removeAttribute("errorEstadoConstancia");
		sesion.removeAttribute("errorMes");
		sesion.removeAttribute("errorAnio");
		sesion.removeAttribute("errorEstadoDescripcion");
		sesion.removeAttribute("errorFiltro");
	}

	public static String getCantidadSinConfirmar() {
		if (ServiceUsuario.listarSinConfirmar("N").size() == 0 || ServiceUsuario.listarSinConfirmar("N") == null) {
			return "";
		}
		return String.valueOf(ServiceUsuario.listarSinConfirmar("N").size());
	}

	public static void generarModal(HttpServletRequest request, String urlBtnSi, String tituloModal,
			String descripcionModal, String metodoModal) {
		request.getSession().setAttribute("mostrarModal", true);
		request.getSession().setAttribute("urlBtnSiModal", urlBtnSi);
		request.getSession().setAttribute("tituloModal", tituloModal);
		request.getSession().setAttribute("descripcionModal", descripcionModal);
		request.getSession().setAttribute("metodoModal", metodoModal);
	}

	public static String getSHA256Hash(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes());

			// Convertir el hash de bytes a formato hexadecimal
			StringBuilder hexString = new StringBuilder();
			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
