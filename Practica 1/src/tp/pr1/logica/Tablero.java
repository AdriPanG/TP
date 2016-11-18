package tp.pr1.logica;

/**
 * Clase que contiene todo lo relacionado con el tablero del Conecta 4. 
 * @author Adrian Panadero Gonz�lez
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class Tablero {

	private Ficha[][] tablero;
	private int ancho = 1;
	private int alto = 1;

	/**
	 * Construye un tablero vac�o.
	 * @param tx - Tama�o en la coordenada x (o n�mero de columnas).
	 * @param ty - Tama�o en la coordenada y (o n�mero de filas).
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
	 * M�todo para obtener el alto del tablero
	 * @return N�mero de filas del tablero.
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * M�todo para obtener el ancho del tablero.
	 * @return N�mero de columnas del tablero.
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * M�todo para acceder a la informaci�n de una casilla o celda concreta.
	 * @param x - N�mero de columna (1..ancho)
	 * @param y - N�mero de fila (1..alto)
	 * @return Informaci�n de la casilla. Si la casilla no es v�lida, devuelve Ficha.VACIA
	 */
	public Ficha getCasilla(int x, int y) {
		if((x <= getAncho()) && (x > 0) && (y <= getAlto()) && (y > 0)){
			return tablero[x-1][y-1];
		}		
		return Ficha.VACIA;
	}

	/**
	 * Permite especificar el nuevo contenido de una casilla. Tambi�n puede utilizarse para quitar una ficha
	 * @param x - N�mero de columna (1..ancho)
	 * @param y - N�mero de fila (1..alto)
	 * @param color Color de la casilla. Si se indica Ficha.VACIA, la celda quedar� sin ficha.
	 */
	public void setCasilla(int x, int y, Ficha color) {
		if((x <= getAncho()) && (x > 0) && (y <= getAlto()) && (y > 0)){
			tablero[x-1][y-1] = color;
		}	
	}
	
	
	/**
	 * Devuelve la casilla vac�a que se encuentre lo m�s abajo posible de la columna pedida del tablero.
	 * @param y - N�mero de columna (1..ancho)
	 * @return fila Fila en la que se encuentra la casilla vac�a. Si no hay una casilla vac�a devuelve -1.
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
	 * M�todo que muestra el tablero.
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