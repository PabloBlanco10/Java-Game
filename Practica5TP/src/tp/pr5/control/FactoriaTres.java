package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoTres;
import tp.pr5.logica.ReglasTres;
import tp.pr5.logica.ReglasJuego;

public class FactoriaTres implements FactoriaTipoJuego{
	private int alto;
	private int ancho;
	
	public FactoriaTres(int col, int fil){
		this.ancho = col;
		this.alto = fil;
	}

	public FactoriaTres() {
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioTres(this);
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoTres(in,this);
	}

	@Override
	public Movimiento creaMovimiento(int col,int fila,  Ficha color) {
		return new MovimientoTres(col, color);
	}

	@Override
	public ReglasJuego creaReglas() {
		return new ReglasTres(ancho, alto);
	}

}
