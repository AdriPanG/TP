package tp.pr2.control;

import java.util.Scanner;

import tp.pr2.logica.Ficha;
import tp.pr2.logica.Movimiento;
import tp.pr2.logica.MovimientoComplica;
import tp.pr2.logica.MovimientoConecta4;
import tp.pr2.logica.Partida;
import tp.pr2.logica.ReglasComplica;
import tp.pr2.logica.ReglasConecta4;
import tp.pr2.logica.ReglasJuego;

/**
 * Clase que controla la ejecución de la partida, 
 * pidiendo al usuario qué quiere ir haciendo, hasta que la partida termina.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class Controlador {
	
	private Partida partida;
	private Scanner in;
	private ReglasJuego reglas;	
	private Movimiento mov;

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
		while(!partida.isTerminada()){
			System.out.println(partida.getTablero().toString());	
			
			System.out.println("Juegan " + partida.muestraTurno());
			System.out.print("Qué quieres hacer? ");
	
			String operacion = in.nextLine();
			//Comprobamos si la operación existe y la ejecuta, si no aparece mensaje de error.
			switch(operacion.toLowerCase()){
			case "jugar c4":
				jugar(operacion);
			break;
			case "jugar co":						
				jugar(operacion);				
			break;
			case "poner":
				poner();				
			break;
					
			case "deshacer":
				deshacer();
			break;
				
			case "reiniciar":
				reiniciar();
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
		resultado();
	}
	
	/**
	 * Método que muestra el resultado final de la partida.
	 */
	public void resultado() {
		System.out.println(partida.getTablero().toString());
		if((partida.getGanador() == Ficha.BLANCA) && (partida.isTerminada())) {
			System.out.println("Ganan las blancas");
		}else if((partida.getGanador() == Ficha.NEGRA) && (partida.isTerminada())) {
			System.out.println("Ganan las negras");
		}else if((partida.getGanador() == Ficha.VACIA) && (partida.isTerminada())){
			System.out.println("Partida terminada en tablas.");
		}
	}
	
	/**
	 * Método que ejecuta la operación de poner ficha en el tablero.
	 */
	public void poner() {
		System.out.print("Introduce la columna: ");
		int col = in.nextInt();
		in.nextLine();
		//Comprueba que movimiento tiene que ejecutar.
		if ((partida.getTablero().getAncho() == 7) && (partida.getTablero().getAlto() == 6)) {
			mov = new MovimientoConecta4(col, partida.getTurno());
		}
		else {
			mov = new MovimientoComplica(col, partida.getTurno());
		}
		//Si no se puede ejecutar el movimiento, devuelve un mensaje de error.
		if(!partida.ejecutaMovimiento(mov))
			System.err.println("Movimiento incorrecto");
	}
	
	/**
	 * Método que cambia de juego.
	 * @param juego - cadena con el tipo de juego al que se quiere jugar
	 */
	public void jugar(String juego) {
		//Dependiendo del tipo de juego carga unas reglas u otras.
		if (juego.equalsIgnoreCase("jugar c4")) {
			reglas = new ReglasConecta4();
		}else if (juego.equalsIgnoreCase("jugar co")) {
			reglas = new ReglasComplica();
		}
		partida.reset(reglas);
		System.out.println("Partida reiniciada.");
	}
	
	/**
	 * Método que realiza la operación de deshacer ficha del tablero.
	 */
	public void deshacer() {
		if(!partida.undo()) {
		 	 System.err.println("Imposible deshacer.");
		}
	}
	
	/**
	 * Método que reinicia la partida.
	 */
	public void reiniciar() {
		partida.reset(reglas);
		System.out.println("Partida reiniciada.");
	}
}
