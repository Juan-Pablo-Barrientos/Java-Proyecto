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
<title>Lista de Publicadores</title>
<link rel="icon" href="img/logo_modificado.ico">
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
	<c:if test="${not empty result}">
		<div class="modal fade" id="modalExito" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Lista de
							publicadores actualizada!</h5>
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
	<div class="modal fade" id="modalEditar" tabindex="-1"
		data-bs-backdrop="static" data-bs-keyboard="false"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="EditarPublicadorNombrelbl">Editar
						publicador</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form action="ListadoPublicadores" method="post" onSubmit="">
					<input type="hidden" name="action2" value="edit" />
					<div class="modal-body">
						<div class="form-group">
							<input type="text" class="form-control" id="publicadorId"
								placeholder="Ingrese nombre de usuario" name="publicadorId"
								hidden="true">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Publicador</label> <input
								type="text" class="form-control" id="InputPublicadorId"
								placeholder="Ingrese nombre de publicador"
								name="InputPublicador" required>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Regresar</button>
						<button type="submit" class="btn btn-primary">Guardar!</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modalNuevo" tabindex="-1"
		data-bs-backdrop="static" data-bs-keyboard="false"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="NuevoPublicadorNombrelbl">Nuevo
						desarrollador</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form action="ListadoPublicadores" method="post" onSubmit="">
					<input type="hidden" name="action3" value="create" />
					<div class="modal-body">
						<div class="form-group">
							<label for="exampleInputEmail1">Desarrollador</label> <input
								type="text" class="form-control" id="InputPublicadorId"
								placeholder="Ingrese nombre de publicador"
								name="InputPublicador" required>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Regresar</button>
						<button type="submit" class="btn btn-primary">Guardar!</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modalBorrar" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Borrar
						publicador</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<label id="modalBorrarlbl"> </label>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Regresar</button>
					<form method="post" action="ListadoPublicadores">
						<input type="hidden" name="action" value="delete" /> <input
							type="hidden" id="hiddenId" name="hiddenId" />
						<button type="submit" class="btn btn-danger">Borrar</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<h4>Publicadores</h4>
		<div class="col-12 col-sm-12 col-lg-12">
			<div class="table-responsive">
				<table class="table" id="table" data-toggle="table">
					<thead>
						<tr>
							<th data-field="idpublicador">Id Publicador</th>
							<th data-field="nombre">Nombre</th>
							<th data-field="operate" data-formatter="operateFormatter"
								data-events="operateEvents"><a class="nuevo"
								id="nuevoButtonId" onclick="showModal()"
								href="javascript:void(0)" title="nuevo"> <i
									class="fas fa-plus fa-2x"></i></a></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listapublicadores}" var="c">
							<tr>
								<td><c:out value="${c.id}"></c:out></td>
								<td><c:out value="${c.nombre}"></c:out></td>
								<td></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="/Footer.jsp" />
	<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>

	<script src="bootstrap/js/bootstrap.bundle.js"></script>
	<script
		src="https://unpkg.com/bootstrap-table@1.18.3/dist/bootstrap-table.min.js"></script>

	<script>
		var $table = $('#table')

		function operateFormatter(value, row, index) {
			return [ '<a class="like" href="javascript:void(0)" title="Editar">',
					'<i class="fas fa-pencil fa-2x"></i>', '</a>  ',
										'<a class="remove" href="javascript:void(0)" title="Borrar">',
										'<i class="fas fa-trash fa-2x"></i>', '</a>'].join('')
		}

		window.operateEvents = {
			'click .like' : function(e, value, row, index) {
				$('#modalEditar').modal('show');
				$('#EditarPublicadorNombrelbl').text(
						"Editar publicador: " + row.nombre);
				$('#publicadorId').val([ row.idpublicador ]);
				$('#InputPublicadorId').val([ row.nombre ]);
			},
		'click .remove' : function(e, value, row, index) {
			$("#modalBorrarlbl").text(
					"Esta seguro de que quiere borrar el publicador "
							+ row.nombre);
			$("#modalBorrar").modal('show');
			$("#hiddenId").val([ row.idpublicador ]);
		}
		};
		
		function showModal(){
			$('#modalNuevo').modal('show');
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