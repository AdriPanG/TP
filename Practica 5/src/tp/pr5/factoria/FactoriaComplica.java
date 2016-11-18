package tp.pr5.factoria;


import tp.pr5.jugador.Jugador;
import tp.pr5.jugador.JugadorAleatorioComplica;
import tp.pr5.jugador.JugadorHumanoComplica;
import tp.pr5.logica.Ficha;
import tp.pr5.movimiento.Movimiento;
import tp.pr5.movimiento.MovimientoComplica;
import tp.pr5.reglas.ReglasComplica;
import tp.pr5.reglas.ReglasJuego;

/** 
 * Implementación de la factoría para el juego del Complica. Los métodos devuelven los objetos capaces de jugar a ese juego.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class FactoriaComplica implements FactoriaTipoJuego {

	/** 
	 * Construye el objeto de la entrada que utilizará el objeto para preguntar al usuario.
	 * @param in - Scanner de la entrada que utilizará el objeto para preguntar al usuario.
	 * @return Objeto Jugador que utilizar para preguntar al usuario el siguiente movimiento.
	 */
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioComplica();
	}

	/** 
	 * Construye el objeto Jugador que se encarga de preguntar al usuario por consola
	 * el siguiente movimiento a realizar.
	 * @return Objeto Jugador que utilizar para preguntar al usuario el siguiente movimiento.
	 */
	public Jugador creaJugadorHumanoConsola() {
		return new JugadorHumanoComplica();
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
		return new MovimientoComplica(col, color);
	}

	/** 
	 * Construye las reglas del juego concreto.
	 * @return El Objeto que implementa las reglas del juego que representamos.
	 */
	public ReglasJuego creaReglas() {
		return new ReglasComplica();
	}

}
