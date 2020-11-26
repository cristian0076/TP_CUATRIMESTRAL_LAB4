package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datos.PrestamosDao;
import datosImpl.PrestamosDaoImpl;
import entidad.Prestamos;
import negocio.PrestamosNeg;

public class PrestamosNegImpl implements PrestamosNeg {

	private PrestamosDao PrestamosDao = new PrestamosDaoImpl();
	
	
	public PrestamosNegImpl(PrestamosDao dirDao)
	{
		this.PrestamosDao= dirDao;
	}
	
	
	
	public PrestamosNegImpl() {

	}



	@Override
	public boolean insertar(Prestamos p) {
		return PrestamosDao.insertar(p);
	}



	@Override
	public List<Prestamos> ListarPrestamosxUsuario(int id) {
		return PrestamosDao.ListarPrestamosxUsuario(id);
	}

	@Override
	public List<Prestamos> ObtenerPrestamos(int IdUsuario) {
		
		return (ArrayList<Prestamos>)PrestamosDao.ObtenerPrestamos(IdUsuario);
	}
	
	@Override
	public Prestamos obtenerPrestamo(int IdPrestamo) {
		// TODO Auto-generated method stub
		return PrestamosDao.obtenerPrestamo(IdPrestamo);
	}
	
	@Override
	public boolean modificar(Prestamos prestamo) {
		return PrestamosDao.modificar(prestamo);
	}
}
