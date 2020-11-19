package datosImpl;

import java.sql.CallableStatement;

import datos.MovimientosDao;

public class MovimientosDaoImpl implements MovimientosDao {
	
	private Conexion cn;

	@Override
	public boolean Transferencias(float importe, int cuentaorigen, int cuentadestino, int usuarioorigen, int usuariodestino,
			String detalle) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cn = new Conexion();
		cn.Open();
		
		int sp_check = 0;
		boolean filas= false;
		try {
		
		CallableStatement sp = cn.connection.prepareCall("{call prMovTransferencias(?,?,?,?,?,?)}");
		sp.setFloat(1, importe);
		sp.setInt(2, cuentaorigen);	
		sp.setInt(3, cuentadestino);
		sp.setInt(4, usuarioorigen);
		sp.setInt(5, usuariodestino);
		sp.setString(6, detalle);
		
		 sp_check = sp.executeUpdate();
	
		if( sp_check == 1)
		{
			filas = true;
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
	
		return filas;
	}


	
	
}
