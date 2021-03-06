package tp.pr2.logica;


/**
 * Clase que implementa el movimiento para el juego del Conecta 4. 
 * Se deben implementar los métodos abstractos de la clase padre.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class MovimientoConecta4 extends Movimiento{	
	
	/**
	 * Constructor del objeto.
	 * @param donde - Columna en la que se colocará la ficha
	 * @param color - Color de la ficha que se pondrá (o jugador que pone).
	 */
	public MovimientoConecta4(int donde, Ficha color){
		super(donde, color);	
	}
	
	
	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como parámetro. 
	 * Se puede dar por cierto que el tablero recibido sigue las reglas del tipo de juego al que pertenece el movimiento. 
	 * En caso contrario, el comportamiento es indeterminado.
	 * @param tab - Tablero sobre el que ejecutar el movimiento
	 * @return true si todo fue bien. Se devuelve false si el movimiento no puede ejecutarse sobre el tablero.
	 */
	public boolean ejecutaMovimiento(Tablero tab) {
		boolean ejecuta = false;
		if (color != Ficha.VACIA){
			if ((tab.getAncho() >= donde) && (donde > 0)){
				//Comprueba que la columna no esta llena.
				if (tab.buscarFila(donde) != 0) {
					int fila = tab.buscarFila(donde);
					//Si la casilla esta vacía, añade el movimiento.
					if (tab.getCasilla(donde, fila) == Ficha.VACIA){	
						ejecuta = true;
						tab.setCasilla(donde, fila, color);								
					}
				}
			}
		}
		return ejecuta;
	}
	
	
	

	/**
	 * Deshace el movimiento en el tablero recibido como parámetro. 
	 * Se puede dar por cierto que el movimiento se ejecutó sobre ese tablero; 
	 * en caso contrario, el comportamiento es indeterminado.
	 * Por lo tanto, es de suponer que el método siempre funcionará correctamente.
	 * @param tab - Tablero de donde deshacer el movimiento.
	 */
	public void undo(Tablero tab) {
		int fila = tab.buscarFila(donde);
		tab.setCasilla(donde, fila+1, Ficha.VACIA);
	}

}
