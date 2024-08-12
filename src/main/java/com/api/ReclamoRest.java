package com.api;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.api.request.ReclamoRequest;
import com.cliente.dto.AnalistaDTO;
import com.cliente.dto.ReclamoDTO;
import com.cliente.services.ServiceAnalista;
import com.cliente.services.ServiceEstado;
import com.cliente.services.ServiceEstadoReclamo;
import com.cliente.services.ServiceEstudiante;
import com.cliente.services.ServiceEvento;
import com.cliente.services.ServiceReclamo;
import com.servidor.entidades.EstadoReclamo;
import com.servidor.entidades.EstadoReclamoPK;
import com.servidor.entidades.Estudiante;
import com.servidor.entidades.Evento;
import com.servidor.entidades.Reclamo;
import com.servidor.utils.Respuesta;

@Path("reclamos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReclamoRest {

	@GET
	@Path("/estudiante")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<ReclamoDTO> getMisReclamos(@QueryParam("idEstudiante") String idEstudiante) {

		ArrayList<ReclamoDTO> listaDeReclamos = ServiceReclamo.listarFiltro(idEstudiante);

		return listaDeReclamos;
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<ReclamoDTO> getReclamos() {
		ArrayList<ReclamoDTO> listaDeReclamos = ServiceReclamo.listar();

		return listaDeReclamos;
	}

	@GET
	@Path("/id")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReclamoDTO getReclamo(@QueryParam("idReclamo") String idReclamo) {
		ReclamoDTO reclamo = new ReclamoDTO(ServiceReclamo.getById(Long.valueOf(idReclamo)));

		return reclamo;
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Respuesta create(ReclamoRequest oReclamoRequest) {

		System.out.println(oReclamoRequest);

		// Verificación de parámetros nulos
		if (oReclamoRequest == null || oReclamoRequest.getTitulo() == null || oReclamoRequest.getDetalle() == null
				|| oReclamoRequest.getDocumento() == null || oReclamoRequest.getIdEvento() == null) {
			return new Respuesta("error", "Debe completar todos los datos", null);
		}

		Estudiante oEstudiante = new Estudiante(ServiceEstudiante.getByDocumento(oReclamoRequest.getDocumento()));
		Evento oEvento = ServiceEvento.buscarEventoPorId(oReclamoRequest.getIdEvento());
		Reclamo oReclamoCreado = ServiceReclamo
				.crear(new Reclamo(oReclamoRequest.getTitulo(), oEstudiante, oEvento, "S"));

		for (AnalistaDTO oAnalista : ServiceAnalista.listar()) {
			long offset = oAnalista.getIdAnalista() * 1000;
			Date uniqueDate = new Date(new Date().getTime() + offset);

			EstadoReclamoPK ePK = new EstadoReclamoPK(oReclamoCreado.getIdReclamo(), oAnalista.getIdAnalista(),
					uniqueDate);
			EstadoReclamo eR = new EstadoReclamo(ePK, oReclamoRequest.getDetalle(),
					ServiceEstado.listarEstados().get(0));
			var EstadoReclamoCreado = ServiceEstadoReclamo.crear(eR);
		}

		if (oReclamoCreado == null) {
			return new Respuesta("error", "Error al crear la solicitud de reclamo", null);
		}

		return new Respuesta("success", "Solicitud de reclamo creada con exito", new ReclamoDTO(oReclamoCreado, "api"));
	}

	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Respuesta edit(ReclamoRequest oReclamoRequest) {
		// Verificación de parámetros nulos
		if (oReclamoRequest == null || oReclamoRequest.getIdReclamo() == null || oReclamoRequest.getTitulo() == null
				|| oReclamoRequest.getDetalle() == null || oReclamoRequest.getIdEvento() == null) {
			return new Respuesta("error", "Debe completar todos los datos", null);
		}

		Reclamo oReclamoAEditar = ServiceReclamo.getById(oReclamoRequest.getIdReclamo());
		oReclamoAEditar.setDetalle(oReclamoRequest.getTitulo());
		Evento oEvento = ServiceEvento.buscarEventoPorId(oReclamoRequest.getIdEvento());
		oReclamoAEditar.setEvento(oEvento);

		for (EstadoReclamo oEstadoReclamo : ServiceEstadoReclamo
				.listarEstadosReclamoPorIdReclamo(oReclamoAEditar.getIdReclamo())) {
			oEstadoReclamo.setDetalle(oReclamoRequest.getDetalle());
			var estadoEditado = ServiceEstadoReclamo.actualizar(oEstadoReclamo);
		}

		var oReclamoEditado = ServiceReclamo.actualizar(oReclamoAEditar);

		if (!oReclamoEditado) {
			return new Respuesta("error", "Error al editar la solicitud de reclamo", null);
		}

		return new Respuesta("success", "Solicitud de reclamo editada con exito",
				new ReclamoDTO(oReclamoAEditar, "api"));
	}

	@DELETE
	@Path("/id")
	@Produces(MediaType.APPLICATION_JSON)
	public Respuesta delete(@QueryParam("idReclamo") String idReclamo) {

		if (idReclamo == null) {
			return new Respuesta("error", "Faltan datos", null);
		}

		boolean eliminado = ServiceReclamo.eliminar(Long.valueOf(idReclamo));
		if (!eliminado) {
			return new Respuesta("error", "Error al eliminar el reclamo", null);
		}

		return new Respuesta("success", "Reclamo eliminado con éxito", null);
	}

}
