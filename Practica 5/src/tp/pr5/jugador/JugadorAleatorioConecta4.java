package tp.pr5.jugador;

import java.util.Random;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Partida;
import tp.pr5.logica.Tablero;
import tp.pr5.movimiento.Movimiento;
import tp.pr5.movimiento.MovimientoConecta4;
import tp.pr5.movimiento.MovimientoInvalido;

/** 
 * Jugador que juega de forma aleatoria a Conecta 4. Simplemente elige una columna aleatoria
 * en el tablero, comprobando antes que se podrá colocar en ella (no está llena).
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class JugadorAleatorioConecta4 implements Jugador{

	
	/** 
	 * Devuelve el siguiente movimiento a efectuar por el jugador.
	 * @param tab - Estado del tablero donde poner.
	 * @param partida - Partida del juego.
	 * @param color - Color de la ficha que hay que colocar.
	 * @return Movimiento que desea ejecutar. 
	 * @throws MovimientoInvalido 
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color, Partida partida) throws MovimientoInvalido {
	
		Random rnd = new Random();
		int aleatorio = rnd.nextInt(tab.getAncho()) + 1;
		while(tab.getCasilla(aleatorio, 1) != Ficha.VACIA){
			aleatorio = rnd.nextInt(tab.getAncho()) + 1;
		}
		return new MovimientoConecta4(aleatorio, color);
	}

}
