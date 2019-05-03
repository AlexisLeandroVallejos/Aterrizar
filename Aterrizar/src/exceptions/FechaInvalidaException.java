package exceptions;

public class FechaInvalidaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FechaInvalidaException() {
		super("La fecha ingresada es invalida.");
	}
}
