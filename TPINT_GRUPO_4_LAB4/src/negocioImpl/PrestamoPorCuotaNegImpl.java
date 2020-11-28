package negocioImpl;

import java.util.List;

import datos.PrestamosPorCuotaDao;
import datosImpl.PrestamosPorCuotaDaoImpl;
import entidad.PrestamoPorCuota;
import negocio.PrestamoPorCuotaNeg;


public class PrestamoPorCuotaNegImpl implements PrestamoPorCuotaNeg{
	
	private PrestamosPorCuotaDao CuotaDao= new PrestamosPorCuotaDaoImpl();
	
	@Override
	public List<PrestamoPorCuota> ObtenerCuotas(int IdPrestamo) {
		return CuotaDao.ObtenerCuotas(IdPrestamo);
	}

}
