package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoGravity;
import tp.pr5.logica.ReglasGravity;
import tp.pr5.logica.ReglasJuego;

public class FactoriaGravity implements FactoriaTipoJuego{
	private int alto;
	private int ancho;
	
	public FactoriaGravity(int col, int fil){
		this.ancho = col;
		this.alto = fil;
	}

	public FactoriaGravity() {
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity(this);
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoGravity(in,this);
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoGravity(col, fila, color);
	}

	@Override
	public ReglasJuego creaReglas() {
		return new ReglasGravity(ancho, alto);
	}

}