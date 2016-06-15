package tp.pr5.logica;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class MovimientoGravity extends Movimiento{
	
	private class Dupla {
		int distancia;
		Direccion dir;
		
		public Dupla(Direccion dir, int distancia){
			this.distancia = distancia;
			this.dir = dir;
		}
		
	}

	private int fila;
	
	public MovimientoGravity(int columna,int fila, Ficha color) {
		super(color, columna);
		this.fila = fila;
	}

	@Override
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido{
		if(columna < 1 || columna > tab.getAncho()){
			throw new MovimientoInvalido("Posición incorrecta.");
		}
		
		else if (fila < 1 || fila > tab.getAlto()){
			throw new MovimientoInvalido("Posición incorrecta.");
		}
		
		else if (tab.getCasilla(columna, fila) != Ficha.VACIA){
			throw new MovimientoInvalido("Casilla ocupada.");
		}
		
		else {
			int incrY = 0;
			int incrX = 0;
			
			switch (getDireccion(tab)){
			case NORTE: 
				incrY = 1;
				incrX = 0;
				break;
			case SUR: 
				incrY = -1;
				incrX = 0;
				break;
			case ESTE: 
				incrY = 0;
				incrX = 1;
				break;
			case OESTE: 
				incrY = 0;
				incrX = -1;
				break;
			case NORESTE: 
				incrY = 1;
				incrX = 1;
				break;
			case SURESTE: 
				incrY = -1;
				incrX = 1;
				break;
			case NOROESTE: 
				incrY = 1;
				incrX = -1;
				break;
			case SUROESTE: 
				incrY = -1;
				incrX = -1;
				break;
			case NONE: 
				incrY = 0;
				incrX = 0;
				break;
			}
			
			if(incrY == 0 && incrX == 0){
				tab.setCasilla(columna, fila, jugador);
			}
			
			else {
				boolean encontrada = false;
				while (!encontrada &&
						(fila > 1 && fila < tab.getAlto()) &&
						(columna > 1 && columna < tab.getAncho())){
					fila += incrY;
					columna += incrX;
					if(tab.getCasilla(columna, fila) != Ficha.VACIA){
						encontrada = true;
					}
				}
				if (encontrada){
					fila -= incrY;
					columna -= incrX;
				}
				
				tab.setCasilla(columna, fila, jugador);
			}
		}
		return true;
	}

	@Override
	public void undo(Tablero tab) {
		tab.setCasilla(columna, fila, Ficha.VACIA);
	}
	
	private Direccion getDireccion(Tablero tab){
		// 4 por el numero de direcciones (norte, sur, este, oeste)
		Vector <Dupla> v = new Vector <Dupla>(4);
		
		v.add(new Dupla(Direccion.SUR, fila - 1));
		v.add(new Dupla(Direccion.OESTE, columna - 1));
		v.add(new Dupla(Direccion.NORTE, tab.getAlto() - fila));
		v.add(new Dupla(Direccion.ESTE, tab.getAncho() - columna));
		// recibe un vector y la forma en la que queremos ordenarlo
		Collections.sort(v, new Comparator<Dupla>(){

			@Override
			public int compare(Dupla o1, Dupla o2) {
				if (o1.distancia > o2.distancia){
					return 1;
				}
				
				else if (o1.distancia < o2.distancia){
					return -1;
				}
				
				else {
					return 0;
				}
			}
			
		});
		
		//primer caso: 1 menor que los demas
		if (v.get(0).distancia != v.get(1).distancia){
			return v.get(0).dir;
		}
		// segundo caso: 4 iguales, estamos en la cuspide y no se mueve
		else if (v.get(0).distancia == v.get(3).distancia){
			return Direccion.NONE;
		}
		// tercer caso: 3 iguales y uno mayor que los demas, va al lado opuesto del mayor
		else if (v.get(0).distancia == v.get(2).distancia){
			switch (v.get(3).dir){
			case NORTE:
				return Direccion.SUR;
				
			case SUR:
				return Direccion.NORTE;
				
			case ESTE:
				return Direccion.OESTE;
				
			case OESTE:
				return Direccion.ESTE;
			default:
				return Direccion.NONE;

			}
		}
		//cuarto caso: 2 iguales, cae hacia la combinada, diagonal
		else {
			return getCombinada(v);
			
		}
	

	}

	private Direccion getCombinada(Vector<Dupla> v) {
		Direccion dir1 = v.get(0).dir;
		Direccion dir2 = v.get(1).dir;
		switch (dir1){
		case NORTE:
			if(dir2 == Direccion.ESTE){
				return Direccion.NORESTE;
			}
			
			else if(dir2 == Direccion.OESTE){
				return Direccion.NOROESTE;
			}
			
			else {
				if(v.get(2).distancia == v.get(3).distancia){
					return Direccion.NONE;
				}
				
				else {
					return v.get(2).dir;
				}
			}
			
		case SUR:
			if(dir2 == Direccion.ESTE){
				return Direccion.SURESTE;
			}
			
			else if (dir2 == Direccion.OESTE){
				return Direccion.SUROESTE;
			}	
			
			else {
				if(v.get(2).distancia == v.get(3).distancia){
					return Direccion.NONE;
				}
				
				else {
					return v.get(2).dir;
				}
			}
		case ESTE:
			if(dir2 == Direccion.NORTE){
				return Direccion.NORESTE;
			}
			
			else if(dir2 == Direccion.SUR){
				return Direccion.SURESTE;

			}	
			
			else {
				if(v.get(2).distancia == v.get(3).distancia){
					return Direccion.NONE;
				}
				
				else {
					return v.get(2).dir;
				}
			}
		case OESTE:
			if(dir2 == Direccion.NORTE){
				return Direccion.NOROESTE;
			}
			
			else if(dir2 == Direccion.SUR){
				return Direccion.SUROESTE;

			}	
			
			else {
				if(v.get(2).distancia == v.get(3).distancia){
					return Direccion.NONE;
				}
				
				else {
					return v.get(2).dir;
				}
			}
		default:
			return Direccion.NONE;
		}
	}

}
