<%@ page language="java"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="Styles/Style.css" rel="stylesheet" type="text/css" />

<link rel="icon" href="img/logo_modificado.ico">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<meta charset="UTF-8">
<title>Java</title>



</head>
<body class="vertical-center" style="background-color: #F5F5F5;">
	<c:if test="${not empty User}">
		<div class="modal fade" id="modalExito" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Nombre o Email
							ya existentes!</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body"></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							data-bs-dismiss="modal">Entendido!</button>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<div class="container">
		<div class="row d-flex justify-content-center">
			<div class="col-md-6">
				<div class="login-form mt-4 p-4 color-principal text-dark shadow-lg"
					style="border-radius: 4px;">
					<form class="row g-3" role="form" style="margin: 20px;"
						action="SignUp" method="post" id="formID" name="frmSignUp"
						onSubmit="return ValidateEmail()">
						<h4>Registrarse</h4>

						<div class="col-12">
							<label for="exampleInputUsuario">Usuario</label> <input
								type="text" class="form-control" id="exampleInputUsuario"
								placeholder="Ingrese nombre de usuario" name="InputUsuario"
								value="${User.nombreUsuario}" required>
						</div>

						<div class="col-12">
							<label for="idEmail1">Email</label> <input type="email"
								class="form-control" id="idEmail1" placeholder="Ingrese email"
								name="InputEmail" value="${User.email}" required>
						</div>

						<div class="col-12">
							<label for="idEmail2">Confimar Email</label> <input type="email"
								class="form-control" id="idEmail2"
								placeholder="Ingrese email nuevamente" name="InputConfirmEmail"
								value="${User.email}" required>
						</div>

						<div class="col-12">
							<label for="idContraseña">Contraseña</label> <input
								type="password"
								pattern="^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,16}$"
								class="form-control" id="idContraseña" name="InputPassword"
								placeholder="Ingrese contraseña" value="${User.contraseña}"
								required> <label style="font-style: italic;">La
								contraseña debe tener entre 8 y 16 caracteres al menos un
								digito, una minúscula y una mayúscula </label>
						</div>

						<div class="col-12">
							<label for="idContraseña2">Confirmar contraseña</label> <input
								type="password" class="form-control" id="idContraseña2"
								name="InputConfirmaPassword"
								placeholder="Ingrese contraseña nuevamente"
								value="${User.contraseña}" required>
						</div>

						<div class="col-12">
							<label for="idNickname">Nickname</label> <input type="text"
								class="form-control" id="idNickname"
								placeholder="Ingrese nickname" name="InputNickname"
								value="${User.nickname}" required>
						</div>

						<div class="col-12">
							<label for="idTelefono">Telefono</label> <input type="text"
								pattern="^[0-9]+$" class="form-control" id="idTelefono"
								placeholder="Ingrese número de telefono" name="InputTelefono"
								value="${User.telefono}" required>
						</div>

						<div class="col-12">
							<label for="idFechaNacimiento">Fecha de nacimiento</label>
							<div></div>
							<input type="date" id="idFechaNacimiento"
								name="InputFechaNacimiento" value="${User.fechaNacimiento}"
								required>
						</div>
						<div class="col-12">
							<button type="submit" name="submit" class="boton">Confirmar
								registro</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>

	<script src="bootstrap/js/bootstrap.bundle.js"></script>
	<script
		src="https://unpkg.com/bootstrap-table@1.18.3/dist/bootstrap-table.min.js"></script>

	<script type="text/javascript">
		$(window).on('load', function() {
			$('#modalExito').modal('show');
		});
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

		$(function() {
			var dtToday = new Date();

			var month = dtToday.getMonth() + 1;
			var day = dtToday.getDate();
			var year = dtToday.getFullYear();

			if (month < 10)
				month = '0' + month.toString();
			if (day < 10)
				day = '0' + day.toString();

			var maxDate = year + '-' + month + '-' + day;
			$('#idFechaNacimiento').attr('max', maxDate);
		});
	</script>
</body>
</html>