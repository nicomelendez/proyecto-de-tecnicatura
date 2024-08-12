
<%
if (request.getSession().getAttribute("mostrarNotificacion") != null) {
%>
<div id="notificacion" class="notificacion">
	<div class="notificacionContenido">
		<svg xmlns="http://www.w3.org/2000/svg"
			class="icon icon-tabler icon-tabler-circle-check" width="100"
			height="100" viewBox="0 0 24 24" stroke-width="2"
			stroke="currentColor" fill="none" stroke-linecap="round"
			stroke-linejoin="round">
			<path stroke="none" d="M0 0h24v24H0z" fill="none" />
			<path d="M12 12m-9 0a9 9 0 1 0 18 0a9 9 0 1 0 -18 0" />
			<path d="M9 12l2 2l4 -4" /></svg>
	</div>
	<h3><%=request.getSession().getAttribute("tituloNotificacion")%></h3>
	<p><%=request.getSession().getAttribute("descripcionNotificacion")%></p>
</div>
<%
}
%>