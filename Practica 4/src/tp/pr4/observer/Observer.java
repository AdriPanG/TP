package tp.pr4.observer;

import tp.pr4.control.FactoriaTipoJuego;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.ReglasJuego;
import tp.pr4.logica.Tablero;

public interface Observer {
	public void onEjecutaMovimiento(Tablero tablero, Ficha turno);
	public void onUndo(Tablero tablero);
	public void onReset(int col, int fila);
	public void onResultado(Ficha ganador);
	public void onTurno(String cadena);
	public void onError(String cadena);
	public void onPideDatos(Movimiento mov);
}
