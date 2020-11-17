package datosImpl;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import datos.CuentasPorUsuarioDao;
import entidad.CuentasPorUsuario;
import entidad.Generos;
import entidad.TiposDeUsuarios;
import entidad.Usuarios;

public class CuentasPorUsuarioDaoImpl implements CuentasPorUsuarioDao {

	private Conexion cn;
	
	public CuentasPorUsuarioDaoImpl()
	{
		
	}
	

	public List<CuentasPorUsuario> ObtenerFiltro(String Nombre, String Email, String Dni, String Cuil) {
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cn= new Conexion();
		cn.Open();
		
		List<CuentasPorUsuario> list = new ArrayList<CuentasPorUsuario>();
		try {
			
			 
			ResultSet rs = cn.query("SELECT U.idUsuario, U.NombreUsuario, U.Nombre, U.Apellido, U.Email, U.DNI, U.Cuil, (SELECT COUNT(*) From cuentas C WHERE C.IdUsuario = U.IdUsuario) AS Cantidad_Cuentas FROM usuarios U WHERE U.NombreUsuario like  '%"+Nombre+"%' AND U.Email LIKE '%"+Email+"%' AND U.DNI LIKE '%"+Dni+"%' AND U.Cuil LIKE '%"+Cuil+"%' ");
			
			while(rs.next())
			{
				CuentasPorUsuario CU = new CuentasPorUsuario();
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
				CU.setCantidadCuentas(rs.getInt("Cantidad_Cuentas"));
		
				list.add(CU);
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