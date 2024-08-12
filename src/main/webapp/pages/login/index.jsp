<%@page import="com.cliente.contexto.Fabrica"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Fabrica.limpiarMensajesDeError(request.getSession());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/Proyecto-PInfra/style.css">
<link rel="stylesheet" type="text/css"
	href="/Proyecto-PInfra/pages/login/style.css">
<meta name="description"
	content="Página de gestión de constancias de UTEC. Inicia sesión y ponte al día de tus constanciass.">
<meta name="viewport" content="width=device-width">
<link rel="icon" type="image/ico"
	href="/Proyecto-PInfra/utils/img/faviconapp.ico">
<title>Inicio de sesión - UTEC</title>
</head>

<body>
	<div class="app">
		<!-- Encabezado de la página -->
		<jsp:include page="/components/layout/nav/index.jsp" />

		<!-- Contenido de la página -->
		<main class="contenido">
			<!-- Modificar a gusto -->
			<section class="loginContenido">
				<div class="columnaIzq"></div>

				<div class="columnaDer">
					<form action="/Proyecto-PInfra/Login" method="POST"
						class="formularioLogin">
						<img class="imagenapp"
							src="/Proyecto-PInfra/utils/img/logo-app.png"
							alt="Logo de la aplicación">
						<div class="">
							<h2>Iniciar sesión</h2>
							<%
							if (request.getAttribute("mensajeError") != null) {
							%>
							<p
								style="max-width: 300px; font-size: 10px; color: red; margin-top: 10px;"><%=request.getAttribute("mensajeError")%></p>
							<%
							}
							%>

						</div>
						<label> Nombre de Usuario <input type="text"
							name="nombreUsuario"
							placeholder="Ingrese su nombre de usuario...">
						</label> <label> Contraseña <input type="password" name="clave"
							placeholder="Ingrese su contraseña...">
						</label>
						<button type="submit">Ingresar</button>
						<p class="registrarse">
							¿No tienes una cuenta? <a
								href="/Proyecto-PInfra/pages/registro/index.jsp">Registrate
								aquí</a>
						</p>
					</form>
				</div>
			</section>
		</main>

		<!-- Pie de página -->
		<jsp:include page="/components/layout/footer/index.jsp" />
	</div>
</body>

</html>