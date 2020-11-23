<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8">
<title>Insert title here</title>
   <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
   <style type="text/css">
  	<jsp:include page="/Estilos/PrincipalADM.css"></jsp:include>
  </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light  bg-dark text-white-50">
       <a class="navbar-brand" style="color: white" href="#">Home Bank</a>
       <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
          <ul class="navbar-nav mr-auto mt-2 mt-lg-0">


      	     <li class="nav-item active">
                   <a class="nav-link" href="CuentasCli.jsp" style="color:white">Cuentas <span class="sr-only">(current)</span></a>
             </li>

              <li class="nav-item dropdown">
        		<a class="nav-link dropdown-toggle" style="color: white" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          		Prestamos
        		</a>
        		<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          		<a class="dropdown-item" href="ListarPrestamos.jsp">Mis Prestamos</a>
         		 <a class="dropdown-item" href="SolicitarPrestamo.jsp">Solicitar Prestamo</a>
        		</div>
      		  </li>

          </ul>
       </div>
  
       <span id="perfil" class="navbar-text" style="padding: 10px">
       		<label id="Usuario">Usuario Activo</label>
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
  <h2>Solicitud de Préstamo</h2>
  <p>Sujeto a aprobación crediticia.</p>  
 
  <br>
  </div>
<div class="dropdown" style="margin-left: 200px;">
    		<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">
      			Seleccione Cuenta donde se acreditará el préstamo
    		</button>
    		<div class="dropdown-menu">
      			<a class="dropdown-item" href="#">Cuenta 1</a>
      			<a class="dropdown-item" href="#">Cuenta 2</a>
    		</div>
  		 </div>
<div style="margin-left: 200px; margin-top: 10px;"><label>Monto a solicitar</label></div>
<div>
<div style="float: left; width: 40%; text-align: justify; margin-left: 200px;">
	<input class="form-control" id="myInput" type="text" placeholder="">
</div>
<div style="width: 60%; text-align: justify; display: inline-block; margin-top: 10px;"></div>
</div>
<div style="margin-left: 200px;"><label>Cantidad de cuotas</label></div>
<div style="float: left; width: 40%; text-align: justify; margin-left: 200px;">
	<input class="form-control" id="myInput" type="text" placeholder="">
</div>
<div style="width: 60%; text-align: justify; display: inline-block; margin-top: 10px;"></div>

<div style="margin-left: 200px;"><label>Valor de la cuota mensual</label></div>
<div style="float: left; width: 40%; text-align: justify; margin-left: 200px;">
	<input class="form-control" id="myInput" type="text" placeholder="">
</div>
<div style="width: 60%; text-align: justify; display: inline-block; margin-top: 10px;"></div>

<div style="margin-left: 200px;margin-top: 10px;"><button type="button" class="btn btn-secondary" >Solicitar Préstamo</button></div>
 



 
</body>
</html>