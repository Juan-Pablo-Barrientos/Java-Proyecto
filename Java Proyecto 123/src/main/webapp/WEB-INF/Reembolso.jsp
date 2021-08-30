<%@page import="entities.*"%>
<%@page import="logic.*"%>
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
<title>Reembolsos Pendientes</title>
<link href="Styles/Style.css" rel="stylesheet" type="text/css" />
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />

<%
LinkedList<Reembolso> rems = (LinkedList<Reembolso>) request.getAttribute("listaReembolso");
%>

</head>
<body>
	<div class="container">
		<div class="row">
			<h4>Reembolsos</h4>
			<div class="col-12 col-sm-12 col-lg-12">
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th>Id</th>
								<th>Nombre usuario</th>
								<th>IdUsuario</th>
								<th>Nombre del Juego</th>
								<th>HS Jugadas</th>
								<th>Estado</th>								
								<th></th>
								<!-- editar -->
								<th></th>
								<!-- borrar -->
							</tr>
						</thead>
						<tbody>
							<%
							for (Reembolso r : rems) {
							   Compra com = new CompraLogic().getOneByReembolso(r);						   
							%>
							<tr>
								<td><%=r.getId()%></td>
								<td><%=com.getNroSerie()%></td>						
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