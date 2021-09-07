<%@ page import="entities.Usuario"%>
<%@ page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="http://getbootstrap.com/favicon.ico">
<title>Listado de Usuarios</title>
<link href="Styles/Style.css" rel="stylesheet" type="text/css" />
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />

</head>
<body>
	<jsp:include page="/Navbar.jsp" />
	<div class="container">
		<div class="row">
			<h4>Usuarios</h4>
			<div class="col-12 col-sm-12 col-lg-12">
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th>Id</th>
								<th>Nombre usuario</th>
								<th>Email</th>
								<th>Nickname</th>
								<th>Fecha de nacimiento</th>
								<th>Telefono</th>
								<th>Tipo Usuario</th>
								<th></th>
								<!-- editar -->
								<th></th>
								<!-- borrar -->
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
									<!-- editar -->
									<td></td>
									<!-- borrar -->
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	</main>
	<jsp:include page="/Footer.jsp" />
</body>
</html>