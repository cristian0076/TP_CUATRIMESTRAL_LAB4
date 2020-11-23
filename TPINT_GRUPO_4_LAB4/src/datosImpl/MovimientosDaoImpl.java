package datosImpl;

import datos.MovimientosDao;


public class MovimientosDaoImpl implements MovimientosDao {
	
	private Conexion cn;
		
	
	@Override
	public boolean Transferencias(float importe, int cuentaorigen, int cuentadestino, int usuarioorigen, int usuariodestino,
			String detalle, int tipomovimiento) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cn = new Conexion();
		cn.Open();
		
		boolean sp_check = false;
		
		String sp_transferencias = "call prMovTransferencias "+"("+importe+","+cuentaorigen+","+cuentadestino+",'"+usuarioorigen+"','"+usuariodestino+"','"+detalle+"',"+tipomovimiento+")";
		
		try {
		
				
		if(cn.execute(sp_transferencias)==true)
		{
			sp_check = true;
		}
	
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		return sp_check;
	}
}
