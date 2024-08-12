function mostrarCampos() {
	var rolSelect = document.getElementById("rolSelect");
	var areaLabel = document.getElementById("areaLabel");
	var areaSelect = document.querySelector("select[name='area']");
	var semestreLabel = document.getElementById("semestreLabel");
	var semestreSelect = document.querySelector("select[name='semestre']");
	var generacionLabel = document.getElementById("generacionLabel");
	var generacionSelect = document.querySelector("select[name='generacion']");

	var selectedRol = rolSelect.value;
	console.log(selectedRol)

	// Ocultar todos los elementos
	areaLabel.style.display = "none";
	areaSelect.style.display = "none";
	semestreLabel.style.display = "none";
	semestreSelect.style.display = "none";
	generacionLabel.style.display = "none";
	generacionSelect.style.display = "none";



	if (selectedRol === "Tutor" || selectedRol === "Encargado") {
		areaLabel.style.display = "flex";
		areaSelect.style.display = "flex";
	} else if (selectedRol === "Estudiante") {
		semestreLabel.style.display = "flex";
		semestreSelect.style.display = "flex";
		generacionLabel.style.display = "flex";
		generacionSelect.style.display = "flex";
	}
}
