package tp.pr4.gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;

public class PanelDerecha extends JPanel{

	private static final long serialVersionUID = 1L;
	private PanelPartida partida;
	private PanelCambioJuego cambioJuego;
	private ControladorGui control;
	
	public PanelDerecha(ControladorGui c){
		
		control = c;
		
		partida = new PanelPartida(control);
		cambioJuego = new PanelCambioJuego(control);
		
		//partida.activarDeshacer();
		
		this.setLayout(new FlowLayout());
		this.add(partida);
		this.add(cambioJuego);
		
		this.setVisible(true);
		
	}
	
}
