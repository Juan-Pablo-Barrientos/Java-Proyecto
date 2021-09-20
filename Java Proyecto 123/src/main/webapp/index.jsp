<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link href="Styles/Style.css" rel="stylesheet" type="text/css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>Java</title>
</head>
<body style="background-color: black">
	<div class="container"
		style="border: 2px solid black; border-radius: 20px; background-color: gold; margin-top: 20%;">

		<form role="form" style="margin: 20px;" action="signin" method="post">
			<div class="form-group">
				<label for="exampleInputEmail1">Usuario o Email</label> <input
					type="text" class="form-control" id="exampleInputEmail1"
					placeholder="Ingrese email o usuario" name="InputEmail" required>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Contraseña</label> <input
					type="password" class="form-control" id="exampleInputPassword1"
					placeholder="Contraseña" name="InputPass" required>
			</div>
			<div class="checkbox">
				<label> <input type="checkbox" name=checkboxRemember>
					Recuérdame
				</label>
			</div>
			<button type="submit" class="btn btn-lg btn-primary">Ingresar</button>
		</form>
		<form method="post" action="signUp.jsp">
			<button type="submit" style="margin-bottom: 5px; margin-left: 20px"
				class="btn btn-link btn-sm">Registarse</button>
		</form>
		<form method="post" action="">
			<button type="submit" style="margin-bottom: 5px; margin-left: 20px"
				class="btn btn-link btn-sm">Olvidé mi contraseña</button>
		</form>
	</div>
</body>
</html>