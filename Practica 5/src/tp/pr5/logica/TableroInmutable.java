package tp.pr5.logica;


/** 
 * Interface que contiene un TableroInmutable
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 *
 */
public interface TableroInmutable {
	/** 
	 * Método que devuelve el ancho del tablero.
	 * @return columnas del tablero.
	 */
	int getColumnas();
	
	/** 
	 * Método que devuelve el alto del tablero.
	 * @return filas del tablero.
	 */
	int getFilas();

	/** 
	 * Método que devuelve una casilla del tablero.
	 * @param col - Columna de una casilla del tablero.
	 * @param fila - Fila de una casilla del tablero.
	 * @return casilla que hay en esos parámetros.
	 */
	Ficha getCasilla(int col, int fila);
}
