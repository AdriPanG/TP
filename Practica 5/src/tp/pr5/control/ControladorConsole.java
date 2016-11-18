package tp.pr5.control;

import tp.pr5.comandos.Comando;
import tp.pr5.comandos.GestionComandos;
import tp.pr5.factoria.FactoriaComplica;
import tp.pr5.factoria.FactoriaConecta4;
import tp.pr5.factoria.FactoriaGravity;
import tp.pr5.factoria.FactoriaReversi;
import tp.pr5.factoria.FactoriaTipoJuego;
import tp.pr5.gui.VistaConsola;
import tp.pr5.jugador.Jugador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Partida;
import tp.pr5.logica.TipoJuego;
import tp.pr5.movimiento.Movimiento;
import tp.pr5.movimiento.MovimientoInvalido;
import tp.pr5.observer.Observer;

/**
 * Clase que controla la ejecución de la partida por consola, pidiendo al usuario qué quiere
 * ir haciendo, hasta que la partida termina. * 
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 4
 */
public class ControladorConsole {
	
	private Partida partida; //Partida del juego.
	private Comando comando; //Comando ha ejecutar.
	private FactoriaTipoJuego factoria; //Factoria de la partida.
	private Jugador jugador1; //Jugador 1 de la partida.
	private Jugador jugador2; //Jugador 2 de la partida.
	private TipoJuego tipoJuego; //Tipo de juego al que se va a jugar.
	
	
	/** 
	 * Constructor de la clase.
	 * @param partida - Partida a la que se jugará.
	 * @param f       - Factoria del tipo de juego al que se va a jugar. 
	 * @param tipoJuego - Tipo de juego al que se va a jugar. 
	 */
	public ControladorConsole(Partida partida, FactoriaTipoJuego f, TipoJuego tipoJuego) {
		this.partida = partida;
		this.factoria = f;
		this.tipoJuego = tipoJuego;
		jugador1 = factoria.creaJugadorHumanoConsola();
		jugador2 = factoria.creaJugadorHumanoConsola();
	}	

	
	
	/** 
	 * Método que añade el Observador de la partida.
	 * @param o - Observer que informa de los cambios de la partida.
	 */
	public void addObserver(Observer o){
		partida.addObserver(o);
	}
	
	
	
	/** 
	 * Método que devuelve el juego de la partida.
	 * @return El juego al que se está jugando.
	 */
	public TipoJuego getTipoJuego() {
		return tipoJuego;
	}

	/**
	 * Método que cambia el tipo de juego.
	 * @param tipo - Tipo de juego.
	 */
	public void setTipoJuego(TipoJuego tipo) {
		this.tipoJuego = tipo;
	}
	
	
	/** 
	 * Método que devuelve la factoria del tipo de juego al que se va a jugar.
	 * @return factoria - La factoria del tipo de juego de la partida.
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
	 * Método que muestra el turno de la partida.
	 * @return el turno de la partida.
	 */
	public String muestraTurno(){
		return partida.muestraTurno();
	}

	
	
	/** 
	 * Método que ejecuta el comando introducido.
	 * @param operacion - El comando que se desea ejecutar.
	 * @param vistaConsola - La vista de la consola.
	 * @return true si el comando se puede ejecutar
	 * @throws MovimientoInvalido
	 */
	public void aniadeOperacion(VistaConsola vistaConsola) throws MovimientoInvalido {				
		comando.ejecuta(ControladorConsole.this);
	}
	
	public boolean analizaOperacion(String operacion, VistaConsola vistaConsola) {
		comando = GestionComandos.analiza(operacion);		
		
		if(comando != null){
			return true;
		}
		return false;	
	}
	
	
	/** 
	 * Método que muestra el tablero de la partida.
	 * @return el tablero de la partida.
	 */
	public StringBuilder muestraTablero(){
		return partida.getTablero().imprimeTablero();
	}
	
	
	
	/** 
	 * Método que comprueba si la partida ha terminado.
	 * @return si ha terminado la partida.
	 */
	public boolean partidaTerminada(){
		return partida.isTerminada();		
	}

	
	
	/** 
	 * Método que muestra el resultado de la partida.
	 */
	public void muestraResultado(){
		partida.resultado();
	}
	
	
	
