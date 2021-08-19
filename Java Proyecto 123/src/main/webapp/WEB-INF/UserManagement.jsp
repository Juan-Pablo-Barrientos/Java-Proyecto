<%@page import="entities.Usuario"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>User Management</title>
<link href="Styles/Style.css" rel="stylesheet" type="text/css" />
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />

<%
Usuario usr = (Usuario) session.getAttribute("usuario");
LinkedList<Usuario> lu = (LinkedList<Usuario>) request.getAttribute("listaPersonas");
%>

</head>
<body>
	<div class="container">
		<div class="row">
			<h4>Usuarios</h4>
			<div class="col-12 col-sm-12 col-lg-12">
				<div class="table-responsive">
					<table class="table"></table>
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
						<%
						for (Usuario u : lu) {
						%>
						<tr>
							<td><%=usr.getId()%></td>
							<td><%=usr.getNombreUsuario()%></td>
							<td><%=usr.getEmail()%></td>
							<td><%=usr.getNickname()%></td>
							<td><%=usr.getFechaNacimiento()%></td>
							<td><%=usr.getTelefono()%></td>
							<td><%=usr.getTipo()%></td>
							<td></td>
							<!-- editar -->
							<td></td>
							<!-- borrar -->
						</tr>
						<%
						}
						%>
					</tbody>
				</div>
			</div>
		</div>
	</div>
</body>
</html>