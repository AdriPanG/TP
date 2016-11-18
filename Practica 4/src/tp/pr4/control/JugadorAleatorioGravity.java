package tp.pr4.control;

import java.util.Random;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoGravity;
import tp.pr4.logica.Tablero;

/** 
 * Jugador que juega de forma aleatoria a Gravity. Simplemente elige una posición en el tablero,
 * comprobando antes que esa casilla está vacía (se podrá poner).
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class JugadorAleatorioGravity implements Jugador{

	/** 
	 * Devuelve el siguiente movimiento a efectuar por el jugador.
	 * @param tab - Estado del tablero donde poner.
	 * @param color - Color de la ficha que hay que colocar.
	 * @return Movimiento que desea ejecutar. 
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
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
