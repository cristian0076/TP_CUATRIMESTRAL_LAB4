<%@page import="entidad.Usuarios"%>
<%@page import="entidad.Cuentas"%>
<%@page import="entidad.CuentasPorUsuario"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>ABM Cuentas</title>

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
					class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" style="color: white" href="#"
				id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Administrar clientes
			</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="ABMclientes.jsp">ABM Clientes</a> <a
						class="dropdown-item" href="ABMCuentas.jsp">Apertura de
						cuentas</a>
				</div></li>
			<li class="nav-item active"><a class="nav-link"
				href="Reportes.jsp" style="color: white">Reportes <span
					class="sr-only">(current)</span></a></li>
		</ul>
	</div>
	<span id="perfil" class="navbar-text" style="padding: 10px"> <a
		href="DatosPersonalesADM.jsp"> <img
			src="https://i.ibb.co/Xzbf1pS/usuario.png" />
	</a> Perfil
	</span> <span id="salir" class="navbar-text"> <a href="Login.jsp">
			<img src="https://i.ibb.co/TcV6LW4/salir-arriba-a-la-derecha.png" />
	</a> Salir
	</span> </nav>

	<h1 style="text-align: center; padding-top: 30px;"
		class="font-italic text">Administración de cuentas</h1>

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
			<form method="post" action="ServletCuentas">
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
									<th scope="col"></th>
									<th scope="col">ID</th>
									<th scope="col">Usuario</th>
									<th scope="col">Nombre</th>
									<th scope="col">Apellido</th>
									<th scope="col">Email</th>
									<th scope="col">Dni</th>
									<th scope="col">Cuil</th>
									<th scope="col">Cant. Cuentas</th>
								</tr>
							</thead>
							<tbody>

								<%
									ArrayList<CuentasPorUsuario> listaCu = null;
									if (request.getAttribute("BusquedaCu") != null) {
										listaCu = (ArrayList<CuentasPorUsuario>) request.getAttribute("BusquedaCu");
									}
								%>
								<%
									if (listaCu != null)
										for (CuentasPorUsuario cu : listaCu) {
								%>
								<tr style="color: black">
									<form method="post" action="ServletCuentas">
										<td><input HeaderText="Borrado" class="btn btn-primary"
											type="submit" name="btnListar" Onclick="abrir()"
											id="btnListar" value="Listar"></td>

										<td><input class="btn btn-primary" type="submit"
											name="btnModalAgregar" id="btnModalAgregar"
											Onclick="Agregar()" value="Agregar"></td>

										<th scope="row"><%=cu.getUsuario().getIdUsuario()%> 
											<input type="hidden" name="idUsuario" value="<%=cu.getUsuario().getIdUsuario()%>">
										</th>
										<td><%=cu.getUsuario().getNombreUsuario()%></td>
										<td><%=cu.getUsuario().getNombre()%></td>
										<td><%=cu.getUsuario().getApellido()%></td>
										<td><%=cu.getUsuario().getEmail()%></td>
										<td><%=cu.getUsuario().getDni()%></td>
										<td><%=cu.getUsuario().getCuil()%></td>
										<th scope="row" Style="text-align:center;"><%=cu.getCantidadCuentas()%> 
											<input type="hidden" name="idUsuario" value="<%=cu.getCantidadCuentas()%>">
										</th>
									</form>
								</tr>
							</tbody>
							<%
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
			<form method="post" action="ServletCuentas">
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
									<th scope="col"></th>
									<th scope="col">Id</th>
									<th scope="col">Apellido</th>
									<th scope="col">Cuil</th>
									<th scope="col">Tipo Cuenta</th>
									<th scope="col">Nro. Cuenta</th>
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
								ArrayList<Cuentas> CuentasUsuario = null;
								if (request.getAttribute("listaC") != null) {
									CuentasUsuario = (ArrayList<Cuentas>) request.getAttribute("listaC");
								}
							%>


							<%
								if (CuentasUsuario != null) {
									for (Cuentas c : CuentasUsuario) {
							%>
							<tbody>
								<tr>
									<form method="post" action="ServletCuentas">
										<td><input HeaderText="Modificar" class="btn btn-primary"
											type="submit" name="btnModalModificar"
											Onclick="abrirModificar()" id="btnModalModificar"
											value="Modificar"></td>

										<td><input HeaderText="Eliminar" class="btn btn-primary"
											type="submit" name="btnEliminar" Onclick="abrirEliminar()"
											id="btnModalEliminar" value="Eliminar"></td>

										<th scope="row"><%=c.getUsuario().getIdUsuario()%></th>
										<td><%=c.getUsuario().getApellido()%></td>
										<td><%=c.getUsuario().getCuil()%></td>
										<td><%=c.getTipoDeCuenta().getDescripcion()%></td>
										<th scope="row" Style="text-align:center;"><%=c.getNroDeCuenta()%> <input
											type="hidden" name="idCuentaEdit"
											value="<%=c.getNroDeCuenta()%>"></th>
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


	<div class="modal fade bd-example-modal-lg" id="modalAgregar"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<form method="post" action="ServletCuentas">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="staticBackdropLabel3">Agregar
							cuenta</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<%
						Usuarios usuario = null;
						if (request.getAttribute("usuarioCta") != null) {
							usuario = (Usuarios) request.getAttribute("usuarioCta");
							System.out.println(usuario.getDni());
					%>

					<div class="modal-body">
						<div class="form-group">

							<input type="hidden" ID="Id" class="form-control" name="txtId"
								Style="margin: 5px;" value="<%=usuario.getIdUsuario()%>">

							<h5>Nombre</h5>
							<input type="text" ID="Nombre" class="form-control"
								name="txtNombre" Style="margin: 5px;"
								value="<%=usuario.getNombre()%>" disabled>

							<h5>Apellido</h5>
							<input type="text" ID="Apellido" class="form-control"
								name="txtApellidoA" Style="margin: 5px;" placeholder="Apellido"
								Value=<%=usuario.getApellido()%> disabled>

							<h5>Dni</h5>
							<input type="text" ID="DNI" class="form-control"
								onkeypress="javascript:return solonumeros(event)" name="txtDni"
								placeholder="Dni" value=<%=usuario.getDni()%>
								Style="margin: 5px;" disabled>

							<h5>Tipo De Caja</h5>
							<select id="txtTipoCaja" name="txtTipoCaja" class="form-control"
								required="required">
								<option value="1">Caja de ahorro</option>
								<option value="2">Cuenta corriente</option>
							</select>
						</div>

						<%
							}

							if (request.getAttribute("usuarioCta") != null) {
						%>

						<script type="text/javascript">
							
 							$(function(){
  								$('#modalAgregar').modal();
 							});
 							
						</script>

						<%
							}
						%>
					</div>

					<div class="modal-footer">
						<input class="btn btn-primary col text-center" type="submit"
							value="Agregar" name="btnAgregar">
					</div>
				</div>
		</div>
	</div>


	<div class="modal fade bd-example-modal-lg" id="modalModificar"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<form method="post" action="ServletCuentas">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="staticBackdropLabel3">Modificar
							Cuenta</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<%
						Cuentas cuentaEdit = null;
						if (request.getAttribute("usuarioCtaEdit") != null) {
							cuentaEdit = (Cuentas) request.getAttribute("usuarioCtaEdit");
					%>

					<div class="modal-body">
						<div class="form-group">

							<input type="hidden" ID="Id" class="form-control"
								name="txtIdCtaEdit" Style="margin: 5px;"
								value="<%=cuentaEdit.getNroDeCuenta()%>">

							<h5>Saldo</h5>
							<input type="text" ID="Nombre" class="form-control"
								name="txtSaldo" Style="margin: 5px;"
								value="<%=cuentaEdit.getSaldo()%>">

							<h5>Tipo De Caja</h5>
							<select id="txtTipoCaja" name="txtTipoCaja" class="form-control"
								required="required">
								<option value="1">Caja de ahorro</option>
								<option value="2">Cuenta corriente</option>
							</select>

						</div>

						<%
							}

							if (request.getAttribute("usuarioCtaEdit") != null) {
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


	<div class="modal fade bd-example-modal-sm" id="modalEliminar"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel2">Eliminar
						cliente</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="form-group">¿Esta seguro de eliminar el cliente
						seleccionado?</div>
				</div>
				<div class="modal-footer">
					<form method="post" action="ServletUsuarios">
						<input class="btn btn-primary col text-center" type="submit"
							value="Confirmar" name="btnEliminarE">
					</form>
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
        
        function abrirAgregar() {
            $('#modalAgregar').modal('show');
        }
        
        function abrirModificar() {
            $('#modalModificar').modal('show');
        }
        
        function abrirEliminar() {
            $('#modalEliminar').modal('show');
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