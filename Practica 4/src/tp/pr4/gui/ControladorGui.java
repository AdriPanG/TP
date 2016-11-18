package tp.pr4.gui;


import tp.pr4.control.FactoriaComplica;
import tp.pr4.control.FactoriaConecta4;
import tp.pr4.control.FactoriaGravity;
import tp.pr4.control.FactoriaTipoJuego;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Partida;
import tp.pr4.observer.Observer;

public class ControladorGui {

	private Partida partida;
	private FactoriaTipoJuego factoria;	
	
	public ControladorGui(FactoriaTipoJuego f, Partida p) {
		this.partida = p;
		this.factoria = f;		
	}

	public FactoriaTipoJuego getFactoria() {
		return factoria;
	}

	public void setFactoria(FactoriaTipoJuego factoria) {
		this.factoria = factoria;
	}		
	
	public void addObserver(Observer o){
		partida.addObserver(o);
	}
	
	public void deshacer(){
		partida.undo();
	}
	
	public void reiniciar(){
		partida.reset(getFactoria().creaReglas());
	}
	
	public String muestraTurno(){
		return partida.muestraTurno();
	}
	
	public void poner(int col, int fila){
		Movimiento mov = null;
		mov = factoria.creaMovimiento(col, fila, partida.getTurno());		
		partida.ejecutaMovimiento(mov);
	}
	
	public void ponerAleatorio() throws MovimientoInvalido {
		Movimiento mov = null;
		mov = factoria.creaJugadorAleatorio().getMovimiento(partida.getTablero(), partida.getTurno());
		partida.ejecutaMovimiento(mov);
	}
	
	public void cambioJuego(Object object, int col, int fila) {
		if(object.equals("Conecta4")) {
			factoria = new FactoriaConecta4();
			setFactoria(factoria);
		}
		else if(object.equals("Complica")) {
			factoria = new FactoriaComplica();
			setFactoria(factoria);
		}
		else if(object.equals("Gravity")) {
			if (col > 0 && fila > 0) {
				factoria = new FactoriaGravity(col, fila);
			} 
			setFactoria(factoria);
		}
		partida.reset(factoria.creaReglas());
	}
	
}
