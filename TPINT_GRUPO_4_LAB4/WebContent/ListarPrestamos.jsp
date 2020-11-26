<%@page import = "entidad.Prestamos" %>
<%@page import = "entidad.Usuarios" %>
<%@page import="java.util.ArrayList" %>

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
      <th scope="row"><%=p.getIdPrestamo()%> <input type="hidden" name="idUsuario" value="<%=p.getIdPrestamo() %>" ></th>
      <td style="text-align: center"><%=p.getFecha()%></td>
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
  <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#myModal">
  	Pagar cuotas
  </button>

  <!-- The Modal -->
  <div class="modal fade" id="myModal">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
         <div class="dropdown">
    		<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">
      			Débito de Cuenta
    		</button>
    		<div class="dropdown-menu">
      			<a class="dropdown-item" href="#">Cuenta 1</a>
      			<a class="dropdown-item" href="#">Cuenta 2</a>
    		</div>
  		 </div>
        </div>
        <div class="modal-header">
         <div class="dropdown">
    		<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">
      			Préstamo a pagar
    		</button>
    		<div class="dropdown-menu">
      			<a class="dropdown-item" href="#">Prestamo 1</a>
      			<a class="dropdown-item" href="#">Prestamo 2</a>
    		</div>
  		 </div>
        </div>
        <div class="modal-header">
         <div class="dropdown">
    		<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">
      			Cuotas
    		</button>
    		<div class="dropdown-menu">
      			<a class="dropdown-item" href="#"> 1 </a>
      			<a class="dropdown-item" href="#"> 2 </a>
      			<a class="dropdown-item" href="#"> 3 </a>
    		</div>
  		 </div>
        </div>
        <!-- Modal body -->
        <div class="modal-body"> Está pagando 2 cuotas del Préstamo 1. Total: 5000 
        </div>
        <div class="modal-body"> Se debitará de la Cuenta 1 
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" >Continuar</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
        </div>
        
      </div>
    </div>
</div>
</html>