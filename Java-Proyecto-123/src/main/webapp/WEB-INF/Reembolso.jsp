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
<title>Reembolsos Pendientes</title>
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

</head>
<body>
	<jsp:include page="/Navbar.jsp" />
	<div class="container">
		<c:if test="${not empty result}">
			<div class="modal fade" id="modalExito" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Lista de
								reembolsos actualizada!</h5>
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
		<div class="modal fade" id="modalDecline" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Denegar
							reembolso</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
						<form method="post" action="ListadoReembolsoPendiente">
					<div class="modal-body">
						<div class="form-group">
							<label id="razonId">Razon</label>
						</div>
						<p></p>
						<div class="form-group">
							<label for="InputComentario">Comentario: </label> 
							<input type="text" id="InputComentarioId" name="InputComentarioId"/>
							<label for="InputHorasJugadas"></label> 
						</div>

					</div>
					<div class="modal-footer">
						<label id="modalDeclinelbl"> </label>
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Regresar</button>
							<input type="hidden" name="declineInput" value="decline" /> <input
								type="hidden" id="hiddenIdDecline" name="hiddenIdDecline" />
							<button type="submit" class="btn btn-danger">Rechazar</button>
						
					</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="modalApprove" tabindex="-1"
			data-bs-backdrop="static" data-bs-keyboard="false"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="EditarDesarrolladorNombrelbl">Aprobar
							reembolso</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<form action="ListadoReembolsoPendiente" method="post" onSubmit="">
						<input type="hidden" name="approveInput" value="approve" />
						<div class="modal-body">
							<input type="hidden" id="hiddenIdApprove" name="hiddenIdApprove" />
							<input type="hidden" id="hiddenIdGame" name="hiddenIdGame" />
							<input type="hidden" id="hiddenIdUser" name="hiddenIdUser" />
							<label id="modalApprovelbl"> </label>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Regresar</button>
							<button type="submit" class="btn btn-primary">Aprobar!</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="row">
			<h4>Reembolsos</h4>
			<div class="col-12 col-sm-12 col-lg-12">
				<div class="table-responsive">
					<table class="table" id="table" data-toggle="table">
						<thead>
							<tr>
								<th data-field="idReembolso">Id Reembolso</th>
								<th data-field="nombreUsuario">Nombre del usuario</th>
								<th data-field="idUsuario">Id Usuario</th>
								<th data-field="idCompra">Id Compra</th>
								<th data-field="nombreJuego">Nombre del juego</th>
								<th data-field="horasJugadas">Horas jugadas</th>
								<th data-field="estado">Estado</th>
								<th data-field="razon">Razon</th>
								<th data-field="Comentario">Comentario Evaluador</th>
								<th data-field="operate" data-formatter="operateFormatter"
									data-events="operateEvents"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listaCompraView}" var="c">
								<c:if test="${c.reembolso.id !=0}">
									<tr>
										<td><c:out value="${c.reembolso.id}"></c:out></td>
										<td><c:out value="${c.usuario.nombreUsuario}"></c:out></td>
										<td><c:out value="${c.usuario.id}"></c:out></td>
										<td><c:out value="${c.compra.nroSerie}"></c:out></td>
										<td><c:out value="${c.juego.nombre}"></c:out></td>
										<td><c:out value="${c.compra.horas_jugadas}"></c:out> Hs</td>
										<td><c:out value="${c.reembolso.estado}"></c:out></td>
										<td><c:out value="${c.reembolso.razon}"></c:out></td>
										<td><c:out value="${c.reembolso.comentario}"></c:out></td>
										<c:if test="${c.reembolso.estado =='Pendiente'}">
											<td></td>
										</c:if>
									</tr>
								</c:if>
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
		var $table = $('#table')

		function operateFormatter(value, row, index) {
			return [
					'<a class="like" href="javascript:void(0)" title="Aprobar">',
					'<i class="fas fa-check fa-2x"></i>',
					'</a>  ',
					'<a class="remove" href="javascript:void(0)" title="Rechazar">',
					'<i class="fas fa-times fa-2x"></i>', '</a>' ].join('')
		}

		window.operateEvents = {
			'click .like' : function(e, value, row, index) {
				$("#hiddenIdUser").val([ row.idUsuario ]);
				$("#hiddenIdGame").val([ row.idCompra ]);
				$("#hiddenIdApprove").val([ row.idReembolso ]);
				$("#modalApprovelbl").text(
						"Esta seguro de que quiere aprobar el reembolso del usuario "
								+ row.nombreUsuario+ "?");
				$("#modalApprove").modal('show');
			},
			'click .remove' : function(e, value, row, index) {
				$("#hiddenIdDecline").val([ row.idReembolso ]);
				$("#razonId").text( "Razon: "+row.razon);
				$("#razonId").text( "Horas jugadas: "+row.horasJugadas);
				$("#modalDeclinelbl").text(
						"Esta seguro de que quiere denegar el reembolso del usuario "
								+ row.nombreUsuario+ "?");
				$("#modalDecline").modal('show');
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
	      )
	</script>
</body>
</html>