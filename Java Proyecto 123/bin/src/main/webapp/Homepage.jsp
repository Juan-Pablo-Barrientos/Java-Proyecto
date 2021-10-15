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
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
<title>Homepage</title>
<style>
</style>

</head>
<body style="background-color: black">
<jsp:include page="Navbar.jsp"/>
			<div class="row ">
				<div class="col ">1 of 3</div>
				<div class="col ">
					<h3>
						Bienvenido
						${usuario.nickname}</h3>
						
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
					fdjfj
				</div>
				<div class="col" style="padding: 5px;">
					fyj
				</div>
				<div class="col" style="padding: 5px;">3 of 3</div>
			</div>
<jsp:include page="Footer.jsp"/>
	<script src="bootstrap/js/bootstrap.bundle.js"></script>
</body>
</html>



