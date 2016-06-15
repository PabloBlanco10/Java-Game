package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.ReglasJuego;

public interface FactoriaTipoJuego {

	
	 public Jugador creaJugadorAleatorio();
	
	 public Jugador creaJugadorHumanoConsola(java.util.Scanner in);
	
	 public Movimiento creaMovimiento(int col, int fila, Ficha color);
	
	 public ReglasJuego creaReglas();
}
