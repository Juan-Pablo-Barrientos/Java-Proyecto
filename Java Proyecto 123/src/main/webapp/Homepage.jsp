<%@page import="java.util.LinkedList"%>
<%@page import="entities.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<link rel="icon" href="http://getbootstrap.com/favicon.ico">
<link href="Styles/Style.css" rel="stylesheet" type="text/css" />
<link href="Styles/sticky-footer.css" rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<title>Homepage</title>
<%
Usuario usr = (Usuario) session.getAttribute("usuario");
LinkedList<Usuario> lu = (LinkedList<Usuario>) request.getAttribute("listaUsuarios");
%>
<style>
</style>

</head>
<body style="background-color: black">
	<div id="wrapper">
		<nav class="navbar navbar-expand-lg"
			style="color: black; background-color: gold;">
			<a class="navbar-brand" style="padding: 5px; color: grey" href="#">Juego
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
					<li class="nav-item"><a class="nav-link" style="color: black"
						href="#">Placeholder</a></li>
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
				<div class="col ">
					<h3>
						Bienvenido
						<%=usr.getNickname()%></h3>
				</div>
				<div class="col ">3 of 3</div>
			</div>
			<div class="row">
				<div class="col ">1 of 3</div>
				<div class="col" style="justify-content: center">
					<div class="center2">

						<h1>Juegos</h1>
						<div id="carouselExampleInterval" class="carousel slide"
							style="border: 5px solid black" data-bs-ride="carousel">
							<div class="carousel-inner">
								<div class="carousel-item active">
									<img src="img/1.jpg" class="d-block w-100" alt="...">
									<h3>Sven Coop</h3>
								</div>
								<div class="carousel-item" data-bs-interval="2000">
									<img src="img/2.jpg" class="d-block w-100" alt="...">
									<h3>Half Life</h3>
								</div>
								<div class="carousel-item">
									<img src="img/3.jpg" class="d-block w-100" alt="...">
									<h3>Orange Box</h3>
								</div>
							</div>
							<button class="carousel-control-prev" type="button"
								data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
								<span class="carousel-control-prev-icon" aria-hidden="true"></span>
								<span class="visually-hidden">Previous</span>
							</button>
							<button class="carousel-control-next" type="button"
								data-bs-target="#carouselExampleInterval" data-bs-slide="next">
								<span class="carousel-control-next-icon" aria-hidden="true"></span>
								<span class="visually-hidden">Next</span>
							</button>

						</div>
					</div>
				</div>
				<div class="col " style="">3 of 3</div>
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
				<div class="col" style="padding: 5px;">
					3 of 3
				</div>
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



