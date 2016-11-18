package tp.pr1.logica;

/**
 * Clase que contiene todo lo relacionado para jugar al Conecta 4.
 * @author Adrian Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class Partida {
	
	private Tablero tablero;
	private Ficha turno;
	private boolean terminada;
	private Ficha ganador;
	
	private int[] undoStack; //Columnas.
	private int numUndo; //Nº de posiciones ocupadas.

	/**
	 * Construye una partida nueva.
	 */
	public Partida(){
		numUndo = -1;
		tablero = new Tablero(7,6);
		undoStack = new int[10];
		turno = Ficha.BLANCA;
		ganador = Ficha.VACIA;	
		terminada = false;
		
	}
	
	/**
	 * Reinicia la partida en curso. Esta operación no puede deshacerse.
	 */
	public void reset(){
		numUndo = -1;
		undoStack = new int[10];
		turno = Ficha.BLANCA;
		ganador = Ficha.VACIA;
		terminada = false;
		tablero = new Tablero(tablero.getAncho(),tablero.getAlto());
		
	}
	
	/**
	 * Reinicia la partida en curso. Esta operación no puede deshacerse.
	 * @param color - Color del jugador que pone.
	 * @param col - Columna donde colocar la ficha (1..7)
	 * @return true si se puede efectuar el movimiento. Es un error intentar colocar una ficha del jugador que no tiene el turno, cuando la partida está terminada, columna llena, ...
	 */
	public boolean ejecutaMovimiento(Ficha color, int col){		
				
		if ((turno == color) && (terminada == false)){
			if ((tablero.getAncho() >= col) && (col > 0)){
				if (tablero.buscarFila(col) != -1) {
					int fila = tablero.buscarFila(col);						
					
					if (tablero.getCasilla(col, fila) == Ficha.VACIA){
					
						if (numUndo+1 == undoStack.length){
							
							for(int i = 0; i < numUndo; i++){
								undoStack[i] = undoStack[i + 1];
							}
														
						}else{
							numUndo++;
						}
						tablero.setCasilla(col, fila, color);		
						undoStack[numUndo] = col;
						
						if ((comprobarHorizontal(col, fila)) || (comprobarVertical(col, fila)) || (comprobarDiagonalIzquierda(col, fila)) || (comprobarDiagonalDerecha(col, fila))) {
							ganador = color;
							terminada = true;
						}
						else if( comprobarTablas()){
							ganador = Ficha.VACIA;
							terminada = true;
						}
						else{
							if(getTurno() == Ficha.BLANCA){
								turno = Ficha.NEGRA;
							}else if(getTurno() == Ficha.NEGRA){
								turno = Ficha.BLANCA;
							}	
						}
						return true;
					}
				}
			}
		}
		return false;	
		
			
	}
	
	/**
	 * Deshace el último movimiento ejecutado.
	 * @return true si se pudo deshacer.
	 */
	public boolean undo(){
		int col = 0, fila = 0;
		  
		  if(numUndo > -1) {
		   col = undoStack[numUndo];
		   fila = tablero.buscarFila(col);
		   
		   tablero.setCasilla(col, fila+1, Ficha.VACIA);
		   
		   numUndo--;
		   
		   if(getTurno() == Ficha.BLANCA){
		    turno = Ficha.NEGRA;
		   }else if(getTurno() == Ficha.NEGRA){
		    turno = Ficha.BLANCA;
		   }
		   
		   return true;
		  }
		  return false;
		    
		 }
	
	
	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * @return Color del jugador, o Ficha.VACIA si la partida ha terminado.
	 */
	public Ficha getTurno(){
		return turno;		
	}
	
	/**
	 * Devuelve el color del ganador. Sólo válido si la partida ya ha terminado (isTerminada() == true).
	 * @return Color del ganador. Si la partida terminó en tablas, Ficha.VACIA. Si la partida no ha terminado aún, el resultado es indeterminado.
	 */
	public Ficha getGanador(){
		return ganador;		
	}
	
	/**
	 * Método para saber si la partida ha conluído ya o no.
	 * @return true si la partida ha acabado.
	 */
	public boolean isTerminada(){		
		return terminada;		
	}
	
	/**
	 * Método de acceso al tablero. Dependiendo de cómo se haga la implementación
	 * del resto de clases (principalmente de la clase Controlador), 
	 * es posible que no se utilice para nada en la práctica. 
	 * Sin embargo, es necesario implementarlo para poder ejecutar los test de unidad.
	 * @return Estado del tablero actual.
	 */
	public Tablero getTablero(){		
		return tablero;		
	}
	
	/**
	 * Comprueba si se produce 4 en raya en horizontal.
	 * @param x - Número de columna (1..ancho)
	 * @param y - Número de fila (1..alto) 
	 * @return True si se ha completado el 4 en raya vertical, false si no se ha completado.
	 */
	public boolean comprobarHorizontal(int x, int y) {
		int enraya = 0;
		boolean comprueba = false;			


		for(int j = 1; j < tablero.getAncho(); j++){
			if((tablero.getCasilla(j, y) == tablero.getCasilla(j+1, y)) && (tablero.getCasilla(j, y) != Ficha.VACIA)){
				enraya++;
			}
			else{
				enraya = 0;
			}
			if(enraya == 3){
				comprueba = true;
			}
		
		}
		return comprueba;
	}
	
	/**
	 * Comprueba si se produce 4 en raya en vertical.
	 * @param x - Número de columna (1..ancho)
	 * @param y - Número de fila (1..alto) 
	 * @return True si se ha completado el 4 en raya vertical, false si no se ha completado.
	 */
	public boolean comprobarVertical(int x, int y) {
		int enraya = 0;
		boolean comprueba = false;			


		for(int j = 1; j < tablero.getAlto(); j++){
			if((tablero.getCasilla(x, j) == tablero.getCasilla(x, j+1)) && (tablero.getCasilla(x, j+1) != Ficha.VACIA)){
				enraya++;
			}
			else{
				enraya = 0;
			}
			if(enraya == 3){
				comprueba = true;
			}
		
		}
		return comprueba;
	}
	
	/**
	 * Comprueba si se produce 4 en raya por la diagonal ascendente hacia la izquierda o por la diagonal descendete hacia la derecha.
	 * @param x - Número de columna (1..ancho)
	 * @param y - Número de fila (1..alto) 
	 * @return True si se ha completado el 4 en raya diagonal ascendente hacia la izquierda
	 * o descendente hacia la derecha, false si no se ha completado. 
	 */
	public boolean comprobarDiagonalIzquierda(int x, int y) {
		
		boolean comprueba = false;
		
		comprueba=tablero.getCasilla(x+1, y-1)==turno && tablero.getCasilla(x+2, y-2)==turno
				&& tablero.getCasilla(x+3, y-3)==turno;
		
		if(!comprueba) {
			comprueba=tablero.getCasilla(x-1, y+1)==turno && tablero.getCasilla(x+1, y-1)==turno
					&& tablero.getCasilla(x+2, y-2)==turno;
		}
		
		if (!comprueba) {
			comprueba=tablero.getCasilla(x-1, y+1)==turno && tablero.getCasilla(x-2, y+2)==turno
					&& tablero.getCasilla(x+1, y-1)==turno;
		}
		
		if (!comprueba) {
			comprueba=tablero.getCasilla(x-1, y+1)==turno && tablero.getCasilla(x-2, y+2)==turno
					&& tablero.getCasilla(x-3, y+3)==turno;
		}
		
		return comprueba;
	}
	
	/**
	 * Comprueba si se produce 4 en raya por la diagonal ascendente hacia la derecha o por la diagonal descendete hacia la izquierda.
	 * @param x - Número de columna (1..ancho)
	 * @param y - Número de fila (1..alto) 
	 * @return True si se ha completado el 4 en raya diagonal ascendete hacia la derecha
	 * o descendente hacia la izquierda, false si no se ha completado.
	 */
	public boolean comprobarDiagonalDerecha(int x, int y) {
		boolean comprueba = false;
		
		comprueba=tablero.getCasilla(x+1, y+1)==turno && tablero.getCasilla(x+2, y+2)==turno
				&& tablero.getCasilla(x+3, y+3)==turno;
		
		if(!comprueba) {
			comprueba=tablero.getCasilla(x-1, y-1)==turno && tablero.getCasilla(x+1, y+1)==turno
					&& tablero.getCasilla(x+2, y+2)==turno;
		}
		
		if (!comprueba) {
			comprueba=tablero.getCasilla(x-2, y-2)==turno && tablero.getCasilla(x-1, y-1)==turno
					&& tablero.getCasilla(x+1, y+1)==turno;
		}
		
		if (!comprueba) {
			comprueba=tablero.getCasilla(x-3, y-3)==turno && tablero.getCasilla(x-2, y-2)==turno
					&& tablero.getCasilla(x-1, y-1)==turno;
		}
		
		return comprueba;
	}
	
	/**
	 * Comprueba si el tablero se ha completado y ha quedado la partida en tablas.
	 * @return True si la partida ha quedado en tablas, false si no.
	 */
	public boolean comprobarTablas(){
		boolean tablas = true;
		
		for(int i = 1; i <= tablero.getAncho(); i++){
			for(int j = 1; j <= tablero.getAlto(); j++){
				if(tablero.getCasilla(i, j) == Ficha.VACIA){
					tablas = false;
				}
			}
		}
		
		return tablas;
	}
}
			
	