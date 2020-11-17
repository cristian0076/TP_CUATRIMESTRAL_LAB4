package entidad;

public class TiposDeMovimientos {

		private int IdTipoMovimiento;
		private String DescripcionTipoDeMovimiento;
		
		public TiposDeMovimientos()
		{
			
		}
		
		
		public TiposDeMovimientos(int IdTipoMovimiento, String DescripcionTipoDeMovimiento)
		{
			this.IdTipoMovimiento= IdTipoMovimiento;
			this.DescripcionTipoDeMovimiento= DescripcionTipoDeMovimiento;
		}
		
		public int getIdTipoMovimiento() {
			return IdTipoMovimiento;
		}
		public void setIdTipoMovimiento(int idTipoMovimiento) {
			IdTipoMovimiento = idTipoMovimiento;
		}
		public String getDescripcionTipoDeMovimiento() {
			return DescripcionTipoDeMovimiento;
		}
		public void setDescripcionTipoDeMovimiento(String descripcionTipoDeMovimiento) {
			DescripcionTipoDeMovimiento = descripcionTipoDeMovimiento;
		}


		
		@Override
	public String toString() {
		return "TiposDeMovimientos [IdTipoMovimiento=" + IdTipoMovimiento + ", DescripcionTipoDeMovimiento="
				+ DescripcionTipoDeMovimiento + "]";
	}


}
