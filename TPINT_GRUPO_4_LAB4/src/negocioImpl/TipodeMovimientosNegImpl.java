package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datos.TipodeMovimientosDao;
import datosImpl.TipodeMovimivientosDaoImpl;
import entidad.TiposDeMovimientos;
import negocio.TipodeMovimientosNeg;


public class TipodeMovimientosNegImpl implements TipodeMovimientosNeg{

	private TipodeMovimientosDao tm = new TipodeMovimivientosDaoImpl();

	@Override
	public List<TiposDeMovimientos> listartiposDeMovimientos() {
				return (ArrayList<TiposDeMovimientos>)tm.listartiposDeMovimientos();
	}
}
