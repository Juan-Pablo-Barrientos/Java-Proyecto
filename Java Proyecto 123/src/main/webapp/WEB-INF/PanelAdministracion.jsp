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
<link href="bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<title>Panel de Administracion</title>
</head>
<body style="background-color: black">
	<jsp:include page="/Navbar.jsp" />
	<div class="row ">
		<div class="col ">1 of 3</div>
		<div class="col "></div>
		<div class="col ">3 of 3</div>
	</div>
	<div class="row">
		<div class="col ">1 of 3</div>
		<div class="col" style="justify-content: center">

			<div class="col " style="">3 of 3</div>
		</div>
	</div>
	<div class="row  " style="height: 50vh">
		<div class="col" style="padding: 5px;">
			<form action="ListadoUsuarios" method="get">
				<button class="boton" type="submit">Listado
					Usuarios</button>
			</form>
		</div>
		<div class="col" style="padding: 5px;">
			<form action="ListadoReembolsoPendiente" method="get">
				<button class="boton" type="submit">Listado
					Reembolso</button>

			</form>
		</div>
		<div class="col" style="padding: 5px;">3 of 3</div>
		<div class="col" style="padding: 5px;">
			<form action="ListadoJuegos" method="get">
				<button class="boton" type="submit">Listado de
					juegos</button>
			</form>
		</div>


		<div class="col" style="padding: 5px;">
			<form action="ListadoDesarrolladores" method="get">
				<button class="boton" type="submit">Listado de
					Desarrolladores</button>
			</form>
		</div>

		<div class="col" style="padding: 5px;">
			<form action="ListadoCompras" method="post">
				<button class="boton" type="submit">Listado de
					Compras</button>
			</form>
		</div>

		<div class="col" style="padding: 5px;">
			<form action="ListadoPublicadores" method="get">
				<button class="boton" type="submit">Listado de
					Publicadores</button>
			</form>
		</div>
	</div>

	<jsp:include page="/Footer.jsp" />
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
		crossorigin="anonymous"></script>
</body>
<script>
</script>
</html>

