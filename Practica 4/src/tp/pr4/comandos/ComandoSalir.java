package tp.pr4.comandos;

import tp.pr4.control.ControladorConsole;
import tp.pr4.gui.VistaConsola;
import tp.pr4.logica.Partida;

/** 
 * Clase que contiene el comando Salir. Se utiliza para salir del programa.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class ComandoSalir implements Comando{

	/** 
	 * Ejecuta el comando Salir.
	 * @param partida - Partida sobre la que se ejecuta el comando.
	 * @param controlador - Controla la ejecución del comando.
	 * @return true si todo fue bien. Se devuelve false si el comando no puede ejecutarse.
	 */
	public boolean ejecuta(Partida partida, ControladorConsole control) {
		System.exit(0);
		return true;
	}

	/** 
	 * Analiza el comando por partes hasta que encuentra el comando que se quiere ejecutar.
	 * @param cmd - Comando que se quiere ejecutar.
	 * @return Objeto Comando que se utiliza para ejecutar el comando. Devuelve null si no lo ha encontrado. 
	 */
	public Comando analiza(String cmd) {
		
		if(cmd.equalsIgnoreCase("salir")){
			return new ComandoSalir();
		}else return null;
	}

}
