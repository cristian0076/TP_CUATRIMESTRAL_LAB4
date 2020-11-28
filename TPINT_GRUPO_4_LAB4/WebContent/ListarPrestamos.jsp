<%@page import = "entidad.Prestamos" %>
<%@page import = "entidad.Usuarios" %>
<%@page import="java.util.ArrayList" %>
<%@page import="entidad.PrestamoPorCuota"%>
<%@page import="java.util.Calendar"%>
<%@page import="entidad.Cuentas"%>
<%@page import="entidad.Usuarios"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Prestamos</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
   <style type="text/css">
  	<jsp:include page="/Estilos/PrincipalADM.css"></jsp:include>
  </style>
  
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css">
  
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>


 <script type="text/javascript">
 $(document).ready( function () {
 $('#table_id').DataTable();
 } );
 </script>
  
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light  bg-dark text-white-50">
       <a class="navbar-brand" style="color: white" href="#">Home Bank</a>
       <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
          <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
 

      	     <li class="nav-item active">
                   <a class="nav-link" href="ServletCuentasCliente?IdUsuario=1" style="color:white">Cuentas <span class="sr-only">(current)</span></a>
             </li>

              <li class="nav-item dropdown">
        		<a class="nav-link dropdown-toggle" style="color: white" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          		Prestamos
        		</a>
        		<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          		<a class="dropdown-item" href="ServletPrestamoCLI?Param=2">Mis Prestamos</a>
         		 <a class="dropdown-item" href="ServletPrestamoCLI?Param=1">Solicitar Prestamo</a>
        		</div>
      		  </li>

          </ul>
       </div>
       
     
   			<%! Usuarios u = new Usuarios(); %>
       <span id="perfil" class="navbar-text" style="padding: 10px">
       			<%u= (Usuarios)request.getSession().getAttribute("Session_user");
         	   System.out.println(u.getApellido()); %>
         	   <%if(u.getApellido() != null){ %>
      		 <label><%=u.getNombre()+" "+u.getApellido() %></label>
      		 <%} %>
            <a href="DatosPersonales.jsp">
                <img
                    src="https://i.ibb.co/Xzbf1pS/usuario.png" />
            </a>
            Perfil
        </span>
         <span id="salir" class="navbar-text">
            <a href="Login.jsp">
                <img
                    src="https://i.ibb.co/TcV6LW4/salir-arriba-a-la-derecha.png" />
            </a>
           	Salir
        </span>
    </nav>
    
<div class="container mt-3">
  <h2>Mis Préstamos</h2>
  
  <br>
  <table id="table_id" class="table table-dark" style="overflow:auto; ">
  <thead>
    <tr>
      <th scope="col"></th>
      <th scope="col">Nro Prestamo</th>
      <th scope="col">Fecha</th>
      <th scope="col">Importe solicitado</th>
      <th scope="col">Importe a pagar</th>
      <th scope="col">Cantidad de cuotas</th>
      <th scope="col">Estado</th>
    </tr>
  </thead>
  <tbody>
  
  <% 
	ArrayList<Prestamos> ListaPrestamos = null;
  
	if(request.getAttribute("AllPrestamos")!=null)
	{
		ListaPrestamos = (ArrayList<Prestamos>)request.getAttribute("AllPrestamos");
	}		
  %>
	
	<%  if(ListaPrestamos!=null)
		for(Prestamos p : ListaPrestamos) 
		{
	%>
	
    <tr style="color:black">

      <form  method="post" action="ServletPrestamoCLI?Param=3">
      <td><input   class="btn btn-primary" type="submit" name= "btnVerCuotas" Onclick="abrir()"  value="Cuotas" ></td>
      <th scope="row"><%=p.getIdPrestamo()%> <input type="hidden" name="IDPRESTAMO" value="<%=p.getIdPrestamo() %>" ></th>
      <td style="text-align: center"><%=p.getFecha()%><input type="hidden" name="ValorCuota" value="<%=p.getValorCuotaMensual() %>" ></td>
      <td style="text-align: center"><%=p.getImporteSolicitado()%></td>
      <td style="text-align: center" ><%=p.getImporteConIntereses()%></td>
      <td style="text-align: center" ><%=p.getPlazoDePago()%></td>
      <td style="text-align: center" ><%=p.getEstadoPrestamo().getDescripcion()%></td>
      </form>
      
    </tr>
   <%  } %>
  </tbody>
