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
import entidad.PrestamoPorCuota;
import entidad.Prestamos;
import entidad.Usuarios;
import negocio.PrestamosNeg;
import negocioImpl.CuentasNegImpl;
import negocioImpl.PrestamoPorCuotaNegImpl;
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
		
		//Cargar cuentas en solicitar prestamo select
		if(request.getParameter("Param")!=null) {
		int parametro = Integer.parseInt(request.getParameter("Param"));
		Usuarios u = new Usuarios();
		u= (Usuarios)request.getSession().getAttribute("Session_user");
			if(parametro == 1) {
				//Cargar cuentas en solicitar prestamo select
				ArrayList<Cuentas> listaC=new ArrayList<Cuentas>();
				CuentasNegImpl negocio = new CuentasNegImpl();
				listaC = (ArrayList<Cuentas>)negocio.ObtenerCuentas(u.getIdUsuario());
				request.setAttribute("listaC", listaC);
				RequestDispatcher rd = request.getRequestDispatcher("/SolicitarPrestamo.jsp");   
		        rd.forward(request, response);
			}
		
			if(parametro == 2) {
				//Cargar tabla en ListarPrestamos
				ArrayList<Prestamos> ListaPrestamos = new ArrayList <Prestamos>();
				PrestamosNegImpl negocioPrestamos = new PrestamosNegImpl();
				ListaPrestamos =  (ArrayList<Prestamos>) negocioPrestamos.ListarPrestamosxUsuario(u.getIdUsuario());
				request.setAttribute("AllPrestamos", ListaPrestamos);
				RequestDispatcher rd = request.getRequestDispatcher("/ListarPrestamos.jsp");   
		        rd.forward(request, response);
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int parametro = 0;
		if(request.getParameter("Param")!=null) {
			parametro = Integer.parseInt(request.getParameter("Param"));
		}
		if(parametro == 3 || parametro == 4) {
			if(parametro == 3) {
				ArrayList<PrestamoPorCuota> listaCuotas =new ArrayList<PrestamoPorCuota>();
				PrestamoPorCuotaNegImpl negocio = new PrestamoPorCuotaNegImpl();
				
				//Cargar tabla en ListarPrestamos
				Usuarios u = new Usuarios();
				u= (Usuarios)request.getSession().getAttribute("Session_user");
				ArrayList<Prestamos> ListaPrestamos = new ArrayList <Prestamos>();
				PrestamosNegImpl negocioPrestamos = new PrestamosNegImpl();
				ListaPrestamos =  (ArrayList<Prestamos>) negocioPrestamos.ListarPrestamosxUsuario(u.getIdUsuario());
				request.setAttribute("AllPrestamos", ListaPrestamos);

				if(request.getParameter("ValorCuota")!=null) {
					request.getSession().setAttribute("ValorCuota2",Float.parseFloat(request.getParameter("ValorCuota")));
				}
			
				if(request.getParameter("IDPRESTAMO")!= null){
					request.getSession().setAttribute("ID_PRESTAMO",request.getParameter("IDPRESTAMO"));
					int IdPrestamo = Integer.parseInt(request.getParameter("IDPRESTAMO").toString());
					listaCuotas = (ArrayList<PrestamoPorCuota>) negocio.ObtenerCuotas(IdPrestamo);
					request.setAttribute("listaCuotas",listaCuotas);
					RequestDispatcher rd = request.getRequestDispatcher("/ListarPrestamos.jsp");   
					rd.forward(request, response);
				}
			}else {
				
				ArrayList<Cuentas> listaC1=new ArrayList<Cuentas>();
				ArrayList<Cuentas> listaC=new ArrayList<Cuentas>();
				CuentasNegImpl negocio = new CuentasNegImpl();
				Usuarios u = new Usuarios();
				u= (Usuarios)request.getSession().getAttribute("Session_user");
				listaC1 = (ArrayList<Cuentas>)negocio.ObtenerCuentas(u.getIdUsuario());
				
				float valor_cuota = Float.parseFloat(request.getSession().getAttribute("ValorCuota2").toString());
				request.getSession().setAttribute("Numero_Cuota",request.getParameter("NUMEROCUOTA"));
				for (Cuentas cuentas : listaC1) {
					if(cuentas.getSaldo() > valor_cuota) {
						listaC.add(cuentas);
					}
				}
				
				request.setAttribute("listaC", listaC);
			
				
				request.setAttribute("Pagar",1);
				
				//Cargar tabla en ListarPrestamos
				ArrayList<Prestamos> ListaPrestamos = new ArrayList <Prestamos>();
				PrestamosNegImpl negocioPrestamos = new PrestamosNegImpl();
				ListaPrestamos =  (ArrayList<Prestamos>) negocioPrestamos.ListarPrestamosxUsuario(u.getIdUsuario());
				request.setAttribute("AllPrestamos", ListaPrestamos);
				
				RequestDispatcher rd = request.getRequestDispatcher("/ListarPrestamos.jsp");   
				rd.forward(request, response);
			}
		}
		else {
			if(parametro==5) {
				if(request.getParameter("btnPagar")!= null) {
				PrestamosNegImpl negocio = new PrestamosNegImpl();
				boolean estado = false;
				int nro_cuota = Integer.parseInt(request.getSession().getAttribute("Numero_Cuota").toString());
				int IDcuenta = Integer.parseInt(request.getParameter("cboCuenta"));
				int IDprestamo = Integer.parseInt(request.getSession().getAttribute("ID_PRESTAMO").toString());
				float saldo = Float.parseFloat(request.getSession().getAttribute("ValorCuota2").toString());
				
				try {
					negocio.Pagar_cuota(IDcuenta,IDprestamo,nro_cuota,saldo);
					request.setAttribute("SolicitudOk","El pago se efectuo con exito.");
				} catch (Exception e) {
					request.setAttribute("SolicitudOk","El pago tuvo un error reintente el pago.");
				}
				
				
				//Cargar tabla en ListarPrestamos
				Usuarios u = new Usuarios();
				u= (Usuarios)request.getSession().getAttribute("Session_user");
				ArrayList<Prestamos> ListaPrestamos = new ArrayList <Prestamos>();
				PrestamosNegImpl negocioPrestamos = new PrestamosNegImpl();
				ListaPrestamos =  (ArrayList<Prestamos>) negocioPrestamos.ListarPrestamosxUsuario(u.getIdUsuario());
				request.setAttribute("AllPrestamos", ListaPrestamos);
				RequestDispatcher rd = request.getRequestDispatcher("/ListarPrestamos.jsp");   
				rd.forward(request, response);
				}
				if(request.getParameter("btnCancelar")!= null) {
					//Cargar tabla en ListarPrestamos
					
					Usuarios u = new Usuarios();
					u= (Usuarios)request.getSession().getAttribute("Session_user");
					ArrayList<Prestamos> ListaPrestamos = new ArrayList <Prestamos>();
					PrestamosNegImpl negocioPrestamos = new PrestamosNegImpl();
					ListaPrestamos =  (ArrayList<Prestamos>) negocioPrestamos.ListarPrestamosxUsuario(u.getIdUsuario());
					request.setAttribute("AllPrestamos", ListaPrestamos);
					
					RequestDispatcher rd = request.getRequestDispatcher("/ListarPrestamos.jsp");   
					rd.forward(request, response);
				}
				
			}
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
