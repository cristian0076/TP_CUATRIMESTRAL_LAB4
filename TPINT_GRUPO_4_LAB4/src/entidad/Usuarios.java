package entidad;

import java.util.Date;


public class Usuarios {

		private int IdUsuario;
		private String Dni;
		private String Cuil;
		private String Nombre;
		private String Apellido;
		private Date FechaNacimiento;
		private String Email;
		private String NombreUsuario;
		private String Contrase�a;
		private Generos Genero;
		private TiposDeUsuarios TipoDeUsuario;
		private String NumeroDeTelefono;
		private String Direccion; 
		private boolean Estado;
		

   public Usuarios()
   {
	   
   }
		
   
   public Usuarios(int IdUsuario, String Dni, String Cuil, String Nombre, String Apellido, Date FechaNacimiento, String Email, String NombreUsuario,String Contrase�a,Generos Genero,TiposDeUsuarios TipoDeUsuario,String NumeroDeTelefono,String Direccion,Boolean Estado)
   {
	   this.IdUsuario=IdUsuario;
	   this.Dni= Dni;
	   this.Cuil= Cuil;
	   this.Nombre=Nombre;
	   this.Apellido= Apellido;
	   this.FechaNacimiento= FechaNacimiento;
	   this.Email= Email;
	   this.NombreUsuario= NombreUsuario;
	   this.Contrase�a= Contrase�a;
	   this.Genero= Genero;
	   this.TipoDeUsuario= TipoDeUsuario;
	   this.NumeroDeTelefono = NumeroDeTelefono;
	   this.Direccion = Direccion;
	   this.Estado= Estado;
   }
		
	public int getIdUsuario() {
		return IdUsuario;
	}

	public String getDni() {
		return Dni;
	}

	public String getCuil() {
		return Cuil;
	}

	public String getNombre() {
		return Nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public Date getFechaNacimiento() {
		return FechaNacimiento;
	}

	public String getEmail() {
		return Email;
	}
	
	public String getNombreUsuario() {
		return NombreUsuario;
	}

	public String getContrase�a() {
		return Contrase�a;
	}

	public Generos getGenero() {
		return Genero;
	}

	public TiposDeUsuarios getTipoDeUsuario() {
		return TipoDeUsuario;
	}

	public boolean getEstado() {
		return Estado;
	}

	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public void setCuil(String cuil) {
		Cuil = cuil;
	}
	
	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public void setNombreUsuario(String nombreUsuario) {
		NombreUsuario = nombreUsuario;
	}
	
	public void setContrase�a(String contrase�a) {
		Contrase�a = contrase�a;
	}

	public void setGenero(Generos genero) {
		Genero = genero;
	}
	
	public void setTipoDeUsuario(TiposDeUsuarios tipoDeUsuario) {
		TipoDeUsuario = tipoDeUsuario;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}
	
	public String getNumeroDeTelefono() {
		return NumeroDeTelefono;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setNumeroDeTelefono(String numeroDeTelefono) {
		NumeroDeTelefono = numeroDeTelefono;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
		

@Override
public String toString() {
	return "Usuarios [IdUsuario=" + IdUsuario + ", Dni=" + Dni + ", Cuil=" + Cuil + ", Nombre=" + Nombre
			+ ", Apellido=" + Apellido + ", FechaNacimiento=" + FechaNacimiento + ", Email=" + Email
			+ ", NombreUsuario=" + NombreUsuario + ", Contraseña=" + Contrase�a + ", Genero=" + Genero
			+ ", TipoDeUsuario=" + TipoDeUsuario + ", Estado=" + Estado +"]";
		}
	
}
