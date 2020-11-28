package negocioImpl;

import java.util.Date;
import java.util.ArrayList;

import datos.MovimientosDao;
import datosImpl.MovimientosDaoImpl;
import entidad.Movimientos;
import negocio.MovimientosNeg;


public class MovimientosNegImpl implements MovimientosNeg{

	private MovimientosDao movDao= new MovimientosDaoImpl();
	
	@Override
	public boolean Transferencias(float importe, int cuentaorigen, int cuentadestino, int usuarioorigen,
			int usuariodestino, String detalle,int tipomovimiento) {
		
		return movDao.Transferencias(importe, cuentaorigen, cuentadestino, usuarioorigen, usuariodestino, detalle, tipomovimiento);
	}

	@Override
	public ArrayList<Movimientos> ListarMovimientos(int nrodecuenta) {
		return movDao.ListarMovimientos(nrodecuenta);
	}

	@Override
	public ArrayList<Movimientos> ListarMovimientos(int tipodemovimiento, Date fechadesde, Date fechahasta, int nrodecuenta) {
		return movDao.ListarMovimientos(tipodemovimiento, fechadesde, fechahasta, nrodecuenta);
	}

}
