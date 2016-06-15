package tp.pr5.logica;

public class MovimientoInvalido extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovimientoInvalido() {
		
	}
	
	public MovimientoInvalido(java.lang.String msg) {
		super(msg);
	}
	
	public MovimientoInvalido(java.lang.String msg, java.lang.Throwable arg) {
		super(msg, arg);
	}
	
	public MovimientoInvalido(java.lang.Throwable arg) {
		super(arg);
	}

}
