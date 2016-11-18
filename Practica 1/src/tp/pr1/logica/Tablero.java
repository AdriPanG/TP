package tp.pr1.logica;

/**
 * Clase que contiene todo lo relacionado con el tablero del Conecta 4. 
 * @author Adrian Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class Tablero {

	private Ficha[][] tablero;
	private int ancho = 1;
	private int alto = 1;

	/**
	 * Construye un tablero vacío.
	 * @param tx - Tamaño en la coordenada x (o número de columnas).
	 * @param ty - Tamaño en la coordenada y (o número de filas).
	 */
	public Tablero(int tx, int ty) {
		
		if (tx > 0 && ty > 0) {
			ancho = tx;
			alto = ty;
			tablero = new Ficha[ancho][alto];		
			for (int j = 0; j < ancho; j++) {
				for (int i = 0; i < alto; i++) {
					this.tablero[j][i] = Ficha.VACIA;
				}
			}
		}else {
			tablero = new Ficha[ancho][alto];
			for (int j = 0; j < ancho; j++) {
				for (int i = 0; i < alto; i++) {
					this.tablero[j][i] = Ficha.VACIA;
				}
			}
		}
	}

	/**
	 * Método para obtener el alto del tablero
	 * @return Número de filas del tablero.
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * Método para obtener el ancho del tablero.
	 * @return Número de columnas del tablero.
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Método para acceder a la información de una casilla o celda concreta.
	 * @param x - Número de columna (1..ancho)
	 * @param y - Número de fila (1..alto)
	 * @return Información de la casilla. Si la casilla no es válida, devuelve Ficha.VACIA
	 */
	public Ficha getCasilla(int x, int y) {
		if((x <= getAncho()) && (x > 0) && (y <= getAlto()) && (y > 0)){
			return tablero[x-1][y-1];
		}		
		return Ficha.VACIA;
	}

	/**
	 * Permite especificar el nuevo contenido de una casilla. También puede utilizarse para quitar una ficha
	 * @param x - Número de columna (1..ancho)
	 * @param y - Número de fila (1..alto)
	 * @param color Color de la casilla. Si se indica Ficha.VACIA, la celda quedará sin ficha.
	 */
	public void setCasilla(int x, int y, Ficha color) {
		if((x <= getAncho()) && (x > 0) && (y <= getAlto()) && (y > 0)){
			tablero[x-1][y-1] = color;
		}	
	}
	
	
	/**
	 * Devuelve la casilla vacía que se encuentre lo más abajo posible de la columna pedida del tablero.
	 * @param y - Número de columna (1..ancho)
	 * @return fila Fila en la que se encuentra la casilla vacía. Si no hay una casilla vacía devuelve -1.
	 */
	public int buscarFila(int y) {
		if(y > 0){
			int fila = alto-1;
			while (fila >= 0) {
				if (tablero[y-1][fila] == Ficha.VACIA) {
					return fila+1;
				}else {
					fila--;
				}
			}
		}
		return -1;		
	}

	/**
	 * Método que muestra el tablero.
	 */
	public String toString() {

		String cadena = "";
		  
			for(int i=0; i < alto; i++){      
				cadena += "|";
				for(int j=0; j < ancho; j++){
					if (tablero[j][i] == Ficha.VACIA){
						cadena += " ";
					}
					else if (tablero[j][i] == Ficha.BLANCA) {
						cadena += "O";
					}
					else if (tablero[j][i] == Ficha.NEGRA) {
						cadena += "X";
					}
				}
				cadena += "|\n";
			}
		cadena += "+";
		for(int i=0; i < ancho; i++){
			cadena += "-";
		}
		cadena += "+\n" + " ";
		int numcol = 1;
		for(int j=0; j < ancho; j++) {
			cadena += numcol;
			numcol++;
		}
		cadena += "\n";
		return cadena;
	}
}