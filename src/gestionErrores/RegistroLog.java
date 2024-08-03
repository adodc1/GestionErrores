package gestionErrores;

import java.io.*;
import java.text.*;
import java.util.logging.*;
import java.util.logging.Formatter;

/**
 * <p>
 * Libreria dedicada a la gestion de logs.
 * </p>
 * 
 * @author Dani
 * @version 1.0
 */
public class RegistroLog {

	/**
	 * <p>
	 * Inicializa un descriptor de fichero para escribir en un conjunto de archivos.
	 * Cuando (aproximadamente) se haya escrito el limite indicado en un archivo, se
	 * abrira otro archivo. La salida recorrera un conjunto de archivos volviendo al
	 * primero una vez finalizado el grupo de ficheros.
	 * </p>
	 * <p>
	 * El recuento debe ser al menos 1.
	 * </p>
	 * 
	 * @param fileName   : String : Nombre y ruta de los ficheros que contienen logs
	 * @param fileSize   : int : Tamaño maximo de cada fichero de log.
	 * @param fileAmount : int : Cantidad de ficheros que se pueden crear antes de
	 *                   volver al primer fichero.
	 */
	public static void setupLogger(String fileName, int fileSize, int fileAmount) {

		// Desactiva la consola de logs para que solo se grave en el fichero.
		LogManager.getLogManager().reset();

		try {
			fichero = new FileHandler(fileName, fileSize, fileAmount, true);
			fichero.setFormatter(new MiFormato());
			log.addHandler(fichero);

		} catch (IOException ex) {
			Logger.getLogger(RegistroLog.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * <p>
	 * Registrar un mensaje informativo, especificando la clase y el metodo de
	 * origen.
	 * </p>
	 * 
	 * @param clase   : String : Nombre de la <b>clase</b> que emitio la solicitud
	 *                de registro
	 * @param metodo  : String : Nombre del <b>metodo</b> que emitio la solicitud de
	 *                registro.
	 * @param mensaje : String : La cadena message.
	 */
	public static void infoLogger(String clase, String metodo, String mensaje) {
		log.logp(MiLevel.INFO, clase, metodo, mensaje);
	}

	/**
	 * <p>
	 * Registrar un mensaje de error, especificando la clase y el método de origen.
	 * </p>
	 * 
	 * @param clase   : String : Nombre de la <b>clase</b> que emitió la solicitud
	 *                de registro
	 * @param metodo  : String : Nombre del <b>metodo</b> que emitió la solicitud de
	 *                registro.
	 * @param mensaje : String : La cadena message.
	 */
	public static void errorLogger(String clase, String metodo, String mensaje) {
		log.logp(MiLevel.ERR, clase, metodo, mensaje);
	}

	/**
	 * <p>
	 * Registrar un mensaje de error de tipo Throwable, especificando la clase y el
	 * metodo de origen.
	 * </p>
	 * 
	 * @param clase   : String :nombre de la <b>clase</b> que emitio la solicitud de
	 *                registro
	 * @param metodo  : String : nombre del <b>metodo</b> que emitio la solicitud de
	 *                registro.
	 * @param mensaje : Throwable : La cadena message (o una clave en el catalogo de
	 *                mensajes)
	 */
	public static void errorLogger(String clase, String metodo, Throwable mensaje) {
		log.logp(MiLevel.ERR, clase, metodo, stackTrace2String(mensaje));
	}

	/**
	 * <p>
	 * Registrar un mensaje de tipo Warning, especificando la clase y el metodo de
	 * origen.
	 * </p>
	 * 
	 * @param clase   : String :nombre de la <b>clase</b> que emitio la solicitud de
	 *                registro
	 * @param metodo  : String : nombre del metodo que emitió la solicitud de
	 *                registro.
	 * @param mensaje : String : La cadena message
	 */
	public static void warningLogger(String clase, String metodo, String mensaje) {
		log.logp(MiLevel.WARNING, clase, metodo, mensaje);
	}

	/**
	 * <p>
	 * Registrar un mensaje de tipo Warning, especificando la clase y el metodo de
	 * origen.
	 * </p>
	 * 
	 * @param clase   : String :nombre de la <b>clase</b> que emitio la solicitud de
	 *                registro
	 * @param metodo  : String : nombre del método que emitio la solicitud de
	 *                registro.
	 * @param mensaje : Throwable : La cadena message.
	 */
	public static void warningLogger(String clase, String metodo, Throwable mensaje) {
		log.logp(MiLevel.WARNING, clase, metodo, stackTrace2String(mensaje));
	}

	/**
	 * <p>
	 * Imprime este objeto Throwable y su seguimiento hacia el descriptor de
	 * impresión especificado.
	 * </p>
	 * 
	 * @param mensaje : Throwable : objeto que contiene la ruta y mensaje.
	 * @return String : Mensaje en forma de texto.
	 */
	private static String stackTrace2String(Throwable mensaje) {
		StringWriter sw = new StringWriter();
		mensaje.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	/**
	 * <p>
	 * Cierra el descriptor de ficheros.
	 * </p>
	 */
	public static void closeLogger() {
		if (fichero != null) {
			fichero.close();
		}
	}

	private static final Logger log = Logger.getLogger(RegistroLog.class.getName());
	private static FileHandler fichero;
	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %1$tL <%4$-3s> %5$s %n");
	}

}

/**
 * <p>
 * Fromatea la fecha y hora del mensaje de error.
 * </p>
 * 
 * @author Dani
 * @version 1.0
 * @see Formato de salida para el fichero de fallos.
 */
class MiFormato extends Formatter {

	@Override
	public String format(LogRecord record) {
		DateFormat simple = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss,SSS");
		return simple.format(record.getMillis()) + ":" + record.getSourceClassName() + "."
				+ record.getSourceMethodName() + ":<" + record.getLevel() + ">:" + record.getMessage() + "\n";
	}

}

/**
 * <p>
 * Establece nuevas categorias para los niveles de fallos.
 * </p>
 * 
 * @author Dani
 * @version 1.0
 */
class MiLevel extends Level {

	private static final long serialVersionUID = 1L;

	public static MiLevel ERR = new MiLevel("ERR", 950);
	public static MiLevel WARNING = new MiLevel("WRN", 900);
	public static MiLevel INFO = new MiLevel("INF", 800);
	public static MiLevel DEBUG = new MiLevel("DEBUG", 710);

	/**
	 * <p>
	 * Crea un nivel con un nombre y un valor entero determinados y un nombre de
	 * recurso de localización determinados.
	 * </p>
	 * 
	 * @param name               : String : El nombre del nivel.
	 * @param value              : int : Un valor entero para el nivel.
	 * @param resourceBundleName : String : Nombre de un paquete de recursos que se
	 *                           utilizará para localizar el nombre determinado.
	 * @throws NullPointerException Si el nombre es null
	 */
	protected MiLevel(String name, int value, String resourceBundleName) {
		super(name, value, resourceBundleName);
	}

	/**
	 * <p>
	 * Crea un nivel con un nombre y un valor entero determinados y un nombre de
	 * recurso de localización determinados.
	 * </p>
	 * 
	 * @param name  : String : El nombre del nivel.
	 * @param value : int : Un valor entero para el nivel.
	 * @throws NullPointerException Si el nombre es null
	 */
	protected MiLevel(String name, int value) {
		super(name, value);
	}
}
