<%@page import="entidad.Cuentas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style type="text/css">
<jsp:includepage="/Estilos/PrincipalADM.css"></jsp:include></style>
</head>
<body>

	<nav
		class="navbar navbar-expand-lg navbar-light  bg-dark text-white-50">
	<a class="navbar-brand" style="color: white" href="#">Home Bank</a>
	<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
			<li class="nav-item active"><a class="nav-link"
				href="PrincipalCLI.jsp" style="color: white">Inicio <span
					class="sr-only">(current)</span></a></li>

			<li class="nav-item active"><a class="nav-link"
				href="CuentasCli.jsp" style="color: white">Cuentas <span
					class="sr-only">(current)</span></a></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" style="color: white" href="#"
				id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Prestamos </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="ListarPrestamos.jsp">Mis
						Prestamos</a> <a class="dropdown-item" href="SolicitarPrestamo.jsp">Solicitar
						Prestamo</a>
				</div></li>

		</ul>
	</div>

	<span id="perfil" class="navbar-text" style="padding: 10px"> <label
		id="Usuario">Usuario Activo</label> <a href="DatosPersonales.jsp">
			<img src="https://i.ibb.co/Xzbf1pS/usuario.png" />
	</a> Perfil
	</span> <span id="salir" class="navbar-text"> <a href="Login.jsp">
			<img src="https://i.ibb.co/TcV6LW4/salir-arriba-a-la-derecha.png" />
	</a> Salir
	</span> </nav>
	
	
	


	<div class="container fluid">
		<div class="row">
			<div class="col-sm"></div>
			<div class="col-sm-6">
				<form method="post" action="ServletPrestamoCLI">
					<div style="padding-top: 100px;">
						<div style="text-align: center; padding-top: 30px;"
							class="container mt-3">
							<h2>Solicitud de Préstamo</h2>
							<p>Sujeto a aprobación crediticia.</p><br>
							
							
							<%if(request.getAttribute("MontoInvalido")!= null){ %>
									<p style="color: red"><%=request.getAttribute("MontoInvalido").toString()%></p>
										<%request.setAttribute("MontoInvalido",null);%>							
							<%} %>
							
							

							<br>
						</div>

						<div>
							<select name="cboCuenta" required="required" class="form-control">
								<option value=0 selected="true" disabled="disabled">seleccionar
									cuenta</option>
								<%  if(request.getAttribute("listaC")!=null)
							for(Cuentas cuenta : (ArrayList<Cuentas>)request.getAttribute("listaC")) 
							{%>
								<option value="<%= cuenta.getNroDeCuenta() %>"><%= "Nro: "+cuenta.getNroDeCuenta()+" - "+ cuenta.getTipoDeCuenta().getDescripcion() %></option>
								<%} %>
								
								
								<% if(request.getAttribute("SolicitudOk")!= null){ %>
									
			
 										<script type="text/javascript">
 						 				$(function(){
 						  					$('#modalSolicitudOk').modal();
 						 				});
 										</script>
									
									
									<%request.setAttribute("SolicitudOk", null); %>
								<%} %>
							</select>
						</div>
						<br>
						<div>
							<div>
							<%if(request.getAttribute("MontoCuotas")!= null){ %>
								<input name="txtMonto" class="form-control"
									onkeypress="javascript:return solonumeros(event)" id="myInput"
									type="text" 
									placeholder="$ monto solicitado">			
							<%}else{ %>
									<input name="txtMonto" class="form-control"
									onkeypress="javascript:return solonumeros(event)" id="myInput"
									type="text" required="required"
									placeholder="$ monto solicitado">
							<%} %>
								
							</div>
							<div
								style="width: 60%; text-align: justify; display: inline-block; margin-top: 10px;"></div>
						</div>
						<div>
							<select  name="cboCuota" required="required"
								class="form-control">
								<option value=0 selected="true" disabled="disabled">seleccionar cuotas</option>
								<!-- El value indicado es el porcentaje que se aplica respecto a la cantidad de cuotas seleccionadas -->
								<option value=5>6 cuotas -   5% interes</option>
								<option value=10>12 cuotas - 10% interes</option>
								<option value=20>18 cuotas - 20% interes</option>
								<option value=30>24 cuotas - 30% interes</option>
								<option value=40>48 cuotas - 40% interes</option>
								<option value=50>72 cuotas - 50% interes</option>
							</select>
						</div>
						<br>
						<div style="text-align: center; padding-top: 10px;">
							<%if(request.getAttribute("MontoValido")!= null){ %>
									<label style="color: green" ><%=request.getAttribute("MontoValido").toString()%></label>
										<%request.setAttribute("MontoValido",null);%>							
							<%} %>
						</div>

						<div
							style="width: 60%; text-align: justify; display: inline-block; margin-top: 10px;"></div>

						<div style="margin-left: 200px; margin-top: 10px;">
							<input class="btn btn-primary" value="Simular Prestamo"
								name="btnSimular" type="submit" CssClass="btn btn-primary mb-2"
								Style="text-align: center;">
						</div>
						<div style="margin-left: 200px; margin-top: 10px;">
						
						<%if(request.getAttribute("MontoCuotas")!= null){ %>
								<input class="btn btn-primary" value="Solicitar Prestamo"
								name="btnConfirmar" type="submit" CssClass="btn btn-primary mb-2"
								Style="text-align: center;">
								<%request.setAttribute("MontoCuotas",null);%>							
							<%} %>
						</div>
					</div>
				</form>
			</div>
			<div class="col-sm"></div>
		</div>
	</div>
	
	
	 <div class="modal fade bd-example-modal-sm" id="modalSolicitudOk" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel2">Solicitud confirmada</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                     El prestamo fue solicitado con exito, desde la ventana listar prestamos podra visualizar el estado.
                        </div>
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
    </script>

</body>
</html>