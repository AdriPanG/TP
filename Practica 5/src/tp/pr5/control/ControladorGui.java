package tp.pr5.control;

import tp.pr5.factoria.FactoriaComplica;
import tp.pr5.factoria.FactoriaConecta4;
import tp.pr5.factoria.FactoriaGravity;
import tp.pr5.factoria.FactoriaReversi;
import tp.pr5.factoria.FactoriaTipoJuego;
import tp.pr5.jugador.Jugador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Partida;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.movimiento.Movimiento;
import tp.pr5.movimiento.MovimientoInvalido;
import tp.pr5.observer.Observer;


/** 
 * Clase que controla la ejecución de la partida por ventana gráfica, pidiendo al usuario qué quiere
 * ir haciendo, hasta que la partida termina.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 4
 */
public class ControladorGui {

	private Partida partida; // Partida del juego.
	private FactoriaTipoJuego factoria;	 //Factoria de la partida.
	private Jugador jugadorB;
	private Jugador jugadorN;
	
	
	/** 
	 * Contructor de la clase.
	 * @param f - Factoria del tipo de juego al que se va a jugar. 
	 * @param p - Partida a la que se jugará.
	 */
	public ControladorGui(FactoriaTipoJuego f, Partida p) {
		this.partida = p;
		this.factoria = f;
	}

	
	/** 
	 * Método que devuelve la factoria de la clase.
	 * @return la factoria de la partida.
	 */
	public FactoriaTipoJuego getFactoria() {
		return factoria;
	}

	
	
	/** 
	 * Método para cambiar la factoria de la partida.
	 * @param factoria - factoria de la partida.
	 */
	public void setFactoria(FactoriaTipoJuego factoria) {
		this.factoria = factoria;
	}		
	
	
	
	/** 
	 * Método que añade el Observador de la partida.
	 * @param o - Observer que informa de los cambios de la partida.
	 */
	public void addObserver(Observer o){
		partida.addObserver(o);
	}
	
	
	
	/** 
	 * Método que deshace un movimiento del tablero.
	 */
	public void deshacer(){
		partida.undo();
	}
	
	
	
	/** 
	 * Método que reinicia la partida.
	 */
	public void reiniciar(){
		jugadorB = null;
		jugadorN = null;
		partida.reset(getFactoria().creaReglas());
	}
	
	
	
	/** 
	 * Método que muestra el turno de la partida.
	 * @return el turno de la partida (blancas, negras).
	 */
	public String muestraTurno(){
		return partida.muestraTurno();
	}
	
	
	
	/** 
	 * Método que realiza un movimiento en el tablero.
	 * @param col - Ancho del tablero.
	 * @param fila - Alto del tablero.
	 */
	public void poner(int col, int fila){
		if(!partida.isTerminada() && jugadorB == null) {
			Movimiento mov = factoria.creaMovimiento(col, fila, partida.getTurno());		
			partida.ejecutaMovimiento(mov);
		} else if(!partida.isTerminada() && jugadorN == null) {
			Movimiento mov = factoria.creaMovimiento(col, fila, partida.getTurno());		
			partida.ejecutaMovimiento(mov);
		}
	}
	
	
	/** 
	 * Método que realiza un movimiento aleatorio en el tablero.
	 * @throws MovimientoInvalido
	 */
	public void ponerAleatorio() throws MovimientoInvalido {
		Movimiento mov = factoria.creaJugadorAleatorio().getMovimiento(partida.getTablero(), partida.getTurno(), partida);
		partida.ejecutaMovimiento(mov);
	}
	
	
	/** 
	 * Método que realiza el cambio de juego (Conecta4, Complica, Gravity).
	 * @param object - El juego al que se desea jugar.
	 * @param col - Ancho del tablero.
	 * @param fila - Alto del tablero.
	 */
	public void cambioJuego(Object object, int col, int fila) {
		if(object.equals("Conecta4")) {
			factoria = new FactoriaConecta4();
			setFactoria(factoria);
		}
		else if(object.equals("Complica")) {
			factoria = new FactoriaComplica();
			setFactoria(factoria);
		}
		else if(object.equals("Gravity")) {
			if (col > 0 && fila > 0) {
				factoria = new FactoriaGravity(col, fila);
			} 
			setFactoria(factoria);
		}else if (object.equals("Reversi")) {
			factoria = new FactoriaReversi();
			setFactoria(factoria);
		}
		
		jugadorB = null;
		jugadorN = null;
		partida.reset(factoria.creaReglas());
	}
	
	/**
	 * Método que cambia el tipo de jugador.
	 * @param color - Jugador que se va a cambiar.
	 * @param modo - Si es automático o humano.
	 */
	public void setTipoJugador(Ficha color, String modo) {
		if (color == Ficha.BLANCA) {
			if (modo.equalsIgnoreCase("AUTOMATICO")) {
				jugadorB = factoria.creaJugadorAleatorio();
			} else {
				jugadorB = null;
			}
		} else {
			if (modo.equalsIgnoreCase("AUTOMATICO")) {
				jugadorN = factoria.creaJugadorAleatorio();
			} else {
				jugadorN = null;
			}
		}
		
	}
	
	/**
	 * Método que devuelve el jugador blancas.
	 * @return devuelve el jugador blancas.
	 */
	public Jugador getJugadorB() {
		return jugadorB;
	}
	
	/**
	 * Método que devuelve el jugador negras.
	 * @return devuelve el jugador negras.
	 */
	public Jugador getJugadorN() {
		return jugadorN;
	}
	
	/**
	 * Método que realiza el movimiento de las fichas blancas.
	 * @throws MovimientoInvalido
	 */
	public void ponerJugadorB() throws MovimientoInvalido {
		if(!partida.isTerminada() && jugadorB != null) {
			Movimiento mov = jugadorB.getMovimiento(partida.getTablero(), Ficha.BLANCA, partida);
			partida.ejecutaMovimiento(mov);
		}
	}
	
	/**
	 * Método que realiza el movimiento de las fichas negras.
	 * @throws MovimientoInvalido
	 */
	public void ponerJugadorN() throws MovimientoInvalido {
		if(!partida.isTerminada() && jugadorN != null) {
			Movimiento mov = jugadorN.getMovimiento(partida.getTablero(), Ficha.NEGRA, partida);
			partida.ejecutaMovimiento(mov);
		}
	}
	
	/**
	 * Método que devuelve el tablero inmutable.
	 * @return tablero inmutable
	 */
	public TableroInmutable getTableroInmutable() {
		return partida.getTablero();
	}
	
	public void desactivarBotones() {
		partida.desactivarBotones();
	}
	
	public void activarBotones() {
		if (!partida.isTerminada() && ((jugadorN == null) || (jugadorB == null)))
			partida.activarBotones();
	}
}
