package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import tp.pr4.control.FactoriaTipoJuego;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.Tablero;
import tp.pr4.observer.Observer;

public class PanelTablero extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private JButton[][] botones;
	private ControladorGui control;
	private int ancho;
	private int alto;
	private JPanel casillas;
	private JLabel turno;

	public PanelTablero(ControladorGui c, int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;
		control = c;
		control.addObserver(this);
		pintaTablero(ancho, alto);

	}

	public class PonerButton implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			for (int i = 0; i < alto; i++) {
				for (int j = 0; j < ancho; j++) {
					if (e.getSource() == botones[i][j]) {
						control.poner(j+1, i+1);
					}
				}
			}
		}

	}

	public void onEjecutaMovimiento(Tablero tablero, final Ficha turno) {
		this.repaint();	
		for (int i = 1; i <= alto; i++) {
			for (int j = 1; j <= ancho; j++) {
				if (tablero.getCasilla(j, i) == Ficha.BLANCA) {
					botones[i-1][j-1].setBackground(Color.WHITE);
				} else if (tablero.getCasilla(j, i) == Ficha.NEGRA) {
					botones[i-1][j-1].setBackground(Color.BLACK);
				} else {
					botones[i-1][j-1].setBackground(Color.ORANGE);
				}
			}
		}
	}

	public void onResultado(final Ficha ganador) {
		JOptionPane.showMessageDialog(null,
				"Han ganado las " + control.muestraTurno(), "Ganador",
				JOptionPane.NO_OPTION);
	}

	public void onUndo(Tablero tablero) {
		this.repaint();
		for (int i = 1; i <= alto; i++) {
			for (int j = 1; j <= ancho; j++) {
				if (tablero.getCasilla(j, i) == Ficha.BLANCA) {
					botones[i-1][j-1].setBackground(Color.WHITE);
				} else if (tablero.getCasilla(j, i) == Ficha.NEGRA) {
					botones[i-1][j-1].setBackground(Color.BLACK);
				} else {
					botones[i-1][j-1].setBackground(Color.ORANGE);
				}
			}
		}
	}

	public void onReset(int ancho, int alto) {
		this.removeAll();
		pintaTablero(ancho, alto);
		this.repaint();
		this.revalidate();

		JOptionPane.showMessageDialog(null, "Partida Reiniciada",
				"Nueva Partida", JOptionPane.NO_OPTION);

	}

	public void onTurno(String cadena) {
		turno.setText("Juegan " + cadena);
	}

	private void pintaTablero(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;
		botones = new JButton[alto][ancho];

		casillas = new JPanel();

		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				botones[i][j] = new JButton();

				casillas.add(botones[i][j]);
				botones[i][j].setBackground(Color.ORANGE);

				botones[i][j].addActionListener(new PonerButton());
			}
		}
		
		// Muestra el turno del jugador al que le toca poner
		turno = new JLabel("Juegan " + control.muestraTurno());
		turno.setHorizontalAlignment(SwingConstants.CENTER);

		this.setPreferredSize(new Dimension(280, 100));

		// GridLayout crea el tablero por filas y columnas, al reves que la
		// clase Tablero
		casillas.setLayout(new GridLayout(alto, ancho));
		casillas.setPreferredSize(new Dimension(20, 350));
		this.setLayout(new BorderLayout());
		this.add(casillas, BorderLayout.NORTH);
		this.add(turno, BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.setVisible(true);
	}

	@Override
	public void onError(String cadena) {
		JOptionPane.showMessageDialog(null, cadena, "Movimiento invalido", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void onPideDatos(Movimiento mov) {
		// TODO Auto-generated method stub
		
	}

}