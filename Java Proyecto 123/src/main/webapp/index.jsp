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
	<c:if test="${not empty result}">
		<div class="modal fade" id="modalLogin" data-bs-backdrop="static"
			data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="staticBackdropLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="staticBackdropLabel">Error de
							ingreso!</h5>
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

	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="login-form mt-4 p-4 color-principal text-dark shadow-lg"
					style="border-radius: 4px;">
					<form action="signin" method="post" class="row g-3">
						<h4>Iniciar sesión</h4>
						<div id="liveAlertPlaceholder"></div>
						<div class="col-12">
							<label for="exampleInputEmail1">Usuario o Email</label> <input
								type="text" class="form-control" id="exampleInputEmail1"
								placeholder="Ingrese su email o usuario" name="InputEmail"
								required>
						</div>
						<div class="col-12">
							<label for="exampleInputPassword1">Contraseña</label> <input
								type="password" class="form-control" id="exampleInputPassword1"
								placeholder="Contraseña" name="InputPass" required>
							<div style="margin-top: 5px;"></div>
							<a href=# onclick=OlvideMiContraseña()>Olvidé mi contraseña</a>
						</div>
						<input type="hidden" name="game" value="${param.game}" />
						<div class="col-12">
							<button type="submit" class="boton">Ingresar</button>
						</div>
					</form>
					<hr class="mt-4">
					<div class="col-12">
						<p class="text-center mb-0">
							Aún no tenés cuenta? <a href="signUp.jsp">Registrate.</a>
						</p>
					</div>
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
		$(window).on('load', function() {
			$('#modalLogin').modal('show');
		});

		function OlvideMiContraseña() {
			var email = $("#exampleInputEmail1").val;
			$.ajax({
				url : 'OlvideMiContraseña',
				data : {
					"email" : email
				},
				type : 'post',
				cache : false,
				async : false,
				success : function() {
					alertaLoginPorReestriccion(email)
				},
				error : function() {
					alert('Ajax a fallado');
				}
			});
		};
		function alertaLoginPorReestriccion(email) {
			var alertPlaceholder = document.getElementById('liveAlertPlaceholder')
			function alert(message, type) {

				if (document.getElementById('error') != null) {
					document.getElementById('error').remove()
				}
				var wrapper = document.createElement('div')
				wrapper.innerHTML = '<div id="error" class="alert alert-' + type + ' alert-dismissible" role="alert">'
						+ message
						+ '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'
				alertPlaceholder.append(wrapper)
			}
				alert(
						'Hemos enviado tu contraseña a:'+email,
						'success')
			}
	</script>

</body>
</html>