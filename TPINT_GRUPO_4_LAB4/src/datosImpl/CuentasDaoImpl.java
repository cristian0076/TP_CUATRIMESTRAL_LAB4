package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.CuentasDao;
import entidad.Cuentas;
import entidad.Generos;
import entidad.TipoDeCuentas;
import entidad.TiposDeUsuarios;
import entidad.Usuarios;

public class CuentasDaoImpl implements CuentasDao {

	
	private Conexion cn;
	
	public CuentasDaoImpl()
	{
		
	}
	
	public List<Cuentas> ObtenerCuentas(int IdUsuario) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cn= new Conexion();
		cn.Open();
		
		List<Cuentas> list =new ArrayList<Cuentas>();
		try {
			
			ResultSet rs = cn.query("SELECT u.IdUsuario, u.Apellido ,u.Cuil, c.NroDeCuenta, c.IdTipoDeCuenta, tc.Descripcion, c.FechaCreacion, "
					+ "c.CBU, c.Saldo FROM cuentas c right join usuarios u ON u.IdUsuario = c.IdUsuario "
					+ "LEFT JOIN tiposdecuentas tc ON c.IdTipoDeCuenta = tc.IdTipoDeCuenta "
					+ "WHERE u.IdUsuario = "+ IdUsuario);
			while(rs.next())
			{
				Cuentas c = new Cuentas();
				TipoDeCuentas tc = new TipoDeCuentas();
				Usuarios  u = new Usuarios();
				TiposDeUsuarios tu = new TiposDeUsuarios();
				Generos  g= new Generos();
				
				c.setNroDeCuenta(rs.getInt("c.NroDeCuenta"));
				tc.setIdTipodeCuenta(rs.getInt("c.IdTipoDeCuenta"));
				tc.setDescripcion(rs.getString("tc.Descripcion"));
				c.setFechaCreacion(rs.getDate("c.FechaCreacion"));
				c.setCbu(rs.getString("c.CBU"));
				c.setSaldo(rs.getFloat("c.Saldo"));
				u.setIdUsuario(rs.getInt("u.IdUsuario"));
				u.setApellido(rs.getString("u.Apellido"));
				u.setCuil(rs.getString("u.Cuil"));
				u.setGenero(g);
				u.setTipoDeUsuario(tu);
				c.setTipoDeCuenta(tc);
				c.setUsuario(u);
				
				list.add(c);
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

	
	@Override
	public boolean insertar(Cuentas cuenta) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean estado= true;
		
		cn= new Conexion();
		cn.Open();
		
		java.sql.Date date= new java.sql.Date(cuenta.getFechaCreacion().getTime());
		String query= "INSERT INTO `tp_banco`.`cuentas` (`IdTipoDeCuenta`, `FechaCreacion`, `CBU`, `Saldo`, `IdUsuario`) "
					+ "VALUES ('"+cuenta.getTipoDeCuenta().getIdTipodeCuenta()+"', '"+date+"', '"+cuenta.getCbu()+"', "
							+ "'"+cuenta.getSaldo()+"', '"+cuenta.getUsuario().getIdUsuario()+"');";
		try {
			estado= cn.execute(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return estado;	
	}
	
	
	@Override
	public boolean modificar(Cuentas cuenta) {

		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean estado= true;
		
		cn= new Conexion();
		cn.Open();
		
		String query= "UPDATE `tp_banco`.`cuentas` SET `Saldo` = '"
						+cuenta.getSaldo()+"', `IdTipoDeCuenta`= '"+cuenta.getTipoDeCuenta().getIdTipodeCuenta()
						+"' WHERE NroDeCuenta =" + cuenta.getNroDeCuenta();
		try {
			estado= cn.execute(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return estado;	
	}

	
	public Cuentas obtenerCuenta(int NroCuenta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cn= new Conexion();
		cn.Open();
		
		Cuentas c =new Cuentas();
		try {
			
			ResultSet rs = cn.query("SELECT u.IdUsuario, u.Apellido ,u.Cuil, c.NroDeCuenta, c.IdTipoDeCuenta, tc.Descripcion, c.FechaCreacion, c.CBU, c.Saldo FROM cuentas c left join usuarios u ON u.IdUsuario = c.IdUsuario LEFT JOIN tiposdecuentas tc ON c.IdTipoDeCuenta = tc.IdTipoDeCuenta WHERE c.NroDeCuenta = "+ NroCuenta);
			while(rs.next())
			{
				
				TipoDeCuentas tc = new TipoDeCuentas();
				Usuarios  u = new Usuarios();
				TiposDeUsuarios tu = new TiposDeUsuarios();
				Generos  g= new Generos();
				
				c.setNroDeCuenta(rs.getInt("c.NroDeCuenta"));
				tc.setIdTipodeCuenta(rs.getInt("c.IdTipoDeCuenta"));
				tc.setDescripcion(rs.getString("tc.Descripcion"));
				c.setFechaCreacion(rs.getDate("c.FechaCreacion"));
				c.setCbu(rs.getString("c.CBU"));
				c.setSaldo(rs.getFloat("c.Saldo"));
				u.setIdUsuario(rs.getInt("u.IdUsuario"));
				u.setApellido(rs.getString("u.Apellido"));
				u.setCuil(rs.getString("u.Cuil"));
				u.setGenero(g);
				u.setTipoDeUsuario(tu);
				c.setTipoDeCuenta(tc);
				c.setUsuario(u);
				
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		
		return c;
	}

	
	@Override
	public Cuentas obtenerCuenta(String Cbu) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cn= new Conexion();
		cn.Open();
		
		Cuentas c =new Cuentas();
		try {
			
			ResultSet rs = cn.query("SELECT u.IdUsuario, u.Apellido ,u.Cuil, c.NroDeCuenta, c.IdTipoDeCuenta, tc.Descripcion, c.FechaCreacion, c.CBU, c.Saldo FROM cuentas c left join usuarios u ON u.IdUsuario = c.IdUsuario LEFT JOIN tiposdecuentas tc ON c.IdTipoDeCuenta = tc.IdTipoDeCuenta WHERE c.CBU = '"+Cbu+"'");
			while(rs.next())
			{
				
				TipoDeCuentas tc = new TipoDeCuentas();
				Usuarios  u = new Usuarios();
				TiposDeUsuarios tu = new TiposDeUsuarios();
				Generos  g= new Generos();
				
				c.setNroDeCuenta(rs.getInt("c.NroDeCuenta"));
				tc.setIdTipodeCuenta(rs.getInt("c.IdTipoDeCuenta"));
				tc.setDescripcion(rs.getString("tc.Descripcion"));
				c.setFechaCreacion(rs.getDate("c.FechaCreacion"));
				c.setCbu(rs.getString("c.CBU"));
				c.setSaldo(rs.getFloat("c.Saldo"));
				u.setIdUsuario(rs.getInt("u.IdUsuario"));
				u.setApellido(rs.getString("u.Apellido"));
				u.setCuil(rs.getString("u.Cuil"));
				u.setGenero(g);
				u.setTipoDeUsuario(tu);
				c.setTipoDeCuenta(tc);
				c.setUsuario(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return c;
	}


	@Override
	public boolean eliminar(int id) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean estado= true;
		
		cn= new Conexion();
		cn.Open();
		
		String query= "DELETE FROM cuentas WHERE NroDeCuenta=" + id;
		try {
			estado= cn.execute(query);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return estado;	
	}
}