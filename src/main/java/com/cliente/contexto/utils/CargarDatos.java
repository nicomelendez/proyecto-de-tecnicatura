package com.cliente.contexto.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import com.cliente.contexto.Fabrica;
import com.cliente.dto.AnalistaDTO;
import com.cliente.dto.AreaDTO;
import com.cliente.dto.DepartamentoDTO;
import com.cliente.dto.EstudianteDTO;
import com.cliente.dto.GeneroDTO;
import com.cliente.dto.ItrDTO;
import com.cliente.dto.LocalidadDTO;
import com.cliente.dto.RolDTO;
import com.cliente.dto.TutorDTO;
import com.cliente.dto.UsuarioDTO;
import com.cliente.services.ServiceAnalista;
import com.cliente.services.ServiceArea;
import com.cliente.services.ServiceConstancia;
import com.cliente.services.ServiceEstado;
import com.cliente.services.ServiceEstadoConstancia;
import com.cliente.services.ServiceEstadoReclamo;
import com.cliente.services.ServiceEstudiante;
import com.cliente.services.ServiceEvento;
import com.cliente.services.ServiceEventoEstudiante;
import com.cliente.services.ServiceGenero;
import com.cliente.services.ServiceItr;
import com.cliente.services.ServiceReclamo;
import com.cliente.services.ServiceRol;
import com.cliente.services.ServiceTipo;
import com.cliente.services.ServiceUbicacion;
import com.cliente.services.ServiceUsuario;
import com.servidor.entidades.Analista;
import com.servidor.entidades.Constancia;
import com.servidor.entidades.Estado;
import com.servidor.entidades.EstadoConstancia;
import com.servidor.entidades.EstadoConstanciaPK;
import com.servidor.entidades.EstadoReclamo;
import com.servidor.entidades.EstadoReclamoPK;
import com.servidor.entidades.Estudiante;
import com.servidor.entidades.Evento;
import com.servidor.entidades.EventoEstudiante;
import com.servidor.entidades.EventoEstudiantePK;
import com.servidor.entidades.Itr;
import com.servidor.entidades.Reclamo;
import com.servidor.entidades.Tipo;

public class CargarDatos {

