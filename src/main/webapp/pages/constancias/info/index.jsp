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
ServiceJWT.comprobarSesion(request, response, "Ver Estado constancia");
Constancia oConstancia = (Constancia) request.getSession().getAttribute("oSolicitudVer");

String fechaEmision = ServiceEstadoConstancia.getFechaEmision(oConstancia.getIdConstancia());
String notaDelEstudiante = ServiceEstadoConstancia.getNotaDelEstudiante(oConstancia.getIdConstancia());
String estado = ServiceEstadoConstancia.getEstadoConstancia(oConstancia.getIdConstancia());
String notaDelAnalista = ServiceEstadoConstancia.getNotaDelAnalista(oConstancia.getIdConstancia());
%>

<link rel="stylesheet" type="text/css" href="/Proyecto-PInfra/style.css">
<link rel="stylesheet" type="text/css"
	href="/Proyecto-PInfra/pages/constancias/info/style.css">
<meta name="description"
	content="Página de gestión de constancias de UTEC. Gestiona los estados de constancias">
<meta name="viewport" content="width=device-width">
<link rel="icon" type="image/ico"
	href="/Proyecto-PInfra/utils/img/faviconapp.ico">
<title>Ver estado de constancias - UTEC</title>
</head>
<body>

	<div class="app">

		<!-- Encabezado de la página -->
		<jsp:include page="/components/layout/nav/index.jsp" />

		<!-- Contenido de la página -->
		<main class="contenido">
			<!-- Modificar a gusto -->
			<section class="contenidoDeEstadoConstancia">
				<h2>Estado de la constancia</h2>
				<div class="modificarUnEstado">

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
								<td><%=notaDelAnalista%></td>
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