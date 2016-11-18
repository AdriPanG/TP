package tp.pr4.comandos;

import tp.pr4.control.ControladorConsole;
import tp.pr4.control.Jugador;
import tp.pr4.gui.VistaConsola;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Partida;


/** 
 * Clase que contiene el comando Poner. Se utiliza para poner una ficha en el tablero.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class ComandoPoner implements Comando{
	
	/** 
	 * Constructor de la clase.
	 */
	public ComandoPoner() {
		
	}
	
	/** 
	 * Ejecuta el comando Poner.
	 * @param partida - Partida sobre la que se ejecuta el comando.
	 * @param controlador - Controla la ejecución del comando.
	 * @return true si todo fue bien. Se devuelve false si el comando no puede ejecutarse.
	 */
	public boolean ejecuta(Partida partida, ControladorConsole control) throws MovimientoInvalido {
		
		Jugador jugador1 = control.getJugador1();
		Jugador jugador2 = control.getJugador2();
		Movimiento mov = null;
		if (partida.getTurno() == Ficha.BLANCA) {
			mov = jugador1.getMovimiento(partida.getTablero(), partida.getTurno());
		} else if (partida.getTurno() == Ficha.NEGRA) {
			mov = jugador2.getMovimiento(partida.getTablero(), partida.getTurno());
		}
		
		
		if(!partida.ejecutaMovimiento(mov)){
			System.err.println("Movimiento incorrecto");
			return false;
		}else return true;
	}

	/** 
	 * Analiza el comando por partes hasta que encuentra el comando que se quiere ejecutar.
	 * @param cmd - Comando que se quiere ejecutar.
	 * @return Objeto Comando que se utiliza para ejecutar el comando. Devuelve null si no lo ha encontrado. 
	 */
	public Comando analiza(String cmd) {	
		
		if(cmd.equalsIgnoreCase("poner")){
			return new ComandoPoner();
		}else return null;
				
	}


}
