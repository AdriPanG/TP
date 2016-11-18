package tp.pr4.comandos;

import tp.pr4.control.ControladorConsole;
import tp.pr4.control.FactoriaComplica;
import tp.pr4.control.FactoriaConecta4;
import tp.pr4.control.FactoriaGravity;
import tp.pr4.control.FactoriaTipoJuego;
import tp.pr4.gui.VistaConsola;
import tp.pr4.logica.Partida;

/** 
 * Clase que contiene el comando Jugar. Con el que elige a que juego se quiere jugar ya sea Gravity,
 * Conecta4 o Complica. En el caso del Gravity también se puede cambiar las dimensiones del tablero.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class ComandoJugar implements Comando{

	private String juego; // Juego al que se queire jugar.
	private int tamX; /*Dimensiones del    */
	private int tamY; /*tablero de Gravity*/

	
	/** 
	 * Ejecuta el comando Jugar.
	 * @param partida - Partida sobre la que se ejecuta el comando.
	 * @param controlador - Controla la ejecución del comando.
	 * @return true si todo fue bien. Se devuelve false si el comando no puede ejecutarse.
	 */
	public boolean ejecuta(Partida partida, ControladorConsole control) {
		FactoriaTipoJuego factoria = control.getFactoria();
		
		if(juego.equalsIgnoreCase("c4")){
			factoria = new FactoriaConecta4();
			
		}else if(juego.equalsIgnoreCase("co")){
			factoria = new FactoriaComplica();
			
		}else if(juego.equalsIgnoreCase("gr")){
			if (tamX > 0 && tamY > 0) {
				factoria = new FactoriaGravity(tamX, tamY);
			} 
		}	
		
		control.setFactoria(factoria);
		partida.reset(factoria.creaReglas());
		System.out.println("Partida reiniciada.");
		
		return true;
	}

	
	/** 
	 * Analiza el comando por partes hasta que encuentra el comando que se quiere ejecutar.
	 * @param cmd - Comando que se quiere ejecutar.
	 * @return Objeto Comando que se utiliza para ejecutar el comando. Devuelve null si no lo ha encontrado. 
	 */
	public Comando analiza(String cmd) {
		String[] funcion = cmd.split(" ");
		if (funcion[0].equalsIgnoreCase("jugar")) {
			if (funcion.length > 1) {
				if (funcion[1].equalsIgnoreCase("c4")|| funcion[1].equalsIgnoreCase("co")) {
					juego = funcion[1];
					return this;
				} else if (funcion.length == 4) {
					if (funcion[1].equalsIgnoreCase("gr")) {
						juego = funcion[1];
						tamX = Integer.parseInt(funcion[2]);
						tamY = Integer.parseInt(funcion[3]);
						return this;
					}
				}
			}
		}
		return null;
	}

}
