<%@page import="com.cliente.dto.ItrDTO"%>
<%@page import="com.cliente.dto.DepartamentoDTO"%>
<%@page import="com.cliente.services.ServiceJWT"%>
<%@page import="com.cliente.services.ServiceUbicacion"%>
<%@page import="com.cliente.services.ServiceItr"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
ServiceJWT.comprobarSesion(request, response, "ITRS");
String filtro = request.getSession().getAttribute("filtroActivo") == null
		|| request.getSession().getAttribute("filtroActivo").equals("S") ? "S" : "N";
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/Proyecto-PInfra/style.css">
<link rel="stylesheet" type="text/css"
	href="/Proyecto-PInfra/pages/itrs/style.css">
<meta name="description"
	content="Página de gestión de constancias de UTEC. Solicita tus constancias y mantente al día.">
<meta name="viewport" content="width=device-width">
<link rel="icon" type="image/ico"
	href="/Proyecto-PInfra/utils/img/faviconapp.ico">
<title>ITRs - UTEC</title>
</head>

<body>
	<!-- Modal de confirmación de acción -->
	<jsp:include page="/components/modal/index.jsp" />

	<div class="app">
		<!-- Encabezado de la página -->
		<jsp:include page="/components/layout/nav/index.jsp" />

		<!-- Contenido de la página -->
		<main class="contenido">

			<section class="itrContenido">

				<div>
					<h2>Gestión de ITRs</h2>
				</div>

				<div class="crearUnItr">
					<%
					ItrDTO oItrEditarExiste = request.getSession().getAttribute("itrEditar") == null ? null
							: (ItrDTO) request.getSession().getAttribute("itrEditar");

					boolean mostrarForm = request.getSession().getAttribute("mostrarForm") == null ? false
							: (boolean) request.getSession().getAttribute("mostrarForm");

					if (!mostrarForm && oItrEditarExiste == null) {
					%>

					<form action="/Proyecto-PInfra/SvCrearItr" method="GET">
						<button class="btnCrear" type="submit">Crear</button>
					</form>

					<%
					} else if (mostrarForm) {
					%>

					<form action="/Proyecto-PInfra/SvCrearItr" method="POST">
						<label> Nombre <input type="text" name="nombreItr" /> <%
 if (request.getSession().getAttribute("errorNombreItr") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorNombreItr")%></span>
							<%
							}
							%>
						</label> <label> Departamento <select name="departamento">
								<option selected>Seleccione un departamento</option>
								<%
								for (DepartamentoDTO oDepartamento : ServiceUbicacion.listarDepartamentos()) {
								%>

								<option value="<%=oDepartamento.getNombre()%>"><%=oDepartamento.getNombre()%></option>

								<%
								}
								%>
						</select> <%
 if (request.getSession().getAttribute("errorDepartamentoItr") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorDepartamentoItr")%></span>
							<%
							}
							%>
						</label>
						<button class="btnCrear" type="submit">Crear</button>
					</form>
					<div class="cancelarForm">
						<form action="/Proyecto-PInfra/SvEliminarItr" method="GET">
							<button class="btnEliminar" type="submit">Cancelar</button>
						</form>
					</div>
					<%
					}
					%>
				</div>

				<div class="crearUnItr">
					<%
					ItrDTO oItrEditar = request.getSession().getAttribute("itrEditar") == null ? null
							: (ItrDTO) request.getSession().getAttribute("itrEditar");

					if (oItrEditar != null) {
					%>

					<form action="/Proyecto-PInfra/SvEditarItr" method="POST">

						<label> Nombre <input type="text" name="nombreItrEditar"
							value="<%=oItrEditar.getNombre()%>" /> <%
 if (request.getSession().getAttribute("errorNombreItr") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorNombreItr")%></span>
							<%
							}
							%>
						</label> <label> Departamento <select name="departamentoEditar">
								<option>Seleccione un departamento</option>
								<%
								for (DepartamentoDTO oDepartamento : ServiceUbicacion.listarDepartamentos()) {
								%>

								<option value="<%=oDepartamento.getNombre()%>"
									<%=oDepartamento.getNombre().equals(oItrEditar.getDepartamento().getNombre()) ? "selected" : ""%>><%=oDepartamento.getNombre()%></option>

								<%
								}
								%>
						</select> <%
 if (request.getSession().getAttribute("errorDepartamentoItr") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorDepartamentoItr")%></span>
							<%
							}
							%>
						</label>
						<button class="btnCrear" type="submit">Editar</button>
					</form>

					<div class="cancelarForm">
						<form action="/Proyecto-PInfra/SvEliminarItr" method="GET">
							<button class="btnEliminar" type="submit">Cancelar</button>
						</form>
					</div>
					<%
					}
					%>
				</div>
				<jsp:include page="/components/filtro/index.jsp" />

				<%
				ArrayList<ItrDTO> listaDeItrs = ServiceItr.listarActivoFiltro(filtro);

				if (listaDeItrs.size() == 0 || listaDeItrs == null) {
				%>
				<div class="mensajeDeTablaVacia">
					<h4>No hay ningún ITR</h4>
				</div>
				<%
				} else {
				%>
				<div class="tableContenido">
					<table>
						<thead>
							<tr>
								<th class="comienzo">Nombre</th>
								<th>Departamento</th>
								<th class="fin">Acciones</th>
							</tr>
						</thead>
						<tbody>
							<%
							for (ItrDTO oItr : listaDeItrs) {
							%>
							<tr>
								<td><%=oItr.getNombre()%></td>
								<td><%=oItr.getDepartamento().getNombre()%></td>
								<td class="btnAcciones">
									<%
									if (filtro.equals("S")) {
									%>
									<div>
										<form name="editar" action="/Proyecto-PInfra/SvEditarItr"
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
											<input type="hidden" name="idItr"
												value="<%=oItr.getIdItr()%>">
										</form>
										<form name="eliminar" action="/Proyecto-PInfra/SvEliminarItr"
											method="POST">
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
											<input type="hidden" name="idItr"
												value="<%=oItr.getIdItr()%>">
										</form>
									</div> <%
 } else {
 %>
									<div>
										<form name="reactivar"
											action="/Proyecto-PInfra/SvReactivarItr" method="POST">
											<button class="btnReactivar" type="submit">
												<svg xmlns="http://www.w3.org/2000/svg"
													class="icon icon-tabler icon-tabler-home-check" width="20"
													height="20" viewBox="0 0 24 24" stroke-width="2"
													stroke="currentColor" fill="none" stroke-linecap="round"
													stroke-linejoin="round">
													<path stroke="none" d="M0 0h24v24H0z" fill="none" />
													<path d="M9 21v-6a2 2 0 0 1 2 -2h2a2 2 0 0 1 2 2" />
													<path
														d="M19 13.488v-1.488h2l-9 -9l-9 9h2v7a2 2 0 0 0 2 2h4.525" />
													<path d="M15 19l2 2l4 -4" /></svg>
											</button>
											<input type="hidden" name="idItr"
												value="<%=oItr.getIdItr()%>">
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
			</section>

		</main>

		<!-- Pie de página -->
		<jsp:include page="/components/layout/footer/index.jsp" />
	</div>

</body>
</html>