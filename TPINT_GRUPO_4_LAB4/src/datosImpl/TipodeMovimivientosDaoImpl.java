package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.TipodeMovimientosDao;
import entidad.TiposDeMovimientos;

public class TipodeMovimivientosDaoImpl implements TipodeMovimientosDao {

	private Conexion cn;
	
	@Override
	public List<TiposDeMovimientos> listartiposDeMovimientos() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cn= new Conexion();
		cn.Open();
		
		
		ArrayList<TiposDeMovimientos> list = new ArrayList<TiposDeMovimientos>();
		String query = "SELECT IdTipoMovimiento, DescripcionTipoDeMovimiento FROM tp_banco.tiposdemovimientos";
		
		try {
			
		ResultSet rs = cn.query(query);
		
		while(rs.next())
		{
			TiposDeMovimientos tm = new TiposDeMovimientos();
			
			tm.setIdTipoMovimiento(rs.getInt(1));
			tm.setDescripcionTipoDeMovimiento(rs.getString(2));
			
			list.add(tm);
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		finally {
			cn.close();
		}
		
		
		return list;
	}

}
