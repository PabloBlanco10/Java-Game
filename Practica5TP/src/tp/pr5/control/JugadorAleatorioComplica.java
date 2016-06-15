package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioComplica extends JugadorAleatorio implements Jugador {
	private FactoriaTipoJuego f;
	
	public JugadorAleatorioComplica(FactoriaTipoJuego f) {
		this.f = f;
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		int y; //columna
		y = (int)(Math.random() * tab.getAncho()) + 1;
		return f.creaMovimiento(y, 0, color);
		//DEVOLVER MOVIMIENTO DESDE FACTORIA F.CREAMOVIMIENTO
	}

}