	public static void empezar() {

		var contador = ServiceRol.listar().size();

		if (contador != 0) {
			return;
		}

		RolDTO oRol = new RolDTO("Analista");
		RolDTO oRol2 = new RolDTO("Estudiante");
		RolDTO oRol3 = new RolDTO("Tutor");
		RolDTO oRol4 = new RolDTO("Encargado");

		var rolCreado = ServiceRol.crear(oRol);
		var rolCreado2 = ServiceRol.crear(oRol2);
		var rolCreado3 = ServiceRol.crear(oRol3);
		var rolCreado4 = ServiceRol.crear(oRol4);

		GeneroDTO genero = new GeneroDTO("Masculino");
		GeneroDTO genero2 = new GeneroDTO("Femenino");
		GeneroDTO genero3 = new GeneroDTO("Otro");

		var generoCreado = ServiceGenero.crear(genero);
		var generoCreado2 = ServiceGenero.crear(genero2);
		var generoCreado3 = ServiceGenero.crear(genero3);

		DepartamentoDTO departamento = new DepartamentoDTO("Artigas");
		DepartamentoDTO departamento2 = new DepartamentoDTO("Canelones");
		DepartamentoDTO departamento3 = new DepartamentoDTO("Cerro Largo");
		DepartamentoDTO departamento4 = new DepartamentoDTO("Colonia");
		DepartamentoDTO departamento5 = new DepartamentoDTO("Durazno");
		DepartamentoDTO departamento6 = new DepartamentoDTO("Flores");
		DepartamentoDTO departamento7 = new DepartamentoDTO("Florida");
		DepartamentoDTO departamento8 = new DepartamentoDTO("Lavalleja");
		DepartamentoDTO departamento9 = new DepartamentoDTO("Maldonado");
		DepartamentoDTO departamento10 = new DepartamentoDTO("Montevideo");
		DepartamentoDTO departamento11 = new DepartamentoDTO("Paysandú");
		DepartamentoDTO departamento12 = new DepartamentoDTO("Río Negro");
		DepartamentoDTO departamento13 = new DepartamentoDTO("Rivera");
		DepartamentoDTO departamento14 = new DepartamentoDTO("Rocha");
		DepartamentoDTO departamento15 = new DepartamentoDTO("Salto");
		DepartamentoDTO departamento16 = new DepartamentoDTO("San José");
		DepartamentoDTO departamento17 = new DepartamentoDTO("Soriano");
		DepartamentoDTO departamento18 = new DepartamentoDTO("Tacuarembó");
		DepartamentoDTO departamento19 = new DepartamentoDTO("Treinta y Tres");

		var departamentoCreado = ServiceUbicacion.crearDepartamento(departamento);
		var departamentoCreado2 = ServiceUbicacion.crearDepartamento(departamento2);
		var departamentoCreado3 = ServiceUbicacion.crearDepartamento(departamento3);
		var departamentoCreado4 = ServiceUbicacion.crearDepartamento(departamento4);
		var departamentoCreado5 = ServiceUbicacion.crearDepartamento(departamento5);
		var departamentoCreado6 = ServiceUbicacion.crearDepartamento(departamento6);
		var departamentoCreado7 = ServiceUbicacion.crearDepartamento(departamento7);
		var departamentoCreado8 = ServiceUbicacion.crearDepartamento(departamento8);
		var departamentoCreado9 = ServiceUbicacion.crearDepartamento(departamento9);
		var departamentoCreado10 = ServiceUbicacion.crearDepartamento(departamento10);
		var departamentoCreado11 = ServiceUbicacion.crearDepartamento(departamento11);
		var departamentoCreado12 = ServiceUbicacion.crearDepartamento(departamento12);
		var departamentoCreado13 = ServiceUbicacion.crearDepartamento(departamento13);
		var departamentoCreado14 = ServiceUbicacion.crearDepartamento(departamento14);
		var departamentoCreado15 = ServiceUbicacion.crearDepartamento(departamento15);
		var departamentoCreado16 = ServiceUbicacion.crearDepartamento(departamento16);
		var departamentoCreado17 = ServiceUbicacion.crearDepartamento(departamento17);
		var departamentoCreado18 = ServiceUbicacion.crearDepartamento(departamento18);
		var departamentoCreado19 = ServiceUbicacion.crearDepartamento(departamento19);

		// Artigas
		LocalidadDTO localidad1 = new LocalidadDTO("Artigas", departamentoCreado);
		LocalidadDTO localidad2 = new LocalidadDTO("Bella Unión", departamentoCreado);
		LocalidadDTO localidad3 = new LocalidadDTO("Tomás Gomensoro", departamentoCreado);
		LocalidadDTO localidad4 = new LocalidadDTO("Baltasar Brum", departamentoCreado);
		LocalidadDTO localidad5 = new LocalidadDTO("Sequeira", departamentoCreado);
		LocalidadDTO localidad6 = new LocalidadDTO("Franquia", departamentoCreado);
		LocalidadDTO localidad7 = new LocalidadDTO("Colonia Palma", departamentoCreado);
		LocalidadDTO localidad8 = new LocalidadDTO("Coronado", departamentoCreado);
		LocalidadDTO localidad9 = new LocalidadDTO("Bernabé Rivera", departamentoCreado);
		LocalidadDTO localidad10 = new LocalidadDTO("Portones de Hierro y Campodónico", departamentoCreado);
		LocalidadDTO localidad11 = new LocalidadDTO("Paso Campamento", departamentoCreado);
		LocalidadDTO localidad12 = new LocalidadDTO("Javier de Viana", departamentoCreado);
		LocalidadDTO localidad13 = new LocalidadDTO("Diego Lamas", departamentoCreado);
		LocalidadDTO localidad14 = new LocalidadDTO("Topador", departamentoCreado);
		LocalidadDTO localidad15 = new LocalidadDTO("Cuaró", departamentoCreado);

		// Canelones
		LocalidadDTO localidad16 = new LocalidadDTO("Ciudad de la Costa", departamentoCreado2);
		LocalidadDTO localidad17 = new LocalidadDTO("Las Piedras", departamentoCreado2);
		LocalidadDTO localidad18 = new LocalidadDTO("Pando", departamentoCreado2);
		LocalidadDTO localidad19 = new LocalidadDTO("Barros Blancos", departamentoCreado2);
		LocalidadDTO localidad20 = new LocalidadDTO("Canelones", departamentoCreado2);
		LocalidadDTO localidad21 = new LocalidadDTO("Salinas", departamentoCreado2);
		LocalidadDTO localidad22 = new LocalidadDTO("18 de Mayo", departamentoCreado2);
		LocalidadDTO localidad23 = new LocalidadDTO("Paso Carrasco", departamentoCreado2);
		LocalidadDTO localidad24 = new LocalidadDTO("La Paz", departamentoCreado2);
		LocalidadDTO localidad25 = new LocalidadDTO("Toledo", departamentoCreado2);
		LocalidadDTO localidad26 = new LocalidadDTO("Santa Lucía", departamentoCreado2);
		LocalidadDTO localidad27 = new LocalidadDTO("Suárez", departamentoCreado2);
		LocalidadDTO localidad28 = new LocalidadDTO("Nicolich", departamentoCreado2);
		LocalidadDTO localidad29 = new LocalidadDTO("Progreso", departamentoCreado2);
		LocalidadDTO localidad30 = new LocalidadDTO("Sauce", departamentoCreado2);
		LocalidadDTO localidad31 = new LocalidadDTO("Parque del Plata", departamentoCreado2);
		LocalidadDTO localidad32 = new LocalidadDTO("Atlántida", departamentoCreado2);
		LocalidadDTO localidad33 = new LocalidadDTO("Tala", departamentoCreado2);
		LocalidadDTO localidad34 = new LocalidadDTO("La Floresta", departamentoCreado2);
		LocalidadDTO localidad35 = new LocalidadDTO("San Ramón", departamentoCreado2);
		LocalidadDTO localidad36 = new LocalidadDTO("Los Cerrillos", departamentoCreado2);
		LocalidadDTO localidad37 = new LocalidadDTO("Santa Rosa", departamentoCreado2);
		LocalidadDTO localidad38 = new LocalidadDTO("San Jacinto", departamentoCreado2);
		LocalidadDTO localidad39 = new LocalidadDTO("Empalme Olmos", departamentoCreado2);
		LocalidadDTO localidad40 = new LocalidadDTO("Soca", departamentoCreado2);
		LocalidadDTO localidad41 = new LocalidadDTO("Migues", departamentoCreado2);
		LocalidadDTO localidad42 = new LocalidadDTO("San Bautista", departamentoCreado2);
		LocalidadDTO localidad43 = new LocalidadDTO("San Antonio", departamentoCreado2);
		LocalidadDTO localidad44 = new LocalidadDTO("Aguas Corrientes", departamentoCreado2);
		LocalidadDTO localidad45 = new LocalidadDTO("Montes", departamentoCreado2);

		// Cerro Largo
		LocalidadDTO localidad46 = new LocalidadDTO("Melo", departamentoCreado3);
		LocalidadDTO localidad47 = new LocalidadDTO("Río Branco", departamentoCreado3);
		LocalidadDTO localidad48 = new LocalidadDTO("Fraile Muerto", departamentoCreado3);
		LocalidadDTO localidad49 = new LocalidadDTO("Isidoro Noblía", departamentoCreado3);
		LocalidadDTO localidad51 = new LocalidadDTO("Barrio Hipódromo", departamentoCreado3);
		LocalidadDTO localidad52 = new LocalidadDTO("Aceguá", departamentoCreado3);
		LocalidadDTO localidad53 = new LocalidadDTO("Tupambaé", departamentoCreado3);
		LocalidadDTO localidad54 = new LocalidadDTO("Plácido Rosas", departamentoCreado3);
		LocalidadDTO localidad55 = new LocalidadDTO("Arévalo", departamentoCreado3);
		LocalidadDTO localidad56 = new LocalidadDTO("Barrio López Benítez", departamentoCreado3);
		LocalidadDTO localidad57 = new LocalidadDTO("Ramón Trigo", departamentoCreado3);
		LocalidadDTO localidad58 = new LocalidadDTO("Barrio La Vinchuca", departamentoCreado3);
		LocalidadDTO localidad59 = new LocalidadDTO("Arbolito", departamentoCreado3);
		LocalidadDTO localidad60 = new LocalidadDTO("Cerro de las Cuentas", departamentoCreado3);
		LocalidadDTO localidad61 = new LocalidadDTO("Bañado de Medina", departamentoCreado3);
		LocalidadDTO localidad62 = new LocalidadDTO("Tres Islas", departamentoCreado3);
		LocalidadDTO localidad63 = new LocalidadDTO("La Pedrera", departamentoCreado3);
		LocalidadDTO localidad64 = new LocalidadDTO("Las Cañas", departamentoCreado3);
		LocalidadDTO localidad65 = new LocalidadDTO("Quebracho", departamentoCreado3);
		LocalidadDTO localidad66 = new LocalidadDTO("Centurión", departamentoCreado3);
		LocalidadDTO localidad67 = new LocalidadDTO("Esperanza", departamentoCreado3);
		LocalidadDTO localidad68 = new LocalidadDTO("Arachania", departamentoCreado3);
		LocalidadDTO localidad69 = new LocalidadDTO("Nando", departamentoCreado3);
		LocalidadDTO localidad70 = new LocalidadDTO("Soto Goro", departamentoCreado3);
		LocalidadDTO localidad71 = new LocalidadDTO("Mangrullo", departamentoCreado3);
		LocalidadDTO localidad72 = new LocalidadDTO("Ñangapiré", departamentoCreado3);

		// Colonia
		LocalidadDTO localidad73 = new LocalidadDTO("Colonia del Sacramento", departamentoCreado4);
		LocalidadDTO localidad74 = new LocalidadDTO("Carmelo", departamentoCreado4);
		LocalidadDTO localidad75 = new LocalidadDTO("Juan Lacaze", departamentoCreado4);
		LocalidadDTO localidad76 = new LocalidadDTO("Nueva Helvecia", departamentoCreado4);
		LocalidadDTO localidad77 = new LocalidadDTO("Rosario", departamentoCreado4);
		LocalidadDTO localidad78 = new LocalidadDTO("Nueva Palmira", departamentoCreado4);
		LocalidadDTO localidad79 = new LocalidadDTO("Tarariras", departamentoCreado4);
		LocalidadDTO localidad80 = new LocalidadDTO("Colonia Valdense", departamentoCreado4);
		LocalidadDTO localidad81 = new LocalidadDTO("Florencio Sánchez", departamentoCreado4);
		LocalidadDTO localidad82 = new LocalidadDTO("Ombúes de Lavalle", departamentoCreado4);
		LocalidadDTO localidad83 = new LocalidadDTO("Miguelete", departamentoCreado4);
		LocalidadDTO localidad84 = new LocalidadDTO("La Paz", departamentoCreado4);
		LocalidadDTO localidad85 = new LocalidadDTO("El Semillero", departamentoCreado4);
		LocalidadDTO localidad86 = new LocalidadDTO("Colonia Cosmopolita", departamentoCreado4);
		LocalidadDTO localidad87 = new LocalidadDTO("Conchillas", departamentoCreado4);
		LocalidadDTO localidad88 = new LocalidadDTO("Pueblo Gil", departamentoCreado4);
		LocalidadDTO localidad89 = new LocalidadDTO("Campana", departamentoCreado4);
		LocalidadDTO localidad90 = new LocalidadDTO("Radial Conchillas", departamentoCreado4);
		LocalidadDTO localidad91 = new LocalidadDTO("Estanzuela", departamentoCreado4);
		LocalidadDTO localidad92 = new LocalidadDTO("Santa Ana", departamentoCreado4);
		LocalidadDTO localidad93 = new LocalidadDTO("Los Pinos", departamentoCreado4);
		LocalidadDTO localidad94 = new LocalidadDTO("Barker", departamentoCreado4);
		LocalidadDTO localidad95 = new LocalidadDTO("Riachuelo", departamentoCreado4);
		LocalidadDTO localidad96 = new LocalidadDTO("Laguna de los Patos", departamentoCreado4);
		LocalidadDTO localidad97 = new LocalidadDTO("Minuano", departamentoCreado4);
		LocalidadDTO localidad98 = new LocalidadDTO("Arrivillaga", departamentoCreado4);
		LocalidadDTO localidad99 = new LocalidadDTO("Playa Britópolis", departamentoCreado4);
		LocalidadDTO localidad100 = new LocalidadDTO("La Horqueta", departamentoCreado4);
		LocalidadDTO localidad101 = new LocalidadDTO("Zagarzazú", departamentoCreado4);
		LocalidadDTO localidad102 = new LocalidadDTO("Playa Fomento", departamentoCreado4);

		// Durazno
		LocalidadDTO localidad103 = new LocalidadDTO("Durazno", departamentoCreado5);
		LocalidadDTO localidad104 = new LocalidadDTO("Sarandí del Yí", departamentoCreado5);
		LocalidadDTO localidad105 = new LocalidadDTO("Villa del Carmen", departamentoCreado5);
		LocalidadDTO localidad106 = new LocalidadDTO("La Paloma", departamentoCreado5);
		LocalidadDTO localidad107 = new LocalidadDTO("Centenario", departamentoCreado5);
		LocalidadDTO localidad108 = new LocalidadDTO("Santa Bernardina", departamentoCreado5);
		LocalidadDTO localidad109 = new LocalidadDTO("Blanquillo", departamentoCreado5);
		LocalidadDTO localidad110 = new LocalidadDTO("Carlos Reyles", departamentoCreado5);
		LocalidadDTO localidad111 = new LocalidadDTO("San Jorge", departamentoCreado5);
		LocalidadDTO localidad112 = new LocalidadDTO("Baygorria", departamentoCreado5);
		LocalidadDTO localidad113 = new LocalidadDTO("Ombúes de Oribe", departamentoCreado5);
		LocalidadDTO localidad114 = new LocalidadDTO("Aguas Buenas", departamentoCreado5);
		LocalidadDTO localidad115 = new LocalidadDTO("Feliciano", departamentoCreado5);
		LocalidadDTO localidad116 = new LocalidadDTO("Rossell y Rius", departamentoCreado5);
		LocalidadDTO localidad117 = new LocalidadDTO("Pueblo de Álvarez", departamentoCreado5);
		LocalidadDTO localidad118 = new LocalidadDTO("Las Palmas", departamentoCreado5);

		// Flores
		LocalidadDTO localidad119 = new LocalidadDTO("Trinidad", departamentoCreado6);
		LocalidadDTO localidad120 = new LocalidadDTO("Ismael Cortinas", departamentoCreado6);
		LocalidadDTO localidad121 = new LocalidadDTO("Andresito", departamentoCreado6);
		LocalidadDTO localidad122 = new LocalidadDTO("La Casilla", departamentoCreado6);
		LocalidadDTO localidad123 = new LocalidadDTO("Juan José Castro", departamentoCreado6);
		LocalidadDTO localidad124 = new LocalidadDTO("Cerro Colorado", departamentoCreado6);

		// Florida
		LocalidadDTO localidad125 = new LocalidadDTO("Florida", departamentoCreado7);
		LocalidadDTO localidad126 = new LocalidadDTO("Sarandí Grande", departamentoCreado7);
		LocalidadDTO localidad127 = new LocalidadDTO("Fray Marcos", departamentoCreado7);
		LocalidadDTO localidad128 = new LocalidadDTO("Cerro Chato", departamentoCreado7);
		LocalidadDTO localidad129 = new LocalidadDTO("Casupá", departamentoCreado7);
		LocalidadDTO localidad130 = new LocalidadDTO("25 de Mayo", departamentoCreado7);
		LocalidadDTO localidad131 = new LocalidadDTO("25 de Agosto", departamentoCreado7);
		LocalidadDTO localidad132 = new LocalidadDTO("Alejandro Gallinal", departamentoCreado7);
		LocalidadDTO localidad133 = new LocalidadDTO("Cardal", departamentoCreado7);
		LocalidadDTO localidad134 = new LocalidadDTO("Nico Pérez", departamentoCreado7);
		LocalidadDTO localidad135 = new LocalidadDTO("Capilla del Sauce", departamentoCreado7);
		LocalidadDTO localidad136 = new LocalidadDTO("Mendoza Chico", departamentoCreado7);
		LocalidadDTO localidad137 = new LocalidadDTO("La Cruz", departamentoCreado7);
		LocalidadDTO localidad138 = new LocalidadDTO("Mendoza", departamentoCreado7);
		LocalidadDTO localidad139 = new LocalidadDTO("San Gabriel", departamentoCreado7);
		LocalidadDTO localidad140 = new LocalidadDTO("Chamizo", departamentoCreado7);
		LocalidadDTO localidad141 = new LocalidadDTO("Independencia", departamentoCreado7);
		LocalidadDTO localidad142 = new LocalidadDTO("Reboledo", departamentoCreado7);
		LocalidadDTO localidad143 = new LocalidadDTO("Goñi", departamentoCreado7);
		LocalidadDTO localidad144 = new LocalidadDTO("Valentines", departamentoCreado7);
		LocalidadDTO localidad145 = new LocalidadDTO("Pintado", departamentoCreado7);
		LocalidadDTO localidad146 = new LocalidadDTO("Berrondo", departamentoCreado7);
		LocalidadDTO localidad147 = new LocalidadDTO("Puntas de Maciel", departamentoCreado7);
		LocalidadDTO localidad148 = new LocalidadDTO("Illescas", departamentoCreado7);
		LocalidadDTO localidad149 = new LocalidadDTO("La Macana", departamentoCreado7);
		LocalidadDTO localidad150 = new LocalidadDTO("Montecoral", departamentoCreado7);
		LocalidadDTO localidad151 = new LocalidadDTO("Estación Capilla del Sauce", departamentoCreado7);
		LocalidadDTO localidad152 = new LocalidadDTO("Polanco del Yí", departamentoCreado7);
		LocalidadDTO localidad153 = new LocalidadDTO("Caserío La Fundación", departamentoCreado7);

		// Lavalleja
		LocalidadDTO localidad154 = new LocalidadDTO("Minas", departamentoCreado8);
		LocalidadDTO localidad155 = new LocalidadDTO("José Pedro Varela", departamentoCreado8);
		LocalidadDTO localidad156 = new LocalidadDTO("Solís de Mataojo", departamentoCreado8);
		LocalidadDTO localidad157 = new LocalidadDTO("José Batlle y Ordóñez", departamentoCreado8);
		LocalidadDTO localidad158 = new LocalidadDTO("Mariscala", departamentoCreado8);
		LocalidadDTO localidad159 = new LocalidadDTO("Pirarajá", departamentoCreado8);
		LocalidadDTO localidad160 = new LocalidadDTO("Zapicán", departamentoCreado8);
		LocalidadDTO localidad161 = new LocalidadDTO("Colón", departamentoCreado8);
		LocalidadDTO localidad162 = new LocalidadDTO("Villa del Rosario", departamentoCreado8);
		LocalidadDTO localidad163 = new LocalidadDTO("Illescas", departamentoCreado8);
		LocalidadDTO localidad164 = new LocalidadDTO("Blanes Viale", departamentoCreado8);
		LocalidadDTO localidad165 = new LocalidadDTO("Aramendía", departamentoCreado8);
		LocalidadDTO localidad166 = new LocalidadDTO("Villa Serrana", departamentoCreado8);
		LocalidadDTO localidad167 = new LocalidadDTO("Polanco Norte", departamentoCreado8);
		LocalidadDTO localidad168 = new LocalidadDTO("San Francisco de las Sierras", departamentoCreado8);
		LocalidadDTO localidad169 = new LocalidadDTO("19 de Junio", departamentoCreado8);
		LocalidadDTO localidad170 = new LocalidadDTO("Estación Solís", departamentoCreado8);
		LocalidadDTO localidad171 = new LocalidadDTO("Gaetán", departamentoCreado8);

		// Maldonado
		LocalidadDTO localidad172 = new LocalidadDTO("Maldonado", departamentoCreado9);
		LocalidadDTO localidad173 = new LocalidadDTO("San Carlos", departamentoCreado9);
		LocalidadDTO localidad174 = new LocalidadDTO("Piriápolis", departamentoCreado9);
		LocalidadDTO localidad175 = new LocalidadDTO("Punta del Este", departamentoCreado9);
		LocalidadDTO localidad176 = new LocalidadDTO("Pan de Azúcar", departamentoCreado9);
		LocalidadDTO localidad177 = new LocalidadDTO("Aiguá", departamentoCreado9);
		LocalidadDTO localidad178 = new LocalidadDTO("Solís Grande", departamentoCreado9);
		LocalidadDTO localidad179 = new LocalidadDTO("Garzón", departamentoCreado9);

		// Montevideo
		LocalidadDTO localidad180 = new LocalidadDTO("Montevideo A", departamentoCreado10);
		LocalidadDTO localidad181 = new LocalidadDTO("Montevideo B", departamentoCreado10);
		LocalidadDTO localidad182 = new LocalidadDTO("Montevideo C", departamentoCreado10);
		LocalidadDTO localidad183 = new LocalidadDTO("Montevideo CH", departamentoCreado10);
		LocalidadDTO localidad184 = new LocalidadDTO("Montevideo D", departamentoCreado10);
		LocalidadDTO localidad185 = new LocalidadDTO("Montevideo E", departamentoCreado10);
		LocalidadDTO localidad186 = new LocalidadDTO("Montevideo F", departamentoCreado10);
		LocalidadDTO localidad187 = new LocalidadDTO("Montevideo G", departamentoCreado10);

		// Paysandú
		LocalidadDTO localidad188 = new LocalidadDTO("Paysandú", departamentoCreado11);
		LocalidadDTO localidad189 = new LocalidadDTO("Nuevo Paysandú", departamentoCreado11);
		LocalidadDTO localidad190 = new LocalidadDTO("Guichón", departamentoCreado11);
		LocalidadDTO localidad191 = new LocalidadDTO("Porvenir", departamentoCreado11);
		LocalidadDTO localidad192 = new LocalidadDTO("Chacras de Paysandú", departamentoCreado11);
		LocalidadDTO localidad193 = new LocalidadDTO("Quebracho", departamentoCreado11);
		LocalidadDTO localidad194 = new LocalidadDTO("Piedras Coloradas", departamentoCreado11);
		LocalidadDTO localidad195 = new LocalidadDTO("San Félix", departamentoCreado11);
		LocalidadDTO localidad196 = new LocalidadDTO("Lorenzo Geyres", departamentoCreado11);
		LocalidadDTO localidad197 = new LocalidadDTO("Tambores", departamentoCreado11);
		LocalidadDTO localidad198 = new LocalidadDTO("Chapicuy", departamentoCreado11);
		LocalidadDTO localidad199 = new LocalidadDTO("Gallinal", departamentoCreado11);
		LocalidadDTO localidad200 = new LocalidadDTO("Casa Blanca", departamentoCreado11);
		LocalidadDTO localidad201 = new LocalidadDTO("Cerro Chato", departamentoCreado11);
		LocalidadDTO localidad202 = new LocalidadDTO("El Eucalipto", departamentoCreado11);
		LocalidadDTO localidad203 = new LocalidadDTO("Cañada del Pueblo", departamentoCreado11);
		LocalidadDTO localidad204 = new LocalidadDTO("Zeballos", departamentoCreado11);
		LocalidadDTO localidad205 = new LocalidadDTO("Villa María", departamentoCreado11);
		LocalidadDTO localidad206 = new LocalidadDTO("Soto", departamentoCreado11);
		LocalidadDTO localidad207 = new LocalidadDTO("Federación", departamentoCreado11);
		LocalidadDTO localidad208 = new LocalidadDTO("Cuchilla de Buricayupí", departamentoCreado11);
		LocalidadDTO localidad209 = new LocalidadDTO("Poblado Alonso", departamentoCreado11);

		// Río Negro
		LocalidadDTO localidad210 = new LocalidadDTO("Fray Bentos", departamentoCreado12);
		LocalidadDTO localidad211 = new LocalidadDTO("Young", departamentoCreado12);
		LocalidadDTO localidad212 = new LocalidadDTO("San Javier", departamentoCreado12);
		LocalidadDTO localidad213 = new LocalidadDTO("Nuevo Berlín", departamentoCreado12);
		LocalidadDTO localidad214 = new LocalidadDTO("Barrio Anglo", departamentoCreado12);
		LocalidadDTO localidad215 = new LocalidadDTO("Algorta", departamentoCreado12);
		LocalidadDTO localidad216 = new LocalidadDTO("Grecco", departamentoCreado12);
		LocalidadDTO localidad217 = new LocalidadDTO("Villa General Borges", departamentoCreado12);
		LocalidadDTO localidad218 = new LocalidadDTO("Paso de los Mellizos", departamentoCreado12);
		LocalidadDTO localidad219 = new LocalidadDTO("Bellaco", departamentoCreado12);
		LocalidadDTO localidad220 = new LocalidadDTO("Los Arrayanes", departamentoCreado12);
		LocalidadDTO localidad221 = new LocalidadDTO("Sarandí de Navarro", departamentoCreado12);
		LocalidadDTO localidad222 = new LocalidadDTO("Villa María", departamentoCreado12);
		LocalidadDTO localidad223 = new LocalidadDTO("Las Cañas", departamentoCreado12);
		LocalidadDTO localidad224 = new LocalidadDTO("Tres Quintas", departamentoCreado12);
		LocalidadDTO localidad225 = new LocalidadDTO("El Ombú", departamentoCreado12);
		LocalidadDTO localidad226 = new LocalidadDTO("Menafra", departamentoCreado12);
		LocalidadDTO localidad227 = new LocalidadDTO("Colonia Ofir", departamentoCreado12);
		LocalidadDTO localidad228 = new LocalidadDTO("Gartental", departamentoCreado12);

		// Rivera
		LocalidadDTO localidad229 = new LocalidadDTO("Rivera", departamentoCreado13);
		LocalidadDTO localidad230 = new LocalidadDTO("Tranqueras", departamentoCreado13);
		LocalidadDTO localidad231 = new LocalidadDTO("Cerros de la Calera", departamentoCreado13);
		LocalidadDTO localidad232 = new LocalidadDTO("Vichadero", departamentoCreado13);
		LocalidadDTO localidad233 = new LocalidadDTO("Minas de Corrales", departamentoCreado13);
		LocalidadDTO localidad234 = new LocalidadDTO("Las Flores", departamentoCreado13);
		LocalidadDTO localidad235 = new LocalidadDTO("La Puente", departamentoCreado13);
		LocalidadDTO localidad236 = new LocalidadDTO("Paso Hospital", departamentoCreado13);
		LocalidadDTO localidad237 = new LocalidadDTO("Lagos del Norte", departamentoCreado13);
		LocalidadDTO localidad238 = new LocalidadDTO("Masoller", departamentoCreado13);
		LocalidadDTO localidad239 = new LocalidadDTO("Moirones", departamentoCreado13);
		LocalidadDTO localidad240 = new LocalidadDTO("Cerro Pelado", departamentoCreado13);
		LocalidadDTO localidad241 = new LocalidadDTO("Cerrillada", departamentoCreado13);
		LocalidadDTO localidad242 = new LocalidadDTO("Paso Ataques", departamentoCreado13);
		LocalidadDTO localidad243 = new LocalidadDTO("Arroyo Blanco", departamentoCreado13);
		LocalidadDTO localidad245 = new LocalidadDTO("Amarillo", departamentoCreado13);

		// Rocha
		LocalidadDTO localidad246 = new LocalidadDTO("Rocha", departamentoCreado14);
		LocalidadDTO localidad247 = new LocalidadDTO("Chuy", departamentoCreado14);
		LocalidadDTO localidad248 = new LocalidadDTO("Castillos", departamentoCreado14);
		LocalidadDTO localidad249 = new LocalidadDTO("La Paloma", departamentoCreado14);
		LocalidadDTO localidad250 = new LocalidadDTO("Cebollatí", departamentoCreado14);
		LocalidadDTO localidad251 = new LocalidadDTO("Velázquez", departamentoCreado14);
		LocalidadDTO localidad252 = new LocalidadDTO("18 de Julio", departamentoCreado14);
		LocalidadDTO localidad253 = new LocalidadDTO("Punta del Diablo", departamentoCreado14);
		LocalidadDTO localidad254 = new LocalidadDTO("San Luis al Medio", departamentoCreado14);
		LocalidadDTO localidad255 = new LocalidadDTO("Capacho", departamentoCreado14);
		LocalidadDTO localidad256 = new LocalidadDTO("La Coronilla", departamentoCreado14);
		LocalidadDTO localidad257 = new LocalidadDTO("Puimayen", departamentoCreado14);
		LocalidadDTO localidad258 = new LocalidadDTO("Barra del Chuy", departamentoCreado14);
		LocalidadDTO localidad259 = new LocalidadDTO("Barra de Valizas", departamentoCreado14);
		LocalidadDTO localidad260 = new LocalidadDTO("19 de Abril", departamentoCreado14);
		LocalidadDTO localidad261 = new LocalidadDTO("Barrio Pereira", departamentoCreado14);
		LocalidadDTO localidad262 = new LocalidadDTO("Santa Isabel de la Pedrera", departamentoCreado14);
		LocalidadDTO localidad263 = new LocalidadDTO("Barrio Torres", departamentoCreado14);
		LocalidadDTO localidad264 = new LocalidadDTO("Puente Valizas", departamentoCreado14);
		LocalidadDTO localidad265 = new LocalidadDTO("La Riviera", departamentoCreado14);
		LocalidadDTO localidad266 = new LocalidadDTO("Puerto de los Botes", departamentoCreado14);
		LocalidadDTO localidad267 = new LocalidadDTO("Parallé", departamentoCreado14);
		LocalidadDTO localidad268 = new LocalidadDTO("Palmares de la Coronilla", departamentoCreado14);
		LocalidadDTO localidad269 = new LocalidadDTO("Pueblo Nuevo", departamentoCreado14);
		LocalidadDTO localidad270 = new LocalidadDTO("Oceanía del Polonio", departamentoCreado14);
		LocalidadDTO localidad271 = new LocalidadDTO("Tajamares de la Pedrera", departamentoCreado14);
		LocalidadDTO localidad272 = new LocalidadDTO("Barrancas", departamentoCreado14);
		LocalidadDTO localidad273 = new LocalidadDTO("Cabo Polonio", departamentoCreado14);

		// Salto
		LocalidadDTO localidad274 = new LocalidadDTO("Salto", departamentoCreado15);
		LocalidadDTO localidad275 = new LocalidadDTO("Villa Constitución", departamentoCreado15);
		LocalidadDTO localidad276 = new LocalidadDTO("San Antonio", departamentoCreado15);
		LocalidadDTO localidad277 = new LocalidadDTO("Colonia Lavalleja", departamentoCreado15);
		LocalidadDTO localidad278 = new LocalidadDTO("Belén", departamentoCreado15);
		LocalidadDTO localidad279 = new LocalidadDTO("Rincón de Valentín", departamentoCreado15);
		LocalidadDTO localidad280 = new LocalidadDTO("Mataojo", departamentoCreado15);
		LocalidadDTO localidad281 = new LocalidadDTO("Colonia 18 de Julio", departamentoCreado15);
		LocalidadDTO localidad282 = new LocalidadDTO("Migliaro", departamentoCreado15);
		LocalidadDTO localidad283 = new LocalidadDTO("Colonia Itapebí", departamentoCreado15);
		LocalidadDTO localidad284 = new LocalidadDTO("Termas del Daymán", departamentoCreado15);
		LocalidadDTO localidad285 = new LocalidadDTO("Termas del Arapey", departamentoCreado15);
		LocalidadDTO localidad286 = new LocalidadDTO("Campo de Todos", departamentoCreado15);
		LocalidadDTO localidad287 = new LocalidadDTO("Puntas de Valentín", departamentoCreado15);
		LocalidadDTO localidad288 = new LocalidadDTO("Cerros de Vera", departamentoCreado15);
		LocalidadDTO localidad289 = new LocalidadDTO("Arenitas Blancas", departamentoCreado15);
		LocalidadDTO localidad290 = new LocalidadDTO("Laureles", departamentoCreado15);
		LocalidadDTO localidad291 = new LocalidadDTO("Paso Cementerio", departamentoCreado15);
		LocalidadDTO localidad292 = new LocalidadDTO("Arerunguá", departamentoCreado15);
		LocalidadDTO localidad293 = new LocalidadDTO("Pueblo Cayetano", departamentoCreado15);
		LocalidadDTO localidad294 = new LocalidadDTO("Paso del Parque del Daymán", departamentoCreado15);

		// San José
		LocalidadDTO localidad295 = new LocalidadDTO("San José de Mayo", departamentoCreado16);
		LocalidadDTO localidad296 = new LocalidadDTO("Ciudad del Plata", departamentoCreado16);
		LocalidadDTO localidad297 = new LocalidadDTO("Libertad", departamentoCreado16);
		LocalidadDTO localidad298 = new LocalidadDTO("Ecilda Paullier", departamentoCreado16);
		LocalidadDTO localidad299 = new LocalidadDTO("Rodríguez", departamentoCreado16);
		LocalidadDTO localidad300 = new LocalidadDTO("Puntas de Valdez", departamentoCreado16);
		LocalidadDTO localidad301 = new LocalidadDTO("Rafael Perazza", departamentoCreado16);
		LocalidadDTO localidad302 = new LocalidadDTO("Ituzaingó", departamentoCreado16);
		LocalidadDTO localidad303 = new LocalidadDTO("Raigón", departamentoCreado16);
		LocalidadDTO localidad304 = new LocalidadDTO("Villa María", departamentoCreado16);
		LocalidadDTO localidad305 = new LocalidadDTO("Capurro", departamentoCreado16);
		LocalidadDTO localidad306 = new LocalidadDTO("18 de Julio-Pueblo Nuevo", departamentoCreado16);
		LocalidadDTO localidad307 = new LocalidadDTO("Mal Abrigo", departamentoCreado16);
		LocalidadDTO localidad308 = new LocalidadDTO("Juan Soler", departamentoCreado16);
		LocalidadDTO localidad309 = new LocalidadDTO("Kiyú-Ordeig", departamentoCreado16);
		LocalidadDTO localidad310 = new LocalidadDTO("La Radial", departamentoCreado16);
		LocalidadDTO localidad311 = new LocalidadDTO("González", departamentoCreado16);
		LocalidadDTO localidad312 = new LocalidadDTO("Scavino", departamentoCreado16);
		LocalidadDTO localidad313 = new LocalidadDTO("Rincón del Pino", departamentoCreado16);
		LocalidadDTO localidad314 = new LocalidadDTO("Cololó", departamentoCreado16);
		LocalidadDTO localidad315 = new LocalidadDTO("Costas de Pereira", departamentoCreado16);
		LocalidadDTO localidad316 = new LocalidadDTO("Carreta Quemada", departamentoCreado16);
		LocalidadDTO localidad317 = new LocalidadDTO("Cerámicas del Sur", departamentoCreado16);
		LocalidadDTO localidad318 = new LocalidadDTO("Mangrullo", departamentoCreado16);
		LocalidadDTO localidad319 = new LocalidadDTO("La Boyada", departamentoCreado16);
		LocalidadDTO localidad320 = new LocalidadDTO("Cañada Grande - Fabre", departamentoCreado16);
		LocalidadDTO localidad321 = new LocalidadDTO("Colonia Delta", departamentoCreado16);
		LocalidadDTO localidad322 = new LocalidadDTO("San Gregorio", departamentoCreado16);
		LocalidadDTO localidad323 = new LocalidadDTO("Bocas del Cufré", departamentoCreado16);

		// Soriano
		LocalidadDTO localidad324 = new LocalidadDTO("Mercedes", departamentoCreado17);
		LocalidadDTO localidad325 = new LocalidadDTO("Dolores", departamentoCreado17);
		LocalidadDTO localidad326 = new LocalidadDTO("Cardona", departamentoCreado17);
		LocalidadDTO localidad327 = new LocalidadDTO("José Enrique Rodó", departamentoCreado17);
		LocalidadDTO localidad328 = new LocalidadDTO("Palmitas", departamentoCreado17);
		LocalidadDTO localidad329 = new LocalidadDTO("Chacras de Dolores", departamentoCreado17);
		LocalidadDTO localidad330 = new LocalidadDTO("Villa Soriano", departamentoCreado17);
		LocalidadDTO localidad331 = new LocalidadDTO("Santa Catalina", departamentoCreado17);
		LocalidadDTO localidad332 = new LocalidadDTO("Egaña", departamentoCreado17);
		LocalidadDTO localidad333 = new LocalidadDTO("Agraciada", departamentoCreado17);
		LocalidadDTO localidad334 = new LocalidadDTO("Risso", departamentoCreado17);
		LocalidadDTO localidad335 = new LocalidadDTO("Sacachispas", departamentoCreado17);
		LocalidadDTO localidad336 = new LocalidadDTO("Cañada Nieto", departamentoCreado17);
		LocalidadDTO localidad337 = new LocalidadDTO("Palmar", departamentoCreado17);
		LocalidadDTO localidad338 = new LocalidadDTO("Palo Solo", departamentoCreado17);
		LocalidadDTO localidad339 = new LocalidadDTO("Castillos", departamentoCreado17);
		LocalidadDTO localidad340 = new LocalidadDTO("Perseverano", departamentoCreado17);
		LocalidadDTO localidad341 = new LocalidadDTO("La Loma", departamentoCreado17);
		LocalidadDTO localidad342 = new LocalidadDTO("Lares", departamentoCreado17);
		LocalidadDTO localidad343 = new LocalidadDTO("La Concordia", departamentoCreado17);
		LocalidadDTO localidad344 = new LocalidadDTO("El Tala", departamentoCreado17);
		LocalidadDTO localidad345 = new LocalidadDTO("Colonia Concordia", departamentoCreado17);
		LocalidadDTO localidad346 = new LocalidadDTO("Cuchilla del Perdido", departamentoCreado17);

		// Tacuarembó
		LocalidadDTO localidad347 = new LocalidadDTO("Tacuarembó", departamentoCreado18);
		LocalidadDTO localidad348 = new LocalidadDTO("Paso de los Toros", departamentoCreado18);
		LocalidadDTO localidad349 = new LocalidadDTO("San Gregorio de Polanco", departamentoCreado18);
		LocalidadDTO localidad350 = new LocalidadDTO("Ansina", departamentoCreado18);
		LocalidadDTO localidad351 = new LocalidadDTO("Tambores", departamentoCreado18);
		LocalidadDTO localidad352 = new LocalidadDTO("Las Toscas", departamentoCreado18);
		LocalidadDTO localidad353 = new LocalidadDTO("Curtina", departamentoCreado18);
		LocalidadDTO localidad354 = new LocalidadDTO("Achar", departamentoCreado18);
		LocalidadDTO localidad355 = new LocalidadDTO("Paso Bonilla", departamentoCreado18);
		LocalidadDTO localidad356 = new LocalidadDTO("Cuchilla de Caraguatá", departamentoCreado18);
		LocalidadDTO localidad357 = new LocalidadDTO("Balneario Iporá", departamentoCreado18);
		LocalidadDTO localidad358 = new LocalidadDTO("La Pedrera", departamentoCreado18);
		LocalidadDTO localidad359 = new LocalidadDTO("Paso del Cerro", departamentoCreado18);
		LocalidadDTO localidad360 = new LocalidadDTO("Cuchilla de Peralta", departamentoCreado18);
		LocalidadDTO localidad361 = new LocalidadDTO("Piedra Sola", departamentoCreado18);
		LocalidadDTO localidad362 = new LocalidadDTO("Pueblo de Arriba", departamentoCreado18);
		LocalidadDTO localidad363 = new LocalidadDTO("Clara", departamentoCreado18);
		LocalidadDTO localidad364 = new LocalidadDTO("Sauce de Batoví", departamentoCreado18);
		LocalidadDTO localidad365 = new LocalidadDTO("Punta de Carretera", departamentoCreado18);
		LocalidadDTO localidad366 = new LocalidadDTO("La Hilera", departamentoCreado18);
		LocalidadDTO localidad367 = new LocalidadDTO("Pueblo del Barro", departamentoCreado18);
		LocalidadDTO localidad368 = new LocalidadDTO("Cuchilla del Ombú", departamentoCreado18);
		LocalidadDTO localidad369 = new LocalidadDTO("Puntas de Cinco Sauces", departamentoCreado18);
		LocalidadDTO localidad370 = new LocalidadDTO("Cardozo", departamentoCreado18);
		LocalidadDTO localidad371 = new LocalidadDTO("Montevideo Chico", departamentoCreado18);
		LocalidadDTO localidad372 = new LocalidadDTO("Rincón de Pereira", departamentoCreado18);
		LocalidadDTO localidad373 = new LocalidadDTO("Laureles", departamentoCreado18);

		// Treinta y Tres
		LocalidadDTO localidad374 = new LocalidadDTO("Treinta y Tres", departamentoCreado19);
		LocalidadDTO localidad375 = new LocalidadDTO("Ejido de Treinta y Tres", departamentoCreado19);
		LocalidadDTO localidad376 = new LocalidadDTO("Vergara", departamentoCreado19);
		LocalidadDTO localidad377 = new LocalidadDTO("Cerro Chato", departamentoCreado19);
		LocalidadDTO localidad378 = new LocalidadDTO("Santa Clara de Olimar", departamentoCreado19);
		LocalidadDTO localidad379 = new LocalidadDTO("General Enrique Martínez", departamentoCreado19);
		LocalidadDTO localidad380 = new LocalidadDTO("Estación Rincón", departamentoCreado19);
		LocalidadDTO localidad381 = new LocalidadDTO("Arrozal Treinta y Tres", departamentoCreado19);
		LocalidadDTO localidad382 = new LocalidadDTO("Isla Patrulla", departamentoCreado19);
		LocalidadDTO localidad383 = new LocalidadDTO("Valentines", departamentoCreado19);
		LocalidadDTO localidad384 = new LocalidadDTO("Arrocera Zapata", departamentoCreado19);
		LocalidadDTO localidad385 = new LocalidadDTO("Mendizábal", departamentoCreado19);
		LocalidadDTO localidad386 = new LocalidadDTO("Arrocera San Fernando", departamentoCreado19);
		LocalidadDTO localidad387 = new LocalidadDTO("María Albina", departamentoCreado19);
		LocalidadDTO localidad388 = new LocalidadDTO("Arrocera Rincón", departamentoCreado19);
		LocalidadDTO localidad389 = new LocalidadDTO("El Bellaco", departamentoCreado19);
		LocalidadDTO localidad390 = new LocalidadDTO("Arrocera Mini", departamentoCreado19);
		LocalidadDTO localidad391 = new LocalidadDTO("Arrocera Las Palmas", departamentoCreado19);
		LocalidadDTO localidad392 = new LocalidadDTO("Arrocera El Tigre", departamentoCreado19);
		LocalidadDTO localidad393 = new LocalidadDTO("Arrocera La Catumbera", departamentoCreado19);
		LocalidadDTO localidad394 = new LocalidadDTO("Arrocera La Querencia", departamentoCreado19);
		LocalidadDTO localidad395 = new LocalidadDTO("Arrocera Santa Fé", departamentoCreado19);
		LocalidadDTO localidad396 = new LocalidadDTO("Arrocera Los Teros", departamentoCreado19);
		LocalidadDTO localidad397 = new LocalidadDTO("Arrocera Procipa", departamentoCreado19);
		LocalidadDTO localidad398 = new LocalidadDTO("Alonso", departamentoCreado19);
		LocalidadDTO localidad399 = new LocalidadDTO("Villa Passano", departamentoCreado19);
		LocalidadDTO localidad400 = new LocalidadDTO("Puntas del Parao", departamentoCreado19);
		LocalidadDTO localidad401 = new LocalidadDTO("Arrocera Bonomo", departamentoCreado19);
		LocalidadDTO localidad402 = new LocalidadDTO("Arrocera Los Ceibos", departamentoCreado19);

		var localidadCreada1 = ServiceUbicacion.crearLocalidad(localidad1);
		var localidadCreada2 = ServiceUbicacion.crearLocalidad(localidad2);
		var localidadCreada3 = ServiceUbicacion.crearLocalidad(localidad3);
		var localidadCreada4 = ServiceUbicacion.crearLocalidad(localidad4);
		var localidadCreada5 = ServiceUbicacion.crearLocalidad(localidad5);
		var localidadCreada6 = ServiceUbicacion.crearLocalidad(localidad6);
		var localidadCreada7 = ServiceUbicacion.crearLocalidad(localidad7);
		var localidadCreada8 = ServiceUbicacion.crearLocalidad(localidad8);
		var localidadCreada9 = ServiceUbicacion.crearLocalidad(localidad9);
		var localidadCreada10 = ServiceUbicacion.crearLocalidad(localidad10);
		var localidadCreada11 = ServiceUbicacion.crearLocalidad(localidad11);
		var localidadCreada12 = ServiceUbicacion.crearLocalidad(localidad12);
		var localidadCreada13 = ServiceUbicacion.crearLocalidad(localidad13);
		var localidadCreada14 = ServiceUbicacion.crearLocalidad(localidad14);
		var localidadCreada15 = ServiceUbicacion.crearLocalidad(localidad15);
		var localidadCreada16 = ServiceUbicacion.crearLocalidad(localidad16);
		var localidadCreada17 = ServiceUbicacion.crearLocalidad(localidad17);
		var localidadCreada18 = ServiceUbicacion.crearLocalidad(localidad18);
		var localidadCreada19 = ServiceUbicacion.crearLocalidad(localidad19);
		var localidadCreada20 = ServiceUbicacion.crearLocalidad(localidad20);
		var localidadCreada21 = ServiceUbicacion.crearLocalidad(localidad21);
		var localidadCreada22 = ServiceUbicacion.crearLocalidad(localidad22);
		var localidadCreada23 = ServiceUbicacion.crearLocalidad(localidad23);
		var localidadCreada24 = ServiceUbicacion.crearLocalidad(localidad24);
		var localidadCreada25 = ServiceUbicacion.crearLocalidad(localidad25);
		var localidadCreada26 = ServiceUbicacion.crearLocalidad(localidad26);
		var localidadCreada27 = ServiceUbicacion.crearLocalidad(localidad27);
		var localidadCreada28 = ServiceUbicacion.crearLocalidad(localidad28);
		var localidadCreada31 = ServiceUbicacion.crearLocalidad(localidad31);
		var localidadCreada32 = ServiceUbicacion.crearLocalidad(localidad32);
		var localidadCreada33 = ServiceUbicacion.crearLocalidad(localidad33);
		var localidadCreada34 = ServiceUbicacion.crearLocalidad(localidad34);
		var localidadCreada35 = ServiceUbicacion.crearLocalidad(localidad35);
		var localidadCreada36 = ServiceUbicacion.crearLocalidad(localidad36);
		var localidadCreada37 = ServiceUbicacion.crearLocalidad(localidad37);
		var localidadCreada38 = ServiceUbicacion.crearLocalidad(localidad38);
		var localidadCreada39 = ServiceUbicacion.crearLocalidad(localidad39);
		var localidadCreada40 = ServiceUbicacion.crearLocalidad(localidad40);
		var localidadCreada41 = ServiceUbicacion.crearLocalidad(localidad41);
		var localidadCreada42 = ServiceUbicacion.crearLocalidad(localidad42);
		var localidadCreada43 = ServiceUbicacion.crearLocalidad(localidad43);
		var localidadCreada44 = ServiceUbicacion.crearLocalidad(localidad44);
		var localidadCreada45 = ServiceUbicacion.crearLocalidad(localidad45);
		var localidadCreada46 = ServiceUbicacion.crearLocalidad(localidad46);
		var localidadCreada47 = ServiceUbicacion.crearLocalidad(localidad47);
		var localidadCreada48 = ServiceUbicacion.crearLocalidad(localidad48);
		var localidadCreada49 = ServiceUbicacion.crearLocalidad(localidad49);
		var localidadCreada51 = ServiceUbicacion.crearLocalidad(localidad51);
		var localidadCreada52 = ServiceUbicacion.crearLocalidad(localidad52);
		var localidadCreada53 = ServiceUbicacion.crearLocalidad(localidad53);
		var localidadCreada54 = ServiceUbicacion.crearLocalidad(localidad54);
		var localidadCreada55 = ServiceUbicacion.crearLocalidad(localidad55);
		var localidadCreada56 = ServiceUbicacion.crearLocalidad(localidad56);
		var localidadCreada57 = ServiceUbicacion.crearLocalidad(localidad57);
		var localidadCreada58 = ServiceUbicacion.crearLocalidad(localidad58);
		var localidadCreada59 = ServiceUbicacion.crearLocalidad(localidad59);
		var localidadCreada60 = ServiceUbicacion.crearLocalidad(localidad60);
		var localidadCreada61 = ServiceUbicacion.crearLocalidad(localidad61);
		var localidadCreada62 = ServiceUbicacion.crearLocalidad(localidad62);
		var localidadCreada63 = ServiceUbicacion.crearLocalidad(localidad63);
		var localidadCreada64 = ServiceUbicacion.crearLocalidad(localidad64);
		var localidadCreada65 = ServiceUbicacion.crearLocalidad(localidad65);
		var localidadCreada66 = ServiceUbicacion.crearLocalidad(localidad66);
		var localidadCreada67 = ServiceUbicacion.crearLocalidad(localidad67);
		var localidadCreada68 = ServiceUbicacion.crearLocalidad(localidad68);
		var localidadCreada69 = ServiceUbicacion.crearLocalidad(localidad69);
		var localidadCreada70 = ServiceUbicacion.crearLocalidad(localidad70);
		var localidadCreada71 = ServiceUbicacion.crearLocalidad(localidad71);
		var localidadCreada72 = ServiceUbicacion.crearLocalidad(localidad72);
		var localidadCreada73 = ServiceUbicacion.crearLocalidad(localidad73);
		var localidadCreada74 = ServiceUbicacion.crearLocalidad(localidad74);
		var localidadCreada75 = ServiceUbicacion.crearLocalidad(localidad75);
		var localidadCreada76 = ServiceUbicacion.crearLocalidad(localidad76);
		var localidadCreada77 = ServiceUbicacion.crearLocalidad(localidad77);
		var localidadCreada78 = ServiceUbicacion.crearLocalidad(localidad78);
		var localidadCreada79 = ServiceUbicacion.crearLocalidad(localidad79);
		var localidadCreada80 = ServiceUbicacion.crearLocalidad(localidad80);
		var localidadCreada81 = ServiceUbicacion.crearLocalidad(localidad81);
		var localidadCreada82 = ServiceUbicacion.crearLocalidad(localidad82);
		var localidadCreada83 = ServiceUbicacion.crearLocalidad(localidad83);
		var localidadCreada84 = ServiceUbicacion.crearLocalidad(localidad84);
		var localidadCreada85 = ServiceUbicacion.crearLocalidad(localidad85);
		var localidadCreada86 = ServiceUbicacion.crearLocalidad(localidad86);
		var localidadCreada87 = ServiceUbicacion.crearLocalidad(localidad87);
		var localidadCreada88 = ServiceUbicacion.crearLocalidad(localidad88);
		var localidadCreada89 = ServiceUbicacion.crearLocalidad(localidad89);
		var localidadCreada90 = ServiceUbicacion.crearLocalidad(localidad90);
		var localidadCreada91 = ServiceUbicacion.crearLocalidad(localidad91);
		var localidadCreada92 = ServiceUbicacion.crearLocalidad(localidad92);
		var localidadCreada93 = ServiceUbicacion.crearLocalidad(localidad93);
		var localidadCreada94 = ServiceUbicacion.crearLocalidad(localidad94);
		var localidadCreada95 = ServiceUbicacion.crearLocalidad(localidad95);
		var localidadCreada96 = ServiceUbicacion.crearLocalidad(localidad96);
		var localidadCreada97 = ServiceUbicacion.crearLocalidad(localidad97);
		var localidadCreada98 = ServiceUbicacion.crearLocalidad(localidad98);
		var localidadCreada99 = ServiceUbicacion.crearLocalidad(localidad99);
		var localidadCreada100 = ServiceUbicacion.crearLocalidad(localidad100);
		var localidadCreada101 = ServiceUbicacion.crearLocalidad(localidad101);
		var localidadCreada102 = ServiceUbicacion.crearLocalidad(localidad102);
		var localidadCreada103 = ServiceUbicacion.crearLocalidad(localidad103);
		var localidadCreada104 = ServiceUbicacion.crearLocalidad(localidad104);
		var localidadCreada105 = ServiceUbicacion.crearLocalidad(localidad105);
		var localidadCreada106 = ServiceUbicacion.crearLocalidad(localidad106);
		var localidadCreada107 = ServiceUbicacion.crearLocalidad(localidad107);
		var localidadCreada108 = ServiceUbicacion.crearLocalidad(localidad108);
		var localidadCreada109 = ServiceUbicacion.crearLocalidad(localidad109);
		var localidadCreada110 = ServiceUbicacion.crearLocalidad(localidad110);
		var localidadCreada111 = ServiceUbicacion.crearLocalidad(localidad111);
		var localidadCreada112 = ServiceUbicacion.crearLocalidad(localidad112);
		var localidadCreada113 = ServiceUbicacion.crearLocalidad(localidad113);
		var localidadCreada114 = ServiceUbicacion.crearLocalidad(localidad114);
		var localidadCreada115 = ServiceUbicacion.crearLocalidad(localidad115);
		var localidadCreada116 = ServiceUbicacion.crearLocalidad(localidad116);
		var localidadCreada117 = ServiceUbicacion.crearLocalidad(localidad117);
		var localidadCreada118 = ServiceUbicacion.crearLocalidad(localidad118);
		var localidadCreada119 = ServiceUbicacion.crearLocalidad(localidad119);
		var localidadCreada120 = ServiceUbicacion.crearLocalidad(localidad120);
		var localidadCreada121 = ServiceUbicacion.crearLocalidad(localidad121);
		var localidadCreada122 = ServiceUbicacion.crearLocalidad(localidad122);
		var localidadCreada123 = ServiceUbicacion.crearLocalidad(localidad123);
		var localidadCreada124 = ServiceUbicacion.crearLocalidad(localidad124);
		var localidadCreada125 = ServiceUbicacion.crearLocalidad(localidad125);
		var localidadCreada126 = ServiceUbicacion.crearLocalidad(localidad126);
		var localidadCreada127 = ServiceUbicacion.crearLocalidad(localidad127);
		var localidadCreada128 = ServiceUbicacion.crearLocalidad(localidad128);
		var localidadCreada131 = ServiceUbicacion.crearLocalidad(localidad131);
		var localidadCreada132 = ServiceUbicacion.crearLocalidad(localidad132);
		var localidadCreada133 = ServiceUbicacion.crearLocalidad(localidad133);
		var localidadCreada134 = ServiceUbicacion.crearLocalidad(localidad134);
		var localidadCreada135 = ServiceUbicacion.crearLocalidad(localidad135);
		var localidadCreada136 = ServiceUbicacion.crearLocalidad(localidad136);
		var localidadCreada137 = ServiceUbicacion.crearLocalidad(localidad137);
		var localidadCreada138 = ServiceUbicacion.crearLocalidad(localidad138);
		var localidadCreada139 = ServiceUbicacion.crearLocalidad(localidad139);
		var localidadCreada140 = ServiceUbicacion.crearLocalidad(localidad140);
		var localidadCreada141 = ServiceUbicacion.crearLocalidad(localidad141);
		var localidadCreada142 = ServiceUbicacion.crearLocalidad(localidad142);
		var localidadCreada143 = ServiceUbicacion.crearLocalidad(localidad143);
		var localidadCreada144 = ServiceUbicacion.crearLocalidad(localidad144);
		var localidadCreada145 = ServiceUbicacion.crearLocalidad(localidad145);
		var localidadCreada146 = ServiceUbicacion.crearLocalidad(localidad146);
		var localidadCreada147 = ServiceUbicacion.crearLocalidad(localidad147);
		var localidadCreada148 = ServiceUbicacion.crearLocalidad(localidad148);
		var localidadCreada149 = ServiceUbicacion.crearLocalidad(localidad149);
		var localidadCreada150 = ServiceUbicacion.crearLocalidad(localidad150);
		var localidadCreada151 = ServiceUbicacion.crearLocalidad(localidad151);
		var localidadCreada152 = ServiceUbicacion.crearLocalidad(localidad152);
		var localidadCreada153 = ServiceUbicacion.crearLocalidad(localidad153);
		var localidadCreada154 = ServiceUbicacion.crearLocalidad(localidad154);
		var localidadCreada155 = ServiceUbicacion.crearLocalidad(localidad155);
		var localidadCreada156 = ServiceUbicacion.crearLocalidad(localidad156);
		var localidadCreada157 = ServiceUbicacion.crearLocalidad(localidad157);
		var localidadCreada158 = ServiceUbicacion.crearLocalidad(localidad158);
		var localidadCreada159 = ServiceUbicacion.crearLocalidad(localidad159);
		var localidadCreada160 = ServiceUbicacion.crearLocalidad(localidad160);
		var localidadCreada161 = ServiceUbicacion.crearLocalidad(localidad161);
		var localidadCreada162 = ServiceUbicacion.crearLocalidad(localidad162);
		var localidadCreada163 = ServiceUbicacion.crearLocalidad(localidad163);
		var localidadCreada164 = ServiceUbicacion.crearLocalidad(localidad164);
		var localidadCreada165 = ServiceUbicacion.crearLocalidad(localidad165);
		var localidadCreada166 = ServiceUbicacion.crearLocalidad(localidad166);
		var localidadCreada167 = ServiceUbicacion.crearLocalidad(localidad167);
		var localidadCreada168 = ServiceUbicacion.crearLocalidad(localidad168);
		var localidadCreada169 = ServiceUbicacion.crearLocalidad(localidad169);
		var localidadCreada170 = ServiceUbicacion.crearLocalidad(localidad170);
		var localidadCreada171 = ServiceUbicacion.crearLocalidad(localidad171);
		var localidadCreada172 = ServiceUbicacion.crearLocalidad(localidad172);
		var localidadCreada173 = ServiceUbicacion.crearLocalidad(localidad173);
		var localidadCreada174 = ServiceUbicacion.crearLocalidad(localidad174);
		var localidadCreada175 = ServiceUbicacion.crearLocalidad(localidad175);
		var localidadCreada176 = ServiceUbicacion.crearLocalidad(localidad176);
		var localidadCreada177 = ServiceUbicacion.crearLocalidad(localidad177);
		var localidadCreada178 = ServiceUbicacion.crearLocalidad(localidad178);
		var localidadCreada179 = ServiceUbicacion.crearLocalidad(localidad179);
		var localidadCreada180 = ServiceUbicacion.crearLocalidad(localidad180);
		var localidadCreada181 = ServiceUbicacion.crearLocalidad(localidad181);
		var localidadCreada182 = ServiceUbicacion.crearLocalidad(localidad182);
		var localidadCreada183 = ServiceUbicacion.crearLocalidad(localidad183);
		var localidadCreada184 = ServiceUbicacion.crearLocalidad(localidad184);
		var localidadCreada185 = ServiceUbicacion.crearLocalidad(localidad185);
		var localidadCreada186 = ServiceUbicacion.crearLocalidad(localidad186);
		var localidadCreada187 = ServiceUbicacion.crearLocalidad(localidad187);
		var localidadCreada188 = ServiceUbicacion.crearLocalidad(localidad188);
		var localidadCreada189 = ServiceUbicacion.crearLocalidad(localidad189);
		var localidadCreada190 = ServiceUbicacion.crearLocalidad(localidad190);
		var localidadCreada191 = ServiceUbicacion.crearLocalidad(localidad191);
		var localidadCreada192 = ServiceUbicacion.crearLocalidad(localidad192);
		var localidadCreada193 = ServiceUbicacion.crearLocalidad(localidad193);
		var localidadCreada194 = ServiceUbicacion.crearLocalidad(localidad194);
		var localidadCreada195 = ServiceUbicacion.crearLocalidad(localidad195);
		var localidadCreada196 = ServiceUbicacion.crearLocalidad(localidad196);
		var localidadCreada197 = ServiceUbicacion.crearLocalidad(localidad197);
		var localidadCreada198 = ServiceUbicacion.crearLocalidad(localidad198);
		var localidadCreada199 = ServiceUbicacion.crearLocalidad(localidad199);
		var localidadCreada200 = ServiceUbicacion.crearLocalidad(localidad200);
		var localidadCreada201 = ServiceUbicacion.crearLocalidad(localidad201);
		var localidadCreada202 = ServiceUbicacion.crearLocalidad(localidad202);
		var localidadCreada203 = ServiceUbicacion.crearLocalidad(localidad203);
		var localidadCreada204 = ServiceUbicacion.crearLocalidad(localidad204);
		var localidadCreada205 = ServiceUbicacion.crearLocalidad(localidad205);
		var localidadCreada206 = ServiceUbicacion.crearLocalidad(localidad206);
		var localidadCreada207 = ServiceUbicacion.crearLocalidad(localidad207);
		var localidadCreada208 = ServiceUbicacion.crearLocalidad(localidad208);
		var localidadCreada209 = ServiceUbicacion.crearLocalidad(localidad209);
		var localidadCreada210 = ServiceUbicacion.crearLocalidad(localidad210);
		var localidadCreada211 = ServiceUbicacion.crearLocalidad(localidad211);
		var localidadCreada212 = ServiceUbicacion.crearLocalidad(localidad212);
		var localidadCreada213 = ServiceUbicacion.crearLocalidad(localidad213);
		var localidadCreada214 = ServiceUbicacion.crearLocalidad(localidad214);
		var localidadCreada215 = ServiceUbicacion.crearLocalidad(localidad215);
		var localidadCreada216 = ServiceUbicacion.crearLocalidad(localidad216);
		var localidadCreada217 = ServiceUbicacion.crearLocalidad(localidad217);
		var localidadCreada218 = ServiceUbicacion.crearLocalidad(localidad218);
		var localidadCreada219 = ServiceUbicacion.crearLocalidad(localidad219);
		var localidadCreada220 = ServiceUbicacion.crearLocalidad(localidad220);
		var localidadCreada221 = ServiceUbicacion.crearLocalidad(localidad221);
		var localidadCreada222 = ServiceUbicacion.crearLocalidad(localidad222);
		var localidadCreada223 = ServiceUbicacion.crearLocalidad(localidad223);
		var localidadCreada224 = ServiceUbicacion.crearLocalidad(localidad224);
		var localidadCreada225 = ServiceUbicacion.crearLocalidad(localidad225);
		var localidadCreada226 = ServiceUbicacion.crearLocalidad(localidad226);
		var localidadCreada227 = ServiceUbicacion.crearLocalidad(localidad227);
		var localidadCreada228 = ServiceUbicacion.crearLocalidad(localidad228);
		var localidadCreada231 = ServiceUbicacion.crearLocalidad(localidad231);
		var localidadCreada232 = ServiceUbicacion.crearLocalidad(localidad232);
		var localidadCreada233 = ServiceUbicacion.crearLocalidad(localidad233);
		var localidadCreada234 = ServiceUbicacion.crearLocalidad(localidad234);
		var localidadCreada235 = ServiceUbicacion.crearLocalidad(localidad235);
		var localidadCreada236 = ServiceUbicacion.crearLocalidad(localidad236);
		var localidadCreada237 = ServiceUbicacion.crearLocalidad(localidad237);
		var localidadCreada238 = ServiceUbicacion.crearLocalidad(localidad238);
		var localidadCreada239 = ServiceUbicacion.crearLocalidad(localidad239);
		var localidadCreada240 = ServiceUbicacion.crearLocalidad(localidad240);
		var localidadCreada241 = ServiceUbicacion.crearLocalidad(localidad241);
		var localidadCreada242 = ServiceUbicacion.crearLocalidad(localidad242);
		var localidadCreada243 = ServiceUbicacion.crearLocalidad(localidad243);
		var localidadCreada245 = ServiceUbicacion.crearLocalidad(localidad245);
		var localidadCreada246 = ServiceUbicacion.crearLocalidad(localidad246);
		var localidadCreada247 = ServiceUbicacion.crearLocalidad(localidad247);
		var localidadCreada248 = ServiceUbicacion.crearLocalidad(localidad248);
		var localidadCreada249 = ServiceUbicacion.crearLocalidad(localidad249);
		var localidadCreada250 = ServiceUbicacion.crearLocalidad(localidad250);
		var localidadCreada251 = ServiceUbicacion.crearLocalidad(localidad251);
		var localidadCreada252 = ServiceUbicacion.crearLocalidad(localidad252);
		var localidadCreada253 = ServiceUbicacion.crearLocalidad(localidad253);
		var localidadCreada254 = ServiceUbicacion.crearLocalidad(localidad254);
		var localidadCreada255 = ServiceUbicacion.crearLocalidad(localidad255);
		var localidadCreada256 = ServiceUbicacion.crearLocalidad(localidad256);
		var localidadCreada257 = ServiceUbicacion.crearLocalidad(localidad257);
		var localidadCreada258 = ServiceUbicacion.crearLocalidad(localidad258);
		var localidadCreada259 = ServiceUbicacion.crearLocalidad(localidad259);
		var localidadCreada260 = ServiceUbicacion.crearLocalidad(localidad260);
		var localidadCreada261 = ServiceUbicacion.crearLocalidad(localidad261);
		var localidadCreada262 = ServiceUbicacion.crearLocalidad(localidad262);
		var localidadCreada263 = ServiceUbicacion.crearLocalidad(localidad263);
		var localidadCreada264 = ServiceUbicacion.crearLocalidad(localidad264);
		var localidadCreada265 = ServiceUbicacion.crearLocalidad(localidad265);
		var localidadCreada266 = ServiceUbicacion.crearLocalidad(localidad266);
		var localidadCreada267 = ServiceUbicacion.crearLocalidad(localidad267);
		var localidadCreada268 = ServiceUbicacion.crearLocalidad(localidad268);
		var localidadCreada269 = ServiceUbicacion.crearLocalidad(localidad269);
		var localidadCreada270 = ServiceUbicacion.crearLocalidad(localidad270);
		var localidadCreada271 = ServiceUbicacion.crearLocalidad(localidad271);
		var localidadCreada272 = ServiceUbicacion.crearLocalidad(localidad272);
		var localidadCreada273 = ServiceUbicacion.crearLocalidad(localidad273);
		var localidadCreada274 = ServiceUbicacion.crearLocalidad(localidad274);
		var localidadCreada275 = ServiceUbicacion.crearLocalidad(localidad275);
		var localidadCreada276 = ServiceUbicacion.crearLocalidad(localidad276);
		var localidadCreada277 = ServiceUbicacion.crearLocalidad(localidad277);
		var localidadCreada278 = ServiceUbicacion.crearLocalidad(localidad278);
		var localidadCreada279 = ServiceUbicacion.crearLocalidad(localidad279);
		var localidadCreada280 = ServiceUbicacion.crearLocalidad(localidad280);
		var localidadCreada281 = ServiceUbicacion.crearLocalidad(localidad281);
		var localidadCreada282 = ServiceUbicacion.crearLocalidad(localidad282);
		var localidadCreada283 = ServiceUbicacion.crearLocalidad(localidad283);
		var localidadCreada284 = ServiceUbicacion.crearLocalidad(localidad284);
		var localidadCreada285 = ServiceUbicacion.crearLocalidad(localidad285);
		var localidadCreada286 = ServiceUbicacion.crearLocalidad(localidad286);
		var localidadCreada287 = ServiceUbicacion.crearLocalidad(localidad287);
		var localidadCreada288 = ServiceUbicacion.crearLocalidad(localidad288);
		var localidadCreada289 = ServiceUbicacion.crearLocalidad(localidad289);
		var localidadCreada290 = ServiceUbicacion.crearLocalidad(localidad290);
		var localidadCreada291 = ServiceUbicacion.crearLocalidad(localidad291);
		var localidadCreada292 = ServiceUbicacion.crearLocalidad(localidad292);
		var localidadCreada293 = ServiceUbicacion.crearLocalidad(localidad293);
		var localidadCreada294 = ServiceUbicacion.crearLocalidad(localidad294);
		var localidadCreada295 = ServiceUbicacion.crearLocalidad(localidad295);
		var localidadCreada296 = ServiceUbicacion.crearLocalidad(localidad296);
		var localidadCreada297 = ServiceUbicacion.crearLocalidad(localidad297);
		var localidadCreada298 = ServiceUbicacion.crearLocalidad(localidad298);
		var localidadCreada299 = ServiceUbicacion.crearLocalidad(localidad299);
		var localidadCreada300 = ServiceUbicacion.crearLocalidad(localidad300);
		var localidadCreada301 = ServiceUbicacion.crearLocalidad(localidad301);
		var localidadCreada302 = ServiceUbicacion.crearLocalidad(localidad302);
		var localidadCreada303 = ServiceUbicacion.crearLocalidad(localidad303);
		var localidadCreada304 = ServiceUbicacion.crearLocalidad(localidad304);
		var localidadCreada305 = ServiceUbicacion.crearLocalidad(localidad305);
		var localidadCreada306 = ServiceUbicacion.crearLocalidad(localidad306);
		var localidadCreada307 = ServiceUbicacion.crearLocalidad(localidad307);
		var localidadCreada308 = ServiceUbicacion.crearLocalidad(localidad308);
		var localidadCreada309 = ServiceUbicacion.crearLocalidad(localidad309);
		var localidadCreada310 = ServiceUbicacion.crearLocalidad(localidad310);
		var localidadCreada311 = ServiceUbicacion.crearLocalidad(localidad311);
		var localidadCreada312 = ServiceUbicacion.crearLocalidad(localidad312);
		var localidadCreada313 = ServiceUbicacion.crearLocalidad(localidad313);
		var localidadCreada314 = ServiceUbicacion.crearLocalidad(localidad314);
		var localidadCreada315 = ServiceUbicacion.crearLocalidad(localidad315);
		var localidadCreada316 = ServiceUbicacion.crearLocalidad(localidad316);
		var localidadCreada317 = ServiceUbicacion.crearLocalidad(localidad317);
		var localidadCreada318 = ServiceUbicacion.crearLocalidad(localidad318);
		var localidadCreada319 = ServiceUbicacion.crearLocalidad(localidad319);
		var localidadCreada320 = ServiceUbicacion.crearLocalidad(localidad320);
		var localidadCreada321 = ServiceUbicacion.crearLocalidad(localidad321);
		var localidadCreada322 = ServiceUbicacion.crearLocalidad(localidad322);
		var localidadCreada323 = ServiceUbicacion.crearLocalidad(localidad323);
		var localidadCreada324 = ServiceUbicacion.crearLocalidad(localidad324);
		var localidadCreada325 = ServiceUbicacion.crearLocalidad(localidad325);
		var localidadCreada326 = ServiceUbicacion.crearLocalidad(localidad326);
		var localidadCreada327 = ServiceUbicacion.crearLocalidad(localidad327);
		var localidadCreada328 = ServiceUbicacion.crearLocalidad(localidad328);
		var localidadCreada331 = ServiceUbicacion.crearLocalidad(localidad331);
		var localidadCreada332 = ServiceUbicacion.crearLocalidad(localidad332);
		var localidadCreada333 = ServiceUbicacion.crearLocalidad(localidad333);
		var localidadCreada334 = ServiceUbicacion.crearLocalidad(localidad334);
		var localidadCreada335 = ServiceUbicacion.crearLocalidad(localidad335);
		var localidadCreada336 = ServiceUbicacion.crearLocalidad(localidad336);
		var localidadCreada337 = ServiceUbicacion.crearLocalidad(localidad337);
		var localidadCreada338 = ServiceUbicacion.crearLocalidad(localidad338);
		var localidadCreada339 = ServiceUbicacion.crearLocalidad(localidad339);
		var localidadCreada340 = ServiceUbicacion.crearLocalidad(localidad340);
		var localidadCreada341 = ServiceUbicacion.crearLocalidad(localidad341);
		var localidadCreada342 = ServiceUbicacion.crearLocalidad(localidad342);
		var localidadCreada343 = ServiceUbicacion.crearLocalidad(localidad343);
		var localidadCreada344 = ServiceUbicacion.crearLocalidad(localidad344);
		var localidadCreada345 = ServiceUbicacion.crearLocalidad(localidad345);
		var localidadCreada346 = ServiceUbicacion.crearLocalidad(localidad346);
		var localidadCreada347 = ServiceUbicacion.crearLocalidad(localidad347);
		var localidadCreada348 = ServiceUbicacion.crearLocalidad(localidad348);
		var localidadCreada349 = ServiceUbicacion.crearLocalidad(localidad349);
		var localidadCreada350 = ServiceUbicacion.crearLocalidad(localidad350);
		var localidadCreada351 = ServiceUbicacion.crearLocalidad(localidad351);
		var localidadCreada352 = ServiceUbicacion.crearLocalidad(localidad352);
		var localidadCreada353 = ServiceUbicacion.crearLocalidad(localidad353);
		var localidadCreada354 = ServiceUbicacion.crearLocalidad(localidad354);
		var localidadCreada355 = ServiceUbicacion.crearLocalidad(localidad355);
		var localidadCreada356 = ServiceUbicacion.crearLocalidad(localidad356);
		var localidadCreada357 = ServiceUbicacion.crearLocalidad(localidad357);
		var localidadCreada358 = ServiceUbicacion.crearLocalidad(localidad358);
		var localidadCreada359 = ServiceUbicacion.crearLocalidad(localidad359);
		var localidadCreada360 = ServiceUbicacion.crearLocalidad(localidad360);
		var localidadCreada361 = ServiceUbicacion.crearLocalidad(localidad361);
		var localidadCreada362 = ServiceUbicacion.crearLocalidad(localidad362);
		var localidadCreada363 = ServiceUbicacion.crearLocalidad(localidad363);
		var localidadCreada364 = ServiceUbicacion.crearLocalidad(localidad364);
		var localidadCreada365 = ServiceUbicacion.crearLocalidad(localidad365);
		var localidadCreada366 = ServiceUbicacion.crearLocalidad(localidad366);
		var localidadCreada367 = ServiceUbicacion.crearLocalidad(localidad367);
		var localidadCreada368 = ServiceUbicacion.crearLocalidad(localidad368);
		var localidadCreada369 = ServiceUbicacion.crearLocalidad(localidad369);
		var localidadCreada370 = ServiceUbicacion.crearLocalidad(localidad370);
		var localidadCreada371 = ServiceUbicacion.crearLocalidad(localidad371);
		var localidadCreada372 = ServiceUbicacion.crearLocalidad(localidad372);
		var localidadCreada373 = ServiceUbicacion.crearLocalidad(localidad373);
		var localidadCreada374 = ServiceUbicacion.crearLocalidad(localidad374);
		var localidadCreada375 = ServiceUbicacion.crearLocalidad(localidad375);
		var localidadCreada376 = ServiceUbicacion.crearLocalidad(localidad376);
		var localidadCreada377 = ServiceUbicacion.crearLocalidad(localidad377);
		var localidadCreada378 = ServiceUbicacion.crearLocalidad(localidad378);
		var localidadCreada379 = ServiceUbicacion.crearLocalidad(localidad379);
		var localidadCreada380 = ServiceUbicacion.crearLocalidad(localidad380);
		var localidadCreada381 = ServiceUbicacion.crearLocalidad(localidad381);
		var localidadCreada382 = ServiceUbicacion.crearLocalidad(localidad382);
		var localidadCreada383 = ServiceUbicacion.crearLocalidad(localidad383);
		var localidadCreada384 = ServiceUbicacion.crearLocalidad(localidad384);
		var localidadCreada385 = ServiceUbicacion.crearLocalidad(localidad385);
		var localidadCreada386 = ServiceUbicacion.crearLocalidad(localidad386);
		var localidadCreada387 = ServiceUbicacion.crearLocalidad(localidad387);
		var localidadCreada388 = ServiceUbicacion.crearLocalidad(localidad388);
		var localidadCreada389 = ServiceUbicacion.crearLocalidad(localidad389);
		var localidadCreada390 = ServiceUbicacion.crearLocalidad(localidad390);
		var localidadCreada391 = ServiceUbicacion.crearLocalidad(localidad391);
		var localidadCreada392 = ServiceUbicacion.crearLocalidad(localidad392);
		var localidadCreada393 = ServiceUbicacion.crearLocalidad(localidad393);
		var localidadCreada394 = ServiceUbicacion.crearLocalidad(localidad394);
		var localidadCreada395 = ServiceUbicacion.crearLocalidad(localidad395);
		var localidadCreada396 = ServiceUbicacion.crearLocalidad(localidad396);
		var localidadCreada397 = ServiceUbicacion.crearLocalidad(localidad397);
		var localidadCreada398 = ServiceUbicacion.crearLocalidad(localidad398);
		var localidadCreada399 = ServiceUbicacion.crearLocalidad(localidad399);
		var localidadCreada400 = ServiceUbicacion.crearLocalidad(localidad400);
		var localidadCreada401 = ServiceUbicacion.crearLocalidad(localidad401);
		var localidadCreada402 = ServiceUbicacion.crearLocalidad(localidad402);

		ItrDTO itr = new ItrDTO("ITR Suroeste", departamentoCreado, "S");
		ItrDTO itr2 = new ItrDTO("ITR Centro-Sur", departamentoCreado2, "S");
		ItrDTO itr3 = new ItrDTO("ITR Norte", departamentoCreado3, "S");

		var itrCreada = ServiceItr.crear(itr);
		var itrCreada2 = ServiceItr.crear(itr2);
		var itrCreada3 = ServiceItr.crear(itr3);

		// Edad Martina
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2002);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 25);
		Date dateMartina = calendar.getTime();

		// Edad Rony
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.YEAR, 2002);
		calendar2.set(Calendar.MONTH, Calendar.JANUARY);
		calendar2.set(Calendar.DAY_OF_MONTH, 16);
		Date dateRony = calendar2.getTime();

		// Edad Matias
		Calendar calendar3 = Calendar.getInstance();
		calendar3.set(Calendar.YEAR, 2001);
		calendar3.set(Calendar.MONTH, Calendar.JANUARY);
		calendar3.set(Calendar.DAY_OF_MONTH, 22);
		Date dateMatias = calendar3.getTime();

		// Edad Valentina
		Calendar calendar4 = Calendar.getInstance();
		calendar4.set(Calendar.YEAR, 2001);
		calendar4.set(Calendar.MONTH, Calendar.JANUARY);
		calendar4.set(Calendar.DAY_OF_MONTH, 12);
		Date dateValentina = calendar4.getTime();

		// Edad Pepe
		Calendar calendar5 = Calendar.getInstance();
		calendar5.set(Calendar.YEAR, 2000);
		calendar5.set(Calendar.MONTH, Calendar.JANUARY);
		calendar5.set(Calendar.DAY_OF_MONTH, 21);
		Date datePepe = calendar5.getTime();

		// Edad Nico
		Calendar calendar6 = Calendar.getInstance();
		calendar6.set(Calendar.YEAR, 1995);
		calendar6.set(Calendar.MONTH, Calendar.JANUARY);
		calendar6.set(Calendar.DAY_OF_MONTH, 20);
		Date dateNico = calendar6.getTime();

		Calendar calendar7 = Calendar.getInstance();
		calendar7.set(Calendar.YEAR, 1990);
		calendar7.set(Calendar.MONTH, Calendar.JANUARY);
		calendar7.set(Calendar.DAY_OF_MONTH, 15);
		Date date1 = calendar7.getTime();

		Calendar calendar8 = Calendar.getInstance();
		calendar8.set(Calendar.YEAR, 1987);
		calendar8.set(Calendar.MONTH, Calendar.MARCH);
		calendar8.set(Calendar.DAY_OF_MONTH, 23);
		Date date2 = calendar8.getTime();

		Calendar calendar9 = Calendar.getInstance();
		calendar9.set(Calendar.YEAR, 1995);
		calendar9.set(Calendar.MONTH, Calendar.JULY);
		calendar9.set(Calendar.DAY_OF_MONTH, 8);
		Date date3 = calendar9.getTime();

		Calendar calendar10 = Calendar.getInstance();
		calendar10.set(Calendar.YEAR, 1993);
		calendar10.set(Calendar.MONTH, Calendar.APRIL);
		calendar10.set(Calendar.DAY_OF_MONTH, 30);
		Date date4 = calendar10.getTime();

		Calendar calendar11 = Calendar.getInstance();
		calendar11.set(Calendar.YEAR, 1991);
		calendar11.set(Calendar.MONTH, Calendar.FEBRUARY);
		calendar11.set(Calendar.DAY_OF_MONTH, 12);
		Date date5 = calendar11.getTime();

		Calendar calendar12 = Calendar.getInstance();
		calendar12.set(Calendar.YEAR, 1999);
		calendar12.set(Calendar.MONTH, Calendar.NOVEMBER);
		calendar12.set(Calendar.DAY_OF_MONTH, 5);
		Date date6 = calendar12.getTime();

		Calendar calendar13 = Calendar.getInstance();
		calendar13.set(Calendar.YEAR, 1988);
		calendar13.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar13.set(Calendar.DAY_OF_MONTH, 18);
		Date date7 = calendar7.getTime();

		BigDecimal cedula = new BigDecimal(78896556);
		BigDecimal cedula2 = new BigDecimal(64100571);
		BigDecimal cedula3 = new BigDecimal(53312088);
		BigDecimal cedula4 = new BigDecimal(31464217);
		BigDecimal cedula5 = new BigDecimal(40988404);
		BigDecimal cedula6 = new BigDecimal(13963015);

		BigDecimal cedula7 = new BigDecimal(28322098);
		BigDecimal cedula8 = new BigDecimal(61365912);
		BigDecimal cedula9 = new BigDecimal(9162728);
		BigDecimal cedula10 = new BigDecimal(10278390);
		BigDecimal cedula11 = new BigDecimal(69510179);
		BigDecimal cedula12 = new BigDecimal(64282155);
		BigDecimal cedula13 = new BigDecimal(82086690);
		BigDecimal cedula14 = new BigDecimal(50477869);

		UsuarioDTO oUsuarioNuevo = new UsuarioDTO(Fabrica.getSHA256Hash("12345678a"), cedula, dateNico,
				"nicolas.melendez@utec.edu.uy", "nico@hotmail.com", "nicolas.melendez", "Meléndez", "Nicolás",
				"Gutierrez", "Nahuel", "099616534", "S", "S", generoCreado, itrCreada, localidadCreada1, rolCreado);

		UsuarioDTO oUsuarioNuevo2 = new UsuarioDTO(Fabrica.getSHA256Hash("12345678a"), cedula2, dateRony,
				"rony.palacios@utec.edu.uy", "rony@adinet.com", "rony.palacios", "Palacios", "Rony", "Guerra", "",
				"099652242", "S", "S", generoCreado, itrCreada, localidadCreada154, rolCreado3);

		UsuarioDTO oUsuarioNuevo3 = new UsuarioDTO(Fabrica.getSHA256Hash("12345678a"), cedula3, dateMartina,
				"matias.luzardo@utec.edu.uy", "matias@gmail.com", "matias.luzardo", "Luzardo", "Matias", "Frade",
				"Gabriel", "092344423", "S", "N", generoCreado, itrCreada, localidadCreada125, rolCreado4);

		UsuarioDTO oUsuarioNuevo4 = new UsuarioDTO(Fabrica.getSHA256Hash("P4$$word"), cedula4, dateValentina,
				"valentina.hernandez@utec.edu.uy", "vale@gmail.com", "valentina.hernandez", "Hernandez", "Valentina",
				"Modino", "", "094567443", "S", "S", generoCreado2, itrCreada, localidadCreada154, rolCreado);

		UsuarioDTO oUsuarioNuevo5 = new UsuarioDTO(Fabrica.getSHA256Hash("P4$$word"), cedula5, dateMartina,
				"martina.fernandez@estudiantes.utec.edu.uy", "marti@gmail.com", "martina.fernandez", "Fernandez",
				"Martina", "Baladón", "", "095672344", "S", "S", generoCreado2, itrCreada, localidadCreada16,
				rolCreado2);

		UsuarioDTO oUsuarioNuevo6 = new UsuarioDTO(Fabrica.getSHA256Hash("P4$$word"), cedula6, datePepe,
				"pepe.lopez@estudiantes.utec.edu.uy", "pepe@gmail.com", "pepe.lopez", "Lopez", "Pepe", "Roman", "",
				"091342344", "S", "S", generoCreado3, itrCreada, localidadCreada12, rolCreado2);

		UsuarioDTO oUsuarioNuevo7 = new UsuarioDTO(Fabrica.getSHA256Hash("23456789b"), cedula7, date1,
				"juan.gonzalez@estudiantes.utec.edu.uy", "juan@gmail.com", "juan.gonzalez", "Gonzalez", "Juan",
				"Carlos", "Herrera", "098765432", "S", "S", generoCreado2, itrCreada2, localidadCreada8, rolCreado2);

