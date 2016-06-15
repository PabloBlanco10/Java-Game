package tp.pr5.control;

import java.util.Vector;

import tp.pr5.logica.Comprobador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioReversi extends JugadorAleatorio {

	private FactoriaTipoJuego f ;
	private class Dupla {
		int col; //columna
		int fil; // fila
		
		public Dupla(int c, int f){
			this.col = c;
			this.fil = f;
		}
		
	}
	private Vector<Dupla> v = new Vector<Dupla>();
	
	public JugadorAleatorioReversi(FactoriaTipoJuego f) {
		this.f = f;
	}
	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		int x; //fila
		int y; //columna
		boolean parar = false;
		
		/*do {
			y = (int)(Math.random() * tab.getAncho()) + 1;
			x = (int)(Math.random() * tab.getAlto()) + 1;
		
			if(tab.getCasilla(y, x) == Ficha.VACIA && Comprobador.flanqueo(tab, x, y, color)){
				parar = true;
			}
			
		}while(!parar);
		return f.creaMovimiento(v, x, color);*/
		
		v.clear();
		for(int i = 1; i <= tab.getFilas(); i++){
			for(int j = 1; j <= tab.getColumnas(); j++){
				if(tab.getCasilla(j, i) == Ficha.VACIA && Comprobador.flanqueo(tab, i, j, color)){
					v.add(new Dupla(j, i));
				}
			}
		}
		x = (int)(Math.random() * (v.size() - 1)) ;
		return f.creaMovimiento(v.get(x).col, v.get(x).fil, color);
	}

}
