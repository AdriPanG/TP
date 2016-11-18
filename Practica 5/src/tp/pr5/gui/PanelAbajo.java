package tp.pr5.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.control.ControladorGui;
import tp.pr5.movimiento.MovimientoInvalido;

/** 
 * Clase que crea el panel de abajo de la ventana. Donde éstan los botones de Aleatorio y Salir.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 4
 *
 */
public class PanelAbajo extends JPanel{

	private static final long serialVersionUID = 1L;
	private ControladorGui control;
	private JButton aleatorio;
	private JButton salir;
	private ImageIcon imagenAleatorio;
	private ImageIcon imagenSalir;
	
	
	/** 
	 * Constructor de la clase.
	 * @param c - ControladorGui de la partida.
	 */
	public PanelAbajo(ControladorGui c){
		control = c;
		
		//Creamos una imagen para el botón de aleatorio.
		imagenAleatorio = new ImageIcon("src/Aleatorio.jpg");
		//Creamos el botón aleatorio y le colocamos la imagen.
		aleatorio = new JButton("Poner Aleatorio", imagenAleatorio);
		//Añadimos el ActionListener.
		aleatorio.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try {
							//Ejecuta el movimiento de aleatorio al pulsar el botón.
							control.ponerAleatorio();
						} catch (MovimientoInvalido e1) {
							e1.printStackTrace();
						}				
					}
			
				});
		
		
		//Creamos una imagen para el botón de salir.
		imagenSalir = new ImageIcon("src/Salir.jpg");
		//Creamos el botón salir y le colocamos la imagen.
		salir = new JButton("Salir", imagenSalir);
		//Añadimos el ActionListener.
		salir.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						//Con ésta opción, creamos un panel que aparece al pulsar el botón salir y pregunta al usuario
						//si quiere salir de la aplicación.
						if(JOptionPane.showConfirmDialog(null, "Estás seguro de que desea salir?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						System.exit(0);
					}			
						
					}			
				});
		
		//Añadimos los dos botones al panel.
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 10));
		this.add(aleatorio);
		this.add(salir);
		
		this.setVisible(true);
		
	}
	
	void onDesactivarAleatorio(){
		this.aleatorio.setEnabled(false);
	}
	
	void onActivarAleatorio(){
		this.aleatorio.setEnabled(true);
	}
	
}
