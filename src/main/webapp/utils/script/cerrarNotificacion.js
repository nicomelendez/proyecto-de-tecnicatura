function cerrarNotificacion() {
	document.addEventListener("DOMContentLoaded", function() {
		setTimeout(function() {

			var xhr = new XMLHttpRequest();
			xhr.open("GET", "/Proyecto-PInfra/SvCerrarNotificacion", true);
			xhr.send();

		}, 4000);
	});
}
