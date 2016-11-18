package tp.pr5.movimiento;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

/**
 * Clase que implementa el movimiento para el juego del Complica. 
 * Se deben implementar los métodos abstractos de la clase padre.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class MovimientoComplica extends Movimiento{	
	
	private Ficha color_aux; //Ficha auxiliar para guardar la ficha que desaparece del tablero.
	
	/**
	 * Constructor del objeto.
	 * @param donde - Columna en la que se colocará la ficha
	 * @param color - Color de la ficha que se pondrá (o jugador que pone).
	 */
	public MovimientoComplica(int donde, Ficha color){
		super (donde, color);
		color_aux = Ficha.VACIA;
	}
	
	
	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como parámetro. 
	 * Se puede dar por cierto que tablero recibido sigue las reglas del tipo de juego al que pertenece el movimiento. 
	 * En caso contrario, el comportamiento es indeterminado.
	 * @param tab - Tablero sobre el que ejecutar el movimiento
	 * @return true si todo fue bien.Se devuelve false si el movimiento no puede ejecutarse sobre el tablero.
	 * @throws MovimientoInvalido 
	 */
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {	
		boolean ejecuta = false;
		
		if (color != Ficha.VACIA){
			if ((tab.getAncho() >= donde) && (donde > 0)){
					fila = tab.buscarFila(donde);
					//Si la fila es mayor que 0, añade la ficha al tablero.
					if (fila > 0) {
						tab.setCasilla(donde, fila, color);
						ejecuta = true;
					}
					//Añade una nueva ficha cuando la columna esta llena.
					else if (fila == 0) {
						color_aux = tab.getCasilla(donde, tab.getAlto());
						//Desplaza hacia abajo.
						for(int i = tab.getAlto(); i > 0 ;i--){							
							tab.setCasilla(donde, i, tab.getCasilla(donde, i-1));
						}
						tab.setCasilla(donde, 1, color);
						ejecuta = true;
					}
			}else{
				throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y " + tab.getAncho() + ".");	
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
			if (color_aux != Ficha.VACIA) {
				//Mueve los movimientos a una casilla superior.
				for (int i = 0; i < tab.getAlto(); i++) {
					tab.setCasilla(donde, i, tab.getCasilla(donde, i+1));
				}
				//Añade la casilla que desapareció del tablero.
				tab.setCasilla(donde, tab.getAlto(), color_aux);
			} else {
				tab.setCasilla(donde, fila, Ficha.VACIA);
			}			
	}

}
