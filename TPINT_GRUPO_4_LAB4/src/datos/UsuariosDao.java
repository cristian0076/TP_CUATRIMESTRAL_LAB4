package datos;

import java.util.List;

import entidad.CuentasPorUsuario;
import entidad.Usuarios;

public interface UsuariosDao {
	
	public List<Usuarios> obtenerTodos();
	public Usuarios obtenerUno(int id);
	public boolean insertar(Usuarios usuario);
	public boolean editar(Usuarios usuario);
	public boolean borrar(int id);
	public boolean cambiar_estado(int id);
	public boolean editar_clave(Usuarios usuario);
	public Usuarios obtenerUnoxUsuario(String Usuario, String Password);
	public List<Usuarios> ListarUsuariosFiltro (String Nombre,String Email, String Dni, String Cuil);
}
