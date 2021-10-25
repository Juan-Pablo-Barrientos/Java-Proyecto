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
	<c:if test="${not empty result}">
		<div class="modal fade" id="modalExito" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Lista de
							juegos actualizada!</h5>
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
					<h5 class="modal-title" id="EditarJuegoNombrelbl">Editar Juego</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form action="ListadoJuegos" method="post" onSubmit="">
					<input type="hidden" name="action" value="" id="action" />
					<div class="modal-body">
						<div class="form-group">
							<input type="text" class="form-control" id="juegoId"
								placeholder="Ingrese nombre del juego" name="juegoId"
								hidden="true">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" id="publicadorId"
								placeholder="Ingrese nombre del juego" name="publicadorId"
								hidden="true">
						</div>
						<div class="form-group">
							<label id="publicadorLblId" for="juegoNombreId">Publicador</label>
							<div class="input-group mb-3">
								<select name="publicadorNombreId" id="publicadorNombreId"
									class="form-control" required>
									<option value="">Seleccione publicador</option>
									<c:forEach items="${listapubs}" var="publicador">
										<option value="${publicador.id}">${publicador.nombre}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" id="desarrolladorId"
								placeholder="Ingrese nombre del juego" name="desarrolladorId"
								hidden="true">
						</div>
						<div class="form-group">
							<label for="juegoNombreId" id="desarrolladorLblId">Desarrollador</label>
							<div class="input-group mb-3">
								<select name="desarrolladorNombreId" id="desarrolladorNombreId"
									class="form-control" required>
									<option value="">Seleccione desarrollador</option>
									<c:forEach items="${listadevs}" var="desarrollador">
										<option value="${desarrollador.id}">${desarrollador.nombre}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="juegoNombreId">Juego</label>
							<div class="input-group mb-3">
								<input type="text" class="form-control" id="juegoNombreId"
									placeholder="Ingrese nombre del juego" name="juegoNombreId"
									required required>
							</div>
						</div>
						<div class="form-group">
							<label for="juegoPrecioBaseId">Precio base</label>
							<div class="input-group mb-3">
								<span class="input-group-text">$</span> <input type="text"
									id="juegoPrecioBaseId" name="juegoPrecioBaseId"
									placeholder="Ingrese precio base del juego"
									class="form-control" required>
							</div>
						</div>
						<div class="form-group">
							<label for="juegoDescuentoId" id="lblDescuentoId">Descuento</label>
							<div class="input-group mb-3" id="juegoDescuentoDiv">
								<input type="number" class="form-control" id="juegoDescuentoId2"
									name="juegoDescuentoId2" max="100" min="0" required
									placeholder="Ingrese descuento del juego" /> <span
									class="input-group-text">%</span>
							</div>
						</div>
						<div class="form-group">
							<label for="juegoGeneroId">Genero</label>
							<div class="input-group mb-3">
								<input type="text" class="form-control" id="juegoGeneroId"
									name="juegoGeneroId" placeholder="Ingrese genero del juego"
									required />
							</div>
						</div>
						<div class="form-group">
							<label for="juegoNombreId">Fecha de publicacion</label>
							<div class="input-group mb-3">
								<input type="date" class="form-control" id="juegoFechaId"
									placeholder="Ingrese nombre del juego" name="juegoFechaId"
									required>
							</div>
						</div>
						<div class="form-group">
							<label for="juegoNombreId">Reestriccion por edad</label>
							<div class="input-group mb-3">
								<input type="text" class="form-control" id="juegoRestriccionId"
									placeholder="Ingrese reestriccion por edad del juego"
									name="juegoReestriccionId">
							</div>
						</div>
						<div class="form-group">
							<label for="juegoDescuentoId" id="lblDescripcion">Descripcion</label>
							<div class="mb-3">
								<textarea id="juegoDescripcionId" class="form-control" rows="5"
									name="juegoDescripcionId" required
									placeholder="Ingrese descripcion del juego"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="juegoUrlId" id="lblUrl">Url</label>
							<div class="mb-3">
								<input type="text" class="form-control" id="juegoUrlId"
									placeholder="Ingrese url del juego (Iframe)"
									name="juegoUrlId" required>
							</div>
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
	<div class="modal fade" id="modalDescuento" tabindex="-1"
		data-bs-backdrop="static" data-bs-keyboard="false"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="EditarJuegoDescuentolbl">Editar
						descuento</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form action="ListadoJuegos" method="post" onSubmit="">
					<input type="hidden" name="actionDiscount" value="descuento" />
					<div class="modal-body">
						<div class="form-group">
							<input type="text" class="form-control" id="hiddenIdDescuento"
								placeholder="Ingrese nombre de usuario" name="juegoId"
								hidden="true">
						</div>
						<div class="form-group">
							<label for="juegoDescuentoId">Descuento</label>
							<div class="input-group mb-3">
								<input type="number" class="form-control" id="juegoDescuentoId"
									name="juegoDescuentoId" max="100" min="0" required
									placeholder="Ingrese descuento del juego" /> <span
									class="input-group-text">%</span>
							</div>
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
	<div class="modal fade" id="modalDescripcion" tabindex="-1"
		data-bs-backdrop="static" data-bs-keyboard="false"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="EditarDescripcionlbl">Editar
						descripcion</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form action="ListadoJuegos" method="post" onSubmit="">
					<input type="hidden" name="actionDesc" value="desc" />
					<div class="modal-body">
						<div class="form-group">
							<input type="text" class="form-control" id="hiddenIdDescripcion"
								placeholder="Ingrese nombre de usuario" name="juegoId"
								hidden="true">
						</div>
						<div class="form-group">
							<label for="juegoDescuentoId">Descripcion</label>
							<div class="mb-3">
								<textarea id="juegoDescripcionId2" class="form-control" rows="5"
									name="juegoDescripcionId2" required
									placeholder="Ingrese descripcion del juego"></textarea>
							</div>
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
					<h5 class="modal-title" id="exampleModalLabel">Borrar juego</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<label id="modalBorrarlbl"> </label>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Regresar</button>
					<form method="post" action="ListadoJuegos">
						<input type="hidden" name="actionDelete" value="delete" /> <input
							type="hidden" id="hiddenIdDelete" name="hiddenId">
						<button type="submit" class="btn btn-danger">Borrar</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<h4>Juegos</h4>
		<div class="col-12 col-sm-12 col-lg-12">
			<div class="table-responsive fixed-table-body">
				<table class="table hideFullColumn  " id="table" data-toggle="table"
					style="table-layout: fixed; width: 100%;">
					<thead>
						<tr>
							<th data-field="idjuego">Id</th>
							<th data-field="idpublicador" class="hidecol">Id Publicador</th>
							<th data-field="nombrePublicador">Publicador</th>
							<th data-field="iddesarrollador" class="hidecol">Id
								Desarrollador</th>
							<th data-field="nombreDesarrollador">Desarrollador</th>
							<th data-field="nombreJuego">Nombre</th>
							<th data-field="precio">Precio</th>
							<th data-field="descuento">Descuento</th>
							<th data-field="genero">Genero</th>
							<th data-field="fechapublicacion">Publicacion</th>
							<th data-field="restriccion">Restriccion</th>
							<th data-field="descripcion" class="hidecol">Descripcion</th>
							<th data-field="url" class="">Url</th>
							<th data-field="operate" data-formatter="operateFormatter"
								data-events="operateEvents"><a class="nuevo"
								id="nuevoButtonId" onclick="createJuego()"
								href="javascript:void(0)" title="nuevo"> <i
									class="fas fa-plus fa-2x"></i></a></th>
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
								<td><c:out value="${c.juego.descuento*100}"></c:out>%</td>
								<td><c:out value="${c.juego.genero}"></c:out></td>
								<td><c:out value="${c.juego.fecha_publicacion}"></c:out></td>
								<td><c:out value="${c.juego.reestriccionPorEdad}"></c:out></td>
								<td><c:out value="${c.juego.descripcion}"></c:out></td>
								<td><c:out value="${c.juego.url}"></c:out></td>
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
			return [ '<a class="like" href="javascript:void(0)" title="Editar">',
					'<i class="fas fa-pencil fa-2x"></i>', '</a>  ',
					'<a class="descuento" href="javascript:void(0)" title="Descuento">',
					'<i class="fas fa-hand-holding-usd fa-2x"style="color:green""></i>', '</a>  ',
					'<a class="descripcion" href="javascript:void(0)" title="Descripcion">',
					'<i class="fas fa-book fa-2x"></i>', '</a>  ',
					'<a class="remove" href="javascript:void(0)" title="Borrar">',
					'<i class="fas fa-trash  fa-2x"style="color:red;"></i>', '</a>'].join('')
		}

		window.operateEvents = {
			'click .like' : function(e, value, row, index) {
				$('#modalEditar').modal('show');
				$("#action").val("edit");
				clearField();
				$("#EditarJuegoNombrelbl").text("Editar juego: "+row.nombreJuego);
				$("#desarrolladorNombreId").val([row.iddesarrollador]).change();
				$("#publicadorNombreId").val([row.idpublicador]).change();
				$("#juegoId").val([row.idjuego]);
				$("#publicadorId").val([row.idpublicador]);
				$("#desarrolladorId").val([row.iddesarrollador]);
				$("#juegoNombreId").val([row.nombreJuego]);
				let anyString =([row.precio]).toString();
				$("#juegoPrecioBaseId").val(anyString.substring(1, anyString.length - 2));
				$("#juegoDescuentoId").val([row.descuento]);
				$("#juegoGeneroId").val([row.genero]);
				$("#juegoFechaId").val([row.fechapublicacion]);
				$("#juegoRestriccionId").val([row.restriccion]);
				$("#juegoUrlId").val([row.url]);
			    $("#juegoDescripcionId").hide();
			    $("#lblDescripcion").hide();
			    $("#juegoDescuentoDiv").hide();
			    $("#lblDescuentoId").hide();
			    $('#juegoDescripcionId').removeAttr('required');
			    $('#juegoDescuentoId2').removeAttr('required');
			    },
			'click .remove' : function(e, value, row, index) {
			$("#modalBorrarlbl").text(
					"Esta seguro de que quiere borrar el juego "
							+ row.nombreJuego);
			$("#modalBorrar").modal('show');
			$("#hiddenIdDelete").val([ row.idjuego ]);
			},
			'click .descuento' : function(e, value, row, index) {
				$("#modalDescuento").modal('show');
				$("#EditarJuegoDescuentolbl").text("Editar descuento del juego: "+row.nombreJuego);
				$("#hiddenIdDescuento").val([ row.idjuego ]);
				$("#juegoDescuentoId").val(parseInt([ row.descuento ]));
			},
			'click .descripcion' : function(e, value, row, index) {
				$("#modalDescripcion").modal('show');
				$("#EditarDescripcionlbl").text("Editar descripcion del juego: "+row.nombreJuego);
				$("#hiddenIdDescripcion").val([ row.idjuego ]);
				$("#juegoDescripcionId2").val([ row.descripcion ]);
			}
		};
		
		function clearField(){
			$("#juegoId").val("");
			$("#publicadorId").val("");
			$("#publicadorNombreId").val("").change();
			$("#desarrolladorId").val("");
			$("#desarrolladorNombreId").val("").change();
			$("#juegoNombreId").val("");
			$("#juegoDescripcionId").val("");
			$("#juegoPrecioBaseId").val("");
			$("#juegoDescuentoId2").val("");
			$("#juegoGeneroId").val("");
			$("#juegoFechaId").val("");
			$("#juegoReestriccionId").val("");
			$("#juegoUrlId").val("");
		}
		
		function createJuego(){
			$('#modalEditar').modal('show');
			clearField();
			$("#action").val("new");
		    $("#juegoDescripcionId").show();
		    $("#lblDescripcion").show();
		    $("#juegoDescuentoDiv").show();
		    $("#lblDescuentoId").show();
			$("#EditarJuegoNombrelbl").text("Crear nuevo juego");
		    $('#juegoDescripcionId').Attr('required');
		    $('#juegoDescuentoId2').Attr('required');
		}
		
		$(window).on('load', function() {
			$('#modalExito').modal('show');
		});
		   const url = new URL(window.location.href)
	      const params = new URLSearchParams(url.search.slice(1))
	      window.history.replaceState(
	        {},
	        '',
	        `${window.location.pathname}?${"s=8"}${window.location.hash}`,
	      );
	</script>
</body>
</html>