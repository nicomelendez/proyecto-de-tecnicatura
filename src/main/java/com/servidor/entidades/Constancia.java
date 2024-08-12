package com.servidor.entidades;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.cliente.dto.EstadoConstanciaDTO;
import com.cliente.services.ServiceAnalista;
import com.cliente.services.ServiceEstadoConstancia;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the CONSTANCIAS database table.
 * 
 */
@Entity
@Table(name = "CONSTANCIAS")
@NamedQuery(name = "Constancia.findAll", query = "SELECT c FROM Constancia c")
public class Constancia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CONSTANCIAS_IDCONSTANCIA_GENERATOR", sequenceName = "SEQ_ID_CONSTANCIA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONSTANCIAS_IDCONSTANCIA_GENERATOR")
	@Column(name = "ID_CONSTANCIA", unique = true, nullable = false, precision = 38)
	private long idConstancia;

	@Column(nullable = false, length = 255)
	private String detalle;

	@Column(nullable = false, length = 1)
	private String activo;

	// bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name = "ID_ESTUDIANTE", nullable = false)
	private Estudiante estudiante;

	// bi-directional many-to-one association to Evento
	@ManyToOne
	@JoinColumn(name = "ID_EVENTO", nullable = false)
	private Evento evento;

	// bi-directional many-to-one association to Tipo
	@ManyToOne
	@JoinColumn(name = "ID_TIPO", nullable = false)
	private Tipo tipo;

	// bi-directional many-to-one association to EstadosConstancia
	@OneToMany(mappedBy = "constancia", fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private List<EstadoConstancia> estadosConstancias;

	public Constancia() {
	}

	public Constancia(String detalle, Estudiante estudiante, Evento evento, Tipo tipo, String activo) {
		super();
		this.detalle = detalle;
		this.estudiante = estudiante;
		this.evento = evento;
		this.tipo = tipo;
		this.activo = activo;
	}
	
	public String getFecha() {
		return ServiceEstadoConstancia.getFechaEmision(this.idConstancia);
	}

	public long getIdConstancia() {
		return this.idConstancia;
	}

	public void setIdConstancia(long idConstancia) {
		this.idConstancia = idConstancia;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Estudiante getEstudiante() {
		return this.estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Evento getEvento() {
		return this.evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<EstadoConstancia> getEstadosConstancias() {
		return this.estadosConstancias;
	}

	public void setEstadosConstancias(List<EstadoConstancia> estadosConstancias) {
		this.estadosConstancias = estadosConstancias;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public EstadoConstancia addEstadosConstancia(EstadoConstancia estadosConstancia) {
		getEstadosConstancias().add(estadosConstancia);
		estadosConstancia.setConstancia(this);

		return estadosConstancia;
	}

	public EstadoConstancia removeEstadosConstancia(EstadoConstancia estadosConstancia) {
		getEstadosConstancias().remove(estadosConstancia);
		estadosConstancia.setConstancia(null);

		return estadosConstancia;
	}

	public EstadoConstanciaDTO getEstadoDeConstancia() {

		// Listar luego la cantidad de los analistas del ITR del estudiante de la
		// constancia
		var contadoDeAnalistas = ServiceAnalista.listar().size();
		var contadoDeEstados = this.estadosConstancias.size();
		EstadoConstanciaDTO oEstadoConstanciaDTO = new EstadoConstanciaDTO();
		Date ultimaFecha = null;
		Date fechaEmision = null;
		for (EstadoConstancia oEstadoConstancia : estadosConstancias) {

			// Si el contado es igual es porque no hay interacciones de ningun analista
			if (contadoDeAnalistas == contadoDeEstados) {
				oEstadoConstanciaDTO.setAnalista("-");
				oEstadoConstanciaDTO.setEstado(oEstadoConstancia.getEstado().getDescripcion());
				oEstadoConstanciaDTO.setFechaEmision(oEstadoConstancia.getId().getFechaHora().toString());
				break;
			} else {
				if (fechaEmision == null || oEstadoConstancia.getId().getFechaHora().compareTo(fechaEmision) < 0) {
					fechaEmision = (Date) oEstadoConstancia.getId().getFechaHora();
				}
				// Si el numero no es igual hay que ver cual de todos los registro es el ultimo
				// para obtener su estado y analista
				if (ultimaFecha == null || ultimaFecha.compareTo(oEstadoConstancia.getId().getFechaHora()) < 0) {
					// Si la última fecha es nula o es menor que la fecha del estado actual,
					// actualizamos la información del DTO con el estado actual
					ultimaFecha = (Date) oEstadoConstancia.getId().getFechaHora();
					oEstadoConstanciaDTO.setAnalista(ServiceAnalista.getById(oEstadoConstancia.getId().getIdAnalista())
							.getUsuario().getNombreCompleto());
					oEstadoConstanciaDTO.setEstado(oEstadoConstancia.getEstado().getDescripcion());
					oEstadoConstanciaDTO.setFechaEmision(fechaEmision.toString());
				}

			}
		}

		return oEstadoConstanciaDTO;

	}

	public String getTituloPdf() {
		return "Constancia_" + this.tipo.getNombre().trim() + "_" + this.estudiante.getUsuario().getNombreCompleto().trim();
	}
}