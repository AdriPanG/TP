package tp.pr5.jugador;

import java.util.Random;

import tp.pr5.jugador.Jugador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Flanqueo;
import tp.pr5.logica.Partida;
import tp.pr5.movimiento.Movimiento;
import tp.pr5.movimiento.MovimientoInvalido;
import tp.pr5.movimiento.MovimientoReversi;
import tp.pr5.logica.Tablero;

/** 
 * Jugador que juega de forma aleatoria a Reverti. Simplemente elige una columna aleatoria
 * en el tablero, comprobando antes que se podrá colocar en ella (no está llena).
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class JugadorAleatorioReversi implements Jugador{

	/** 
	 * Devuelve el siguiente movimiento a efectuar por el jugador.
	 * @param tab - Estado del tablero donde poner.
	 * @param color - Color de la ficha que hay que colocar.
	 * @return Movimiento que desea ejecutar. 
	 * @throws MovimientoInvalido 
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color, Partida partida)
			throws MovimientoInvalido {
		Random rnd = new Random();
		int columna = rnd.nextInt(tab.getAncho()) + 1;
		int fila = rnd.nextInt(tab.getAlto()) + 1;
		boolean flanqueo = false;
		while(!flanqueo){
			columna = rnd.nextInt(tab.getAncho()) + 1;
			fila = rnd.nextInt(tab.getAlto()) + 1;
			if (tab.getCasilla(columna, fila) == Ficha.VACIA && Flanqueo.flanqueoFichas(columna, fila, color, tab)) {
				flanqueo = true;
			}
		}
		return new MovimientoReversi(columna, fila, color);
	}

}

