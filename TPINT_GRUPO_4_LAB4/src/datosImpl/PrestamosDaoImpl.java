package datosImpl;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import datos.PrestamosDao;
import entidad.Cuentas;
import entidad.EstadosDePrestamo;
import entidad.Generos;
import entidad.Prestamos;
import entidad.TipoDeCuentas;
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



	public List<Prestamos> ObtenerPrestamos(int IdUsuario) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Prestamos> list =new ArrayList<Prestamos>();
		cn= new Conexion();
		cn.Open();
		

		try {
			System.out.println("INICIO DE TODO PROCESO PRESTAMOSDAOIMPL:  " + IdUsuario);

			
			
			ResultSet rs = cn.query("SELECT u.IdUsuario,p.idUsuario, p.IdPrestamo, u.Apellido,c.NroDeCuenta,edp.IdEstado ,c.IdTipoDeCuenta,tdc.Descripcion,c.CBU, u.Cuil,p.Fecha, p.ImporteConIntereses,p.ImporteSolicitado, p.PlazoDePago, p.ValorCuotaMensual, edp.Descripcion FROM prestamos p RIGHT JOIN usuarios u ON u.IdUsuario = p.IdUsuario LEFT JOIN estadosdeprestamo edp ON p.IdEstado = edp.IdEstado LEFT JOIN Cuentas c ON c.IdUsuario = p.IdUsuario LEFT JOIN tiposdecuentas tdc ON tdc.IdTipoDeCuenta = c.IdTipoDeCuenta WHERE u.IdUsuario = "+ IdUsuario);
			while(rs.next())
			{
				
				Prestamos p = new Prestamos();
				Cuentas c = new Cuentas();

				TipoDeCuentas tc = new TipoDeCuentas();
				Usuarios  u = new Usuarios();
				TiposDeUsuarios tu = new TiposDeUsuarios();
				Generos  g= new Generos();
				EstadosDePrestamo ep = new EstadosDePrestamo();
				
				c.setNroDeCuenta(rs.getInt("c.NroDeCuenta"));
				tc.setIdTipodeCuenta(rs.getInt("c.IdTipoDeCuenta"));
				tc.setDescripcion(rs.getString("tdc.Descripcion"));
				c.setCbu(rs.getString("c.CBU"));
				u.setIdUsuario(rs.getInt("u.IdUsuario"));
				u.setApellido(rs.getString("u.Apellido"));
				u.setCuil(rs.getString("u.Cuil"));
				
				p.setIdPrestamo(rs.getInt("p.IdPrestamo"));
				p.setFecha(rs.getDate("p.Fecha")) ;
				p.setImporteConIntereses(rs.getInt("p.ImporteConIntereses")) ;
				p.setImporteSolicitado(rs.getInt("p.ImporteSolicitado")) ;
				p.setPlazoDePago(rs.getInt("p.PlazoDePago")) ;
				p.setValorCuotaMensual(rs.getInt("p.ValorCuotaMensual"));
				ep.setIdEstado(rs.getInt("edp.IdEstado"));
				ep.setDescripcion(rs.getString("edp.Descripcion"));
				
				
				p.setEstadoPrestamo(ep);
				u.setGenero(g);
				u.setTipoDeUsuario(tu);
				c.setTipoDeCuenta(tc);
				p.setUsuario(u);
				
				list.add(p);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		System.out.println("LISTA DE LOS PRESTAMOS POR USUARIOOO " + list);
		
		return list;
	}

	@Override
	public Prestamos obtenerPrestamo(int idPrestamo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cn= new Conexion();
		cn.Open();
		
		Prestamos p =new Prestamos();
		try {
			
			ResultSet rs = cn.query("SELECT u.IdUsuario,p.idUsuario, p.IdPrestamo, u.Apellido,c.NroDeCuenta,edp.IdEstado ,c.IdTipoDeCuenta,tdc.Descripcion,c.CBU, u.Cuil,p.Fecha, p.ImporteConIntereses,p.ImporteSolicitado, p.PlazoDePago, p.ValorCuotaMensual, edp.Descripcion FROM prestamos p RIGHT JOIN usuarios u ON u.IdUsuario = p.IdUsuario LEFT JOIN estadosdeprestamo edp ON p.IdEstado = edp.IdEstado LEFT JOIN Cuentas c ON c.IdUsuario = p.IdUsuario LEFT JOIN tiposdecuentas tdc ON tdc.IdTipoDeCuenta = c.IdTipoDeCuenta WHERE p.IdPrestamo = "+ idPrestamo);
			while(rs.next())
			{
				
				Cuentas c = new Cuentas();

				TipoDeCuentas tc = new TipoDeCuentas();
				Usuarios  u = new Usuarios();
				TiposDeUsuarios tu = new TiposDeUsuarios();
				Generos  g= new Generos();
				EstadosDePrestamo ep = new EstadosDePrestamo();
				
				c.setNroDeCuenta(rs.getInt("c.NroDeCuenta"));
				tc.setIdTipodeCuenta(rs.getInt("c.IdTipoDeCuenta"));
				tc.setDescripcion(rs.getString("tdc.Descripcion"));
				c.setCbu(rs.getString("c.CBU"));
				u.setIdUsuario(rs.getInt("u.IdUsuario"));
				u.setApellido(rs.getString("u.Apellido"));
				u.setCuil(rs.getString("u.Cuil"));
				
				p.setIdPrestamo(rs.getInt("p.IdPrestamo"));
				p.setFecha(rs.getDate("p.Fecha")) ;
				p.setImporteConIntereses(rs.getInt("p.ImporteConIntereses")) ;
				p.setImporteSolicitado(rs.getInt("p.ImporteSolicitado")) ;
				p.setPlazoDePago(rs.getInt("p.PlazoDePago")) ;
				p.setValorCuotaMensual(rs.getInt("p.ValorCuotaMensual"));
				ep.setIdEstado(rs.getInt("edp.IdEstado"));
				ep.setDescripcion(rs.getString("edp.Descripcion"));
				
				
				p.setEstadoPrestamo(ep);
				u.setGenero(g);
				u.setTipoDeUsuario(tu);
				c.setTipoDeCuenta(tc);
				p.setUsuario(u);
				
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		
		return p;
	}

	@Override
	public boolean modificar(Prestamos prestamo) {

		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean estado= true;
		
		cn= new Conexion();
		cn.Open();
		System.out.println("INICIO prestamo.getEstadoPrestamo().getIdEstado()  " + prestamo.getEstadoPrestamo().getIdEstado());
		System.out.println("INICIO prestamo.getIdPrestamo():  " + prestamo.getIdPrestamo());
		String query= "UPDATE `tp_banco`.`prestamos` SET `IdEstado` = '"+ prestamo.getEstadoPrestamo().getIdEstado()+"' WHERE IdPrestamo =" + prestamo.getIdPrestamo();
		try {
			estado= cn.execute(query);
			
			System.out.println("INICIO DE estado:  " + estado);

			
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
