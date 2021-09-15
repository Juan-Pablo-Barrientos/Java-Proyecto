<%@page import="entities.*"%>
<%@page import="logic.*"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<%
LinkedList<Reembolso> rems = (LinkedList<Reembolso>) request.getAttribute("listaReembolso");
%>

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
								<th>Id Reembolso</th>
								<th>Nombre usuario</th>
								<th>IdUsuario</th>
								<th>Nombre del Juego</th>
								<th>HS Jugadas</th>
								<th>Estado</th>								
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<%
							for (Reembolso r : rems) {
							   Compra com = new CompraLogic().getOneByReembolso(r);
							   Juego j = new JuegoLogic().getOne(com.getId_juego());
							   Usuario u = new UsuarioLogic().getOne(com.getId_usuario());
							%>
							<tr>
								<td><%=r.getId()%></td>
								<td><%=u.getNombreUsuario()%></td>
								<td><%=u.getId()%></td>	
								<td><%=j.getNombre()%></td>	
								<td><%=com.getHoras_jugadas()%></td>	
								<td><%=r.getEstado()%></td>							
								<td></td>
								<td></td>
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
	</main>
<jsp:include page="/Footer.jsp"/>
</body>
</html>