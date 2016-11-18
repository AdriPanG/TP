package tp.pr3.logica;

/**
 * Clase que contiene la pila de la aplicación. Sólo almacena los últimos 10 movimientos.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 2
 * @since Práctica 1
 */
public class Pila {
	
	private Movimiento[] undoStack; //Movimientos.
	private int numUndo; //Nº de posiciones ocupadas.
	
	/**
	 * Construye la pila.
	 */
	public Pila (){
		numUndo = 0;
		undoStack = new Movimiento[10];
	}
	
	/**
	 * Desplaza los elementos de la pila a una posición anterior.
	 */
	public void desplazarPila(){
		//Si la pila se llena entonces realiza el desplazamiento.
		if (numUndo+1 == undoStack.length){
			//Bucle que recorre la pila y desplazamos los movimientos a una posición anterior.
			for(int i = 0; i < numUndo; i++){
				undoStack[i] = undoStack[i + 1];
			}
										
		}else{
			numUndo++;
		}		
	}
	
	/**
	 * Método para obtener el contador de la pila.
	 * @return Número de posiciones ocupadas.
	 */
	public int getNumUndo() {
		return numUndo;
	}

	/**
	 * Método que permite modificar el contador de la pila.
	 * @param numUndo - Número de posiciones ocupadas.
	 */
	public void setNumUndo(int numUndo) {
		this.numUndo = numUndo;
	}
	
	/**
	 * Método para obtener el último movimiento introducido en la pila.
	 * @return Movimiento de la pila.
	 */
	public Movimiento cima() {
		if(!estaVacia()){
			return undoStack[numUndo];
		}
		return undoStack[-1];
	}

	
	
	public boolean estaVacia(){
		return (numUndo == 0);
	}
	
	/**
	 * Método que añade un movimiento en la pila.
	 * @param mov - Añade el movimiento en la pila.
	 */
	public void apilar(Movimiento mov) {
		desplazarPila();
		this.undoStack[numUndo] = mov;
	}
	
	/**
	 * Método que quita un movimiento de la pila.
	 * @return La pila sin el último movimiento.
	 */
	public Movimiento desapilar() {
		if(!estaVacia()){
			return this.undoStack[numUndo--];
		}
		return this.undoStack[numUndo];
	}
	
}
