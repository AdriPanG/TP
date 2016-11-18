package tp.pr5.swing;

import tp.pr5.control.ControladorGui;

/** 
 * Clase que crea el jugador aleatorio o humano.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 5
 *
 */
public class JugadorAleatorioSwing extends JugadorSwing {

	private HebraBlanca hB;
	private HebraNegra hN;

	
	/** 
	 * Constructor de la clase.
	 * @param con - Controlador de la ventana gráfica.
	 */
	public JugadorAleatorioSwing (ControladorGui cont) {
		super(cont);
	}
	
	/** 
	 * Método abstracto que pone una ficha en el lugar donde se ha pulsado el botón.
	 * @param col - Columna del botón que se ha pulsado en el tablero.
	 * @param fila - Fila del botón que s eha pulsado en el tablero.
	 */
	public void pulsarTablero(int col, int fila) {
		
	}

	/** 
	 * Método que crea una hebra del jugador de fichas blancas.
	 */
	public void turnoHebraB() {
		hB = new HebraBlanca(c);
		hB.start();
		
	}

	/** 
	 * Método que interrumpe la hebra.
	 */
	public void pararHebra() {
		if(!hB.isInterrupted()) hB.interrupt();	
		if (!hN.isInterrupted()) hN.interrupt();
	}

	/** 
	 * Método que crea una hebra del jugador de fichas negras.
	 */
	public void turnoHebraN() {
		hN = new HebraNegra(c);
		hN.start();
		
	}

}
