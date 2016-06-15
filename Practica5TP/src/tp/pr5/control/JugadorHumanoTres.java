package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;


public class JugadorHumanoTres extends JugadorHumano implements Jugador {
	private Scanner in;
	private FactoriaTipoJuego f ;

	
	public JugadorHumanoTres(Scanner in, FactoriaTipoJuego f){
		this.in = in;
		this.f = f;
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		System.out.println("Introduce la columna:");
		int col = in.nextInt();
		in.nextLine();
		return f.creaMovimiento(col, 0, color);
		//return new MovimientoTres(col, color);
	}

}

