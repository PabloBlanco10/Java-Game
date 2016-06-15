package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioGravity extends JugadorAleatorio implements Jugador {
	private FactoriaTipoJuego f ;
	
	public JugadorAleatorioGravity(FactoriaTipoJuego f) {
		this.f = f;
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		int x; //fila
		int y; //columna
		boolean parar = false;
		
		do {
			y = (int)(Math.random() * tab.getAncho()) + 1;
			x = (int)(Math.random() * tab.getAlto()) + 1;
		
			if(tab.getCasilla(y, x) == Ficha.VACIA){
				parar = true;
			}
			
		}while(!parar);
		return f.creaMovimiento(y, x, color);
		//return new MovimientoGravity(y,x, color);
	}

}