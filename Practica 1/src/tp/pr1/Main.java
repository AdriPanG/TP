package tp.pr1;

import java.util.Scanner;

import tp.pr1.control.Controlador;
import tp.pr1.logica.Partida;

/**
 * Clase principal que ejecuta la aplicación Conecta 4.
 * @author Adrian Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class Main {

	/**
	 * Método principal de la aplicación.
	 * @param args - Argumentos pasados a la aplicación. No se utilizan.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Partida Partida = new Partida();
		Scanner Scanner = new Scanner(System.in);
		Controlador controlador = new Controlador(Partida, Scanner);
		
		controlador.run();
		
		
	}

}
