package datosImpl;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import datos.PrestamosDao;
import entidad.EstadosDePrestamo;
import entidad.Generos;
import entidad.Prestamos;
import entidad.TiposDeUsuarios;
import entidad.Usuarios;

public class PrestamosDaoImpl implements PrestamosDao {

	private Conexion cn;
	
	
	
	public PrestamosDaoImpl() {
	}

	@Override
	public boolean insertar(Prestamos p) {
		
		Calendar fecha = Calendar.getInstance();
		int anio = fecha.get(Calendar.YEAR); 
		int mes = fecha.get(Calendar.MONTH); 
		int dia = fecha.get(Calendar.DATE); 
		mes=mes+1;
		String f=  Integer.toString(anio)+"-"+Integer.toString(mes)+"-"+Integer.toString(dia);
		
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");

		Date date;
		try {
			date = d.parse(f);
			p.setFecha(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.sql.Date date2 = new java.sql.Date(p.getFecha().getTime());
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean estado= true;
		
		cn= new Conexion();
		cn.Open();
		String query= "INSERT INTO `tp_banco`.`prestamos` (`Fecha`, `ImporteConIntereses`, `ImporteSolicitado`, `PlazoDePago`, `ValorCuotaMensual`, `IdUsuario`, `IdEstado`,`NroDeCuenta`) VALUES ('"+date2+"', '"+p.getImporteConIntereses()+"', '"+p.getImporteSolicitado()+"', '"+p.getPlazoDePago()+"', '"+p.getValorCuotaMensual()+"', '"+p.getUsuario().getIdUsuario()+"', '"+p.getEstadoPrestamo().getIdEstado()+"','"+p.getCuenta().getNroDeCuenta()+"');";
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
	public List<Prestamos> ListarPrestamosxUsuario(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cn = new Conexion();
		cn.Open();
		
		List<Prestamos> list = new ArrayList<Prestamos>();
		try {
			
			ResultSet rs = cn.query("select P.IdPrestamo As `Nro de prestamo`,P.Fecha,P.ImporteSolicitado As `Importe solicitado`,P.ImporteConIntereses As `Importe a pagar`,P.PlazoDePago As `Cantidad de cuotas`,P.IdEstado,EDP.Descripcion from Prestamos As P Inner join estadosdeprestamo As EDP ON EDP.IdEstado = P.IdEstado WHERE P.IdUsuario = "+id);
			while(rs.next())
			{
				Prestamos p = new Prestamos();
				EstadosDePrestamo e = new EstadosDePrestamo();
				
				
				p.setIdPrestamo(rs.getInt(1));
				p.setFecha(rs.getDate(2));
				p.setImporteSolicitado(rs.getFloat(3));
				p.setImporteConIntereses(rs.getFloat(4));
				p.setPlazoDePago(rs.getInt(5));
				e.setIdEstado(rs.getInt(6));
				e.setDescripcion(rs.getString(7));
				p.setEstadoPrestamo(e);
				
					list.add(p);
				
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
