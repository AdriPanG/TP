package tp.pr4.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr4.logica.MovimientoInvalido;

public class PanelAbajo extends JPanel{

	private static final long serialVersionUID = 1L;
	private ControladorGui control;
	private JButton aleatorio;
	private JButton salir;
	private ImageIcon imagenAleatorio;
	private ImageIcon imagenSalir;
	
	
	public PanelAbajo(ControladorGui c){
		control = c;
		
		imagenAleatorio = new ImageIcon("Aleatorio.jpg");
		aleatorio = new JButton("Poner Aleatorio", imagenAleatorio);
		aleatorio.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try {
							control.ponerAleatorio();
						} catch (MovimientoInvalido e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}				
					}
			
				});
		
		
		imagenSalir = new ImageIcon("Salir.jpg");
		salir = new JButton("Salir", imagenSalir);
		salir.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						if(JOptionPane.showConfirmDialog(null, "Estás seguro de que desea salir?") == JOptionPane.OK_OPTION){
						System.exit(0);
					}			
						
					}			
				});
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 10));
		this.add(aleatorio);
		this.add(salir);
		
		this.setVisible(true);
		
	}
	
}
