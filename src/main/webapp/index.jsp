<%@page import="com.cliente.contexto.Fabrica"%>
<%@ page import="com.cliente.contexto.utils.CargarDatos"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
CargarDatos.empezar();
Fabrica.limpiarMensajesDeError(request.getSession());
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/Proyecto-PInfra/style.css">
<meta name="description"
	content="Página de gestión de constancias de UTEC. Solicita tus constancias y mantente al día.">
<meta name="viewport" content="width=device-width">
<link rel="icon" type="image/ico"
	href="/Proyecto-PInfra/utils/img/faviconapp.ico">
<title>Inicio - UTEC</title>
</head>

<body>
	<div class="app">
		<!-- Encabezado de la página -->
		<jsp:include page="/components/layout/nav/index.jsp" />

		<!-- Contenido de la página -->
		<main class="contenido">

			<section class="inicioContenido">
				<div class="informacion">
					<div class="informacionPrincipal">
						<h1>Tu acceso rápido y sencillo a documentos académicos</h1>
						<p>Nuestra plataforma está diseñada para simplificar tus
							trámites académicos. Olvídate de las complicaciones y papeleos.
							Aquí, te ofrecemos la manera más fácil y rápida de obtener tus
							constancias de prueba, transporte, créditos, exámenes y más.</p>
						<div>
							<a class="btnSaberMas" href="https://utec.edu.uy/es/"
								target="_blank">Saber más</a>
						</div>
					</div>
					<div class="estadistica">
						<div class="estadisticaItem">
							<span>3.455</span>
							<p>estudiantes matriculados en 2022</p>
						</div>
						<div class="estadisticaItem">
							<span>84% </span>
							<p>es la primera generación universitaria en su familia</p>
						</div>
						<div class="estadisticaItem">
							<span>100%</span>
							<p>de egresados con certificación de inglés</p>
						</div>
					</div>
				</div>

				<div>
					<img class="imageninicio"
						src="/Proyecto-PInfra/utils/img/imagenInicio.png"
						alt="Imagen de estudiantes">
				</div>
			</section>
		</main>

		<!-- Pie de página -->
		<footer class="pieDePagina">
			<p>Creado por</p>
			<img class="imagenEquipo"
				src="/Proyecto-PInfra/utils/img/error404.png" alt="Logo de Error404">
		</footer>
	</div>
</body>

</html>