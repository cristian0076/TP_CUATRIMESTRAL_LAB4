package datos;

import java.util.List;
import entidad.CuentasPorUsuario;


public interface CuentasPorUsuarioDao {
	
	public List<CuentasPorUsuario> ObtenerFiltro(String NombreUsuario,String Email,String Dni,String Cuil);
	
}