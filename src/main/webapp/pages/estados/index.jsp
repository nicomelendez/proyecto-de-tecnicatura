<%@page import="com.cliente.services.ServiceJWT"%>
<%@page import="com.cliente.services.ServiceEstado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.servidor.entidades.Estado"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%
ServiceJWT.comprobarSesion(request, response, "Estados");
String filtro = request.getSession().getAttribute("filtroActivo") == null
		|| request.getSession().getAttribute("filtroActivo").equals("S") ? "S" : "N";
%>

<link rel="stylesheet" type="text/css" href="/Proyecto-PInfra/style.css">
<link rel="stylesheet" type="text/css"
	href="/Proyecto-PInfra/pages/estados/style.css">
<meta name="description" content="Página de lista auxiliar de Estados.">
<meta name="viewport" content="width=device-width">
<link rel="icon" type="image/ico"
	href="/Proyecto-PInfra/utils/img/faviconapp.ico">
<title>Estados - UTEC</title>
</head>
<body>
	<!-- Modal de confirmación de acción -->
	<jsp:include page="/components/modal/index.jsp" />

	<div class="app">

		<!-- Encabezado de la página -->
		<jsp:include page="/components/layout/nav/index.jsp" />
		<main class="contenido">
			<section class="estadosContenido">
				<h2>Estados</h2>

				<div class="datosEstado">
					<div>
						<%
						Estado oEstadoEditar = request.getSession().getAttribute("estadoEditar") == null ? null
								: (Estado) request.getSession().getAttribute("estadoEditar");
						if (oEstadoEditar == null) {
						%>
						<div class="formularioDeEstado">
							<h2>Crear un estado</h2>
							<form action="/Proyecto-PInfra/SvCrearEstado" method="post">
								<h3
									style="text-align: center; font-size: 14px; margin-top: 18px;">Todos
									los campos son obligatorios</h3>
								<label>Nombre<input type="text" name="descripcion" /> <%
 if (request.getSession().getAttribute("errorEstadoDescripcion") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorEstadoDescripcion")%></span>
									<%
									}
									%>
								</label>
								<button class="btnCrear" type="submit">Crear</button>
							</form>
						</div>
						<%
						} else {
						%>
						<div class="formularioDeEstado">
							<h2>Editar estado</h2>
							<form action="/Proyecto-PInfra/SvEditarEstado" method="post">
								<h3
									style="text-align: center; font-size: 14px; margin-top: 18px;">Todos
									los campos son obligatorios</h3>
								<label>Nombre<input type="text" name="descripcion"
									value="<%=oEstadoEditar.getDescripcion()%>" /> <%
 if (request.getSession().getAttribute("errorEstadoDescripcion") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorEstadoDescripcion")%></span>
									<%
									}
									%>
								</label>
								<button class="btnCrear" type="submit">Editar</button>
							</form>
							<div class="cancelarForm">
								<form action="/Proyecto-PInfra/SvEliminarEstado" method="GET">
									<button class="btnEliminar" type="submit">Cancelar</button>
								</form>
							</div>
						</div>
						<%
						}
						%>

						<div>
							<div class="filtro">
								<jsp:include page="/components/filtro/index.jsp" />
							</div>

							<%
							ArrayList<Estado> listaDeEstados = ServiceEstado.listarPorActivo(filtro);

							if (listaDeEstados.size() == 0 || listaDeEstados == null) {
							%>
							<div class="mensajeDeTablaVacia">
								<h4>No hay ningún estado.</h4>
							</div>
							<%
							} else {
							%>
							<div class="tableContenido">
								<table>
									<thead>
										<tr>
											<th class="comienzo">Nombre</th>
											<th class="fin">Acciones</th>
										</tr>
									</thead>
									<tbody>
										<%
										for (Estado oEstado : listaDeEstados) {
										%>
										<tr>
											<td><%=oEstado.getDescripcion()%></td>
											<td>
												<%
												if (filtro.equals("S")) {
												%>
												<div>
													<form name="editar"
														action="/Proyecto-PInfra/SvCargarDatosDeEstado"
														method="GET">
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
														<input type="hidden" name="idEstado"
															value="<%=(oEstado.getIdEstado())%>">
													</form>

													<form name="eliminar"
														action="/Proyecto-PInfra/SvEliminarEstado" method="POST">
														<button class="btnEliminar" type="submit">
															<svg xmlns="http://www.w3.org/2000/svg"
																class="icon icon-tabler icon-tabler-trash-x-filled"
																width="20" height="20" viewBox="0 0 24 24"
																stroke-width="2" stroke="currentColor" fill="none"
																stroke-linecap="round" stroke-linejoin="round">
													<path stroke="none" d="M0 0h24v24H0z" fill="none" />
													<path
																	d="M20 6a1 1 0 0 1 .117 1.993l-.117 .007h-.081l-.919 11a3 3 0 0 1 -2.824 2.995l-.176 .005h-8c-1.598 0 -2.904 -1.249 -2.992 -2.75l-.005 -.167l-.923 -11.083h-.08a1 1 0 0 1 -.117 -1.993l.117 -.007h16zm-9.489 5.14a1 1 0 0 0 -1.218 1.567l1.292 1.293l-1.292 1.293l-.083 .094a1 1 0 0 0 1.497 1.32l1.293 -1.292l1.293 1.292l.094 .083a1 1 0 0 0 1.32 -1.497l-1.292 -1.293l1.292 -1.293l.083 -.094a1 1 0 0 0 -1.497 -1.32l-1.293 1.292l-1.293 -1.292l-.094 -.083z"
																	stroke-width="0" fill="currentColor" />
													<path
																	d="M14 2a2 2 0 0 1 2 2a1 1 0 0 1 -1.993 .117l-.007 -.117h-4l-.007 .117a1 1 0 0 1 -1.993 -.117a2 2 0 0 1 1.85 -1.995l.15 -.005h4z"
																	stroke-width="0" fill="currentColor" /></svg>
														</button>
														<input type="hidden" name="idEstado"
															value="<%=(oEstado.getIdEstado())%>">
													</form>

												</div> <%
 } else {
 %>
												<div style="display: flex; justify-content: center;">
													<form action="/Proyecto-PInfra/SvReactivarEstado"
														method="POST">
														<button type="submit" class="btnReactivar">
															<svg xmlns="http://www.w3.org/2000/svg" width="20"
																height="20" viewBox="0 0 24 24" fill="none"
																stroke="currentColor" stroke-width="2"
																stroke-linecap="round" stroke-linejoin="round"
																class="icon icon-tabler icons-tabler-outline icon-tabler-checklist">
															<path stroke="none" d="M0 0h24v24H0z" fill="none" />
															<path
																	d="M9.615 20h-2.615a2 2 0 0 1 -2 -2v-12a2 2 0 0 1 2 -2h8a2 2 0 0 1 2 2v8" />
															<path d="M14 19l2 2l4 -4" />
															<path d="M9 8h4" />
															<path d="M9 12h2" /></svg>
														</button>
														<input type="hidden" name="idEstado"
															value="<%=oEstado.getIdEstado()%>">
													</form>
												</div> <%
 }
 %>
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
						</div>
					</div>
			</section>
		</main>
		<!-- Pie de página -->
		<jsp:include page="/components/layout/footer/index.jsp" />
	</div>
</body>
</html>