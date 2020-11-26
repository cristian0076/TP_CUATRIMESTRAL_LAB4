package negocio;

import java.util.List;

import entidad.CuentasPorUsuario;
import entidad.PrestamosPorUsuario;

public interface PrestamosPorUsuarioNeg {
		
	
	public List<PrestamosPorUsuario> FiltroPrestamosPorUsuario(String NombreUsuario,String Email,String Dni,String Cuil);
}