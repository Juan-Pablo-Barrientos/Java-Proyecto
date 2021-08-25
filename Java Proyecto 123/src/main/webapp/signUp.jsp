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
				<label for="exampleInputEmail1">Contrase�a</label> <input
					type="password"
					pattern="^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,16}$"
					class="form-control" id="idContrase�a"
					placeholder="Ingrese Contrase�a" name="InputPassword" required>
				<label style="font-style: italic;">La contrase�a debe tener
					entre 8 y 16 caracteres al menos un digito, una minuscula y una
					mayuscula </label>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Confirmar contrase�a</label> <input
					type="password" class="form-control" id="idContrase�a2"
					placeholder="Ingrese contrase�a nuevamente"
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
					alert("Contrase�a no coincide")
					return false

				}
			}
			alert("Email no coincide");
			return false
		}
	</script>
</body>
</html>