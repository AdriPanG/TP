package tp.pr5.swing;

import tp.pr5.control.ControladorGui;
import tp.pr5.movimiento.MovimientoInvalido;


/** 
 * Implementación de la hebra para el jugador de fichas Negras.
 * @author Adrián Panadero González
 * @author Cristina
 * @version 1
 */
public class HebraNegra extends Thread {
	private ControladorGui c;
	
	/** 
	 * Constructor de la clase.
	 * @param cont - Controlador de la ventana gráfica.
	 */
	public HebraNegra (ControladorGui cont) {
		c = cont;
	}
	
	public void run() {
		if (c.muestraTurno().equalsIgnoreCase("negras") && c.getJugadorN() != null) {
			try {
				c.desactivarBotones();
				//Suspende temporalmente la ejecución de la hebra.
				Thread.sleep(1000);
				c.ponerJugadorN();
				//Solicita la interrupción de la hebra.
				this.interrupt();
				c.activarBotones();
			} catch (InterruptedException e) {
				return;
			} catch (MovimientoInvalido e) {
				e.printStackTrace();
			}
		}
	}
}
