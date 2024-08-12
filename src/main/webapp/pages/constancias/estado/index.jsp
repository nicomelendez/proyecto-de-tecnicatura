<%@page import="com.cliente.services.ServiceEstadoConstancia"%>
<%@page import="com.servidor.entidades.Estado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cliente.services.ServiceEstado"%>
<%@page import="com.servidor.entidades.Constancia"%>
<%@page import="com.cliente.services.ServiceJWT"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%
ServiceJWT.comprobarSesion(request, response, "Estado constancia");
Constancia oConstancia = (Constancia) request.getSession().getAttribute("oSolicitudAEditar");

String fechaEmision = ServiceEstadoConstancia.getFechaEmision(oConstancia.getIdConstancia());
String notaDelEstudiante = ServiceEstadoConstancia.getNotaDelEstudiante(oConstancia.getIdConstancia());
String estado = ServiceEstadoConstancia.getEstadoConstancia(oConstancia.getIdConstancia());
String notaDelAnalista = ServiceEstadoConstancia.getNotaDelAnalista(oConstancia.getIdConstancia());
String detalle = request.getSession().getAttribute("detalle") == null ? null
		: (String) request.getSession().getAttribute("detalle");
String estadoNuevo = request.getSession().getAttribute("estado") == null ? null
		: (String) request.getSession().getAttribute("estado");
%>

<link rel="stylesheet" type="text/css" href="/Proyecto-PInfra/style.css">
<link rel="stylesheet" type="text/css"
	href="/Proyecto-PInfra/pages/constancias/estado/style.css">
<meta name="description"
	content="Página de gestión de constancias de UTEC. Gestiona los estados de constancias">
<meta name="viewport" content="width=device-width">
<link rel="icon" type="image/ico"
	href="/Proyecto-PInfra/utils/img/faviconapp.ico">
<title>Estado de Constancias - UTEC</title>
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
			<section class="contenidoDeEstadoConstancia">
				<h2>Editar estado de la constancia</h2>
				<div class="modificarUnEstado">
					<div>
						<form action="/Proyecto-PInfra/SvEditarEstadoConstancia"
							method="POST">
							<h3
								style="text-align: center; font-size: 14px; margin-top: 18px;">Todos
								los campos son obligatorios</h3>
							<label>Detalle <textarea name="detalle"><%=detalle == null ? "" : detalle%></textarea>
								<%
								if (request.getSession().getAttribute("errorDetalleConstancia") != null) {
								%> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorDetalleConstancia")%></span>
								<%
								}
								%>
							</label> <label class="comboBoxEstado"> Cambiar el estado <select
								id="selectEstado" name="estado">
									<option value="default">Seleccione un estado</option>
									<%
									ArrayList<Estado> listaDeEstados = ServiceEstado.listarEstados();
									for (Estado oEstado : listaDeEstados) {
										String seleccionado = "";
										if (estadoNuevo != null) {
											seleccionado = oEstado.getIdEstado() == Long.valueOf(estadoNuevo) ? "selected" : "";
										}

										if (estado.equals("Ingresado") && !oEstado.getDescripcion().equals("Ingresado")) {
									%>
									<option value="<%=oEstado.getIdEstado()%>" <%=seleccionado%>>
										<%=oEstado.getDescripcion()%></option>
									<%
									} else if (estado.equals("En proceso") && !oEstado.getDescripcion().equals("Ingresado")
											&& !oEstado.getDescripcion().equals("En proceso")) {
									%>
									<option value="<%=oEstado.getIdEstado()%>" <%=seleccionado%>>
										<%=oEstado.getDescripcion()%></option>
									<%
									}
									}
									%>
							</select> <%
 if (request.getSession().getAttribute("errorEstadoConstancia") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorEstadoConstancia")%></span>
								<%
								}
								%>
							</label>
							<div class="btns">
								<button class="btnEditar" id="btnEditar">Editar</button>
							</div>
						</form>
						<div class="btns">
							<form action="/Proyecto-PInfra/SvCancelarConstancia"
								method="POST">
								<input type="hidden" name="ruta" value="estado">
								<button class="btnCancelar" type="submit">Cancelar</button>
							</form>
						</div>
					</div>

					<table>

						<tbody>
							<tr>
								<td class="titleCard">Titulo</td>
								<td><%=oConstancia.getDetalle()%></td>
							<tr>
							<tr>
								<td class="titleCard">Detalle</td>
								<td><%=notaDelEstudiante%></td>
							<tr>
							<tr>
								<td class="titleCard">Estudiante</td>
								<td><%=oConstancia.getEstudiante().getUsuario().getNombreCompleto()%></td>
							<tr>
							<tr>
								<td class="titleCard">Fecha Emisión</td>
								<td><%=fechaEmision%></td>
							<tr>
							<tr>
								<td class="titleCard">Evento</td>
								<td><%=oConstancia.getEvento().getTitulo()%></td>
							<tr>
							<tr>
								<td class="titleCard">Estado</td>
								<td><%=estado%></td>
							<tr>
							<tr>
								<td class="titleCard">Nota del analista</td>
								<td><%=notaDelAnalista.equals(notaDelEstudiante) ? "-" : notaDelAnalista%></td>
							<tr>
						</tbody>
					</table>
				</div>
			</section>
		</main>

		<!-- Pie de página -->
		<jsp:include page="/components/layout/footer/index.jsp" />
	</div>
</body>
</html>