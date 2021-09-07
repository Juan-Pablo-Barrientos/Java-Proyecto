<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link href="Styles/Style.css" rel="stylesheet" type="text/css" />
<link href="bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<title>Panel de Administracion</title>
</head>
<body style="background-color: black">

	<div id="wrapper">
		<nav class="navbar navbar-expand-lg"
			style="color: black; background-color: gold;">
			<a class="navbar-brand" style="padding: 10px; color: grey" href="Homepage.jsp">Juego
				Mundo</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item active"><a class="nav-link"
						style="color: black" href="#">Home <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" style="color: black"
						href="#">Placeholder</a></li>

					<li><a class="nav-link" style="color: black" href="#">Placeholder</a>
					</li>
					<li class="nav-item"><c:if test="${usuario.tipo==('admin')}">
							<form action="PanelAdministracion" method="post">
								<button class="btn btn-success" type="submit">Panel de
									administracion</button>
							</form>
						</c:if></li>
				</ul>
				<ul class="navbar-nav ms-auto" style="margin-right: 10px">
					<li class="nav-item"><form action="SignOut" method="post"
							style="text-align: right;">
							<button class="btn btn-danger" "type="submit">Cerrar
								Sesión</button>
						</form></li>
				</ul>
			</div>
		</nav>
		<main role="main" class="container"
			style="background-color: gold; text-align: center">
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
					<form action="ListadoUsuarios" method="post">
						<button class="btn btn-success" type="submit">Listado
							Usuarios</button>
					</form>
				</div>
				<div class="col" style="padding: 5px;">
					<form action="ListadoReembolsoPendiente" method="post">
						<button class="btn btn-success" type="submit">Listado
							Reembolso</button>

					</form>
				</div>
				<div class="col" style="padding: 5px;">3 of 3</div>
			</div>
		</main>
		<footer class="nav-footer" style="background: orange; padding: 10px">
			<div class="container">
				<span>Place sticky footer content here.</span>
			</div>
		</footer>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
		crossorigin="anonymous"></script>
</body>
</html>