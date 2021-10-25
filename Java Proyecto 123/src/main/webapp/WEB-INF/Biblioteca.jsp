<%@page import="entities.*"%>
<%@page import="logic.*"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="icon" href="img/logo_modificado.ico">
<title>Mi Biblioteca</title>
<link href="Styles/Style.css" rel="stylesheet" type="text/css" />
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />

<link href="bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://unpkg.com/bootstrap-table@1.18.3/dist/bootstrap-table.min.css">
<style>
.modal-dialog {
  position: relative;
  display: table;
  overflow: auto;
  width: auto;
  min-width: 300px;
}
.modal-body { /* Restrict Modal width to 90% */
  overflow-x: auto !important;
  max-width: 90vw !important;
}
</style>
</head>
<body>
	<jsp:include page="/Navbar.jsp" />
	<c:if test="${not empty result}">
		<div class="modal fade" id="modalExito" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Solicitud
							Enviada!</h5>
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
	<div class="modal fade" id="modalReembolso" tabindex="-1"
		data-bs-backdrop="static" data-bs-keyboard="false"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="Reembolsar">Esta seguro que quiere
						reembolsar este juego?</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form action="Biblioteca" method="post" onSubmit="">
					<input type="hidden" name="action1" value="create" />
					<div class="modal-body">
						<div class="form-group">
							<input type="text" class="form-control" id="idCompra"
								placeholder="id compra" name="idCompra" hidden="true">
						</div>
						<div class="form-group">
							<label id="nombreJuego"> </label>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" id="razon"
								placeholder="Escriba su razon" name="razon" required>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Regresar</button>
						<button type="submit" class="btn btn-primary">Enviar!</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modalJugando" tabindex="-1"
		static" data-bs-keyboard="false" aria-labelledby="exampleModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="Jugar">Jugando</h5>
				</div>
				<form action="Biblioteca" method="get" onSubmit="">
					<input type="hidden" name="action2" value="update" />
					<div class="modal-body">
						<div id="#gameDiv" class="gameDiv">
							<iframe id="game" width="560" height="315" src=""
								allow="fullscreen; autoplay; encrypted-media" frameborder="0"
								allowfullscreen="true" msallowfullscreen="true"
								mozallowfullscreen="true" webkitallowfullscreen="true"
								allowpaymentrequest="false" referrerpolicy="unsafe-url"
								sandbox="allow-same-origin allow-forms allow-scripts allow-pointer-lock allow-orientation-lock allow-popups"
								scrolling="no"></iframe>

						</div>
						
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Dejar de
							jugar</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<h4>Mis Juegos</h4>
			<div class="col-12 col-sm-12 col-lg-12">
				<div class="table-responsive">
					<table class="table hideFullColumn" id="table" data-toggle="table">
						<thead>
							<tr>
								<th data-field="idCompra" class="hidecol">Id Compra</th>
								<th data-field="linkJuego" class="hidecol">Id Compra</th>
								<th data-field="nombreJuego">Nombre del juego</th>
								<th data-field="hsJugadas">Horas jugadas</th>
								<th data-field="operate" data-formatter="operateFormatter"
									data-events="operateEvents"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listaCompraView}" var="c">
								<tr>
									<td><c:out value="${c.compra.nroSerie}"></c:out></td>
									<td><c:out value="${c.juego.url}"></c:out></td>
									<td><c:out value="${c.juego.nombre}"></c:out></td>
									<td><c:out value="${c.compra.horas_jugadas}"></c:out></td>
									<td></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/Footer.jsp" />
	<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="bootstrap/js/bootstrap.bundle.js"></script>
	<script
		src="https://unpkg.com/bootstrap-table@1.18.3/dist/bootstrap-table.min.js"></script>
	<script>
	var $table = $('#table');
	let startTime;
	let elapsedTime = 0;
	let timerInterval;

	function operateFormatter(value, row, index) {
		return [
				'<a class="jugar" href="javascript:void(0)" title="Jugar">',
				'<i class="fas fa-play fa-2x"></i>',
				'</a>  ',
				'<a class="reembolso" href="javascript:void(0)" title="Reembolso">',
				'<i class="fas fa-dollar-sign fa-2x"></i>', '</a>' ].join('')
	}
	
	window.operateEvents = {
		'click .jugar' : function(e, value, row, index) {			
			$('#modalJugando').modal('show');
		    $("#game").attr("src", ([row.linkJuego]));
			$('#nroCompra').val([ row.idCompra ]);
			var intervaloTiempo=30000
			   timerInterval = setInterval(function printTime() {
		       $.ajax
		        (
		            {
		                url:'BibliotecaHoras',
		                data:{"segundos":intervaloTiempo,"nroCompra":row.idCompra},
		                type:'get',
		                cache:false,
		                success:function(){
		                },
		                error:function(){alert('no funco');}
		            }
		        );
				  }, intervaloTiempo);	
			  
			
		},
		'click .reembolso' : function(e, value, row, index) {
			$('#modalReembolso').modal('show');
			$('#Reembolso').text(
					"Juego : " + row.nombreJuego);
			$('#idCompra').val([ row.idCompra ]);
			$('#nombreJuego').text([ row.nombreJuego ]);
		}
		 
	}
	$(window).on('load', function() {
		$('#modalExito').modal('show');
	});
	   const url = new URL(window.location.href)
      const params = new URLSearchParams(url.search.slice(1))
      window.history.replaceState(
        {},
        '',
        `${window.location.pathname}?${"s=4"}${window.location.hash}`,
      );
			</script>
</body>
</html>