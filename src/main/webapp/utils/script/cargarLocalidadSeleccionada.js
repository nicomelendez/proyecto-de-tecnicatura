function cargarLocalidadSeleccionada(nombre) {
	var departamentoSelect = document.getElementById("departamentoSelect");
	var localidadSelect = document.getElementById("localidadSelect");

	var selectedDepartamento = departamentoSelect.value;
	var selectedLocalidad = nombre;
	
	console.log(selectedLocalidad)
	
	localidadSelect.disabled = (selectedDepartamento === "Selecciona un departamento");

	if (!localidadSelect.disabled) {
		// Realizar una petición Ajax al servidor para obtener las localidades del departamento seleccionado
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				// Limpiar las opciones actuales
				localidadSelect.innerHTML = '<option>Selecciona una localidad</option>';

				// Parsear la respuesta JSON
				var localidades = JSON.parse(xhr.responseText);

				// Agregar las nuevas opciones
				for (var i = 0; i < localidades.length; i++) {
					var option = document.createElement("option");
					option.value = localidades[i].nombre;
					option.text = localidades[i].nombre;

					if (localidades[i].nombre === selectedLocalidad) {
						option.selected = true;
					}

					localidadSelect.add(option);
				}
			}
		};

		xhr.open("GET", "/Proyecto-PInfra/SvLocalidades?departamento="
			+ selectedDepartamento, true);
		xhr.send();
	}
}