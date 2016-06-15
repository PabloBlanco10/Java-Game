package tp.pr5.logica;

import java.util.Vector;


public class MovimientoReversi extends Movimiento {
	
	private class Dupla {
		int col; //columna
		int fil; // fila
		
		public Dupla(int c, int f){
			this.col = c;
			this.fil = f;
		}
		
	}

	private int fila;
	private Vector<Dupla> v = new Vector<Dupla>();
	
	public MovimientoReversi(int columna,int fila, Ficha color) {
		super(color, columna);
		this.fila = fila;
	}

	@Override
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		if(columna < 1 || columna > tab.getAncho()){
			throw new MovimientoInvalido("Posici�n incorrecta.");
		}
		
		else if (fila < 1 || fila > tab.getAlto()){
			throw new MovimientoInvalido("Posici�n incorrecta.");
		}
		
		else if (tab.getCasilla(columna, fila) != Ficha.VACIA){
			throw new MovimientoInvalido("Casilla ocupada.");
		}
		else if (!Comprobador.flanqueo(tab, fila, columna, jugador)){
			throw new MovimientoInvalido("Posici�n Incorrecta.");
		}
		else {
			int incrX;
			int incrY;
			
			
			for (int j = 0; j < Comprobador.getDirecciones().size(); j++){
				/* 
				 * Para cada direccion(norte, sur etc) existe una i asociada,
				 * el vector de direcciones del comprobador guarda varias Duplas
				 * (direccion, i). Hacemos esto para trabajar mas facilmente con las
				 * direcciones.
				 * NOROESTE	- 0
				 * NORTE 	- 1
				 * NORESTE 	- 2
				 * OESTE	- 3
				 * NONE		- 4
				 * ESTE		- 5
				 * SUROESTE	- 6
				 * SUR		- 7
				 * SURESTE	- 8
				 */
				int i = Comprobador.getDirecciones().get(j).i;
				//incrY
				if(i < 3){
					incrY = -1;
				}
				
				else if(i < 6){
					incrY = 0;
				}
				
				else{
					incrY = 1;
				}
				
				//incrX
				if(i % 3 == 0){
					incrX = -1;
				}
				
				else if((i % 3) - 1 == 0){
					incrX = 0;
				}
				
				else{
					incrX = 1;
				}
				int f = fila + incrY;
				int c = columna + incrX;
				
				while(tab.getCasilla(c, f) != jugador){
					tab.setCasilla(c, f, jugador);
					v.add(new Dupla(c,f));
					c += incrX;
					f += incrY;
					
				}
			}
			tab.setCasilla(columna, fila, jugador);
			return true;
		}
	}

	@Override
	public void undo(Tablero tab) {
		Ficha contraria = Ficha.BLANCA;
		if(jugador == Ficha.BLANCA){
			contraria = Ficha.NEGRA;
		}
		else if (jugador == Ficha.NEGRA){
			contraria = Ficha.BLANCA;
		}
		
		
		tab.setCasilla(columna, fila, Ficha.VACIA);
		
		for(Dupla d: v){
			tab.setCasilla(d.col, d.fil, contraria);
		}

		v.clear();
	}

}
