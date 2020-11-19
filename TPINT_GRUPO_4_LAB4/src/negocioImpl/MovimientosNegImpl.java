package negocioImpl;

import datos.MovimientosDao;
import datosImpl.MovimientosDaoImpl;
import negocio.MovimientosNeg;


public class MovimientosNegImpl implements MovimientosNeg{

	private MovimientosDao movDao= new MovimientosDaoImpl();
	
	@Override
	public boolean Transferencias(float importe, int cuentaorigen, int cuentadestino, int usuarioorigen,
			int usuariodestino, String detalle) {
		
		return movDao.Transferencias(importe, cuentaorigen, cuentadestino, usuarioorigen, usuariodestino, detalle);
	}

}
