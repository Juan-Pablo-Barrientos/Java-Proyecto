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
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link rel="icon" href="img/logo_modificado.ico">
<link href="Styles/Style.css" rel="stylesheet" type="text/css" />
<link href="bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<title>MasterPage</title>
</head>
<body style="background-color: black">

	<div id="wrapper">
		<nav class="navbar navbar-expand-lg"
			style="color: black; background-color: #FF9800;">
			<a class="navbar-brand" href="/Java_Proyecto_123/Homepage.jsp"
				style="color: black;"> <img src="img/logo_modificado.svg" alt=""
				width="30" height="30"
				style="filter: invert(21%) sepia(68%) saturate(6615%) hue-rotate(269deg) brightness(93%) contrast(115%); margin-left: 10px"
				class="d-inline-block align-text-top"> Claw Games
			</a>

			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" style="color: black"
						href="Homepage.jsp">Home <span class="sr-only">(current)</span>
					</a></li>

					<c:if test="${usuario != null}">

						<li class="nav-item "><a class="nav-link"
							style="color: black" href="Biblioteca">Biblioteca<span
								class="sr-only">(current)</span>
						</a></li>

					</c:if>

					<li class="nav-item">
					<li><a class="nav-link" style="color: black" href="#">Placeholder</a>
					</li>

					<li class="nav-item"><c:choose>
							<c:when test="${usuario.tipo==('admin')}">
								<a class="nav-link" style="color: black"
									href="PanelAdministracion">Panel de Administración<span
									class="sr-only">(current)</span>
								</a>
							</c:when>
							<c:otherwise>
							</c:otherwise>

						</c:choose></li>

				</ul>
				<ul class="navbar-nav ms-auto" style="margin-right: 10px">
					<li><div class="container-fluid">
							<form class="d-flex" action="busquedaJuegos" method="get">
								<input class="form-control bg-light" type="search"
									placeholder="Búsqueda" aria-label="Search" name="InputBusqueda">
								<button class="boton" style="margin-left: 10px;" type="submit">
									<i class="fas fa-search"></i>
								</button>
							</form>
						</div></li>


					<c:if test="${usuario != null}">
						<li class="nav-item">
							<div>
								<div class="row">
									<div class="col-md-auto" style="margin: 0px 0px 0px 10px">
										<div class=""
											style="font-size: medium; text-align: right; height: 20px;">${usuario.nickname}</div>
										<div class=""
											style="font-size: small; text-align: right; height: 20px;">$${usuario.saldo}</div>
									</div>
									<div class="col-md-auto align-items-center"
										style="height: 40px; margin: 0px 10px 0px 0px; font-size: 24px">
										<i class="fas fa-user" style="line-height: 40px"></i>
									</div>
								</div>
							</div>
						</li>
					</c:if>




					<li class="nav-item">
						<div class="container-fluid">
							<c:choose>
								<c:when test="${usuario != null}">
									<form action="SignOut" method="post">
										<button class="btn btn-danger" type="submit">Cerrar
											Sesión</button>
									</form>
								</c:when>
								<c:otherwise>
									<form action="index.jsp" method="get">
										<button class="boton" type="submit">Iniciar Sesión</button>
									</form>
								</c:otherwise>
							</c:choose>
						</div>
					</li>
				</ul>
			</div>
		</nav>

	</div>
	<script src="bootstrap/js/bootstrap.bundle.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>

	<script>
		$(document).ready(
				function() {
					$('a.active').removeClass('active');
					$(
							'a[href="'
									+ window.location.pathname.split("/").pop()
									+ '"]').closest('a').addClass('active');
				});
	</script>

</body>
</html>

<main role="main" class="container"
	style="background-color: #F5F5F5; text-align: center; padding: 20px">