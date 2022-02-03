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


<link rel="stylesheet" type="text/css" href="css/Style.css" />
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/31c85e85ce.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://unpkg.com/bootstrap-table@1.18.3/dist/bootstrap-table.min.css">
<style>
tr {
	cursor: pointer;
}
</style>
</head>
<body>

	<jsp:include page="/Navbar.jsp" />
	<c:if test="${not empty result}">
		<div class="modal fade" id="modalBusquedaJuegos" data-bs-backdrop="static"
			data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="staticBackdropLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="staticBackdropLabel">Error de
							acceso al juego!</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<c:out value="${result} "></c:out>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							data-bs-dismiss="modal">Entendido!</button>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<div class="row">
		<h4>Juegos</h4>
	</div>
	<div class="row" style="text-align: center">
		<div id="liveAlertPlaceholder"></div>
		<div class="center">
			<button id="Estrategia" class="boton">Estrategia</button>
			<button id="MundoAbierto" class="boton">Mundo Abierto</button>
			<button id="Accion" class="boton">Acción</button>
			<button id="Rpg" class="boton">Rpg</button>
			<button id="Simulacion" class="boton">Simulacion</button>
			<button id="Deportes" class="boton">Deportes</button>
			<button id="Pelea" class="boton">Pelea</button>
			<button id="Todos" class="boton">Todos</button>
		</div>
	</div>
	<div class="row">
		<div class="col-12 col-sm-12 col-lg-12">
			<div class="table-responsive">
				<table class="table hideFullColumn" id="table" data-toggle="table"
					data-checkbox-header="false" data-toolbar="#toolbar">

					<thead>
						<tr>
							<th data-field="id" class="hidecol">Id</th>
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
							<tr class='clickable-row' data-href="Game?game=${j.id}" data-id="${j.id}">
								<td><c:out value="${j.id}"></c:out></td>
								<td><c:out value="${j.nombre}"></c:out></td>
								<td>$<c:out
										value="${j.precioBase - (j.precioBase * j.descuento)}"></c:out></td>
								<td><c:out value="${j.descuento * 100}%"></c:out></td>
								<td><c:out value="${j.genero}"></c:out></td>
								<td><c:out value="${j.fecha_publicacion}"></c:out></td>
								<td><c:out value="${j.reestriccionPorEdad}"></c:out></td>

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
		var $Estrategia = $('#Estrategia')
		var $MundoAbierto = $('#MundoAbierto')
		var $Accion = $('#Accion')
		var $Rpg = $('#Rpg')
		var $Simulacion = $('#Simulacion')
		var $Deportes = $('#Deportes')
		var $Pelea = $('#Pelea')
		var $Todos = $('#Todos')

		jQuery(document).ready(function($) {
			$(".clickable-row").click(function() {
				var jgId = $(this).data("id");
				var href = $(this).data("href");
				estaLogueado(jgId, href);
			});
		});
		function estaLogueado(jgId, href) {
			$.ajax({
				url : 'EstaLogeado',
				data : {
					"jgId" : jgId
				},
				type : 'post',
				cache : false,
				async : false,
				success : function(data) {
					parseInt(data);
					if (data == 1) {
						window.location = href
					} else if (data >= 2) {
						alertaLoginPorReestriccion(data)
					}
				},
				error : function() {
					alert('Ajax a fallado');
				}
			});
		};

		$(window).on('load', function() {
			$('#modalBusquedaJuegos').modal('show');
		});
		
		function alertaLoginPorReestriccion(data) {
			var alertPlaceholder = document
					.getElementById('liveAlertPlaceholder')

			function alert(message, type) {

				if (document.getElementById('error') != null) {
					document.getElementById('error').remove()
				}
				var wrapper = document.createElement('div')
				wrapper.innerHTML = '<div id="error" class="alert alert-' + type + ' alert-dismissible" role="alert">'
						+ message
						+ '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'
				alertPlaceholder.append(wrapper)
			}
			if (data == 2) {
				alert(
						'Este juego cuenta con una reestriccion por edad, por favor inicie sesion',
						'danger')
			} else {

				alert(
						'Usted no cuenta con la edad necesaria para ver este juego',
						'danger')
			}
		}
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

		$(function() {
			$Estrategia.click(function() {
				$table.bootstrapTable('filterBy', {
					genero : [ "Estrategia" ]
				})
			})
		})

		$(function() {
			$MundoAbierto.click(function() {
				$table.bootstrapTable('filterBy', {
					genero : [ "Mundo Abierto" ]
				})
			})
		})

		$(function() {
			$Accion.click(function() {
				$table.bootstrapTable('filterBy', {
					genero : [ "Accion" ]
				})
			})
		})

		$(function() {
			$Rpg.click(function() {
				$table.bootstrapTable('filterBy', {
					genero : [ "Rpg" ]
				})
			})
		})

		$(function() {
			$Simulacion.click(function() {
				$table.bootstrapTable('filterBy', {
					genero : [ "Simulacion" ]
				})
			})
		})

		$(function() {
			$Deportes.click(function() {
				$table.bootstrapTable('filterBy', {
					genero : [ "Deportes" ]
				})
			})
		})

		$(function() {
			$Pelea.click(function() {
				$table.bootstrapTable('filterBy', {
					genero : [ "Pelea" ]
				})
			})
		})

		$(function() {
			$Todos.click(function() {
				$table.bootstrapTable('filterBy', {
					genero : [ "Accion", "Mundo Abierto", "Estrategia", "Rpg",
							"Simulacion", "Deportes", "Pelea" ]
				})
			})
		})
	</script>
</body>
</html>