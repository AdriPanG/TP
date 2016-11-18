package tp.pr4.gui;

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

	public PanelCambioJuego(ControladorGui c) {
		control = c;

		juegos = new JComboBox<String>();
		juegos.setPreferredSize(new Dimension(200, 25));
		juegos.addItem("Conecta4");
		juegos.addItem("Complica");
		juegos.addItem("Gravity");
		juegos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		central = new JPanel();
		central.setLayout(new FlowLayout());
		pedircol = new JLabel("Columna: ");
		col = new JFormattedTextField(new Integer(0));
		col.setColumns(2);
		pedirfila = new JLabel("Fila: ");
		fila = new JFormattedTextField(new Integer(0));
		fila.setColumns(2);
		central.add(pedircol);
		central.add(col);
		central.add(pedirfila);
		central.add(fila);
		central.setVisible(true);

		cambiar = new JButton("Cambiar");
		cambiar.setPreferredSize(new Dimension(20, 30));
		cambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (juegos.getSelectedItem() == "Gravity") {
					Integer columna = (Integer) col.getValue();
					Integer filaT = (Integer) fila.getValue();
					control.cambioJuego(juegos.getSelectedItem(), columna, filaT);
				} else {
					control.cambioJuego(juegos.getSelectedItem(), 0, 0);
				}
			}

		});

		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Cambio de juego"));
		this.setPreferredSize(new Dimension(300, 120));

		this.add(juegos, BorderLayout.NORTH);
		this.add(central, BorderLayout.CENTER);
		this.add(cambiar, BorderLayout.SOUTH);

		this.setVisible(true);

	}

}
