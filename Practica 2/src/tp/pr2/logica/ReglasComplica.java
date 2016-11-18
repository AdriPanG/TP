package tp.pr2.logica;


/**
 * Implementación de las reglas del Complica. 
 * Se deben implementar todos los métodos del interfaz, además del constructor.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class ReglasComplica extends ReglasJuegoAbstracta{

	private Ficha turno; //Jugador que realiza la acción.
	
	/**
	 * Constructor de la clase, sin parámetros.
	 */
	public ReglasComplica(){
		super();
	}
	
	
	/**
	 * Construye el tablero que hay que utilizar para la partida, seg�n las reglas del juego.
	 * @return Tablero a utilizar. El estado del tablero ser� el de inicio de la partida.
	 */
	public Tablero iniciaTablero() {		
		return new Tablero(4,7);
	}
	
	

	/**
	 * Devuelve el color del jugador que comienza la partida.
	 * @return Color del primer jugador en colocar ficha.
	 */
	public Ficha jugadorInicial() {		
		return Ficha.BLANCA;
	}

	
	
	/**
	 * Permite averiguar si en la partida ya tenemos un ganador o no. 
	 * Devuelve el color del ganador (si lo hay).
	 * @param ultimoMovimiento - Ultimo movimiento realizado. 
	 * 							Las distintas implementaciones pueden utilizar este parámetro para optimizar la búsqueda del ganador.
	 * @param t - Estado del tablero.
	 * @return color del ganador, si lo hay. Si no lo hay, devuelve Ficha.VACIA 
	 * 		(eso NO significa necesariamente que la partida haya terminado en tablas).
	 */	
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		int contB = 0; //Número de 4 en raya que forman las fichas blancas.
		int contN = 0; //Número de 4 en raya que forman las fichas negras.
		int donde = ultimoMovimiento.getDonde();
		//Recorre todo el tablero
		for (int i = t.getAlto(); i >= 1; i--) {
			turno = t.getCasilla(donde, i);
			//Comprueba los posibles 4 en raya.
			if (comprobar4enraya(donde, i, turno, t)) {
				if(turno == Ficha.NEGRA)
					contN++;
				if(turno == Ficha.BLANCA)
					contB++;		
			}
		}
		//Devuelve el color del ganador dependiendo de los 4 en raya comprobados.
		if((contB > 0) && (contN == 0)) {
			turno = Ficha.BLANCA;
		} else if ((contN > 0) && (contB == 0)) {
			turno = Ficha.NEGRA;
		} else {
			turno = Ficha.VACIA; 
		}
		return turno;
	}

	
	
	/**
	 * Devuelve false ya que en este juego nuo puede acabar en tablas.
	 * @param ultimoEnPoner - Jugador que acaba de poner ficha
	 * @param t - Estado del tablero
	 * @return false, la partida no puede terminar por tablas.
	 */
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {				
		return false;		
	}

	
	
	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * @param ultimoEnPoner - Último jugador en poner ficha
	 * @param t - Estado del tablero.
	 * @return Siguiente jugador que debe poner ficha.
	 */
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {		
		if((ultimoEnPoner == Ficha.BLANCA)) {
			turno = Ficha.NEGRA;
		} else if (ultimoEnPoner == Ficha.NEGRA) {
			turno = Ficha.BLANCA;
		}
		return turno;
	}
}
