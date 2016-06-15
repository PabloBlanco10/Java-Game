package tp.pr5.logica;

import tp.pr5.control.TipoJuego;
import tp.pr5.control.TipoJugador;

public interface PartidaObserver {
	// gestion de p a r t i d a
	void onReset(TableroInmutable tab, Ficha turno);

	void onPartidaTerminada(TableroInmutable tablero, Ficha ganador);

	void onCambioJuego(TableroInmutable tab, Ficha turno, TipoJuego juego);

	// G e s t i � n de m ovimi e n t o s
	void onUndoNotPossible(TableroInmutable tablero, Ficha turno);

	void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas, int blancas, int negras);

	void onMovimientoEnd(TableroInmutable tablero, Ficha jugador, Ficha turno, int blancas, int negras);

	void onMovimientoIncorrecto(MovimientoInvalido movimientoException);
	
	void onFichasCambiadas(int blancas, int negras);

	void onLimiteAlcanzado();
	

}
