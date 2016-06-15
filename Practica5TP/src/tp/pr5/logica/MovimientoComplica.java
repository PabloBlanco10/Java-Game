package tp.pr5.logica;

public class MovimientoComplica extends Movimiento {
	
	private Ficha perdida; // tiene que ser un atributo de la clase para ademas de estar en ejecuta movimiento tambien este en undo

	public MovimientoComplica(int donde, Ficha color) {
		super(color, donde);
		this.perdida = Ficha.VACIA;
	}

	@Override
	/*Comprobamos que se va a introducir dentro del tablero, y que no esta llena esa columna
	 *en caso de q este la columna llena se coloca encima de todo, se bajan todas hacia abajo, desaparece la ultima y se guarda.
	 *si no esta la columna llena se pone en la primera casilla vacia.
	 */
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		if(columna < 1 || columna > tab.getAncho()){
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y " + tab.getAncho() + ".");
		}
		else if (tab.getCasilla(columna, 1) != Ficha.VACIA){// col,1 porque la casilla (1,1) esta arriba a la izquierda 
			perdida = tab.getCasilla(columna, tab.getAlto());
			
			for(int i = tab.getAlto(); i >= 2; i--){
				Ficha encima = tab.getCasilla(columna, i-1);
				tab.setCasilla(columna, i, encima);
			}
			tab.setCasilla(columna, 1, jugador);
			return true;
		}		
		else{
			perdida = Ficha.VACIA;
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
	/* funcion de deshacer, si ficha perdida = vacia, undo = que el conecta
	 * si no, se mueven todas una casilla hacia arriba y se mete abajo del todo la perdida anteriormente.
	 */
	public void undo(Tablero tab) {
		if(perdida == Ficha.VACIA){
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
		else {
			for(int i = 1; i < tab.getAlto(); i++){
				Ficha debajo = tab.getCasilla(columna, i+1);
				tab.setCasilla(columna, i, debajo);
			}
			tab.setCasilla(columna, tab.getAlto(), perdida);
		}
	}

}
