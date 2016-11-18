package tp.pr5.observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.movimiento.Movimiento;
import tp.pr5.movimiento.MovimientoInvalido;

/** 
 * Interface que contiene el Observador de la aplicación.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 4
 */
public interface Observer {
	
	/** 
	 * Notifica que se ha realizado un movimiento en el tablero.
	 * @param tablero - Tablero del juego.
	 * @param turno - Turno de la partida (blancas, negras).
	 * @throws MovimientoInvalido
	 */
	public abstract void onEjecutaMovimiento(TableroInmutable tablero, Ficha turno) throws MovimientoInvalido;
	
	
	/** 
	 * Notifica que se ha deshecho un movimiento del tablero.
	 * @param tablero - Tablero del juego.
	 */
	public abstract void onUndo(TableroInmutable tablero);
	
	
	/** 
	 * Notifica que se ha reiniciado la partida.
	 * @param col - Ancho del tablero.
	 * @param fila - Alto del tablero.
	 */
	public abstract void onReset(TableroInmutable tablero);
	
	
	/** 
	 * Notifica que se ha terminado la partida y la aplicación tiene que mostrar el resultado de la partida.
	 * @param ganador - El jugador que gana la partida (blancas, negras).
	 */
	public abstract void onResultado(Ficha ganador);
	
	
	
	/** 
	 * Notifica que se ha cambiado el turno de la partida.
	 * @param cadena - Turno de la partida (blancas, negras).
	 */
	public abstract void onTurno(String cadena);
	
	
	
	/** 
	 * Notifica que se ha producido un error en la partida.
	 * @param cadena - El error que se ha producido.
	 */
	public abstract void onError(String cadena);
	
	
	
	/** 
	 * Muestra la ayuda de la partida.
	 */
	public abstract void onAyuda();
	
	
	
	/** 
	 * Pide los datos de la partida al usuario.
	 * @return Movimiento que se tiene que realizar.
	 */
	public abstract Movimiento onPideDatos();


	/**
	 * Muestra un mensaje con la partida en tablas.
	 */
	public abstract void onTablas();
	
	
	/** 
	 * Informa si la partida ha terminado.
	 */
	public abstract void onTerminada();
	
	/**
	 * Activa los botones de la ventana despues de que un jugador automático ponga ficha.
	 */
	public abstract void onActivarBotones();
}
