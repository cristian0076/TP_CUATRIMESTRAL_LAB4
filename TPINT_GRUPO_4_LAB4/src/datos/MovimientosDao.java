package datos;

public interface MovimientosDao {

	public boolean Transferencias(float importe,int cuentaorigen,int cuentadestino,int usuarioorigen,int usuariodestino,String detalle,int tipomovimiento);
	
}
