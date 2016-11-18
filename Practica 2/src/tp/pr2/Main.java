package tp.pr2;

import java.util.Scanner;

import tp.pr2.control.Controlador;
import tp.pr2.logica.Partida;
import tp.pr2.logica.ReglasConecta4;
import tp.pr2.logica.ReglasJuego;

/**
 * Clase que contiene el punto de entrada a la aplicación.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class Main {

	/**
	 * Método principal de la aplicación.
	 * @param args - Argumentos pasados a la aplicación. No se utilizan.
	 */
	public static void main(String[] args) {
		ReglasJuego reglas = new ReglasConecta4();
		
		Partida partida = new Partida(reglas);
		Scanner scanner = new Scanner(System.in);
		Controlador controlador = new Controlador(partida, scanner);
		
		controlador.run();
		
		scanner.close();
		
		
	}

}
