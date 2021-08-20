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
LinkedList<Usuario> lu = (LinkedList<Usuario>) request.getAttribute("listaUsuarios");
%>

</head>
<body>
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
							<%
							for (Usuario u : lu) {
							%>
							<tr>
								<td><%=u.getId()%></td>
								<td><%=u.getNombreUsuario()%></td>
								<td><%=u.getEmail()%></td>
								<td><%=u.getNickname()%></td>
								<td><%=u.getFechaNacimiento()%></td>
								<td><%=u.getTelefono()%></td>
								<td><%=u.getTipo()%></td>
								<td></td>
								<!-- editar -->
								<td></td>
								<!-- borrar -->
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>