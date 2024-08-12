|<%@page import="com.cliente.services.ServiceEstadoReclamo"%>
<%@page import="java.util.List"%>
<%@page import="com.cliente.dto.EstadoReclamoDTO"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.NoSuchElementException"%>
<%@page import="com.servidor.entidades.Evento"%>
<%@page import="com.cliente.services.ServiceEvento"%>
<%@page import="com.cliente.dto.EventoDTO"%>
<%@page import="com.cliente.services.ServiceEstudiante"%>
<%@page import="com.cliente.dto.UsuarioDTO"%>
<%@page import="com.cliente.dto.ReclamoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cliente.services.ServiceReclamo"%>
<%@page import="com.cliente.services.ServiceJWT"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%
ServiceJWT.comprobarSesion(request, response, "Reclamos");
request.getSession().removeAttribute("reclamoAEditar");
%>

<link rel="stylesheet" type="text/css" href="/Proyecto-PInfra/style.css">
<link rel="stylesheet" type="text/css"
	href="/Proyecto-PInfra/pages/reclamos/style.css">
<meta name="description"
	content="Página de gestión de constancias de UTEC. Gestiona tus reclamoss">
<meta name="viewport" content="width=device-width">
<link rel="icon" type="image/ico"
	href="/Proyecto-PInfra/utils/img/faviconapp.ico">
