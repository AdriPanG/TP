package tp.pr5.jugador;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Partida;
import tp.pr5.logica.Tablero;
import tp.pr5.movimiento.Movimiento;
import tp.pr5.movimiento.MovimientoInvalido;


/** 
 * Interfaz que representa un jugador; cuando el controlador quiere saber qué movimiento ejecutar
 * a continuación, le pregunta al jugador que tiene el turno.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public interface Jugador {
	
	/** 
	 * Devuelve el siguiente movimiento a efectuar por el jugador.
	 * @param tab - Estado del tablero donde poner.
	 * @param color - Color de la ficha que hay que colocar.
	 * @param partida - Partida del juego.
	 * @return Movimiento que desea ejecutar. 
	 * @throws MovimientoInvalido
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color, Partida partida) throws MovimientoInvalido;
	
}
