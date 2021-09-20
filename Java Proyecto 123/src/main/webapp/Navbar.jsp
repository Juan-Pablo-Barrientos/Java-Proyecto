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
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
<title>MasterPage</title>
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
					</li><li class="nav-item">
					<c:if test="${usuario.tipo==('admin')}">
							<form action="PanelAdministracion" method=get>
								<button class="btn btn-success" type="submit">Panel de administracion</button>
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
		
	</div>
	<script src="bootstrap/js/bootstrap.bundle.js"></script>
</body>
</html>

	<main role="main" class="container"
		style="background-color: gold; text-align: center">