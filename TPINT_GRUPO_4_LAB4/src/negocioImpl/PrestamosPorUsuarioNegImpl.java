package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datos.PrestamosPorUsuarioDao;
import datosImpl.PrestamosPorUsuarioDaoImpl;
import entidad.PrestamosPorUsuario;
import negocio.PrestamosPorUsuarioNeg;

public class PrestamosPorUsuarioNegImpl implements PrestamosPorUsuarioNeg{

	private PrestamosPorUsuarioDao CuDao = new PrestamosPorUsuarioDaoImpl();
	
	public PrestamosPorUsuarioNegImpl() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<PrestamosPorUsuario> FiltroPrestamosPorUsuario(String NombreUsuario, String Email, String Dni, String Cuil) {
		return (ArrayList<PrestamosPorUsuario>)CuDao.FiltroPrestamosPorUsuario(NombreUsuario, Email, Dni, Cuil);
	}

}