<%@page import="entidad.Reporte01"%>
<%@page import="java.util.ArrayList"%>
<%@page import = "entidad.Usuarios" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Administracion</title>
<link rel="shortcut icon" href="https://i.ibb.co/wcwqp98/banco.png">

<!--Bootstrap-->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	
<!--Estilos propios-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/Estilos/PrincipalADM.css" />
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
</head>
<body>


<nav
		class="navbar navbar-expand-lg navbar-light  bg-dark text-white-50">

		<a class="navbar-brand" style="color: white" href="#">Home Bank</a>


		<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active"><a class="nav-link"
					href="PrincipalADM.jsp" style="color: white">Inicio <span
						class="sr-only">(current)</span></a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" style="color: white" href="#"
					id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> Administrar
						clientes </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="ABMclientes.jsp">ABM Clientes</a> <a
							class="dropdown-item" href="ABMCuentas.jsp">Apertura de cuentas</a>
							<a class="dropdown-item" href="AutorizarPrestamos.jsp">Autorizar Prestamos</a>
					</div></li>

			</ul>
		</div>
		<%! Usuarios u = new Usuarios(); %>

		<span id="perfil" class="navbar-text" style="padding: 10px">
		<%
			if(request.getSession().getAttribute("Session_user") != null){
				u= (Usuarios)request.getSession().getAttribute("Session_user");
         	   	System.out.println(u.getApellido()); 
			}
         	   %>
         	   <%if(u.getApellido() != null){ %>
      		 <label><%=u.getNombre()+" "+u.getApellido() %></label>
      		 <%} %>
		 <a
			href="DatosPersonalesADM.jsp"> <img src="https://i.ibb.co/Xzbf1pS/usuario.png" />
		</a> Perfil
		</span> <span id="salir" class="navbar-text"> <a href="Login.jsp">
				<img src="https://i.ibb.co/TcV6LW4/salir-arriba-a-la-derecha.png" />
		</a> Salir
		</span>
	</nav>

<div class="container mt-3">
    <div class="footer-siempre-abajo" style="background-color:white">
  <h2>Reportes</h2>
  <p>podremos generar el reporte buscando por los filtros solicitados y se generara un archivo PDF. </p>  

 <form method="post" action="ServletReporte" >
 <select class= "form-control" required aria-labelledby="dropdownMenuButton" name="SelectMes">
	    <option class="dropdown-item" value="">-- Seleccionar Mes -- </option>  
		<option class="dropdown-item" value="1">Enero </option>
        <option class="dropdown-item" value="2">Febrero </option>
 		<option class="dropdown-item" value="3">Marzo </option>
 		<option class="dropdown-item" value="4">Abril</option>
 		<option class="dropdown-item" value="5">Mayo</option>
 		<option class="dropdown-item" value="6">Junio</option>
 		<option class="dropdown-item" value="7">Julio</option>
 		<option class="dropdown-item" value="8">Agosto</option>
 		<option class="dropdown-item" value="9">Septiembre</option>
 		<option class="dropdown-item" value="10">Octubre</option>
 		<option class="dropdown-item" value="11">Noviembre</option>
 		<option class="dropdown-item" value="12">Diciembre</option>
 </select>
 <br>
	<input  type="submit" value="Aplicar Filtro" class="btn btn-dark" name="btnInforme">
</form> 
  <br>

<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
<br>
<br>
<div id="container2" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
 </div>
</div>


<%

ArrayList<Reporte01> lista= null;
ArrayList<Reporte01> lista2= null;

if(request.getAttribute("Informe")!=null)
{
	lista = (ArrayList<Reporte01>)request.getAttribute("Informe");
}

if(request.getAttribute("Informe2")!=null)
{
	lista2 = (ArrayList<Reporte01>)request.getAttribute("Informe2");
}


%>




<script type="text/javascript">
$(function () {
	$('#container').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Saldo final por dia - Caja de Ahorro'
        },
        subtitle: {
            text: 'Administracion Banco'
        },
        xAxis: {
            categories: [
            	<%
            	
            	if(lista!=null)
            		for(Reporte01 r : lista)
            		{
            	%>
             	'<%=r.getDia() %>',          
                <%}
                %>
                ]
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Pesos $'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} $</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Caja de Ahorro',
            data: [
            	
				<%         	
            	if(lista!=null)
            		for(Reporte01 r : lista)
            		{
            	%>
            	<%=r.getImporte()%>,          
                <%}%>
                ]
      }]
    });
});
</script>


<script type="text/javascript">
$(function () {
	$('#container2').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Saldo final por dia - Cuenta Corriente'
        },
        subtitle: {
            text: 'Administracion Banco'
        },
        xAxis: {
            categories: [
            	<%
            	
            	if(lista2!=null)
            		for(Reporte01 r : lista2)
            		{
            	%>
             	'<%=r.getDia() %>',          
                <%}
                %>
                ]
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Pesos $'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} $</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Cuenta Corriente',
            data: [
            	
				<%         	
            	if(lista2!=null)
            		for(Reporte01 r : lista2)
            		{
            	%>
            	<%=r.getImporte()%>,          
                <%}%>
                ]
      }]
    });
});
</script>


	<footer id="sticky-footer" class="py-4 bg-dark text-white-50">
		<div class="container text-center">
			<span class="logo-text">© 2020 - By Grupo Nro 4 LAB4 - todos
				los derechos reservados </span>
		</div>
	</footer>


</body>
</html>
