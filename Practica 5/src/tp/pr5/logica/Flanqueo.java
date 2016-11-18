package tp.pr5.logica;

import java.util.ArrayList;

/** 
 * Clase que controla los posibles flanqueos que puede hacer una ficha en el juego de Reversi.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class Flanqueo {
	
	/**
	 * Clase pública que guardará las posibles posiciones de flanqueo.
	 * @author Adrián Panadero González
	 * @author Cristina Barquilla Blanco
	 * @version 1
	 */
	public static class CambioFicha{
		public int i;
		public Posicion p;
		
		public CambioFicha(int i, Posicion pos) {
			this.i = i;
			this.p = pos;
		}
	}
	
	private static ArrayList<CambioFicha> list = new ArrayList<CambioFicha>(); //Guarda las posiciones de los posibles flanqueos.
	/**
	 * Array del enumerado posición que contiene todas las posibles direcciones del flanqueo.
	 */
	private static Posicion[] pos = { 	Posicion.NOROESTE, Posicion.NORTE, Posicion.NORESTE,
								Posicion.OESTE, Posicion.NULL, Posicion.ESTE,
								Posicion.SUROESTE, Posicion.SUR, Posicion.SURESTE};
	/**
	 * Devuelve la lista de posibles posiciones de la ficha
	 * @return lista
	 */
	public static ArrayList<CambioFicha> getPosiciones(){
		return list;
	}
	
	/**
	 * Método que realiza la comprobación de los posibles flanqueos que tiene la ficha.
	 * @param columna - Columna en la que se ha colocado la ficha.
	 * @param fila - Fila en la que se ha colocado la ficha.
	 * @param color - Color de la ficha que se ha colocado.
	 * @param tab - Tablero en el que se ha colocado la ficha.
	 * @return true si existe mínimo un posible flanqueo, false si no hay ningún flanqueo posible.
	 */
	public static boolean flanqueoFichas(int columna, int fila, Ficha color, Tablero tab) {
		boolean comprueba = false;
		  list.clear();
		  int x = -1;
		  int y = -1;
		  int c = columna;
		  int f = fila;
		  
		  Ficha anterior = Ficha.BLANCA;
		  if (color == Ficha.BLANCA) {
		   anterior = Ficha.NEGRA;
		  } else if (color == Ficha.NEGRA) {
		   anterior = Ficha.BLANCA;
		  }
		  
		  if(tab.getCasilla(columna, fila) != Ficha.VACIA){
		   return false;
		  }
		
		//Bucle para comprobar todas las posiciones alrededor de la ficha colocada, incluida en la que está la ficha.
		for (int i = 0; i < 9; i++) {		
			c = columna;
			f = fila;
			
			/*Cuando cambie de fila para seguir comprobando, se pondrá la x de nuevo a -1 (para coger la casilla de la izquierda)
			* y se aumentará la y para pasar a la siguiente fila contando desde abajo.
			*/
			if(i == 3 || i == 6) {
				x = -1;
				y++;
			}
			//Si i = 4 se producirá un error ya que es la posición de la casilla introducida por lo que x e y serían igual a 0.
			if(i != 4) {
				boolean flanqueo = false;
				while (compruebaTableroFlanqueo(c, f, x, y, tab) && tab.getCasilla(c + x, f + y) == anterior && flanqueo == false) {
					c += x;
					f += y;
					
					if (compruebaTableroFlanqueo(c, f, x, y, tab) && tab.getCasilla(c + x, f + y) == color) {						
						comprueba = true;
						list.add(new CambioFicha(i, pos[i]));
						flanqueo = true; //Para salir del while.
					}
				}
			}
			x++;
		}
		
		return comprueba;
	}
	
	/**
	 * Comprueba que se pueda realizar el flanqueo según el tamaño del tablero.
	 * @param col - Columna en la que se colocará la ficha.
	 * @param fila - Fila en la que se colocará la ficha.
	 * @param x - Posición del eje x en la que se está mirando.
	 * @param y - Posición del eje y en la que se está mirando.
	 * @param t - Tablero sobre el que se comprueba el movimiento.
	 * @return true si se puede realizar el flanqueo, false en caso contrario.
	 */
	private static boolean compruebaTableroFlanqueo(int col, int fila, int x, int y, Tablero t) {
		if((col + x > 0) && (fila + y > 0) && (col + x <= t.getAncho()) && (fila + y <= t.getAlto())) {
			return true;
		}
		return false;
		
	}
}