</table>
  
  
  <a href="ServletPrestamoCLI?Param=1"><button type="button" class="btn btn-secondary" href="ServletPrestamoCLI?Param=2">Solicitar Nuevo Prestamo</button></a>

  
  
  
  
  <div class="modal fade bd-example-modal-sm" id="modalListar"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<form method="post" action="ServletPrestamoCLI?Param=4">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="staticBackdropLabel2">Lista de cuotas</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body" style="overflow:scroll;height:450px;">
						<table class="table table-dark " style="overflow:scroll;height:50px;">
							<thead>
								<tr>
									<th scope="col"></th>
									<th scope="col" style="text-align: center" >Nro cuota</th>
									<th scope="col" style="text-align: center" >Fecha</th>
									<th scope="col" style="text-align: center" >Estado de pago</th>
								</tr>
							</thead>

							<%
								if (request.getAttribute("listaCuotas") != null) {
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
								ArrayList<PrestamoPorCuota> Cuotas = null;
								if (request.getAttribute("listaCuotas") != null) {
									Cuotas = (ArrayList<PrestamoPorCuota>) request.getAttribute("listaCuotas");
								}
							%>


							<%
								if (Cuotas != null) {
									for (PrestamoPorCuota c : Cuotas) {
							%>
							<tbody>
								<tr>
									<form method="post" action="ServletPrestamoCLI?Param=4">
									
									<%! int mes_a;int anio_a;int mes;int anio;boolean flag=false;%>
									
									<%
							         Calendar cal = Calendar.getInstance();
							         cal.setTime(c.getFechaPago());
							         anio = cal.get(Calendar.YEAR);
							         mes = cal.get(Calendar.MONTH);
							         mes=mes+1;
							         
							        Calendar fecha = Calendar.getInstance();
							 		anio_a = fecha.get(Calendar.YEAR); 
							 		mes_a = fecha.get(Calendar.MONTH);  
							 		mes_a=mes_a+1;
							 		
							 		if(anio_a > anio){flag = true;}
							 		else{
							 			flag=false;
							 		}
							 		
							 		if(anio_a == anio){
							 			if(mes_a == mes){
							 				flag=true;
							 			}else{
							 				if(mes_a < mes){
							 					int dif;
							 					dif = mes-mes_a;
							 					if(dif == 1){
							 						flag=true;
							 					}else{
							 						flag=false;
							 					}
							 				}else{
							 					flag=true;
							 				}
							 			}
							 		}
							 
									%>
										
										<%if((c.getEstado() != true) && (flag==true)){ %>
										<td><input HeaderText="Pagar" class="btn btn-primary"
											type="submit" name="btnPagar" Onclick="AbrirPagar()"
											id="btnModalPagar" value="Pagar"></td>
										<%}else{%>
											<%if(flag == true){ %>
												<td><input HeaderText="Pagar" disabled = "true" class="btn btn-primary"
											type="submit" name="btnPagar" Onclick="AbrirPagar()"
											id="btnModalPagar" value="Pagado"></td>
											<%}else{ %>
												<td><input HeaderText="Pagar" disabled = "true" class="btn btn-primary"
												type="submit" name="btnPagar" Onclick="AbrirPagar()"
												id="btnModalPagar" value="Mes posterior"></td>
											<%} %>
										<%} %>
										<td style="text-align: center" ><%=c.getNroCuota() %><input type="hidden" name="NUMEROCUOTA" value="<%=c.getNroCuota() %>" ></td>
										<td style="text-align: center" ><%=c.getFechaPago()%></td>
										<td style="text-align: center" ><%=c.getEstado() %></td>
										<th scope="row" Style="text-align:center;"><input
											type="hidden" name="idCuentaEdit"
											value=""></th>
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
  
  
  		<%if(request.getAttribute("Pagar")!= null ){%>
  		<script type="text/javascript">
            $(function(){
            $('#ModalPagar').modal();
            });
            <%}%>
        </script>


		<%
			if(request.getAttribute("SolicitudOk") != null) {
				
		%>


		<script type="text/javascript">
			$(function() {
				$('#modalSolicitudOk').modal();
			});
		</script>


		<%
			
		%>
		<%
			}
		%>

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
                    <% if(request.getAttribute("SolicitudOk") != null) {%>
                     <h5><%=request.getAttribute("SolicitudOk").toString() %></h5>
                     <%
                     request.setAttribute("SolicitudOk", null);
                    } %>
                        </div>
                </div>
            </div>
        </div>
    </div>


		<!-- The Modal -->
   <div class="modal fade bd-example-modal-lg" id="ModalPagar" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
        <form method= "post" action="ServletPrestamoCLI?Param=5">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel3">Pagar prestamo</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                       
                        <h5>Seleccionar cuenta a debitar</h5><br>
                        <p style="Color:red;">Solo apareceran en el listado las cuentas que cuenten con el saldo para cubrir la cuota.</p><br>
                        <select name="cboCuenta" required="required" class="form-control">
								<option value=0 selected="true" disabled="disabled">seleccionar
									cuenta</option>
								<%  if(request.getAttribute("listaC")!=null)
							for(Cuentas cuenta : (ArrayList<Cuentas>)request.getAttribute("listaC")) 
							{%>
							   <%if(cuenta.getNroDeCuenta() != 0){ %>
								<option value="<%= cuenta.getNroDeCuenta() %>"><%= "Nro: "+cuenta.getNroDeCuenta()+" - "+ cuenta.getTipoDeCuenta().getDescripcion() %></option>
								<%} %>
								<%} %>
							</select>
                    </div>
                </div>
                <div class="modal-footer">
                    <input class="btn btn-primary col text-center" type="submit" value= "Pagar" name="btnPagar">
                     <input class="btn btn-primary col text-center" type="submit" value= "Cancelar" name="btnCancelar">
                </div>
                </div>
            </div>
            </form>
        </div>

	<script>
		function AbrirPagar() {
            $('#myModal').modal('show');
        }
	</script>
	
	<script>
		function Abrir() {
            $('#modalListar').modal('show');
        }
	</script>
   

</html>