//		UsuarioDTO oUsuarioNuevo8 = new UsuarioDTO("34567890c", cedula8, date2,
//				"maria.rodriguez@estudiantes.utec.edu.uy", "maria@gmail.com", "maria.rodriguez", "Rodriguez", "Maria",
//				"Fernanda", "Furtado", "095623421", "S", "N", generoCreado, itrCreada, localidadCreada10, rolCreado2);

		UsuarioDTO oUsuarioNuevo9 = new UsuarioDTO(Fabrica.getSHA256Hash("45678901d"), cedula9, date3,
				"ana.martinez@estudiantes.utec.edu.uy", "ana@gmail.com", "ana.martinez", "Martinez", "Ana", "Laura",
				"Silva", "092345678", "S", "S", generoCreado2, itrCreada2, localidadCreada14, rolCreado2);

		UsuarioDTO oUsuarioNuevo10 = new UsuarioDTO(Fabrica.getSHA256Hash("56789012e"), cedula10, date4,
				"luis.perez@estudiantes.utec.edu.uy", "luis@gmail.com", "luis.perez", "Perez", "Luis", "Alberto",
				"Urterio", "096734521", "S", "S", generoCreado, itrCreada, localidadCreada18, rolCreado2);

		UsuarioDTO oUsuarioNuevo11 = new UsuarioDTO(Fabrica.getSHA256Hash("67890123f"), cedula11, date5,
				"laura.garcia@estudiantes.utec.edu.uy", "laura@gmail.com", "laura.garcia", "Garcia", "Laura", "Isabel",
				"", "091234567", "S", "N", generoCreado2, itrCreada2, localidadCreada21, rolCreado2);

		UsuarioDTO oUsuarioNuevo12 = new UsuarioDTO(Fabrica.getSHA256Hash("78901234g"), cedula12, date6,
				"pedro.fernandez@utec.edu.uy", "pedro@gmail.com", "pedro.fernandez", "Fernandez", "Pedro", "Jose",
				"Gonzalez", "098765432", "S", "N", generoCreado, itrCreada, localidadCreada23, rolCreado3);

		UsuarioDTO oUsuarioNuevo13 = new UsuarioDTO(Fabrica.getSHA256Hash("89012345h"), cedula13, date7,
				"carla.sanchez@utec.edu.uy", "carla@gmail.com", "carla.sanchez", "Sanchez", "Carla", "Lucia", "",
				"092345678", "N", "S", generoCreado2, itrCreada2, localidadCreada2, rolCreado4);

		UsuarioDTO oUsuarioNuevo14 = new UsuarioDTO(Fabrica.getSHA256Hash("P4$$word"), cedula14, dateValentina,
				"jose.perez@utec.edu.uy", "jose.perez@gmail.com", "jose.perez", "Pérez", "José", "Ferreira", "Juan",
				"094567443", "S", "S", generoCreado3, itrCreada, localidadCreada154, rolCreado);

		System.out.println(
				oUsuarioNuevo.getIdUsuario() + " ------------------------------------------------------------ ");

