package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoComplica;
import tp.pr5.logica.ReglasComplica;
import tp.pr5.logica.ReglasJuego;

public class FactoriaComplica implements FactoriaTipoJuego{

	private int limite;
	public FactoriaComplica(int limite) {
		// TODO Auto-generated constructor stub
		this.limite = limite;
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioComplica(this);
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoComplica(in,this);
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoComplica(col, color);
	}

	@Override
	public ReglasJuego creaReglas() {
		return new ReglasComplica(limite);
	}

}
