package tp.pr5.swing;

import tp.pr5.control.ControladorGui;
import tp.pr5.logica.Ficha;
import tp.pr5.movimiento.MovimientoInvalido;

/** 
 * Clase abstracta que crea el jugador automático o humano.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public abstract class JugadorSwing {
	
	protected ControladorGui c;
	protected JugadorAleatorioSwing aleat;
	protected JugadorHumanoSwing human;
	
	/** 
	 * Constructor de la clase.
	 * @param con - Controlador de la ventana gráfica.
	 */
	public JugadorSwing(ControladorGui con) {
		this.c = con;				
	}

	/** 
	 * Método abstracto que pone una ficha en el lugar donde se ha pulsado el botón.
	 * @param col - Columna del botón que se ha pulsado en el tablero.
	 * @param fila - Fila del botón que s eha pulsado en el tablero.
	 * @throws MovimientoInvalido 
	 */
	public abstract void pulsarTablero(int col, int fila);
	
	/** 
	 * Método que crea una hebra del jugador de fichas blancas.
	 */
	public abstract void turnoHebraB();
	
	/** 
	 * Método abstracto que crea una hebra del jugador de fichas negras.
	 */
	public abstract void turnoHebraN();
	
	/** 
	 * Método abstracto que interrumpe la hebra.
	 */
	public abstract void pararHebra();
	
	/** 
	 * Método que devuelve el turno del jugador ya sea humano o aleatorio.
	 * @param turno - El turno de la partida.
	 * @param modo - El modo en el que se está jugando (Aleatorio o humano).
	 * @return Devuelve el modo de juego (Aleatorio o humano).
	 */
	public JugadorSwing turnoActual(Ficha turno, String modo) {
		//Crea el jugador aleatorio.
		aleat = new JugadorAleatorioSwing(c);
		//Crea el jugador humano.
		human = new JugadorHumanoSwing(c);
		
		//Si el turno es de las blancas y el modo es automático devuelve jugador aleatorio. Humano en caso contrario
		if (turno == Ficha.BLANCA) {
			if (modo.equalsIgnoreCase("AUTOMATICO")) {	
				return aleat;
			} else {
				return human;
			}
			//Si el turno es de las negras y el modo es automático devuelve jugador aleatorio. Humano en caso contrario
		} else if (turno == Ficha.NEGRA) {
			if (modo.equalsIgnoreCase("AUTOMATICO")) {
				return aleat;
			} else {
				return human;
			}
		} else
			//Si no es el turno de ninguno de los dos devuelve null
			return null;
		
	}
}
