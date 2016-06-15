package tp.pr5.logica;

public abstract class Movimiento {

	protected Ficha jugador;
	protected int columna;

	//CONSTRUCTOR
	public Movimiento(Ficha color, int columna) {
		this.jugador = color;
		this.columna = columna;
	}
	
	public Ficha getJugador(){
		return this.jugador;
	}
	
	public abstract boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido;
	
	public abstract void undo(Tablero tab);

}
