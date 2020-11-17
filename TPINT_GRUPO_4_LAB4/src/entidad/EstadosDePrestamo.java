package entidad;

public class EstadosDePrestamo {

	private int IdEstado;
	private String Descripcion;
	
	public EstadosDePrestamo()
	{
		
	}
	
	public EstadosDePrestamo(int IdEstado, String Descripcion)
	{
		this.IdEstado = IdEstado;
		this.Descripcion= Descripcion;
	}

	
	public int getIdEstado() {
		return IdEstado;
	}


	public String getDescripcion() {
		return Descripcion;
	}


	public void setIdEstado(int idEstado) {
		IdEstado = idEstado;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "EstadosDePrestamo [IdEstado=" + IdEstado + ", Descripcion=" + Descripcion + "]";
	}	
	
}
