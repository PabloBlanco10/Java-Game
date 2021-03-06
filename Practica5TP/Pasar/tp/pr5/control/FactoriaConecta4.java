package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoConecta4;
import tp.pr5.logica.ReglasConecta4;
import tp.pr5.logica.ReglasJuego;

public class FactoriaConecta4 implements FactoriaTipoJuego{

	private int limite;
	
	public FactoriaConecta4() {
	}

	public FactoriaConecta4(int limite) {
		// TODO Auto-generated constructor stub
		this.limite = limite;
	}

	@Override
	public  Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioConecta4(this);
	}

	@Override
	public  Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoConecta4(in, this);
	}

	@Override
	public  Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoConecta4(col, color);
	}

	@Override
	public  ReglasJuego creaReglas() {
		return new ReglasConecta4(limite);
	}

}
