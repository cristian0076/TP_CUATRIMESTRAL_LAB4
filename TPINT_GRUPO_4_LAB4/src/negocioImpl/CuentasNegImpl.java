package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datos.CuentasDao;
import datosImpl.CuentasDaoImpl;
import entidad.Cuentas;
import negocio.CuentasNeg;

public class CuentasNegImpl implements CuentasNeg {

	private CuentasDao Cdao = new CuentasDaoImpl(); 
	
	
	public CuentasNegImpl() {
	
	}
	
	
	@Override
	public List<Cuentas> ObtenerCuentas(int IdUsuario) {
		
		return (ArrayList<Cuentas>)Cdao.ObtenerCuentas(IdUsuario);
	}
	
	@Override
	public boolean insertar(Cuentas cuenta) {
		return Cdao.insertar(cuenta);
	}
	
	@Override
	public boolean modificar(Cuentas cuenta) {
		return Cdao.modificar(cuenta);
	}
	
		@Override
	public Cuentas obtenerCuenta(int NroCuenta) {
		// TODO Auto-generated method stub
		return Cdao.obtenerCuenta(NroCuenta);
	}


	@Override
	public Cuentas obtenerCuenta(String Cbu) {
		return Cdao.obtenerCuenta(Cbu);
	}
	
	@Override
	public boolean eliminar(int id) {
		return Cdao.eliminar(id);
	}
}