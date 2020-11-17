package entidad;

public class CuentasPorUsuario {

	private Usuarios Usuario;
	private int CantidadCuentas;
	
	
	public CuentasPorUsuario()
	{
		
	}

	public CuentasPorUsuario(Usuarios Usuario, int CantidadCuentas , Cuentas Cuenta)
	{
		this.CantidadCuentas= CantidadCuentas;
		this.Usuario= Usuario;
		
		
	}
	
	
	public Usuarios getUsuario() {
		return Usuario;
	}


	public int getCantidadCuentas() {
		return CantidadCuentas;
	}

	public void setUsuario(Usuarios usuario) {
		Usuario = usuario;
	}


	public void setCantidadCuentas(int cantidadCuentas) {
		CantidadCuentas = cantidadCuentas;
	}


	
	
	@Override
	public String toString() {
		return "CuentasPorUsuario [Usuario=" + Usuario + ", CantidadCuentas=" + CantidadCuentas + "]";
	}

}