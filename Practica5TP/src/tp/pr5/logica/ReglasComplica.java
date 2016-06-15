package tp.pr5.logica;

import tp.pr5.control.TipoJuego;

public class ReglasComplica implements ReglasJuego {
	
	private final int PREMIO = 4;
	private int blancas = 0;
	private int negras = 0;

	@Override
	//se comprueban todas las maneras de conseguir 4 en raya
	 
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		blancas = 0;
		negras = 0;
		
		compruebaHorizontal(t);
		
		compruebaVertical(t);
			
		compruebaDiagonalNormal(t);
				
		compruebaDiagonalInversa(t);
				
		if(blancas > 0 && negras > 0)
			return Ficha.VACIA;
		else if(negras > 0)
			return Ficha.NEGRA;
		else if(blancas > 0)
			return Ficha.BLANCA;
		else
			return Ficha.VACIA;
	}
	
	private Ficha compruebaHorizontal(Tablero tablero){
		int contB = 0;
		int contN = 0;
		//Ficha ganador = Ficha.VACIA;
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
						if(actual == Ficha.BLANCA){
							contB++;
						}
						else{
							contN++;
						}
						seguidas = 0; // resetamos para que el contador no sea > 4
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
		
		blancas += contB;
		negras += contN;
		
		return Ficha.VACIA;	
	}
	
	
	private Ficha compruebaVertical(Tablero tablero){
		int contB = 0;
		int contN = 0;
		//Ficha ganador = Ficha.VACIA;
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
						if(actual == Ficha.BLANCA){
							contB++;
						}
						else{
							contN++;
						}
						seguidas = 0;
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
		blancas += contB;
		negras += contN;
		
		return Ficha.VACIA;
	}
	
	
	
	private Ficha compruebaDiagonalNormal(Tablero tablero){ // normal = /
		int i = 1;
		while (i <= tablero.getAlto()){ 
			compruebaDiagonalNormalSuperior(tablero, i);
			i++;
		}
		i = 2; // empezamos en 2 porque la diagonal principal ya la hemos comprobado en el bucle anterior
		while(i <= tablero.getAncho()){
			compruebaDiagonalNormalInferior(tablero, i);
			i++;
		}
		

		return Ficha.VACIA;
	}
	
	
	private Ficha compruebaDiagonalNormalSuperior(Tablero tablero, int fila){
		int contB = 0;
		int contN = 0;
		//Ficha ganador = Ficha.VACIA;
		boolean encontrada = false;
		int incremento = 0;
		int seguidas = 0;
		Ficha actual = Ficha.BLANCA;
		while(!encontrada && ((fila - incremento) >= 1) && (incremento + 1 <= tablero.getAncho())){
			if(tablero.getCasilla(incremento + 1, fila - incremento) == actual){ //compruebo si es de mi mismo color y empezamos en incremento + 1 para que sea la col 1
				seguidas++;
				if(seguidas == PREMIO){
					if(actual == Ficha.BLANCA){
						contB++;
					}
					else{
						contN++;
					}
					seguidas = 0;
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
		blancas += contB;
		negras += contN;
		return Ficha.VACIA;	
	}
	
	
	private Ficha compruebaDiagonalNormalInferior(Tablero tablero, int col){
		int contB = 0;
		int contN = 0;
		//Ficha ganador = Ficha.VACIA;
		boolean encontrada = false;
		int incremento = 0;
		int seguidas = 0;
		Ficha actual = Ficha.BLANCA;
		while(!encontrada && (col + incremento) <= tablero.getAncho() && (tablero.getAlto() - incremento >= 1)){
			if(tablero.getCasilla(col + incremento, tablero.getAlto() - incremento) == actual){ //compruebo si es de mi mismo color
				seguidas++;
				if(seguidas == PREMIO){
					if(actual == Ficha.BLANCA){
						contB++;
					}
					else{
						contN++;
					}
					seguidas = 0;
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
		blancas += contB;
		negras += contN;
		return Ficha.VACIA;	
		
	}
	
	
	
	private Ficha compruebaDiagonalInversa(Tablero tablero){ // inversa = \
		int i = 1;
		while (i <= tablero.getAncho()){ 
			compruebaDiagonalInversaInferior(tablero, i);
			i++;
		}
		
		i = 1;
		while(i < tablero.getAlto()){
			compruebaDiagonalInversaSuperior(tablero, i);
			i++;
		}

		return Ficha.VACIA;
		
	}
	
	
	private Ficha compruebaDiagonalInversaSuperior(Tablero tablero, int fila){
		int contB = 0;
		int contN = 0;
		//Ficha ganador = Ficha.VACIA;
		boolean encontrada = false;
		int incremento = 0;
		int seguidas = 0;
		Ficha actual = Ficha.BLANCA;
		while(!encontrada && (fila - incremento) >= 1 && incremento <= tablero.getAncho()){
			// (ancho - 1) - incremento para saber la columna i y fila + incremento indica la fila i
			if(tablero.getCasilla(tablero.getAncho() - incremento, fila - incremento) == actual){ 
				seguidas++;
				if(seguidas == PREMIO){
					if(actual == Ficha.BLANCA){
						contB++;
					}
					else{
						contN++;
					}
					seguidas = 0;
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
		blancas += contB;
		negras += contN;
		return Ficha.VACIA;		
	}
	
	
	private Ficha compruebaDiagonalInversaInferior(Tablero tablero, int col){
		int contB = 0;
		int contN = 0;
		//Ficha ganador = Ficha.VACIA;
		boolean encontrada = false;
		int incremento = 0;
		int seguidas = 0;
		Ficha actual = Ficha.BLANCA;
		while(!encontrada && (col - incremento) >= 1 && (tablero.getAlto() - incremento >= 1 )){ // -incremento porque vamos hacia la izquierda
			if(tablero.getCasilla(col - incremento, tablero.getAlto() - incremento) == actual){ //compruebo si es de mi mismo color
				seguidas++;
				if(seguidas == PREMIO){
					if(actual == Ficha.BLANCA){
						contB++;
					}
					else{
						contN++;
					}
					seguidas = 0;
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
		blancas += contB;
		negras += contN;
		return Ficha.VACIA;	
	}

	@Override
	//crea un nuevo tablero
	public Tablero iniciaTablero() {
		return new Tablero(4,7);
	}

	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}

	@Override
	//cambia el turno
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		if (ultimoEnPoner == Ficha.BLANCA){
			return Ficha.NEGRA;
		}
		return Ficha.BLANCA;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		return false; // nunca hay tablas, siempre hay un ganador
	}

	@Override
	public TipoJuego getTipoJuego() {
		return TipoJuego.COMPLICA;
	}

}
