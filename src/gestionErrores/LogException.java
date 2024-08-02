package gestionErrores;

/**
 * Excepcion generada por la libreria Log
 * 
 * @author Dani
 * @version 1.0
 */
public class LogException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Mensaje de error.
	 */
	private final String mensajeLog;

	/**
	 * Crea una excepcion sin mensaje de error.
	 */
	public LogException() {
		this.mensajeLog = "";
	}

	/**
	 * Crea una excepcion con el mensaje de error.
	 * 
	 * @param mensajeLog : String : Mensaje de error.
	 */
	public LogException(String mensajeLog) {
		this.mensajeLog = mensajeLog;
	}

	/**
	 * Retorna el mensaje de error que provoco la excepcion.
	 * 
	 * @return Devuelve el mensaje de error.
	 */
	public String getMensajeLog() {
		return this.mensajeLog;
	}

}
