<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="img/logo_modificado.ico">
<link href="Styles/Style.css" rel="stylesheet" type="text/css" />
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<title>Java</title>
</head>
<body class="vertical-center" style="background-color: #F5F5F5;">

	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="login-form mt-4 p-4 color-principal text-dark shadow-lg"
					style="border-radius: 4px;">
					<form action="OlvideMiContraseña" method="post" class="row g-3">
						<h4>Olvide mi contraseña</h4>
						<div id="liveAlertPlaceholder"></div>
						<div class="col-12">
							<label for="exampleInputEmail1">Email</label> <input
							 class="form-control" id="exampleInputEmail1" type="email"
								placeholder="Ingrese su email" name="InputEmail"
								required>
						</div>
						<div class="col-12">
							<button type="submit" class="boton">Enviar email</button>
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

	<script>
	</script>

</body>
</html>