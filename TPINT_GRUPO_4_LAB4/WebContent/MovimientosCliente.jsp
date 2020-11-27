<%@page import="entidad.TiposDeMovimientos"%>
<%@page import="entidad.Movimientos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Usuarios"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>
   <style type="text/css">
  	<jsp:include page="/Estilos/PrincipalADM.css"></jsp:include>
  </style>

 <script type="text/javascript">
 $(document).ready( function () {
 $('#table_id').DataTable();
 } );
 </script>  
  <title>Movimientos Cliente</title>
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

<div class="footer-siempre-abajo" style="background-color:white">

<% 
	ArrayList<Movimientos> movimientos = null;
	if(request.getAttribute("listaMovimientos")!=null)
		movimientos = (ArrayList<Movimientos>)request.getAttribute("listaMovimientos");
%>



<div class="container mt-3">
  <h2>Movimientos</h2>
  <p>Aqui podrá visualizar todos los movimientos generados en su cuenta.</p> 
<%
if(request.getAttribute("NroDeCuentaM")!=null && request.getAttribute("TipoCuentaM")!=null)
{
%>
  <h5><%=request.getAttribute("TipoCuentaM")%> $ - <%=request.getAttribute("NroDeCuentaM")%></h5> 
<%}else{
%>
   <h5></h5> 
<%}
%>
<br>

<form method="post" action="ServletMovimientosCliente" onsubmit="return ValidarFechas()">
  <div class="form-row">
    <div class="col">
	 <h6>Tipo de Movimiento</h6>
	 
	<%
	ArrayList<TiposDeMovimientos> tm= null;
	if(request.getAttribute("tiposdeMovimiento")!=null)
		tm= (ArrayList<TiposDeMovimientos>)request.getAttribute("tiposdeMovimiento"); 	
	%>
	 
	  <select  class="form-control" aria-labelledby="dropdownMenuButton" name="SelectTipodeMovimiento">
	    <option class="dropdown-item" value="">-- Todos -- </option>  
      
      <%if(tm!=null) 
      		for(TiposDeMovimientos lista : tm)
      		{
      %>
      	<option class="dropdown-item" value="<%=lista.getIdTipoMovimiento()%>"><%= lista.getDescripcionTipoDeMovimiento() %></option>
      <% 
      		}
      %>
      </select>
     
     
    </div>
    <div class="col">
    <h6>Fecha Desde:</h6>
      <input type="date" class="form-control" required   id="fechadesde" name="fechadesde" >
    </div>
    <div class="col">
     <h6>Fecha Hasta:</h6>
      <input type="date" class="form-control" required id="fechahasta" name="fechahasta" >
    </div>
     <div class="col">
     <h6>&nbsp</h6>
     	<input  type="submit" value="Aplicar Filtros" class="btn btn-dark" name="btnFiltros">	
    </div>
 
  </div>
  
  <%
if(request.getAttribute("NroDeCuentaM")!=null && request.getAttribute("TipoCuentaM")!=null)
{
%>
  <input type="hidden" name="NroDeCuenta" value="<%=request.getAttribute("NroDeCuentaM")%>">
   <input type="hidden" name="TipoCuenta" value="<%=request.getAttribute("TipoCuentaM")%>">
<%}
  
%> 
  
  
</form>
<br>

  <table id="table_id" class="table table-bordered">
    <thead>
      <tr>
        <th>Tipo de Movimiento</th>
        <th>Fecha</th>  
        <th>Detalle/Concepto</th> 
        <th>Importe</th>
        
      </tr>
    </thead>
    <tbody id="myTable">
<%
	if(movimientos!=null)
		for(Movimientos m : movimientos)
		{

%>

      <tr>
        <td><%=m.getTipoDeMovimiento().getDescripcionTipoDeMovimiento()%></td>
        <td><%=m.getFecha()%></td>
        <td><%=m.getDetalle()%></td>
        <td><%=m.getImporte()%></td>
      </tr>
<%
}%>



    </tbody>
  </table> 

</div>


</div>


<script type="text/javascript"> 
function ValidarFechas()
{
var check = true;
var fechadesde = document.getElementById("fechadesde").value;
var fechahasta = document.getElementById("fechahasta").value;


check = confirm("Desea continuar ? ") ;
if( check != false)
{	
if(fechadesde > fechahasta)
	{
	alert("La fecha hasta debe ser mayor o igual a la fecha desde");
	check = false;
	}

}

return check;

}
</script>

<footer id="sticky-footer" class="py-4 bg-dark text-white-50">
        <div class="container text-center">
            <span class="logo-text">© 2020 - By Grupo Nro 4 LAB4  - todos los derechos reservados </span>
        </div>
</footer>


</body>
</html>