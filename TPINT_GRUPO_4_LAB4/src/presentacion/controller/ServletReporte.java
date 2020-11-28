package presentacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Reporte01;
import negocioImpl.Reporte01NegImpl;

/**
 * Servlet implementation class ServletReporte
 */
@WebServlet("/ServletReporte")
public class ServletReporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReporte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("btnInforme")!= null)
		{
			int mes= 0;
			if(request.getParameter("SelectMes").toString().equals("") != true)
			{
				mes= Integer.parseInt(request.getParameter("SelectMes").toString());
				ArrayList<Reporte01> list = new ArrayList<Reporte01>();
				ArrayList<Reporte01> list2 = new ArrayList<Reporte01>();
				Reporte01NegImpl negocio = new Reporte01NegImpl();
				
				list= (ArrayList<Reporte01>)negocio.listarinforme(mes);
				list2= (ArrayList<Reporte01>)negocio.listarinforme2(mes);
				
				request.setAttribute("Informe", list);
				request.setAttribute("Informe2", list2);
				RequestDispatcher rd = request.getRequestDispatcher("/PrincipalADM.jsp");   
		        rd.forward(request, response);	
				
			}
			
		}
		
	}

}
