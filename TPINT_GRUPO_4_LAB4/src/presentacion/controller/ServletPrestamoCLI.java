package presentacion.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuentas;
import entidad.EstadosDePrestamo;
import entidad.Prestamos;
import entidad.Usuarios;
import negocioImpl.CuentasNegImpl;
import negocioImpl.PrestamosNegImpl;

/**
 * Servlet implementation class ServletPrestamoCLI
 */
@WebServlet("/ServletPrestamoCLI")
public class ServletPrestamoCLI extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ServletPrestamoCLI() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Cuentas> listaC=new ArrayList<Cuentas>();
		CuentasNegImpl negocio = new CuentasNegImpl();
		Usuarios u = new Usuarios();
		u= (Usuarios)request.getSession().getAttribute("Session_user");
		listaC = (ArrayList<Cuentas>)negocio.ObtenerCuentas(u.getIdUsuario());
		
		request.setAttribute("listaC", listaC);

	
		RequestDispatcher rd = request.getRequestDispatcher("/SolicitarPrestamo.jsp");   
        rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Cuentas> listaC=new ArrayList<Cuentas>();
		CuentasNegImpl negocio = new CuentasNegImpl();
		Usuarios u = new Usuarios();
		u= (Usuarios)request.getSession().getAttribute("Session_user");
		listaC = (ArrayList<Cuentas>)negocio.ObtenerCuentas(u.getIdUsuario());
		
		request.setAttribute("listaC", listaC);
		
		if(request.getParameter("btnSimular")!= null) {
		if(request.getParameter("txtMonto")!= null) {
			long monto;
			try {
				monto =  Long.parseLong(request.getParameter("txtMonto").toString());
				} catch (Exception e) {
					monto = 0;
				}
			
			if(monto > 100000 || monto < 1000) {
				String texto = "El monto debe ser mayor a $1000 y menor a $1000000";
				request.setAttribute("MontoInvalido",texto);
				RequestDispatcher rd = request.getRequestDispatcher("/SolicitarPrestamo.jsp");   
		        rd.forward(request, response);
			}else{
				try {

				if((Integer.parseInt(request.getParameter("cboCuenta"))!=0) && (Integer.parseInt(request.getParameter("cboCuota"))!=0)) {
					
					float monto_interes = calcular_cuota(Integer.parseInt(request.getParameter("cboCuota").toString()),Long.parseLong(request.getParameter("txtMonto").toString()));
					
					int cant_cuotas = cuota(Integer.parseInt(request.getParameter("cboCuota").toString()));
					
					float cuotas = monto_interes/cant_cuotas;
					
					int cuenta = Integer.parseInt(request.getParameter("cboCuenta"));
					
					String texto = "Monto solicitado: "+ monto+" - "+"Monto con interes: "+ monto_interes+" - "+cant_cuotas+" cuotas de $"+cuotas ;
					request.setAttribute("MontoValido",texto);
					
					request.setAttribute("MontoPedido",monto);
					request.setAttribute("MontoInteres",monto_interes);
					request.setAttribute("CantidadCuotas",cant_cuotas);
					request.setAttribute("MontoCuotas",cuotas);
					request.setAttribute("IDcuenta",cuenta);
					
					EstadosDePrestamo e = new EstadosDePrestamo();
					Prestamos p = new Prestamos();
					e.setIdEstado(1);
					p.setEstadoPrestamo(e);
					p.setImporteConIntereses(Float.parseFloat(request.getAttribute("MontoInteres").toString()));
					p.setImporteSolicitado(Float.parseFloat(request.getAttribute("MontoPedido").toString()));
					p.setPlazoDePago(Integer.parseInt(request.getAttribute("CantidadCuotas").toString()));
					p.setValorCuotaMensual(Float.parseFloat(request.getAttribute("MontoCuotas").toString()));
					Cuentas c = new Cuentas();
					c.setNroDeCuenta(Integer.parseInt(request.getAttribute("IDcuenta").toString()));
					p.setCuenta(c);
					p.setUsuario(u);
					request.getSession().setAttribute("PrestamoOBJ",p);
					RequestDispatcher rd = request.getRequestDispatcher("/SolicitarPrestamo.jsp");   
			        rd.forward(request, response);
				}else {
					String texto = "Verifique haber seleccionado cuenta y cuotas.";
					request.setAttribute("MontoInvalido",texto);
					RequestDispatcher rd = request.getRequestDispatcher("/SolicitarPrestamo.jsp");   
			        rd.forward(request, response);
				}
				} catch (Exception e) {
					String texto = "Verifique haber seleccionado cuenta y cuotas.";
					request.setAttribute("MontoInvalido",texto);
					RequestDispatcher rd = request.getRequestDispatcher("/SolicitarPrestamo.jsp");   
			        rd.forward(request, response);
				}
			}
		}
	}
		if(request.getParameter("btnConfirmar")!= null) {
			
			PrestamosNegImpl n = new PrestamosNegImpl();
			boolean filas = false;
			Prestamos p = new Prestamos();
			p = (Prestamos)request.getSession().getAttribute("PrestamoOBJ");
			filas= n.insertar(p);
			request.setAttribute("SolicitudOk",1);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/SolicitarPrestamo.jsp");   
        rd.forward(request, response);
	}
	
	public float calcular_cuota (int interes, long monto) {
		
	       float monto_interes =  monto+((monto*interes)/100);
		
		return monto_interes;
	}
	
	public int cuota(int interes) {
		
		int cuotas;
		switch (interes) {
		
		case 5:
			cuotas = 6;
			break;
			
		case 10:
			cuotas = 12;
			break;
		case 20:
			cuotas = 18;
			break;
		case 30:
			cuotas = 24;
			break;
		case 40:
			cuotas = 48;
			break;
		case 50:
			cuotas = 72;
			break;

		default:
			cuotas = 0;
			break;
		}
		
		return cuotas;
	}

}
