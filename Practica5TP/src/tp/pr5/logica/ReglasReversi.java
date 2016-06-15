package tp.pr5.logica;

import tp.pr5.control.TipoJuego;

public class ReglasReversi implements ReglasJuego {

	private int contBlancas =  0;
	private int contNegras = 0;
	
	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		if(compruebaLleno(t)){
			if(contBlancas == contNegras){
				return Ficha.VACIA;
			}
			else if (contBlancas > contNegras){
				return Ficha.BLANCA; 
			}
			
			else{
				return Ficha.NEGRA; 
			}
		}
		else if (contNegras == 0){
			return Ficha.BLANCA;
		}
		else if(contBlancas == 0){
			return Ficha.NEGRA;
		}
		else{
			int i = 1; //filas
			int j = 1; //columnas
			boolean encontrado = false; // encontrado quiere decir, encontrada posicion para flanquear
				//�Flanquean las negras?
			while (!encontrado && i <= t.getAlto()){
				j = 1;
				while(!encontrado && j <= t.getAncho()){
					encontrado = Comprobador.flanqueo(t,i,j,Ficha.NEGRA);
					j++;
				}
				i++;
			}
			if(!encontrado){
				i = 1;
				j = 1;
				//�Flanquean las blancas?
				while (!encontrado && i <= t.getAlto()){
					j = 1;
					while(!encontrado && j <= t.getAncho()){
						encontrado = Comprobador.flanqueo(t,i,j,Ficha.BLANCA);
						j++;
					}
					i++;
				}
				if (encontrado){
					return Ficha.VACIA;
				}
				else {
					if(contBlancas == contNegras){
						return Ficha.VACIA;
					}
					else if (contBlancas > contNegras){
						return Ficha.BLANCA; 
					}
					
					else{
						return Ficha.NEGRA; 
					}
				}
			}

		}
		return Ficha.VACIA;
	}

	@Override
	public Tablero iniciaTablero() {
		Tablero t = new Tablero(8,8);
		t.setCasilla(4, 4, Ficha.BLANCA);
		t.setCasilla(5, 5, Ficha.BLANCA);
		t.setCasilla(5, 4, Ficha.NEGRA);
		t.setCasilla(4, 5, Ficha.NEGRA);
		return t;
	}

	@Override
	public Ficha jugadorInicial() {
		return Ficha.NEGRA;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		int i = 1; //filas
		int j = 1; //columnas
		boolean encontrado = false; // encontrado quiere decir, encontrada posicion para flanquear
		if (ultimoEnPoner == Ficha.BLANCA){
			//�Flanquean las negras?
			while (!encontrado && i <= t.getAlto()){
				j = 1;
				while(!encontrado && j <= t.getAncho()){
					encontrado = Comprobador.flanqueo(t,i,j,Ficha.NEGRA);
					j++;
				}
				i++;
			}
			if (encontrado){
				return Ficha.NEGRA;
			}
			else {
				return Ficha.BLANCA;
			}
		}
		else {
			//�Flanquean las blancas?
			while (!encontrado && i <= t.getAlto()){
				j = 1;
				while(!encontrado && j <= t.getAncho()){
					encontrado = Comprobador.flanqueo(t,i,j,Ficha.BLANCA);
					j++;
				}
				i++;
			}
			if (encontrado){
				return Ficha.BLANCA;
			}
			else {
				return Ficha.NEGRA;
			}
		}
	}
	
	//si devuelve false los contadores no son correctos
	private boolean compruebaLleno(Tablero t){
		contBlancas =  0;
		contNegras = 0;
		boolean encontrado = false;
		int i = 1; // columna
		int j = 1; // fila
		
		while(i <= t.getAncho()){
			j = 1;
			while(j <= t.getAlto()){
				if(t.getCasilla(i, j) == Ficha.VACIA){
					encontrado = true; // encontrado = encontrada ficha vacia
				}
				else if (t.getCasilla(i, j) == Ficha.BLANCA){
					contBlancas++;
				}
				else{
					contNegras++;
				}
				j++;
			}
			i++;
		}
		return !encontrado; // no hay ficha vacia
		
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		if(compruebaLleno(t)){
			if(contBlancas == contNegras){
				return true;
			}
			return false; // lleno pero no iguales
		}
		return false; // no lleno
	}

	@Override
	public TipoJuego getTipoJuego() {
		return TipoJuego.REVERSI;
	}

}
