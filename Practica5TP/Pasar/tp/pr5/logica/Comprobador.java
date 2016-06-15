package tp.pr5.logica;

import java.util.Vector;

public class Comprobador {
	
	public static class Dupla {
		int i;
		Direccion dir;
		
		public Dupla(int i, Direccion dir){
			this.i = i;
			this.dir = dir;
		}
		
		
	}
	
	private static Vector<Dupla> direcciones = new Vector<Dupla>();
	
	private static Direccion[] fijas = { 	Direccion.NOROESTE, Direccion.NORTE, Direccion.NORESTE,
									Direccion.OESTE, Direccion.NONE, Direccion.ESTE,
									Direccion.SUROESTE, Direccion.SUR, Direccion.SURESTE};
	
	public static Vector<Dupla> getDirecciones(){
		return direcciones;
	}
	
	public static boolean flanqueo(Tablero t, int fila, int columna, Ficha turno){
		boolean encontrado = false;
		direcciones.clear();
		int incrX = -1;
		//en la primera iteraci�n Y se va a incrementar
		int incrY = -2;
		int f = fila;
		int c = columna;
		Ficha contraria = Ficha.BLANCA;
		
		if(turno == Ficha.BLANCA){
			contraria = Ficha.NEGRA;
		}
		if(t.getCasilla(columna, fila) != Ficha.VACIA){
			return false;
		}
		
		for (int i = 0; i < 9; i++){
			f = fila;
			c = columna;
			if (i % 3 == 0){
				incrY++;
				incrX = -1;
			}
			if (i != 4){
				// si i = 4, incrX e incrY son 0 entonces el bucle while seria infinito
				
				boolean fOk = (f + incrY >= 1) && (f + incrY <= t.getAlto());
				boolean cOk = (c + incrX >= 1) && (c + incrX <= t.getAncho());
						
				//comprobamos que la ficha de al lado es contraria a nuestro color
				//tambien tenemos en cuenta si la ficha es vacia
				while(fOk && cOk && t.getCasilla(c + incrX, f + incrY) == contraria){
					f += incrY;
					c += incrX;
					fOk = (f + incrY >= 1) && (f + incrY <= t.getAlto());
					cOk = (c + incrX >= 1) && (c + incrX <= t.getAncho());
					if(fOk && cOk && t.getCasilla(c + incrX, f + incrY) == turno){
						// La siguiente es de mi color y por tanto hay flanqueo
						encontrado = true;
						direcciones.add(new Dupla(i,fijas[i]));
						fOk = false; // solo para que salga del bucle while
					}
				}
				
			}
			incrX++;
		}
		return encontrado;
	}

}
