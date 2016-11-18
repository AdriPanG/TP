package tp.pr5.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.pr5.logica.Ficha;
import tp.pr5.swing.JugadorHumanoSwing;
import tp.pr5.swing.JugadorSwing;
import tp.pr5.control.ControladorGui;

/** 
 * Clase que crea el panel de cambio de jugadores entre modo humano o modo automático.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
@SuppressWarnings("serial")
public class PanelGestionJugadores extends JPanel implements ActionListener{

	private ControladorGui control;
	private JComboBox<String> modoblancas;
	private JComboBox<String> modonegras;
	private JLabel blancas;
	private JLabel negras;
	private String[] modo = {"HUMANO", "AUTOMATICO"};
	private JugadorSwing jugadorB; //Jugador blancas.
	private JugadorSwing jugadorN; //Jugador negras.
	
	/**
	 * Constructor de la clase.
	 * @param c - Controlador de la ventana.
	 */
	public PanelGestionJugadores(ControladorGui c) {
		this.control = c;
		jugadorB = new JugadorHumanoSwing(control);
		jugadorN = new JugadorHumanoSwing(control);
		blancas = new JLabel("Jugador de Blancas ");
		negras = new JLabel("Jugador de Negras ");
		//Creamos los JComboBox donde aparecen los modos de jugador a elegir.
		modoblancas = new JComboBox<String>(modo);
		modoblancas.setSelectedItem(0);
		modoblancas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == modoblancas) {
					if (modoblancas.getSelectedItem() == "AUTOMATICO") {
						//Ejecuta el movimiento de aleatorio al pulsar el botón.
						control.setTipoJugador(Ficha.BLANCA, modo[1]);
						jugadorB = jugadorB.turnoActual(Ficha.BLANCA, modo[1].toString());
						movimientoBlancas();
					} else if (modoblancas.getSelectedItem() == "HUMANO") {
						control.setTipoJugador(Ficha.BLANCA, modo[0]);
						jugadorB = jugadorB.turnoActual(Ficha.BLANCA, modo[0].toString());
					}
				}
			}
		});
		modonegras = new JComboBox<String>(modo);
		modonegras.setSelectedItem(0);
		modonegras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == modonegras) {
					if (modonegras.getSelectedItem() == "AUTOMATICO") {
						//Ejecuta el movimiento de aleatorio al pulsar el botón.
						control.setTipoJugador(Ficha.NEGRA, modo[1]);
						jugadorN = jugadorN.turnoActual(Ficha.NEGRA, modo[1].toString());
						movimientoNegras();
					} else if (modonegras.getSelectedItem() == "HUMANO") {
						control.setTipoJugador(Ficha.NEGRA, modo[0]);
						jugadorN = jugadorN.turnoActual(Ficha.NEGRA, modo[0].toString());
					}
				}
			}
		});
		
		
		//Elegimos las dimensiones que queremos ponerle.
		modoblancas.setPreferredSize(new Dimension(100, 25));
		modonegras.setPreferredSize(new Dimension(100, 25));

		this.setLayout(new GridLayout(2,2));
		//Crea el borde alrededor del panel y el título del panel.
		this.setBorder(BorderFactory.createTitledBorder("Gestión de jugadores"));
		//Ponemos la dimensión del panel.
		this.setPreferredSize(new Dimension(300, 80));

		//Los añadimos al panel gestion de jugadores.
		this.add(blancas);
		this.add(modoblancas);
		this.add(negras);
		this.add(modonegras);

		this.setVisible(true);
	}

	public String[] getModo() {
		return modo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	/**
	 * Método que ejecuta el movimiento de las blancas con una hebra.
	 */
	public void movimientoBlancas () {
		jugadorB.turnoHebraB();	
	}
	
	/**
	 * Método que ejecuta el movimiento de las negras con una hebra.
	 */
	public void movimientoNegras () {
		jugadorN.turnoHebraN();
	}
	
	/**
	 * Método que deshabilita los combobox del modo de los jugadores
	 */
	public void onDesactivarComboBox(){
		modoblancas.setEnabled(false);
		modonegras.setEnabled(false);
	}
	
	/**
	 * Método que habilita los combobox del modo de los jugadores
	 */
	public void onActivarComboBox(){
		modoblancas.setEnabled(true);
		modonegras.setEnabled(true);
	}
	
	/**
	 * Método que reinicia y habilita el estado de los combobox al inicio de la partida.
	 */
	public void onReset(){
		modoblancas.setEnabled(true);
		modoblancas.setSelectedIndex(0);
		control.setTipoJugador(Ficha.BLANCA, modo[0]);
		jugadorB = jugadorB.turnoActual(Ficha.BLANCA, modo[0].toString());
		
		modonegras.setEnabled(true);
		modonegras.setSelectedIndex(0);
		control.setTipoJugador(Ficha.NEGRA, modo[0]);
		jugadorN = jugadorN.turnoActual(Ficha.NEGRA, modo[0].toString());		
	}

}
