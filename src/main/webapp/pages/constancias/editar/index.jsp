<%@page import="com.cliente.services.ServiceEstadoConstancia"%>
<%@page import="com.servidor.entidades.Constancia"%>
<%@page import="com.cliente.services.ServiceTipo"%>
<%@page import="com.servidor.entidades.Tipo"%>
<%@page import="com.cliente.services.ServiceEvento"%>
<%@page import="com.servidor.entidades.Evento"%>
<%@page import="com.cliente.services.ServiceJWT"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%
ServiceJWT.comprobarSesion(request, response, "Editar constancia");
%>

<link rel="stylesheet" type="text/css" href="/Proyecto-PInfra/style.css">
<link rel="stylesheet" type="text/css"
	href="/Proyecto-PInfra/pages/constancias/editar/style.css">
<meta name="description"
	content="Página de gestión de constancias de UTEC. Editar solicitud de constancia">
<meta name="viewport" content="width=device-width">
<link rel="icon" type="image/ico"
	href="/Proyecto-PInfra/utils/img/faviconapp.ico">
<title>Editar Solicitud de Constancia - UTEC</title>
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
			<section class="contenidoDeEditarConstancia">
				<h2>Editar solicitud de constancia</h2>
				<%
				Constancia oConstancia = (Constancia) request.getSession().getAttribute("oSolicitudAEditar");
				String notaDelEstudiante = ServiceEstadoConstancia.getNotaDelEstudiante(oConstancia.getIdConstancia());
				%>
				<form action="/Proyecto-PInfra/SvEditarSolicitud" method="POST">
					<h3 style="text-align: center; font-size: 14px; margin-top: 18px;">Todos
						los campos son obligatorios</h3>
					<label>Titulo <input type="text" name="titulo"
						value="<%=oConstancia.getDetalle()%>" /> <%
 if (request.getSession().getAttribute("errorTituloConstancia") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorTituloConstancia")%></span>
						<%
						}
						%>
					</label> <label>Detalle <textarea name="detalle"><%=notaDelEstudiante%></textarea>
						<%
						if (request.getSession().getAttribute("errorDetalleConstancia") != null) {
						%> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorDetalleConstancia")%></span>
						<%
						}
						%>
					</label> <label>Evento <select name="evento">
							<option>Seleccione un evento</option>
							<%
							for (Evento oEvento : ServiceEvento.listarEventos()) {
							%>

							<option value="<%=oEvento.getIdEvento()%>"
								<%=oEvento.getIdEvento() == oConstancia.getEvento().getIdEvento() ? "selected" : ""%>>
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
							<option value="default" selected>Seleccione un tipo de
								constancia</option>
							<%
							for (Tipo oTipo : ServiceTipo.listarTiposConstancias()) {
							%>

							<option value="<%=oTipo.getIdTipo()%>"
								<%=oTipo.getIdTipo() == oConstancia.getTipo().getIdTipo() ? "selected" : ""%>>
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
					<button class="btnEditar" type="submit">Editar</button>

				</form>
				<div class="btns">
					<form action="/Proyecto-PInfra/SvCancelarConstancia" method="POST">
						<input type="hidden" name="ruta" value="solicitud">
						<button class="btnCancelar" type="submit">Cancelar</button>
					</form>
				</div>
			</section>
		</main>

		<!-- Pie de página -->
		<jsp:include page="/components/layout/footer/index.jsp" />
	</div>
</body>
</html>