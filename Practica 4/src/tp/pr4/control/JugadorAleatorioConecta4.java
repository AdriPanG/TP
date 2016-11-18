package tp.pr4.control;

import java.util.Random;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoConecta4;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Tablero;

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
	 * @param color - Color de la ficha que hay que colocar.
	 * @return Movimiento que desea ejecutar. 
	 * @throws MovimientoInvalido 
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color) throws MovimientoInvalido {
		Random rnd = new Random();
		int aleatorio = rnd.nextInt(tab.getAncho()) + 1;
		while(tab.getCasilla(aleatorio, 1) != Ficha.VACIA){
			aleatorio = rnd.nextInt(tab.getAncho()) + 1;
		}
		return new MovimientoConecta4(aleatorio, color);
	}

}
