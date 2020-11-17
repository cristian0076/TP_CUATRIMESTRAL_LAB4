<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <!--Bootstrap-->
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!--Estilos propios-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/Estilos/Reportes.css" />

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light  bg-dark text-white-50">

          <a class="navbar-brand" style="color: white" href="#">Home Bank</a>


        <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item active">
                    <a class="nav-link" href="PrincipalADM.jsp" style="color:white">Inicio <span class="sr-only">(current)</span></a>
                </li>
                 <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" style="color: white" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Administrar clientes
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="ABMclientes.jsp">ABM Clientes</a>
          <a class="dropdown-item" href="ABMCuentas.jsp">Apertura de cuentas</a>
        </div>
      </li>
      <li class="nav-item active">
                    <a class="nav-link" href="Reportes.jsp" style="color:white">Reportes <span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </div>
       <span id="perfil" class="navbar-text" style="padding: 10px">
            <a href="DatosPersonalesADM.jsp">
                <img
                    src="https://i.ibb.co/Xzbf1pS/usuario.png" />
            </a>
            Perfil
        </span>
         <span id= "salir" class="navbar-text">
            <a href="Login.jsp">
                <img
                    src="https://i.ibb.co/TcV6LW4/salir-arriba-a-la-derecha.png" />
            </a>
           	Salir
        </span>
    </nav>
    
<div class="container mt-3">
    <div class="footer-siempre-abajo" style="background-color:white">
  <h2>La idea de esta ventana es poder ver los reportes</h2>
  <p>podremos generar el reporte buscando por los filtros solicitados y se generara un archivo PDF. </p>  
  <input class="form-control" id="myInput" type="text" placeholder="">
  <br>
 </div>
</div>

<footer id="sticky-footer" class="py-4 bg-dark text-white-50">
        <div class="container text-center">
            <span class="logo-text">© 2020 - By Grupo Nro 4 LAB4  - todos los derechos reservados </span>
        </div>
 </footer>

</body>
</html>
