package tp.pr2.logica;

/**
 * Clase para representar la información de una partida. Se encarga de almacenar
 * el estado del tablero, a quién le toca, si ya hay un ganador, etc., así como
 * la lista de movimientos que se han ido realizando para poder deshacerlos. La
 * partida guarda al menos los 10 últimos movimientos.
 * 
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class Partida {

	private Tablero tablero; //Tablero del juego.
	private Ficha turno; //Jugador que realiza la acción.
	private static boolean terminada; //Comprobado de si termina la partida o no.
	private Ficha ganador; //Jugador que gana la partida.
	private Pila pila; //Pila en la que se añaden los movimientos.
	private ReglasJuego reglas; //Las reglas de los distintos tipos de juegos.

	/**
	 * Construye una partida nueva.
	 * 
	 * @since Práctica 2
	 * @param reglas - Reglas del juego que se utilizarón durante la partida 
	 * 				(al menos hasta que se ejecute reset).
	 */
	public Partida(ReglasJuego reglas) {
		this.reglas = reglas;
		reset(reglas);

	}

	/**
	 * Reinicia la partida en curso. Esta operación no puede deshacerse. Gracias al parámetro, 
	 * permite cambiar el tipo de juego al que se juega.
	 * 
	 * @since Práctica 2
	 * @param reglas - Las reglas del juego a utilizar a partir de ahora.
	 */
	public void reset(ReglasJuego reglas) {
		tablero = reglas.iniciaTablero();
		terminada = false;
		turno = Ficha.BLANCA;
		ganador = Ficha.VACIA;
		pila = new Pila();
		this.reglas = reglas;
	}

	/**
	 * Ejecuta el movimiento indicado.
	 * 
	 * @since Práctica 2
	 * @param mov - Movimiento a ejecutar. Se asume que el movimiento es
	 *      	  compatible con las reglas de la partida que se está jugando
	 *            (si se está jugando a Conecta 4, el tipo de movimiento es
	 *            Conecta 4, etc.). En caso contrario, el comportamiento es
	 *            indeterminado.
	 * @return true si se puede efectuar el movimiento. Es un error intentar
	 *         colocar una ficha del jugador que no tiene el turno, cuando la
	 *         partida está terminada, columna llena, ...
	 */
	public boolean ejecutaMovimiento(Movimiento mov) {
		boolean ejecuta = false;
		if (!isTerminada()) {
			if (turno == mov.getJugador()) {
			ejecuta = mov.ejecutaMovimiento(getTablero());
				//Si se ejecuta el movimiento comprueba si acaba la partida o cambia de turno.
				if (ejecuta == true) {
					pila.setUndoStack(mov);
					ganador = reglas.hayGanador(mov, tablero);
					//si hay ganador o hay tablas termina la partida, si no cambia de turno y sigue jugando.
					if(ganador != Ficha.VACIA){
						terminada = true;
					}else if(reglas.tablas(turno, tablero)){
						if(ganador == Ficha.VACIA){
							terminada = true;
						}
					} else{						
						cambiaTurno();
					}
				}

			}
		}
		
		return ejecuta;
	}

	/**
	 * Deshace el último movimiento ejecutado.
	 * 
	 * @return true si se pudo deshacer.
	 */
	public boolean undo() {
		Movimiento mov = pila.desapilar();
		boolean ejecuta = false;
		//Si la pila no está vacía deshace el último movimiento.
		if (mov != null) {
			mov.undo(tablero);
			ejecuta = true;
			cambiaTurno();
		}
		
		return ejecuta;
	}

	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * 
	 * @return Color del jugador, o Ficha.VACIA si la partida ha terminado.
	 */
	public Ficha getTurno() {
		return turno;
	}

	/**
	 * Devuelve el color del ganador. Sólo válido si la partida ya ha terminado
	 * (isTerminada() == true).
	 * 
	 * @return Color del ganador. Si la partida terminó en tablas, Ficha.VACIA.
	 *         Si la partida no ha terminado aún, el resultado es indeterminado.
	 */
	public Ficha getGanador() {
		return ganador;
	}

	/**
	 * Método para saber si la partida ha concluído ya o no.
	 * 
	 * @return true si la partida ha acabado.
	 */
	public boolean isTerminada() {
		return terminada;
	}

	/**
	 * Método de acceso al tablero. Dependiendo de cómo se haga la
	 * implementación del resto de clases (principalmente de la clase
	 * Controlador), es posible que no se utilice para nada en la práctica. Sin
	 * embargo, es necesario implementarlo para poder ejecutar los test de
	 * unidad.
	 * 
	 * @return Estado del tablero actual.
	 */
	public Tablero getTablero() {
		return tablero;
	}

	/**
	 * Método que muestra el turno del jugador que tiene que poner.
	 * @return Devuelve la cadena del turno. 
	 */
	public String muestraTurno() {
		String fichas = null;
		if (getTurno() == Ficha.BLANCA) {
			fichas = "blancas";
		} else if (getTurno() == Ficha.NEGRA) {
			fichas = "negras";
		}

		return fichas;
	}

	/**
	 * Método que cambia el turno de la partida.
	 * @return turno - turno del jugador.
	 */
	private Ficha cambiaTurno() {
		if (getTurno() == Ficha.BLANCA) {
			turno = reglas.siguienteTurno(getTurno(), tablero);
		} else if (getTurno() == Ficha.NEGRA) {
			turno = reglas.siguienteTurno(getTurno(), tablero);
		}

		return turno;
	}
}
