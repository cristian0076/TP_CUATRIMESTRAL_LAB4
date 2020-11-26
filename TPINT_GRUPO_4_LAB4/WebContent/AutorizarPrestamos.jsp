<%@page import="entidad.Usuarios"%>
<%@page import="entidad.Cuentas"%>
<%@page import="entidad.CuentasPorUsuario"%>
<%@page import="entidad.Prestamos"%>
<%@page import="entidad.PrestamosPorUsuario"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Autorizar Prestamos</title>

<!--Bootstrap-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
<style type="text/css">
 	<jsp:include page="/Estilos/AMBclientes.css"></jsp:include>
 </style>
	
<!--Paginado-->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css">

<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>


<script type="text/javascript">
 	$(document).ready( function () {
 		$('#tablaCuentas').DataTable();
 	} );
 </script>

</head>

<body>

<nav
		class="navbar navbar-expand-lg navbar-light  bg-dark text-white-50">

		<a class="navbar-brand" style="color: white" href="#">Home Bank</a>


		<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active"><a class="nav-link"
					href="PrincipalADM.jsp" style="color: white">Inicio <span
						class="sr-only">(current)</span></a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" style="color: white" href="#"
					id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> Administrar
						clientes </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="ABMclientes.jsp">ABM Clientes</a> <a
							class="dropdown-item" href="ABMCuentas.jsp">Apertura de cuentas</a>
							<a
							class="dropdown-item" href="AutorizarPrestamos.jsp">Autorizar Prestamos</a>
					</div></li>
				<li class="nav-item active"><a class="nav-link" href="Reportes.jsp"
					style="color: white">Reportes <span class="sr-only">(current)</span></a>
				</li>
			</ul>
		</div>
		<%! Usuarios u = new Usuarios(); %>

		<span id="perfil" class="navbar-text" style="padding: 10px">
		<%
			if(request.getSession().getAttribute("Session_user") != null){
				u= (Usuarios)request.getSession().getAttribute("Session_user");
         	   	System.out.println(u.getApellido()); 
			}
         	   %>
         	   <%if(u.getApellido() != null){ %>
      		 <label><%=u.getNombre()+" "+u.getApellido() %></label>
      		 <%} %>
		 <a
			href="DatosPersonalesADM.jsp"> <img src="https://i.ibb.co/Xzbf1pS/usuario.png" />
		</a> Perfil
		</span> <span id="salir" class="navbar-text"> <a href="Login.jsp">
				<img src="https://i.ibb.co/TcV6LW4/salir-arriba-a-la-derecha.png" />
		</a> Salir
		</span>
	</nav>

	<h1 style="text-align: center; padding-top: 30px;"
		class="font-italic text">Autorizar Prestamos</h1>

	<h3 style="color: red">
		<%
			if (request.getAttribute("mensaje") != null) {
		%>

		<%=request.getAttribute("mensaje").toString()%>

		<%
			}
		%>
	</h3>

	<div class="container">
		<div class="footer-siempre-abajo" style="background-color: white">
			<form method="post" action="ServletPrestamoAdm">
				<div class="row">
					<div class="col-12" style="padding: 20px; text-align: center;">
						<div class="input-group" style="text-align: center;">
							<input type="text" ID="txtNombre" class="form-control"
								name="txtUsuario" Style="margin: 5px;" placeholder="Usuario">
							<span class="input-group-addon"></span> <input type="text"
								ID="txtMail" class="form-control" name="txtEmail"
								Style="margin: 5px;" placeholder="Email">
						</div>
						<div class="input-group" style="text-align: center;">
							<input type="text" ID="txtDni" class="form-control" name="txtDni"
								onkeypress="javascript:return solonumeros(event)"
								Style="margin: 5px; width: 100px;" placeholder="Dni"> <input
								type="text" ID="txtCuil" class="form-control" name="txtCuil"
								onkeypress="javascript:return solonumeros(event)"
								Style="margin: 5px; width: 100px;" placeholder="Cuil">
						</div>
						<div class="col text-center" style="padding:0;">
							<input class="btn btn-primary" name="btnBuscar" type="submit"
								value="Buscar" CssClass="btn btn-primary mb-2" style="width:200px">
						</div>
					</div>
				</div>

				<div class="row" style="overflow: auto; height: 400px;">

					<div class="col-md-12 col-sm-4">

						<table id="tablaCuentas" class="table-dark">
							<thead>
								<tr>
									<th scope="col"></th>
									<th scope="col">ID</th>
									<th scope="col">Usuario</th>
									<th scope="col">Nombre</th>
									<th scope="col">Apellido</th>
									<th scope="col">Email</th>
									<th scope="col">Dni</th>
									<th scope="col">Cuil</th>
									<th scope="col">Cant. Prestamos</th>
								</tr>
							</thead>
							<tbody>

								<%
									ArrayList<PrestamosPorUsuario> listaCu = null;
									if (request.getAttribute("BusquedaCu") != null) {
										listaCu = (ArrayList<PrestamosPorUsuario>) request.getAttribute("BusquedaCu");
									}
								%>
								<%
									if (listaCu != null)
										for (PrestamosPorUsuario cu : listaCu) {
											if (cu.getCantidadPrestamos() > 0){
								%>
								<tr style="color: black">
									<form method="post" action="ServletPrestamoAdm">
										<td><input HeaderText="Borrado" class="btn btn-primary"
											type="submit" name="btnListar" Onclick="abrir()"
											id="btnListar" value="Listar"></td>
											
										<th scope="row"><%=cu.getUsuario().getIdUsuario()%> 
											<input type="hidden" name="idUsuario" value="<%=cu.getUsuario().getIdUsuario()%>">
										</th>
										<td><%=cu.getUsuario().getNombreUsuario()%></td>
										<td><%=cu.getUsuario().getNombre()%></td>
										<td><%=cu.getUsuario().getApellido()%></td>
										<td><%=cu.getUsuario().getEmail()%></td>
										<td><%=cu.getUsuario().getDni()%></td>
										<td><%=cu.getUsuario().getCuil()%></td>
										<th scope="row" Style="text-align:center;"><%=cu.getCantidadPrestamos()%> 
											<input type="hidden" name="idUsuario" value="<%=cu.getUsuario().getIdUsuario()%>">
										</th>
									</form>
								</tr>
							</tbody>
							<%
											}
								}
							%>
						</table>
					</div>
				</div>
			</form>
		</div>
	</div>


	<div class="modal fade bd-example-modal-sm" id="modalListar"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<form method="post" action="ServletPrestamoAdm">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="staticBackdropLabel2">Listar
							Cuentas</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<table class="table table-dark">
							<thead>
								<tr>
									<th scope="col"></th>
									<th scope="col">Id</th>
									<th scope="col">Apellido</th>
									<th scope="col">Imp. Solicitado</th>
									<th scope="col">Imp. Con Interes</th>
									<th scope="col">Imp. Mensual</th>
									<th scope="col">Estado Prestamo</th>
									<th scope="col">Cant. Cuotas</th>
								</tr>
							</thead>

							<%
								if (request.getAttribute("listaC") != null) {
							%>
							<script type="text/javascript">
                 				$(function(){
                  					$('#modalListar').modal();
                     				});
                			</script>
							<%
								}
							%>

							<%
								ArrayList<Prestamos> PrestamosUsuario = null;
								if (request.getAttribute("listaC") != null) {
									PrestamosUsuario = (ArrayList<Prestamos>) request.getAttribute("listaC");
								}
							%>


							<%
								if (PrestamosUsuario != null) {
									for (Prestamos c : PrestamosUsuario) {
							%>
							<tbody>
								<tr>
									<form method="post" action="ServletPrestamoAdm">
										<td><input HeaderText="Modificar" class="btn btn-primary"
											type="submit" name="btnModalModificar"
											Onclick="abrirModificar()" id="btnModalModificar"
											value="Autorizar"></td>

										<th scope="row"><%=c.getUsuario().getIdUsuario()%></th>
										<td><%=c.getUsuario().getApellido()%></td>
										<td><%=c.getImporteSolicitado()%></td>
										<td><%=c.getImporteConIntereses()%></td>
										<td><%=c.getValorCuotaMensual()%></td>
										<td><%=c.getEstadoPrestamo().getDescripcion()%></td>
										<th scope="row" Style="text-align:center;"><%=c.getPlazoDePago()%> <input
											type="hidden" name="idPrestamoEdit"
											value="<%=c.getIdPrestamo()%>"></th>
									</form>
								</tr>
								<%
									}
										}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="modal fade bd-example-modal-lg" id="modalModificar"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<form method="post" action="ServletPrestamoAdm">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="staticBackdropLabel3">Autorizar
							Prestamo</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<%
						Prestamos prestamoEdit = null;
						if (request.getAttribute("prestamoCtaEdit") != null) {
							prestamoEdit = (Prestamos) request.getAttribute("prestamoCtaEdit");
					%>

					<div class="modal-body">
						<div class="form-group">

							<input type="hidden" ID="Id" class="form-control"
								name="txtIdPrestamoEdit" Style="margin: 5px;"
								value="<%=prestamoEdit.getIdPrestamo()%>">
								
							<h5>Estado de Prestamo</h5>
							<select id="txtEstadoPrestamo" name="txtEstadoPrestamo" class="form-control"
								required="required">
								<option value="2">Confirmado</option>
								<option value="3">Rechazado</option>
							</select>

						</div>

						<%
							}

							if (request.getAttribute("prestamoCtaEdit") != null) {
						%>

						<script type="text/javascript">
							
 						$(function(){
  								$('#modalModificar').modal();
 						});
 							
					</script>

						<%
							}
						%>
					</div>

					<div class="modal-footer">
						<input class="btn btn-primary col text-center" type="submit"
							value="Modificar" name=btnModificar>
					</div>
				</div>
		</div>
	</div>
	<% String mensaje = null;
	if(request.getAttribute("MensajeAutorizacion")!= null)
	{
		
		mensaje = request.getAttribute("MensajeAutorizacion").toString();
	
	%>
	    <script> 
	    window.onload = function abrir() {
	        $('#modalMensaje').modal('show');
	    }
	    </script>
	<%
	}else{
	
	 %>	
	    
	<%}
	%>
<div class="modal fade" id="modalMensaje" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Mensaje</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <%= mensaje %>
      </div>
      <div class="modal-footer">
          <a href="AutorizarPrestamos.jsp" class="btn btn-secondary" >Close</a>
       </div>
    </div>
  </div>
</div>
	<script>

        function solonumeros(e) {
            var key;
            if (window.event) // IE
            {
                key = e.keyCode;
            }
            else if (e.which) // Netscape/Firefox/Opera
            {
                key = e.which;
            }
            if (key < 48 || key > 57) {
                return false;
            }
            return true;
        }
        
        
        function abrirListar() {
            $('#modalListar').modal('show');
        }
        

        function abrirModificar() {
            $('#modalModificar').modal('show');
        }
        

        
    </script>

	<!-- Footer -->
	<footer id="sticky-footer" class="py-4 bg-dark text-white-50">
	<div class="container text-center">
		<span class="logo-text">© 2020 - By Grupo Nro 4 LAB4 - todos
			los derechos reservados </span>
	</div>
	</footer>

</body>
</html>