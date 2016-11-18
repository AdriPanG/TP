package tp.pr5.comandos;

import tp.pr5.control.ControladorConsole;

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
	 * @param controlador - Controla la ejecución del comando.
	 * @return true si todo fue bien. Se devuelve false si el comando no puede ejecutarse.
	 */
	public boolean ejecuta(ControladorConsole control) {
		control.jugar(tamX, tamY, juego);		
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
				if (funcion[1].equalsIgnoreCase("c4") || funcion[1].equalsIgnoreCase("co") || funcion[1].equalsIgnoreCase("rv")) {
					juego = funcion[1];
					if(funcion.length > 2){
						return null;
					}else{
						return this;
					}					
				} else if (funcion.length == 4) {
					if (funcion[1].equalsIgnoreCase("gr")) {
						juego = funcion[1];
						
						if(isNumeric(funcion[2])){
							tamX = Integer.parseInt(funcion[2]);
							if(isNumeric(funcion[3])){
								tamY = Integer.parseInt(funcion[3]);
								return this;
							}
						}
						
						
					}
				}
			}
		}
		return null;
	}

private static boolean isNumeric(String funcion){
		
		try {
			Integer.parseInt(funcion);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
}
