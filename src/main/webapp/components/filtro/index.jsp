<form class="formReactivar" action="/Proyecto-PInfra/SvListarActivos"
	method="POST">
	<div style="display: flex; flex-direction: column; gap: 8px; ">
		<select name="filtro">
			<option selected value="D">Seleccione un filtro</option>
			<option value="S">Activos</option>
			<option value="N">Inactivos</option>
		</select>
		<%
		if (request.getSession().getAttribute("errorFiltro") != null) {
		%>
		<span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorFiltro")%></span>
		<%
		}
		%>

	</div>
	<button type="submit" style="max-height: 40.8px;">Filtrar</button>
</form>