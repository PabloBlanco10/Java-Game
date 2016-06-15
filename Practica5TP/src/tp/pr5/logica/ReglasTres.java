package tp.pr5.logica;

import tp.pr5.control.TipoJuego;

public class ReglasTres implements ReglasJuego {
	
	private final int PREMIO = 3;

	
	private int nCols;
	private int nFilas;

	public ReglasTres(int numCols, int numFilas) {
		this.nCols = numCols;
		this.nFilas = numFilas;
	}
	
	@Override
	//se comprueban todas las maneras de conseguir 4 en raya

	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha ganador = Ficha.VACIA;
		
		ganador = compruebaHorizontal(t);
		if (ganador == Ficha.VACIA){
			ganador = compruebaVertical(t);
			if(ganador == Ficha.VACIA){
				ganador = compruebaDiagonalNormal(t);
				if(ganador == Ficha.VACIA){
					return compruebaDiagonalInversa(t);
				}
			}			
		}
		return ganador;
	}
		private Ficha compruebaHorizontal(Tablero tablero){
			Ficha ganador = Ficha.VACIA;
			boolean encontrado = false;
			int i = 1; //alto
			int j = 1; //ancho
			int seguidas = 0; //para premio seguidas = 4!! empezamos en 0 y comprobamos si es igual
			Ficha actual = Ficha.BLANCA;
			while (!encontrado && i <= tablero.getAlto()){
				j = 1;
				seguidas = 0;
				while(!encontrado && j <= tablero.getAncho()){
					if(tablero.getCasilla(j, i) == actual){ //compruebo si es de mi mismo color
						seguidas++;
						if(seguidas == PREMIO){
							ganador = actual;
							encontrado = true;
						}
					}
					else{ // no es de mi color entonces compruebo si vacia
						if(tablero.getCasilla(j, i) == Ficha.VACIA){
							seguidas = 0;
						}
						else { // en este caso es del otro color
							seguidas = 1;
							actual = tablero.getCasilla(j, i); //asignamos el color a actual
						}
					}
					j++;
				}
				i++;
			}
			return ganador;
		}
		
		
		private Ficha compruebaVertical(Tablero tablero){
			Ficha ganador = Ficha.VACIA;
			boolean encontrado = false;
			int i = 1; //ancho
			int j = 1; //alto
			int seguidas = 0; //para premio seguidas = 4!! empezamos en 0 y comprobamos si es igual
			Ficha actual = Ficha.BLANCA;
			while (!encontrado && i <= tablero.getAncho()){
				j = 1;
				seguidas = 0;
				while(!encontrado && j <= tablero.getAlto()){
					//todas las i,j est�n cambiadas porque getCasilla primero recibe el alto y luego el ancho
					if(tablero.getCasilla(i, j) == actual){ //compruebo si es de mi mismo color
						seguidas++;
						if(seguidas == PREMIO){
							ganador = actual;
							encontrado = true;
						}
					}
					else{ // no es de mi color entonces compruebo si vacia
						if(tablero.getCasilla(i, j) == Ficha.VACIA){
							seguidas = 0;
						}
						else { // en este caso es del otro color
							seguidas = 1;
							actual = tablero.getCasilla(i, j); //asignamos el color a actual
						}
					}
					j++;
				}
				i++;
			}
			return ganador;
		}
		
		
		
		private Ficha compruebaDiagonalNormal(Tablero tablero){ // normal = /
			Ficha ganador = Ficha.VACIA;
			int i = 1;
			while (ganador == Ficha.VACIA && i <= tablero.getAlto()){ 
				ganador = compruebaDiagonalNormalSuperior(tablero, i);
				i++;
			}
			i = 2; // empezamos en 2 porque la diagonal principal ya la hemos comprobado en el bucle anterior
			while(ganador == Ficha.VACIA && i <= tablero.getAncho()){
				ganador = compruebaDiagonalNormalInferior(tablero, i);
				i++;
			}
		

			return ganador;
		}
		
		
		private Ficha compruebaDiagonalNormalSuperior(Tablero tablero, int fila){
			Ficha ganador = Ficha.VACIA;
			boolean encontrada = false;
			int incremento = 0;
			int seguidas = 0;
			Ficha actual = Ficha.BLANCA;
			while(!encontrada && ((fila - incremento) >= 1) && (incremento + 1 <= tablero.getAncho())){
				if(tablero.getCasilla(incremento + 1, fila - incremento) == actual){ //compruebo si es de mi mismo color y empezamos en incremento + 1 para que sea la col 1
					seguidas++;
					if(seguidas == PREMIO){
						ganador = actual;
						encontrada = true;
					}
				}
				else{ // no es de mi color entonces compruebo si vacia
					if(tablero.getCasilla(incremento + 1, fila - incremento) == Ficha.VACIA){
						seguidas = 0;
					}
					else { // en este caso es del otro color
						seguidas = 1;
						actual = tablero.getCasilla(incremento + 1, fila - incremento); //asignamos el color a actual
					}
				}
				incremento++;
			}
			return ganador;
		}
		
		
		private Ficha compruebaDiagonalNormalInferior(Tablero tablero, int col){
			Ficha ganador = Ficha.VACIA;
			boolean encontrada = false;
			int incremento = 0;
			int seguidas = 0;
			Ficha actual = Ficha.BLANCA;
			while(!encontrada && (col + incremento) <= tablero.getAncho() && (tablero.getAlto() - incremento >= 1)){
				if(tablero.getCasilla(col + incremento, tablero.getAlto() - incremento) == actual){ //compruebo si es de mi mismo color
					seguidas++;
					if(seguidas == PREMIO){
						ganador = actual;
						encontrada = true;
					}
				}
				else{ // no es de mi color entonces compruebo si vacia
					if(tablero.getCasilla(col + incremento, tablero.getAlto() - incremento) == Ficha.VACIA){
						seguidas = 0;
					}
					else { // en este caso es del otro color
						seguidas = 1;
						actual = tablero.getCasilla(col + incremento, tablero.getAlto() - incremento); //asignamos el color a actual
					}
				}
				incremento++;
			}
			return ganador;
		}
		
		
		
		private Ficha compruebaDiagonalInversa(Tablero tablero){ // inversa = \
			Ficha ganador = Ficha.VACIA;
			int i = 1;
			while (ganador == Ficha.VACIA && i <= tablero.getAncho()){ 
				ganador = compruebaDiagonalInversaInferior(tablero, i);
				i++;
			}
			
			i = 1;
			while(ganador == Ficha.VACIA && i < tablero.getAlto()){
				ganador = compruebaDiagonalInversaSuperior(tablero, i);
				i++;
			}
			

			return ganador;
			
		}
		
		
		private Ficha compruebaDiagonalInversaSuperior(Tablero tablero, int fila){
			Ficha ganador = Ficha.VACIA;
			boolean encontrada = false;
			int incremento = 0;
			int seguidas = 0;
			Ficha actual = Ficha.BLANCA;
			while(!encontrada && (fila - incremento) >= 1 && incremento <= tablero.getAncho()){
				// (ancho - 1) - incremento para saber la columna i y fila + incremento indica la fila i
				if(tablero.getCasilla(tablero.getAncho() - incremento, fila - incremento) == actual){ 
					seguidas++;
					if(seguidas == PREMIO){
						ganador = actual;
						encontrada = true;
					}
				}
				else{ // no es de mi color entonces compruebo si vacia
					if(tablero.getCasilla(tablero.getAncho() - incremento, fila - incremento) == Ficha.VACIA){
						seguidas = 0;
					}
					else { // en este caso es del otro color
						seguidas = 1;
						actual = tablero.getCasilla(tablero.getAncho() - incremento, fila - incremento); //asignamos el color a actual
					}
				}
				incremento++;
			}
			return ganador;
		}
		
		
		private Ficha compruebaDiagonalInversaInferior(Tablero tablero, int col){
			Ficha ganador = Ficha.VACIA;
			boolean encontrada = false;
			int incremento = 0;
			int seguidas = 0;
			Ficha actual = Ficha.BLANCA;
			while(!encontrada && (col - incremento) >= 1 && (tablero.getAlto() - incremento >= 1 )){ // -incremento porque vamos hacia la izquierda
				if(tablero.getCasilla(col - incremento, tablero.getAlto() - incremento) == actual){ //compruebo si es de mi mismo color
					seguidas++;
					if(seguidas == PREMIO){
						ganador = actual;
						encontrada = true;
					}
				}
				else{ // no es de mi color entonces compruebo si vacia
					if(tablero.getCasilla(col - incremento, tablero.getAlto() - incremento) == Ficha.VACIA){
						seguidas = 0;
					}
					else { // en este caso es del otro color
						seguidas = 1;
						actual = tablero.getCasilla(col - incremento, tablero.getAlto() - incremento); //asignamos el color a actual
					}
				}
				incremento++;
			}
			return ganador;
		}

		public Tablero iniciaTablero() {
			return new Tablero(nCols, nFilas);
		}

	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		if (ultimoEnPoner == Ficha.BLANCA){
			return Ficha.NEGRA;
		}
		return Ficha.BLANCA;
	}

	@Override
	//devuelve si el tablero esta lleno, en caso de que devuelva true, seran tablas porque no ha ganado ninguno 
	//y se ha llenado el tablero entero
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		boolean lleno = true;
		int i = 1;
		while(lleno && i <= t.getAncho()){
			if (t.getCasilla(i, 1) == Ficha.VACIA){ // i, 1 porque la ultima fila es (1,1)
				lleno = false;
			}
			else {
				i++;
			}
		}
		return lleno; 
	}
	@Override
	public TipoJuego getTipoJuego() {
		return TipoJuego.TRES;
	}
}