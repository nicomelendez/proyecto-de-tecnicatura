<%@page import="com.cliente.dto.UsuarioDTO"%>
<%@page import="com.cliente.contexto.Fabrica"%>
<%@page import="com.cliente.services.ServiceJWT"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
HttpSession sessionActual = request.getSession(false); // No crear una nueva sesión si no existe
UsuarioDTO usuarioLogueado = (UsuarioDTO) sessionActual.getAttribute("usuarioLogueado");

ServiceJWT.comprobarSesion(request, response, "Gestion");
Fabrica.limpiarMensajesDeError(request.getSession());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/Proyecto-PInfra/style.css">
<link rel="stylesheet" type="text/css"
	href="/Proyecto-PInfra/pages/gestion/style.css">
<meta name="description"
	content="Página de gestión de constancias de UTEC. Crea tus usuarios y gestionalos de una manera sencilla.">
<meta name="viewport" content="width=device-width">
<link rel="icon" type="image/ico"
	href="/Proyecto-PInfra/utils/img/faviconapp.ico">
<title>Gestión de usuarios - UTEC</title>
</head>

<body>

	<div class="app">

		<!-- Encabezado de la página -->
		<jsp:include page="/components/layout/nav/index.jsp" />

		<!-- Contenido de la página -->
		<main class="contenido">
			<!-- Modificar a gusto -->
			<section class="contenidoDeGestion">
				<div class="columnaIzquierda">
					<div class="informacionGestion">
						<h2 class="tituloGestion">
							Seleccione el <span class="textoResaltado">tipo de usuario</span>
						</h2>
						<p class="descripcionGestion">Ahorre tiempo con una
							herramienta de gestión eficaz.</p>
					</div>
				</div>

				<div class="columnaDerecha">

					<ul class="opcionesGestion">
						<li class="opcionItem"><a class="opcionLink"
							href="/Proyecto-PInfra/pages/gestion/estudiantes/index.jsp">Estudiantes</a>
						</li>
						<li class="opcionItem"><a class="opcionLink"
							href="/Proyecto-PInfra/pages/gestion/analistas/index.jsp">Analistas</a>
						</li>
						<li class="opcionItem"><a class="opcionLink"
							href="/Proyecto-PInfra/pages/gestion/tutores/index.jsp">Tutores</a>
						</li>
					</ul>
				</div>
			</section>
		</main>

		<!-- Pie de página -->
		<jsp:include page="/components/layout/footer/index.jsp" />
	</div>
</body>

</html>