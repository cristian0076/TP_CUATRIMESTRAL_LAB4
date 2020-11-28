package negocio;

import java.util.Date;
import java.util.ArrayList;

import entidad.Movimientos;

public interface MovimientosNeg {
	
	public boolean Transferencias(float importe,int cuentaorigen,int cuentadestino,int usuarioorigen,int usuariodestino,String detalle, int tipomovimiento);
	public ArrayList<Movimientos> ListarMovimientos(int nrodecuenta); 
    public ArrayList<Movimientos> ListarMovimientos(int tipodemovimiento, Date fechadesde,Date fechahasta, int nrodecuenta);
}
