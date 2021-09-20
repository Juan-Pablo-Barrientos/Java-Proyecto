<%@ page language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="Styles/Style.css" rel="stylesheet" type="text/css" />

<link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
<meta charset="UTF-8">
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
					entre 8 y 16 caracteres al menos un digito, una minúscula y una
					mayúscula </label>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Confirmar contraseña</label> <input
					type="password" class="form-control" id="idContraseña2"
					placeholder="Ingrese contraseña nuevamente"
					name="InputConfirmaPassword" required>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Nickname</label> <input
					type="text" class="form-control" id="idNickname"
					placeholder="Ingrese nickname" name="InputNickname" required>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Telefono</label> <input type="text"
					pattern="^[0-9]+$" class="form-control" id="idTelefono"
					placeholder="Ingrese numero de telefono" name="InputTelefono"
					required>
			</div>
			<p></p>
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
	</script>
</body>
</html>