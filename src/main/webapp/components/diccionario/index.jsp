
<%
if (request.getSession().getAttribute("mostrarDiccionario") != null) {
%>
<div id="diccionario" class="diccionario">
	<div class="diccionarioContenido">
		<div style='text-align: justify;'>
			<p>
				A continuación, encontrarás una lista de palabras clave que puedes
				emplear para personalizar una plantilla de PDF.<br />Te
				recomendamos tener en cuenta tanto las mayúsculas como las
				minúsculas al utilizar estas palabras clave en el documento.<br />Cada
				palabra clave se muestra entre dos ampersands (&&) y su contenido
				correspondiente.
			</p>
		</div>

		<div class="palabrasClave">
			<div>
				<h3>
					Datos del estudiante
					</h2>
					<p>
						Cédula - &cedula& <br>Nombres - &nombres& <br>Apellidos
						- &apellidos& <br>Nombre completo - &nombreCompleto& <br>Fecha
						de Nacimiento - &fechaNacimiento& <br>Edad - &edad& <br>Localidad
						del ITR - &localidadItr& <br>Nombre del ITR - &nombreItr& <br>
						Localidad del Estudiante - &localidadEstudiante& <br>Departamento
						del Estudiante - &departamentoEstudiante& <br>Teléfono -
						&telefono& <br>Mail Personal - &mailPersonal& <br>Mail
						Institucional - &mailInstitucional&
					</p>
			</div>
			<div>
				<div>
					<h3>
						Datos de la Constancia
						</h2>
						<p>
							Fecha Expedido - &fechaExpedido&<br>Tipo Constancia -
							&tipoConstancia
						</p>
				</div>
				<div class="palabrasClaveEvento">
					<h3>
						Datos del Evento
						</h2>
						<p>
							Nombre Evento - &nombreEvento&<br>Fecha Inicio Evento -
							&fechaInicioEvento&<br>Fecha Fin Evento - &fechaFinEvento&
						</p>
				</div>
			</div>

		</div>

		<form class="btnCerrar" action="/Proyecto-PInfra/SvDiccionario"
			method="GET">
			<button type="submit" class="btnDiccionario">Cerrar</button>
		</form>

	</div>
</div>
<%
}
%>