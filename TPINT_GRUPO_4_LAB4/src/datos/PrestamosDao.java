package datos;

import java.util.List;

import entidad.Prestamos;

public interface PrestamosDao {
	public boolean insertar(Prestamos p);
	public List<Prestamos> ListarPrestamosxUsuario (int id);
}
