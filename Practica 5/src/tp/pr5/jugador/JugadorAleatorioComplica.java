package tp.pr5.jugador;

import java.util.Random;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Partida;
import tp.pr5.logica.Tablero;
import tp.pr5.movimiento.Movimiento;
import tp.pr5.movimiento.MovimientoComplica;


/** 
 * Jugador que juega de forma aleatoria a Complica. En este caso cualquier columna es válida, 
 * pues si está llena, se hará hueco.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class JugadorAleatorioComplica implements Jugador {

	/** 
	 * Devuelve el siguiente movimiento a efectuar por el jugador.
	 * @param tab - Estado del tablero donde poner.
	 * @param partida - Partida del juego.
	 * @param color - Color de la ficha que hay que colocar.
	 * @return Movimiento que desea ejecutar. 
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color, Partida partida) {
		
		Random rnd = new Random();
		int aleatorio = rnd.nextInt(tab.getAncho()) + 1;
		
		return new MovimientoComplica(aleatorio, color);
	}
	
}
