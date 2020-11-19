package negocio;

public interface MovimientosNeg {
	
	public boolean Transferencias(float importe,int cuentaorigen,int cuentadestino,int usuarioorigen,int usuariodestino,String detalle);
}
