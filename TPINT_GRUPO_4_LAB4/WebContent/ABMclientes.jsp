<%@page import = "entidad.Usuarios" %>
<%@page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>ABM clientes</title>
<link rel="shortcut icon" href="https://i.ibb.co/wcwqp98/banco.png">

<!--Bootstrap-->
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
<!--Estilos propios-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/Estilos/AMBclientes.css" />



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
    

    <h1 style="text-align: center; padding-top: 50px;" class="font-italic text">Administración de clientes</h1><br>
     			
     			<%!int filas = 2; %>
     			
     			         <%! String texto; %>
                  		 
                  		 <% 
                  		 if(request.getAttribute("Error")!= null){
                  			texto = request.getAttribute("Error").toString(); 
                  		 }
                  		 %>
                    
                    <%
                	if(request.getAttribute("insert")!=null)
                	{
                		filas = Integer.parseInt(request.getAttribute("insert").toString());
                }else{%>
                    <h1 class="modal-title" id="staticBackdropLabel3" style="color:green;"></h1>
                <%}%>
                    
                    <%if(filas == 1)
                    { %>
                    	<h1 id="1" style="text-align: center; color:green; padding-top: 50px;" class="font-italic text">Usuario agregado con exito.</h1>
                    	<%filas = 2; %>
                  <%} else{ if(filas == 0){%>
                  		<h3 id="1" style="text-align: center; color:red; padding-top: 50px;" class="font-italic text"><%= "Error al agregar usuario."+" "+texto %></h3>
                  		 <script>alert(<%=texto%>)</script> 
                  		 <%filas = 2; %>
                  <%}} %>
                  
                  <% if(filas == 2){ %>

                  	<h1 id="1" style="text-align: center; color:red; padding-top: 50px;" class="font-italic text"></h1>
                  <%}%>
                  
                  
                  <%
                  	if(request.getAttribute("delete")!=null){%>
                  
 				
    			<script type="text/javascript">
 				$(function(){
  					$('#modalEliminar').modal();
 				});
				</script>
                  <%}%>
                  
                  
                   <%
                  	if(request.getAttribute("modificar")!=null){%>
                  
 				
    			<script type="text/javascript">
 				$(function(){
  					$('#ModalModificaciones').modal();
 				});
				</script>
                  <%}%>
                  
           
                  
                  
    <div class="container-fluid">
    <div class="footer-siempre-abajo" style="background-color:white">
        <div class="row">
            <div class="col-12" style="padding: 10px; text-align: center;">
            <form method="post" action="ServletUsuarios" >
                <div class="input-group" style="text-align: center;">
                	<input type="text"   class="form-control" name="txtUsuario" Style="margin: 5px;" placeholder="Usuario">
                    <span class="input-group-addon"></span>
                    <input type="text"   class="form-control" name="txtEmail" Style="margin: 5px;" placeholder="Email">
                </div>
                <div class="input-group" style="text-align: center;">
                 	<input type="text"  class="form-control" name="txtDni" onkeypress="javascript:return solonumeros(event)" Style="margin: 5px; width: 100px;" placeholder="Dni">
                    <input type="text"   class="form-control" name="txtCuil" onkeypress="javascript:return solonumeros(event)" Style="margin: 5px; width: 100px;" placeholder="Cuil">
                </div>
                <div class="col text-center">
                 
               	 <input class="btn btn-primary"  name="btnBuscar" type="submit" value="Buscar" CssClass="btn btn-primary mb-2" Style="text-align: center; width: 100px;">
                 <input class="btn btn-primary"  name="btnTodos" type="submit" value="Todos" CssClass="btn btn-primary mb-2" Style="text-align: center; width: 100px;">
                </div>
                </form>
            </div>
        </div>
        <div class="row" style="overflow:auto; height:400px;">
            <div class="col-md-1 col-sm-4"></div>
            <div class="col-md-10 col-sm-4">

