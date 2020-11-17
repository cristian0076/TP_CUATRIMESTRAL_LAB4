package negocio;

import java.util.List;

import entidad.CuentasPorUsuario;

public interface CuentasPorUsuarioNeg {
		
	
	public List<CuentasPorUsuario> ObtenerFiltro(String NombreUsuario,String Email,String Dni,String Cuil);

}