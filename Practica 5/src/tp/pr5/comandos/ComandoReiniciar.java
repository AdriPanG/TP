package tp.pr5.comandos;

import tp.pr5.control.ControladorConsole;

/** 
 * Clase que contiene el comando Reiniciar. Reinicia la partida.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class ComandoReiniciar implements Comando{

	/** 
	 * Ejecuta el comando Reiniciar.
	 * @param controlador - Controla la ejecución del comando.
	 * @return true si todo fue bien. Se devuelve false si el comando no puede ejecutarse.
	 */
	public boolean ejecuta(ControladorConsole control) {
		control.reiniciar();		
		return true;
	}

	/** 
	 * Analiza el comando por partes hasta que encuentra el comando que se quiere ejecutar.
	 * @param cmd - Comando que se quiere ejecutar.
	 * @return Objeto Comando que se utiliza para ejecutar el comando. Devuelve null si no lo ha encontrado. 
	 */
	public Comando analiza(String cmd) {
		
		if(cmd.equalsIgnoreCase("reiniciar")){
			return new ComandoReiniciar();
		}else return null;
	
	}

}
