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
<link rel="icon" href="http://getbootstrap.com/favicon.ico">
<title>Reembolsos Pendientes</title>
<link href="Styles/Style.css" rel="stylesheet" type="text/css" />
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />

</head>
<body>
<jsp:include page="/Navbar.jsp"/>
	<div class="container">
		<div class="row">
			<h4>Reembolsos</h4>
			<div class="col-12 col-sm-12 col-lg-12">
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th data-field="idReembolso">Id Reembolso</th>
								<th data-field="nombreUsuario">Nombre del usuario</th>
								<th data-field="idUsuario">Id Usuario</th>
								<th data-field="nombreJuego">Nombre del juego</th>
								<th data-field="horasJugadas">Horas jugadas</th>
								<th data-field="estado">Estado</th>						
								<th data-field="operate" data-formatter="operateFormatter"
									data-events="operateEvents"></th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${listaCompraView}" var="c">
								<tr>
									<td><c:out value="${c.reembolso.id}"></c:out></td>
									<td><c:out value="${c.usuario.nombreUsuario}"></c:out></td>
									<td><c:out value="${c.usuario.id}"></c:out></td>
									<td><c:out value="${c.juego.nombre}"></c:out></td>			
									<td><c:out value="${c.compra.horas_jugadas}"></c:out></td>
									<td><c:out value="${c.reembolso.estado}"></c:out></td>														
									<td></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	</main>
<jsp:include page="/Footer.jsp"/>
</body>
</html>