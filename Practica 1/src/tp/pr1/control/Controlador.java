package tp.pr1.control;

import java.util.Scanner;

import tp.pr1.logica.Ficha;
import tp.pr1.logica.Partida;

/**
 * Clase que controla el funcionamiento del juego Conecta 4.
 * @author Adrian Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class Controlador {
	
	private Partida partida;
	private Scanner in;

	/**
	 * Constructor de la clase.
	 * @param p - Partida a la que se jugará.
	 * @param in - Scanner que hay que utilizar para pedirle la información al usuario.
	 */
	public Controlador(Partida p, java.util.Scanner in){
		partida = p;
		this.in = in;
	}
	
	/**
	 * Ejecuta la partida hasta que ésta termina.
	 */
	public void run(){	
		
		while(partida.isTerminada() == false){		
			System.out.println(partida.getTablero().toString());
			
			String fichas = null;
			if(partida.getTurno() == Ficha.BLANCA){
				fichas = "blancas";
			}else if(partida.getTurno() == Ficha.NEGRA){
				fichas = "negras";
			}	
			
			System.out.println("Juegan " + fichas);
			System.out.print("¿Qué quieres hacer? ");
	
			String operacion = in.nextLine(); 
			switch(operacion.toLowerCase()){
			case "poner":
				System.out.print("Introduce la columna: ");
				String numero = in.nextLine();
				int col = Integer.parseInt(numero);
				if(!partida.ejecutaMovimiento(partida.getTurno(), col))
					System.err.println("Movimiento incorrecto");
			break;
					
			case "deshacer":
				if(!partida.undo()) {
				 	 System.err.println("Imposible deshacer.");
				}
			break;
				
			case "reiniciar":
				partida.reset();
				System.out.println("Partida reiniciada.");
			break;
				
			case "salir":
				System.exit(0);
			break;
				
			default:
			    System.err.println("No te entiendo."); 
			break;
			}		
		}
		
		//Devuelve el tablero y escribe por consola cómo ha acabado la partida.
		System.out.println(partida.getTablero().toString());
		if((partida.getGanador() == Ficha.BLANCA) && (partida.isTerminada() == true)) {
			System.out.println("Ganan las blancas");
		}else if((partida.getGanador() == Ficha.NEGRA) && (partida.isTerminada() == true)) {
			System.out.println("Ganan las negras");
		}else if((partida.getGanador() == Ficha.VACIA) && (partida.isTerminada() == true)){
			System.out.println("Partida terminada en tablas");
		}
	}
	
}
