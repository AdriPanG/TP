package tp.pr4.gui;

import java.util.Scanner;

import tp.pr4.control.ControladorConsole;
import tp.pr4.control.FactoriaTipoJuego;
import tp.pr4.control.Jugador;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Tablero;
import tp.pr4.observer.Observer;


public class VistaConsola implements Observer {

	private FactoriaTipoJuego factoria;
	private ControladorConsole control;
	private Scanner in;
	private Jugador jugador1;
	private Jugador jugador2;
	
	
	public VistaConsola(ControladorConsole c, Scanner in) {	
		this.control = c;
		control.addObserver(this);
		this.in = in;
		jugador1 = control.getFactoria().creaJugadorHumanoConsola();
		jugador2 = control.getFactoria().creaJugadorHumanoConsola();
	}	
	
	
	public void run() throws MovimientoInvalido {
		
		while(!control.partidaTerminada()){
			System.out.println(control.muestraTablero().toString());
			
			System.out.println("Juegan " + control.muestraTurno());
			System.out.print("¿Qué quieres hacer? ");
			
			control.aniadeOperacion(in.nextLine(), VistaConsola.this);
		}		
		control.muestraResultado();
	}
	
	public void pedirDatos(){	
		
		
	}


	@Override
	public void onEjecutaMovimiento(Tablero tablero, Ficha turno) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onUndo(Tablero tablero) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onReset(int col, int fila) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onResultado(Ficha ganador) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTurno(String cadena) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onError(String cadena) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPideDatos(Movimiento mov) {
		//if (control.getFactoria())
		System.out.print("Introduce la columna: ");
		int col = in.nextInt();
		in.nextLine();	
		
	}
	
}
