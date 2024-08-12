package com.cliente.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.dto.UsuarioDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class ServiceJWT {

	private static final String SECRET_KEY = "clavesupersecreta";
	private static final List<String> rutasAnalista = Arrays.asList("Estados", "Gestion", "GestionAnalistas", "GestionEstudiantes",
			"Perfil", "GestionTutores", "Confirmacion", "Eventos", "Constancias", "SolicitudesConstancias",
			"TiposConstancias", "Reclamos", "EditarReclamos", "Reportes", "ITRS", "Edicion", "Estado constancia");
	private static final List<String> rutasEstudiante = Arrays.asList("Constancias", "Reclamos", "EditarReclamos",
			"Reportes", "Perfil", "SolicitarConstancia", "ListarMisConstancia", "Editar constancia",
			"Ver Estado constancia");
	private static final List<String> rutasTutor = Arrays.asList("Perfil");
	private static final List<String> rutasEncargado = Arrays.asList("Perfil");

	public static String generarToken(UsuarioDTO oUsuario, Date fechaExpiracion) {
		return Jwts.builder().setSubject(oUsuario.getNombreUsuario()).claim("rol", oUsuario.getRol().getDescripcion())
				.setExpiration(fechaExpiracion).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public static boolean validarToken(HttpServletRequest request, HttpServletResponse response) {
		try {
			String token = (String) request.getSession().getAttribute("token");
			System.out.println("--------TOKEN GENERADO--------");
			System.out.println(token);
			System.out.println("-------------------------------");
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
			Date expirationDate = claims.getExpiration();
			Date now = new Date();
			if (expirationDate != null && now.after(expirationDate)) {
				return false;
			}

			String nombreUsuario = claims.getSubject();
			boolean existeUsuario = ServiceUsuario.comprobarNombreUsuario(nombreUsuario);
			if (existeUsuario) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static void comprobarSesion(HttpServletRequest request, HttpServletResponse response, String pagina)
			throws IOException {
		boolean tienePermiso = false;

		// Mapa que asocia roles con páginas a las que puede acceder cada tipo de
		// usuario
		Map<String, List<String>> rolPermisos = new HashMap<>();
		rolPermisos.put("Analista", rutasAnalista);
		rolPermisos.put("Estudiante", rutasEstudiante);
		rolPermisos.put("Tutor", rutasTutor);
		rolPermisos.put("Encargado", rutasEncargado);

		if (request.getSession().getAttribute("token") == null) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}

		tienePermiso = ServiceJWT.validarToken(request, response);

		if (!tienePermiso) {
			response.sendRedirect("/Proyecto-PInfra/pages/login/index.jsp");
			return;
		}

		String rol = getRol(request);
		// Compruebo si el rol tiene permiso para acceder a la página solicitada
		if (!rolPermisos.getOrDefault(rol, List.of()).contains(pagina)) {
			response.sendRedirect("/Proyecto-PInfra/index.jsp");
			return;
		}
	}

	public static String getRol(HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("token");
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		return (String) claims.get("rol");
	}

}
