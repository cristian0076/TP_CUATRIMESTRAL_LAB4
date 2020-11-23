package datos;

import java.util.ArrayList;

import entidad.Movimientos;

public interface MovimientosDao {

	public boolean Transferencias(float importe,int cuentaorigen,int cuentadestino,int usuarioorigen,int usuariodestino,String detalle,int tipomovimiento);
    public ArrayList<Movimientos> ListarMovimientos(int nrodecuenta);
}
