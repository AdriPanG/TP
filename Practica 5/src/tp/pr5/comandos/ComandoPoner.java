package tp.pr5.comandos;

import tp.pr5.control.ControladorConsole;
import tp.pr5.movimiento.MovimientoInvalido;


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
	 * @param controlador - Controla la ejecución del comando.
	 * @return true si todo fue bien. Se devuelve false si el comando no puede ejecutarse.
	 */
	public boolean ejecuta(ControladorConsole control) throws MovimientoInvalido {
		
		control.poner();
		return true;
		
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
