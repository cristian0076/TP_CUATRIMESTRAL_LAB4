package negocio;

import java.util.List;

import entidad.Cuentas;

public interface CuentasNeg {

	public List<Cuentas> ObtenerCuentas(int IdUsuario);
	public Cuentas obtenerCuenta(int NroCuenta);
}