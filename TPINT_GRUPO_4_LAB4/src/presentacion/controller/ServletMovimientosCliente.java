package presentacion.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuentas;
import entidad.Movimientos;
import entidad.TiposDeMovimientos;
import negocioImpl.MovimientosNegImpl;
import negocioImpl.TipodeMovimientosNegImpl;

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
			ArrayList<TiposDeMovimientos> listatm = new ArrayList<TiposDeMovimientos>();
			TipodeMovimientosNegImpl negocioTM= new TipodeMovimientosNegImpl();
			
			listatm = (ArrayList<TiposDeMovimientos>)negocioTM.listartiposDeMovimientos();
			movimientos = negocio.ListarMovimientos(Integer.parseInt(request.getParameter("NroDeCuenta")));
			request.setAttribute("listaMovimientos",movimientos);
			request.setAttribute("NroDeCuentaM",Integer.parseInt(request.getParameter("NroDeCuenta")));
			request.setAttribute("TipoCuentaM", request.getParameter("TipoCuenta").toString());
			request.setAttribute("tiposdeMovimiento", listatm);
			
			RequestDispatcher rd = request.getRequestDispatcher("/MovimientosCliente.jsp");   
	        rd.forward(request, response);	
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(request.getParameter("btnFiltros")!=null)
		{
			ArrayList<Movimientos> movimientos = new ArrayList<Movimientos>();
			MovimientosNegImpl negocio = new MovimientosNegImpl();
			ArrayList<TiposDeMovimientos> listatm = new ArrayList<TiposDeMovimientos>();
			TipodeMovimientosNegImpl negocioTM= new TipodeMovimientosNegImpl();
			int error = 0;
			int idTM = 0;
			Date fechadesde = null;
			Date fechahasta = null;
			int nrodecuenta = 0;
			
			SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
			
			if(request.getParameter("SelectTipodeMovimiento")!="")
				idTM = Integer.parseInt(request.getParameter("SelectTipodeMovimiento").toString());
			
			if(request.getParameter("fechadesde")!="")
			{	
			try {
					 fechadesde = d.parse(request.getParameter("fechadesde").toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
			error = 1;
			}
			
			if(request.getParameter("fechahasta")!="")
			{	
				
				try {
					 fechahasta = d.parse(request.getParameter("fechahasta").toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
			error = 1;
			}
			
			listatm = (ArrayList<TiposDeMovimientos>)negocioTM.listartiposDeMovimientos();
			
			if(error!=1) {
			
				nrodecuenta = Integer.parseInt(request.getParameter("NroDeCuenta"));
				movimientos = negocio.ListarMovimientos(idTM,fechadesde,fechahasta,nrodecuenta);
			}
				
			request.setAttribute("listaMovimientos",movimientos);
			request.setAttribute("NroDeCuentaM",Integer.parseInt(request.getParameter("NroDeCuenta")));
			request.setAttribute("TipoCuentaM", request.getParameter("TipoCuenta").toString());
			request.setAttribute("tiposdeMovimiento", listatm);
			
			RequestDispatcher rd = request.getRequestDispatcher("/MovimientosCliente.jsp");   
	        rd.forward(request, response);	
		}
		
	}
	

}
