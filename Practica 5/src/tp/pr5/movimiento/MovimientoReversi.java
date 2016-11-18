package tp.pr5.movimiento;

import java.util.ArrayList;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Flanqueo;
import tp.pr5.movimiento.Movimiento;
import tp.pr5.movimiento.MovimientoInvalido;
import tp.pr5.logica.Tablero;

/**
 * Clase que implementa el movimiento para el juego de Reversi. 
 * Se deben implementar los métodos abstractos de la clase padre.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class MovimientoReversi extends Movimiento{
	
	/**
	 * Clase privada que contiene los parámetros y el color de las fichas que van a cambiar.
	 * @author Adrián Panadero González
	 * @author Cristina Barquilla Blanco
	 * @version 1
	 */
	private class CambioFicha{
		int col;
		int fila;
		Ficha color;
		
		/**
		 * Constructor de la clase.
		 * @param c - Columna en la que se encuentra la ficha.
		 * @param f - Fila en la que se encuentra la ficha.
		 * @param color - Color de la ficha.
		 */
		public CambioFicha(int c, int f, Ficha color) {
			this.col = c;
			this.fila = f;
			this.color = color;
		}
	}
	
	private static ArrayList<CambioFicha> list = new ArrayList<CambioFicha>();	//Guarda la fila y la columna de la ficha
	
	private int fila;
	
	/**
	 * Constructor del objeto.
	 * @param columna - Columna en la que se colocará la ficha.
	 * @param fila - Fila en la que se colocará la ficha.
	 * @param color - Color de la ficha que se pondrá (o jugador que pone).
	 */
	public MovimientoReversi(int donde, int fila, Ficha color){
		super(donde, color);
		this.fila = fila;
	}
	
	
	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como parámetro. 
	 * Se puede dar por cierto que el tablero recibido sigue las reglas del tipo de juego al que pertenece el movimiento. 
	 * En caso contrario, el comportamiento es indeterminado.
	 * @param tab - Tablero sobre el que ejecutar el movimiento
	 * @return true si todo fue bien. Se devuelve false si el movimiento no puede ejecutarse sobre el tablero.
	 * @throws MovimientoInvalido 
	 */
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		boolean ejecuta = false;
		if (color != Ficha.VACIA){
			if ((tab.getAncho() >= donde) && (donde > 0) && (tab.getAlto() >= fila) && (fila > 0)){
				//Si la casilla esta vacía, añade el movimiento.
				if(Flanqueo.flanqueoFichas(donde, fila, color, tab)) {
					if (tab.getCasilla(donde, fila) == Ficha.VACIA){
						return ejecuta = movimientoReversi(color, tab);								
					} else {
						throw new MovimientoInvalido("Casilla ocupada.");
					}
				} else {
					throw new MovimientoInvalido("Posición incorrecta.");
				}
			} else {
				throw new MovimientoInvalido("Posición incorrecta.");
			}
		}
		
		return ejecuta;
	}
	
	
	

	private boolean movimientoReversi(Ficha color, Tablero tab) {
		this.color = color;
		int x;
		int y;
		list.add(new CambioFicha(0,0, Ficha.VACIA)); //Para separar cada movimiento en la lista
		for (int j = 0; j < Flanqueo.getPosiciones().size(); j++){
			/* 
			 * Para cada direccion(norte, sur, etc) existe una i asociada,
			 * el vector de direcciones del comprobador guarda varios cambios de fichas
			 * (direccion, i). Hacemos esto para trabajar mas facilmente con las
			 * direcciones.
			 * NOROESTE	- 0
			 * NORTE 	- 1
			 * NORESTE 	- 2
			 * OESTE	- 3
			 * NONE		- 4
			 * ESTE		- 5
			 * SUROESTE	- 6
			 * SUR		- 7
			 * SURESTE	- 8
			 */
			int i = Flanqueo.getPosiciones().get(j).i;
			//incrementa la y para cambiar de fila, siempre la casilla que se compruebe es y = 0, x = 0
			if(i < 3){
				y = -1;
			}else if(i < 6){
				y = 0;
			}else{
				y = 1;
			}
			
			//incrementa la x para cambiar de columna
			if(i % 3 == 0){
				x = -1;
			}else if((i % 3) - 1 == 0){
				x = 0;
			}else{
				x = 1;
			}
			int f = fila + y;
			int c = donde + x;
			//Coloca la cambios de fichas en el tablero y los añade en la lista para facilitar el deshacer. 
			while(tab.getCasilla(c, f) != this.color){
				tab.setCasilla(c, f, this.color);
				list.add(new CambioFicha(c,f, this.color));
				c += x;
				f += y;
			}
		}
		tab.setCasilla(donde, fila, this.color);
		
		return true;
	}


	/**
	 * Deshace el movimiento en el tablero recibido como parámetro. 
	 * Se puede dar por cierto que el movimiento se ejecutó sobre ese tablero; 
	 * en caso contrario, el comportamiento es indeterminado.
	 * Por lo tanto, es de suponer que el método siempre funcionará correctamente.
	 * @param tab - Tablero de donde deshacer el movimiento.
	 */
	public void undo(Tablero tab) {		
		Ficha anterior = Ficha.BLANCA;
		
		tab.setCasilla(donde, fila, Ficha.VACIA);
		CambioFicha c;
		//Comprueba que la lista no este vacía
		if (list.size() > 0) {
			c = list.get(list.size()-1); //Coge las casillas de la lista.
			while (c.color != Ficha.VACIA){				
				if(tab.getCasilla(c.col, c.fila) == Ficha.BLANCA) {
					anterior = Ficha.NEGRA;
				} else if (tab.getCasilla(c.col, c.fila) == Ficha.NEGRA) {
					anterior = Ficha.BLANCA;
				}
				tab.setCasilla(c.col, c.fila, anterior); //Devuelve el color anterior de la casilla
				list.remove(list.size()-1); //Borra la casilla de la lista
				c = list.get(list.size()-1);
			} 
			list.remove(list.size()-1); //Para eliminar la Ficha vacia auxiliar.
			
		}
	}

}

