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
<title>Lista de Juegos</title>
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

	<div class="row">
		<h4>Juegos</h4>
		<div class="col-12 col-sm-12 col-lg-12">
			<div class="table-responsive">
				<table class="table hideFullColumn" id="table" data-toggle="table">
					<thead>
						<tr>
							<th data-field="idjuego">Id Juego</th>
							<th data-field="idpublicador" class="hidecol">Id Publicador</th>
							<th data-field="nombrePublicador">Nombre del Publicador</th>
							<th data-field="iddesarrollador" class="hidecol">Id
								Desarrollador</th>
							<th data-field="nombreDesarrollador">Nombre del
								Desarrollador</th>
							<th data-field="nombreJuego">Nombre del juego</th>
							<th data-field="precio">Precio</th>
							<th data-field="descuento">Descuento</th>
							<th data-field="genero">Genero</th>
							<th data-field="fechapublicacion">Fecha de publicacion</th>
							<th data-field="resticcion">Restriccion por edad</th>
							<th data-field="operate" data-formatter="operateFormatter"
								data-events="operateEvents"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listajuegosview}" var="c">
							<tr>
								<td><c:out value="${c.juego.id}"></c:out></td>
								<td><c:out value="${c.juego.idPublicador}"></c:out></td>
								<td><c:out value="${c.publicador.nombre}"></c:out></td>
								<td><c:out value="${c.juego.idDesarrollador}"></c:out></td>
								<td><c:out value="${c.desarrollador.nombre}"></c:out></td>
								<td><c:out value="${c.juego.nombre}"></c:out></td>
								<td>$<c:out value="${c.juego.precioBase}"></c:out></td>
								<td><c:out value="${c.juego.descuento}"></c:out></td>
								<td><c:out value="${c.juego.genero}"></c:out></td>
								<td><c:out value="${c.juego.fecha_publicacion}"></c:out></td>
								<td><c:out value="${c.juego.reestriccionPorEdad}"></c:out></td>
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
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>

	<script src="bootstrap/js/bootstrap.bundle.js"></script>
	<script
		src="https://unpkg.com/bootstrap-table@1.18.3/dist/bootstrap-table.min.js"></script>

	<script>
		var $table = $('#table');
		function operateFormatter(value, row, index) {
			return [
					'<a class="like" href="javascript:void(0)" title="Like">',
					'<i class="fas fa-heart fa-2x"></i>',
					'</a>  ',
					'<a class="remove" href="javascript:void(0)" title="Remove">',
					'<i class="fas fa-trash fa-2x"></i>', '</a>' ].join('')
		}

		window.operateEvents = {
			'click .like' : function(e, value, row, index) {
				alert([ row.idpublicador ])
			},
			'click .remove' : function(e, value, row, index) {
				$table.bootstrapTable('remove', {
					field : 'id',
					values : [ row.id ]
				})
			}
		}
	</script>
</body>
</html>