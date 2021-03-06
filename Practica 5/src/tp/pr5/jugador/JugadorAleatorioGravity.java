package tp.pr5.jugador;

import java.util.Random;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Partida;
import tp.pr5.logica.Tablero;
import tp.pr5.movimiento.Movimiento;
import tp.pr5.movimiento.MovimientoGravity;

/** 
 * Jugador que juega de forma aleatoria a Gravity. Simplemente elige una posición en el tablero,
 * comprobando antes que esa casilla está vacía (se podrá poner).
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class JugadorAleatorioGravity implements Jugador {

	/** 
	 * Devuelve el siguiente movimiento a efectuar por el jugador.
	 * @param tab - Estado del tablero donde poner.
	 * @param partida - Partida del juego.
	 * @param color - Color de la ficha que hay que colocar.
	 * @return Movimiento que desea ejecutar. 
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color, Partida partida) {
		
		Random rnd = new Random();
		int columna = rnd.nextInt(tab.getAncho()) + 1;
		int fila = rnd.nextInt(tab.getAlto()) + 1;
		while(tab.getCasilla(columna, fila) != Ficha.VACIA){
			columna = rnd.nextInt(tab.getAncho()) + 1;
			fila = rnd.nextInt(tab.getAlto()) + 1;
		}
		return new MovimientoGravity(columna, fila, color);
	}

}
