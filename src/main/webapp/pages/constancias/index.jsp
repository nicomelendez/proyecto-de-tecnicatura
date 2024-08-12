<%@page import="com.cliente.services.ServiceJWT"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%
ServiceJWT.comprobarSesion(request, response, "Constancias");
%>

<link rel="stylesheet" type="text/css" href="/Proyecto-PInfra/style.css">
<link rel="stylesheet" type="text/css"
	href="/Proyecto-PInfra/pages/constancias/style.css">
<meta name="description"
	content="Página de gestión de constancias de UTEC. Gestiona tus solicitudes de constancias">
<meta name="viewport" content="width=device-width">
<link rel="icon" type="image/ico"
	href="/Proyecto-PInfra/utils/img/faviconapp.ico">
<title>Gestión de Constancias - UTEC</title>
</head>
<body>

	<div class="app">

		<!-- Encabezado de la página -->
		<jsp:include page="/components/layout/nav/index.jsp" />

		<!-- Contenido de la página -->
		<main class="contenido">
			<!-- Modificar a gusto -->
			<section class="contenidoDeGestion">
				<%
				String token = ServiceJWT.getRol(request);
				if (token.equals("Analista")) {
				%>
				<div class="columnaIzquierda">
					<div class="informacionGestion">
						<h2 class="tituloGestion">
							Seleccione <span class="textoResaltado">una opción</span>
						</h2>
						<p class="descripcionGestion">Ahorre tiempo con una
							herramienta de gestión eficaz.</p>
					</div>
				</div>

				<div class="columnaDerecha">

					<ul class="opcionesGestion">
						<li class="opcionItem"><a class="opcionLink"
							href="/Proyecto-PInfra/pages/constancias/solicitudes/index.jsp">Solicitudes
								de Constancia</a></li>
						<li class="opcionItem"><a class="opcionLink"
							href="/Proyecto-PInfra/pages/constancias/tipos/index.jsp">Tipos
								de Constancias</a></li>
					</ul>
				</div>

				<%
				}
				%>

				<%
				if (token.equals("Estudiante")) {
				%>


				<div class="columnaIzquierda">
					<div class="informacionGestion">
						<h2 class="tituloGestion">
							Seleccione <span class="textoResaltado">una opción</span>
						</h2>
						<p class="descripcionGestion">Ahorre tiempo con una
							herramienta de gestión eficaz.</p>
					</div>
				</div>

				<div class="columnaDerecha">

					<ul class="opcionesGestion">
						<li class="opcionItem"><a class="opcionLink"
							href="/Proyecto-PInfra/pages/constancias/solicitar/index.jsp">Solicitar
								constancia</a></li>
						<li class="opcionItem"><a class="opcionLink"
							href="/Proyecto-PInfra/pages/constancias/listar/index.jsp">Mis
								solicitudes</a></li>
					</ul>
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