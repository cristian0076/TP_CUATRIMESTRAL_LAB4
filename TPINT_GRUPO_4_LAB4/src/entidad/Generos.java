package entidad;

public class Generos {

	private int IdGenero;
	private String DescripcionGenero;
	
	
	public  Generos() {
	
	}
	
	public Generos(int IdGenero, String DescripcionGenero)
	{
		this.IdGenero= IdGenero;
		this.DescripcionGenero= DescripcionGenero;
		
	}
	

	public int getIdGenero() {
		return IdGenero;
	}


	public String getDescripcionGenero() {
		return DescripcionGenero;
	}


	public void setIdGenero(int idGenero) {
		IdGenero = idGenero;
	}

	public void setDescripcionGenero(String descripcionGenero) {
		DescripcionGenero = descripcionGenero;
	}	



	@Override
	public String toString() {
		return "Generos [IdGenero=" + IdGenero + ", DescripcionGenero=" + DescripcionGenero + "]";
	}

}
