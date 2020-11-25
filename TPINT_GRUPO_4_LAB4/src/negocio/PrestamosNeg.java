package negocio;

import java.util.List;

import entidad.Prestamos;

public interface PrestamosNeg {
	public boolean insertar(Prestamos p);
	public List<Prestamos> ListarPrestamosxUsuario (int id);
}
