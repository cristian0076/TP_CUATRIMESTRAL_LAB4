package entidad;

import java.sql.Timestamp;

public class Prestamos {

	private int IdPrestamo;
	private Timestamp Fecha;
	private float ImporteConIntereses;
	private float ImporteSolicitado;
	private int PlazoDePago;
	private float ValorCuotaMensual;
	private int CantidadDeCuotas;
	private Usuarios Usuario;
	private EstadosDePrestamo EstadoPrestamo;
	
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
		this.CantidadDeCuotas= CantidadDeCuotas;
		this.Usuario = Usuario;
		this.EstadoPrestamo= EstadoPrestamo;
	}


	public int getIdPrestamo() {
		return IdPrestamo;
	}

	public Timestamp getFecha() {
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

	public int getCantidadDeCuotas() {
		return CantidadDeCuotas;
	}

	public void setIdPrestamo(int idPrestamo) {
		IdPrestamo = idPrestamo;
	}

	public void setFecha(Timestamp fecha) {
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

	public void setCantidadDeCuotas(int cantidadDeCuotas) {
		CantidadDeCuotas = cantidadDeCuotas;
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
				+ ", ValorCuotaMensual=" + ValorCuotaMensual + ", CantidadDeCuotas=" + CantidadDeCuotas + "]";
	}
	
}
