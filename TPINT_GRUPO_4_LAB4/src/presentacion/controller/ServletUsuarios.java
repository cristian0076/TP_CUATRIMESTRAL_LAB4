package presentacion.controller;
import java.io.IOException;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.DateFormat;
import java.text.ParseException;
import entidad.Usuarios;
import negocioImpl.CuentasPorUsuarioNegImpl;
import negocioImpl.UsuariosNegImpl;
import entidad.CuentasPorUsuario;
import entidad.Generos;
import entidad.TiposDeUsuarios;

@WebServlet("/ServletUsuarios")
public class ServletUsuarios extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ServletUsuarios() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnTodos")!= null) {
			ArrayList<Usuarios> listaUsers = new ArrayList<>();
			UsuariosNegImpl negocio = new UsuariosNegImpl();
			listaUsers =  negocio.obtenerTodos();
			request.setAttribute("AllUsers",listaUsers);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ABMclientes.jsp");   
	        rd.forward(request, response);
		}
		
if(request.getParameter("btnAgregar")!=null) {
			
			UsuariosNegImpl negocio = new UsuariosNegImpl();
			Usuarios user = new Usuarios();
			Generos g = new Generos();
			TiposDeUsuarios t = new TiposDeUsuarios();
			int error = 0;
			boolean filas = false;
			
			if(request.getParameter("txtDNIA").toString() != null && request.getParameter("txtDNIA").toString() != "") {
				user.setDni(request.getParameter("txtDNIA").toString());
				
				if(user.getDni().length() > 8 ) {
					error = 1;
					String texto;
					texto = "El tamaño del dni supera la cifra maxima.";
					request.setAttribute("insert",0);
					request.setAttribute("Error",texto);
				}
				
			}else {
				error = 1;
			}
			
			if(request.getParameter("txtCUILA").toString() != null && request.getParameter("txtCUILA").toString() != "") {
				user.setCuil(request.getParameter("txtCUILA").toString());
				
				if(user.getCuil().length() > 12 ) {
					error = 1;
					String texto;
					texto = "El tamaño del cuil supera la cifra maxima.";
					request.setAttribute("Error",texto);
					request.setAttribute("insert",0);
				}
				
			}else {
				error = 1;
			}
			
			if(request.getParameter("txtNombreA").toString() != null && request.getParameter("txtNombreA").toString() != "") {
				user.setNombre(request.getParameter("txtNombreA").toString());
				
				if(user.getNombre().length() > 50 ) {
					error = 1;
					String texto;
					texto = "El nombre debe contener menos de 50 caracteres.";
					request.setAttribute("Error",texto);
					request.setAttribute("insert",0);
				}
				
			}else {
				error = 1;
			}
			
			if(request.getParameter("txtApellidoA").toString() != null && request.getParameter("txtApellidoA").toString() != "") {
				user.setApellido(request.getParameter("txtApellidoA").toString());
				
				if(user.getApellido().length() > 50 ) {
					error = 1;
					String texto;
					texto = "El apellido debe contener menos de 50 caracteres.";
					request.setAttribute("Error",texto);
					request.setAttribute("insert",0);
				}
			}else {
				error = 1;
			}
			
			if(request.getParameter("txtFechaNacA").toString() != null && request.getParameter("txtFechaNacA").toString() != "") {
				SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");

				Date date;
				try {
					date = d.parse(request.getParameter("txtFechaNacA").toString());
					user.setFechaNacimiento(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}else {
				error = 1;
			}
			
			try {	
			
			if(request.getParameter("txtEmailA").toString() != null && request.getParameter("txtEmailA").toString() != "") {
				user.setEmail(request.getParameter("txtEmailA").toString());
				
				// Excepción propia en Cliente //				
				Usuarios.validaMail(request.getParameter("txtEmailA").toString());
				
				if(user.getEmail().length() > 50 ) {
					error = 1;
					String texto;
					texto = "El e-mail debe contener menos de 50 caracteres.";
					request.setAttribute("Error",texto);
					request.setAttribute("insert",0);
				}
			}else {
				error = 1;
			}
			
			} catch (Exception e) {
				error = 1;
				String texto;
				texto = e.getMessage();
				request.setAttribute("Error", texto);
			}
			if(request.getParameter("txtUsuarioA").toString() != null && request.getParameter("txtUsuarioA").toString() != "") {
				user.setNombreUsuario(request.getParameter("txtUsuarioA").toString());
				if(user.getNombreUsuario().length() > 50 || user.getNombreUsuario().length() < 8 ) {
					error = 1;
					String texto;
					texto = "El  nombre de usuario debe contener entre 8 y 50 caracteres.";
					request.setAttribute("Error",texto);
					request.setAttribute("insert",0);
				}
			}else {
				error = 1;
			}
			
			if(request.getParameter("txtGenero").toString() != null && request.getParameter("txtGenero").toString() != "") {
				g.setIdGenero(Integer.parseInt(request.getParameter("txtGenero")));
				user.setGenero(g);
			}else {
				error = 1;
			}
			
			if(request.getParameter("txtTelefonoA").toString() != null && request.getParameter("txtTelefonoA").toString() != "") {
				user.setNumeroDeTelefono(request.getParameter("txtTelefonoA").toString());
				if(user.getNumeroDeTelefono().length() != 10 ) {
					error = 1;
					String texto;
					texto = "El telefono debe contener 10 numeros.";
					request.setAttribute("Error",texto);
					request.setAttribute("insert",0);
				}
			}else {
				error = 1;
			}
			
			if(request.getParameter("txtDireccionA").toString() != null && request.getParameter("txtDireccionA").toString() != "") {
				user.setDireccion(request.getParameter("txtDireccionA").toString());
			}else {
				error = 1;
			}
			
			if(request.getParameter("txtContraseniaA").toString() != null && request.getParameter("txtContraseniaA").toString() != "") {
				user.setContraseña(request.getParameter("txtContraseniaA").toString());
				if(user.getContraseña().length() > 50 || user.getContraseña().length() < 8 ) {
					error = 1;
					String texto;
					texto = "La contraseña debe contener entre 8 y 50 caracteres.";
					request.setAttribute("Error",texto);
					request.setAttribute("insert",0);
				}
			}else {
				error = 1;
			}

			t.setIdTipoDeUsuario(1);
			
			user.setEstado(true);
			
			user.setTipoDeUsuario(t);
			
			if(error == 1) {
				request.setAttribute("insert",0);
			}
			else {
				try {
					filas = negocio.insertar(user);
				} catch (Exception e) {
					System.out.println(e.toString());
				}
				
			}
				
		
			
			
			if(filas == true) {
				request.setAttribute("insert",1);
			}else {
				request.setAttribute("insert",0);
			}
			
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/ABMclientes.jsp");   
	        rd.forward(request, response);
		}

		if(request.getParameter("btnEliminar")!=null) {
			
			if(request.getParameter("idUsuario")!=null) {
				request.setAttribute("delete",request.getParameter("idUsuario").toString());
				request.getSession().setAttribute("idborrar",request.getParameter("idUsuario").toString());
				RequestDispatcher rd = request.getRequestDispatcher("/ABMclientes.jsp");
		        rd.forward(request, response);
			}
			
		}
		
		
		if(request.getParameter("btnEliminarE")!=null) {
				int id = Integer.parseInt(request.getSession().getAttribute("idborrar").toString());
				UsuariosNegImpl negocio = new UsuariosNegImpl();
				negocio.cambiar_estado(id);
				RequestDispatcher rd = request.getRequestDispatcher("/ABMclientes.jsp");
		        rd.forward(request, response);
		}
		
		if(request.getParameter("btnModificar")!= null) {
			request.setAttribute("modificar",request.getParameter("idUsuario").toString());
			UsuariosNegImpl negocio = new UsuariosNegImpl();
			Usuarios u = new Usuarios();
			u = negocio.obtenerUno(Integer.parseInt(request.getParameter("idUsuario").toString()));
			request.getSession().setAttribute("UserMod",u);
			request.getSession().setAttribute("idmodificar",request.getParameter("idUsuario").toString());
			RequestDispatcher rd = request.getRequestDispatcher("/ABMclientes.jsp");
	        rd.forward(request, response);
		}
		
		if(request.getParameter("btnMod")!= null) {
			int id = Integer.parseInt(request.getSession().getAttribute("idmodificar").toString());
			UsuariosNegImpl negocio = new UsuariosNegImpl();
			Usuarios user = new Usuarios();
			user.setIdUsuario(id);
			
			int error = 0;
			boolean filas = false;
			
			if(request.getParameter("DNIM").toString() != null && request.getParameter("DNIM").toString() != "") {
				user.setDni(request.getParameter("DNIM").toString());
				
				if(user.getDni().length() > 8 ) {
					error = 1;
					String texto;
					texto = "El tamaño del dni supera la cifra maxima.";
					request.setAttribute("Editar",0);
					request.setAttribute("Error2",texto);
				}
				
			}else {
				error = 1;
			}
			
			if(request.getParameter("CUILM").toString() != null && request.getParameter("CUILM").toString() != "") {
				user.setCuil(request.getParameter("CUILM").toString());
				
				if(user.getCuil().length() > 11 ) {
					error = 1;
					String texto;
					texto = "El tamaño del cuil supera la cifra maxima.";
					request.setAttribute("Error2",texto);
					request.setAttribute("Editar",0);
				}
				
			}else {
				error = 1;
			}
			
			if(request.getParameter("NOMBREM").toString() != null && request.getParameter("NOMBREM").toString() != "") {
				user.setNombre(request.getParameter("NOMBREM").toString());
				
				if(user.getNombre().length() > 50 ) {
					error = 1;
					String texto;
					texto = "El nombre debe contener menos de 50 caracteres.";
					request.setAttribute("Error2",texto);
					request.setAttribute("Editar",0);
				}
				
			}else {
				error = 1;
			}
			
			if(request.getParameter("APELLIDOM").toString() != null && request.getParameter("APELLIDOM").toString() != "") {
				user.setApellido(request.getParameter("APELLIDOM").toString());
				
				if(user.getApellido().length() > 50 ) {
					error = 1;
					String texto;
					texto = "El apellido debe contener menos de 50 caracteres.";
					request.setAttribute("Error2",texto);
					request.setAttribute("Editar",0);
				}
			}else {
				error = 1;
			}
			
			if(request.getParameter("FECHAM").toString() != null && request.getParameter("FECHAM").toString() != "") {
				SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");

				Date date;
				try {
					date = d.parse(request.getParameter("FECHAM").toString());
					user.setFechaNacimiento(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}else {
				error = 1;
			}
			
			if(request.getParameter("EMAILM").toString() != null && request.getParameter("EMAILM").toString() != "") {
				user.setEmail(request.getParameter("EMAILM").toString());
				if(user.getEmail().length() > 50 ) {
					error = 1;
					String texto;
					texto = "El e-mail debe contener menos de 50 caracteres.";
					request.setAttribute("Error2",texto);
					request.setAttribute("Editar",0);
				}
			}else {
				error = 1;
			}
			
			Generos g = new Generos();
			try {
				
			
			if(request.getParameter("GENEROM") != null) {
				g.setIdGenero(Integer.parseInt(request.getParameter("GENEROM")));
				user.setGenero(g);
			}else {
				if(request.getSession().getAttribute("UserMod")!= null) {
					Usuarios u = new Usuarios();
					u = (Usuarios)request.getSession().getAttribute("UserMod");
					g.setIdGenero(u.getGenero().getIdGenero());
					user.setGenero(g);
				}else {
					error = 1;
				}
				
			}
			
			} catch (Exception e) {
				error = 1;
			}
			
			if(request.getParameter("TELEFONOM").toString() != null && request.getParameter("TELEFONOM").toString() != "") {
				user.setNumeroDeTelefono(request.getParameter("TELEFONOM").toString());
				if(user.getNumeroDeTelefono().length() != 10 ) {
					error = 1;
					String texto;
					texto = "El telefono debe contener 10 numeros.";
					request.setAttribute("Error2",texto);
					request.setAttribute("Editar",0);
				}
			}else {
				error = 1;
			}
			
			if(request.getParameter("DIRECCIONM").toString() != null && request.getParameter("DIRECCIONM").toString() != "") {
				user.setDireccion(request.getParameter("DIRECCIONM").toString());
			}else {
				error = 1;
			}
			
			if(request.getParameter("CLAVEM").toString() != null && request.getParameter("CLAVEM").toString() != "") {
				user.setContraseña(request.getParameter("CLAVEM").toString());
				if(user.getContraseña().length() > 50 || user.getContraseña().length() < 8 ) {
					error = 1;
					String texto;
					texto = "La contraseña debe contener entre 8 y 50 caracteres.";
					request.setAttribute("Error2",texto);
					request.setAttribute("Editar",0);
				}
			}else {
				error = 1;
			}

			
			if(error == 1) {
				request.setAttribute("Editar",0);
			}
			else {
				filas = negocio.editar(user);
			}
				
		
			
			
			if(filas == true) {
				request.setAttribute("Editar",1);
			}else {
				request.setAttribute("Editar",0);
			}
			
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/ABMclientes.jsp");   
	        rd.forward(request, response);
			
		}
		
		if(request.getParameter("btnBuscar")!= null) {
			String nombreUsuario = "";
			String Email = "";
			String DNI = "";
			String Cuil = "";
			
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
			
			ArrayList<Usuarios> listaUsers = new ArrayList<>();
			UsuariosNegImpl negocio = new UsuariosNegImpl();
			listaUsers =  negocio.ListarUsuariosFiltro(nombreUsuario, Email, DNI, Cuil);
			request.setAttribute("BusquedaCu",listaUsers);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ABMclientes.jsp");   
	        rd.forward(request, response);
		}
		
		
	}
}
