<%@page import="com.cliente.dto.UsuarioDTO"%>
<%@page import="com.cliente.contexto.Fabrica"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%
HttpSession sessionActual = request.getSession(false); // No crear una nueva sesión si no existe
UsuarioDTO usuarioLogueado = (UsuarioDTO) sessionActual.getAttribute("usuarioLogueado");
%>
<header class="encabezado">
	<a class="btnHome" href="/Proyecto-PInfra/index.jsp"> <img
		class="imagenUtec" src="/Proyecto-PInfra/utils/img/utec.png"
		alt="Logo de UTEC">
	</a>
	<nav class="menu">
		<%
		if (usuarioLogueado != null && usuarioLogueado.getRol().getDescripcion().equals("Analista")) {
		%>

		<ul class="links">
			<li><a href="/Proyecto-PInfra/pages/gestion/index.jsp">Gestión
					de usuarios</a></li>
			<li><a href="/Proyecto-PInfra/pages/confirmacion/index.jsp">Confirmación
					de usuarios <%
			if (!Fabrica.getCantidadSinConfirmar().equals("")) {
			%> <sup class="superindice"><%=Fabrica.getCantidadSinConfirmar()%></sup>
					<%
					}
					%>

			</a></li>
			<li><a href="/Proyecto-PInfra/pages/eventos/index.jsp">Eventos</a></li>
			<li><a href="/Proyecto-PInfra/pages/constancias/index.jsp">Constancias</a></li>
			<li><a href="/Proyecto-PInfra/pages/reclamos/index.jsp">Reclamos</a></li>
			<li><a href="/Proyecto-PInfra/pages/itrs/index.jsp">ITRs</a></li>
			<li><a href="/Proyecto-PInfra/pages/estados/index.jsp">Estados</a></li>
		</ul>


		<%
		} else if (usuarioLogueado != null && usuarioLogueado.getRol().getDescripcion().equals("Estudiante")) {
		%>
		<ul class="links">
			<li><a href="/Proyecto-PInfra/pages/constancias/index.jsp">Solicitud
					de Constancias</a></li>
			<li><a href="/Proyecto-PInfra/pages/reclamos/index.jsp">Reclamos</a></li>
		</ul>
		<%
		} else if (usuarioLogueado != null && usuarioLogueado.getRol().getDescripcion().equals("Tutor")) {
		%>
		<!-- Páginas a las que puede acceder un Tutor -->
		<%
		} else if (usuarioLogueado != null && usuarioLogueado.getRol().getDescripcion().equals("Encargado")) {
		%>
		<!-- Páginas a las que puede acceder un Tutor -->
		<%
		}
		%>
	</nav>
	<%
	if (usuarioLogueado != null) {
	%>
	<div class="btnSesion">
		<p style="font-size: 12px; font-weight: bold;"><%=usuarioLogueado.getNombreCompleto()%></p>
		<a class="btnPerfil" href="/Proyecto-PInfra/pages/perfil/index.jsp">
			<svg xmlns="http://www.w3.org/2000/svg"
				class="icon icon-tabler icon-tabler-user-edit" width="20"
				height="20" viewBox="0 0 24 24" stroke-width="2"
				stroke="currentColor" fill="none" stroke-linecap="round"
				stroke-linejoin="round">
				<path stroke="none" d="M0 0h24v24H0z" fill="none" />
				<path d="M8 7a4 4 0 1 0 8 0a4 4 0 0 0 -8 0" />
				<path d="M6 21v-2a4 4 0 0 1 4 -4h3.5" />
				<path
					d="M18.42 15.61a2.1 2.1 0 0 1 2.97 2.97l-3.39 3.42h-3v-3l3.42 -3.39z" /></svg>
		</a> <a class="btnCerrarSesion" href="/Proyecto-PInfra/SvCerrarSesion"><svg
				xmlns="http://www.w3.org/2000/svg"
				class="icon icon-tabler icon-tabler-logout" width="20" height="20"
				viewBox="0 0 24 24" stroke-width="2" stroke="currentColor"
				fill="none" stroke-linecap="round" stroke-linejoin="round">
						<path stroke="none" d="M0 0h24v24H0z" fill="none" />
						<path
					d="M14 8v-2a2 2 0 0 0 -2 -2h-7a2 2 0 0 0 -2 2v12a2 2 0 0 0 2 2h7a2 2 0 0 0 2 -2v-2" />
						<path d="M9 12h12l-3 -3" />
						<path d="M18 15l3 -3" /></svg></a>
	</div>
	<%
	} else {
	%>
	<div class="btnSesion">
		<a href="/Proyecto-PInfra/pages/login/index.jsp">Iniciar sesión</a> <a
			href="/Proyecto-PInfra/pages/registro/index.jsp">Registrarme</a>
	</div>
	<%
	}
	%>

</header>