package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class JugadorHumanoConecta4 extends JugadorHumano implements Jugador {
	private Scanner in;
	private FactoriaTipoJuego f ;

	
	public JugadorHumanoConecta4(Scanner in, FactoriaTipoJuego f){
		this.in = in;
		this.f = f;
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		System.out.println("Introduce la columna:");
		int col = in.nextInt();
		in.nextLine();
		return f.creaMovimiento(col, 0, color);
		//return new MovimientoConecta4(col, color);
	}

}
