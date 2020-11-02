<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>ABM clientes</title>
<link rel="shortcut icon" href="https://i.ibb.co/wcwqp98/banco.png">

<!--Bootstrap-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

<!--Estilos propios-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/Estilos/AMBclientes.css" />

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
    

    <h1 style="text-align: center; padding-top: 150px;" class="font-italic text">Administraci�n de cuentas</h1>
    <div class="container-fluid">
    <div class="footer-siempre-abajo" style="background-color:white">
        <div class="row">
            <div class="col-12" style="padding: 10px; text-align: center;">
                <div class="input-group" style="text-align: center;">
                	<input type="text" ID="txtNombre"  class="form-control" name="txtUsuario" Style="margin: 5px;" placeholder="Usuario">
                    <span class="input-group-addon"></span>
                    <input type="text" ID="txtMail"  class="form-control" name="txtEmail" Style="margin: 5px;" placeholder="Email">
                </div>
                <div class="input-group" style="text-align: center;">
                 	<input type="text" ID="txtDni"  class="form-control" name="txtDni" onkeypress="javascript:return solonumeros(event)" Style="margin: 5px; width: 100px;" placeholder="Dni">
                    <input type="text" ID="txtCuil"  class="form-control" name="txtCuil" onkeypress="javascript:return solonumeros(event)" Style="margin: 5px; width: 100px;" placeholder="Cuil">
                </div>
                <div class="col text-center">
                <input class="btn btn-primary"  name="btnBuscar" type="submit" value="Buscar" CssClass="btn btn-primary mb-2" Style="text-align: center; width: 100px;">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-1 col-sm-4"></div>
            <div class="col-md-10 col-sm-4">

			<table class="table table-dark">
			  <thead>
			    <tr>
			       <th scope="col"></th>
			       <th scope="col"></th>
			      <th scope="col">ID</th>
			      <th scope="col">Nombre</th>
			      <th scope="col">Apellido</th>
			      <th scope="col">Email</th>
			      <th scope="col">Dni</th>
			      <th scope="col">Cuil</th>
			      <th scope="col">Cant. Cuentas</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
			      <td><input HeaderText="Borrado"  class="btn btn-primary" type="submit" name= "btnListar" Onclick="abrir()" id="btnListar" value="Listar" ></td>
			      <td><input class="btn btn-primary" type="submit" name= "btnAgregar" id="btnAgregar" Onclick="Agregar()" value="Agregar" ></td>      <th scope="row">1</th>
			      <td>Martin</td>
			      <td>Arga�araz</td>
			      <td>MartinArg98@gmail.com</td>
			      <td>40558512</td>
			      <td>21405585120</td>
			      <td>1</td>
			    </tr>
			     <tr>
			      <td><input class="btn btn-primary" type="submit" name= "btnListar" id="btnListar" Onclick="abrir()" value="Listar" ></td>
			      <td><input class="btn btn-primary" type="submit" name= "btnAgregar" id="btnAgregar" Onclick="Agregar()" value="Agregar" ></td>      <th scope="row">2</th>
			      <td>Ana</td>
			      <td>Perez</td>
			      <td>Anaperez12@gmail.com</td>
			      <td>40558512</td>
			      <td>21405585120</td>
			      <td>0</td>
			    </tr>
			     <tr>
			      <td><input class="btn btn-primary" type="submit" name= "btnListar" id=btnListar  Onclick="abrir()" value="Listar" ></td>
			      <td><input class="btn btn-primary" type="submit" name= "btnAgregar" id="btnAgregar" Onclick="Agregar()" value="Agregar" ></td>
			      <th scope="row">3</th>
			      <td>Luciano</td>
			      <td>Souza</td>
			      <td>lu_souza76@gmail.com</td>
			      <td>40558512</td>
			      <td>21405585120</td>
			      <td>3</td>
			    </tr>
			  </tbody>
			</table>
            </div>
            <div class="col-md-1 col-sm-4"></div>
        </div>
        <div class="row">
            <div class="col-sm-12 col-md-4">
                
            </div>
          
        </div>
        </div> <!-- Footer siempre abajo -->
    </div>

     
   


     <div class="modal fade bd-example-modal-lg" id="ModalAgregar" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel3">Agregar cuenta</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <h5>Dni</h5>
                        <input type="text" ID="DNI"  class="form-control" onkeypress="javascript:return solonumeros(event)"  name="DNI" Style="margin: 5px;" placeholder="Dni">
                        <h5>Cuil</h5>
                        <input type="text" ID="CUIL" class="form-control" onkeypress="javascript:return solonumeros(event)"  name="CUIL" Style="margin: 5px;" placeholder="Cuil">
                        <h5>Nombre</h5>
                        <input type="text" ID="Nombre"  class="form-control" name="txtNombreA" Style="margin: 5px;" placeholder="Nombre">
                        <h5>Apellido</h5>
                        <input type="text" ID="Apellido"  class="form-control" name="txtApellidoA" Style="margin: 5px;" placeholder="Apellido">
				        <h5>Nombre de usuario</h5>
                        <input type="text" ID="txtUsuario"  class="form-control" name="txtUsuarioA" Style="margin: 5px;" placeholder="Usuario">
                        <h5>Tipo De Caja</h5>
                        <select id="txtTipoCaja" name="txtTipoCaja" class="form-control" required="required">
                            <option value="Femenino" selected="">Caja de ahorro</option>
                            <option value="Masculino">Cuenta corriente</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <input ID="btnAgregar" class="btn btn-primary col text-center" type="submit" value= "Agregar" name="btnAgregar">
                </div>
            </div>
        </div>
    </div>

 
    <div class="modal fade bd-example-modal-sm" id="modalListar" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel2">Listar Cuentas</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <table class="table table-dark">
				  <thead>
				    <tr>
				      <th scope="col">ID</th>
				      <th scope="col">Apellido</th>
				      <th scope="col">Cuil</th>
				      <th scope="col">Tipo Cuenta</th>
				      <th scope="col">Nro. Cuenta</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
					  <th scope="row">1</th>
				      <td>Arga�araz</td>
				      <td>40558512</td>
				      <td>Caja de ahorro</td>
				      <td>0340558512/4</td>
				    </tr>
				     <tr>
				     <th scope="row">2</th>
				      <td>Perez</td>
				      <td>40558512</td>
				      <td>Caja de ahorro</td>
				      <td>0240558512/4</td>
				    </tr>
				     <tr>
				      <th scope="row">3</th>
				      <td>Souza</td>
				      <td>40558512</td>
				      <td>Cuenta corriente</td>
				      <td>0140558512/4</td>
				    </tr>
				  </tbody>
				</table>
                </div>

            </div>
        </div>
    </div>

   
    <script>
        function Abrir_Modificar() {
            $('#ModalModificaciones').modal('show');
        }
    </script>
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

     <script> 
         function abrir() {
             $('#modalListar').modal('show');
         }
    </script>

    <script> 
        function Agregar() {
            $('#ModalAgregar').modal('show');
        }
    </script>
    
<!-- Footer -->

<footer id="sticky-footer" class="py-4 bg-dark text-white-50">
        <div class="container text-center">
            <span class="logo-text">� 2020 - By Grupo Nro 4 LAB4  - todos los derechos reservados </span>
        </div>
    </footer>
    
    <!-- bootstraps -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</body>
</html>