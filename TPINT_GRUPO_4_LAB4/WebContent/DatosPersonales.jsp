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
<title>Datos Personales</title>
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
<div class="container-fluid">
<br>
<div class="card">
  <h4 class="card-header">Mi Perfil</h4>
  <div class="card-body">
    <h5 class="card-title"><%= u.getNombre() %></h5>
    <p class="card-text">DNI: <%=u.getDni() %></p>
    <p class="card-text">CUIL: <%=u.getCuil() %></p>
    <p class="card-text">Nacionalidad: Argentina</p>
   </div>
</div>
<div class="card">
  <h4 class="card-header">Domicilio</h4>
  <div class="card-body">
    <p class="card-text">Direccion:  <%=u.getDireccion() %></p>
    <p class="card-text">Provincia: Buenos Aires</p>
   </div>
</div>
<div class="card">
  <h4 class="card-header">Contacto</h4>
  <div class="card-body">
    <p class="card-text">Nro Telefono/Celular: <%= u.getNumeroDeTelefono() %></p>
    <p class="card-text">Email: <%= u.getEmail() %></p> 
    
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