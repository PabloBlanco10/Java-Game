package tp.pr5.logica;

import tp.pr5.control.TipoJuego;

public interface ReglasJuego {
	
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t);
	
	public Tablero iniciaTablero();
	
	public Ficha jugadorInicial();
	
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t);
	
	public boolean tablas(Ficha ultimoEnPoner, Tablero t);

	public TipoJuego getTipoJuego();

	public int getLimite();
}
