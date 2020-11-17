package entidad;

import java.sql.Timestamp;
import java.util.Date;

public class Movimientos {

	private int IdMovimiento;
	private Usuarios Usuario;
	private Date Fecha;
	private String Detalle;
	private float Importe;
	private TiposDeMovimientos TipoDeMovimiento;
	
	public Movimientos()
	{
		
	}
	
	public Movimientos(int IdMovimiento,Usuarios Usuario ,Timestamp Fecha, String Detalle, float Importe, TiposDeMovimientos TipoDeMovimiento)
	{
		this.IdMovimiento = IdMovimiento;
		this.Usuario=Usuario;
		this.Fecha= Fecha;
		this.Detalle= Detalle;
		this.Importe = Importe;
		this.TipoDeMovimiento= TipoDeMovimiento;
	}
	

	public int getIdMovimiento() {
		return IdMovimiento;
	}

	public Date getFecha() {
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

	public void setFecha(Date fecha) {
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

	public Usuarios getUsuario() {
		return Usuario;
	}

	public void setUsuario(Usuarios usuario) {
		Usuario = usuario;
	}

	@Override
	public String toString() {
		return "Movimientos [IdMovimiento=" + IdMovimiento + ", Usuario=" + Usuario + ", Fecha=" + Fecha + ", Detalle="
				+ Detalle + ", Importe=" + Importe + ", TipoDeMovimiento=" + TipoDeMovimiento + "]";
	}	

	
	
}
