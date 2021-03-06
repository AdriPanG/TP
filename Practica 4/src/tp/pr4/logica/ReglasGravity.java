package tp.pr4.logica;


/** 
 * Implementación de las reglas del Gravity. Se deben implementar todos los métodos del interfaz,
 * además del constructor.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class ReglasGravity extends ReglasCuatroEnRaya{
	
	private int numCols;
	private int numFilas;
	/** 
	 * Constructor de la clase.
	 * @param numCols - Número de columnas del tablero.
	 * @param numFilas - Número de filas del tablero.
	 */
	public ReglasGravity(int numCols, int numFilas){
		this.numCols = numCols;
		this.numFilas = numFilas;
	}
	
	
	/**
	 * Construye el tablero que hay que utilizar para la partida, según las reglas del juego.
	 * @return Tablero a utilizar. El estado del tablero será el de inicio de la partida.
	 */
	public Tablero iniciaTablero() {		
		return new Tablero(numCols, numFilas);
	}

	/**
	 * Devuelve el color del jugador que comienza la partida.
	 * @return Color del primer jugador en colocar ficha.
	 */
	public Ficha jugadorInicial() {	
		return Ficha.BLANCA;
	}

	/**
	 * Permite averiguar si en la partida ya tenemos un ganador o no. 
	 * Devuelve el color del ganador (si lo hay).
	 * @param ultimoMovimiento - Ultimo movimiento realizado. 
	 * 							Las distintas implementaciones pueden utilizar este parámetro para optimizar la búsqueda del ganador.
	 * @param t - Estado del tablero.
	 * @return color del ganador, si lo hay. Si no lo hay, devuelve Ficha.VACIA 
	 * 			(eso NO significa necesariamente que la partida haya terminado en tablas).
	 */	
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha turno = ultimoMovimiento.getJugador();
		int donde = ultimoMovimiento.getDonde();
		int fila = ultimoMovimiento.getFila(); 
		if (comprobar4enraya(donde, fila, turno, t)) {
			return turno;
		} else if (tablas(turno, t)){
			turno = Ficha.VACIA;
		} else {
			turno = Ficha.VACIA;
		}
		return turno;
	}

	/**
	 * Devuelve true si, con el estado del tablero dado, la partida ha terminado en tablas.
	 * @param ultimoEnPoner - Jugador que acaba de poner ficha
	 * @param t - Estado del tablero
	 * @return true si la partida ha terminado sin ganador.
	 */
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		boolean tablas = true;
		for (int i = 1; i <= t.getAncho(); i++) {
			for (int j = 1; j <= t.getAlto(); j++) {
				if (t.getCasilla(i, j) == Ficha.VACIA) {
					tablas = false;
				}
			}
		}
		return tablas;
	}

	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * @param ultimoEnPoner - Último jugador en poner ficha
	 * @param t - Estado del tablero.
	 * @return Siguiente jugador que debe poner ficha.
	 */
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		if((ultimoEnPoner == Ficha.BLANCA)) {
			ultimoEnPoner = Ficha.NEGRA;
		} else if (ultimoEnPoner == Ficha.NEGRA) {
			ultimoEnPoner = Ficha.BLANCA;
		}
		
		return ultimoEnPoner;
	}

}
