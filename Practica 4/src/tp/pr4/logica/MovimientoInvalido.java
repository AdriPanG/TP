package tp.pr4.logica;


/** 
 * Excepción generada cuando se intenta ejecutar un movimiento incorrecto.
 * @author Adrián Panadero González
 * @author Cristina Barquilla Blanco
 * @version 1
 */
public class MovimientoInvalido extends java.lang.Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/** 
	 * Constructor sin parámetros.
	 */
	public MovimientoInvalido(){
		
	}
	
	
	/** 
	 * Constructor con un parámetro para el mensaje.
	 * @param msg - Mensaje.
	 */
	public MovimientoInvalido(java.lang.String msg){
		super(msg);
	}
	
	
	/** 
	 * Constructor con un parámetro para el mensaje y otra para la causa.
	 * @param msg
	 * @param arg
	 */
	public MovimientoInvalido(java.lang.String msg, java.lang.Throwable arg){
		super(msg, arg);
	}
	
	
	/** 
	 * Constructor con un parámetro para la causa inicial que provocó la excepción.
	 * @param arg
	 */
	public MovimientoInvalido(java.lang.Throwable arg){
		super(arg);
	}
}
