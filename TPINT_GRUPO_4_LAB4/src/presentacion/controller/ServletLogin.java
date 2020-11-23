package presentacion.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entidad.Usuarios;
import negocioImpl.UsuariosNegImpl;

import datosImpl.UsuariosDaoImpl;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ServletLogin() {
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
		

			
			
		
		//Ingresar
		if (request.getParameter("btnIngresar") != null) {
			// Entra por haber echo click en el hyperlink mostrar usuarios
			UsuariosDaoImpl UsuarioDao = new UsuariosDaoImpl();
			Usuarios usuario = UsuarioDao.obtenerUnoxUsuario(request.getParameter("txtUsuario"),request.getParameter("txtClave"));
			//System.out.println(usuario);;
			if (usuario.getIdUsuario() != 0) {
				
				request.getSession().setAttribute("Session_user",usuario);
				
				if (usuario.getTipoDeUsuario().getIdTipoDeUsuario() == 2) {
					RequestDispatcher rd = request.getRequestDispatcher("/PrincipalADM.jsp");
			        rd.forward(request, response);
			        return;
				} else if (usuario.getTipoDeUsuario().getIdTipoDeUsuario() != 2) {
					response.sendRedirect("ServletCuentasCliente?IdUsuario=1");
					return;
				}
			} else {	

				  int error=1;
				  request.setAttribute("UsuarioYaExiste", error);
				  request.getRequestDispatcher("Login.jsp").forward(request, response); 
				  return;
			}
		}
		
		
	}
}

