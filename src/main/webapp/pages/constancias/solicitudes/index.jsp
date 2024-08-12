<%@page import="com.cliente.contexto.Fabrica"%>
<%@page import="com.cliente.dto.EstadoConstanciaDTO"%>
<%@page import="com.cliente.services.ServiceEstadoConstancia"%>
<%@page import="com.cliente.services.ServiceConstancia"%>
<%@page import="com.servidor.entidades.Constancia"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cliente.services.ServiceJWT"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%
ServiceJWT.comprobarSesion(request, response, "SolicitudesConstancias");
String solicitudesDeUnEstudiante = request.getSession().getAttribute("solicitudesDeUnEstudiante") == null
		? null
		: (String) request.getSession().getAttribute("solicitudesDeUnEstudiante");
%>

<link rel="stylesheet" type="text/css" href="/Proyecto-PInfra/style.css">
<link rel="stylesheet" type="text/css"
	href="/Proyecto-PInfra/pages/constancias/solicitudes/style.css">
<meta name="description"
	content="Página de gestión de solicitudes de constancias de UTEC.">
<meta name="viewport" content="width=device-width">
<link rel="icon" type="image/ico"
	href="/Proyecto-PInfra/utils/img/faviconapp.ico">
<title>Solicitudes de Constancia - UTEC</title>
</head>
<body>
	<!-- Modal de confirmación de acción -->
	<jsp:include page="/components/modal/index.jsp" />

	<div class="app">

		<!-- Encabezado de la página -->
		<jsp:include page="/components/layout/nav/index.jsp" />

		<!-- Contenido de la página -->
		<main class="contenido">
			<!-- Modificar a gusto -->
			<section class="constanciasContenido">
				<h2>Solicitudes de Constancia</h2>

				<div class="buscadorContaniner">
					<form class="buscador"
						action="/Proyecto-PInfra/SvBuscarSolicitudes" method="POST">
						<label> Ingrese el documento del estudiante<input
							class="inputBuscador" name="documento" placeholder="Buscar..." />
							<%
							if (request.getSession().getAttribute("errorCedula") != null) {
							%> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorCedula")%></span>
							<%
							}
							%>
						</label>
						<div class="rangoDeFechas">
							<div>
								<select name="mes">
									<option value="default">Seleccione un mes</option>
									<%
									int mesNumero = 1;
									for (String mes : Fabrica.getMeses()) {
									%>
									<option value="<%=mesNumero%>"><%=mes%></option>
									<%
									mesNumero++;
									}
									%>
								</select>
								<%
								if (request.getSession().getAttribute("errorMes") != null) {
								%>
								<span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorMes")%></span>
								<%
								}
								%>
							</div>

							<div>
								<select name="anio">
									<option value="default">Seleccione un año</option>
									<%
									for (String anio : Fabrica.getAnios()) {
									%>
									<option value="<%=anio%>"><%=anio%></option>
									<%
									}
									%>
								</select>
								<%
								if (request.getSession().getAttribute("errorAnio") != null) {
								%>
								<span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorAnio")%></span>
								<%
								}
								%>
							</div>

						</div>
						<button class="btnBuscar" type="submit">Buscar</button>
					</form>
					<%
					if (solicitudesDeUnEstudiante != null) {
					%>
					<form action="/Proyecto-PInfra/SvBuscarSolicitudes" method="GET">
						<button class="btnLimpiar">Limpiar</button>
					</form>
					<%
					}
					%>
				</div>


				<%
				if (solicitudesDeUnEstudiante == null) {
					ArrayList<Constancia> listaDeConstancias = ServiceConstancia.listarConstancias();

					if (listaDeConstancias.size() == 0 || listaDeConstancias == null) {
				%>
				<div class="mensajeDeTablaVacia">
					<h4>No hay ninguna constancia</h4>
				</div>
				<%
				} else {
				%>
				<div class="tableContenido">
					<table>
						<thead>
							<tr>
								<th class="comienzo">Tipo</th>
								<th>Evento</th>
								<th>Detalle</th>
								<th>Fecha Emision</th>
								<th>Estudiante</th>
								<th>Documento del Estudiante</th>
								<th>Estado</th>
								<th>Analista</th>
								<th class="fin">Acciones</th>
							</tr>
						</thead>
						<tbody>
							<%
							for (Constancia oConstancia : listaDeConstancias) {
								EstadoConstanciaDTO estadoDeConstancia = oConstancia.getEstadoDeConstancia();
								String fechaEmision = ServiceEstadoConstancia.getFechaEmision(oConstancia.getIdConstancia());
								String notaDelEstudiante = ServiceEstadoConstancia.getNotaDelEstudiante(oConstancia.getIdConstancia());
								String estado = ServiceEstadoConstancia.getEstadoConstancia(oConstancia.getIdConstancia());
								String notaDelAnalista = ServiceEstadoConstancia.getNotaDelAnalista(oConstancia.getIdConstancia());
								String analista = "-";

								if (!estado.equals("Ingresado")) {
									analista = ServiceEstadoConstancia.getNombreDelAnalista(oConstancia.getIdConstancia());
								}
							%>
							<tr>
								<td><%=oConstancia.getTipo().getNombre()%></td>
								<td><%=oConstancia.getEvento().getTitulo()%></td>
								<td><%=oConstancia.getDetalle()%></td>
								<td><%=fechaEmision%></td>
								<td><%=oConstancia.getEstudiante().getUsuario().getNombreCompleto()%></td>
								<td><%=oConstancia.getEstudiante().getUsuario().getDocumento()%></td>
								<td><%=estado%></td>
								<td><%=analista%></td>
								<td>

									<div>
										<%
										if (estadoDeConstancia.getEstado().equals("Ingresado") || estadoDeConstancia.getEstado().equals("En proceso")) {
										%>
										<form name="editar"
											action="/Proyecto-PInfra/SvEditarSolicitud" method="GET">
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
											<input type="hidden" name="idSolicitud"
												value="<%=oConstancia.getIdConstancia()%>"> <input
												type="hidden" name="tipo" value="Estado">
										</form>
										<%
										}
										%>
										<%
										if (estadoDeConstancia.getEstado().equals("Finalizado")) {
										%>
										<div class="btnDescargarContenido">
											<button class="btnDescargar"
												id="generatePdfButton<%=oConstancia.getIdConstancia()%>">
												<svg xmlns="http://www.w3.org/2000/svg" width="20"
													height="20" viewBox="0 0 24 24" fill="none"
													stroke="currentColor" stroke-width="2"
													stroke-linecap="round" stroke-linejoin="round"
													class="icon icon-tabler icons-tabler-outline icon-tabler-download">
													<path stroke="none" d="M0 0h24v24H0z" fill="none" />
													<path d="M4 17v2a2 2 0 0 0 2 2h12a2 2 0 0 0 2 -2v-2" />
													<path d="M7 11l5 5l5 -5" />
													<path d="M12 4l0 12" /></svg>
											</button>

											<script>
        document.getElementById('generatePdfButton<%=oConstancia.getIdConstancia()%>').addEventListener('click', function () {
            fetch('http://localhost:8080/Proyecto-PInfra/api/constancia/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    idConstancia: <%=oConstancia.getIdConstancia()%> // Reemplaza '1' con el ID real de la constancia
                })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al generar el PDF');
                }
                return response.blob();
            })
            .then(blob => {
                const url = window.URL.createObjectURL(new Blob([blob]));
                const a = document.createElement('a');
                a.style.display = 'none';
                a.href = url;
                a.download = '<%=oConstancia.getTituloPdf()%>.pdf';
                document.body.appendChild(a);
                a.click();
                window.URL.revokeObjectURL(url);
            })
            .catch(error => console.error('Error:', error));
        });
    </script>
										</div>
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
				<%
				}
				} else {
				ArrayList<Constancia> lista = request.getSession().getAttribute("listaSolicitudesDeUnEstudiante") == null
						? null
						: (ArrayList<Constancia>) request.getSession().getAttribute("listaSolicitudesDeUnEstudiante");
				if (lista == null || lista.size() == 0) {
				%>
				<p>No hay constancias en ese periodo</p>

				<%
				} else {
				%>
				<div class="tableContenido">
					<table>
						<thead>
							<tr>
								<th class="comienzo">Tipo</th>
								<th>Evento</th>
								<th>Detalle</th>
								<th>Fecha Emision</th>
								<th>Estudiante</th>
								<th>Documento del Estudiante</th>
								<th>Estado</th>
								<th>Analista</th>
								<th class="fin">Acciones</th>
							</tr>
						</thead>
						<tbody>
							<%
							for (Constancia oConstancia : lista) {
								EstadoConstanciaDTO estadoDeConstancia = oConstancia.getEstadoDeConstancia();
								String fechaEmision = ServiceEstadoConstancia.getFechaEmision(oConstancia.getIdConstancia());
								String notaDelEstudiante = ServiceEstadoConstancia.getNotaDelEstudiante(oConstancia.getIdConstancia());
								String estado = ServiceEstadoConstancia.getEstadoConstancia(oConstancia.getIdConstancia());
								String notaDelAnalista = ServiceEstadoConstancia.getNotaDelAnalista(oConstancia.getIdConstancia());
								String analista = "-";

								if (!estado.equals("Ingresado")) {
									analista = ServiceEstadoConstancia.getNombreDelAnalista(oConstancia.getIdConstancia());
								}
							%>
							<tr>
								<td><%=oConstancia.getTipo().getNombre()%></td>
								<td><%=oConstancia.getEvento().getTitulo()%></td>
								<td><%=oConstancia.getDetalle()%></td>
								<td><%=fechaEmision%></td>
								<td><%=oConstancia.getEstudiante().getUsuario().getNombreCompleto()%></td>
								<td><%=oConstancia.getEstudiante().getUsuario().getDocumento()%></td>
								<td><%=estado%></td>
								<td><%=analista%></td>
								<td>

									<div>
										<%
										if (estadoDeConstancia.getEstado().equals("Ingresado") || estadoDeConstancia.getEstado().equals("En proceso")) {
										%>
										<form name="editar"
											action="/Proyecto-PInfra/SvEditarSolicitud" method="GET">
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
											<input type="hidden" name="idSolicitud"
												value="<%=oConstancia.getIdConstancia()%>"> <input
												type="hidden" name="tipo" value="Estado">
										</form>
										<%
										}
										%>
										<%
										if (estadoDeConstancia.getEstado().equals("Finalizado")) {
										%>
										<div class="btnDescargarContenido">
											<button class="btnDescargar"
												id="generatePdfButton<%=oConstancia.getIdConstancia()%>">
												<svg xmlns="http://www.w3.org/2000/svg" width="20"
													height="20" viewBox="0 0 24 24" fill="none"
													stroke="currentColor" stroke-width="2"
													stroke-linecap="round" stroke-linejoin="round"
													class="icon icon-tabler icons-tabler-outline icon-tabler-download">
													<path stroke="none" d="M0 0h24v24H0z" fill="none" />
													<path d="M4 17v2a2 2 0 0 0 2 2h12a2 2 0 0 0 2 -2v-2" />
													<path d="M7 11l5 5l5 -5" />
													<path d="M12 4l0 12" /></svg>
											</button>

											<script>
        document.getElementById('generatePdfButton<%=oConstancia.getIdConstancia()%>').addEventListener('click', function () {
            fetch('http://localhost:8080/Proyecto-PInfra/api/constancia/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    idConstancia: <%=oConstancia.getIdConstancia()%> // Reemplaza '1' con el ID real de la constancia
                })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al generar el PDF');
                }
                return response.blob();
            })
            .then(blob => {
                const url = window.URL.createObjectURL(new Blob([blob]));
                const a = document.createElement('a');
                a.style.display = 'none';
                a.href = url;
                a.download = '<%=oConstancia.getTituloPdf()%>.pdf';
                document.body.appendChild(a);
                a.click();
                window.URL.revokeObjectURL(url);
            })
            .catch(error => console.error('Error:', error));
        });
    </script>
										</div>
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
				<%
				}
				}
				%>
			</section>
		</main>

		<!-- Pie de página -->
		<jsp:include page="/components/layout/footer/index.jsp" />
	</div>
</body>
</html>