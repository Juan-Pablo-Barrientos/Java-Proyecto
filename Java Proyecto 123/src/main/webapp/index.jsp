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
<link rel="icon" href="http://getbootstrap.com/favicon.ico">
<link href="Styles/Style.css" rel="stylesheet" type="text/css" />
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<title>Java</title>
</head>
<body style="background-color: black">
	<c:if test="${not empty result}">
		<div class="modal fade" id="modalLogin" data-bs-backdrop="static"
			data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="staticBackdropLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="staticBackdropLabel">Error de
							ingreso</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<c:out value="${result} "></c:out>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							data-bs-dismiss="modal">Entendido!</button>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<div class="container"
		style="border: 2px solid black; border-radius: 20px; background-color: #FF9800; margin-top: 20%; margin-bottom: 20%;">
		<form role="form" style="margin: 20px;" action="signin" method="post">

			<div class="form-group">
				<label for="exampleInputEmail1" style="color: white">Usuario
					o Email</label> <input type="text" class="form-control"
					id="exampleInputEmail1" placeholder="Ingrese email o usuario"
					name="InputEmail" required>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1" style="color: white">Contraseña</label>
				<input type="password" class="form-control"
					id="exampleInputPassword1" placeholder="Contraseña"
					name="InputPass" required>
			</div>
			<div class="checkbox">
				<label style="color: white"> <input type="checkbox"
					name=checkboxRemember> Recuérdame
				</label>
			</div>
			<button type="submit" class="boton">Ingresar</button>
			<div></div>
			<a href="signUp.jsp">Registrarse</a>
			<div></div>
			<a href="#">Olvidé mi contraseña</a>
		</form>

	</div>

	<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>

	<script src="bootstrap/js/bootstrap.bundle.js"></script>
	<script
		src="https://unpkg.com/bootstrap-table@1.18.3/dist/bootstrap-table.min.js"></script>

	<script>
		$(window).on('load', function() {
			$('#modalLogin').modal('show');
		});
	</script>

</body>
</html>