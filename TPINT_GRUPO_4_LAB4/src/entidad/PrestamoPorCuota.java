package entidad;

import java.sql.Timestamp;
import java.util.Date;

public class PrestamoPorCuota {
	
	private Prestamos Prestamo;
	private int NroCuota;
	private Date FechaPago;
	private boolean Estado;
	
	
	public PrestamoPorCuota()
	{
		
	}
	
	public PrestamoPorCuota(Prestamos Prestamo, int NroCuota, Timestamp FechaPago, boolean Estado)
	{
		this.Prestamo= Prestamo;
		this.NroCuota= NroCuota;
		this.FechaPago= FechaPago;
		this.Estado= Estado;
	}

	public Prestamos getPrestamo() {
		return Prestamo;
	}
	
	public boolean getEstado() {
		return Estado;
	}

	public int getNroCuota() {
		return NroCuota;
	}

	public Date getFechaPago() {
		return FechaPago;
	}

	public boolean isEstado() {
		return Estado;
	}

	public void setPrestamo(Prestamos prestamo) {
		Prestamo = prestamo;
	}

	public void setNroCuota(int nroCuota) {
		NroCuota = nroCuota;
	}

	public void setFechaPago(Date fechaPago) {
		FechaPago = fechaPago;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}
	

	@Override
	public String toString() {
		return "PrestamoPorCuota [Prestamo=" + Prestamo + ", NroCuota=" + NroCuota + ", FechaPago=" + FechaPago
				+ ", Estado=" + Estado + "]";
	}	
	
	
}
