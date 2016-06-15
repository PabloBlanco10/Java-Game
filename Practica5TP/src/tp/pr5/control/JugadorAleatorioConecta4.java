package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioConecta4 extends JugadorAleatorio implements Jugador{
	private FactoriaTipoJuego f;

	public JugadorAleatorioConecta4(FactoriaTipoJuego f) {
		// TODO Auto-generated constructor stub
		this.f = f;
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		//int x; //fila
		int y; //columna
		boolean parar = false;
		
		do {
			y = (int)(Math.random() * tab.getAncho()) + 1;
			//x = (int)(Math.random() * tab.getAlto());
		
			if(tab.getCasilla(y, 1) == Ficha.VACIA){
				parar = true;
			}
			
		}while(!parar);
		return f.creaMovimiento(y, 0, color);
		//return new MovimientoConecta4(y, color);
	}

}
