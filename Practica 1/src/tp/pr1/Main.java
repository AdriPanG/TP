package tp.pr1;

import java.util.Scanner;

import tp.pr1.control.Controlador;
import tp.pr1.logica.Partida;

/**
 * Clase principal que ejecuta la aplicaci�n Conecta 4.
 * @author Adrian Panadero Gonz�lez
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class Main {

	/**
	 * M�todo principal de la aplicaci�n.
	 * @param args - Argumentos pasados a la aplicaci�n. No se utilizan.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Partida Partida = new Partida();
		Scanner Scanner = new Scanner(System.in);
		Controlador controlador = new Controlador(Partida, Scanner);
		
		controlador.run();
		
		
	}

}
