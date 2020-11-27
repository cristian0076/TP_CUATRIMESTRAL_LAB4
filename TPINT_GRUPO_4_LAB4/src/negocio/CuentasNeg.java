package negocio;

import java.util.List;
import entidad.Cuentas;


public interface CuentasNeg {

	public List<Cuentas> ObtenerCuentas(int IdUsuario);
	public boolean insertar(Cuentas cuenta);
	public boolean modificar(Cuentas cuenta);
	public Cuentas obtenerCuenta(int NroCuenta);
	public Cuentas obtenerCuenta(String Cbu);
	public boolean eliminar(int id);
}