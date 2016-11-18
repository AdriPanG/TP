package tp.pr4.control;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoComplica;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Partida;
import tp.pr4.logica.Tablero;

/** 
 * Jugador humano del juego Complica.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class JugadorHumanoComplica implements Jugador{
	
	private Partida partida;
	private java.util.Scanner in; // Scanner que se utiliza para que el jugador 
	                             //elija la columna en la que quiere que caiga la ficha
	
	
	/** 
	 * Constructor de la clase.
	 * @param in
	 */
	public JugadorHumanoComplica(/*java.util.Scanner in*/){
		//this.in = in;
	}
	
	/** 
	 * Devuelve el siguiente movimiento a efectuar por el jugador.
	 * @param tab - Estado del tablero donde poner.
	 * @param color - Color de la ficha que hay que colocar.
	 * @return Movimiento que desea ejecutar. 
	 * @throws MovimientoInvalido 
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		partida.pedirDatos();
		/*System.out.print("Introduce la columna: ");
		int col = in.nextInt();
		in.nextLine();		
		
		Movimiento mov = new MovimientoConecta4(col, color);*/
		
		return null;
	}

}
