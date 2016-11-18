package tp.pr5.swing;

import tp.pr5.control.ControladorGui;
import tp.pr5.movimiento.MovimientoInvalido;

/** 
 * Implementación de la hebra para el jugador de fichas Blancas.
 * @author Adrián Panadero González
 * @author Cristina
 * @version 1
 */
public class HebraBlanca extends Thread {
	private ControladorGui c;

	/** 
	 * Constructor de la clase.
	 * @param cont - Controlador de la ventana gráfica.
	 */
	public HebraBlanca(ControladorGui cont) {
		c = cont;
	}
	
	/** 
	 * Método que ejecuta la hebra.
	 */
	public void run() {
		if (c.muestraTurno().equalsIgnoreCase("blancas") && c.getJugadorB() != null) {
			try {
				c.desactivarBotones();
				//Suspende temporalmente la ejecución de la hebra.
				Thread.sleep(1000); 
				c.ponerJugadorB();
				//Solicita la interrupción de la hebra.
				this.interrupt();
				c.activarBotones();
			} catch (InterruptedException e) {
				return;
			} catch (MovimientoInvalido e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
