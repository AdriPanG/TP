package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoGravity;
import tp.pr4.logica.ReglasGravity;
import tp.pr4.logica.ReglasJuego;


/** 
 * Implementación de la factoría para el juego Gravity. Los métodos devuelven los objetos capaces de jugar a ese juego.
 * Dado que el tamaño del tablero de Gravity no está fijo, sino que se puede cambiar en cada partida, la factoría
 * puede configurarse con el tamaño del tablero que se quiere utilicar.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class FactoriaGravity implements FactoriaTipoJuego{
	
	private int tamX = 10;
	private int tamY = 10;
	
	public FactoriaGravity() {
		
	}
	
	public FactoriaGravity(int tamX, int tamY) {
		this.tamX = tamX;
		this.tamY = tamY;
	}

	/** 
	 * Construye el objeto de la entrada que utilizará el objeto para preguntar al usuario.
	 * @param in - Scanner de la entrada que utilizará el objeto para preguntar al usuario.
	 * @return Objeto Jugador que utilizar para preguntar al usuario el siguiente movimiento.
	 */
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity();
	}

	/** 
	 * Construye el objeto Jugador que se encarga de preguntar al usuario por consola
	 * el siguiente movimiento a realizar.
	 * @param in - Scanner de la entra que utilizará el objeto para preguntar al usuario.
	 * @return Objeto Jugador que utilizar para preguntar al usuario el siguiente movimiento.
	 */
	public Jugador creaJugadorHumanoConsola(/*Scanner in*/) {
		return new JugadorHumanoGravity(/*in*/);
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
		return new MovimientoGravity(col, fila, color);
	}

	/** 
	 * Construye las reglas del juego concreto.
	 * @return El Objeto que implementa las reglas del juego que representamos.
	 */
	public ReglasJuego creaReglas() {
		return new ReglasGravity(tamX, tamY);
	}
}
