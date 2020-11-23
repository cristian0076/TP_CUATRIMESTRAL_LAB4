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
import entidad.Movimientos;
import negocioImpl.MovimientosNegImpl;

/**
 * Servlet implementation class ServletMovimientosCliente
 */
@WebServlet("/ServletMovimientosCliente")
public class ServletMovimientosCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ServletMovimientosCliente() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("NroDeCuenta")!=null)
		{
			ArrayList<Movimientos> movimientos = new ArrayList<Movimientos>();
			MovimientosNegImpl negocio = new MovimientosNegImpl();
			
			
						
			movimientos = negocio.ListarMovimientos(Integer.parseInt(request.getParameter("NroDeCuenta")));
			request.setAttribute("listaMovimientos",movimientos);
			request.setAttribute("NroDeCuentaM",Integer.parseInt(request.getParameter("NroDeCuenta")));
			request.setAttribute("TipoCuentaM", request.getParameter("TipoCuenta").toString());
			
			RequestDispatcher rd = request.getRequestDispatcher("/MovimientosCliente.jsp");   
	        rd.forward(request, response);	
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
