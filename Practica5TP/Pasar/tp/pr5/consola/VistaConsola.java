package tp.pr5.consola;

import tp.pr5.control.ControladorConsola;
import tp.pr5.control.TipoJuego;
import tp.pr5.control.TipoJugador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.PartidaObserver;
import tp.pr5.logica.TableroInmutable;

public class VistaConsola implements PartidaObserver{
	
	
	public VistaConsola(ControladorConsola c, TableroInmutable t, Ficha turno){
		c.addObserver(this);
		System.out.println(tableroToString(t));
		System.out.println("Juegan " + turno.toString().toLowerCase() + "s");
	}
	@Override
	public void onReset(TableroInmutable tab, Ficha turno) {
		System.out.println(tableroToString(tab));
		System.out.println("Juegan " + turno.toString().toLowerCase() + "s");

		System.out.println("Movimientos Negras: 0" );
		System.out.println("Movimientos Blancas: 0");
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		
		if(ganador == Ficha.BLANCA){
			System.out.println("Ganan las blancas");
		}
		else if(ganador == Ficha.NEGRA){
			System.out.println("Ganan las negras");

		}
		else{
			System.out.println("Partida terminada en tablas.");

		}
		
		
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno, TipoJuego juego) {
		//no haria faltar poner nada porque ya lo hacemos en el reset
		//System.out.println("Tipo de Juego cambiado.");
		//System.out.println("Juegan " + turno.toString() + "s");

		System.out.println("Movimientos Negras: 0" );
		System.out.println("Movimientos Blancas: 0");
		
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		System.out.println("Imposible deshacer");
		System.out.println("Juegan " + turno.toString().toLowerCase() + "s");
		
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas, int blancas, int negras) {
		System.out.println(tableroToString(tablero));
		System.out.println("Juegan " + turno.toString().toLowerCase() + "s");
		System.out.println("Movimientos Negras: " + negras);
		System.out.println("Movimientos Blancas: " + blancas);
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno, int blancas, int negras) {
		System.out.println(tableroToString(tablero));
		System.out.println("Juegan " + turno.toString().toLowerCase() + "s");
		System.out.println("Movimientos Negras: " + negras);
		System.out.println("Movimientos Blancas: " + blancas);
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		System.err.println(movimientoException.getMessage());
		
	}
	
	private String tableroToString(TableroInmutable t){
		String s = "";
		String aux = " ";
		for(int i = 1; i <= t.getFilas(); i++){ // de abajo arriba
			s += "|";
			for(int j = 1; j <= t.getColumnas(); j++){ // de izquierda a derecha
				if (t.getCasilla(j, i) == Ficha.VACIA){
					s += " ";
				}
				else if (t.getCasilla(j, i) == Ficha.BLANCA){
					s += "O";
				}
				else {
					s += "X";
				}
			}
			s += "|" + "\n";
		}
		s += "+";
		for(int j = 1; j <= t.getColumnas(); j++){
			s += "-";
			aux += j % 10; // para que vaya poniendo a la vez los n�meros
		}
		s += "+" + "\n"; 
		s += aux + "\n";
		
		return s;
	}
	@Override
	public void onFichasCambiadas(int blancas, int negras) {
		//no hace nada
	}
	
	@Override
	public void onLimiteAlcanzado() {
		// TODO Auto-generated method stub
		System.err.println("Límite movimientos alcanzado");
		System.exit(0);
	}

	

}
