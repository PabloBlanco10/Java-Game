package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class JugadorHumanoGravity extends JugadorHumano implements Jugador {
	private Scanner in;
	private FactoriaTipoJuego f ;

	
	public JugadorHumanoGravity(Scanner in, FactoriaTipoJuego f){
		this.in = in;
		this.f = f;
	}
	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		int col;
		int fil;
		System.out.println("Introduce la columna:");
		col = in.nextInt();
		in.nextLine();
		System.out.println("Introduce la fila:");
		fil = in.nextInt();
		in.nextLine();
		return f.creaMovimiento(col, fil, color);
		//return new MovimientoGravity(col, fil, color);
	}

}
