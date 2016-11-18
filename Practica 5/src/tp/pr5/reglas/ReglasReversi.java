package tp.pr5.reglas;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Flanqueo;
import tp.pr5.movimiento.Movimiento;
import tp.pr5.reglas.ReglasCuatroEnRaya;
import tp.pr5.logica.Tablero;

/**
 * Implementación de las reglas del Reversi. 
 * Se deben implementar todos los métodos del interfaz, además del constructor.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class ReglasReversi extends ReglasCuatroEnRaya {

	private int contBlancas;
	private int contNegras;
	
	/**
	 * Constructor de la clase, sin parámetros.
	 */
	public ReglasReversi () {
		super();
		contBlancas = 2;
		contNegras = 2;
	}
	
	/**
	 * Construye el tablero que hay que utilizar para la partida, según las reglas del juego.
	 * @return Tablero a utilizar. El estado del tablero será el de inicio de la partida.
	 */
	public Tablero iniciaTablero() {
		Tablero t = new Tablero(8,8);
		t.setCasilla(4, 4, Ficha.BLANCA);
		t.setCasilla(4, 5, Ficha.NEGRA);
		t.setCasilla(5, 4, Ficha.NEGRA);
		t.setCasilla(5, 5, Ficha.BLANCA);
		return t;
		
	}

	
	/**
	 * Devuelve el color del jugador que comienza la partida.
	 * @return Color del primer jugador en colocar ficha.
	 */
	public Ficha jugadorInicial() {		
		return Ficha.NEGRA;
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
		cuentaFichas(t);
		
		if(compruebaTablero(t)) {
			//Comprueba si termina en tablas, hay o no ganador y si sigue jugando.
			//Si uno de los dos se queda a 0 gana el equipo contrario.
			if (contBlancas > contNegras) {
				return Ficha.BLANCA;
			} else if (contBlancas < contNegras) {
				return Ficha.NEGRA;
			} else if (tablas(turno, t)){
				return Ficha.VACIA;
			} else {
				turno = Ficha.VACIA;
			}
		} else {
			boolean flanqueo = false;
			for (int i = 1; i <= t.getAlto(); i++) {
				for (int j = 1; j <= t.getAncho(); j++) {
					if(Flanqueo.flanqueoFichas(i, j, turno, t)) {
						flanqueo = true;
					}
				}
			}
			if (!flanqueo) {
				Ficha contraria = siguienteTurno(turno, t);
				for (int i = 1; i <= t.getAlto(); i++) {
					for (int j = 1; j <= t.getAncho(); j++) {
						if(Flanqueo.flanqueoFichas(i, j, contraria, t)) {
							flanqueo = true;
						}
					}
				}
				if (!flanqueo) {
					if (contBlancas > contNegras) {
						return Ficha.BLANCA;
					} else if (contBlancas < contNegras) {
						return Ficha.NEGRA;
					} else if (tablas(turno, t)){
						return Ficha.VACIA;
					} else {
						turno = Ficha.VACIA;
					}
				}
			}
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
		if(compruebaTablero(t)) {
			if (contBlancas == contNegras) {
				return true;
			}
		}
		return false;
	}

	
	
	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * @param ultimoEnPoner - Último jugador en poner ficha
	 * @param t - Estado del tablero.
	 * @return Siguiente jugador que debe poner ficha.
	 */
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		boolean pasarTurno = false;
		int i = 1;
		int j = 1;		
		
		if(!compruebaTablero(t)) {
			//Si el último en poner son las blancas.
			if(ultimoEnPoner == Ficha.BLANCA) {
				while (!pasarTurno && i <= t.getAlto()) {
					j = 1;
					while (!pasarTurno && j <= t.getAncho()) {
						//Se flanquea para ver si el turno siguiente (Negras) puede poner ficha y sino pasar el turno.
						pasarTurno = Flanqueo.flanqueoFichas(i, j, Ficha.NEGRA, t);
						j++;
					}
					i++;
				}
				//Si el ultimo en poner han sido las blancas y las negras no pueden poner se pasa el turno a las blancas otra vez.
				if (!pasarTurno){
					return Ficha.BLANCA;
				}
				else {
					//Si pueden poner, el turno es de las negras.
					return Ficha.NEGRA;
				}
			} else {
				//Si el último en poner son las negras.
				while (!pasarTurno && i <= t.getAlto()) {
					j = 1;
					while (!pasarTurno && j <= t.getAncho()) {
						//Se flanquea para ver si el turno siguiente (Blancas) puede poner ficha y sino pasar el turno.
						pasarTurno = Flanqueo.flanqueoFichas(i, j, Ficha.BLANCA, t);
						j++;
					}
					i++;
				}
				//Si el ultimo en poner han sido las negras y las blancas no pueden poner se pasa el turno a las negras otra vez.
				if (!pasarTurno){
					return Ficha.NEGRA;
				}
				else {
					return Ficha.BLANCA;
				}
			}
		} else {
			return Ficha.VACIA;
		}
	}
	
	/** 
	 * Método para comprobar si el tablero esta lleno o todavía quedan casillas vacías, 
	 * además de contar la cantidad de fichas blancas y negras.
	 * @param t - Tablero de la partida.
	 */
	private void cuentaFichas(Tablero t) {
		contBlancas = 0;
		contNegras = 0;
		for (int i = 1; i <= t.getAncho(); i++) {
			for (int j = 1; j <= t.getAlto(); j++) {
				if (t.getCasilla(i, j) == Ficha.BLANCA) {
					contBlancas++;
				} else if (t.getCasilla(i, j) == Ficha.NEGRA) {
					contNegras++;
				} 
			}
		}
	}
	
	private boolean compruebaTablero(Tablero t) {
		boolean terminado = true;
		for (int i = 1; i <= t.getAncho(); i++) {
			for (int j = 1; j <= t.getAlto(); j++) {
				if (t.getCasilla(i, j) == Ficha.VACIA) {
					terminado = false;
				}
			}
		}
		return terminado;
		
	}
	
	/**
	 * Devuelve el número de fichas blancas que hay en el tablero.
	 */
	public int getContB() {
		return contBlancas;
	}
	
	/**
	 * Devuelve el número de fichas negras que hay en el tablero.
	 */
	public int getContN() {
		return contNegras;
	}

}

