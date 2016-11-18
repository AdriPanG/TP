package tp.pr5.gui;

import java.util.Scanner;

import tp.pr5.control.ControladorConsole;
import tp.pr5.factoria.FactoriaTipoJuego;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;
import tp.pr5.movimiento.Movimiento;
import tp.pr5.movimiento.MovimientoInvalido;
import tp.pr5.observer.Observer;

/** 
 * Clase que crea la vista consola de la aplicación.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 4
 */
public class VistaConsola implements Observer {

	private ControladorConsole control;
	private Scanner in;

	
	/** 
	 * Constructor de la partida.
	 * @param c - ControladorGui de la partida.
	 * @param in - Scanner que hay que utilizar para pedirle la información al usuario.
	 */
	public VistaConsola(ControladorConsole c, Scanner in) {	
		this.control = c;
		control.addObserver(this);
		this.in = in;
	}	
	
	
	
	/** 
	 * Ejecuta la partida hasta que ésta termina.
	 * @throws MovimientoInvalido
	 */
	public void run() throws MovimientoInvalido{
		
		while(!control.partidaTerminada()){
			System.out.println(control.muestraTablero().toString());			
			System.out.println("Juegan " + control.muestraTurno());
			System.out.print("¿Qué quieres hacer? ");
			if(control.analizaOperacion(in.nextLine(), VistaConsola.this) == true) {
				control.aniadeOperacion(VistaConsola.this);
			} else {
				onError("No te entiendo.");
			}
		}		
	}	


	
	/** 
	 * Muestra que clase de error se ha producido en caso de error en la partida.
	 */
	public void onError(String error) {
		System.err.println(error);		
	}

	
	
	/** 
	 * Pide los datos de la partida al usuario.
	 */
	public Movimiento onPideDatos() {
		
		int col = 0, fila = 0;
		TipoJuego tipoJuego = control.getTipoJuego();
		FactoriaTipoJuego factoria = control.getFactoria();	
		
		if(tipoJuego == TipoJuego.CONECTA4 || tipoJuego == TipoJuego.COMPLICA){
			System.out.print("Introduce la columna: ");
			col = in.nextInt();
			in.nextLine();	
		}else if(tipoJuego == TipoJuego.GRAVITY || tipoJuego == TipoJuego.REVERSI){
			System.out.print("Introduce la columna: ");
			col = in.nextInt();
			in.nextLine();	
			
			System.out.print("Introduce la fila: ");
			fila = in.nextInt();
			in.nextLine();
		}
	
		//Crea el movimiento que se va a ejecutar.
		Movimiento mov = factoria.creaMovimiento(col, fila, control.turno());
		
		return mov;		
		
	}	

	
	
	/** 
	 * Muestra que se ha deshecho un movimiento con éxito.
	 */
	public void onUndo(TableroInmutable tablero) {
		System.out.println("Movimiento deshecho.");
	}


	
	/** 
	 * Muestra que se ha reiniciado la partida con éxito.
	 */
	public void onReset(TableroInmutable tablero) {
		System.out.println("Partida reiniciada.");
	}


	
	/** 
	 * Muestra el resultado de la partida. Qué jugador ha ganado.
	 */
	public void onResultado(Ficha ganador) {
		if(control.partidaTerminada()){
			System.out.println(control.muestraTablero());
			if(ganador == Ficha.BLANCA){
				System.out.println("Ganan las blancas.");
			}else if(ganador == Ficha.NEGRA){
				System.out.println("Ganan las blancas.");
			}else if(ganador == Ficha.VACIA){
				System.out.println("Partida terminada en tablas.");
			}
		}		
	}


	public void onEjecutaMovimiento(TableroInmutable tablero, Ficha turno) throws MovimientoInvalido {		
				
	}

	public void onTurno(String cadena) {
		
	}

	
	
	/** 
	 * Muestra la ayuda de la partida.
	 */
	public void onAyuda() {		
		StringBuilder cadena = control.ayuda();
		System.out.println(cadena);
	}



	@Override
	public void onTablas() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTerminada() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onActivarBotones() {
		// TODO Auto-generated method stub
		
	}

}
