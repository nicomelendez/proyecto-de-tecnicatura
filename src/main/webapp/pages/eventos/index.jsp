<%@page import="com.cliente.dto.ItrDTO"%>
<%@page import="com.cliente.services.ServiceItr"%>
<%@page import="com.servidor.entidades.Itr"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cliente.services.ServiceTutor"%>
<%@page import="com.cliente.dto.TutorDTO"%>
<%@page import="com.cliente.services.ServiceJWT"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%
ServiceJWT.comprobarSesion(request, response, "Eventos");
%>

<link rel="stylesheet" type="text/css" href="/Proyecto-PInfra/style.css">
<link rel="stylesheet" type="text/css"
	href="/Proyecto-PInfra/pages/eventos/style.css">
<meta name="description"
	content="Página de gestión de constancias de UTEC. Gestiona tus eventos">
<meta name="viewport" content="width=device-width">
<link rel="icon" type="image/ico"
	href="/Proyecto-PInfra/utils/img/faviconapp.ico">
<title>Gestión de Eventos - UTEC</title>
</head>
<body>

	<!-- Notificación -->
	<jsp:include page="/components/notificacion/index.jsp" />

	<div class="app">
		<!-- Encabezado de la página -->
		<jsp:include page="/components/layout/nav/index.jsp" />

		<main class="contenido">
			<section class="eventoContenido">

				<div>
					<h2>Eventos</h2>
				</div>

				<div class="crearUnEvento">
					<%
					ArrayList<TutorDTO> listaDeTutores = ServiceTutor.listarActivoFiltro("S");
					ArrayList<String> listaDeTutoresSeleccionados = request.getSession().getAttribute("tutoresSeleccionados") != null
							? (ArrayList<String>) request.getSession().getAttribute("tutoresSeleccionados")
							: new ArrayList<String>();
					if (listaDeTutores == null || listaDeTutores.size() == 0) {
					%>
					<label>Algo salio mal</label>
					<%
					} else {
					%>
					<form id="formularioEvento" action="/Proyecto-PInfra/SvCrearEvento"
						method="POST">
						<h3 style="text-align: center; font-size: 14px; margin-top: 18px;">Todos
							los campos son obligatorios</h3>
						<label> Titulo del evento <input type="text"
							name="tituloEvento" /> <%
 if (request.getSession().getAttribute("errorTituloEvento") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorTituloEvento")%></span>
							<%
							}
							%>
						</label>
						<div class="rangoDeFechas">
							<label> Fecha y hora de inicio: <input
								type="datetime-local" name="fechaInicio"> <%
 if (request.getSession().getAttribute("errorFechaInicioEvento") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorFechaInicioEvento")%></span>
								<%
								}
								%>
							</label> <label> Fecha y hora de fin: <input
								type="datetime-local" name="fechaFin"> <%
 if (request.getSession().getAttribute("errorFechaFinEvento") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorFechaFinEvento")%></span>
								<%
								}
								%>
							</label>
						</div>
						<%
						if (request.getSession().getAttribute("errorFechaEvento") != null) {
						%>
						<span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorFechaEvento")%></span>
						<%
						}
						%>
						<div>
							<label> ITR <select id="selectITR" name="itrEvento">
									<option value="default">Seleccione un ITR</option>
									<%
									ArrayList<ItrDTO> listaDeItrs = ServiceItr.listar();
									for (ItrDTO oItr : listaDeItrs) {
									%>
									<option value="<%=oItr.getIdItr()%>"><%=oItr.getNombre()%></option>
									<%
									}
									%>
							</select> <%
 if (request.getSession().getAttribute("errorItrEvento") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorItrEvento")%></span>
								<%
								}
								%>
							</label>
						</div>

						<div class="asignarTutores">
							<h3>Asignar tutores</h3>
							<div class="selectorTutores">
								<label> Nombre del tutor <select id="selectTutores">
										<option value="default">Seleccione un tutor</option>
										<%
										for (TutorDTO oTutor : listaDeTutores) {
											if (!listaDeTutoresSeleccionados.contains(oTutor.getoUsuario().getDocumento().toString())) {
										%>
										<option value="<%=oTutor.getoUsuario().getDocumento()%>"><%=oTutor.getoUsuario().getNombreCompleto()%></option>
										<%
										}
										}
										%>
								</select> <%
 if (request.getSession().getAttribute("errorListaDeTutores") != null) {
 %> <span style="max-width: 300px; font-size: 10px; color: red;"><%=request.getSession().getAttribute("errorListaDeTutores")%></span>
									<%
									}
									%>
								</label>
								<button class="btnAsignar" id="btnAsignar">Asignar</button>

							</div>
						</div>
						<div class="tableContenido">
							<%
							if (listaDeTutoresSeleccionados.size() != 0) {
							%>
							<table>
								<thead>
									<tr>
										<th class="comienzo">Nombre completo</th>
										<th>Documento</th>
										<th class="fin"></th>
									</tr>
								</thead>
								<tbody>
									<%
									for (String documento : listaDeTutoresSeleccionados) {
									%>
									<tr>
										<td><%=ServiceTutor.getByDocumento(documento).getoUsuario().getNombreCompleto()%>
										</td>

										<td><%=ServiceTutor.getByDocumento(documento).getoUsuario().getDocumento().toString()%></td>

										<td>
											<button class="btnEliminar" type="button"
												onclick="eliminarTutor('<%=documento%>')">X</button>
										</td>
									</tr>


									<%
									}
									%>

								</tbody>

							</table>
							<%
							} else {
							%>
							<p class="noHayTutores">No hay tutores seleccionados</p>
							<%
							}
							%>

							<button class="btnCrear" type="submit">Crear</button>
					</form>
					<%
					}
					%>


				</div>
			</section>
		</main>

		<!-- Pie de página -->
		<jsp:include page="/components/layout/footer/index.jsp" />
	</div>
</body>

<script>
function eliminarTutor(documento) {
	// Llamada Fetch para eliminar el tutor
	fetch("/Proyecto-PInfra/SvEliminarTutor?documento=" + documento, {
		method: "POST"
	}).then(response => {
		// Manejar la respuesta del servidor si es necesario
		console.log("Tutor eliminado correctamente");
		// Recargar la página después de 1 segundo (1000 milisegundos)
		window.location.reload();
	}).catch(error => {
		console.error("Error al eliminar el tutor:", error);
	});
}


document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("btnAsignar").addEventListener("click", function(event) {
        event.preventDefault(); // Evita que el formulario se envíe antes de procesar el evento
        
        var select = document.getElementById("selectTutores");
        var tutorSeleccionado = select.value;
   
        if (tutorSeleccionado !== "default") {
            // Construir la URL con los datos a enviar como parámetros
            var url = "/Proyecto-PInfra/SvAsignarTutor?tutorSeleccionado=" + encodeURIComponent(tutorSeleccionado);
            
            // Enviar los datos al servidor utilizando Fetch API
            fetch(url, {
                method: "POST"
            }).then(response => {
                // Manejar la respuesta del servidor si es necesario
                window.location.reload();
            }).catch(error => {
                console.error("Error al asignar el tutor:", error);
            });
            
        } else {
            alert("Por favor seleccione un tutor antes de asignar.");
        }
    });
});
</script>
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

		}, 2500);
	}

	document.addEventListener("DOMContentLoaded", function() {
		cerrarNotificacion();
	});
</script>
</html>