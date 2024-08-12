<%@page import="com.cliente.dto.UsuarioDTO"%>
<%@page import="com.cliente.services.ServiceJWT"%>
<%@page import="com.cliente.services.ServiceUsuario"%>
<%@page import="com.cliente.services.ServiceUbicacion"%>
<%@page import="com.cliente.services.ServiceTutor"%>
<%@page import="com.servidor.entidades.Tutor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cliente.contexto.Fabrica"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Fabrica.limpiarMensajesDeError(request.getSession());
ServiceJWT.comprobarSesion(request, response, "Confirmacion");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/Proyecto-PInfra/style.css">
<link rel="stylesheet" type="text/css"
	href="/Proyecto-PInfra/pages/confirmacion/style.css">
<meta name="description"
	content="Página de gestión de constancias de UTEC. Confirma tus usuarios.">
<meta name="viewport" content="width=device-width">
<link rel="icon" type="image/ico"
	href="/Proyecto-PInfra/utils/img/faviconapp.ico">
<title>Confirmación de usuarios - UTEC</title>
</head>

<body>
	<!-- Modal de confirmación de acción -->
	<jsp:include page="/components/modal/index.jsp" />

	<div class="app">
		<!-- Encabezado de la página -->
		<jsp:include page="/components/layout/nav/index.jsp" />

		<!-- Contenido de la página -->
		<main class="contenido">

			<section class="confirmacionContenido">
				<h2>Usuarios por confirmar</h2>
				<%
				ArrayList<UsuarioDTO> listaDeUsuarios = ServiceUsuario.listarSinConfirmar("N");
				if (listaDeUsuarios.size() == 0 || listaDeUsuarios == null) {
				%>
				<div class="mensajeDeTablaVacia">
					<h4>No hay ningún usuario para confirmar</h4>
				</div>
				<%
				} else {
				%>
				<div class="tableContenido">
					<table>
						<thead>
							<tr>
								<th class="comienzo">Cédula</th>
								<th>Nombres</th>
								<th>Apellidos</th>
								<th>Mail Personal</th>
								<th>Mail Institucional</th>
								<th>Género</th>
								<th>Departamento</th>
								<th>Localidad</th>
								<th>ITR</th>
								<th>Teléfono</th>
								<th>Fecha de Nacimiento</th>
								<th>Rol</th>
								<th class="fin">Acciones</th>
							</tr>
						</thead>
						<tbody>
							<%
							for (UsuarioDTO oUsuario : listaDeUsuarios) {
							%>
							<tr>
								<td><%=oUsuario.getDocumento()%></td>
								<td><%=oUsuario.getNombres()%></td>
								<td><%=oUsuario.getApellidos()%></td>
								<td><%=oUsuario.getMailPersonal()%></td>
								<td><%=oUsuario.getMailInstitucional()%></td>
								<td><%=oUsuario.getGenero().getNombre()%></td>
								<td><%=oUsuario.getDepartamento().getNombre()%></td>
								<td><%=oUsuario.getLocalidad().getNombre()%></td>
								<td><%=oUsuario.getItr().getNombre()%></td>
								<td><%=oUsuario.getTelefono()%></td>
								<td><%=oUsuario.getFechaNacimiento()%></td>
								<td><%=oUsuario.getRol().getDescripcion()%></td>
								<td>
									<div>
										<form name="confirmar"
											action="/Proyecto-PInfra/SvConfirmarUsuario" method="POST">
											<button class="btnConfirmar" type="submit">
												<svg xmlns="http://www.w3.org/2000/svg"
													class="icon icon-tabler icon-tabler-circle-check-filled"
													width="20" height="20" viewBox="0 0 24 24" stroke-width="2"
													stroke="currentColor" fill="none" stroke-linecap="round"
													stroke-linejoin="round">
													<path stroke="none" d="M0 0h24v24H0z" fill="none" />
													<path
														d="M17 3.34a10 10 0 1 1 -14.995 8.984l-.005 -.324l.005 -.324a10 10 0 0 1 14.995 -8.336zm-1.293 5.953a1 1 0 0 0 -1.32 -.083l-.094 .083l-3.293 3.292l-1.293 -1.292l-.094 -.083a1 1 0 0 0 -1.403 1.403l.083 .094l2 2l.094 .083a1 1 0 0 0 1.226 0l.094 -.083l4 -4l.083 -.094a1 1 0 0 0 -.083 -1.32z"
														stroke-width="0" fill="currentColor" /></svg>
											</button>
											<input type="hidden" name="cedula"
												value="<%=oUsuario.getDocumento()%>">
										</form>
										<form name="noConfirmar"
											action="/Proyecto-PInfra/SvConfirmarUsuario" method="GET">
											<button class="btnNoConfirmar" type="submit">
												<svg xmlns="http://www.w3.org/2000/svg"
													class="icon icon-tabler icon-tabler-circle-x-filled"
													width="20" height="20" viewBox="0 0 24 24" stroke-width="2"
													stroke="currentColor" fill="none" stroke-linecap="round"
													stroke-linejoin="round">
													<path stroke="none" d="M0 0h24v24H0z" fill="none" />
													<path
														d="M17 3.34a10 10 0 1 1 -14.995 8.984l-.005 -.324l.005 -.324a10 10 0 0 1 14.995 -8.336zm-6.489 5.8a1 1 0 0 0 -1.218 1.567l1.292 1.293l-1.292 1.293l-.083 .094a1 1 0 0 0 1.497 1.32l1.293 -1.292l1.293 1.292l.094 .083a1 1 0 0 0 1.32 -1.497l-1.292 -1.293l1.292 -1.293l.083 -.094a1 1 0 0 0 -1.497 -1.32l-1.293 1.292l-1.293 -1.292l-.094 -.083z"
														stroke-width="0" fill="currentColor" /></svg>
											</button>
											<input type="hidden" name="cedula"
												value="<%=oUsuario.getDocumento()%>">
										</form>
									</div>
								</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
				<%
				}
				%>
			</section>

		</main>

		<!-- Pie de página -->
		<footer class="pieDePagina">
			<p>Creado por</p>
			<img class="imagenEquipo"
				src="/Proyecto-PInfra/utils/img/error404.png" alt="Logo de Error404">
		</footer>
	</div>
</body>

</html>