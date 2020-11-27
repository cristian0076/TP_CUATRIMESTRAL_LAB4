package presentacion.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuentas;
import entidad.CuentasPorUsuario;

import entidad.Usuarios;
import entidad.TipoDeCuentas;

import negocioImpl.CuentasNegImpl;
import negocioImpl.CuentasPorUsuarioNegImpl;
import negocioImpl.UsuariosNegImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import excepciones.SaldoNegativoException;

@WebServlet("/ServletCuentas")
public class ServletCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletCuentas() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombreUsuario = "";
		String Email = "";
		String DNI = "";
		String Cuil = "";

		
		// Búsqueda por filtros //
		
		if (request.getParameter("btnBuscar") != null) {
			if (request.getParameter("txtUsuario") != null) {
				nombreUsuario = request.getParameter("txtUsuario").toString();
			}

			if (request.getParameter("txtEmail") != null) {
				Email = request.getParameter("txtEmail").toString();
			}

			if (request.getParameter("txtDni") != null) {
				DNI = request.getParameter("txtDni").toString();
			}

			if (request.getParameter("txtCuil") != null) {
				Cuil = request.getParameter("txtCuil").toString();
			}

			ArrayList<CuentasPorUsuario> listaCu = new ArrayList<CuentasPorUsuario>();
			CuentasPorUsuarioNegImpl negocio = new CuentasPorUsuarioNegImpl();
			listaCu = (ArrayList<CuentasPorUsuario>) negocio.ObtenerFiltro(nombreUsuario, Email, DNI, Cuil);

			request.setAttribute("BusquedaCu", listaCu);
			RequestDispatcher rd = request.getRequestDispatcher("/ABMCuentas.jsp");
			rd.forward(request, response);
			return;
		}

		
		// Lista de cuentas que tiene el Usuario //
		
		if (request.getParameter("btnListar") != null) {
			
			int error = 0;
			int cantidadCuentas = (Integer.parseInt(request.getParameter("cantidadCuentas")));
			
			if (cantidadCuentas > 0)
			{

			ArrayList<Cuentas> listaC = new ArrayList<Cuentas>();
			CuentasNegImpl negocio = new CuentasNegImpl();
			listaC = (ArrayList<Cuentas>) negocio
					.ObtenerCuentas(Integer.parseInt(request.getParameter("idUsuario").toString()));

			request.setAttribute("listaC", listaC);

			RequestDispatcher rd = request.getRequestDispatcher("/ABMCuentas.jsp");
			rd.forward(request, response);
			}
			else {
				error = 1;
				String texto;
				texto = "No tiene cuentas para listar";
				request.setAttribute("Error", texto);
			}			
		}

		
		// Levanta el modal para agregar una nueva cuenta al Usuario //
		
		if (request.getParameter("btnModalAgregar") != null) {
			UsuariosNegImpl negocio = new UsuariosNegImpl();
			Usuarios usuarioCta = new Usuarios();
			int error = 0;

			int cantidadCuentas = (Integer.parseInt(request.getParameter("cantidadCuentas")));

			if (cantidadCuentas < 3) {

				usuarioCta = negocio.obtenerUno(Integer.parseInt(request.getParameter("idUsuario").toString()));

				request.setAttribute("usuarioCta", usuarioCta);
			} else {
				error = 1;
				String texto;
				texto = "Sólo se permiten 3 cuentas por usuario.";
				request.setAttribute("Error", texto);
			}
		}

		// Agrega cuenta al Usuario //
		
		if (request.getParameter("btnAgregar") != null) {
			CuentasNegImpl negocio = new CuentasNegImpl();

			Cuentas cuenta = new Cuentas();
			TipoDeCuentas tipoDeCuenta = new TipoDeCuentas();
			Usuarios usuario = new Usuarios();
			boolean filas = false;
			int error = 0;

			if (request.getParameter("txtId").toString() != null && request.getParameter("txtId").toString() != "") {
				usuario.setIdUsuario(Integer.parseInt(request.getParameter("txtId")));
				cuenta.setUsuario(usuario);
			} else {
				error = 1;
			}

			if (request.getParameter("txtTipoCaja").toString() != null
					&& request.getParameter("txtTipoCaja").toString() != "") {
				tipoDeCuenta.setIdTipodeCuenta(Integer.parseInt(request.getParameter("txtTipoCaja")));
				cuenta.setTipoDeCuenta(tipoDeCuenta);
			} else {
				error = 1;
			}

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat simpleformat = new SimpleDateFormat("yyyy-MM-dd");
			String fecha = simpleformat.format(cal.getTime());

			Date date;
			try {
				date = simpleformat.parse(fecha);
				cuenta.setFechaCreacion(date);
			} catch (ParseException e) {

				e.printStackTrace();
			}

			cuenta.setSaldo(10000);
			int numero = (int) (Math.random() * 100000000 + 1);

			cuenta.setCbu("0025-" + Integer.toString(numero));

			filas = negocio.insertar(cuenta);

			if (filas == true) {
				request.setAttribute("insert", 1);
			} else {
				request.setAttribute("insert", 0);
			}
		}

		
		// Levanta el modal para modificar una cuenta del Usuario //
		
		if (request.getParameter("btnModalModificar") != null) {
			CuentasNegImpl negocio = new CuentasNegImpl();
			Cuentas usuarioCtaEdit = new Cuentas();

			if (request.getParameter("idCuentaEdit").toString() != null
					&& request.getParameter("idCuentaEdit").toString() != "") {

				usuarioCtaEdit = negocio
						.obtenerCuenta(Integer.parseInt(request.getParameter("idCuentaEdit").toString()));

			}

			request.setAttribute("usuarioCtaEdit", usuarioCtaEdit);
		}

		
		// Modifica cuenta del Usuario //
		
		if (request.getParameter("btnModificar") != null) {
			CuentasNegImpl negocio = new CuentasNegImpl();

			Cuentas cuenta = new Cuentas();
			TipoDeCuentas tipoDeCuenta = new TipoDeCuentas();

			int error = 0;

			try {
				if (request.getParameter("txtIdCtaEdit").toString() != null
						&& request.getParameter("txtIdCtaEdit").toString() != "") {
					cuenta.setNroDeCuenta(Integer.parseInt(request.getParameter("txtIdCtaEdit")));
				}

				if (request.getParameter("txtSaldo").toString() != null
						&& request.getParameter("txtSaldo").toString() != "") {

					// Excepción propia en Cuentas //
					Cuentas.validaSaldo(Float.parseFloat(request.getParameter("txtSaldo")));
					cuenta.setSaldo(Float.parseFloat(request.getParameter("txtSaldo")));
				}

				if (request.getParameter("txtTipoCaja").toString() != null
						&& request.getParameter("txtTipoCaja").toString() != "") {
					tipoDeCuenta.setIdTipodeCuenta(Integer.parseInt(request.getParameter("txtTipoCaja")));
					cuenta.setTipoDeCuenta(tipoDeCuenta);
				}

				boolean fila = negocio.modificar(cuenta);
				
			} catch (Exception e) {
				error = 1;
				String texto;
				texto = e.getMessage();
				request.setAttribute("Error", texto);
			}

		}

		
		// Levanta el modal para eliminar una cuenta al Usuario //
		
		if (request.getParameter("btnModalEliminar") != null) {
			
			if (request.getParameter("idCuentaEdit") != null) {				
				
				request.setAttribute("delete",request.getParameter("idCuentaEdit"));
				request.getSession().setAttribute("idborrar",request.getParameter("idCuentaEdit").toString());
			}
		}

		
		// Elimina cuenta
		
		if (request.getParameter("btnEliminar") != null) {
			
			int id = Integer.parseInt(request.getSession().getAttribute("idborrar").toString());
			
			CuentasNegImpl negocio = new CuentasNegImpl();
			negocio.eliminar(id);

		}

		RequestDispatcher rd = request.getRequestDispatcher("/ABMCuentas.jsp");
		rd.forward(request, response);
	}
}
