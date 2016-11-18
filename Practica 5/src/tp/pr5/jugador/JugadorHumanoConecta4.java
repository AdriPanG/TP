package tp.pr5.jugador;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Partida;
import tp.pr5.logica.Tablero;
import tp.pr5.movimiento.Movimiento;
import tp.pr5.movimiento.MovimientoInvalido;
/** 
 * Jugador humano del juego Conecta4.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class JugadorHumanoConecta4 implements Jugador{

	 
	/** 
	 * Constructor de la clase.
	 */
	public JugadorHumanoConecta4(){
		
	}
	
	/** 
	 * Devuelve el siguiente movimiento a efectuar por el jugador.
	 * @param tab - Estado del tablero donde poner.
	 * @param partida - Partida del juego.
	 * @param color - Color de la ficha que hay que colocar.
	 * @return Movimiento que desea ejecutar. 
	 * @throws MovimientoInvalido 
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color, Partida partida) {
		
		return partida.pedirDatos();	
	}

}
