package tp.pr4.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class PanelPartida extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JButton deshacer;
	private JButton reiniciar;
	private ImageIcon imagenDeshacer;
	private ImageIcon imagenReiniciar;
	private ControladorGui control;
	
	public PanelPartida(ControladorGui c){

		control = c;
		
		imagenDeshacer = new ImageIcon("Deshacer.jpg");
		deshacer = new JButton("Deshacer", imagenDeshacer);
		deshacer.setPreferredSize(new Dimension(140, 50));
		deshacer.setEnabled(false);
		deshacer.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						control.deshacer();					
					}			
				});
		
		
		
		imagenReiniciar = new ImageIcon("Reiniciar.jpg");
		reiniciar = new JButton("Reiniciar", imagenReiniciar);
		reiniciar.setPreferredSize(new Dimension(140, 50));
		reiniciar.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						control.reiniciar();						
					}			
				});
		
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createTitledBorder("Partida"));
		this.add(deshacer);
		this.add(reiniciar);
		
		this.setVisible(true);
	}
	
	public void activarDeshacer() {
		if(deshacer.isEnabled() == false)
		deshacer.setEnabled(true);
	}
}
