package tp.pr3.logica;

/**
 * Clase abstracta que contiene los métodos de comprobación de los juegos Conecta 4.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public abstract class ReglasCuatroEnRaya implements ReglasJuego {
	
	private Ficha turno;
	
	/**
	 * Comprueba si se produce 4 en raya en horizontal.
	 * @param x - Número de columna (1..ancho)
	 * @param y - Número de fila (1..alto)
	 * @return True si se ha completado el 4 en raya horizontal, false si no se ha completado.
	 */
	protected boolean comprobarHorizontal(int x, int y, Tablero t) {		
		boolean comprueba = false;
			
		//Comprueba si el jugador que tiene el turno ha colocado una ficha en la posición del último movimiento y
		//a partir de ahí comprueba una a una las fichas de su misma fila. Si hay 4 en raya devuelve true.		
		comprueba = t.getCasilla(x + 1, y) == turno
				&& t.getCasilla(x + 2, y) == turno
				&& t.getCasilla(x + 3, y) == turno;

		if (!comprueba) {
			comprueba = t.getCasilla(x - 1, y) == turno
					&& t.getCasilla(x + 1, y) == turno
					&& t.getCasilla(x + 2, y) == turno;
		}

		if (!comprueba) {
			comprueba = t.getCasilla(x - 1, y) == turno
					&& t.getCasilla(x - 2, y) == turno
					&& t.getCasilla(x + 1, y) == turno;
		}

		if (!comprueba) {
			comprueba = t.getCasilla(x - 1, y) == turno
					&& t.getCasilla(x - 2, y) == turno
					&& t.getCasilla(x - 3, y) == turno;
		}

		return comprueba;
	}

	/**
	 * Comprueba si se produce 4 en raya en vertical.
	 * @param x - Número de columna (1..ancho)
	 * @param y - Número de fila (1..alto)
	 * @return True si se ha completado el 4 en raya vertical, false si no se ha completado.
	 */
	protected boolean comprobarVertical(int x, int y, Tablero t) {
		boolean comprueba = false;

		//Comprueba si el jugador que tiene el turno ha colocado una ficha en la posición del último movimiento y
		//a partir de ahí comprueba una a una las fichas de su misma columna. Si hay 4 en raya devuelve true.		
		comprueba = t.getCasilla(x, y - 1) == turno
				&& t.getCasilla(x, y - 2) == turno
				&& t.getCasilla(x, y - 3) == turno;

		if (!comprueba) {
			comprueba = t.getCasilla(x, y + 1) == turno
					&& t.getCasilla(x, y - 1) == turno
					&& t.getCasilla(x, y - 2) == turno;
		}

		if (!comprueba) {
			comprueba = t.getCasilla(x, y + 1) == turno
					&& t.getCasilla(x, y + 2) == turno
					&& t.getCasilla(x, y - 1) == turno;
		}

		if (!comprueba) {
			comprueba = t.getCasilla(x, y + 1) == turno
					&& t.getCasilla(x, y + 2) == turno
					&& t.getCasilla(x, y + 3) == turno;
		}

		return comprueba;
	}

	/**
	 * Comprueba si se produce 4 en raya por la diagonal ascendente hacia la
	 * izquierda o por la diagonal descendente hacia la derecha.
	 * @param x - Número de columna (1..ancho)
	 * @param y - Número de fila (1..alto)
	 * @return True si se ha completado el 4 en raya diagonal ascendente hacia la izquierda
	 *  o descendente hacia la derecha, false si no se ha completado.
	 */
	protected boolean comprobarDiagonalIzquierda(int x, int y, Tablero t) {
		boolean comprueba = false;

		//Comprueba si el jugador que tiene el turno ha colocado una ficha en la posición del último movimiento y
		//a partir de ahí comprueba una a una las fichas de la diagonal. Si hay 4 en raya devuelve true.
		comprueba = t.getCasilla(x + 1, y - 1) == turno
				&& t.getCasilla(x + 2, y - 2) == turno
				&& t.getCasilla(x + 3, y - 3) == turno;

		if (!comprueba) {
			comprueba = t.getCasilla(x - 1, y + 1) == turno
					&& t.getCasilla(x + 1, y - 1) == turno
					&& t.getCasilla(x + 2, y - 2) == turno;
		}

		if (!comprueba) {
			comprueba = t.getCasilla(x - 1, y + 1) == turno
					&& t.getCasilla(x - 2, y + 2) == turno
					&& t.getCasilla(x + 1, y - 1) == turno;
		}

		if (!comprueba) {
			comprueba = t.getCasilla(x - 1, y + 1) == turno
					&& t.getCasilla(x - 2, y + 2) == turno
					&& t.getCasilla(x - 3, y + 3) == turno;
		}

		return comprueba;
	}

	/**
	 * Comprueba si se produce 4 en raya por la diagonal ascendente hacia la
	 * derecha o por la diagonal descendete hacia la izquierda.
	 * @param x - Número de columna (1..ancho)
	 * @param y - Número de fila (1..alto)
	 * @return True si se ha completado el 4 en raya diagonal ascendete hacia la derecha 
	 * o descendente hacia la izquierda, false si no se ha completado.
	 */
	protected boolean comprobarDiagonalDerecha(int x, int y, Tablero t) {
		boolean comprueba = false;
		
		//Comprueba si el jugador que tiene el turno ha colocado una ficha en la posición del último movimiento y
		//a partir de ahí comprueba una a una las fichas de la diagonal. Si hay 4 en raya devuelve true.
		comprueba = t.getCasilla(x + 1, y + 1) == turno
				&& t.getCasilla(x + 2, y + 2) == turno
				&& t.getCasilla(x + 3, y + 3) == turno;

		if (!comprueba) {
			comprueba = t.getCasilla(x - 1, y - 1) == turno
					&& t.getCasilla(x + 1, y + 1) == turno
					&& t.getCasilla(x + 2, y + 2) == turno;
		}

		if (!comprueba) {
			comprueba = t.getCasilla(x - 2, y - 2) == turno
					&& t.getCasilla(x - 1, y - 1) == turno
					&& t.getCasilla(x + 1, y + 1) == turno;
		}

		if (!comprueba) {
			comprueba = t.getCasilla(x - 3, y - 3) == turno
					&& t.getCasilla(x - 2, y - 2) == turno
					&& t.getCasilla(x - 1, y - 1) == turno;
		}

		return comprueba;
	}
	
	/**
	 * Comprueba si se realizan todas las posibilidades de 4 en raya.
	 * @param columna - Número de columna (1...ancho)
	 * @param fila - Número de fila (1...alto)
	 * @param ficha - turno
	 * @return True si realiza las comprobaciones de 4 en raya, si no false.
	 */
	protected boolean comprobar4enraya(int columna, int fila, Ficha ficha, Tablero t) {
		turno = ficha;
		
		//Comprueba si hay 4 en raya en horizontal, vertical o diagonal y si lo hay devuelve true.
		if ((comprobarHorizontal(columna, fila, t)) || 
				(comprobarVertical(columna, fila, t)) || 
				(comprobarDiagonalDerecha(columna, fila, t)) || 
				(comprobarDiagonalIzquierda(columna, fila, t))) {
			return true;
		}
		return false;
	}

}
