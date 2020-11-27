package excepciones;

@SuppressWarnings("serial")
public class SaldoNegativoException extends RuntimeException{
	
	public SaldoNegativoException()
	{}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "No se permiten saldos negativos.";
	}	
}
