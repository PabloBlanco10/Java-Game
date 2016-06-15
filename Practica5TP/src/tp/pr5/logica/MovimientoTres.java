package tp.pr5.logica;
public class MovimientoTres extends Movimiento {
	
	// no ponemos Ficha color porque ya hemos declarado en Movimiento jugador
	
	
	//CONSTRUCTOR
	public MovimientoTres(int columna, Ficha color) {
		super(color, columna); // heredado de la clase Movimiento
	}

	

	@Override
	/*Comprobamos que se va a introducir dentro del tablero, y que no esta llena esa columna
	 *si esta llena se devuelve false, sino recorre desde abajo hasta encontrar una vacia y la mete.
	 */
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		if(columna < 1 || columna > tab.getAncho()){
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y " + tab.getAncho() + ".");
		}
		else if (tab.getCasilla(columna, 1) != Ficha.VACIA){// col,1 porque la casilla (1,1) esta arriba a la izquierda 
			throw new MovimientoInvalido("Columna llena.");
		}		
		else{
			int i = tab.getAlto();// empezamos abajo  y subimos hacia arriba
			/*
			 * |	   |
			 * |	   |
			 * |	   |
			 * |	   v	
			 * -------->
			 */
			boolean encontrada = false;
			//buscamos una posicion libre para colocar la casilla
			while(!encontrada && i >= 1){
				if(tab.getCasilla(columna,i) == Ficha.VACIA){
					encontrada = true;
					tab.setCasilla(columna, i, jugador);
				}
				else {
					i--;
				}
			}
			return true;
		} 
	}

	
	@Override
	/* funcion de deshacer
	 * quita la ultima ficha introducida anteriormente
	 */
	public void undo(Tablero tab) {
		boolean encontrada = false;
		int i = 1;
		while(!encontrada && i <= tab.getAlto()){
			if(tab.getCasilla(columna,i) != Ficha.VACIA){
				encontrada = true;
				if(tab.getCasilla(columna,i) == jugador){
					tab.setCasilla(columna, i, Ficha.VACIA);
				}
			}
			else {
				i++;
			}
		}
			
	}

}
