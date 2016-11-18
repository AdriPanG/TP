package tp.pr4.control;

import tp.pr4.comandos.Comando;
import tp.pr4.comandos.GestionComandos;
import tp.pr4.gui.VistaConsola;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Partida;
import tp.pr4.observer.Observer;

/**
 * Clase que controla la ejecución de la partida, pidiendo al usuario qué quiere
 * ir haciendo, hasta que la partida termina.
 * 
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class ControladorConsole {
	
	private Partida partida;
	private Comando comando;
	private FactoriaTipoJuego factoria;
	private Jugador jugador1;
	private Jugador jugador2;
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param p  - Partida a la que se jugará.	 
	 */
	public ControladorConsole(Partida partida, FactoriaTipoJuego f) {
		this.partida = partida;
		this.factoria = f;
	}	

	public void addObserver(Observer o){
		partida.addObserver(o);
	}

	public FactoriaTipoJuego getFactoria() {
		return factoria;
	}


	public void setFactoria(FactoriaTipoJuego factoria) {
		this.factoria = factoria;
		actualizaJugadores(null);
	}

	public void actualizaJugadores(Ficha ficha) {
		if (ficha == Ficha.BLANCA) {
			setJugador1(factoria.creaJugadorHumanoConsola());
		} else if (ficha == Ficha.NEGRA) {
			setJugador2(factoria.creaJugadorHumanoConsola());
		} else if (ficha == null) {
			setJugador1(factoria.creaJugadorHumanoConsola());
			setJugador2(factoria.creaJugadorHumanoConsola());
		}
	}
	
	public Jugador getJugador1() {
		return jugador1;
	}

	public void setJugador1(Jugador jugador1) {
		this.jugador1 = jugador1;
	}

	public Jugador getJugador2() {
		return jugador2;
	}

	public void setJugador2(Jugador jugador2) {
		this.jugador2 = jugador2;
	}
	
	public String muestraTurno(){
		return partida.muestraTurno();
	}

	public void aniadeOperacion(String operacion, VistaConsola vistaConsola) {
		comando = GestionComandos.analiza(operacion);
		
		
		try{
			if(comando != null){
				comando.ejecuta(partida, ControladorConsole.this);
			}else{
				throw new MovimientoInvalido("No te entiendo.");  ///ESTO NO PUEDE IR AQUI
			}
		}catch(MovimientoInvalido e){
			System.err.println(e.getMessage());
		}		
	}	
	
	public StringBuilder muestraTablero(){
		return partida.getTablero().imprimeTablero();
	}
	
	public boolean partidaTerminada(){
		return partida.isTerminada();		
	}

	public void muestraResultado(){
		partida.resultado();
	}
}
