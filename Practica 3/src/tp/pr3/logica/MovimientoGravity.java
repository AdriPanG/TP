package tp.pr3.logica;

/**
 * Clase que implementa el movimiento para el juego del Gravity. 
 * Se deben implementar los métodos abstractos de la clase padre.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 * @since Práctica 3
 */
public class MovimientoGravity extends Movimiento {
		
	/**
	 * Constructor del objeto.
	 * @param columna - Columna en la que se colocará la ficha.
	 * @param fila - Fila en la que se colocará la ficha.
	 * @param color - Color de la ficha que se pondrá (o jugador que pone).
	 */
	public MovimientoGravity(int columna, int fila, Ficha color) {
		super(columna, color);
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
					if (tab.getCasilla(donde, fila) == Ficha.VACIA){	
						ejecuta = movimientoGravity(color, tab);								
					} else {
						throw new MovimientoInvalido("Casilla ocupada.");
					} 
			} else {
				throw new MovimientoInvalido("Posición incorrecta.");
			}
		}
		return ejecuta;
	}

	/**
	 * Deshace el movimiento en el tablero recibido como parámetro. 
	 * Se puede dar por cierto que el movimiento se ejecuta sobre ese tablero; 
	 * en caso contrario, el comportamiento es indeterminado.
	 * Por lo tanto, es de suponer que el método siempre funcionará correctamente.
	 * @param tab - Tablero de donde deshacer el movimiento.
	 */
	public void undo(Tablero tab) {
			if (tab.getCasilla(donde, fila) != Ficha.VACIA)
				tab.setCasilla(donde, fila, Ficha.VACIA);
	}
	
	private boolean movimientoGravity(Ficha color, Tablero tab) {
		boolean ejecuta = false;
		
		int izquierda = donde - 1 ;
		int derecha = tab.getAncho() - donde;
		int arriba = fila - 1;
		int abajo = tab.getAlto() - fila;		
		
		
		//Desplaza a la izquierda.	
		if ((izquierda < derecha) && (abajo < arriba) && (izquierda < abajo)) {
			while (donde > 1 && !ejecuta) {
				if (tab.getCasilla(donde - 1, fila) != Ficha.VACIA) {
					tab.setCasilla(donde, fila, color);
					ejecuta = true;
				} else {
					donde--;
				}
			}
		}
		//Desplaza hacia abajo
		else if ((izquierda < derecha) && (abajo < arriba) && (abajo < izquierda)) {
			while (fila < tab.getAlto()  && !ejecuta) {
				if (tab.getCasilla(donde, fila + 1) != Ficha.VACIA) {
					tab.setCasilla(donde, fila, color);
					ejecuta = true;
				} else {
					fila++;
				}
			}
		}
		//Desplaza en diagonal izquierda-abajo
		else if ((izquierda < derecha) && (abajo < arriba) && (abajo == izquierda)) {
			while ((donde > 1) && (fila < tab.getAlto()) && !ejecuta) {
				if (tab.getCasilla(donde - 1, fila + 1) != Ficha.VACIA) {
					tab.setCasilla(donde, fila, color);
					ejecuta = true;
				} else {
					donde--;
					fila++;
				}
			}
		}
		//Desplaza a la izquierda
		else if ((izquierda < derecha) && (arriba < abajo) && (izquierda < arriba)) {
			while (donde > 1 && !ejecuta) {
				if (tab.getCasilla(donde - 1, fila) != Ficha.VACIA) {
					tab.setCasilla(donde, fila, color);
					ejecuta = true;
				} else {
					donde--;
				}
			}
		}
		//Desplaza hacia arriba
		else if ((izquierda < derecha) && (arriba < abajo) && (arriba < izquierda)) {
			while (fila > 1 && !ejecuta) {
				if (tab.getCasilla(donde, fila - 1) != Ficha.VACIA) {
					tab.setCasilla(donde, fila, color);
					ejecuta = true;
				} else {
					fila--;
				}
			}
		}
		//Desplaza en diagonal izquierda-arriba
				else if ((izquierda < derecha) && (arriba < abajo) && (arriba == izquierda)) {
					while ((donde > 1) && (fila > 1) && !ejecuta) {
						if (tab.getCasilla(donde - 1, fila - 1) != Ficha.VACIA) {
							tab.setCasilla(donde, fila, color);
							ejecuta = true;
						} else {
							donde--;
							fila--;
						}
					}
				}
		//Desplaza a la derecha
				else if ((derecha < izquierda) && (abajo < arriba) && (derecha < abajo)) {
					while (donde < tab.getAncho() && !ejecuta) {
						if (tab.getCasilla(donde + 1, fila) != Ficha.VACIA) {
							tab.setCasilla(donde, fila, color);
							ejecuta = true;
						} else {
							donde++;
						}
					}
				}
		//Desplaza hacia abajo
				else if ((derecha < izquierda) && (abajo < arriba) && (abajo < derecha)) {
					while (fila < tab.getAlto() && !ejecuta) {
						if (tab.getCasilla(donde, fila + 1) != Ficha.VACIA) {
							tab.setCasilla(donde, fila, color);
							ejecuta = true;
						} else {
							fila++;
						}
					}
				}
		//Desplaza hacia diagonal derecha-abajo
				else if ((derecha < izquierda) && (abajo < arriba) && (derecha == abajo)) {
					while ((donde < tab.getAncho()) && (fila < tab.getAlto()) && !ejecuta) {
						if (tab.getCasilla(donde + 1, fila + 1) != Ficha.VACIA) {
							tab.setCasilla(donde, fila, color);
							ejecuta = true;
						} else {
							donde++;
							fila++;
						}
					}
				}
		//Desplaza a la derecha
				else if ((derecha < izquierda) && (arriba < abajo) && (derecha < arriba)) {
					while (donde < tab.getAncho() && !ejecuta) {
						if (tab.getCasilla(donde + 1, fila) != Ficha.VACIA) {
							tab.setCasilla(donde, fila, color);
							ejecuta = true;
						} else {
							donde++;
						}
					}
				}
		//Desplaza hacia arriba
				else if ((derecha < izquierda) && (arriba < abajo) && (arriba < derecha)) {
					while (fila > 1 && !ejecuta) {
						if (tab.getCasilla(donde, fila - 1) != Ficha.VACIA) {
							tab.setCasilla(donde, fila, color);
							ejecuta = true;
						} else {
							fila--;
						}
					}
				}
		//Desplaza hacia diagonal derecha-arriba
				else if ((derecha < izquierda) && (arriba < abajo) && (derecha == arriba)) {
					while ((donde < tab.getAncho()) && (fila > 1) && !ejecuta) {
						if (tab.getCasilla(donde + 1, fila - 1) != Ficha.VACIA) {
							tab.setCasilla(donde, fila, color);
							ejecuta = true;
						} else {
							donde++;
							fila--;
						}
					}
				}
			//Equilibrio
				else if ((derecha == izquierda) && (arriba == abajo) && (derecha == abajo)) {
					tab.setCasilla(donde, fila, color);
					ejecuta = true;
				}
			//Equilibrio izquierda-derecha, va hacia arriba
				else if ((derecha == izquierda) && (arriba < abajo) && (arriba <= derecha)) {
					while (fila > 1 && !ejecuta) {
						if (tab.getCasilla(donde, fila - 1) != Ficha.VACIA) {
							tab.setCasilla(donde, fila, color);
							ejecuta = true;
						} else {
							fila--;
						}
					}
				}
		//Equilibrio izquierda-derecha, va hacia abajo
				else if ((derecha == izquierda) && (abajo < arriba) && (abajo <= derecha)) {
					while (fila < tab.getAlto() && !ejecuta) {
						if (tab.getCasilla(donde, fila + 1) != Ficha.VACIA) {
							tab.setCasilla(donde, fila, color);
							ejecuta = true;
						} else {
							fila++;
						}
					}
				}
		//Equilibrio arriba-abajo, va hacia la izquierda
				else if ((izquierda < derecha) && (arriba == abajo) && (izquierda <= arriba)) {
					while (donde > 1 && !ejecuta) {
						if (tab.getCasilla(donde - 1, fila) != Ficha.VACIA) {
							tab.setCasilla(donde, fila, color);
							ejecuta = true;
						} else {
							donde--;
						}
					}
				}
		//Equilibrio arriba-abajo, va hacia la derecha
				else if ((derecha < izquierda) && (arriba == abajo) && (derecha <= arriba)) {
					while (donde < tab.getAncho() && !ejecuta) {
						if (tab.getCasilla(donde + 1, fila) != Ficha.VACIA) {
							tab.setCasilla(donde, fila, color);
							ejecuta = true;
						} else {
							donde++;
						}
					}
				}
			
		if(ejecuta == false) {
			tab.setCasilla(donde, fila, color);
			ejecuta = true;
		}
				
		return ejecuta;
	}

}
