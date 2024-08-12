<%@page import="com.cliente.services.ServiceEstadoConstancia"%>
<%@page import="com.cliente.dto.EstadoConstanciaDTO"%>
<%@page import="com.cliente.dto.UsuarioDTO"%>
<%@page import="com.cliente.services.ServiceEstudiante"%>
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
UsuarioDTO usuarioLogueado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogueado");
ServiceJWT.comprobarSesion(request, response, "ListarMisConstancia");
%>

<link rel="stylesheet" type="text/css" href="/Proyecto-PInfra/style.css">
<link rel="stylesheet" type="text/css"
	href="/Proyecto-PInfra/pages/constancias/listar/style.css">
<meta name="description"
	content="Página de gestión de constancias de UTEC. Gestiona tus constancias">
<meta name="viewport" content="width=device-width">
<link rel="icon" type="image/ico"
	href="/Proyecto-PInfra/utils/img/faviconapp.ico">
<title>Listar mis solicitudes de constancias - UTEC</title>
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
			<section class="misConstanciasContenido">
				<h2>Mis Solicitudes de Constancia</h2>

				<%
				long idEstudiante = ServiceEstudiante.getByDocumento(usuarioLogueado.getDocumento().toString()).getIdEstudiante();
				ArrayList<Constancia> listaDeConstancias = ServiceConstancia.listarConstanciasEstudiante(idEstudiante);

				if (listaDeConstancias == null || listaDeConstancias.size() == 0) {
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
								String estado = ServiceEstadoConstancia.getEstadoConstancia(oConstancia.getIdConstancia());
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
								<td><%=estado%></td>
								<td><%=analista%></td>
								<td>
									<div>
										<%
										if (estado.equals("Ingresado")) {
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
												type="hidden" name="tipo" value="Solicitud">
										</form>
										<form name="eliminar"
											action="/Proyecto-PInfra/SvEliminarConstancia" method="POST">
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
											<input type="hidden" name="idConstancia"
												value="<%=oConstancia.getIdConstancia()%>">
										</form>
										<%
										}
										%>
										<%
										if (estado.equals("En proceso")) {
										%>
										<form name="ver"
											action="/Proyecto-PInfra/SvEditarSolicitud" method="GET">
											<button class="btnEditar" type="submit">
												<svg xmlns="http://www.w3.org/2000/svg" width="20"
													height="20" viewBox="0 0 24 24" fill="none"
													stroke="currentColor" stroke-width="2"
													stroke-linecap="round" stroke-linejoin="round"
													class="icon icon-tabler icons-tabler-outline icon-tabler-info-circle">
													<path stroke="none" d="M0 0h24v24H0z" fill="none" />
													<path d="M3 12a9 9 0 1 0 18 0a9 9 0 0 0 -18 0" />
													<path d="M12 9h.01" />
													<path d="M11 12h1v4h1" /></svg>
											</button>
											<input type="hidden" name="idSolicitud"
												value="<%=oConstancia.getIdConstancia()%>"> <input
												type="hidden" name="tipo" value="Ver">
										</form>
										<%
										}
										%>
										<%
										if (estado.equals("Finalizado")) {
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
				%>
			</section>
		</main>

		<!-- Pie de página -->
		<jsp:include page="/components/layout/footer/index.jsp" />
	</div>
</body>
</html>