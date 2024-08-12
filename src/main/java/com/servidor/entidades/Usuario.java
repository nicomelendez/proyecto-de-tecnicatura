package com.servidor.entidades;

import java.io.Serializable;
import javax.persistence.*;

import com.cliente.dto.UsuarioDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the USUARIOS database table.
 * 
 */
@Entity
@Table(name = "USUARIOS")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
@NamedQuery(name = "Usuario.login", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario AND u.clave = :clave")
@NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
@NamedQuery(name = "Usuario.findByRol", query = "SELECT u FROM Usuario u WHERE u.rol.idRol = :idRol")
@NamedQuery(name = "Usuario.findByDocumento", query = "SELECT u FROM Usuario u WHERE u.documento = :documento")
@NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.primerNombre LIKE :filtro1 AND u.primerApellido LIKE :filtro2")
@NamedQuery(name = "Usuario.findByActivo", query = "SELECT u FROM Usuario u WHERE u.confirmado = :filtro AND u.activo = :activo")
@NamedQuery(name = "Usuario.findByNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
@NamedQuery(name = "Usuario.findByMailPersonal", query = "SELECT u FROM Usuario u WHERE u.mailPersonal = :mailPersonal")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "USUARIOS_IDUSUARIO_GENERATOR", sequenceName = "SEQ_ID_USUARIO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIOS_IDUSUARIO_GENERATOR")
	@Column(name = "ID_USUARIO", unique = true, nullable = false, precision = 38)
	private long idUsuario;

	@Column(nullable = false, length = 1)
	private String activo;

	@Column(nullable = false, length = 255)
	private String clave;

	@Column(nullable = false, length = 1)
	private String confirmado;

	@Column(nullable = false, precision = 38)
	private BigDecimal documento;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_NACIMIENTO", nullable = false)
	private Date fechaNacimiento;

	@Column(name = "MAIL_INSTITUCIONAL", nullable = false, length = 70)
	private String mailInstitucional;

	@Column(name = "MAIL_PERSONAL", nullable = false, length = 70)
	private String mailPersonal;

	@Column(name = "NOMBRE_USUARIO", nullable = false, length = 50)
	private String nombreUsuario;

	@Column(name = "PRIMER_APELLIDO", nullable = false, length = 20)
	private String primerApellido;

	@Column(name = "PRIMER_NOMBRE", nullable = false, length = 20)
	private String primerNombre;

	@Column(name = "SEGUNDO_APELLIDO", length = 20)
	private String segundoApellido;

	@Column(name = "SEGUNDO_NOMBRE", length = 20)
	private String segundoNombre;

	@Column(nullable = false, length = 9)
	private String telefono;

	// bi-directional many-to-one association to Analista
	@OneToMany(mappedBy = "usuario")
	private List<Analista> analistas;

	// bi-directional many-to-one association to Estudiante
	@OneToMany(mappedBy = "usuario")
	private List<Estudiante> estudiantes;

	// bi-directional many-to-one association to Tutor
	@OneToMany(mappedBy = "usuario")
	private List<Tutor> tutores;

	// bi-directional many-to-one association to Genero
	@ManyToOne
	@JoinColumn(name = "ID_GENERO", nullable = false)
	private Genero genero;

	// bi-directional many-to-one association to Itr
	@ManyToOne
	@JoinColumn(name = "ID_ITR", nullable = false)
	private Itr itr;

	// bi-directional many-to-one association to Localidad
	@ManyToOne
	@JoinColumn(name = "ID_LOCALIDAD", nullable = false)
	private Localidad localidad;

	// bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name = "ID_ROL", nullable = false)
	private Rol rol;

	public Usuario() {
	}

	public Usuario(UsuarioDTO oUsuarioDTO) {
		this.idUsuario = oUsuarioDTO.getIdUsuario();
		this.clave = oUsuarioDTO.getClave();
		this.documento = oUsuarioDTO.getDocumento();
		this.fechaNacimiento = oUsuarioDTO.getFechaNacimiento();
		this.mailInstitucional = oUsuarioDTO.getMailInstitucional();
		this.mailPersonal = oUsuarioDTO.getMailPersonal();
		this.nombreUsuario = oUsuarioDTO.getNombreUsuario();
		this.primerApellido = oUsuarioDTO.getPrimerApellido();
		this.primerNombre = oUsuarioDTO.getPrimerNombre();
		this.segundoApellido = oUsuarioDTO.getSegundoApellido();
		this.segundoNombre = oUsuarioDTO.getSegundoNombre();
		this.telefono = oUsuarioDTO.getTelefono();
		this.activo = oUsuarioDTO.getActivo();
		this.confirmado = oUsuarioDTO.getConfirmado();
		this.genero = new Genero(oUsuarioDTO.getGenero());
		this.itr = new Itr(oUsuarioDTO.getItr());
		this.localidad = new Localidad(oUsuarioDTO.getLocalidad());
		this.rol = new Rol(oUsuarioDTO.getRol());
	}

	public long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getActivo() {
		return this.activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getConfirmado() {
		return this.confirmado;
	}

	public void setConfirmado(String confirmado) {
		this.confirmado = confirmado;
	}

	public BigDecimal getDocumento() {
		return this.documento;
	}

	public void setDocumento(BigDecimal documento) {
		this.documento = documento;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getMailInstitucional() {
		return this.mailInstitucional;
	}

	public void setMailInstitucional(String mailInstitucional) {
		this.mailInstitucional = mailInstitucional;
	}

	public String getMailPersonal() {
		return this.mailPersonal;
	}

	public void setMailPersonal(String mailPersonal) {
		this.mailPersonal = mailPersonal;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrimerNombre() {
		return this.primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSegundoNombre() {
		return this.segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Analista> getAnalistas() {
		return this.analistas;
	}

	public void setAnalistas(List<Analista> analistas) {
		this.analistas = analistas;
	}

	public Analista addAnalista(Analista analista) {
		getAnalistas().add(analista);
		analista.setUsuario(this);

		return analista;
	}

	public Analista removeAnalista(Analista analista) {
		getAnalistas().remove(analista);
		analista.setUsuario(null);

		return analista;
	}

	public List<Estudiante> getEstudiantes() {
		return this.estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public Estudiante addEstudiante(Estudiante estudiante) {
		getEstudiantes().add(estudiante);
		estudiante.setUsuario(this);

		return estudiante;
	}

	public Estudiante removeEstudiante(Estudiante estudiante) {
		getEstudiantes().remove(estudiante);
		estudiante.setUsuario(null);

		return estudiante;
	}

	public List<Tutor> getTutores() {
		return this.tutores;
	}

	public void setTutores(List<Tutor> tutores) {
		this.tutores = tutores;
	}

	public Tutor addTutore(Tutor tutore) {
		getTutores().add(tutore);
		tutore.setUsuario(this);

		return tutore;
	}

	public Tutor removeTutore(Tutor tutore) {
		getTutores().remove(tutore);
		tutore.setUsuario(null);

		return tutore;
	}

	public Genero getGenero() {
		return this.genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Itr getItr() {
		return this.itr;
	}

	public void setItr(Itr itr) {
		this.itr = itr;
	}

	public Localidad getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(Localidad localidade) {
		this.localidad = localidade;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRole(Rol rol) {
		this.rol = rol;
	}

	public String getNombreCompleto() {
		return this.primerNombre + " " + this.primerApellido;
	}
}