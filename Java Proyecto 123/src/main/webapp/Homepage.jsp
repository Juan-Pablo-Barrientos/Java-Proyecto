<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
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
<title>Homepage</title>
<style>
</style>

</head>
<body style="background-color: black">
	<jsp:include page="Navbar.jsp" />
	<div class="row">
		<div class="col" style="justify-content: center">
			<div class="center2">

				<h1>Juegos en descuento</h1>
				<!-- Carousel de juegos en descuentos -->
				<div id="carouselDescuento" class="carousel slide carousel-fade"
					style="border: 5px solid black" data-bs-ride="carousel">
					<div class="carousel-inner"
						style="height: auto; width: auto; overflow: hidden;">
						<div class="carousel-item active">
							<img style="height: 334px !important; width: 595px !important;"
								src="img/Descuentos.png" class="d-block w-100" alt="">
						</div>
						<c:forEach items="${juegos}" var="j">
							<c:if test="${j.descuento!=0}">
								<div class="carousel-item">
									<a href="Game?game=${j.id}"> <img
										style="height: 334px !important; width: 595px !important;"
										src="${pageContext.request.contextPath}/Images/1.png?game=${j.id}"
										class="d-block w-100" alt="...">
										<div class="carousel-caption d-none d-md-block"style="opacity:0.8;background-color:black;">
											<h4 style="opacity:1">${j.nombre}</h4>
										</div>
									</a>
								</div>
							</c:if>
						</c:forEach>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselDescuento" data-bs-slide="prev">
						<span class="carouselDescuento" aria-hidden="true"></span> <span
							class="sr-only">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselDescuento" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</button>

				</div>
			</div>
		</div>
		<div class="col" style="justify-content: center">
			<div class="center2">

				<h1>Juegos de Deporte</h1>
				<!-- Carousel de juegos de deporte -->
				<div id="carouselDeporte" class="carousel slide carousel-fade"
					style="border: 5px solid black" data-bs-ride="carousel">
					<div class="carousel-inner"
						style="height: auto; width: auto; overflow: hidden;">
						<div class="carousel-item active">
							<img style="height: 334px !important; width: 595px !important;"
								src="img/Deportes.jpg" class="d-block w-100" alt="">
						</div>
						<c:forEach items="${juegos}" var="j">
							<c:if test="${j.genero=='Deportes' }">
								<div class="carousel-item">

									<a href="Game?game=${j.id}"> <img
										style="height: 334px !important; width: 595px !important;"
										src="${pageContext.request.contextPath}/Images/1.png?game=${j.id}"
										class="d-block w-100" alt="...">
										<div class="carousel-caption d-none d-md-block"style="opacity:0.8;background-color:black;">
											<h4 style="opacity:1">${j.nombre}</h4>
										</div>
									</a>
								</div>
							</c:if>
						</c:forEach>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselDeporte" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselDeporte" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>

				</div>
			</div>
		</div>

	</div>
	<div class="row">
		<div class="col" style="justify-content: center">
			<div class="center2">

				<h1>Juegos de estrategia</h1>
				<!-- Carousel de juegos de estrategia -->
				<div id="carouselEstrategia" class="carousel slide carousel-fade"
					style="border: 5px solid black" data-bs-ride="carousel">
					<div class="carousel-inner"
						style="height: auto; width: auto; overflow: hidden;">
						<div class="carousel-item active">
							<img style="height: 334px !important; width: 595px !important;"
								src="img/Estrategia.jpg" class="d-block w-100" alt="">
						</div>
						<c:forEach items="${juegos}" var="j">
							<c:if test="${j.genero=='Estrategia'}">
								<div class="carousel-item">
									<a href="Game?game=${j.id}"> <img
										style="height: 334px !important; width: 595px !important;"
										src="${pageContext.request.contextPath}/Images/1.png?game=${j.id}"
										class="d-block w-100" alt="...">
										<div class="carousel-caption d-none d-md-block"style="opacity:0.8;background-color:black;">
											<h4 style="opacity:1">${j.nombre}</h4>
										</div>
									</a>
								</div>
							</c:if>
						</c:forEach>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselEstrategia" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselEstrategia" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>

				</div>
			</div>
		</div>
		<div class="col" style="justify-content: center">
			<div class="center2">

				<h1>Juegos de Mundo Abierto</h1>
				<!-- Carousel de juegos de Mundo Abierto -->
				<div id="carouselMundoAbierto" class="carousel slide carousel-fade"
					style="border: 5px solid black" data-bs-ride="carousel">
					<div class="carousel-inner"
						style="height: auto; width: auto; overflow: hidden;">
						<div class="carousel-item active">
							<img style="height: 334px !important; width: 595px !important;"
								src="img/Mundoabierto.jpg" class="d-block w-100" alt="">
						</div>
						<c:forEach items="${juegos}" var="j">
							<c:if test="${j.genero=='Mundo Abierto'}">
								<div class="carousel-item">

									<a href="Game?game=${j.id}"><img
										style="height: 334px !important; width: 595px !important;"
										src="${pageContext.request.contextPath}/Images/1.png?game=${j.id}"
										class="d-block w-100" alt="...">
										<div class="carousel-caption d-none d-md-block"style="opacity:0.8;background-color:black;">
											<h4 style="opacity:1">${j.nombre}</h4>
										</div>
										</a>
								</div>
							</c:if>
						</c:forEach>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselMundoAbierto" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselMundoAbierto" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>

				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col" style="justify-content: center">
			<div class="center2">

				<h1>Juegos de Simulacion</h1>
				<!-- Carousel de juegos de simulacion -->
				<div id="carouselSimulacion" class="carousel slide carousel-fade"
					style="border: 5px solid black" data-bs-ride="carousel">
					<div class="carousel-inner"
						style="height: auto; width: auto; overflow: hidden;">
						<div class="carousel-item active">
							<img style="height: 334px !important; width: 595px !important;"
								src="img/Simulacion.jpg" class="d-block w-100" alt="">
						</div>
						<c:forEach items="${juegos}" var="j">
							<c:if test="${j.genero=='Simulacion' }">
								<div class="carousel-item">

									<a href="Game?game=${j.id}"> <img
										style="height: 334px !important; width: 595px !important;"
										src="${pageContext.request.contextPath}/Images/1.png?game=${j.id}"
										class="d-block w-100" alt="...">
										<div class="carousel-caption d-none d-md-block"style="opacity:0.8;background-color:black;">
											<h4 style="opacity:1">${j.nombre}</h4>
										</div>
									</a>
								</div>
							</c:if>
						</c:forEach>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselSimulacion" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselSimulacion" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>

				</div>
			</div>
		</div>
		<div class="col" style="justify-content: center">
			<div class="center2">

				<h1>Juegos de Pelea</h1>
				<!-- Carousel de juegos de pelea -->
				<div id="carouselPelea" class="carousel slide carousel-fade"
					style="border: 5px solid black" data-bs-ride="carousel">
					<div class="carousel-inner"
						style="height: auto; width: auto; overflow: hidden;">
						<div class="carousel-item active">
							<img style="height: 334px !important; width: 595px !important;"
								src="img/Pelea.jpg" class="d-block w-100" alt="">
						</div>
						<c:forEach items="${juegos}" var="j">
							<c:if test="${j.genero=='Pelea' }">
								<div class="carousel-item">

									<a href="Game?game=${j.id}"> <img
										style="height: 334px !important; width: 595px !important;"
										src="${pageContext.request.contextPath}/Images/1.png?game=${j.id}"
										class="d-block w-100" alt="...">
										<div class="carousel-caption d-none d-md-block"style="opacity:0.8;background-color:black;">
											<h4 style="opacity:1">${j.nombre}</h4>
										</div>
									</a>
								</div>
							</c:if>
						</c:forEach>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselPelea" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselPelea" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>

				</div>
			</div>
		</div>

	</div>

	<div class="row">
		<div class="col" style="justify-content: center">
			<div class="center2">

				<h1>Juegos de Accion</h1>
				<!-- Carousel de juegos de accion -->
				<div id="carouselAccion" class="carousel slide carousel-fade"
					style="border: 5px solid black" data-bs-ride="carousel">
					<div class="carousel-inner"
						style="height: auto; width: auto; overflow: hidden;">
						<div class="carousel-item active">
							<img style="height: 334px !important; width: 595px !important;"
								src="img/Accion.jpg" class="d-block w-100" alt="">
						</div>
						<c:forEach items="${juegos}" var="j">
							<c:if test="${j.genero==Accion }">
								<div class="carousel-item">

									<a href="Game?game=${j.id}"> <img
										style="height: 334px !important; width: 595px !important;"
										src="${pageContext.request.contextPath}/Images/1.png?game=${j.id}"
										class="d-block w-100" alt="...">
										<div class="carousel-caption d-none d-md-block"style="opacity:0.8;background-color:black;">
											<h4 style="opacity:1">${j.nombre}</h4>
										</div>
									</a>
								</div>
							</c:if>
						</c:forEach>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselAccion" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselAccion" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>

				</div>
			</div>
		</div>
		<div class="col" style="justify-content: center">
			<div class="center2">

				<h1>Juegos de Rpg</h1>
				<!-- Carousel de juegos de rpg -->
				<div id="carouselRpg" class="carousel slide carousel-fade"
					style="border: 5px solid black" data-bs-ride="carousel">
					<div class="carousel-inner"
						style="height: auto; width: auto; overflow: hidden;">
						<div class="carousel-item active">
							<img style="height: 334px !important; width: 595px !important;"
								src="img/Rpg.jpg" class="d-block w-100" alt="">
						</div>
						<c:forEach items="${juegos}" var="j">
							<c:if test="${j.genero=='Rpg' }">
								<div class="carousel-item">

									<a href="Game?game=${j.id}"> <img
										style="height: 334px !important; width: 595px !important;"
										src="${pageContext.request.contextPath}/Images/1.png?game=${j.id}"
										class="d-block w-100" alt="...">
										<div class="carousel-caption d-none d-md-block"style="opacity:0.8;background-color:black;">
											<h4 style="opacity:1">${j.nombre}</h4>
										</div>
									</a>
								</div>
							</c:if>
						</c:forEach>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselRpg" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselRpg" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>

				</div>
			</div>
		</div>
	</div>
	<jsp:include page="Footer.jsp" />
	<script src="bootstrap/js/bootstrap.bundle.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>

	<script
		src="https://unpkg.com/bootstrap-table@1.18.3/dist/bootstrap-table.min.js"></script>

</body>
</html>