<table id="table_id" class="table table-dark" style="overflow:auto; height:400px;">
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
    </tr>
  </thead>
  <tbody>
  
  <% 
	ArrayList<Usuarios> ListaUsuarios = null;
	if(request.getAttribute("AllUsers")!=null)
	{
		ListaUsuarios = (ArrayList<Usuarios>)request.getAttribute("AllUsers");
	}else{
		if(request.getAttribute("BusquedaCu")!= null){
			ListaUsuarios = (ArrayList<Usuarios>)request.getAttribute("BusquedaCu");
			System.out.println(ListaUsuarios);
		}
	}			
  %>
	
	<%  if(ListaUsuarios!=null)
		for(Usuarios user : ListaUsuarios) 
		{
	%>
	
    <tr style="color:black">
     <%if(user.getEstado() == true){ %>
      <form  method="post" action="ServletUsuarios">
      <td><input   class="btn btn-primary" type="submit" name= "btnEliminar" Onclick="abrir()"  value="Eliminar" ></td>
      <td><input   class="btn btn-primary" type="submit" name= "btnModificar" Onclick="Abrir_Modificar()"  value="Modificar" ></td>
      <th scope="row"><%=user.getIdUsuario()%> <input type="hidden" name="idUsuario" value="<%=user.getIdUsuario() %>" ></th>
      <td><%=user.getNombre()%></td>
      <td><%=user.getApellido()%></td>
      <td><%=user.getEmail()%></td>
      <td><%=user.getDni()%></td>
      <td><%=user.getCuil()%></td>
      </form>
      <%} %>
    </tr>
   <%  } %>
  </tbody>
</table>
            </div>
            <div class="col-md-1 col-sm-4"></div>
        </div>
        <div class="row">
            <div class="col-sm-12 col-md-4">
                
            </div>
            <div class="col-sm-12 col-md-4" style="text-align: center; padding: 3px;">
            	<input class="btn btn-primary" type="submit" name= "btnAgregar" id="btnAgregar"  Onclick="Agregar()" value="Agregar cliente" >
                <!--  
                 <asp:Button runat="server" class="btn-block btn btn-outline-primary" ID="btnAgregarProdu"  Text="Agregar Producto" OnClick="btnAgregarProdu_Click" />
                 <asp:Button runat="server" class="btn-block btn btn-outline-primary" ID="btnAgregarCateg" Onclick="btnAgregarCateg_Click" Text="Agregar categoria"  />
                 <asp:Button runat="server" class="btn-block btn btn-outline-primary" ID="btnModificarCateg"  Text="Modificar categoria" OnClick="btnModificarCateg_Click"  />
                 -->
            </div>
            <div class="col-sm-12 col-md-4">
                
            </div>
        </div>
        </div> <!-- Footer siempre abajo -->
    </div>

     
   
