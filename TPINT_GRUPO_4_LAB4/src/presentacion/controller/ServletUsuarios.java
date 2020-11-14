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
import negocioImpl.UsuariosNegImpl;
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
				
			}else {
				error = 1;
			}
			
			if(request.getParameter("txtCUILA").toString() != null && request.getParameter("txtCUILA").toString() != "") {
				user.setCuil(request.getParameter("txtCUILA").toString());
			}else {
				error = 1;
			}
			
			if(request.getParameter("txtNombreA").toString() != null && request.getParameter("txtNombreA").toString() != "") {
				user.setNombre(request.getParameter("txtNombreA").toString());
			}else {
				error = 1;
			}
			
			if(request.getParameter("txtApellidoA").toString() != null && request.getParameter("txtApellidoA").toString() != "") {
				user.setApellido(request.getParameter("txtApellidoA").toString());
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
			
			if(request.getParameter("txtEmailA").toString() != null && request.getParameter("txtEmailA").toString() != "") {
				user.setEmail(request.getParameter("txtEmailA").toString());
			}else {
				error = 1;
			}
			
			if(request.getParameter("txtUsuarioA").toString() != null && request.getParameter("txtUsuarioA").toString() != "") {
				user.setNombreUsuario(request.getParameter("txtUsuarioA").toString());
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
			}else {
				error = 1;
			}

			t.setIdTipoDeUsuario(1);
			
			user.setEstado(true);
			
			user.setTipoDeUsuario(t);
			
			
			filas = negocio.insertar(user);
			
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
			request.getSession().setAttribute("idmodificar",request.getParameter("idUsuario").toString());
			RequestDispatcher rd = request.getRequestDispatcher("/ABMclientes.jsp");
	        rd.forward(request, response);
		}
		
		if(request.getParameter("btnMod")!= null) {
			int id = Integer.parseInt(request.getSession().getAttribute("idmodificar").toString());
			UsuariosNegImpl negocio = new UsuariosNegImpl();
			Usuarios u = new Usuarios();
			u.setIdUsuario(id);
			if(request.getParameter("CLAVE")!= null) {
				u.setContraseña(request.getParameter("CLAVE").toString());
				negocio.editar_clave(u);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/ABMclientes.jsp");
	        rd.forward(request, response);
			
		}
		
		
	}
}
