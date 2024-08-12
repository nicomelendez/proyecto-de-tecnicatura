<%@page import="com.cliente.services.ServiceEstudiante"%>
<%@page import="com.cliente.dto.AreaDTO"%>
<%@page import="com.cliente.services.ServiceTutor"%>
<%@page import="com.cliente.dto.RolDTO"%>
<%@page import="com.cliente.dto.DepartamentoDTO"%>
<%@page import="com.cliente.dto.ItrDTO"%>
<%@page import="com.cliente.dto.GeneroDTO"%>
<%@page import="com.cliente.dto.UsuarioDTO"%>
<%@page import="com.cliente.services.ServiceJWT"%>
<%@page import="com.cliente.services.ServiceGenero"%>
<%@page import="com.cliente.services.ServiceArea"%>
<%@page import="com.cliente.services.ServiceRol"%>
<%@page import="com.cliente.services.ServiceUbicacion"%>
<%@page import="com.cliente.services.ServiceItr"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
HttpSession sessionActual = request.getSession(false); // No crear una nueva sesión si no existe
UsuarioDTO usuarioLogueado = (UsuarioDTO) sessionActual.getAttribute("usuarioLogueado");
UsuarioDTO oUsuarioAEditar = (UsuarioDTO) sessionActual.getAttribute("oUsuarioAEditar");
if (oUsuarioAEditar == null) {
	response.sendRedirect("/Proyecto-PInfra/pages/gestion/index.jsp");
	return;
}
ServiceJWT.comprobarSesion(request, response, "Edicion");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/Proyecto-PInfra/style.css">
<link rel="stylesheet" type="text/css"
	href="/Proyecto-PInfra/pages/registro/style.css">
<meta name="description"
	content="Página de gestión de constancias de UTEC. Solicita tus constancias y mantente al día.">
<meta name="viewport" content="width=device-width">
<link rel="icon" type="image/ico"
	href="/Proyecto-PInfra/utils/img/faviconapp.ico">
<title>Editar usuario - UTEC</title>
</head>

