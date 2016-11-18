package tp.pr3.control;

import tp.pr3.logica.MovimientoInvalido;
import tp.pr3.logica.Partida;

/** 
 * Interfaz que contiene los comandos de la aplicación.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */

public interface Comando {
	
	/** 
	 * Ejecuta el comando que el usuario quiere ejecutar.
	 * @param partida - Partida sobre la que se ejecuta el comando.
	 * @param controlador - Controla la ejecución del comando.
	 * @return true si todo fue bien. Se devuelve false si el comando no puede ejecutarse.
	 * @throws MovimientoInvalido
	 */
	public abstract boolean ejecuta(Partida partida, Controlador controlador) throws MovimientoInvalido;
	
	
	/** 
	 * Analiza el comando por partes hasta que encuentra el comando que se quiere ejecutar.
	 * @param cmd - Comando que se quiere ejecutar.
	 * @return Objeto Comando que se utiliza para ejecutar el comando. Devuelve null si no lo ha encontrado. 
	 */
	public abstract Comando analiza(String cmd);
	
	
}
