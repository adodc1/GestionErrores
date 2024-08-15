package gestionErrores.controlVersiones;

/**
 * CONTROL DE VERSIONES
 * 
 */
public final class Version {

	private static final String version = "01.00.00";
	private static final String appName = "ElzerLogger";

	/**
	 * <p>
	 * <b>Mayor:</b> Cambia cuando hay modificaciones significativas o incompatibles
	 * con versiones anteriores.
	 * </p>
	 * <p>
	 * <b>Menor:</b> Se incrementa cuando se añaden nuevas funcionalidades que son
	 * compatibles con versiones anteriores.
	 * </p>
	 * <p>
	 * <b>Revisión o Parche:</b> Se incrementa cuando se corrigen errores o se hacen
	 * pequeñas mejoras que no afectan la compatibilidad.
	 * </p>
	 * 
	 * @return la version
	 */
	public static final String getVersion() {
		return " ( " + version + "v ) ";
	}

	/**
	 * Nombre de la libreria
	 * 
	 * @return Nombre de la libreria
	 */
	public static final String getAppName() {
		return appName;
	}

	/**
	 * Nombre de la libreria y versión
	 * 
	 * @return cadena con el nombre de la libreria y la version.
	 */
	public static final String getApplication() {
		return getAppName() + getVersion();
	}

}
