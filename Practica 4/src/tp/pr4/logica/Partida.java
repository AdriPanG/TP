package tp.pr4.logica;

import java.util.ArrayList;

import tp.pr4.control.FactoriaTipoJuego;
import tp.pr4.observer.Observer;

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

	private Tablero tablero; // Tablero del juego.
	private Ficha turno; // Jugador que realiza la acción.
	private static boolean terminada; // Comprobado de si termina la partida o
										// no.
	private Ficha ganador; // Jugador que gana la partida.
	private Pila pila; // Pila en la que se añaden los movimientos.
	private ReglasJuego reglas; // Las reglas de los distintos tipos de juegos.
	private ArrayList<Observer> obs;

	/**
	 * Construye una partida nueva.
	 * 
	 * @since Práctica 2
	 * @param reglas
	 *            - Reglas del juego que se utilizarón durante la partida (al
	 *            menos hasta que se ejecute reset).
	 */
	public Partida(ReglasJuego reglas) {
		this.reglas = new ReglasConecta4();
		obs = new ArrayList<Observer>();
		reset(reglas);	

	}

	public void addObserver(Observer o) {
		obs.add(o);
	}

	/**
	 * Reinicia la partida en curso. Esta operación no puede deshacerse. Gracias
	 * al parámetro, permite cambiar el tipo de juego al que se juega.
	 * 
	 * @since Práctica 2
	 * @param reglas
	 *            - Las reglas del juego a utilizar a partir de ahora.
	 */
	public void reset(ReglasJuego reglas) {
		tablero = reglas.iniciaTablero();
		terminada = false;
		turno = Ficha.BLANCA;
		ganador = Ficha.VACIA;
		pila = new Pila();
		this.reglas = reglas;
		for(Observer o: obs) {
			o.onReset(tablero.getAncho(), tablero.getAlto());
		}
		
	}

	/**
	 * Ejecuta el movimiento indicado.
	 * 
	 * @since Práctica 2
	 * @param mov
	 *            - Movimiento a ejecutar. Se asume que el movimiento es
	 *            compatible con las reglas de la partida que se está jugando
	 *            (si se está jugando a Conecta 4, el tipo de movimiento es
	 *            Conecta 4, etc.). En caso contrario, el comportamiento es
	 *            indeterminado.
	 * @return true si se puede efectuar el movimiento. Es un error intentar
	 *         colocar una ficha del jugador que no tiene el turno, cuando la
	 *         partida está terminada, columna llena, ...
	 * @throws MovimientoInvalido
	 */
	public boolean ejecutaMovimiento(Movimiento mov) /*throws MovimientoInvalido*/ {
		boolean ejecuta = false;
		
		if (!isTerminada() && turno == mov.getJugador()) {
			try {
				ejecuta = mov.ejecutaMovimiento(tablero);
			} catch (MovimientoInvalido e) {
				for(Observer o: obs)
					o.onError(e.getMessage());
			}

			// Si se ejecuta el movimiento comprueba si acaba la partida o
			// cambia de turno.
			if (ejecuta == true) {
				pila.apilar(mov);
				if (ganador == Ficha.VACIA) {
					for (Observer o : obs) {
						o.onEjecutaMovimiento(tablero, turno);
					}
				} else {
					for (Observer o : obs) {
						o.onEjecutaMovimiento(tablero, turno);
					}
				}
				ganador = reglas.hayGanador(mov, tablero);
				// si hay ganador o hay tablas termina la partida, si no cambia
				// de turno y sigue jugando.
				if (ganador != Ficha.VACIA) {
					terminada = true;
					for (Observer o : obs)
						o.onResultado(ganador);
				} else if (reglas.tablas(turno, tablero)) {
					if (ganador == Ficha.VACIA) {
						terminada = true;
						for (Observer o : obs)
							o.onResultado(ganador);
					}
				} else {
					turno = cambiaTurno();
					for(Observer o: obs)
						o.onTurno(muestraTurno());					
				}
			}
		} else {
			for(Observer o: obs)
				o.onError("Movimiento inválido.");
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
		// Si la pila no está vacía deshace el último movimiento.
		if (mov != null) {
			mov.undo(tablero);
			for (Observer o: obs)
				o.onUndo(tablero);
			ejecuta = true;
			cambiaTurno();
			for (Observer o: obs)
				o.onTurno(muestraTurno());
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
	 * 
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
	 * 
	 * @return turno - turno del jugador.
	 */
	private Ficha cambiaTurno() {
		return turno = reglas.siguienteTurno(getTurno(), tablero);
	}

	public void resultado() {
		if (isTerminada()) {
			System.out.println(tablero.imprimeTablero());
			if (ganador == Ficha.BLANCA) {
				System.out.println("Ganan las blancas");
			} else if (ganador == Ficha.NEGRA) {
				System.out.println("Ganan las negras");
			} else if (ganador == Ficha.VACIA) {
				System.out.println("Partida terminada en tablas.");
			}
		}
		for (Observer o : obs)
			o.onResultado(ganador);
	}
	
	
	public void pedirDatos(Movimiento mov){
		for (Observer o : obs)
			o.onPideDatos(mov);
	}
	

}
