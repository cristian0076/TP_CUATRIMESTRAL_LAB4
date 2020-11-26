package datosImpl;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import datos.PrestamosPorUsuarioDao;
import entidad.Generos;
import entidad.PrestamosPorUsuario;
import entidad.TiposDeUsuarios;
import entidad.Usuarios;

public class PrestamosPorUsuarioDaoImpl implements PrestamosPorUsuarioDao {

	private Conexion cn;
	
	public PrestamosPorUsuarioDaoImpl()
	{
		
	}
	
	@Override
	public List<PrestamosPorUsuario> FiltroPrestamosPorUsuario(String Nombre, String Email, String Dni, String Cuil) {
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cn= new Conexion();
		cn.Open();
		
		List<PrestamosPorUsuario> list = new ArrayList<PrestamosPorUsuario>();
		try {
			
			 
			ResultSet rs = cn.query("SELECT U.idUsuario, U.NombreUsuario, U.Nombre, U.Apellido, U.Email, U.DNI, U.Cuil, (SELECT COUNT(*) From prestamos C WHERE C.IdUsuario = U.IdUsuario) AS CantidadPrestamos FROM usuarios u");

			System.out.println("RESULLLTTT "+ rs);
			while(rs.next())
			{
				PrestamosPorUsuario CU = new PrestamosPorUsuario();
				Usuarios User = new Usuarios();
				Generos gen = new Generos();
				TiposDeUsuarios TipoU= new TiposDeUsuarios();
				
				User.setIdUsuario(rs.getInt("U.idUsuario"));
				User.setNombreUsuario(rs.getString("U.NombreUsuario"));
				User.setNombre(rs.getString("U.Nombre"));
				User.setApellido(rs.getString("U.Apellido"));
				User.setEmail(rs.getString("U.Email"));
				User.setDni(rs.getString("U.DNI"));
				User.setCuil(rs.getString("U.Cuil"));
				User.setGenero(gen);
				User.setTipoDeUsuario(TipoU);
				
				
				CU.setUsuario(User);
				CU.setCantidadPrestamos(rs.getInt("CantidadPrestamos"));
		
				list.add(CU);
				
				System.out.println("LISTAAAA "+ list);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		return list;
	}


	


}