package tp.pr3.logica;


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
	 * Construye un tablero vacÃ­o.
	 * @param tx - TamaÃ±o en la coordenada x (o nÃºmero de columnas).
	 * @param ty - TamaÃ±o en la coordenada y (o nÃºmero de filas).
	 */
	public Tablero(int tx, int ty) {
		
		if (tx > 0 && ty > 0) {
			//AÃ±ade los valores de ancho y alto del tablero.
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
	 * MÃ©todo para obtener el alto del tablero
	 * @return NÃºmero de filas del tablero.
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * MÃ©todo para obtener el ancho del tablero.
	 * @return NÃºmero de columnas del tablero.
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * MÃ©todo para acceder a la informaciÃ³n de una casilla o celda concreta.
	 * @param x - NÃºmero de columna (1..ancho)
	 * @param y - NÃºmero de fila (1..alto)
	 * @return InformaciÃ³n de la casilla. Si la casilla no es vÃ¡lida, devuelve Ficha.VACIA
	 */
	public Ficha getCasilla(int x, int y) {
		if(compruebaTablero(x, y)){
			return tablero[x-1][y-1];
		}
			return Ficha.VACIA;
	}

	/**
	 * Permite especificar el nuevo contenido de una casilla. TambiÃ©n puede utilizarse para quitar una ficha
	 * @param x - NÃºmero de columna (1..ancho)
	 * @param y - NÃºmero de fila (1..alto)
	 * @param color Color de la casilla. Si se indica Ficha.VACIA, la celda quedarÃ¡ sin ficha.
	 */
	public void setCasilla(int x, int y, Ficha color) {
		if(compruebaTablero(x, y)){
			tablero[x-1][y-1] = color;
		}	
	}
	
	
	/**
	 * Devuelve la casilla vacÃ­a que se encuentre lo mÃ¡s abajo posible de la columna pedida del tablero.
	 * @param y - NÃºmero de columna (1..ancho)
	 * @return fila Fila en la que se encuentra la casilla vacÃ­a. Si no hay una casilla vacÃ­a devuelve -1.
	 */
	public int buscarFila(int y) {
		if(y > 0){
			int fila = alto-1;
			//Busca una casilla en la columna y que estÃ© vacÃ­a.
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
	 * @param cadena.append(arg0) - Cadena que contiene las fichas del tablero en cada posiciÃ³n.
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
	 * MÃ©todo que muestra el tablero.
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
		
		//Crea los nÃºmeros de las columnas
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