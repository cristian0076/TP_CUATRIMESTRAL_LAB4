package presentacion.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.EstadosDePrestamo;
import entidad.Prestamos;
import entidad.PrestamosPorUsuario;
import entidad.TipoDeCuentas;
import entidad.Usuarios;
import negocioImpl.PrestamosNegImpl;
import negocioImpl.PrestamosPorUsuarioNegImpl;
import negocioImpl.UsuariosNegImpl;

/**
 * Servlet implementation class ServletPrestamoCLI
 */
@WebServlet("/ServletPrestamoAdm")
public class ServletPrestamoAdm extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ServletPrestamoAdm() {
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
			
			ArrayList<PrestamosPorUsuario> listaCu= new ArrayList<PrestamosPorUsuario>();
			PrestamosPorUsuarioNegImpl negocio = new PrestamosPorUsuarioNegImpl();
			listaCu = (ArrayList<PrestamosPorUsuario>)negocio.FiltroPrestamosPorUsuario(nombreUsuario,Email,DNI,Cuil);
			
			request.setAttribute("BusquedaCu", listaCu);
			RequestDispatcher rd = request.getRequestDispatcher("/AutorizarPrestamos.jsp");   
	        rd.forward(request, response);
	        return;
		}

		if(request.getParameter("btnListar")!= null)
		{

			ArrayList<Prestamos> listaC=new ArrayList<Prestamos>();
			PrestamosNegImpl negocio = new PrestamosNegImpl();
			listaC = (ArrayList<Prestamos>)negocio.ObtenerPrestamos(Integer.parseInt(request.getParameter("idUsuario").toString()));
			
			System.out.println("listac " +  listaC);
			
			request.setAttribute("listaC", listaC);
			
			RequestDispatcher rd = request.getRequestDispatcher("/AutorizarPrestamos.jsp");   
	        rd.forward(request, response);		
		}

		
		
	if(request.getParameter("btnModalModificar")!=null)

	{
		PrestamosNegImpl negocio = new PrestamosNegImpl();
		Prestamos prestamoCtaEdit = new Prestamos();

		if (request.getParameter("idPrestamoEdit").toString() != null
				&& request.getParameter("idPrestamoEdit").toString() != "") {

			prestamoCtaEdit = negocio.obtenerPrestamo(Integer.parseInt(request.getParameter("idPrestamoEdit").toString()));

		}
		System.out.println("prestamoCtaEdit " +  prestamoCtaEdit);
		request.setAttribute("prestamoCtaEdit", prestamoCtaEdit);
	}

	if(request.getParameter("btnModificar")!=null)
	{
		PrestamosNegImpl negocio = new PrestamosNegImpl();
		String mensaje;
		Prestamos Prestamo = new Prestamos();
		EstadosDePrestamo estadoPrestamo = new EstadosDePrestamo();

		boolean filas = false;
		int error = 0;

		if (request.getParameter("txtIdPrestamoEdit").toString() != null
				&& request.getParameter("txtIdPrestamoEdit").toString() != "") {
			Prestamo.setIdPrestamo(Integer.parseInt(request.getParameter("txtIdPrestamoEdit")));
		} else {
			error = 1;
		}
		if (request.getParameter("txtEstadoPrestamo").toString() != null
				&& request.getParameter("txtEstadoPrestamo").toString() != "") {
			estadoPrestamo.setIdEstado(Integer.parseInt(request.getParameter("txtEstadoPrestamo")));
			Prestamo.setEstadoPrestamo(estadoPrestamo);
		} else {
			error = 1;
		}
		System.out.println("Prestamo.getEstadoPrestamo() " +  Prestamo.getEstadoPrestamo());

		filas = negocio.modificar(Prestamo);
		System.out.println("filasfilas " +  filas);
		if (filas == true) {
			mensaje = "Autorizacion finalizada con Exito.";
			request.setAttribute("MensajeAutorizacion", mensaje);	
			request.setAttribute("insert", 1);

		} else {
			mensaje = "Hubo un Problema con el servicio al querer autorizar el prestamo.";
			request.setAttribute("MensajeAutorizacion", mensaje);
			request.setAttribute("insert", 0);

		}
	}


	RequestDispatcher rd = request.getRequestDispatcher("/AutorizarPrestamos.jsp");rd.forward(request,response);
}}
