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
						<h5 class="modal-title" id="exampleModalLabel">Compra realizada!</h5>
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
					<input type="text" class="form-control" id="idJuego" name="idJuego" value="${game.juego.id}" hidden/>								 								
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Regresar</button>					
						<input type="hidden" name="action" value="delete" /> <input
							type="hidden" id="hiddenId" name="hiddenId" />
						<button type="submit" class="btn btn-primary">Comprar</button>
					</form>
				</div>
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
				<div><h1><span id="gameDescuento"class="badge bg-success">Descuento: ${game.juego.descuento*100}</span></h1></div>
			</c:if>
			<div class="input-group" style="justify-content:center">
				<div class="input-group-prepend">
					<span class="input-group-text" id="">Precio: $${game.juego.precioBase-(game.juego.precioBase*game.juego.descuento)}</span>
				</div>
				<button class="btn btn-primary" onclick=showModal()>Comprar</button>
			</div>
		</div>
	</div>










	<jsp:include page="Footer.jsp" />
	<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>

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
		</script>
</body>
</html>