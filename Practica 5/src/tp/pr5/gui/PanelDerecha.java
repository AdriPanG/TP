package tp.pr5.gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import tp.pr5.control.ControladorGui;


/** 
 * Clase que crea el panel de la derecha donde estarÃ¡n el panel de partid ay el de cambio de juego.
 * @author AdriÃ¡n Panadero GonzÃ¡lez
 * @author Cristina Barquilla Blanco
 * @version 4
 *
 */
public class PanelDerecha extends JPanel{

	private static final long serialVersionUID = 1L;
	private PanelPartida partida;
	private PanelGestionJugadores jugadores;
	private PanelCambioJuego cambioJuego;
	private ControladorGui control;
	
	
	/** 
	 * Contructor de la clase
	 * @param c - ControladorGui de la partida.
	 */
	public PanelDerecha(ControladorGui c){
		
		control = c;
		
		//Creamos el panel de la partida.
		partida = new PanelPartida(control);
		//Creamos el panel de gestiÃ³n de jugadores.
		jugadores = new PanelGestionJugadores(control);
		//Creamos el paner de cambio de juego.
		cambioJuego = new PanelCambioJuego(control);
		
		this.setLayout(new FlowLayout());
		//AÃ±adimos los dos paneles al panel de la derecha.
		this.add(partida);
		this.add(jugadores);
		this.add(cambioJuego);
		
		this.setVisible(true);
		
	}
	
	/**
	 * Método que ejecuta la hebra de las fichas blancas y de las negras.
	 * @param turno - Jugador que realiza el movimiento.
	 */
	public void jugadores(String turno) {
		if (turno == "blancas") {
			jugadores.movimientoBlancas();
		} else {
			jugadores.movimientoNegras();
		}
	}
	
	/**
	 * Método que deshabilita los botones de la ventana cuando termina la partida.
	 */
	public void onTerminada(){
		this.partida.onDesactivarDeshacer();
		this.jugadores.onDesactivarComboBox();
	}
	
	/**
	 * Método que habilita los botones de la ventana cuando se inicia una partida.
	 */
	public void onReset(){
		this.partida.onActivarDeshacer();
		this.jugadores.onReset();
	}
	
	public void onActivarBotones() {
		this.partida.onActivarDeshacer();
		this.jugadores.onActivarComboBox();
	}
}
