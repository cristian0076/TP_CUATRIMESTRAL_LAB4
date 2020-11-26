package entidad;

public class PrestamosPorUsuario {

	private Usuarios Usuario;
	private int CantidadPrestamos;
	
	
	public PrestamosPorUsuario()
	{
		
	}

	public PrestamosPorUsuario(Usuarios Usuario, int CantidadPrestamos , Prestamos prestamos)
	{
		this.CantidadPrestamos= CantidadPrestamos;
		this.Usuario= Usuario;
		
		
	}
	
	
	public Usuarios getUsuario() {
		return Usuario;
	}


	public int getCantidadPrestamos() {
		return CantidadPrestamos;
	}

	public void setUsuario(Usuarios usuario) {
		Usuario = usuario;
	}


	public void setCantidadPrestamos(int CantidadPrestamos) {
		this.CantidadPrestamos = CantidadPrestamos;
	}


	
	
	@Override
	public String toString() {
		return "CuentasPorUsuario [Usuario=" + Usuario + ", CantidadPrestamos=" + CantidadPrestamos + "]";
	}

}