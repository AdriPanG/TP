package tp.pr5.comandos;

import tp.pr5.control.ControladorConsole;

/** 
 * Clase que contiene el comando Deshacer. Quita la ficha del último movimiento que se ha ejecutado.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class ComandoDeshacer implements Comando{

	/** 
	 * Ejecuta el comando Deshacer.
	 * @param controlador - Controla la ejecución del comando.
	 * @return true si todo fue bien. Se devuelve false si el comando no puede ejecutarse.
	 */
	public boolean ejecuta(ControladorConsole control) {
		control.deshacer();
		return true;
	}

	/** 
	 * Analiza el comando por partes hasta que encuentra el comando que se quiere ejecutar.
	 * @param cmd - Comando que se quiere ejecutar.
	 * @return Objeto Comando que se utiliza para ejecutar el comando. Devuelve null si no lo ha encontrado. 
	 */
	public Comando analiza(String cmd) {
		
		if(cmd.equalsIgnoreCase("deshacer")){
			return new ComandoDeshacer();
		}else return null;
		
	}

}
