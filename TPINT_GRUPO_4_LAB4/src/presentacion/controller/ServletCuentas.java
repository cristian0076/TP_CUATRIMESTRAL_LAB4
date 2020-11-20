package presentacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuentas;
import entidad.CuentasPorUsuario;
import entidad.Usuarios;
//import entidad.Generos;
///import entidad.TipoDeCuentas;
//import entidad.TiposDeUsuarios;
//import entidad.Usuarios;
import negocioImpl.CuentasNegImpl;
import negocioImpl.CuentasPorUsuarioNegImpl;

@WebServlet("/ServletCuentas")
public class ServletCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ServletCuentas() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreUsuario = "";
		String Email = "";
		String DNI = "";
		String Cuil = "";
		
		if(request.getParameter("btnBuscar")!= null)
		{		  
			if(request.getParameter("txtUsuario")!= null)
			{
		       nombreUsuario = request.getParameter("txtUsuario").toString();
			}
			
			if(request.getParameter("txtEmail")!=null)
			{
				Email = request.getParameter("txtEmail").toString(); 
			}
			
			if(request.getParameter("txtDni")!=null)
			{
				DNI = request.getParameter("txtDni").toString(); 
			}
			
			if(request.getParameter("txtCuil")!=null)
			{
				Cuil = request.getParameter("txtCuil").toString(); 
			}
			
			ArrayList<CuentasPorUsuario> listaCu= new ArrayList<CuentasPorUsuario>();
			CuentasPorUsuarioNegImpl negocio = new CuentasPorUsuarioNegImpl();
			listaCu = (ArrayList<CuentasPorUsuario>)negocio.ObtenerFiltro(nombreUsuario,Email,DNI,Cuil);
			
			
			request.setAttribute("BusquedaCu", listaCu);
			RequestDispatcher rd = request.getRequestDispatcher("/ABMCuentas.jsp");   
	        rd.forward(request, response);
	        return;
		}

		
		if(request.getParameter("btnListar")!= null)
		{
			
			ArrayList<Cuentas> listaC=new ArrayList<Cuentas>();
			CuentasNegImpl negocio = new CuentasNegImpl();
			listaC = (ArrayList<Cuentas>)negocio.ObtenerCuentas(Integer.parseInt(request.getParameter("idUsuario").toString()));
			
			request.setAttribute("listaC", listaC);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/ABMCuentas.jsp");   
	        rd.forward(request, response);
			
		}
	
		RequestDispatcher rd = request.getRequestDispatcher("/ABMCuentas.jsp");   
        rd.forward(request, response);
	}

}