//		var oUsuarioCreado = Crear.usuario(oUsuarioNuevo, new Analista());
		var oUsuarioCreado4 = ServiceUsuario.crear(oUsuarioNuevo4, new AnalistaDTO());

		var oUsuarioCreado5 = ServiceUsuario.crear(oUsuarioNuevo5, new EstudianteDTO("2019", new BigDecimal(4)));
		var oUsuarioCreado6 = ServiceUsuario.crear(oUsuarioNuevo6, new EstudianteDTO("2022", new BigDecimal(1)));
		var oUsuarioCreado7 = ServiceUsuario.crear(oUsuarioNuevo7, new EstudianteDTO("2018", new BigDecimal(4)));
//		var oUsuarioCreado8 = ServiceUsuario.crear(oUsuarioNuevo8, new EstudianteDTO("2020", new BigDecimal(3)));
		var oUsuarioCreado9 = ServiceUsuario.crear(oUsuarioNuevo9, new EstudianteDTO("2019", new BigDecimal(2)));
		var oUsuarioCreado10 = ServiceUsuario.crear(oUsuarioNuevo10, new EstudianteDTO("2023", new BigDecimal(1)));
		var oUsuarioCreado11 = ServiceUsuario.crear(oUsuarioNuevo11, new EstudianteDTO("2019", new BigDecimal(3)));
		var oUsuarioCreado14 = ServiceUsuario.crear(oUsuarioNuevo14, new AnalistaDTO());

		AreaDTO oArea = new AreaDTO("Programación");
		AreaDTO oArea2 = new AreaDTO("Testing");
		AreaDTO oArea3 = new AreaDTO("Base de Datos");
		AreaDTO oArea4 = new AreaDTO("Infraestructura");

		var oAreaCreada = ServiceArea.crear(oArea);
		var oAreaCreada2 = ServiceArea.crear(oArea2);
		var oAreaCreada3 = ServiceArea.crear(oArea3);
		var oAreaCreada4 = ServiceArea.crear(oArea4);

		var oUsuarioCreado3 = ServiceUsuario.crear(oUsuarioNuevo2, new TutorDTO(oAreaCreada));
		var oUsuarioCreado2 = ServiceUsuario.crear(oUsuarioNuevo3, new TutorDTO(oAreaCreada2));
		var oUsuarioCreado12 = ServiceUsuario.crear(oUsuarioNuevo12, new TutorDTO(oAreaCreada));
		var oUsuarioCreado13 = ServiceUsuario.crear(oUsuarioNuevo13, new TutorDTO(oAreaCreada2));

		Estado oEstado1 = new Estado("S", "Ingresado");
		Estado oEstado2 = new Estado("S", "En proceso");
		Estado oEstado3 = new Estado("S", "Finalizado");

		var estadoGuardado = ServiceEstado.crearEstado(oEstado1);
		var estadoGuardado2 = ServiceEstado.crearEstado(oEstado2);
		var estadoGuardado3 = ServiceEstado.crearEstado(oEstado3);

		// Crear los objetos Evento con los rangos de fechas correspondientes
		Evento oEvento1 = new Evento(new Timestamp(124, 3, 30, 8, 0, 0, 0), new Timestamp(124, 2, 1, 8, 0, 0, 0),
				"Inglés", new Itr(itrCreada));
		Evento oEvento2 = new Evento(new Timestamp(124, 5, 24, 8, 0, 0, 0), new Timestamp(124, 2, 1, 8, 0, 0, 0),
				"Curso Impresión 3D", new Itr(itrCreada2));
		Evento oEvento3 = new Evento(new Timestamp(124, 5, 6, 8, 0, 0, 0), new Timestamp(124, 2, 1, 8, 0, 0, 0),
				"Vinculación con el medio 2023", new Itr(itrCreada3));
		Evento oEvento4 = new Evento(new Timestamp(124, 7, 5, 8, 0, 0, 0), new Timestamp(124, 2, 1, 8, 0, 0, 0),
				"Conferencia de Inteligencia Artificial", new Itr(itrCreada2));
		Evento oEvento5 = new Evento(new Timestamp(124, 5, 14, 8, 0, 0, 0), new Timestamp(124, 2, 14, 8, 0, 0, 0),
				"Hackathon de Desarrollo Web", new Itr(itrCreada));
		Evento oEvento6 = new Evento(new Timestamp(124, 6, 1, 8, 0, 0, 0), new Timestamp(124, 2, 12, 8, 0, 0, 0),
				"Taller de Ciberseguridad", new Itr(itrCreada3));
		Evento oEvento7 = new Evento(new Timestamp(124, 7, 16, 8, 0, 0, 0), new Timestamp(124, 2, 8, 8, 0, 0, 0),
				"Congreso de Big Data", new Itr(itrCreada3));
		Evento oEvento8 = new Evento(new Timestamp(124, 7, 12, 8, 0, 0, 0), new Timestamp(124, 2, 4, 8, 0, 0, 0),
				"Charla sobre Blockchain", new Itr(itrCreada));
		Evento oEvento9 = new Evento(new Timestamp(124, 5, 1, 8, 0, 0, 0), new Timestamp(124, 2, 11, 8, 0, 0, 0),
				"Feria de Startups", new Itr(itrCreada));
		Evento oEvento10 = new Evento(new Timestamp(124, 6, 1, 8, 0, 0, 0), new Timestamp(124, 2, 12, 8, 0, 0, 0),
				"Encuentro de Innovación Tecnológica", new Itr(itrCreada3));
		Evento oEvento11 = new Evento(new Timestamp(124, 7, 1, 8, 0, 0, 0), new Timestamp(124, 3, 12, 8, 0, 0, 0),
				"Workshop de Programación en Python", new Itr(itrCreada3));
		Evento oEvento12 = new Evento(new Timestamp(124, 6, 1, 8, 0, 0, 0), new Timestamp(124, 2, 1, 8, 0, 0, 0),
				"Conferencia de Experiencia de Usuario", new Itr(itrCreada));
		Evento oEvento13 = new Evento(new Timestamp(124, 4, 1, 8, 0, 0, 0), new Timestamp(124, 4, 6, 8, 0, 0, 0),
				"Hackathon de Internet de las Cosas", new Itr(itrCreada3));
		Evento oEvento14 = new Evento(new Timestamp(124, 6, 1, 8, 0, 0, 0), new Timestamp(124, 3, 1, 8, 0, 0, 0),
				"Taller de Desarrollo de Aplicaciones Móviles", new Itr(itrCreada));

		var eventoGuardado = ServiceEvento.crearEvento(oEvento1);
		var eventoGuardado2 = ServiceEvento.crearEvento(oEvento2);
		var eventoGuardado3 = ServiceEvento.crearEvento(oEvento3);
		var eventoGuardado4 = ServiceEvento.crearEvento(oEvento4);
		var eventoGuardado5 = ServiceEvento.crearEvento(oEvento5);
		var eventoGuardado6 = ServiceEvento.crearEvento(oEvento6);
		var eventoGuardado7 = ServiceEvento.crearEvento(oEvento7);
		var eventoGuardado8 = ServiceEvento.crearEvento(oEvento8);
		var eventoGuardado9 = ServiceEvento.crearEvento(oEvento9);
		var eventoGuardado10 = ServiceEvento.crearEvento(oEvento10);
		var eventoGuardado11 = ServiceEvento.crearEvento(oEvento11);
		var eventoGuardado12 = ServiceEvento.crearEvento(oEvento12);
		var eventoGuardado13 = ServiceEvento.crearEvento(oEvento13);
		var eventoGuardado14 = ServiceEvento.crearEvento(oEvento14);

		Estudiante oEstudiante = new Estudiante(ServiceEstudiante.listar().get(1));
		Estudiante oEstudiante2 = new Estudiante(ServiceEstudiante.listar().get(0));

		var oEventoCreado = ServiceEvento.listarEventos().get(0);
		var oEventoCreado2 = ServiceEvento.listarEventos().get(1);
		var oEventoCreado3 = ServiceEvento.listarEventos().get(2);
		var oEventoCreado4 = ServiceEvento.listarEventos().get(3);
		var oEventoCreado5 = ServiceEvento.listarEventos().get(4);
		var oEventoCreado6 = ServiceEvento.listarEventos().get(5);
		var oEventoCreado7 = ServiceEvento.listarEventos().get(6);
		var oEventoCreado8 = ServiceEvento.listarEventos().get(7);
		var oEventoCreado13 = ServiceEvento.listarEventos().get(13);

		Tipo oTipo = new Tipo("Transporte", "S",
				"Se hace constar que &nombreCompleto& con Cédula de Identidad Nº &cedula& nacido en la fecha &fechaNacimiento& es estudiante de la Universidad Tecnológica (UTEC) y se encuentra cursando la carrera Licenciatura en Tecnologías de la Información dictada en el departamento de &localidadItr&.\n\nSe expide la presente constancia el día &fechaExpedido& para su presentación ante la empresa de transporte en el marco de la Resolución de la Dirección Nacional de Transporte, EXP 14/7/1012, que habilita a los estudiantes que cursan carreras en la UTEC, que al 1° de enero de cada año sean menores de 30 años, a acceder a la bonificación dispuesta por el Artículo 2.12 del Decreto 218/09 de fecha 11 de mayo de 2009, a adquirir boletos abono de valor igual al 50% (cincuenta por ciento).");

		Tipo oTipo2 = new Tipo("Presencial Común", "S",
				"Se deja constancia que &nombreCompleto& con Cédula de Identidad Nº &cedula& estudiante de la carrera Licenciatura en Tecnologías de la Información, ha asistido a la jornada presencial el día &fechaInicioEvento& en el &nombreItr&.\n\nSe expide la presente constancia el día &fechaExpedido&.");

		Tipo oTipo3 = new Tipo("Presencial Prueba", "S",
				"Se deja constancia que &nombreCompleto& con Cédula de Identidad Nº &cedula& estudiante de la carrera Licenciatura en Tecnologías de la Información, ha asistido a la prueba presencial del evento &nombreEvento& el día &fechaInicioEvento& en el &nombreItr&.\n\nSe expide la presente constancia el día &fechaExpedido&.");

		Tipo oTipo4 = new Tipo("Estudiante Activo", "S",
				"Se deja constancia que &nombreCompleto& con Cédula de Identidad Nº &cedula& es estudiante de esta institución y se encuentra cursando la carrera de Licenciatura en Tecnologías de la Información que se desarrolla en el departamento de &localidadItr&.\n\nAsiste a clase cada 15 días entre 9:00 AM y las 5:00 PM.\n\nSe expide la presente constancia el día &fechaExpedido&.");

		Tipo oTipo5 = new Tipo("Examen", "S",
				"Se deja constancia que &nombreCompleto& con Cédula de Identidad Nº &cedula& es estudiante de esta institución y se encuentra cursando la carrera de Licenciatura en Tecnologías de la Información.\n\nEl día &fechaInicioEvento& ha rendido el examen de &nombreEvento&.\n\nSe expide la presente constancia el día &fechaExpedido&.");

		Tipo oTipo6 = new Tipo("Vinculación con el Medio", "S",
				"Se deja constancia que &nombreCompleto& con Cédula de Identidad Nº &cedula& estudiante de la carrera Licenciatura en Tecnologías de la Información, ha obtenido los créditos por cursar &nombreEvento&.\n\nSe expide la presente constancia el día &fechaExpedido&.");

		Tipo oTipo7 = new Tipo("UTEC Innova", "S",
				"Se deja constancia que &nombreCompleto& con Cédula de Identidad Nº &cedula& estudiante de la carrera Licenciatura en Tecnologías de la Información, ha obtenido los créditos por cursar &nombreEvento&.\n\nSe expide la presente constancia el día &fechaExpedido&.");

		Tipo oTipo8 = new Tipo("Optativa", "S",
				"Se deja constancia que &nombreCompleto& con Cédula de Identidad Nº &cedula& estudiante de la carrera Licenciatura en Tecnologías de la Información, ha obtenido los créditos por cursar &nombreEvento&.\n\nSe expide la presente constancia el día &fechaExpedido&.");

		boolean tipoCreado = ServiceTipo.crearTipoConstancia(oTipo);
		boolean tipoCreado2 = ServiceTipo.crearTipoConstancia(oTipo2);
		boolean tipoCreado3 = ServiceTipo.crearTipoConstancia(oTipo3);
		boolean tipoCreado4 = ServiceTipo.crearTipoConstancia(oTipo4);
		boolean tipoCreado5 = ServiceTipo.crearTipoConstancia(oTipo5);
		boolean tipoCreado6 = ServiceTipo.crearTipoConstancia(oTipo6);
		boolean tipoCreado7 = ServiceTipo.crearTipoConstancia(oTipo7);
		boolean tipoCreado8 = ServiceTipo.crearTipoConstancia(oTipo8);

		var tipo1 = ServiceTipo.listarTiposConstancias().get(0);
		var tipo2 = ServiceTipo.listarTiposConstancias().get(1);
		var tipo3 = ServiceTipo.listarTiposConstancias().get(2);
		var tipo4 = ServiceTipo.listarTiposConstancias().get(3);
		var tipo5 = ServiceTipo.listarTiposConstancias().get(4);
		var tipo6 = ServiceTipo.listarTiposConstancias().get(5);
		var tipo7 = ServiceTipo.listarTiposConstancias().get(6);
		var tipo8 = ServiceTipo.listarTiposConstancias().get(7);

		// Constancia(String detalle, Date fechaHora, Estado estado, Estudiante
		// estudiante, Evento evento)
		Constancia oConstancia1 = new Constancia("Necesito renovar mi abono.", oEstudiante, oEventoCreado2, tipo1, "S");
		Constancia oConstancia2 = new Constancia("No me dieron los créditos.", oEstudiante2, oEventoCreado, tipo2, "S");
		Constancia oConstancia3 = new Constancia("Necesito una constancia de asistencia.", oEstudiante2, oEventoCreado4,
				tipo3, "S");
		Constancia oConstancia4 = new Constancia(
				"Hola! Necesito que se me valide que soy estudiante activo de esta carrera.", oEstudiante,
				oEventoCreado13, tipo4, "S");
		Constancia oConstancia5 = new Constancia(
				"Necesito una constancia para el trabajo de que realicé el examen de este evento.", oEstudiante2,
				oEventoCreado5, tipo5, "S");
		Constancia oConstancia6 = new Constancia(
				"Necesito una constancia de que termine vinculación con el medio 2023.", oEstudiante2, oEventoCreado3,
				tipo6, "S");
		Constancia oConstancia7 = new Constancia(
				"Hice un curso de UTEC Innova y no tengo una constancia que necesito para mi trabajo.", oEstudiante2,
				oEventoCreado7, tipo7, "S");
		Constancia oConstancia8 = new Constancia("Realicé esta optativa y necesito una constancia de esta.",
				oEstudiante2, oEventoCreado8, tipo8, "S");

		var constanciaGuardada = ServiceConstancia.crearConstancia(oConstancia1);
		var constanciaGuardada2 = ServiceConstancia.crearConstancia(oConstancia2);
		var constanciaGuardada3 = ServiceConstancia.crearConstancia(oConstancia3);
