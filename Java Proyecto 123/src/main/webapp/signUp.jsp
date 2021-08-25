<%@ page language="java"%>
<!DOCTYPE html>
<html>
<head>

<link href="Styles/Style.css" rel="stylesheet" type="text/css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Java</title>



</head>
<body style="background-color: black">
	<div class="container"
		style="border: 2px solid black; border-radius: 20px; background-color: gold; margin-top: 5%;">
		<h2 style="margin-left: 20px;">Registro</h2>
		<form role="form" style="margin: 20px;" action="SignUp" method="post"
			id="formID" name="frmSignUp" onSubmit="return ValidateEmail()">
			<div class="form-group">
				<label for="exampleInputEmail1">Usuario</label> <input type="text"
					class="form-control" id="exampleInputUsuario"
					placeholder="Ingrese nombre de usuario" name="InputUsuario"
					required>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Email</label> <input type="email"
					class="form-control" id="idEmail1" placeholder="Ingrese email"
					name="InputEmail" required>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Confimar Email</label> <input
					type="email" class="form-control" id="idEmail2"
					placeholder="Ingrese email nuevamente" name="InputConfirmEmail"
					required>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Contraseña</label> <input
					type="password"
					pattern="^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,16}$"
					class="form-control" id="idContraseña"
					placeholder="Ingrese Contraseña" name="InputPassword" required>
				<label style="font-style: italic;">La contraseña debe tener
					entre 8 y 16 caracteres al menos un digito, una minuscula y una
					mayuscula </label>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Confirmar contraseña</label> <input
					type="password" class="form-control" id="idContraseña2"
					placeholder="Ingrese contraseña nuevamente"
					name="InputConfirmaPassword" required>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Nombre de pila</label> <input
					type="text" class="form-control" id="idNickname"
					placeholder="Ingrese nombre de pila" name="InputNickname" required>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Telefono</label> <input type="text"
					pattern="^[0-9]+$" class="form-control" id="idTelefono"
					placeholder="Ingrese numero de telefono" name="InputTelefono"
					required>
			</div>
			<div class="form-group">
				<label for="InputFechaNacimiento">Fecha de nacimiento</label> <input type="date" id="idFechaNacimiento" name="InputFechaNacimiento" required> 
			</div>
			
			<p></p>
			<button type="submit" name="submit" class="btn btn-lg btn-primary">Ingresar</button>
		</form>
	</div>
	<script type="text/javascript">
		function ValidateEmail() {
			if (document.getElementById('idEmail1').value === document
					.getElementById('idEmail2').value) {
				if (document.frmSignUp.InputConfirmaPassword.value === document.frmSignUp.InputPassword.value) {
					return true
				} else {
					alert("Contraseña no coincide")
					return false

				}
			}
			alert("Email no coincide");
			return false
		}

		document.addEventListener(
						'DOMContentLoaded',
						function() {
							const monthNames = [ "Enero", "Febrero", "Marzo",
									"Abril", "Mayo", "Junio", "Julio",
									"Agosto", "Septiembre", "Octubre",
									"Noviembre", "Diciembre" ];
							let qntYears = 90;
							let selectYear = document.querySelector(".year");
							let selectMonth = document.querySelector(".month");
							let selectDay = document.querySelector(".day");
							let currentYear = new Date().getFullYear();

							for (var y = 0; y < qntYears; y++) {
								let date = new Date(currentYear);
								let yearElem = document.createElement("option");
								yearElem.value = currentYear
								yearElem.textContent = currentYear;
								selectYear.append(yearElem);
								currentYear--;
							}
							for (var m = 0; m < 12; m++) {
								let month = monthNames[m];
								let monthElem = document
										.createElement("option");
								monthElem.value = m;
								monthElem.textContent = month;
								selectMonth.append(monthElem);
							}

							var d = new Date();
							var month = d.getMonth();
							var year = d.getFullYear();
							var day = d.getDate();
							selectYear.addEventListener('change', Days);
							selectMonth.addEventListener('change', Days);

							Days();
							document.getElementById('dayId').value = day;

							function Days() {
								var year = document.getElementById('yearId').value;
								var month = parseInt(document
										.getElementById('monthId').value) + 1;
								var days = new Date(year, month, 0).getDate();
								removeOptions(document.getElementById('dayId'));
								for (var d = 1; d <= days; d++) {
									var dayElem = document
											.createElement("option");
									dayElem.value = d;
									dayElem.textContent = d;
									selectDay.append(dayElem);
								}

							}
						});

		function removeOptions(selectElement) {
			var i, L = selectElement.options.length - 1;
			for (i = L; i >= 0; i--) {
				selectElement.remove(i);
			}
		}
	</script>
</body>
</html>