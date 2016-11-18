package tp.pr5.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tp.pr5.control.ControladorGui;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.movimiento.Movimiento;
import tp.pr5.movimiento.MovimientoInvalido;
import tp.pr5.observer.Observer;

/** 
 * Clase que crea la ventana swing de la aplicaciÃ³n. Ã‰sta clase crea la ventana principal.
 * @author AdriÃ¡n Panadero GonzÃ¡lez
 * @author Cristina Barquilla Blanco
 * @version 4
 *
 */
public class VistaVentana extends JFrame implements Observer {

	
	private static final long serialVersionUID = 1L;
	private PanelAbajo abajo;
	private PanelDerecha derecha;
	private PanelTablero tablero;	
	private ControladorGui control;

	/** 
	 * Contructor de la clase.
	 * @param c - ControladorGui de la partida.
	 * @param alto 
	 * @param ancho 
	 */
	public VistaVentana(ControladorGui c, int ancho, int alto){
		//Nombre de la ventana principal.
		super("Práctica 5 - TP");
		
		control = c;
		control.addObserver(this);
		
		//Creamos el panel de abajo de la ventana. Donde estÃ¡n los botones de Aleatorio y Salir.
		abajo = new PanelAbajo(control);
		//Creamos el panel de la derecha. Donde estÃ¡n el panel de la partida y el panel de cambio de juego.
		derecha = new PanelDerecha(control);
		//Creamos el paner del tablero. Donde estÃ¡ el tablero de la partida.
		tablero = new PanelTablero(control, ancho, alto);		
		
		
		this.getContentPane();
		this.setLayout(new BorderLayout());
		
		//AÃ±adimos los paneles a la venta principal.
		this.add(tablero, BorderLayout.WEST);
		this.add(derecha);
		this.add(abajo, BorderLayout.SOUTH);
	
		this.setResizable(false);
		this.setLocation(50,100);
		this.setSize(600,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}


	/**
	 * Ejecuta el movimiento deseado y pone la casilla del color del turno (blanca, negra).
	 * @param tablero - Tablero de la partida.
	 * @param turno - Turno del jugador al que le toca poner.
	 */
	public void onEjecutaMovimiento(TableroInmutable tablero, Ficha turno)
			throws MovimientoInvalido {
		this.tablero.ejecutaMovimiento(tablero, turno);
	}

	/**
	 * Deshace un movimiento del tablero de la ventana.
	 * @param tablero - Tablero de la partida
	 */
	public void onUndo(TableroInmutable tablero) {
		this.tablero.undo(tablero);
		
	}


	/**
	 * Reinicia la partida y habilita los botones de la ventana.
	 * @param col - NÃºmero de columnas del tablero.
	 * @param fila - NÃºmero de filas del tablero.
	 */
	public void onReset(TableroInmutable tablero) {
		this.tablero.reset(tablero);
		this.abajo.onActivarAleatorio();
		this.derecha.onReset();
	}

	/**
	 * Muestra el ganador de la partida.
	 * @param ganador - Ganador de la partida.
	 */
	public void onResultado(Ficha ganador) {
		String gana = null;
		if (ganador == Ficha.BLANCA) {
			gana = "blancas";
		} else if (ganador == Ficha.NEGRA) {
			gana = "negras";
		}
		JOptionPane.showMessageDialog(null,
				"Han ganado las " + gana , "Ganador",
				JOptionPane.NO_OPTION);
		
	}

	/** 
	 * Muestra en la ventana el turno de la partida.
	 * @param cadena - cadena de texto con el turno.
	 */
	public void onTurno(String cadena) {
		this.tablero.turno(cadena);
		this.derecha.jugadores(cadena);
	}

	/** 
	 * Muestra que clase de error se ha producido en caso de error en la partida.
	 * @param cadena - cadena de texto con la excepcion mostrada por ventana.
	 */
	public void onError(String cadena) {
		JOptionPane.showMessageDialog(null, cadena, "Movimiento invalido", JOptionPane.ERROR_MESSAGE);
		
	}

	@Override
	public void onAyuda() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Movimiento onPideDatos() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Método que muestra un mensaje informativo de que la partida ha terminado en tablas.
	 */
	public void onTablas() {
		JOptionPane.showMessageDialog(null,
				"Partida terminada en tablas", "Tablas",
				JOptionPane.NO_OPTION);
	}


	/**
	 * Método que deshabilita los botones de la ventana cuando termina la partida.
	 */
	public void onTerminada() {
		this.abajo.onDesactivarAleatorio();
		this.derecha.onTerminada();
		this.tablero.desactivarCasillas();
	}
	
	public void onActivarBotones(){
		this.abajo.onActivarAleatorio();
		this.derecha.onActivarBotones();
		this.tablero.activarCasillas();
	}
	
}
