<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${game.juego.nombre}</title>
<meta http-equiv="Content-Type" content="text/html">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="img/logo_modificado.ico">
<link href="Styles/Style.css" rel="stylesheet" type="text/css" />
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<style>
img {
	max-width: 100%;
	max-height: 100%;
}
</style>
</head>
<body>
	<jsp:include page="Navbar.jsp" />
	<c:if test="${not empty result}">
		<div class="modal fade" id="modalExito" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Operación
							exitosa!</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<c:out value="${result}"></c:out>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							data-bs-dismiss="modal">Entendido!</button>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<!-- Modal para editar reseña -->
	<div class="modal fade" id="modalEditar" tabindex="-1"
		data-bs-backdrop="static" data-bs-keyboard="false" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="EditarDesarrolladorNombrelbl">Editar
						reseña</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form action="Reseñas" method="post" onSubmit="">
					<input type="hidden" name="action" value="edit" /> <input
						type="hidden" name="hiddenIdJuego" value="${game.juego.id}" />
					<div class="modal-body">
						<div class="form-group">
							<input type="hidden" class="form-control" id="idResenaId"
								name="resenaId">
						</div>
						<div class="form-group">
							<label for="inputResenaId">Título de reseña</label> <input
								type="text" class="form-control my-1" id="inputResenaId"
								placeholder="Ingrese título de reseña" name="inputTitulo"
								value="${reseñaViewUsuario.reseña.titulo}" required>
						</div>
						<div class="form-group">
							<label for="idPuntuacion">Puntuación</label> <input type="number"
								class="form-control my-1" id="idPuntuacion"
								name="inputPuntuacion" min="1" max="10" placeholder="1-10"
								value="${reseñaViewUsuario.reseña.puntuacion}" required>
						</div>
						<div class="form-group">
							<label for="inputResenaId">Descripcion</label>
							<textarea class="form-control my-1" id="idDescripcion"
								name="inputDescripcion" placeholder="Ingrese reseña." rows="4"
								required>${reseñaViewUsuario.reseña.descripcion}</textarea>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Regresar</button>
						<button type="submit" class="btn btn-primary">Guardar</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Modal para eliminar reseña  -->
	<div class="modal fade" id="modalBorrar" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Borrar reseña</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<label id="modalBorrarlbl"> </label>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Regresar</button>
					<form method="post" action="Reseñas">
						<input type="hidden" name="action" value="delete" /> <input
							type="hidden" name="hiddenIdJuego" value="${game.juego.id}" /> <input
							type="hidden" name="hiddenNroSerieCompra"
							value="${reseñaViewUsuario.compra.nroSerie}" />
						<button type="submit" class="btn btn-danger">Borrar</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!--  Modal de compra -->
	<div class="modal fade" id="modalComprar" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Comprar juego</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form method="post" action="CompraGame">
					<div class="modal-body">
						<label id="modalComprarlbl"></label>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="idJuego"
							name="idJuego" value="${game.juego.id}" hidden />
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Regresar</button>
						<input type="hidden" name="action" value="delete" /> <input
							type="hidden" id="hiddenId" name="hiddenId" />
						<button type="submit" class="btn btn-primary">Comprar</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="row" style="display: flex">
		<div class="col-4" style="">
			<h1 style="text-align: left; padding-left: 4%" id="gameNombre">${game.juego.nombre}</h1>
		</div>
		<div class="col" style="">
			<h3 style="text-align: right; padding-right: 4%;">${game.juego.genero}
				Games</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-4" style="justify-content: center">
			<div class="">
				<img style="max-width: 100%; max-height: 100%;"
					class="shadow-lg p-3 mb-5 bg-white rounded"
					src="img/${game.juego.id}/1.jpg" />
			</div>
		</div>
		<div class="col" style="">
			<div class="shadow-lg p-3 mb-5 bg-white rounded">
				<p>${game.juego.descripcion}</p>
				<p>Publicador: ${game.publicador.nombre}</p>
				<p>Desarrollador: ${game.desarrollador.nombre}</p>
				<p>Fecha de publicacion: ${game.juego.fecha_publicacion}</p>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-4">

			<c:if test="${game.juego.descuento!=0.0}">
				<div>
					<h1>
						<span id="gameDescuento" class="badge bg-success">Descuento:
							${game.juego.descuento*100}</span>
					</h1>
				</div>
			</c:if>
			<div class="input-group" style="justify-content: center">
				<div class="input-group-prepend">
					<span class="input-group-text mx-2" id="">Precio:
						$${game.juego.precioBase-(game.juego.precioBase*game.juego.descuento)}</span>
				</div>
				<c:choose>
					<c:when test="${usuario==null}">
						<form action="index.jsp" method="get">
							<input type="hidden" name="game" value="${game.juego.id}" />
							<button type="submit" class="btn btn-primary mx-2">Iniciar
								sesion para comprar</button>
						</form>
					</c:when>
					<c:when test="${tieneGame==false }">
						<button class="btn btn-primary mx-2" onclick=showModal()>Comprar</button>
					</c:when>
					<c:when test="${tieneGame==true }">
						<form action="Biblioteca" method="get">
							<button type="submit" class="btn btn-primary mx-2">Jugar</button>
						</form>
					</c:when>
				</c:choose>
			</div>
		</div>
		<div class="col-8 align-items-center">
			<c:if
				test="${(reseñaViewUsuario.reseña.id != 0) && (tieneGame == true)}">
				<div class="shadow-lg p-4 bg-white rounded">
					<div class="row" style="height: 20%;">
						<div class="col-10">
							<h6 class="text-start">Título:
								${reseñaViewUsuario.reseña.titulo}
								${reseñaViewUsuario.reseña.puntuacion}/10</h6>
						</div>
						<div class="col-2">
							<button class="boton" onclick="showModalEditar()">
								<i class="fas fa-pen"></i>
							</button>
							<button class="btn btn-danger" onclick="showModalBorrar()">
								<i class="fas fa-trash"></i>
							</button>
						</div>
					</div>
					<div class="row" style="height: 80%;">
						<p>${reseñaViewUsuario.reseña.descripcion}</p>
						<p align="right">Mi reseña.</p>
					</div>
				</div>
			</c:if>


			<c:if
				test="${(reseñaViewUsuario.reseña.id == 0) && (tieneGame == true)}">
				<div class="shadow-lg p-4 bg-white rounded">
					<form action="Reseñas" method="post">
						<input type="hidden" name="action" value="create" /> <input
							type="hidden" name="hiddenIdJuego" value="${game.juego.id}" /> <input
							type="hidden" name="hiddenNroSerieCompra"
							value="${reseñaViewUsuario.compra.nroSerie}" />
						<div class="form-group row">
							<label for="idTitulo" class="col-auto col-form-label">Título:</label>
							<div class="col-auto">
								<input type="text" class="form-control" id="idTitulo"
									name="inputTitulo" placeholder="Ingrese título reseña."
									required>
							</div>
							<label for="idPuntuacion" class="col-auto col-form-label">Puntuación:</label>
							<div class="col-auto">
								<input type="number" class="form-control" id="idPuntuacion"
									name="inputPuntuacion" min="1" max="10" placeholder="1-10"
									required>
							</div>
						</div>
						<div class="form-group row mb-4">
							<label for="idDescripcion" class="col-auto col-form-label">Descripción:</label>
							<div class="col-12">
								<textarea class="form-control" id="idDescripcion"
									name="inputDescripcion" placeholder="Ingrese reseña." rows="4"
									required></textarea>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-4 offset-9">
								<button class="boton" type="submit">Enviar reseña</button>
							</div>
						</div>
					</form>
				</div>
			</c:if>

		</div>
	</div>

	<!-- Listado de todas las reviews -->
	<c:forEach items="${reseñasJuego}" var="r">
		<c:if test="${reseñaViewUsuario.usuario.id != r.usuario.id}">
			<div class="row">
				<div class="col-4 align-items-center"></div>
				<div class="col-8 align-items-center">
					<div class="shadow-lg mt-4 p-4 bg-white rounded">
						<h6 class="text-start">Título: ${r.reseña.titulo}
							${r.reseña.puntuacion}/10</h6>
						<p>${r.reseña.descripcion}</p>
						<p>Reseña por: ${r.usuario.nombreUsuario}</p>
					</div>
				</div>
			</div>
		</c:if>
	</c:forEach>











	<jsp:include page="Footer.jsp" />
	<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>

	<script src="bootstrap/js/bootstrap.bundle.js"></script>
	<script
		src="https://unpkg.com/bootstrap-table@1.18.3/dist/bootstrap-table.min.js"></script>
	<script>
		$(window).on('load', function() {
			$('#modalExito').modal('show')	
		var str= document.getElementById("gameDescuento").innerHTML;
			str= str.substring(0,str.length  -2).concat("%");
			str= document.getElementById("gameDescuento").innerHTML=str;
		});

		function showModal(){
			$('#modalComprar').modal('show');
			console.log($("#idJuego").val());
			$('#modalComprarlbl').text("Esta seguro de que desea comprar el juego: "+ $('#gameNombre').text()+"?");
		}
		
		function showModalEditar(){
			$('#modalEditar').modal('show');
		}
		
		function showModalBorrar(){
			$('#modalBorrar').modal('show');
		}

		   const url = new URL(window.location.href);
		   const params = new URLSearchParams(url.search.slice(1));
		      window.history.replaceState(
		        {},
		        '',
		        '${window.location.pathname}?${"game="}'+$("#idJuego").val()+'&${"s=7"}',
		      );
		      
		   
		      
		      
		</script>
</body>
</html>