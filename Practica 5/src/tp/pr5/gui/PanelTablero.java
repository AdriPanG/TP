package tp.pr5.gui;

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

import tp.pr5.control.ControladorGui;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.swing.JugadorHumanoSwing;
import tp.pr5.swing.JugadorSwing;


/** 
 * 
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 4
 *
 */
public class PanelTablero extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton[][] botones;
	private ControladorGui control;
	private int ancho;
	private int alto;
	private JPanel casillas;
	private JLabel turno;
	private JugadorSwing jugador;

	
	/** 
	 * Constructor de la clase.
	 * @param c - ControladorGui de la parida
	 * @param ancho - Ancho del tablero.
	 * @param alto - Alto del tablero.
	 */
	public PanelTablero(ControladorGui c, int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;
		control = c;
		TableroInmutable tablero = control.getTableroInmutable();
		pintaTablero(tablero);
		jugador = new JugadorHumanoSwing(control);

	}

	/** 
	 * Clase que implementa el ActionListener del botón poner
	 * @author Adrián Panadero González
	 * @author Cristina Barquilla Blanco
	 * @version 4
	 */
	public class PonerButton implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			//Recorre el tablero de la ventana para saber qué casilla se ha pulsado.
			for (int i = 0; i < alto; i++) {
				for (int j = 0; j < ancho; j++) {
					//Para saber qué casilla se ha pulsado.
					if (e.getSource() == botones[i][j]) {
						//Pone la ficha en la casilla. Le ponemos el +1 para evitar el 0.
						jugador.pulsarTablero(j+1, i+1);	
					}
				}
			}
		}

	}

	
	/** 
	 * Ejecuta el movimiento deseado y pone la casilla del color del turno (blanca, negra).
	 */
	public void ejecutaMovimiento(TableroInmutable tablero, final Ficha turno) {
		//Repinta el tablero.
		this.repaint();	
		//Recorre el tablero de la ventana para pintar la casilla adecuada.
		for (int i = 1; i <= alto; i++) {
			for (int j = 1; j <= ancho; j++) {
				if (tablero.getCasilla(j, i) == Ficha.BLANCA) {
					//Pinta la casilla de color blanco si se ha colocado una ficha blanca.
					botones[i-1][j-1].setBackground(Color.WHITE);
				} else if (tablero.getCasilla(j, i) == Ficha.NEGRA) {
					//Pinta la casilla de color negro si se ha colocado una ficha negra.
					botones[i-1][j-1].setBackground(Color.BLACK);
				} else {
					//Pinta la casilla de color naranja si no se ha colocado ninguna ficha.
					botones[i-1][j-1].setBackground(Color.ORANGE);
				}
			}
		}
	}

	
	/** 
	 * Deshace un movimiento del tablero de la ventana.
	 */
	public void undo(TableroInmutable tablero) {
		//Repinta el tablero.
		this.repaint();
		//Recorre el tablero de la ventana para volver a pintar el tablero según que ficha se ha quitado..
		for (int i = 1; i <= alto; i++) {
			for (int j = 1; j <= ancho; j++) {
				if (tablero.getCasilla(j, i) == Ficha.BLANCA) {
					//Pinta la casilla de color blanco si se ha colocado una ficha blanca.
					botones[i-1][j-1].setBackground(Color.WHITE);
				} else if (tablero.getCasilla(j, i) == Ficha.NEGRA) {
					//Pinta la casilla de color negro si se ha colocado una ficha negra.
					botones[i-1][j-1].setBackground(Color.BLACK);
				} else {
					//Pinta la casilla de color naranja si no se ha colocado ninguna ficha.
					botones[i-1][j-1].setBackground(Color.ORANGE);
				}
			}
		}
	}

	
	
	/** 
	 * Reinicia la partida.
	 */
	public void reset(TableroInmutable tablero) {
		//Borra el tablero.
		this.removeAll();
		//Pinta el tablero con las dimensiones.
		pintaTablero(tablero);
		this.repaint();
		this.revalidate();
		
		//Aaprece un panel que dice partida reiniciada.
		JOptionPane.showMessageDialog(null, "Partida Reiniciada",
				"Nueva Partida", JOptionPane.NO_OPTION);
	}

	
	
	/** 
	 * Muestra en la ventana el turno de la partida.
	 */
	public void turno(String cadena) {
		turno.setText("Juegan " + control.muestraTurno());
	}

	public void desactivarCasillas() {
		for (int i = 1; i < alto + 1 ; i++) {
			for (int j = 1; j < ancho + 1; j++) {
				botones[i-1][j-1].setEnabled(false);
			}
		}
	}
	
	
	/** 
	 * Pinta el tablero en la ventana.
	 * @param ancho - Ancho del tablero.
	 * @param alto - Alto del tablero.
	 */
	private void pintaTablero(TableroInmutable tab) {
		alto = tab.getFilas();
		ancho = tab.getColumnas();

		//Crea una matriz de botones según el alto y el ancho del tablero.
		botones = new JButton[alto][ancho];
		//crea un panel de casillas donde está el tablero.
		casillas = new JPanel();
		//Recorre la matriz del tablero para crear las casillas.
		for (int i = 1; i < alto + 1 ; i++) {
			for (int j = 1; j < ancho + 1; j++) {
				//Crea los botones que serán las casillas del tablero.
				botones[i-1][j-1] = new JButton();
				//Añadimos los botones (casillas) al panel del tablero.
				casillas.add(botones[i-1][j-1]);
				//Coloreamos los botones (casillas) de naranja.
				if (tab.getCasilla(j, i) == Ficha.BLANCA) {
					botones[i-1][j-1].setBackground(Color.WHITE);
				} else if (tab.getCasilla(j, i) == Ficha.NEGRA) {
					botones[i-1][j-1].setBackground(Color.BLACK);
				} else if (tab.getCasilla(j, i) == Ficha.VACIA) {
					botones[i-1][j-1].setBackground(Color.ORANGE);
				}
				//Creamos el ActionListener del botón poner.
				botones[i-1][j-1].addActionListener(new PonerButton());
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


	public void activarCasillas() {
		for (int i = 1; i < alto + 1 ; i++) {
			for (int j = 1; j < ancho + 1; j++) {
				botones[i-1][j-1].setEnabled(true);
			}
		}
	}

}