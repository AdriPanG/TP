package tp.pr5.factoria;

import tp.pr5.jugador.Jugador;
import tp.pr5.jugador.JugadorAleatorioConecta4;
import tp.pr5.jugador.JugadorHumanoConecta4;
import tp.pr5.logica.Ficha;
import tp.pr5.movimiento.Movimiento;
import tp.pr5.movimiento.MovimientoConecta4;
import tp.pr5.reglas.ReglasConecta4;
import tp.pr5.reglas.ReglasJuego;


/** 
 * Implementación de la factoría para el juego Conecta 4. Los métodos devuelven los objetos capaces de jugar a ese juego.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class FactoriaConecta4 implements FactoriaTipoJuego{

	/**
	 * Construye el objeto Jugador capaz de jugar al juego concreto de forma aleatoria.
	 * @return Objeto jugador que juega de forma aleatoria.
	 */
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioConecta4();
	}

	/** 
	 * Construye el objeto Jugador que se encarga de preguntar al usuario por consola
	 * el siguiente movimiento a realizar.
	 * @return Objeto Jugador que utilizar para preguntar al usuario el siguiente movimiento.
	 */
	public Jugador creaJugadorHumanoConsola() {		
		return new JugadorHumanoConecta4();
	}

	/** 
	 * Contruye un movimiento para el juego concreto. Es posible que la implementación
	 * no utilice todos los parámetros.
	 * @param col - Columna donde se quiere colocar.
	 * @param fila - Fila donde se quiere colocar. En juegos como Conecta 4 o Complica
	 * este parámetro no se utilizará.
	 * @param color - Color de la ficha que se pondrá.
	 * @return Objet de tipo Moviemiento capaz de ejecutar el movimiento para el juego concreto.
	 */
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {		
		return new MovimientoConecta4(col, color);
	}

	/** 
	 * Construye las reglas del juego concreto.
	 * @return El Objeto que implementa las reglas del juego que representamos.
	 */
	public ReglasJuego creaReglas() {
		return new ReglasConecta4();
	}

}
