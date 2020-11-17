package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datos.CuentasPorUsuarioDao;
import datosImpl.CuentasPorUsuarioDaoImpl;
import entidad.CuentasPorUsuario;
import negocio.CuentasPorUsuarioNeg;

public class CuentasPorUsuarioNegImpl implements CuentasPorUsuarioNeg{

	private CuentasPorUsuarioDao CuDao= new CuentasPorUsuarioDaoImpl();
	
	public CuentasPorUsuarioNegImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CuentasPorUsuario> ObtenerFiltro(String NombreUsuario, String Email, String Dni, String Cuil) {
		return (ArrayList<CuentasPorUsuario>)CuDao.ObtenerFiltro(NombreUsuario, Email, Dni, Cuil);
	}

}