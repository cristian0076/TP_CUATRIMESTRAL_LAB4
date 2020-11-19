package presentacion.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuentas;
import entidad.CuentasPorUsuario;

import entidad.Usuarios;
import entidad.TipoDeCuentas;

import negocioImpl.CuentasNegImpl;
import negocioImpl.CuentasPorUsuarioNegImpl;
import negocioImpl.UsuariosNegImpl;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/ServletCuentas")
public class ServletCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletCuentas() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		
		if(request.getParameter("btnModalAgregar")!= null)
		{
			UsuariosNegImpl negocio = new UsuariosNegImpl();
			Usuarios usuarioCta = new Usuarios();
			
			usuarioCta = negocio.obtenerUno(Integer.parseInt(request.getParameter("idUsuario").toString()));
			System.out.println(usuarioCta);
		
			request.setAttribute("usuarioCta",usuarioCta);	
		}
		
		
		if(request.getParameter("btnAgregar")!= null)
		{
			CuentasNegImpl negocio = new CuentasNegImpl();

			Cuentas cuenta = new Cuentas();
			TipoDeCuentas tipoDeCuenta = new TipoDeCuentas();
			Usuarios usuario = new Usuarios();
			boolean filas = false;
			int error = 0;
			
			if(cuenta.getSaldo() < 0 ) {
				error = 1;
				String texto;
				texto = "Sólo se permiten saldos positivos.";
				request.setAttribute("Error",texto);
				request.setAttribute("insert",0);
			
			}else {
			error = 1;
			}

			if(request.getParameter("txtId").toString() != null && request.getParameter("txtId").toString() != "") {
				usuario.setIdUsuario(Integer.parseInt(request.getParameter("txtId")));
				cuenta.setUsuario(usuario);
			}else {
				error = 1;
			}
				
			if(request.getParameter("txtTipoCaja").toString() != null && request.getParameter("txtTipoCaja").toString() != "") {
				tipoDeCuenta.setIdTipodeCuenta(Integer.parseInt(request.getParameter("txtTipoCaja")));
				cuenta.setTipoDeCuenta(tipoDeCuenta);
			}else {
				error = 1;
			}			

			Calendar cal = Calendar.getInstance();
		    SimpleDateFormat simpleformat = new SimpleDateFormat("yyyy-MM-dd");
		    String fecha = simpleformat.format(cal.getTime());			    
		    
		    Date date;
			try {
				date = simpleformat.parse(fecha);
				cuenta.setFechaCreacion(date);
			} catch (ParseException e) {

				e.printStackTrace();
			}
			
			cuenta.setSaldo(10000);
			int numero = (int)(Math.random()*100000000+1);			
			
			cuenta.setCbu("0025-"+Integer.toString(numero));		
			
			filas = negocio.insertar(cuenta);
			
			if(filas == true) {
				request.setAttribute("insert",1);
			}else {
				request.setAttribute("insert",0);
			}	
		}

	if(request.getParameter("btnModalModificar")!=null)

	{
		CuentasNegImpl negocio = new CuentasNegImpl();
		Cuentas usuarioCtaEdit = new Cuentas();

		if (request.getParameter("idCuentaEdit").toString() != null
				&& request.getParameter("idCuentaEdit").toString() != "") {

			usuarioCtaEdit = negocio.obtenerCuenta(Integer.parseInt(request.getParameter("idCuentaEdit").toString()));

		}

		request.setAttribute("usuarioCtaEdit", usuarioCtaEdit);
	}

	if(request.getParameter("btnModificar")!=null)
	{
		CuentasNegImpl negocio = new CuentasNegImpl();

		Cuentas cuenta = new Cuentas();
		TipoDeCuentas tipoDeCuenta = new TipoDeCuentas();

		boolean filas = false;
		int error = 0;

		if (request.getParameter("txtIdCtaEdit").toString() != null
				&& request.getParameter("txtIdCtaEdit").toString() != "") {
			cuenta.setNroDeCuenta(Integer.parseInt(request.getParameter("txtIdCtaEdit")));
		} else {
			error = 1;
		}

		if (request.getParameter("txtSaldo").toString() != null && request.getParameter("txtSaldo").toString() != "") {
			cuenta.setSaldo(Integer.parseInt(request.getParameter("txtSaldo")));

			if (cuenta.getSaldo() < 0) {
				error = 1;
				String texto;
				texto = "Sólo se permiten saldos positivos.";
				request.setAttribute("Error", texto);
				request.setAttribute("insert", 0);
			}

		} else {
			error = 1;
		}

		if (request.getParameter("txtTipoCaja").toString() != null
				&& request.getParameter("txtTipoCaja").toString() != "") {
			tipoDeCuenta.setIdTipodeCuenta(Integer.parseInt(request.getParameter("txtTipoCaja")));
			cuenta.setTipoDeCuenta(tipoDeCuenta);
		} else {
			error = 1;
		}

		filas = negocio.modificar(cuenta);

		if (filas == true) {
			request.setAttribute("insert", 1);
		} else {
			request.setAttribute("insert", 0);
		}
	}

	if(request.getParameter("btnEliminar")!=null)
	{

		if (request.getParameter("idUsuario") != null) {
			request.setAttribute("delete", request.getParameter("idUsuario").toString());
			request.getSession().setAttribute("idborrar", request.getParameter("idUsuario").toString());
			RequestDispatcher rd = request.getRequestDispatcher("/ABMclientes.jsp");
			rd.forward(request, response);
		}
	}

	RequestDispatcher rd = request.getRequestDispatcher("/ABMCuentas.jsp");rd.forward(request,response);
}}
