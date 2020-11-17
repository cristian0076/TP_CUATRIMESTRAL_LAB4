package entidad;

public class TipoDeCuentas {

		private int IdTipodeCuenta;
		private String Descripcion;
		
		public TipoDeCuentas()
		{
			
		}
		
		public TipoDeCuentas(int IdTipoDeCuentas, String Descripcion)
		{
			this.IdTipodeCuenta= IdTipoDeCuentas;
			this.Descripcion = Descripcion;
		}

		
		public String getDescripcion() {
			return Descripcion;
		}
		public void setDescripcion(String descripcion) {
			Descripcion = descripcion;
		}
		public int getIdTipodeCuenta() {
			return IdTipodeCuenta;
		}
		public void setIdTipodeCuenta(int idTipodeCuenta) {
			IdTipodeCuenta = idTipodeCuenta;
		}		


	@Override
	public String toString() {
		return "TipoDeCuentas [IdTipodeCuenta=" + IdTipodeCuenta + ", Descripcion=" + Descripcion + "]";
	}

}
