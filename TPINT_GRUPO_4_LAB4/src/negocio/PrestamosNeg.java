package negocio;

import java.util.List;

import entidad.Prestamos;

public interface PrestamosNeg {
	public boolean insertar(Prestamos p);
	public List<Prestamos> ListarPrestamosxUsuario (int id);
	public List<Prestamos> ObtenerPrestamos(int IdUsuario);
	public Prestamos obtenerPrestamo(int IdPrestamo);
	public boolean modificar(Prestamos prestamo);
}
