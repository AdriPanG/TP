package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.MovimientoInvalido;
import tp.pr3.logica.Partida;

/**
 * Clase que controla la ejecución de la partida, pidiendo al usuario qué quiere
 * ir haciendo, hasta que la partida termina.
 * 
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class Controlador {

	private FactoriaTipoJuego factoria;
	private Partida partida;
	private Scanner in;
	private Comando comando;
	private Jugador jugador1;
	private Jugador jugador2;

	/**
	 * Constructor de la clase.
	 * 
	 * @param f
	 *            - Factoria del tipo de juego al que se va a jugar.
	 * @param p
	 *            - Partida a la que se jugará.
	 * @param in
	 *            - Scanner que hay que utilizar para pedirle la información al
	 *            usuario.
	 */
	public Controlador(FactoriaTipoJuego f, Partida p, Scanner in) {
		this.partida = p;
		this.factoria = f;
		this.in = in;
		jugador1 = factoria.creaJugadorHumanoConsola(in);
		jugador2 = factoria.creaJugadorHumanoConsola(in);
	}

	/**
	 * Ejecuta la partida hasta que ésta termina.
	 * 
	 * @throws MovimientoInvalido
	 */
	public void run() throws MovimientoInvalido {

		while (!partida.isTerminada()) {
			System.out.println(partida.getTablero().imprimeTablero().toString());

			System.out.println("Juegan " + partida.muestraTurno());
			System.out.print("Qué quieres hacer? ");

			String operacion = in.nextLine();

			comando = GestionComandos.analiza(operacion);

			try {
				if (comando != null) {
					comando.ejecuta(partida, Controlador.this);
				} else {
					throw new MovimientoInvalido("No te entiendo.");
				}
			} catch (MovimientoInvalido e) {
				System.err.println(e.getMessage());
			}
		}

		// Devuelve el tablero y escribe por consola cómo ha acabado la partida.
		resultado();
	}

	public FactoriaTipoJuego getFactoria() {
		return factoria;
	}

	public void setFactoria(FactoriaTipoJuego factoria) {
		this.factoria = factoria;
		actualizaJugadores(null);
	}

	public Jugador getJugador1() {
		return jugador1;
	}

	public void actualizaJugadores(Ficha ficha) {
		if (ficha == Ficha.BLANCA) {
			setJugador1(factoria.creaJugadorHumanoConsola(in));
		} else if (ficha == Ficha.NEGRA) {
			setJugador2(factoria.creaJugadorHumanoConsola(in));
		} else if (ficha == null) {
			setJugador1(factoria.creaJugadorHumanoConsola(in));
			setJugador2(factoria.creaJugadorHumanoConsola(in));
		}
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

	/**
	 * Método que muestra el resultado final de la partida.
	 */
	private void resultado() {
		if (partida.isTerminada()) {
			System.out.println(partida.getTablero().imprimeTablero());
			if (partida.getGanador() == Ficha.BLANCA) {
				System.out.println("Ganan las blancas");
			} else if (partida.getGanador() == Ficha.NEGRA) {
				System.out.println("Ganan las negras");
			} else if (partida.getGanador() == Ficha.VACIA) {
				System.out.println("Partida terminada en tablas.");
			}
		}
	}

}
