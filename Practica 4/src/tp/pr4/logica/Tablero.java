package tp.pr4.logica;


/**
 * Almacena la información de un tablero rectangular. El tamaño se fija en el momento de la construcción, 
 * y contiene métodos para acceder a la información de cada celda y para colocar fichas. 
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class Tablero {

	private Ficha[][] tablero; //Array bidimensional del tablero.
	private int ancho; //Ancho del tablero.
	private int alto; //Alto del tablero.
	private StringBuilder cadena; //Cadena para tablero

	/**
	 * Construye un tablero vacío.
	 * @param tx - Tamaño en la coordenada x (o número de columnas).
	 * @param ty - Tamaño en la coordenada y (o número de filas).
	 */
	public Tablero(int tx, int ty) {
		
		if (tx > 0 && ty > 0) {
			//Añaade los valores de ancho y alto del tablero.
			ancho = tx;
			alto = ty;
		} else {
			ancho = 1;
			alto = 1;
		}
			
			tablero = new Ficha[ancho][alto];		
			//Recorre el tablero y lo inicializa a Ficha.Vacia.
			for (int j = 0; j < ancho; j++) {
				for (int i = 0; i < alto; i++) {
					this.tablero[j][i] = Ficha.VACIA;
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
		if(compruebaTablero(x, y)){
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
		if(compruebaTablero(x, y)){
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
			//Busca una casilla en la columna y que está vacía.
			while (fila > -1) {
				if (tablero[y-1][fila] == Ficha.VACIA) {
					return fila+1;
				}else {
					fila--;
				}
			}
		}
		return 0;		
	}
	
	/**
	 * Convierte una ficha en una cadena. 
	 * @param j - Ancho del tablero
	 * @param i - Alto del tablero
	 * @param cadena.append(arg0) - Cadena que contiene las fichas del tablero en cada posición.
	 * @return cadena Los caracteres que se mostrarÃ¡n en el tablero dependiendo de la ficha.
	 */
	private String convertirFicha (int j, int i) {
		if (tablero[j][i] == Ficha.VACIA){
			return " ";
		}
		else if (tablero[j][i] == Ficha.BLANCA) {
			return "O";
		}
		else if (tablero[j][i] == Ficha.NEGRA) {
			return "X";
		}
		return "";
	}
	
	private boolean compruebaTablero (int x, int y) {
		if((x <= getAncho()) && (x > 0) && (y <= getAlto()) && (y > 0)) {
			return true;
		}
		return false;
	}

	/**
	 * Método que muestra el tablero.
	 */
	
	public StringBuilder imprimeTablero() {
		
		cadena = new StringBuilder();
		
		//Crea el tablero
		for(int i=0; i < alto; i++){ 
			cadena.append("|");
			for(int j=0; j < ancho; j++){
				cadena.append(convertirFicha(j, i));
			}
			cadena.append("|\n");
		}
		
		//Crea la base del tablero
		cadena.append("+");
		for(int i=0; i < ancho; i++){
			cadena.append("-");
		}
		cadena.append("+\n" + " ");
		
		//Crea los números de las columnas
		int numcol = 1;
		for(int j=0; j < ancho; j++) {
			if(numcol == 10) {
				numcol = 0;
			}
			cadena.append(numcol);
			numcol++;
		}
		cadena.append("\n");
		
		return cadena;
	}
	
}