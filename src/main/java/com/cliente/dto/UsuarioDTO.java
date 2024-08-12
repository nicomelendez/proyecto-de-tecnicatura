package com.cliente.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.cliente.contexto.Fabrica;
import com.servidor.entidades.Usuario;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idUsuario;

	private String activo;

	private String clave;

	private String confirmado;

	private BigDecimal documento;

	private Date fechaNacimiento;

	private String mailInstitucional;

	private String mailPersonal;

	private String nombreUsuario;

	private String primerApellido;

	private String primerNombre;

	private String segundoApellido;

	private String segundoNombre;

	private String telefono;

	private DepartamentoDTO departamento;

	private GeneroDTO genero;

	private ItrDTO itr;

	private LocalidadDTO localidad;

	private RolDTO rol;

	private Long idRol;

	public UsuarioDTO(String clave, BigDecimal documento, Date fechaNacimiento, String mailInstitucional,
			String mailPersonal, String nombreUsuario, String primerApellido, String primerNombre,
			String segundoApellido, String segundoNombre, String telefono, String activo, String confirmado,
			GeneroDTO genero, ItrDTO itr, LocalidadDTO localidad, RolDTO rol) {
		super();
		this.activo = activo;
		this.clave = clave;
		this.confirmado = confirmado;
		this.documento = documento;
		this.fechaNacimiento = fechaNacimiento;
		this.mailInstitucional = mailInstitucional;
		this.mailPersonal = mailPersonal;
		this.nombreUsuario = nombreUsuario;
		this.primerApellido = primerApellido;
		this.primerNombre = primerNombre;
		this.segundoApellido = segundoApellido;
		this.segundoNombre = segundoNombre;
		this.telefono = telefono;
		this.genero = genero;
		this.itr = itr;
		this.localidad = localidad;
		this.rol = rol;
	}

	public UsuarioDTO(Usuario oUsuario) {
		super();
		this.idUsuario = oUsuario.getIdUsuario();
		this.activo = oUsuario.getActivo();
		this.clave = oUsuario.getClave();
		this.confirmado = oUsuario.getConfirmado();
		this.documento = oUsuario.getDocumento();
		this.fechaNacimiento = oUsuario.getFechaNacimiento();
		this.mailInstitucional = oUsuario.getMailInstitucional();
		this.mailPersonal = oUsuario.getMailPersonal();
		this.nombreUsuario = oUsuario.getNombreUsuario();
		this.primerApellido = oUsuario.getPrimerApellido();
		this.primerNombre = oUsuario.getPrimerNombre();
		this.segundoApellido = oUsuario.getSegundoApellido();
		this.segundoNombre = oUsuario.getSegundoNombre();
		this.telefono = oUsuario.getTelefono();
		this.genero = new GeneroDTO(oUsuario.getGenero());
		this.itr = new ItrDTO(oUsuario.getItr());
		this.localidad = new LocalidadDTO(oUsuario.getLocalidad());
		this.departamento = new DepartamentoDTO(oUsuario.getLocalidad().getDepartamento());
		this.rol = new RolDTO(oUsuario.getRol());
	}

	public UsuarioDTO(Usuario oUsuario, Long idRol, String login) {
		super();
		this.idUsuario = oUsuario.getIdUsuario();
		this.activo = oUsuario.getActivo();
		this.confirmado = oUsuario.getConfirmado();
		this.documento = oUsuario.getDocumento();
		this.fechaNacimiento = oUsuario.getFechaNacimiento();
		this.mailInstitucional = oUsuario.getMailInstitucional();
		this.mailPersonal = oUsuario.getMailPersonal();
		this.nombreUsuario = oUsuario.getNombreUsuario();
		this.primerApellido = oUsuario.getPrimerApellido();
		this.primerNombre = oUsuario.getPrimerNombre();
		this.segundoApellido = oUsuario.getSegundoApellido();
		this.segundoNombre = oUsuario.getSegundoNombre();
		this.telefono = oUsuario.getTelefono();
		this.genero = new GeneroDTO(oUsuario.getGenero());
		this.itr = new ItrDTO(oUsuario.getItr());
		this.localidad = new LocalidadDTO(oUsuario.getLocalidad());
		this.departamento = new DepartamentoDTO(oUsuario.getLocalidad().getDepartamento());
		this.rol = new RolDTO(oUsuario.getRol());
		this.idRol = idRol;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(String confirmado) {
		this.confirmado = confirmado;
	}

	public BigDecimal getDocumento() {
		return documento;
	}

	public void setDocumento(BigDecimal documento) {
		this.documento = documento;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getMailInstitucional() {
		return mailInstitucional;
	}

	public void setMailInstitucional(String mailInstitucional) {
		this.mailInstitucional = mailInstitucional;
	}

	public String getMailPersonal() {
		return mailPersonal;
	}

	public void setMailPersonal(String mailPersonal) {
		this.mailPersonal = mailPersonal;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public DepartamentoDTO getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoDTO departamento) {
		this.departamento = departamento;
	}

	public GeneroDTO getGenero() {
		return genero;
	}

	public void setGenero(GeneroDTO genero) {
		this.genero = genero;
	}

	public ItrDTO getItr() {
		return itr;
	}

	public void setItr(ItrDTO itr) {
		this.itr = itr;
	}

	public LocalidadDTO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}

	public RolDTO getRol() {
		return rol;
	}

	public void setRol(RolDTO rol) {
		this.rol = rol;
	}

	public String getEdad() {
		Calendar calNacimiento = new GregorianCalendar();
		calNacimiento.setTime(this.fechaNacimiento);
		int yearNacimiento = calNacimiento.get(Calendar.YEAR);
		int monthNacimiento = calNacimiento.get(Calendar.MONTH);
		int dayNacimiento = calNacimiento.get(Calendar.DAY_OF_MONTH);

		LocalDate fechaNacimientoLocal = LocalDate.of(yearNacimiento, monthNacimiento + 1, dayNacimiento);
		LocalDate fechaActual = LocalDate.now();
		Period periodo = Period.between(fechaNacimientoLocal, fechaActual);
		int edad = periodo.getYears();
		return edad + " años";
	}

	public String getNombreCompleto() {
		return this.primerNombre + " " + this.primerApellido;
	}

	public String getNombres() {
		return this.primerNombre + " " + (this.segundoNombre == null ? "" : this.segundoNombre);
	}

	public String getApellidos() {
		return this.primerApellido + " " + (this.segundoApellido == null ? "" : this.segundoApellido);
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

}