//		var constanciaGuardada4 = ServiceConstancia.crearConstancia(oConstancia4);
//		var constanciaGuardada5 = ServiceConstancia.crearConstancia(oConstancia5);
//		var constanciaGuardada6 = ServiceConstancia.crearConstancia(oConstancia6);
//		var constanciaGuardada7 = ServiceConstancia.crearConstancia(oConstancia7);
//		var constanciaGuardada8 = ServiceConstancia.crearConstancia(oConstancia8);

		var evento = ServiceEvento.listarEventos().get(0);
		var evento2 = ServiceEvento.listarEventos().get(1);
		var evento3 = ServiceEvento.listarEventos().get(2);
		var evento4 = ServiceEvento.listarEventos().get(3);
		var evento5 = ServiceEvento.listarEventos().get(4);
		var evento6 = ServiceEvento.listarEventos().get(5);
		var evento7 = ServiceEvento.listarEventos().get(6);

		var estudiante = ServiceEstudiante.listar().get(0);
		var estudiante2 = ServiceEstudiante.listar().get(1);

		LocalDate localDate = LocalDate.now();

		// Sumar un día
		LocalDate nuevaLocalDate = localDate.plusDays(1);

		// Convertir LocalDate a Date
		Date nuevaFecha = Date.from(nuevaLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		LocalDate localDate2 = LocalDate.now();

		// Sumar un día
		LocalDate nuevaLocalDate2 = localDate2.plusDays(2);

		// Convertir LocalDate a Date
		Date nuevaFecha2 = Date.from(nuevaLocalDate2.atStartOfDay(ZoneId.systemDefault()).toInstant());

		LocalDate localDate3 = LocalDate.now();

		// Sumar un día
		LocalDate nuevaLocalDate3 = localDate3.plusDays(3);

		// Convertir LocalDate a Date
		Date nuevaFecha3 = Date.from(nuevaLocalDate3.atStartOfDay(ZoneId.systemDefault()).toInstant());

		EstadoConstanciaPK idECPK1 = new EstadoConstanciaPK(ServiceConstancia.getById(1L).getIdConstancia(),
				ServiceAnalista.listar().get(0).getIdAnalista(), new Date());

		EstadoConstanciaPK idECPK2 = new EstadoConstanciaPK(ServiceConstancia.getById(2L).getIdConstancia(),
				ServiceAnalista.listar().get(0).getIdAnalista(), new Date());

		EstadoConstanciaPK idECPK3 = new EstadoConstanciaPK(ServiceConstancia.getById(2L).getIdConstancia(),
				ServiceAnalista.listar().get(0).getIdAnalista(), nuevaFecha);

		EstadoConstanciaPK idECPK4 = new EstadoConstanciaPK(ServiceConstancia.getById(3L).getIdConstancia(),
				ServiceAnalista.listar().get(0).getIdAnalista(), new Date());

		EstadoConstanciaPK idECPK5 = new EstadoConstanciaPK(ServiceConstancia.getById(3L).getIdConstancia(),
				ServiceAnalista.listar().get(0).getIdAnalista(), nuevaFecha2);

		EstadoConstanciaPK idECPK6 = new EstadoConstanciaPK(ServiceConstancia.getById(3L).getIdConstancia(),
				ServiceAnalista.listar().get(0).getIdAnalista(), nuevaFecha3);

		EstadoConstancia estadoConstancia1 = new EstadoConstancia(idECPK1, "Necesito renovar mi abono.",
				new Analista(ServiceAnalista.listar().get(0)), ServiceConstancia.getById(1L),
				ServiceEstado.listarEstados().get(0));

		EstadoConstancia estadoConstancia21 = new EstadoConstancia(idECPK2,
				"Di la prueba de inglés pero no me dieron los créditos.", new Analista(ServiceAnalista.listar().get(0)),
				ServiceConstancia.getById(2L), ServiceEstado.listarEstados().get(0));
		EstadoConstancia estadoConstancia22 = new EstadoConstancia(idECPK3,
				"Nos pondremos en proceso para darte tus creditos", new Analista(ServiceAnalista.listar().get(0)),
				ServiceConstancia.getById(2L), ServiceEstado.listarEstados().get(1));

		EstadoConstancia estadoConstancia31 = new EstadoConstancia(idECPK4,
				"Necesito una constancia porque terminé el Hackatón de Desarrollo Web.",
				new Analista(ServiceAnalista.listar().get(0)), ServiceConstancia.getById(3L),
				ServiceEstado.listarEstados().get(0));
		EstadoConstancia estadoConstancia32 = new EstadoConstancia(idECPK5,
				"Dale ya nos pondremos en accion para resolver la problematica.",
				new Analista(ServiceAnalista.listar().get(0)), ServiceConstancia.getById(3L),
				ServiceEstado.listarEstados().get(1));
		EstadoConstancia estadoConstancia33 = new EstadoConstancia(idECPK6,
				"Listo ya esta creada la constancia la puedes descargar.",
				new Analista(ServiceAnalista.listar().get(0)), ServiceConstancia.getById(3L),
				ServiceEstado.listarEstados().get(2));

		var estadoConstanciaGuardado1 = ServiceEstadoConstancia.crearEstadoConstancia(estadoConstancia1);

		var estadoConstanciaGuardado21 = ServiceEstadoConstancia.crearEstadoConstancia(estadoConstancia21);
		var estadoConstanciaGuardado22 = ServiceEstadoConstancia.crearEstadoConstancia(estadoConstancia22);

		var estadoConstanciaGuardado3 = ServiceEstadoConstancia.crearEstadoConstancia(estadoConstancia31);
		var estadoConstanciaGuardado32 = ServiceEstadoConstancia.crearEstadoConstancia(estadoConstancia32);
		var estadoConstanciaGuardado33 = ServiceEstadoConstancia.crearEstadoConstancia(estadoConstancia33);

		// EventoEstudiantePK(long idEvento, long idEstudiante)
		EventoEstudiantePK id = new EventoEstudiantePK(evento.getIdEvento(), estudiante.getIdEstudiante());
		EventoEstudiantePK id2 = new EventoEstudiantePK(evento.getIdEvento(), estudiante2.getIdEstudiante());
		EventoEstudiantePK id3 = new EventoEstudiantePK(evento2.getIdEvento(), estudiante.getIdEstudiante());
		EventoEstudiantePK id4 = new EventoEstudiantePK(evento3.getIdEvento(), estudiante.getIdEstudiante());
		EventoEstudiantePK id5 = new EventoEstudiantePK(evento4.getIdEvento(), estudiante.getIdEstudiante());
		EventoEstudiantePK id6 = new EventoEstudiantePK(evento5.getIdEvento(), estudiante.getIdEstudiante());
		EventoEstudiantePK id7 = new EventoEstudiantePK(evento6.getIdEvento(), estudiante.getIdEstudiante());
		EventoEstudiantePK id8 = new EventoEstudiantePK(evento7.getIdEvento(), estudiante.getIdEstudiante());

		// EventoEstudiante(EventoEstudiantePK id, String asistencia, BigDecimal
		// calificacion)
		BigDecimal nota0 = new BigDecimal(0);
		BigDecimal nota = new BigDecimal(1);
		BigDecimal nota2 = new BigDecimal(2);
		BigDecimal nota3 = new BigDecimal(3);
		BigDecimal nota4 = new BigDecimal(4);
		BigDecimal nota5 = new BigDecimal(5);

		EventoEstudiante oEventoEstudiante = new EventoEstudiante(id, "S", nota);
		EventoEstudiante oEventoEstudiante2 = new EventoEstudiante(id2, "N", nota0);
		EventoEstudiante oEventoEstudiante3 = new EventoEstudiante(id3, "S", nota2);
		EventoEstudiante oEventoEstudiante4 = new EventoEstudiante(id4, "S", nota2);
		EventoEstudiante oEventoEstudiante5 = new EventoEstudiante(id5, "S", nota);
		EventoEstudiante oEventoEstudiante6 = new EventoEstudiante(id6, "S", nota5);
		EventoEstudiante oEventoEstudiante7 = new EventoEstudiante(id7, "N", nota0);
		EventoEstudiante oEventoEstudiante8 = new EventoEstudiante(id8, "S", nota2);

		boolean creado = ServiceEventoEstudiante.crearEventoEstudiante(oEventoEstudiante);
		boolean creado2 = ServiceEventoEstudiante.crearEventoEstudiante(oEventoEstudiante2);
		boolean creado3 = ServiceEventoEstudiante.crearEventoEstudiante(oEventoEstudiante3);
		boolean creado4 = ServiceEventoEstudiante.crearEventoEstudiante(oEventoEstudiante4);
		boolean creado5 = ServiceEventoEstudiante.crearEventoEstudiante(oEventoEstudiante5);
		boolean creado6 = ServiceEventoEstudiante.crearEventoEstudiante(oEventoEstudiante6);
		boolean creado7 = ServiceEventoEstudiante.crearEventoEstudiante(oEventoEstudiante7);
		boolean creado8 = ServiceEventoEstudiante.crearEventoEstudiante(oEventoEstudiante8);

		Reclamo oReclamo1 = new Reclamo("No me cargaron los datos de mi perfil", new Estudiante(estudiante),
				eventoGuardado, "S");
		Reclamo oReclamo2 = new Reclamo("No puedo acceder a mis calificaciones", new Estudiante(estudiante2),
				eventoGuardado2, "S");
		Reclamo oReclamo3 = new Reclamo("No puedo acceder a mis notas finales", new Estudiante(estudiante),
				eventoGuardado3, "S");
		Reclamo oReclamo4 = new Reclamo("Error en la matrícula de las asignaturas", new Estudiante(estudiante2),
				eventoGuardado4, "S");
		Reclamo oReclamo5 = new Reclamo("No se actualizaron mis datos personales", new Estudiante(estudiante),
				eventoGuardado5, "S");
		Reclamo oReclamo6 = new Reclamo("No recibí el correo de confirmación de inscripción",
				new Estudiante(estudiante2), eventoGuardado6, "S");
		Reclamo oReclamo7 = new Reclamo("Problemas con el pago de la matrícula", new Estudiante(estudiante),
				eventoGuardado7, "S");
		Reclamo oReclamo8 = new Reclamo("No aparece mi horario de clases", new Estudiante(estudiante2), eventoGuardado8,
				"S");

		var reclamo = ServiceReclamo.crear(oReclamo1);
		var reclamo2 = ServiceReclamo.crear(oReclamo2);
		var reclamo3 = ServiceReclamo.crear(oReclamo3);
		var reclamo4 = ServiceReclamo.crear(oReclamo4);
		var reclamo5 = ServiceReclamo.crear(oReclamo5);
		var reclamo6 = ServiceReclamo.crear(oReclamo6);
		var reclamo7 = ServiceReclamo.crear(oReclamo7);
		var reclamo8 = ServiceReclamo.crear(oReclamo8);

		long idReclamo1 = ServiceReclamo.listar().get(0).getIdReclamo();
		long idReclamo2 = ServiceReclamo.listar().get(1).getIdReclamo();
		long idReclamo3 = ServiceReclamo.listar().get(2).getIdReclamo();
		long idReclamo4 = ServiceReclamo.listar().get(3).getIdReclamo();
		long idReclamo5 = ServiceReclamo.listar().get(4).getIdReclamo();
		long idReclamo6 = ServiceReclamo.listar().get(5).getIdReclamo();
		long idReclamo7 = ServiceReclamo.listar().get(6).getIdReclamo();
		long idReclamo8 = ServiceReclamo.listar().get(7).getIdReclamo();

		long idAnalista = ServiceAnalista.getById(1).getIdAnalista();
		long idAnalista2 = ServiceAnalista.getById(2).getIdAnalista();

		Date now = new Date();
		long currentTime = now.getTime();

		EstadoReclamoPK oEstadoReclamoPK1 = new EstadoReclamoPK(idReclamo1, idAnalista, new Date(currentTime));
		EstadoReclamoPK oEstadoReclamoPK3 = new EstadoReclamoPK(idReclamo1, idAnalista2, new Date(currentTime + 1000)); // +1
																														// segundo
		EstadoReclamoPK oEstadoReclamoPK2 = new EstadoReclamoPK(idReclamo2, idAnalista, new Date(currentTime + 2000)); // +2
																														// segundos
		EstadoReclamoPK oEstadoReclamoPK4 = new EstadoReclamoPK(idReclamo2, idAnalista2, new Date(currentTime + 3000)); // +3
																														// segundos

		EstadoReclamoPK oEstadoReclamoPK5 = new EstadoReclamoPK(idReclamo3, idAnalista, new Date());
		EstadoReclamoPK oEstadoReclamoPK6 = new EstadoReclamoPK(idReclamo3, idAnalista2, new Date(currentTime + 4000));
		EstadoReclamoPK oEstadoReclamoPK7 = new EstadoReclamoPK(idReclamo4, idAnalista, new Date());
		EstadoReclamoPK oEstadoReclamoPK8 = new EstadoReclamoPK(idReclamo4, idAnalista2, new Date(currentTime + 5000));
		EstadoReclamoPK oEstadoReclamoPK9 = new EstadoReclamoPK(idReclamo5, idAnalista, new Date());
		EstadoReclamoPK oEstadoReclamoPK10 = new EstadoReclamoPK(idReclamo5, idAnalista2, new Date(currentTime + 6000));
		EstadoReclamoPK oEstadoReclamoPK11 = new EstadoReclamoPK(idReclamo6, idAnalista, new Date());
		EstadoReclamoPK oEstadoReclamoPK12 = new EstadoReclamoPK(idReclamo6, idAnalista2, new Date(currentTime + 7000));
		EstadoReclamoPK oEstadoReclamoPK13 = new EstadoReclamoPK(idReclamo7, idAnalista, new Date());
		EstadoReclamoPK oEstadoReclamoPK14 = new EstadoReclamoPK(idReclamo7, idAnalista2, new Date(currentTime + 8000));
		EstadoReclamoPK oEstadoReclamoPK15 = new EstadoReclamoPK(idReclamo8, idAnalista, new Date());
		EstadoReclamoPK oEstadoReclamoPK16 = new EstadoReclamoPK(idReclamo8, idAnalista2, new Date(currentTime + 9000));

		EstadoReclamo oEstadoReclamo1 = new EstadoReclamo(oEstadoReclamoPK1,
				"Cuando entro a mi perfil no aparece mi información personal.", ServiceEstado.listarEstados().get(0));
		EstadoReclamo oEstadoReclamo2 = new EstadoReclamo(oEstadoReclamoPK3,
				"Cuando entro a mi perfil no aparece mi información personal.", ServiceEstado.listarEstados().get(0));
		EstadoReclamo oEstadoReclamo3 = new EstadoReclamo(oEstadoReclamoPK2,
				"El listado donde deberían aparecer mis calificaciones se encuentra vacío.",
				ServiceEstado.listarEstados().get(0));
		EstadoReclamo oEstadoReclamo4 = new EstadoReclamo(oEstadoReclamoPK4,
				"El listado donde deberían aparecer mis calificaciones se encuentra vacío.",
				ServiceEstado.listarEstados().get(0));
		EstadoReclamo oEstadoReclamo5 = new EstadoReclamo(oEstadoReclamoPK5,
				"Mis notas finales aún no figuran como cargadas.", ServiceEstado.listarEstados().get(0));
		EstadoReclamo oEstadoReclamo6 = new EstadoReclamo(oEstadoReclamoPK6,
				"Mis notas finales aún no figuran como cargadas.", ServiceEstado.listarEstados().get(0));
		EstadoReclamo oEstadoReclamo7 = new EstadoReclamo(oEstadoReclamoPK7,
				"En el apartado de asignaturas matriculadas aparecen cursos a los que no me anoté.",
				ServiceEstado.listarEstados().get(0));
		EstadoReclamo oEstadoReclamo8 = new EstadoReclamo(oEstadoReclamoPK8,
				"En el apartado de asignaturas matriculadas aparecen cursos a los que no me anoté.",
				ServiceEstado.listarEstados().get(0));
		EstadoReclamo oEstadoReclamo9 = new EstadoReclamo(oEstadoReclamoPK9,
				"Hice cambios en mi perfil para actualizar mi información pero siguen figurando los datos anteriores.",
				ServiceEstado.listarEstados().get(0));
		EstadoReclamo oEstadoReclamo10 = new EstadoReclamo(oEstadoReclamoPK10,
				"Hice cambios en mi perfil para actualizar mi información pero siguen figurando los datos anteriores.",
				ServiceEstado.listarEstados().get(0));
		EstadoReclamo oEstadoReclamo11 = new EstadoReclamo(oEstadoReclamoPK11,
				"Me auto-matriculé a varias asignaturas y aún no he recibido el correo de confirmación.",
				ServiceEstado.listarEstados().get(0));
		EstadoReclamo oEstadoReclamo12 = new EstadoReclamo(oEstadoReclamoPK12,
				"Me auto-matriculé a varias asignaturas y aún no he recibido el correo de confirmación.",
				ServiceEstado.listarEstados().get(0));
		EstadoReclamo oEstadoReclamo13 = new EstadoReclamo(oEstadoReclamoPK13,
				"La página no toma como válido el pago con tarjeta de crédito.", ServiceEstado.listarEstados().get(0));
		EstadoReclamo oEstadoReclamo14 = new EstadoReclamo(oEstadoReclamoPK14,
				"La página no toma como válido el pago con tarjeta de crédito.", ServiceEstado.listarEstados().get(0));
		EstadoReclamo oEstadoReclamo15 = new EstadoReclamo(oEstadoReclamoPK15,
				"Ya me anoté pero aún no me figuran los horarios correspondientes.",
				ServiceEstado.listarEstados().get(0));
		EstadoReclamo oEstadoReclamo16 = new EstadoReclamo(oEstadoReclamoPK16,
				"Ya me anoté pero aún no me figuran los horarios correspondientes.",
				ServiceEstado.listarEstados().get(0));

		boolean estadoReclamo = ServiceEstadoReclamo.crear(oEstadoReclamo1);
		boolean estadoReclamo2 = ServiceEstadoReclamo.crear(oEstadoReclamo2);
		boolean estadoReclamo3 = ServiceEstadoReclamo.crear(oEstadoReclamo3);
		boolean estadoReclamo4 = ServiceEstadoReclamo.crear(oEstadoReclamo4);
		boolean estadoReclamo5 = ServiceEstadoReclamo.crear(oEstadoReclamo5);
		boolean estadoReclamo6 = ServiceEstadoReclamo.crear(oEstadoReclamo6);
		boolean estadoReclamo7 = ServiceEstadoReclamo.crear(oEstadoReclamo7);
		boolean estadoReclamo8 = ServiceEstadoReclamo.crear(oEstadoReclamo8);
		boolean estadoReclamo9 = ServiceEstadoReclamo.crear(oEstadoReclamo9);
		boolean estadoReclamo10 = ServiceEstadoReclamo.crear(oEstadoReclamo10);
		boolean estadoReclamo11 = ServiceEstadoReclamo.crear(oEstadoReclamo11);
		boolean estadoReclamo12 = ServiceEstadoReclamo.crear(oEstadoReclamo12);
		boolean estadoReclamo13 = ServiceEstadoReclamo.crear(oEstadoReclamo13);
		boolean estadoReclamo14 = ServiceEstadoReclamo.crear(oEstadoReclamo14);
		boolean estadoReclamo15 = ServiceEstadoReclamo.crear(oEstadoReclamo15);
		boolean estadoReclamo16 = ServiceEstadoReclamo.crear(oEstadoReclamo16);

	}

}
