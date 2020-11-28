package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.Reporte01Dao;
import entidad.Reporte01;

public class Reporte01DaoImpl implements Reporte01Dao {

	private Conexion cn;
	
	@Override
	public List<Reporte01> listarinforme(int mes) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cn= new Conexion();
		cn.Open();
		
		ArrayList<Reporte01> list = new ArrayList<Reporte01>();
		String query="SELECT Date(M.Fecha) AS DIA, TP.Descripcion AS DESCRIPCION,  SUM(M.Importe) AS IMPORTE  FROM MOVIMIENTOS M INNER JOIN cuentas C ON M.NroDeCuenta = C.NroDeCuenta INNER JOIN tiposdecuentas TP ON C.IdTipoDeCuenta = TP.IdTipoDeCuenta WHERE MONTH(DATE(M.FECHA))= " + mes + " AND TP.Descripcion = 'Caja de Ahorro' GROUP BY DIA, DESCRIPCION ORDER BY DIA ";
		System.out.println(query);
		try {
			ResultSet rs = cn.query(query);
			
			while(rs.next())
			{
				Reporte01 r = new Reporte01();
				
				r.setDia(rs.getDate(1));
				r.setTipoDeCuenta(rs.getString(2));
				r.setImporte(rs.getFloat(3));
			
			list.add(r);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			cn.close();
		}
		
		return list;
	}

	@Override
	public List<Reporte01> listarinforme2(int mes) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cn= new Conexion();
		cn.Open();
		
		ArrayList<Reporte01> list = new ArrayList<Reporte01>();
		String query="SELECT Date(M.Fecha) AS DIA, TP.Descripcion AS DESCRIPCION,  SUM(M.Importe) AS IMPORTE  FROM MOVIMIENTOS M INNER JOIN cuentas C ON M.NroDeCuenta = C.NroDeCuenta INNER JOIN tiposdecuentas TP ON C.IdTipoDeCuenta = TP.IdTipoDeCuenta WHERE MONTH(DATE(M.FECHA))= " + mes + " AND TP.Descripcion = 'Cuenta Corriente' GROUP BY DIA, DESCRIPCION ORDER BY DIA ";
		System.out.println(query);
		try {
			ResultSet rs = cn.query(query);
			
			while(rs.next())
			{
				Reporte01 r = new Reporte01();
				
				r.setDia(rs.getDate(1));
				r.setTipoDeCuenta(rs.getString(2));
				r.setImporte(rs.getFloat(3));
			
			list.add(r);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			cn.close();
		}
		
		return list;
	}

}
