package entidad;

import java.sql.Timestamp;
import java.util.Date;

public class Prestamos {

	private int IdPrestamo;
	private Date Fecha;
	private float ImporteConIntereses;
	private float ImporteSolicitado;
	private int PlazoDePago;
	private float ValorCuotaMensual;
	private Usuarios Usuario;
	private EstadosDePrestamo EstadoPrestamo;
	private Cuentas Cuenta;
	
	public Prestamos()
	{
		
	}
	
	

	public Prestamos(int IdPrestamo, Timestamp Fecha, float ImporteConIntereses, float ImporteSolicitado, int PlazoDePago, float ValorCuotaMensual,int CantidadDeCuotas,Usuarios Usuario,EstadosDePrestamo EstadoPrestamo)
	{
		this.IdPrestamo= IdPrestamo;
		this.Fecha= Fecha;
		this.ImporteConIntereses = ImporteConIntereses;
		this.ImporteSolicitado = ImporteSolicitado;
		this.PlazoDePago = PlazoDePago;
		this.ValorCuotaMensual= ValorCuotaMensual;
		this.Usuario = Usuario;
		this.EstadoPrestamo= EstadoPrestamo;
	}

	

	public Cuentas getCuenta() {
		return Cuenta;
	}


	public void setCuenta(Cuentas cuenta) {
		Cuenta = cuenta;
	}

	

	public int getIdPrestamo() {
		return IdPrestamo;
	}

	public Date getFecha() {
		return Fecha;
	}

	public float getImporteConIntereses() {
		return ImporteConIntereses;
	}

	public float getImporteSolicitado() {
		return ImporteSolicitado;
	}

	public int getPlazoDePago() {
		return PlazoDePago;
	}

	public float getValorCuotaMensual() {
		return ValorCuotaMensual;
	}

	

	public void setIdPrestamo(int idPrestamo) {
		IdPrestamo = idPrestamo;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}

	public void setImporteConIntereses(float importeConIntereses) {
		ImporteConIntereses = importeConIntereses;
	}

	public void setImporteSolicitado(float importeSolicitado) {
		ImporteSolicitado = importeSolicitado;
	}

	public void setPlazoDePago(int plazoDePago) {
		PlazoDePago = plazoDePago;
	}

	public void setValorCuotaMensual(float valorCuotaMensual) {
		ValorCuotaMensual = valorCuotaMensual;
	}
	

	public Usuarios getUsuario() {
		return Usuario;
	}

	public EstadosDePrestamo getEstadoPrestamo() {
		return EstadoPrestamo;
	}


	public void setUsuario(Usuarios usuario) {
		Usuario = usuario;
	}

	public void setEstadoPrestamo(EstadosDePrestamo estadoPrestamo) {
		EstadoPrestamo = estadoPrestamo;
	}
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Prestamos [IdPrestamo=" + IdPrestamo + ", Fecha=" + Fecha + ", ImporteConIntereses="
				+ ImporteConIntereses + ", ImporteSolicitado=" + ImporteSolicitado + ", PlazoDePago=" + PlazoDePago
				+ ", ValorCuotaMensual=" + ValorCuotaMensual + "]";
	}
	
}
