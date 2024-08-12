package com.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.cliente.contexto.Generar;
import com.cliente.services.ServiceConstancia;
import com.servidor.entidades.Constancia;
import com.servidor.entidades.Estudiante;

@Path("constancia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConstanciaRest {

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response generar(@FormParam("idConstancia") String idConstancia) {
		if (idConstancia == null || idConstancia.trim().isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Faltan datos").build();
		}

		Constancia oConstancia = ServiceConstancia.getById(Long.parseLong(idConstancia));
		Estudiante oEstudiante = oConstancia.getEstudiante();
		Generar generar = new Generar();
		byte[] pdfBytes = generar.constancia(oConstancia, oEstudiante);

		if (pdfBytes == null) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al generar PDF").build();
		}

		return Response.ok(pdfBytes, MediaType.APPLICATION_OCTET_STREAM)
				.header("Content-Disposition", "attachment; filename=\"Constancia.pdf\"").build();
	}
}
