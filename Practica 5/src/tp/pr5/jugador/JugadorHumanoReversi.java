package tp.pr5.jugador;

import tp.pr5.jugador.Jugador;
import tp.pr5.logica.Ficha;
import tp.pr5.movimiento.Movimiento;
import tp.pr5.movimiento.MovimientoInvalido;
import tp.pr5.logica.Partida;
import tp.pr5.logica.Tablero;

/** 
 * Jugador humano del juego Reverti.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class JugadorHumanoReversi implements Jugador{
	
	/** 
	 * Constructor de la clase.
	 */
	public JugadorHumanoReversi(){
		
	}
	
	/** 
	 * Devuelve el siguiente movimiento a efectuar por el jugador.
	 * @param tab - Estado del tablero donde poner.
	 * @param color - Color de la ficha que hay que colocar.
	 * @return Movimiento que desea ejecutar. 
	 * @throws MovimientoInvalido 
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color, Partida partida)
			throws MovimientoInvalido {
		
		return partida.pedirDatos();
	}

}

