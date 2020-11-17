package entidad;

public class TiposDeUsuarios {


	private int IdTipoDeUsuario;
	private String DescripcionTipoDeUsuario;
	
	public TiposDeUsuarios()
	{
		
	}
	
	public TiposDeUsuarios(int IdTipoDeUsuario, String DescripcionTipoDeUsuario)
	{
		this.IdTipoDeUsuario= IdTipoDeUsuario;
		this.DescripcionTipoDeUsuario= DescripcionTipoDeUsuario;
	}

	public int getIdTipoDeUsuario() {
		return IdTipoDeUsuario;
	}

	public String getDescripcionTipoDeUsuario() {
		return DescripcionTipoDeUsuario;
	}

	public void setIdTipoDeUsuario(int idTipoDeUsuario) {
		IdTipoDeUsuario = idTipoDeUsuario;
	}

	public void setDescripcionTipoDeUsuario(String descripcionTipoDeUsuario) {
		DescripcionTipoDeUsuario = descripcionTipoDeUsuario;
	}	


	@Override
	public String toString() {
		return "TiposDeUsuarios [IdTipoDeUsuario=" + IdTipoDeUsuario + ", DescripcionTipoDeUsuario="
				+ DescripcionTipoDeUsuario + "]";
	}
	
}
