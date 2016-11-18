package tp.pr5.comandos;

import tp.pr5.movimiento.MovimientoInvalido;


/** 
 * Clase que gestiona los comandos del programa. Devuelve el comando que se va a ejecutar.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class GestionComandos{
		
	/** 
	 * Array que contiene todos los comandos que se pueden ejecutar.
	 */
	static Comando []comando = {
		new ComandoAyuda(),
		new ComandoDeshacer(),
		new ComandoPoner(),
		new ComandoReiniciar(),
		new ComandoSalir(),
		new ComandoJugar(),
		new ComandoJugador()
	};
	
	
	/** 
	 * Analiza el comando que se pasa como parametro y elige cuál de ellos es. Si no es ninguno lo devuelve como null.
	 * @param cmd - Comando que se queire ejecutar.
	 * @return el comando que se queire ejecutar. Si no existe devuelve null.
	 * @throws MovimientoInvalido 
	 */
	public static Comando analiza(String cmd) {
		Comando aux = null;
		boolean encontrado = false;
		int contador = 0;
		
		while(!encontrado && contador < comando.length){
			
				aux = comando[contador].analiza(cmd);
			
				if(aux != null){
					encontrado = true;
				}else{
					contador++;
				}
			}
		
		return aux;
		
	}
}
