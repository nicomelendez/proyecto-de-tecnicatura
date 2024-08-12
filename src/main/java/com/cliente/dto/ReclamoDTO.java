package com.cliente.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cliente.services.ServiceEstadoReclamo;
import com.servidor.entidades.EstadoReclamo;
import com.servidor.entidades.Reclamo;

public class ReclamoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idReclamo;
	private String detalle;
	private EstudianteDTO estudiante;
	private EventoDTO evento;
	private List<EstadoReclamoDTO> estadosReclamos;
	private String activo;
	private String fechaEmision;
	private String ultimaFecha;

	public ReclamoDTO() {
		super();
	}

	public ReclamoDTO(Reclamo oReclamo) {
		this.idReclamo = oReclamo.getIdReclamo();
		this.detalle = oReclamo.getDetalle();
		this.estudiante = new EstudianteDTO(oReclamo.getEstudiante());
		this.evento = new EventoDTO(oReclamo.getEvento());
		this.estadosReclamos = convertList(oReclamo.getEstadosReclamos());
		this.activo = oReclamo.getActivo();
		this.fechaEmision = ServiceEstadoReclamo.getFechaEmision(oReclamo.getIdReclamo());
		this.ultimaFecha = ServiceEstadoReclamo.getUltimaFecha(oReclamo.getIdReclamo());
	}

	public ReclamoDTO(Reclamo oReclamo, String api) {
		this.idReclamo = oReclamo.getIdReclamo();
		this.detalle = oReclamo.getDetalle();
		this.estudiante = new EstudianteDTO(oReclamo.getEstudiante());
		this.evento = new EventoDTO(oReclamo.getEvento());
		this.activo = oReclamo.getActivo();
		this.fechaEmision = ServiceEstadoReclamo.getFechaEmision(oReclamo.getIdReclamo());
		this.ultimaFecha = ServiceEstadoReclamo.getUltimaFecha(oReclamo.getIdReclamo());
	}

	public ReclamoDTO(String detalle, EstudianteDTO estudiante, EventoDTO evento) {
		super();
		this.detalle = detalle;
		this.estudiante = estudiante;
		this.evento = evento;
	}

	public long getIdReclamo() {
		return idReclamo;
	}

	public void setIdReclamo(long idReclamo) {
		this.idReclamo = idReclamo;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public EstudianteDTO getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(EstudianteDTO estudiante) {
		this.estudiante = estudiante;
	}

	public EventoDTO getEvento() {
		return evento;
	}

	public void setEvento(EventoDTO evento) {
		this.evento = evento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<EstadoReclamoDTO> getEstadosReclamos() {
		return estadosReclamos;
	}

	public void setEstadosReclamos(List<EstadoReclamoDTO> estadosReclamos) {
		this.estadosReclamos = estadosReclamos;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	private List<EstadoReclamoDTO> convertList(List<EstadoReclamo> listaEstadosReclamos) {
		List<EstadoReclamoDTO> lista = new ArrayList<>();
		for (EstadoReclamo oEstadoReclamo : listaEstadosReclamos) {
			EstadoReclamoDTO estadoReclamoDTO = new EstadoReclamoDTO(oEstadoReclamo);

			lista.add(estadoReclamoDTO);
		}
		return lista;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getUltimaFecha() {
		return ultimaFecha;
	}

	public void setUltimaFecha(String ultimaFecha) {
		this.ultimaFecha = ultimaFecha;
	}
}
