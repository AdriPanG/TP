package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoGravity;
import tp.pr3.logica.MovimientoInvalido;
import tp.pr3.logica.Tablero;

/** 
* Jugador humano del juego Gravity.
* @author Adrián Panadero González
* @author Cristina Barquilla Blanco
* @version 1
*/
public class JugadorHumanoGravity implements Jugador{

	private java.util.Scanner in; // Scanner que se utiliza para que el jugador 
								  //elija la columna en la que quiere que caiga la ficha
	
	/** 
	 * Constructor de la clase.
	 * @param in
	 */
	public JugadorHumanoGravity(java.util.Scanner in){
		this.in = in;
	}
	
	
	/** 
	 * Devuelve el siguiente movimiento a efectuar por el jugador.
	 * @param tab - Estado del tablero donde poner.
	 * @param color - Color de la ficha que hay que colocar.
	 * @return Movimiento que desea ejecutar. 
	 * @throws MovimientoInvalido 
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		System.out.print("Introduce la columna: ");
		int col = in.nextInt();
		in.nextLine();
		System.out.print("Introduce la fila: ");
		int fila = in.nextInt();
		in.nextLine();
		
		Movimiento mov = new MovimientoGravity(col, fila, color);		
		
		return mov;
	}

}