<title>Gestión de Reclamos - UTEC</title>
</head>
<body>
	<!-- Modal de confirmación de acción -->
	<jsp:include page="/components/modal/index.jsp" />
	<div class="app">
		<!-- Encabezado de la página -->
		<jsp:include page="/components/layout/nav/index.jsp" />
		<main class="contenido">
			<%
			String token = ServiceJWT.getRol(request);
			if (token.equals("Analista")) {
			%>
			<section class="reclamosContenido">
				<h2 class="tituloReclamos">Reclamos</h2>
				<div class="tableContenido">
					<table>
						<thead>
							<tr>
								<th class="comienzo">Título</th>
								<th>Detalle</th>
								<th>Fecha</th>
								<th>Evento</th>
								<th>Estado</th>
								<th>Estudiante</th>
								<th class="fin">Acciones</th>
							</tr>
						</thead>
						<tbody>
							<%
							ArrayList<ReclamoDTO> listaReclamos = ServiceReclamo.listar();
							for (ReclamoDTO oReclamo : listaReclamos) {

								String detalleEstu = ServiceEstadoReclamo.getDetalleDelEstudiante(oReclamo.getIdReclamo());
								String fechaEmision = ServiceEstadoReclamo.getFechaEmision(oReclamo.getIdReclamo());
								String estadoActual = ServiceEstadoReclamo.getEstadoActual(oReclamo.getIdReclamo());
							%>
							<tr>
								<td><%=oReclamo.getDetalle()%></td>
								<td><%=detalleEstu%></td>
								<td><%=fechaEmision%></td>
								<td><%=oReclamo.getEvento().getTitulo()%></td>
								<td><%=estadoActual%></td>
								<td><%=oReclamo.getEstudiante().getoUsuario().getNombreCompleto()%></td>
								<td style="width: 70px;">
									<div>
										<%
										if (!estadoActual.equals("Finalizado")) {
										%>
										<form name="editar"
											action="/Proyecto-PInfra/SvNavegarEstadoReclamo" method="GET">
											<button class="btnEditar" type="submit">
												<svg xmlns="http://www.w3.org/2000/svg"
													class="icon icon-tabler icon-tabler-edit" width="20"
													height="20" viewBox="0 0 24 24" stroke-width="2"
													stroke="currentColor" fill="none" stroke-linecap="round"
													stroke-linejoin="round">
													<path stroke="none" d="M0 0h24v24H0z" fill="none" />
													<path
														d="M7 7h-1a2 2 0 0 0 -2 2v9a2 2 0 0 0 2 2h9a2 2 0 0 0 2 -2v-1" />
													<path
														d="M20.385 6.585a2.1 2.1 0 0 0 -2.97 -2.97l-8.415 8.385v3h3l8.385 -8.415z" />
													<path d="M16 5l3 3" /></svg>
											</button>
											<input type="hidden" name="idReclamo"
												value="<%=oReclamo.getIdReclamo()%>">
										</form>
										<%
										}
										%>
									</div>
								</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
			</section>
			<%
			} else if (token.equals("Estudiante")) {
			%>
			<section class="reclamosContenido">
				<h2 class="tituloReclamos">Solicitar un reclamo</h2>
				<div class="formulario">
					<form action="/Proyecto-PInfra/SvCrearReclamo" method="POST">
						<label> Título <input type="text"
							placeholder="Ingrese un título..." name="titulo" /> <%
 if (request.getSession().getAttribute("errorTituloReclamo") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorTituloReclamo")%></span>
							<%
							}
							%>
						</label> <label> Detalle <textarea name="detalle" id="detalle"
								rows="4" cols="50" placeholder="Ingrese el detalle..."></textarea>
							<%
							if (request.getSession().getAttribute("errorDetalleReclamo") != null) {
							%> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorDetalleReclamo")%></span>
							<%
							}
							%>

						</label> <label> Nombre del evento <select id="selectEvento"
							name="evento">
								<option value="default">Seleccione un evento</option>
								<%
								ArrayList<Evento> listaDeEventos = ServiceEvento.listarEventos();
								for (Evento oEvento : listaDeEventos) {
								%>
								<option value="<%=oEvento.getIdEvento()%>"><%=oEvento.getTitulo()%></option>
								<%
								}
								%>
						</select> <%
 if (request.getSession().getAttribute("errorEventoReclamo") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorEventoReclamo")%></span>
							<%
							}
							%>
						</label>
						<button class="btnCrear" id="btnCrear">Crear</button>
					</form>
				</div>

				<%
				HttpSession sessionActual = request.getSession(false); // No crear una nueva sesión si no existe
				UsuarioDTO usuarioLogueado = (UsuarioDTO) sessionActual.getAttribute("usuarioLogueado");
				String filtro = Long
						.toString(ServiceEstudiante.getByDocumento(usuarioLogueado.getDocumento().toString()).getIdEstudiante());
				ArrayList<ReclamoDTO> listaReclamos = ServiceReclamo.listarFiltro(filtro);
				if (listaReclamos == null || listaReclamos.size() == 0) {
				%>
				<div class="noReclamos">
					<p>No tienes solicitudes de reclamos</p>
				</div>

				<%
				} else {
				%>
				<h2 class="tituloReclamos">Mis Reclamos</h2>
				<div class="tableContenido">
					<table>
						<thead>
							<tr>
								<th class="comienzo">Título</th>
								<th>Detalle</th>
								<th>Fecha</th>
								<th>Evento</th>
								<th>Estado</th>
								<th class="fin">Acciones</th>
							</tr>
						</thead>
						<tbody>
							<%
							for (ReclamoDTO oReclamo : listaReclamos) {
								String detalleEstu = ServiceEstadoReclamo.getDetalleDelEstudiante(oReclamo.getIdReclamo());
								String fechaEmision = ServiceEstadoReclamo.getFechaEmision(oReclamo.getIdReclamo());
								String estadoActual = ServiceEstadoReclamo.getEstadoActual(oReclamo.getIdReclamo());
							%>
							<tr>
								<td><%=oReclamo.getDetalle()%></td>
								<td><%=detalleEstu%></td>
								<td><%=fechaEmision%></td>
								<td><%=oReclamo.getEvento().getTitulo()%></td>
								<td><%=estadoActual%></td>
								<td>
									<div>
										<%
										if (estadoActual.equals("Ingresado")) {
										%>
										<form name="editar"
											action="/Proyecto-PInfra/SvNavegarEstadoReclamo" method="GET">
											<button class="btnEditar" type="submit">
												<svg xmlns="http://www.w3.org/2000/svg"
													class="icon icon-tabler icon-tabler-edit" width="20"
													height="20" viewBox="0 0 24 24" stroke-width="2"
													stroke="currentColor" fill="none" stroke-linecap="round"
													stroke-linejoin="round">
													<path stroke="none" d="M0 0h24v24H0z" fill="none" />
													<path
														d="M7 7h-1a2 2 0 0 0 -2 2v9a2 2 0 0 0 2 2h9a2 2 0 0 0 2 -2v-1" />
													<path
														d="M20.385 6.585a2.1 2.1 0 0 0 -2.97 -2.97l-8.415 8.385v3h3l8.385 -8.415z" />
													<path d="M16 5l3 3" /></svg>
											</button>
											<input type="hidden" name="idReclamo"
												value="<%=oReclamo.getIdReclamo()%>">
										</form>
										<form name="eliminar"
											action="/Proyecto-PInfra/SvEliminarReclamo" method="POST">
											<button class="btnEliminar" type="submit">
												<svg xmlns="http://www.w3.org/2000/svg"
													class="icon icon-tabler icon-tabler-trash-x-filled"
													width="20" height="20" viewBox="0 0 24 24" stroke-width="2"
													stroke="currentColor" fill="none" stroke-linecap="round"
													stroke-linejoin="round">
													<path stroke="none" d="M0 0h24v24H0z" fill="none" />
													<path
														d="M20 6a1 1 0 0 1 .117 1.993l-.117 .007h-.081l-.919 11a3 3 0 0 1 -2.824 2.995l-.176 .005h-8c-1.598 0 -2.904 -1.249 -2.992 -2.75l-.005 -.167l-.923 -11.083h-.08a1 1 0 0 1 -.117 -1.993l.117 -.007h16zm-9.489 5.14a1 1 0 0 0 -1.218 1.567l1.292 1.293l-1.292 1.293l-.083 .094a1 1 0 0 0 1.497 1.32l1.293 -1.292l1.293 1.292l.094 .083a1 1 0 0 0 1.32 -1.497l-1.292 -1.293l1.292 -1.293l.083 -.094a1 1 0 0 0 -1.497 -1.32l-1.293 1.292l-1.293 -1.292l-.094 -.083z"
														stroke-width="0" fill="currentColor" />
													<path
														d="M14 2a2 2 0 0 1 2 2a1 1 0 0 1 -1.993 .117l-.007 -.117h-4l-.007 .117a1 1 0 0 1 -1.993 -.117a2 2 0 0 1 1.85 -1.995l.15 -.005h4z"
														stroke-width="0" fill="currentColor" /></svg>
											</button>
											<input type="hidden" name="idReclamo"
												value="<%=oReclamo.getIdReclamo()%>">
										</form>
										<%
										}
										%>
									</div>
								</td>
							</tr>


							<%
							}
							%>
						</tbody>
					</table>
				</div>
			</section>
			<%
			}
			}
			%>
		</main>
		<!-- Pie de página -->
		<jsp:include page="/components/layout/footer/index.jsp" />
	</div>
</body>
</html>