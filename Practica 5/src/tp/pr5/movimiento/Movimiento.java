package tp.pr5.movimiento;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

/**
 * Clase que representa el movimiento de un jugador. 
 * Tiene un método para ejecutar el movimiento sobre la partida, y otro para deshacerlo. 
 * Es una clase abstracta; habrá una clase no abstracta por cada tipo de juego soportado.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public abstract class Movimiento {
	
	protected int donde; //Columna donde se realiza el movimiento.
	protected Ficha color; //Color de la ficha.
	protected int fila; //Fila donde estara la ficha.
	
	/** 
	 * Constructor de la clase.
	 * @param donde - Columna donde se realiza el movimiento.
	 * @param color - Color de la ficha.
	 */
	public Movimiento (int donde, Ficha color) {
		this.donde = donde;
		this.color = color;
	}
	
	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como parámetro. 
	 * Se puede dar por cierto que tablero recibido sigue las reglas del tipo de juego al que pertenece el movimiento. 
	 * En caso contrario, el comportamiento es indeterminado.
	 * @param tab - Tablero sobre el que ejecutar el movimiento
	 * @return true si todo fue bien. Se devuelve false si el movimiento no puede ejecutarse sobre el tablero.
	 * @throws MovimientoInvalido 
	 */
	public abstract boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido;
	
	
	/**
	 * Devuelve el color del jugador al que pertenece el movimiento. (Puede hacerse abstracto)
	 * @return Color del jugador (coincide con el pasado al constructor).
	 */
	public Ficha getJugador(){
		return color;		
	}
	
	
	/**
	 * Deshace el movimiento en el tablero recibido como parámetro. Se puede dar por cierto que el movimiento se ejecutó sobre ese tablero; en caso contrario, el comportamiento es indeterminado.
	 * Por lo tanto, es de suponer que el método siempre funcionará correctamente.
	 * @param tab - Tablero de donde deshacer el movimiento.
	 */
	public abstract void undo(Tablero tab);

	/**
	 * Devuelve la columna en la que se ha realizado el movimiento.
	 * @return Columna del movimiento (coincide con el pasado al constructor).
	 */
	public int getDonde() {
		return donde;
	}
	
	/** 
	 * Devuelve la fila en la que se ha realizado el movimiento.
	 * @return Fila del movimiento (coincide con el pasado al constructor).
	 */
	public int getFila(){
		return fila;
	}
	
}
