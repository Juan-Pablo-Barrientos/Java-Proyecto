<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="entities.*"%>
<%@page import="logic.*"%>
<%@page import="java.util.LinkedList"%>
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
				<table class="table" id="table" data-toggle="table"
					data-checkbox-header="false">
					<thead>
						<tr>
							<th data-field="nombreJuego">Nombre del juego</th>
							<th data-field="precio">Precio Final</th>
							<th data-field="descuento">Descuento</th>
							<th data-field="genero">Genero</th>
							<th data-field="fechapublicacion">Fecha de publicacion</th>
							<th data-field="reestriccionPorEdad">Restricción de edad</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${juegosBusqueda}" var="j">
							<tr>
								<td><c:out value="${j.nombre}"></c:out></td>
								<td>$<c:out
										value="${j.precioBase - (j.precioBase * j.descuento)}"></c:out></td>
								<td><c:out value="${j.descuento * 100}%"></c:out></td>
								<td><c:out value="${j.genero}"></c:out></td>
								<td><c:out value="${j.fecha_publicacion}"></c:out></td>
								<td><c:if test="${j.reestriccionPorEdad == 1}">
										<input class="form-check-input" type="checkbox" value="" id="flexCheckCheckedDisabled" checked disabled>
									</c:if>
									<c:if test="${j.reestriccionPorEdad == 0}">
										<input class="form-check-input" type="checkbox" value="" id="flexCheckDisabled" disabled>
									</c:if></td>

								<!-- <td><c:out value="${j.reestriccionPorEdad}"></c:out></td>	 -->
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
		var $table = $('#table')

		function stateFormatter(value, row, index) {
			if ([ row.precio ] > 1000) {
				return {
					checked : true,
					disabled : true
				}
			} else {
				return {
					checked : false,
					disabled : false

				}
			}
			return value
		}
	</script>
</body>
</html>