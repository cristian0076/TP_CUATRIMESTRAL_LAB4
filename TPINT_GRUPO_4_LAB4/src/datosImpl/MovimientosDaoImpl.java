package datosImpl;

import java.util.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.MovimientosDao;
import entidad.Movimientos;
import entidad.TipoDeCuentas;
import entidad.TiposDeMovimientos;
import entidad.Usuarios;


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


	@Override
	public ArrayList<Movimientos> ListarMovimientos(int nrodecuenta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cn= new Conexion();
		cn.Open();
		
		
		ArrayList<Movimientos> list = new ArrayList<Movimientos>();
		String query = "SELECT TM.DescripcionTipoDeMovimiento, M.Fecha, M.Detalle, M.Importe FROM tp_banco.movimientos M INNER JOIN tiposdemovimientos TM ON M.IdTipoMovimiento = TM.IdTipoMovimiento WHERE M.NroDeCuenta = "+nrodecuenta+" ORDER BY M.Fecha DESC";
		
		
		try {
			
			ResultSet rs= cn.query(query);
			
			while(rs.next())
			{
				Movimientos m = new Movimientos();
				Usuarios u = new Usuarios();
				TiposDeMovimientos tm = new TiposDeMovimientos();
				
				tm.setDescripcionTipoDeMovimiento(rs.getString(1));
				m.setFecha(rs.getDate(2));
				m.setDetalle(rs.getString(3));
				m.setImporte(rs.getFloat(4));
				m.setTipoDeMovimiento(tm);
				m.setUsuario(u);
				
				list.add(m);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			cn.close();
		}
		
		
		return list;
	}


	@Override
	public ArrayList<Movimientos> ListarMovimientos(int tipodemovimiento, Date fechadesde, Date fechahasta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cn= new Conexion();
		cn.Open();
		
		
		java.sql.Date date1 = new java.sql.Date(fechadesde.getTime());
		java.sql.Date date2 = new java.sql.Date(fechahasta.getTime());
		
		ArrayList<Movimientos> list = new ArrayList<Movimientos>();
		String query;
		
		if(tipodemovimiento != 0)
		{
			
			 query = "SELECT TM.DescripcionTipoDeMovimiento, M.Fecha, M.Detalle, M.Importe FROM tp_banco.movimientos M INNER JOIN tiposdemovimientos TM ON M.IdTipoMovimiento = TM.IdTipoMovimiento WHERE TM.IdTipoMovimiento = "+tipodemovimiento+" and M.Fecha >= '"+date1+" 00:00:00' and M.Fecha <= '"+date2+" 23:59:59' ORDER BY M.Fecha DESC";
		}else {
			 query = "SELECT TM.DescripcionTipoDeMovimiento, M.Fecha, M.Detalle, M.Importe FROM tp_banco.movimientos M INNER JOIN tiposdemovimientos TM ON M.IdTipoMovimiento = TM.IdTipoMovimiento WHERE  M.Fecha >= '"+date1+" 00:00:00' and M.Fecha <= '"+date2+" 23:59:59' ORDER BY M.Fecha DESC";
		}
			
		System.out.println(query);

		try {
			
			ResultSet rs= cn.query(query);
			
			while(rs.next())
			{
				Movimientos m = new Movimientos();
				Usuarios u = new Usuarios();
				TiposDeMovimientos tm = new TiposDeMovimientos();
				
				tm.setDescripcionTipoDeMovimiento(rs.getString(1));
				m.setFecha(rs.getDate(2));
				m.setDetalle(rs.getString(3));
				m.setImporte(rs.getFloat(4));
				m.setTipoDeMovimiento(tm);
				m.setUsuario(u);
				
				list.add(m);
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
