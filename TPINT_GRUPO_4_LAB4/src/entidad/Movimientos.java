package entidad;

import java.sql.Timestamp;

public class Movimientos {

	private int IdMovimiento;
	private Timestamp Fecha;
	private String Detalle;
	private float Importe;
	private TiposDeMovimientos TipoDeMovimiento;
	
	public Movimientos()
	{
		
	}
	
	public Movimientos(int IdMovimiento, Timestamp Fecha, String Detalle, float Importe, TiposDeMovimientos TipoDeMovimiento)
	{
		this.IdMovimiento = IdMovimiento;
		this.Fecha= Fecha;
		this.Detalle= Detalle;
		this.Importe = Importe;
		this.TipoDeMovimiento= TipoDeMovimiento;
	}
	

	public int getIdMovimiento() {
		return IdMovimiento;
	}

	public Timestamp getFecha() {
		return Fecha;
	}

	public String getDetalle() {
		return Detalle;
	}

	public float getImporte() {
		return Importe;
	}

	public TiposDeMovimientos getTipoDeMovimiento() {
		return TipoDeMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		IdMovimiento = idMovimiento;
	}

	public void setFecha(Timestamp fecha) {
		Fecha = fecha;
	}

	public void setDetalle(String detalle) {
		Detalle = detalle;
	}

	public void setImporte(float importe) {
		Importe = importe;
	}

	public void setTipoDeMovimiento(TiposDeMovimientos tipoDeMovimiento) {
		TipoDeMovimiento = tipoDeMovimiento;
	}
	
	
	@Override
	public String toString() {
		return "Movimientos [IdMovimiento=" + IdMovimiento + ", Fecha=" + Fecha + ", Detalle=" + Detalle + ", Importe="
				+ Importe + ", TipoDeMovimiento=" + TipoDeMovimiento + "]";
	}
}