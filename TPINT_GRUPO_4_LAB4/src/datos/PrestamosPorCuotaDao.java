package datos;

import java.util.List;

import entidad.PrestamoPorCuota;

public interface PrestamosPorCuotaDao {
	public List<PrestamoPorCuota> ObtenerCuotas(int IdPrestamo);
}
