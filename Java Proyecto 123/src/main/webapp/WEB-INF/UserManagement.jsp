<%@ page import="entities.Usuario"%>
<%@ page import="java.util.LinkedList"%>
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

<link href="bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://unpkg.com/bootstrap-table@1.18.3/dist/bootstrap-table.min.css">
<title>Listado usuarios</title>
</head>


<body>
	<jsp:include page="/Navbar.jsp" />
	<div class="container">
		<div class="row">
			<c:if test="${not empty result}">
				<div class="modal fade" id="modalExito" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Usuario borrado</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">El usuario ha sido borrado con exito!</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary" data-bs-dismiss="modal">Entendido!</button>
							</div>
						</div>
					</div>
				</div>
			</c:if>
			<h4>Usuarios</h4>
			<div class="col-12 col-sm-12 col-lg-12">
				<div class="table-responsive">
					<table class="table" id="table" data-toggle="table">
						<thead>
							<tr>
								<th data-field="id">Id</th>
								<th data-field="nombreUsuario">Nombre usuario</th>
								<th data-field="email">Email</th>
								<th data-field="nickname">Nickname</th>
								<th data-field="fechaNacimiento">Fecha de nacimiento</th>
								<th data-field="telefono">Telefono</th>
								<th data-field="tipo">Tipo Usuario</th>
								<th data-field="operate" data-formatter="operateFormatter"
									data-events="operateEvents"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listaUsuarios}" var="u">
								<tr>
									<td><c:out value="${u.id}"></c:out></td>
									<td><c:out value="${u.nombreUsuario}"></c:out></td>
									<td><c:out value="${u.email}"></c:out></td>
									<td><c:out value="${u.nickname}"></c:out></td>
									<td><c:out value="${u.fechaNacimiento}"></c:out></td>
									<td><c:out value="${u.telefono}"></c:out></td>
									<td><c:out value="${u.tipo}"></c:out></td>
									<td></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modalBorrar" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Borrar usuario</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<label id="modalBorrarlbl"> </label>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Regresar</button>
					<form method="post" action="ListadoUsuarios">
						<input type="hidden" name="action" value="delete" /> <input
							type="hidden" id="hiddenId" name="hiddenId" />
						<button type="submit" class="btn btn-primary">Borrar</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	</main>
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
					'<a class="like" href="javascript:void(0)" title="Like">',
					'<i class="fas fa-pencil"></i>',
					'</a>  ',
					'<a class="remove" href="javascript:void(0)" title="Remove">',
					'<i class="fas fa-trash"></i>', '</a>' ].join('')
		}

		window.operateEvents = {
			'click .like' : function(e, value, row, index) {
				alert([ row.id ])
			},
			'click .remove' : function(e, value, row, index) {
				$("#modalBorrarlbl")
						.text(
								("Esta seguro de que quiere borrar el usuario " + row.nickname));
				$("#modalBorrar").modal('show');
				$("#hiddenId").val([ row.id ]);
			}
		};
		$(window).on('load', function() {
			$('#modalExito').modal('show');
		});
	</script>
</body>
</html>