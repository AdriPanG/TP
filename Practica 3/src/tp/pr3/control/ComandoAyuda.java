package tp.pr3.control;

import tp.pr3.logica.Partida;

/** 
 * Clase que contiene el comando Ayuda. Muestra la ayuda del programa.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class ComandoAyuda implements Comando{

	/** 
	 * Ejecuta el comando Ayuda.
	 * @param partida - Partida sobre la que se ejecuta el comando.
	 * @param controlador - Controla la ejecución del comando.
	 * @return true si todo fue bien. Se devuelve false si el comando no puede ejecutarse.
	 */
	public boolean ejecuta(Partida partida, Controlador controlador) {
		
		StringBuilder cadena = new StringBuilder();
		cadena.append("Los comandos disponibles son:\n");
		cadena.append("\n");
		cadena.append("PONER: utilízalo para poner la siguiente ficha.\n");
		cadena.append("DESHACER: deshace el último movimiento hecho en la partida.\n");
		cadena.append("REINICIAR: reinicia la partida.\n");
		cadena.append("JUGAR [c4|co|gr] [tamX tamY]: cambia el tipo de juego.\n");
		cadena.append("JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.\n");
		cadena.append("SALIR: termina la aplicación.\n");
		cadena.append("AYUDA: muestra esta ayuda.\n");
		
		System.out.println(cadena);
		
		return true;
	}

	/** 
	 * Analiza el comando por partes hasta que encuentra el comando que se quiere ejecutar.
	 * @param cmd - Comando que se quiere ejecutar.
	 * @return Objeto Comando que se utiliza para ejecutar el comando. Devuelve null si no lo ha encontrado. 
	 */
	public Comando analiza(String cmd) {
		
		if(cmd.equalsIgnoreCase("ayuda")){
			return new ComandoAyuda();
		}else return null;
		
	}

}
