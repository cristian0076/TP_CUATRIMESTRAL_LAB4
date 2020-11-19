package datos;

import java.util.List;

import entidad.Cuentas;

public interface CuentasDao {

		public List<Cuentas> ObtenerCuentas(int IdUsuario);
		public Cuentas obtenerCuenta(int NroCuenta);
}
