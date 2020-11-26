<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Cuentas"%>
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
   <style type="text/css">
  	<jsp:include page="/Estilos/PrincipalADM.css"></jsp:include>
  </style>
 <title>Transferencias de Cliente</title>
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


	<form action="ServletTransferencias?Transferencia=2" method="post">

	<div class="container mt-3">	
 		<h2>Transferir a Cuenta de Terceros</h2>
 	 	<p>Selecciona desde que cuenta queres enviar la plata</p> 
 		<h3>Origen</h3> 
		<div class="dropdown">
	  		<select class="btn btn-light dropdown-toggle" aria-labelledby="dropdownMenuButton" name="CuentaOrigen">
	    		<option class="dropdown-item" value="SinSeleccion">Seleccionar Cuenta</option>  
	  <% 
		ArrayList<Cuentas> cuentas = null;
		if(request.getAttribute("CuentasCliente")!=null)
		{
			cuentas = (ArrayList<Cuentas>)request.getAttribute("CuentasCliente");
		}

	
		%>
		  
		<% 	if(cuentas!=null)
			for(Cuentas cu : cuentas)
  			{
		
			if(cu.getCbu()!=null)
			{
		 %>	
	 	<option class="dropdown-item" value="<%=cu.getNroDeCuenta()%>"><%=cu.getTipoDeCuenta().getDescripcion() %> $ - <%=cu.getNroDeCuenta() %> - $ <%=cu.getSaldo() %></option>
		<%}
	}%> 
		</select>
	</div>
 	<br> 
  	<div class="input-group mb-3">
 		<input type="text" class="form-control" placeholder="Detalle / Concepto" name="txtDetalle" aria-label="Recipient's username" aria-describedby="basic-addon2">
	</div>
	<div class="input-group mb-3">
   		<input type="text" class="form-control" placeholder="Importe" name="txtImporte" aria-label="Recipient's username" aria-describedby="basic-addon2">
    </div>	
	
	
  		<br>
  		<h3>Destino</h3>
  		<h5>Ingrese el CBU Destino</h5> 
  		<input class="form-control" id="CbuDestino" type="text" placeholder="" name="CbuDestino">
  		<br>
  		<input  type="submit" value="Confirmar Transferencia" class="btn btn-dark" name="btnConfirmar">
	</div>
</form>
	
</div>

<% String mensaje = null;
if(request.getAttribute("MensajeTransferencias")!= null)
{
	
	mensaje = request.getAttribute("MensajeTransferencias").toString();

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
          <a href="ServletCuentasCliente?IdUsuario=1" class="btn btn-secondary" >Close</a>
       </div>
    </div>
  </div>
</div>


<footer id="sticky-footer" class="py-4 bg-dark text-white-50">
        <div class="container text-center">
            <span class="logo-text">© 2020 - By Grupo Nro 4 LAB4  - todos los derechos reservados </span>
        </div>
</footer>


</body>
</html>