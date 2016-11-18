package tp.pr5.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import tp.pr5.control.ControladorGui;


/** 
 * 
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 4
 *
 */
public class PanelPartida extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JButton deshacer;
	private JButton reiniciar;
	private ImageIcon imagenDeshacer;
	private ImageIcon imagenReiniciar;
	private ControladorGui control;
	
	
	/** 
	 * Constructor de la clase.
	 * @param c - ControladorGui de la partida.
	 */
	public PanelPartida(ControladorGui c){

		control = c;
		
		//Creamos la imagen del botón deshacer.
		imagenDeshacer = new ImageIcon("src/Deshacer.jpg");
		//Creamos el botón de deshacer y le colocamos la imagen.
		deshacer = new JButton("Deshacer", imagenDeshacer);
		//Elegimos la dimensión del botón.
		deshacer.setPreferredSize(new Dimension(140, 50));
		//Añadimos el ActionListener.
		deshacer.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						//Deshace un movimiento del tablero al presionar el botón deshacer.
						control.deshacer();					
					}			
				});
		
		
		
		//Creamos la imagen para el botón de reiniciar.
		imagenReiniciar = new ImageIcon("src/Reiniciar.jpg");
		//Creamos el botón de reiniciar y le colocamos la imagen.
		reiniciar = new JButton("Reiniciar", imagenReiniciar);
		//Elegimos la dimesión del botón.
		reiniciar.setPreferredSize(new Dimension(140, 50));
		//Añadimos el ActionListener.
		reiniciar.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						//Reinicia la partida al presionar el botón de reiniciar.
						control.reiniciar();
					}			
				});
		
		this.setLayout(new FlowLayout());
		//Creamos el border del panel partida y le ponemos el título.
		this.setBorder(BorderFactory.createTitledBorder("Partida"));
		//Añadimos los dos botones al panel de la partida.
		this.add(deshacer);
		this.add(reiniciar);
		
		this.setVisible(true);
	}
	
	public void onDesactivarDeshacer(){
		this.deshacer.setEnabled(false);
	}
	
	public void onActivarDeshacer(){
		this.deshacer.setEnabled(true);
	}
}
