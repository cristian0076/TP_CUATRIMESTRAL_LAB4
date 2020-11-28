package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.PrestamosPorCuotaDao;
import entidad.PrestamoPorCuota;
import entidad.Prestamos;

public class PrestamosPorCuotaDaoImpl implements PrestamosPorCuotaDao {

	private Conexion cn;
	
	public PrestamosPorCuotaDaoImpl()
	{
		
	}
	
	@Override
	public List<PrestamoPorCuota> ObtenerCuotas(int IdPrestamo) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cn= new Conexion();
		cn.Open();
		
		List<PrestamoPorCuota> list =new ArrayList<PrestamoPorCuota>();
		
		try {
			
			ResultSet rs = cn.query("select IdPrestamo,NroCuota,FechaPago,Estado from tp_banco.prestamoporcuota where IdPrestamo = "+ IdPrestamo);
			while(rs.next())
			{
				PrestamoPorCuota c = new PrestamoPorCuota();
				Prestamos p = new Prestamos();

			
				p.setIdPrestamo(rs.getInt(1));
				c.setPrestamo(p);
				c.setNroCuota(rs.getInt(2));
				c.setFechaPago(rs.getDate(3));
				c.setEstado(rs.getBoolean(4));
			
				
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

}
