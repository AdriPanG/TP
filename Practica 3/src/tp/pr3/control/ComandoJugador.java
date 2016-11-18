package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Partida;

/** 
 * Clase que contiene el comando Jugador. Elige el modo en el que los dos jugadores (blancas o negras) juegan. 
 * Puede ser en modo aleatorio o humano.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class ComandoJugador implements Comando{

	private String ficha; // Color del jugador. Blancas o Negras.
	private String modo; // Modo del jugador. Aleatorio o humano.
	private Ficha colorF; // Color del jugador para actualizar los jugadores.
	
	
	/** 
	 * Ejecuta el comando Jugador.
	 * @param partida - Partida sobre la que se ejecuta el comando.
	 * @param controlador - Controla la ejecución del comando.
	 * @return true si todo fue bien. Se devuelve false si el comando no puede ejecutarse.
	 */
	public boolean ejecuta(Partida partida, Controlador controlador) {
		FactoriaTipoJuego factoria = controlador.getFactoria();
		
		if((ficha.equalsIgnoreCase("blancas")) && (modo.equalsIgnoreCase("aleatorio"))) {
			controlador.setJugador1(factoria.creaJugadorAleatorio());
		} else if((ficha.equalsIgnoreCase("negras")) && (modo.equalsIgnoreCase("aleatorio"))) {
			controlador.setJugador2(factoria.creaJugadorAleatorio());
		} else if((ficha.equalsIgnoreCase("blancas")) && (modo.equalsIgnoreCase("humano"))) {
			colorF = Ficha.BLANCA;
			controlador.actualizaJugadores(colorF);
		} else if((ficha.equalsIgnoreCase("negras")) && (modo.equalsIgnoreCase("humano"))) {
			colorF = Ficha.NEGRA;
			controlador.actualizaJugadores(colorF);
		}
		
		return true;
	}

	
	/** 
	 * Analiza el comando por partes hasta que encuentra el comando que se quiere ejecutar.
	 * @param cmd - Comando que se quiere ejecutar.
	 * @return Objeto Comando que se utiliza para ejecutar el comando. Devuelve null si no lo ha encontrado. 
	 */
	public Comando analiza(String cmd) {

		String[] funcion = cmd.split(" ");

		if (funcion[0].equalsIgnoreCase("jugador")) {
			if (funcion.length == 3) {
				if (funcion[1].equalsIgnoreCase("blancas") || funcion[1].equalsIgnoreCase("negras")) {
					if (funcion[2].equalsIgnoreCase("aleatorio") || funcion[2].equalsIgnoreCase("humano")) {
						ficha = funcion[1];
						modo = funcion[2];
						return this;
					}
				}
			}
		}
		return null;
	}

}
