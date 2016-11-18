package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.pr5.control.ControladorGui;

/** 
 * Clase que crea el panel para cambiar de juego (Conecta4, Complica, Gravity).
 * @author AdriÃ¡n Panadero GonzÃ¡lez
 * @author Cristina Barquilla Blanco
 * @version 4
 *
 */
public class PanelCambioJuego extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> juegos;
	private JButton cambiar;
	private ControladorGui control;
	private JPanel central;
	private JLabel pedircol;
	private JFormattedTextField col;
	private JLabel pedirfila;
	private JFormattedTextField fila;

	
	/** 
	 * Constructor de la clase.
	 * @param c - ControladorGui de la partida.
	 */
	public PanelCambioJuego(ControladorGui c) {
		control = c;
		
		//Creamos el JComboBox donde aparecen los tres juegos a elegir.
		juegos = new JComboBox<String>();
		//Elegimos las dimensiones que queremos ponerle.
		juegos.setPreferredSize(new Dimension(200, 25));
		//Añadimos los juego al JComboBox
		juegos.addItem("Conecta4");
		juegos.addItem("Complica");
		juegos.addItem("Gravity");
		juegos.addItem("Reversi");
		//Añadimos el ActionListener.
		juegos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (juegos.getSelectedItem() == "Gravity") {
					add(central, BorderLayout.CENTER);
					central.setVisible(true);
				} else if (juegos.getSelectedItem() == "Reversi") {
					central.setVisible(false);
				} else {
					central.setVisible(false);
				}
			}
		});		
		
		//Creamos el panel central donde estarán columna y fila donde pondremos el ancho y alto del tablero del gravity.
		central = new JPanel();
		central.setLayout(new FlowLayout());
		pedircol = new JLabel("Columna: ");
		col = new JFormattedTextField(new Integer(10));
		col.setColumns(2);
		pedirfila = new JLabel("Fila: ");
		fila = new JFormattedTextField(new Integer(10));
		fila.setColumns(2);
		central.add(pedircol);
		central.add(col);
		central.add(pedirfila);
		central.add(fila);
		central.setVisible(false);
		//Creamos el botón cambiar.
		cambiar = new JButton("Cambiar");
		//Elegimos la dimensión del botón.
		cambiar.setPreferredSize(new Dimension(20, 30));
		//Añadimos el ActionListener.
		cambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				central.setVisible(false);
				//Si seleccionamos el juego gravity.
				if (juegos.getSelectedItem() == "Gravity") {
					central.setVisible(true);
					//Cogemos el ancho y el alto del tablero.
					Integer columna = (Integer) col.getValue();
					Integer filaT = (Integer) fila.getValue();
					//Cambia el juego con el ancho y el alto que hemos elegido.
					control.cambioJuego(juegos.getSelectedItem(), columna, filaT);
				} else {
					control.cambioJuego(juegos.getSelectedItem(), 0, 0);
				}
			}

		});

		this.setLayout(new BorderLayout());
		//Crea el borde alrededor del panel y el título del panel.
		this.setBorder(BorderFactory.createTitledBorder("Cambio de juego"));
		//Ponemos la dimensión del panel.
		this.setPreferredSize(new Dimension(300, 120));

		//Los añadimos al panel cambioJuego.
		this.add(juegos, BorderLayout.NORTH);
		this.add(cambiar, BorderLayout.SOUTH);

		this.setVisible(true);

	}

}