	/** 
	 * Método que coge el turno del jugador al que le toca.
	 * @return el turno de la partida.
	 */
	public Ficha turno(){
		return partida.getTurno();
	}
	
	
	
	/** 
	 * Método que deshace un movimiento de la partida.
	 */
	public void deshacer(){
		partida.undo();
	}
	
	
	
	/** 
	 * 	Método que reinicia la partida.
	 */
	public void reiniciar(){			
		partida.reset(factoria.creaReglas());		
	}
	
	
	
	/** 
	 * 	Método que realiza un movimiento en el tablero.
	 * @throws MovimientoInvalido
	 */
	public void poner() throws MovimientoInvalido{
		Movimiento mov = null;
		if(turno() == Ficha.BLANCA){
			mov = jugador1.getMovimiento(partida.getTablero(), turno(), partida);
		}else if(turno() == Ficha.NEGRA){
			mov = jugador2.getMovimiento(partida.getTablero(), turno(), partida);
		}	
		
		partida.ejecutaMovimiento(mov);
	}

	
	
	/** 
	 * 	Método que selecciona a qué juego se va a jugar.
	 * @param tamX - Ancho del tablero.
	 * @param tamY - Alto del tablero.
	 */
	public void jugar(int tamX, int tamY, String juego){
			
		if(juego.equalsIgnoreCase("c4")){
			factoria = new FactoriaConecta4();
			setTipoJuego(TipoJuego.CONECTA4);
		}else if(juego.equalsIgnoreCase("co")){
			factoria = new FactoriaComplica();
			setTipoJuego(TipoJuego.COMPLICA);
		}else if(juego.equalsIgnoreCase("gr")){
			if(tamX > 0 && tamY > 0){
				factoria = new FactoriaGravity(tamX, tamY);
			}else{
				tamX = 1;
				tamY = 1;
				factoria = new FactoriaGravity(tamX, tamY);
			}
			setTipoJuego(TipoJuego.GRAVITY);
		} else if(juego.equalsIgnoreCase("rv")){
			factoria = new FactoriaReversi();
			setTipoJuego(TipoJuego.REVERSI);
		}
			
		reiniciar();		
	}
	
	
	
	/** 
	 * 	Método que selecciona qué jugador es (aleatorio, humano).
	 * @param ficha - Jugador de la partida (blancas, negras).
	 * @param modo - Qué tipo de jugador es (humano, aleatorio).
	 */
	public void jugador(String ficha, String modo){		
		
		if((ficha.equalsIgnoreCase("blancas")) && (modo.equalsIgnoreCase("aleatorio"))) {
			jugador1 = factoria.creaJugadorAleatorio();			
			
		} else if((ficha.equalsIgnoreCase("negras")) && (modo.equalsIgnoreCase("aleatorio"))) {
			jugador2 = factoria.creaJugadorAleatorio();
			
		} else if((ficha.equalsIgnoreCase("blancas")) && (modo.equalsIgnoreCase("humano"))) {
			jugador1 = factoria.creaJugadorHumanoConsola();
			
		} else if((ficha.equalsIgnoreCase("negras")) && (modo.equalsIgnoreCase("humano"))) {
			jugador2 = factoria.creaJugadorHumanoConsola();
		}
		
	}
	
	
	/**
	 * Método que tiene la información de ayuda de la consola.
	 * @return cadena de texto con la ayuda de los comandos disponibles.
	 */
	public StringBuilder ayuda(){
		StringBuilder cadena = new StringBuilder();
		cadena.append("Los comandos disponibles son:\n");
		cadena.append("\n");
		cadena.append("PONER: utilízalo para poner la siguiente ficha.\n");
		cadena.append("DESHACER: deshace el último movimiento hecho en la partida.\n");
		cadena.append("REINICIAR: reinicia la partida.\n");
		cadena.append("JUGAR [c4|co|gr|rv] [tamX tamY]: cambia el tipo de juego.\n");
		cadena.append("JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.\n");
		cadena.append("SALIR: termina la aplicación.\n");
		cadena.append("AYUDA: muestra esta ayuda.\n");
		return cadena;
	}
}
