<%@page import="com.cliente.services.ServiceTipo"%>
<%@page import="com.servidor.entidades.Tipo"%>
<%@page import="com.servidor.entidades.Evento"%>
<%@page import="com.cliente.services.ServiceEvento"%>
<%@page import="com.cliente.services.ServiceJWT"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%
ServiceJWT.comprobarSesion(request, response, "SolicitarConstancia");
%>

<link rel="stylesheet" type="text/css" href="/Proyecto-PInfra/style.css">
<link rel="stylesheet" type="text/css"
	href="/Proyecto-PInfra/pages/constancias/solicitar/style.css">
<meta name="description"
	content="Página de gestión de constancias de UTEC. Gestiona tus constancias">
<meta name="viewport" content="width=device-width">
<link rel="icon" type="image/ico"
	href="/Proyecto-PInfra/utils/img/faviconapp.ico">
<title>Solicitar constancia - UTEC</title>
</head>
<body>
	<jsp:include page="/components/notificacion/index.jsp" />
	<div class="app">
		<!-- Encabezado de la página -->
		<jsp:include page="/components/layout/nav/index.jsp" />
		<main class="contenido">
			<section class="constanciaContenido">
				<h2>Solicitar constancia</h2>
				<form action="/Proyecto-PInfra/SvCrearConstancia" method="POST">
					<h3 style="text-align: center; font-size: 14px; margin-top: 18px;">Todos
						los campos son obligatorios</h3>
					<label>Titulo <input type="text" name="titulo" /> <%
 if (request.getSession().getAttribute("errorTituloConstancia") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorTituloConstancia")%></span>
						<%
						}
						%>
					</label> <label>Detalle <textarea name="detalle"></textarea> <%
 if (request.getSession().getAttribute("errorDetalleConstancia") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorDetalleConstancia")%></span>
						<%
						}
						%>
					</label> <label>Evento <select name="evento">
							<option selected>Seleccione un evento</option>
							<%
							for (Evento oEvento : ServiceEvento.listarEventos()) {
							%>

							<option value="<%=oEvento.getIdEvento()%>">
								<%=oEvento.getTitulo()%>
							</option>

							<%
							}
							%>
					</select> <%
 if (request.getSession().getAttribute("errorEventoConstancia") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorEventoConstancia")%></span>
						<%
						}
						%>
					</label> <label>Tipo de constancia <select name="tipo">
							<option selected>Seleccione un tipo de constancia</option>
							<%
							for (Tipo oTipo : ServiceTipo.listarTiposConstancias()) {
							%>

							<option value="<%=oTipo.getIdTipo()%>">
								<%=oTipo.getNombre()%>
							</option>

							<%
							}
							%>
					</select> <%
 if (request.getSession().getAttribute("errorTipoConstancia") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorTipoConstancia")%></span>
						<%
						}
						%>
					</label>
					<button type="submit">Solicitar</button>


				</form>
			</section>
		</main>
		<!-- Pie de página -->
		<jsp:include page="/components/layout/footer/index.jsp" />
	</div>
</body>
<script>
	function cerrarNotificacion() {
		setTimeout(function() {
			var xhr = new XMLHttpRequest();
			xhr.open("POST", "/Proyecto-PInfra/SvCerrarNotificacion", true);
			xhr.send();
			var notificationElement = document.getElementById("notificacion");
			if (notificationElement) {
				location.reload();
			}

		}, 4000);
	}

	document.addEventListener("DOMContentLoaded", function() {
		cerrarNotificacion();
	});
</script>
</html>