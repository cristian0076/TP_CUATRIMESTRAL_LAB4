package entidad;

import java.sql.Timestamp;
import java.util.Date;

public class Cuentas {

	private int NroDeCuenta;
	private TipoDeCuentas TipoDeCuenta;
	private Date FechaCreacion;
	private String Cbu;
	private float Saldo;
	private Usuarios Usuario;
	
	
	public Cuentas()
	{
		
	}
	
	public Cuentas ( int NroDeCuenta, TipoDeCuentas TipoDeCuenta,Timestamp FechaCreacion, String Cbu, float Saldo ,Usuarios Usuario)
	{
		this.TipoDeCuenta = TipoDeCuenta;
		this.NroDeCuenta = NroDeCuenta;
		this.FechaCreacion= FechaCreacion;
		this.Cbu= Cbu;
		this.Saldo= Saldo;
		this.Usuario= Usuario;
		
	}

	
	public TipoDeCuentas getIdTipoDeCuenta() {
		return TipoDeCuenta;
	}


	public void setIdTipoDeCuenta(TipoDeCuentas tipoDeCuenta) {
		TipoDeCuenta = tipoDeCuenta;
	}


	public int getNroDeCuenta() {
		return NroDeCuenta;
	}


	public void setNroDeCuenta(int nroDeCuenta) {
		NroDeCuenta = nroDeCuenta;
	}


	public Date getFechaCreacion() {
		return FechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}


	public String getCbu() {
		return Cbu;
	}


	public void setCbu(String cbu) {
		Cbu = cbu;
	}


	public float getSaldo() {
		return Saldo;
	}


	public void setSaldo(float saldo) {
		Saldo = saldo;
	}
	public TipoDeCuentas getTipoDeCuenta() {
		return TipoDeCuenta;
	}
	public Usuarios getUsuario() {
		return Usuario;
	}

	public void setTipoDeCuenta(TipoDeCuentas tipoDeCuenta) {
		TipoDeCuenta = tipoDeCuenta;
	}

	public void setUsuario(Usuarios usuario) {
		Usuario = usuario;
	}	
	
	
	
	
	
	@Override
	public String toString() {
		return "Cuentas [IdTipoDeCuenta=" + TipoDeCuenta.getIdTipodeCuenta() +TipoDeCuenta.getDescripcion() + ", NroDeCuenta=" + NroDeCuenta + ", FechaCreacion="
				+ FechaCreacion + ", Cbu=" + Cbu + ", Saldo=" + Saldo + "]";
	}

}
