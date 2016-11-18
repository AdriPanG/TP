package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;





import javax.swing.SwingUtilities;

import tp.pr4.control.FactoriaTipoJuego;
import tp.pr4.logica.Ficha;
import tp.pr4.observer.Observer;

public class MainWindow extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private PanelAbajo abajo;
	private PanelDerecha derecha;
	private PanelTablero tablero;
	
	private ControladorGui control;

	
	public MainWindow(ControladorGui c){
		super("Práctica 4 - TP");
		
		control = c;
		
		abajo = new PanelAbajo(control);
		derecha = new PanelDerecha(control);
		tablero = new PanelTablero(control, 7, 6);		
		
		
		this.getContentPane();
		this.setLayout(new BorderLayout());
		
		
		this.add(tablero, BorderLayout.WEST);
		this.add(derecha);
		this.add(abajo, BorderLayout.SOUTH);
	
		this.setResizable(false);
		this.setLocation(50,100);
		this.setSize(600,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}




	
}
