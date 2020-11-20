package datosImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import datos.PrestamosDao;
import entidad.Prestamos;

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
		String query= "INSERT INTO `tp_banco`.`prestamos` (`Fecha`, `ImporteConIntereses`, `ImporteSolicitado`, `PlazoDePago`, `ValorCuotaMensual`, `IdUsuario`, `IdEstado`,`IdCuenta`) VALUES ('"+date2+"', '"+p.getImporteConIntereses()+"', '"+p.getImporteSolicitado()+"', '"+p.getPlazoDePago()+"', '"+p.getValorCuotaMensual()+"', '"+p.getUsuario().getIdUsuario()+"', '"+p.getEstadoPrestamo().getIdEstado()+"','"+p.getCuenta().getNroDeCuenta()+"');";
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