<body>

	<!-- Modal de confirmación de acción -->
	<jsp:include page="/components/modal/index.jsp" />

	<div class="app">
		<!-- Encabezado de la página -->
		<jsp:include page="/components/layout/nav/index.jsp" />

		<!-- Contenido de la página -->
		<main class="contenido">

			<section class="registroSeccion">
				<div class="registroContenido">
					<h2 class="tituloRegistro">Editar usuario</h2>
					<form class="formularioLogin"
						action="/Proyecto-PInfra/SvEditarUsuario" method="POST">
						<h3 style="text-align: center; font-size: 14px;">Todos
							los campos son obligatorios</h3>
						<div class="formularioContenido">
							<label>Nombres <input type="text" name="nombres"
								placeholder="Ingrese sus nombres..."
								value="<%=oUsuarioAEditar.getNombres()%>"> <%
 if (request.getSession().getAttribute("errorNombre") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorNombre")%></span>
								<%
								}
								%>
							</label> <label>Apellidos <input type="text" name="apellidos"
								placeholder="Ingrese sus apellidos..."
								value="<%=oUsuarioAEditar.getApellidos()%>"> <%
 if (request.getSession().getAttribute("errorApellido") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;">
									<%=request.getSession().getAttribute("errorApellido")%>
							</span> <%
 }
 %>
							</label> <label>Cédula <input type="number" name="cedula"
								placeholder="Ingrese su cédula..."
								value="<%=oUsuarioAEditar.getDocumento()%>" disabled> <%
 if (request.getSession().getAttribute("errorCedula") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorCedula")%></span>
								<%
								}
								%>
							</label> <label>Teléfono <input type="number" name="telefono"
								placeholder="Ingrese su teléfono..."
								value="<%=oUsuarioAEditar.getTelefono()%>"> <%
 if (request.getSession().getAttribute("errorTelefono") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorTelefono")%></span>
								<%
								}
								%>
							</label> <label>Mail Personal <input type="text"
								name="mailPersonal" placeholder="Ingrese su mail personal..."
								value="<%=oUsuarioAEditar.getMailPersonal()%>"> <%
 if (request.getSession().getAttribute("errorMailPersonal") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorMailPersonal")%></span>
								<%
								}
								%>
							</label> <label>Mail Institucional <input type="text"
								name="mailInstitucional"
								placeholder="Ingrese su mail institucional..."
								value="<%=oUsuarioAEditar.getMailInstitucional()%>" disabled>

							</label> <label>Fecha de Nacimiento <input type="date" id="fecha"
								name="fechaNacimiento"
								value="<%=oUsuarioAEditar.getFechaNacimiento()%>"> <%
 if (request.getSession().getAttribute("errorFechaNacimiento") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorFechaNacimiento")%></span>
								<%
								}
								%>
							</label> <label>Género <select name="genero">
									<option selected>Selecciona un género</option>
									<%
									for (GeneroDTO oGenero : ServiceGenero.listar()) {
										// Verifica si el Género actual es igual al Género de oUsuarioAEditar
										String opcionSeleccionada = (oUsuarioAEditar != null
										&& oGenero.getNombre().equals(oUsuarioAEditar.getGenero().getNombre())) ? "selected" : "";
									%>

									<option value="<%=oGenero.getNombre()%>"
										<%=opcionSeleccionada%>><%=oGenero.getNombre()%></option>

									<%
									}
									%>
							</select> <%
 if (request.getSession().getAttribute("errorGenero") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorGenero")%></span>
								<%
								}
								%>
							</label> <label>ITR <select name="itr">
									<option selected>Selecciona un ITR</option>
									<%
									for (ItrDTO oItr : ServiceItr.listar()) {
										// Verifica si el ITR actual es igual al ITR de oUsuarioAEditar
										String opcionSeleccionada = (oUsuarioAEditar != null
										&& oItr.getNombre().equals(oUsuarioAEditar.getItr().getNombre())) ? "selected" : "";
									%>

									<option value="<%=oItr.getNombre()%>" <%=opcionSeleccionada%>><%=oItr.getNombre()%></option>

									<%
									}
									%>
							</select> <%
 if (request.getSession().getAttribute("errorItr") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorItr")%></span>
								<%
								}
								%>
							</label> <label>Departamento <select name="departamento"
								id="departamentoSelect" onchange="cargarLocalidades()">
									<option selected>Selecciona un departamento</option>
									<%
									for (DepartamentoDTO oDepartamento : ServiceUbicacion.listarDepartamentos()) {
										// Verifica si el Departamento actual es igual al Departamento de oUsuarioAEditar
										String opcionSeleccionada = (oUsuarioAEditar != null
										&& oDepartamento.getNombre().equals(oUsuarioAEditar.getDepartamento().getNombre())) ? "selected" : "";
									%>
									<option value="<%=oDepartamento.getNombre()%>"
										<%=opcionSeleccionada%>><%=oDepartamento.getNombre()%></option>
									<%
									}
									%>
							</select> <%
 if (request.getSession().getAttribute("errorDepartamento") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorDepartamento")%></span>
								<%
								}
								%>
							</label> <label>Localidad <select name="localidad"
								id="localidadSelect" disabled>
									<option selected>Selecciona una localidad</option>
							</select> <%
 if (request.getSession().getAttribute("errorLocalidad") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorLocalidad")%></span>
								<%
								}
								%>
							</label> <label>Rol <select name="rol" id="rolSelect"
								onchange="mostrarCampos()" disabled>
									<option selected>Seleccione su rol</option>
									<%
									for (RolDTO oRol : ServiceRol.listar()) {
										// Verifica si el Rol actual es igual al Rol de oUsuarioAEditar
										String opcionSeleccionada = (oUsuarioAEditar != null
										&& oRol.getDescripcion().equals(oUsuarioAEditar.getRol().getDescripcion())) ? "selected" : "";
									%>

									<option value="<%=oRol.getDescripcion()%>"
										<%=opcionSeleccionada%>><%=oRol.getDescripcion()%></option>


									<%
									}
									%>
							</select>
							</label> <label id="areaLabel" style="display: none;">Área <select
								name="area">
									<option selected>Seleccione su área</option>
									<%
									if (oUsuarioAEditar.getRol().getDescripcion().equals("Tutor")
											|| oUsuarioAEditar.getRol().getDescripcion().equals("Encargado")) {
										String opcionDeUsuario = ServiceTutor.getByDocumento(oUsuarioAEditar.getDocumento().toString()).getArea()
										.getDescripcion();
										for (AreaDTO oArea : ServiceArea.listar()) {

											// Verifica si el Area actual es igual al Area de oUsuarioAEditar
											String opcionSeleccionada = (oUsuarioAEditar != null && oArea.getDescripcion().equals(opcionDeUsuario)
											? "selected"
											: "");
									%>

									<option value="<%=oArea.getDescripcion()%>"
										<%=opcionSeleccionada%>><%=oArea.getDescripcion()%></option>
									<%
									}
									}
									%>
							</select> <%
 if (request.getSession().getAttribute("errorArea") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorArea")%></span>
								<%
								}
								%>
							</label> <label id="semestreLabel" style="display: none;">Semestre
								<select name="semestre">
									<option selected>Seleccione su semestre</option>

									<%
									if (oUsuarioAEditar.getRol().getDescripcion().equals("Estudiante")) {
										int opcionUsuario = ServiceEstudiante.getByDocumento(oUsuarioAEditar.getDocumento().toString()).getSemestre()
										.intValue();
										for (int i = 1; i <= 8; i++) {
											String opcionSeleccionada = (oUsuarioAEditar != null && i == opcionUsuario) ? "selected" : "";
									%>
									<option value="<%=i%>" <%=opcionSeleccionada%>><%=i%></option>
									<%
									}
									}
									%>

							</select> <%
 if (request.getSession().getAttribute("errorSemestre") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorSemestre")%></span>
								<%
								}
								%>
							</label> <label id="generacionLabel" style="display: none;">Generación
								<select name="generacion">
									<option selected>Seleccione su generación</option>

									<%
									if (oUsuarioAEditar.getRol().getDescripcion().equals("Estudiante")) {
										int opcionUsuario = Integer
										.parseInt(ServiceEstudiante.getByDocumento(oUsuarioAEditar.getDocumento().toString()).getGeneracion());
										for (int i = 2016; i <= 2024; i++) {
											String opcionSeleccionada = (oUsuarioAEditar != null && i == opcionUsuario) ? "selected" : "";
									%>
									<option value="<%=i%>" <%=opcionSeleccionada%>><%=i%></option>
									<%
									}
									}
									%>

							</select> <%
 if (request.getSession().getAttribute("errorGeneracion") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorGeneracion")%></span>
								<%
								}
								%>
							</label>

						</div>

						<div class="btnContenido">
							<button type="submit">Editar</button>
						</div>
					</form>

				</div>
			</section>

		</main>

		<!-- Pie de página -->
		<jsp:include page="/components/layout/footer/index.jsp" />
	</div>
</body>
<script src="/Proyecto-PInfra/utils/script/localidades.js"></script>
<script src="/Proyecto-PInfra/utils/script/mostrarCampos.js"></script>
<script
	src="/Proyecto-PInfra/utils/script/cargarLocalidadSeleccionada.js"></script>
<script>

document.addEventListener("DOMContentLoaded", function() {
    mostrarCampos();
    cargarLocalidades();
    cargarLocalidadSeleccionada('<%=oUsuarioAEditar.getLocalidad().getNombre()%>');
					});
</script>
</html>