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
import entidad.Usuarios;
import negocio.CuentasNeg;
import negocioImpl.CuentasNegImpl;
import negocioImpl.MovimientosNegImpl;

/**
 * Servlet implementation class ServletTransferencias
 */
@WebServlet("/ServletTransferencias")
public class ServletTransferencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTransferencias() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("Transferencia")!= null)
		{
			
			ArrayList<Cuentas> listaC=new ArrayList<Cuentas>();
			CuentasNegImpl negocio = new CuentasNegImpl();
			Usuarios u = (Usuarios)request.getSession().getAttribute("Session_user");
			listaC = (ArrayList<Cuentas>)negocio.ObtenerCuentas(u.getIdUsuario());
			
			request.setAttribute("CuentasCliente", listaC);
			
			if(request.getParameter("Transferencia").toString().equals("1"))
			{
			RequestDispatcher rd = request.getRequestDispatcher("/TransferenciasCCPropia.jsp");   
	        rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("/TransferenciasCCTerceros.jsp");   
		        rd.forward(request, response);
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("Transferencia")!= null)
		{
		  	int error = 0;
			float importe = 0;
			int cuentaorigen= 0;
			int cuentadestino= 0;
			int usuarioorigen= 0;
			int usuariodestino= 0;
			int tipomovimiento= 4;
			String detalle = null;
			Cuentas c = new Cuentas();
			Cuentas c2 = new Cuentas();
			CuentasNegImpl cuentasNeg= new CuentasNegImpl();
			MovimientosNegImpl transferencia= new MovimientosNegImpl();
			String mensaje;
			String DirTransferencia="";
			
			if(request.getParameter("CuentaOrigen").toString().equals("") == false)
			{
			cuentaorigen =Integer.parseInt(request.getParameter("CuentaOrigen").toString());				
			
			}else {
				error= 1;
			}
			
			if(request.getParameter("txtDetalle").toString().length() > 0)
			{
			detalle =request.getParameter("txtDetalle").toString();				
			}else {
				error= 1;
			}
			
			if(request.getParameter("txtImporte").toString().length()> 0 && Float.parseFloat(request.getParameter("txtImporte").toString()) > importe)
			{
				importe = Float.parseFloat(request.getParameter("txtImporte").toString());
				
			}else {
				error =1;
			}
			
			if(request.getParameter("Transferencia").toString().equals("2")==true)
			{
				if(request.getParameter("CbuDestino")!= null && request.getParameter("CbuDestino").toString().length() > 0 )
				{
				System.out.print(request.getParameter("CbuDestino").toString());
				c2= cuentasNeg.obtenerCuenta((request.getParameter("CbuDestino").toString()));
					if(c2.getCbu() == null)
					{
						mensaje = "No se pudo realizar la transferencia, el CBU ingresado no se corresponde con ninguna cuenta registrada en el Banco";
						request.setAttribute("MensajeTransferencias", mensaje);	
						RequestDispatcher rd = request.getRequestDispatcher(DirTransferencia);   
						rd.forward(request, response);
						return;
					}else {}
				
				}else {
				error= 1;
				}
				  DirTransferencia = "/TransferenciasCCTerceros.jsp";
			}
			else
			{
				if(request.getParameter("CuentaDestino").toString().equals("") == false && request.getParameter("CuentaDestino").toString().equals(request.getParameter("CuentaOrigen").toString()) == false )
					{
					c2= cuentasNeg.obtenerCuenta(Integer.parseInt(request.getParameter("CuentaDestino").toString()));
					}else {
						error= 1;
					}
				  
				  DirTransferencia = "/TransferenciasCCPropia.jsp";
			}
			
			

			
			
			
			if(error != 1)
			{
				c = cuentasNeg.obtenerCuenta(cuentaorigen);
				usuarioorigen = c.getUsuario().getIdUsuario();
				usuariodestino = c2.getUsuario().getIdUsuario();
				cuentadestino = c2.getNroDeCuenta();
				
				
				if(importe > c.getSaldo())
				 {
					mensaje = "No se pudo realizar la transferencia, no posee Saldo Disponible en su cuenta Origen";
					request.setAttribute("MensajeTransferencias", mensaje);	
					RequestDispatcher rd = request.getRequestDispatcher(DirTransferencia);   
			        rd.forward(request, response);
			        return;
				 
				 }
				
				if(request.getParameter("Transferencia").toString().equals("2")==true && c2.getUsuario().getIdUsuario() == c.getUsuario().getIdUsuario())
				{ 
					mensaje = "No se pudo realizar la transferencia, el CBU destino debe pertenecer a una cuenta de Terceros";
					request.setAttribute("MensajeTransferencias", mensaje );	
					RequestDispatcher rd = request.getRequestDispatcher(DirTransferencia);   
			        rd.forward(request, response);
			        return;
				}	
				
				if(transferencia.Transferencias(importe, cuentaorigen, cuentadestino, usuarioorigen, usuariodestino, detalle,tipomovimiento)!= false)
				{
						mensaje = "Transferencia Exitosa";
						request.setAttribute("MensajeTransferencias", mensaje);	
					
					}else {
						mensaje = "Error al realizar la Transferencia, intente mas tarde....";
						request.setAttribute("MensajeTransferencias", mensaje);	
					}
				
				
		
			}else {
				 mensaje = "No se pudo realizar la transferencia, verificar los campos ingresados";
				 request.setAttribute("MensajeTransferencias", mensaje);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(DirTransferencia);   
	        rd.forward(request, response);			
		}
	}
}
