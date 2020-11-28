package entidad;


import java.sql.Date;

public class Reporte01 {
	
	private Date dia;
	private String TipoDeCuenta;
	private Float Importe; 
	
	public Reporte01() {
	}
	
	public Reporte01(Date dia, String TipoDeCuenta, Float Importe)
	{
	this.dia= dia;
	this.TipoDeCuenta = TipoDeCuenta;
	this.Importe = Importe;
	}


		/**
	 * @return the dia
	 */
	public Date getDia() {
		return dia;
	}

	/**
	 * @return the tipoDeCuenta
	 */
	public String getTipoDeCuenta() {
		return TipoDeCuenta;
	}

	/**
	 * @return the importe
	 */
	public Float getImporte() {
		return Importe;
	}

	/**
	 * @param dia the dia to set
	 */
	public void setDia(Date dia) {
		this.dia = dia;
	}

	/**
	 * @param tipoDeCuenta the tipoDeCuenta to set
	 */
	public void setTipoDeCuenta(String tipoDeCuenta) {
		TipoDeCuenta = tipoDeCuenta;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(Float importe) {
		Importe = importe;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Reporte01 [dia=" + dia + ", TipoDeCuenta=" + TipoDeCuenta + ", Importe=" + Importe + "]";
	}	
	
	
}
