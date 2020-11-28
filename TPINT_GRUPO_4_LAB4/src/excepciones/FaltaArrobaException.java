package excepciones;

@SuppressWarnings("serial")
public class FaltaArrobaException extends RuntimeException{
	
	public FaltaArrobaException()
	{}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Error en la validación de mail. Falta arroba";
	}	
}
