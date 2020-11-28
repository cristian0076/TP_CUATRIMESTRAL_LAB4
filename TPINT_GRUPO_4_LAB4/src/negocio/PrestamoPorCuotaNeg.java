package negocio;

import java.util.List;

import entidad.PrestamoPorCuota;

public interface PrestamoPorCuotaNeg {
	public List<PrestamoPorCuota> ObtenerCuotas(int IdPrestamo);
}
