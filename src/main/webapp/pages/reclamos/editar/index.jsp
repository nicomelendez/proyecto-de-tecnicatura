<%@page import="com.cliente.services.ServiceEstadoReclamo"%>
<%@page import="com.servidor.entidades.Evento"%>
<%@page import="com.cliente.services.ServiceEvento"%>
<%@page import="com.servidor.entidades.Estado"%>
<%@page import="com.cliente.services.ServiceEstado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.servidor.entidades.Reclamo"%>
<%@page import="com.cliente.services.ServiceJWT"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%
ServiceJWT.comprobarSesion(request, response, "EditarReclamos");
%>

<link rel="stylesheet" type="text/css" href="/Proyecto-PInfra/style.css">
<link rel="stylesheet" type="text/css"
	href="/Proyecto-PInfra/pages/reclamos/editar/style.css">
<meta name="description"
	content="Página de gestión de constancias de UTEC. Gestiona tus reportes">
<meta name="viewport" content="width=device-width">
<link rel="icon" type="image/ico"
	href="/Proyecto-PInfra/utils/img/faviconapp.ico">
<title>Edición de Reclamos - UTEC</title>
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
			Reclamo oReclamoAEditar = (Reclamo) request.getSession().getAttribute("reclamoAEditar");

			if (token.equals("Analista")) {
				int ultimoIndice = oReclamoAEditar.getEstadosReclamos().size() - 1;
				String notaDelEstudiante = ServiceEstadoReclamo.getDetalleDelEstudiante(oReclamoAEditar.getIdReclamo());
				String notaDelAnalista = oReclamoAEditar.getEstadosReclamos().get(ultimoIndice).getDetalle();
			%>
			<section class="reportesContenidoAnalista">
				<h2>Editar reclamo</h2>
				<div class="modificarUnEstado">
					<div>
						<form action="/Proyecto-PInfra/SvEditarReclamoAnalista"
							method="POST">
							<h3
								style="text-align: center; font-size: 14px; margin-top: 18px;">Todos
								los campos son obligatorios</h3>
							<label> Detalle <textarea name="detalle" id="detalle"
									rows="4" cols="50" placeholder="Ingrese el detalle..."></textarea>
								<%
								if (request.getSession().getAttribute("errorDetalleReclamo") != null) {
								%> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorDetalleReclamo")%></span>
								<%
								}
								%>
							</label> <label class="comboBoxEstado"> Cambiar el estado <select
								id="selectEstado" name="estado">
									<option value="default">Seleccione un estado</option>
									<%
									ArrayList<Estado> listaDeEstados = ServiceEstado.listarEstados();
									for (Estado oEstado : listaDeEstados) {
										if (oReclamoAEditar.getEstadosReclamos().get(ultimoIndice).getEstado().getDescripcion().equals("Ingresado")
										&& !oEstado.getDescripcion().equals("Ingresado")) {
									%>
									<option value="<%=oEstado.getIdEstado()%>"><%=oEstado.getDescripcion()%></option>
									<%
									} else if (oReclamoAEditar.getEstadosReclamos().get(ultimoIndice).getEstado().getDescripcion().equals("En proceso")
											&& !oEstado.getDescripcion().equals("Ingresado") && !oEstado.getDescripcion().equals("En proceso")) {
									%>
									<option value="<%=oEstado.getIdEstado()%>"><%=oEstado.getDescripcion()%></option>
									<%
									}
									}
									%>
							</select> <%
 if (request.getSession().getAttribute("errorEstadoReclamo") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorEstadoReclamo")%></span>
								<%
								}
								%>
							</label>
							<div class="btns">
								<button class="btnEditar" id="btnEditar">Editar</button>
							</div>
						</form>
						<div class="btns">
							<form action="/Proyecto-PInfra/SvCancelarEditarReclamo"
								method="POST">
								<button class="btnCancelar" type="submit">Cancelar</button>
							</form>
						</div>
					</div>

					<table>

						<tbody>
							<tr>
								<td class="titleCard">Titulo</td>
								<td><%=oReclamoAEditar.getDetalle()%></td>
							<tr>
							<tr>
								<td class="titleCard">Detalle</td>
								<td><%=notaDelEstudiante%></td>
							<tr>
							<tr>
								<td class="titleCard">Estudiante</td>
								<td><%=oReclamoAEditar.getEstudiante().getUsuario().getNombreCompleto()%></td>
							<tr>
							<tr>
								<td class="titleCard">Fecha Emisión</td>
								<td><%=ServiceEstadoReclamo.getFechaEmision(oReclamoAEditar.getIdReclamo())%></td>
							<tr>
							<tr>
								<td class="titleCard">Evento</td>
								<td><%=oReclamoAEditar.getEvento().getTitulo()%></td>
							<tr>
							<tr>
								<td class="titleCard">Estado</td>
								<td><%=oReclamoAEditar.getEstadosReclamos().get(ultimoIndice).getEstado().getDescripcion()%></td>
							<tr>
							<tr>
								<td class="titleCard">Nota del analista</td>
								<td><%=notaDelAnalista.equals(notaDelEstudiante) ? "-" : notaDelAnalista%></td>
							<tr>
						</tbody>
					</table>
				</div>
			</section>
			<%
			} else if (token.equals("Estudiante")) {
			%>
			<section class="reportesContenidoEstudiante">
				<h2>Editar mi reclamo</h2>

				<form action="/Proyecto-PInfra/SvEditarReclamoEstudiante"
					method="POST">
					<h3 style="text-align: center; font-size: 14px; margin-top: 18px;">Todos
						los campos son obligatorios</h3>
					<label>Titulo <input type="text"
						placeholder="Ingrese un título..." name="titulo"
						value="<%=oReclamoAEditar.getDetalle()%>" /> <%
 if (request.getSession().getAttribute("errorTituloReclamo") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorTituloReclamo")%></span>
						<%
						}
						%>
					</label> <label> Detalle <textarea name="detalle" id="detalle"
							rows="4" cols="50" placeholder="Ingrese el detalle..."><%=oReclamoAEditar.getEstadosReclamos().get(0).getDetalle()%></textarea>
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
							ArrayList<Evento> listaEventos = ServiceEvento.listarEventos();
							for (Evento oEvento : listaEventos) {
								String seleccionado = oEvento.getTitulo().equals(oReclamoAEditar.getEvento().getTitulo()) ? "selected" : "";
							%>
							<option value="<%=oEvento.getIdEvento()%>" <%=seleccionado%>>
								<%=oEvento.getTitulo()%>
							</option>
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
					<div>
						<button type="submit">Editar</button>
					</div>
				</form>

				<div>
					<form action="/Proyecto-PInfra/SvCancelarEditarReclamo"
						method="POST">
						<button class="btnCancelar" type="submit">Cancelar</button>
					</form>
				</div>

			</section>
			<%
			}
			%>
		</main>
		<!-- Pie de página -->
		<jsp:include page="/components/layout/footer/index.jsp" />
	</div>
</body>
</html>