<div class="modal fade bd-example-modal-lg" id="ModalModificaciones" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
        <form  method="post" action="ServletUsuarios">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Modificar cliente</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                    	<%if(request.getSession().getAttribute("UserMod")!= null){ %>
                    	<%! Usuarios u = new Usuarios(); %>
                    	<% u = (Usuarios)request.getSession().getAttribute("UserMod");  %>
                        <h5>Dni</h5>
                        <input type="text" value =<%=u.getDni() %>   class="form-control" onkeypress="javascript:return solonumeros(event)"  name="DNIM" required="required" Style="margin: 5px;" placeholder="Dni">
                        <h5>Cuil</h5>
                        <input type="text" value =<%=u.getCuil() %>  class="form-control" onkeypress="javascript:return solonumeros(event)"  name="CUILM" required="required"  Style="margin: 5px;" placeholder="Cuil">
                        <h5>Nombre</h5>
                        <input type="text"  value =<%=u.getNombre() %>    class="form-control" name="NOMBREM" Style="margin: 5px;" required="required" placeholder="Nombre">
                        <h5>Apellido</h5>
                        <input type="text" value =<%=u.getApellido() %>  class="form-control" name="APELLIDOM" Style="margin: 5px;" required="required" placeholder="Apellido">
                        <h5>Fecha de nacimiento</h5>
                        <input type="date" value =<%=u.getFechaNacimiento() %> class="form-control" name="FECHAM"  min="1930-01-01" max="2002-01-01" class="form-control bg-white border-left-0 border-md" required="required" Style="margin: 5px;">
                        <h5>Email</h5>
                        <input type="text" value =<%=u.getEmail() %>  class="form-control" name="EMAILM" Style="margin: 5px;" required="required" placeholder="Email">
                         <h5>Contraseña</h5>
                        <input type="password"  value =<%=u.getContraseña() %>   class="form-control" name="CLAVEM" Style="margin: 5px;" required="required" placeholder="Contraseña">
                        <h5>Genero</h5>
                        <select  name="GENEROM" class="form-control">
                        	<option selected="true" disabled="disabled">seleccionar</option>
                            <option value=2 >Femenino</option>
                            <option value=1 >Masculino</option>
                            <option value=3 >Sin definir</option>
                        </select>
                        <h5>Telefono</h5>
                        <input type="text" value =<%=u.getNumeroDeTelefono() %>   class="form-control" name="TELEFONOM" Style="margin: 5px;" onkeypress="javascript:return solonumeros(event)" required="required" placeholder="Telefono">
                        <h5>Direccion</h5>
                        <input type="text" value ="<%=u.getDireccion() %>"  class="form-control" name="DIRECCIONM" Style="margin: 5px;" required="required" >
                         <% System.out.println(u); %>
                        <%}%>
                    </div>
                </div>
                <div class="modal-footer">
                	<input  class="btn btn-primary col text-center" type="submit" value= "Modificar" name="btnMod">
                </div>
            </div>
            </form>
        </div>
    </div>

  
     <div class="modal fade bd-example-modal-lg" id="ModalAgregar" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
        <form method= "post" action="ServletUsuarios">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel3">Agregar cliente</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <h5>Dni</h5>
                        <input type="text"   class="form-control" onkeypress="javascript:return solonumeros(event)"  name="txtDNIA" required="required" Style="margin: 5px;" placeholder="Dni">
                        <h5>Cuil</h5>
                        <input type="text"  class="form-control" onkeypress="javascript:return solonumeros(event)"  name="txtCUILA" required="required"  Style="margin: 5px;" placeholder="Cuil">
                        <h5>Nombre</h5>
                        <input type="text"   class="form-control" name="txtNombreA" Style="margin: 5px;" required="required" placeholder="Nombre">
                        <h5>Apellido</h5>
                        <input type="text"   class="form-control" name="txtApellidoA" Style="margin: 5px;" required="required" placeholder="Apellido">
                         <h5>Fecha de nacimiento</h5>
                         <input type="date"   class="form-control" name="txtFechaNacA"  min="1930-01-01" max="2002-01-01" class="form-control bg-white border-left-0 border-md" required="required" Style="margin: 5px;">
                        <h5>Email</h5>
                        <input type="text"   class="form-control" name="txtEmailA" Style="margin: 5px;" required="required" placeholder="Email">
                         <h5>Nombre de usuario</h5>
                        <input type="text"   class="form-control" name="txtUsuarioA" Style="margin: 5px;" required="required" placeholder="Usuario">
                         <h5>Contraseña</h5>
                        <input type="password"   class="form-control" name="txtContraseniaA" Style="margin: 5px;" required="required" placeholder="Contraseña">
                        <h5>Genero</h5>
                        <select  name="txtGenero" class="form-control">
                         	<option selected="true" disabled="disabled">seleccionar</option>
                            <option value=2 >Femenino</option>
                            <option value=1 >Masculino</option>
                            <option value=3 >Sin definir</option>
                        </select>
                        <h5>Telefono</h5>
                        <input type="text"   class="form-control" name="txtTelefonoA" Style="margin: 5px;" onkeypress="javascript:return solonumeros(event)" required="required" placeholder="Telefono">
                        <h5>Direccion</h5>
                        <input type="text"   class="form-control" name="txtDireccionA" Style="margin: 5px;" required="required" placeholder="Direccion">
                    </div>
                </div>
                <div class="modal-footer">
                    <input class="btn btn-primary col text-center" type="submit" value= "Agregar" name="btnAgregar">
                </div>
                </div>
            </div>
            </form>
        </div>
    

 
    <div class="modal fade bd-example-modal-sm" id="modalEliminar" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel2">Eliminar cliente</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                     ¿Esta seguro de eliminar el cliente seleccionado?
                        </div>
                </div>
                <div class="modal-footer">
            		<form  method="post" action="ServletUsuarios">
                    <input  class="btn btn-primary col text-center" type="submit" value= "Confirmar" name="btnEliminarE">
                    </form>
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
             $('#modalEliminar').modal('show');
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
            <span class="logo-text">© 2020 - By Grupo Nro 4 LAB4  - todos los derechos reservados </span>
        </div>
    </footer>
    
</body>
</html>