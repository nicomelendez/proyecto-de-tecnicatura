package com.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.api.activeDirectory.LdapService;
import com.cliente.contexto.Fabrica;
import com.cliente.services.ServiceUsuario;
import com.servidor.utils.Respuesta;

@Path("login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginRest {

	private LdapService oLdapService = new LdapService();

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Respuesta login(@FormParam("nombreUsuario") String nombreUsuario, @FormParam("clave") String clave) {

		if (nombreUsuario == null || clave == null || nombreUsuario.trim().isEmpty() || clave.trim().isEmpty()) {
			return new Respuesta("error", "Debe completar todos los campos para ingresar a su cuenta.", null);
		}

		boolean isAuthenticated = oLdapService.authenticate(nombreUsuario, clave);

		if (isAuthenticated) {
			Respuesta oRespuesta = ServiceUsuario.login(nombreUsuario, Fabrica.getSHA256Hash(clave));

			return oRespuesta;
		} else {
			return new Respuesta("error", "Usuario o contraseña incorrectos", null);
		}
	}
}
