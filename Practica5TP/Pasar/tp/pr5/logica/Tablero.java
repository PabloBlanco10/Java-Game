package tp.pr5.logica;

import java.util.Vector;

public class Tablero implements TableroInmutable{
	

	//ATRIBUTOS
	private int nBlancas;
	private int nNegras;
	private Ficha[][] tablero;
	private int ancho;
	private int alto;
	private Vector<TableroObserver> obs = new Vector<TableroObserver>();
	
	
	//CONSTRUCTORA
	public Tablero(int ancho, int alto){
		if(ancho < 1){
			ancho = 1;
		}
		if(alto < 1){
			alto = 1;
		}
		this.ancho = ancho;
		this.alto = alto;
		nBlancas = 0;
		nNegras = 0;
		// alto +1 y ancho + 1 porque la fila y columna 0 no van a contar
		tablero = new Ficha[ancho + 1][alto + 1]; //[ancho][alto] porque la variable mas lenta es ancho
		this.reset(); // para inicializar el tablero
	}
	
	//MÉTODOS
	public void reset(){
		for(int i = 0; i <= this.alto; i++){
			for(int j = 0; j <= this.ancho; j++){
				tablero[j][i] = Ficha.VACIA;
			}
		}
		nBlancas = 0;
		nNegras = 0;
		notifyReset();
	}
	
	public int getAncho(){
		return this.ancho;
	}
	
	public int getAlto() {
		return this.alto;
	}
	
	public Ficha getCasilla(int x, int y){ // x = ancho, y = alto
		if((x > 0 && x < ancho + 1) && (y > 0 && y < alto + 1)){
			return this.tablero[x][y];
		}
		return Ficha.VACIA; //porque tiene que devolver algo
	}
	
	public void setCasilla(int x, int y, Ficha color){
		if(x > 0 && x <= ancho){
			if(color == Ficha.BLANCA){
				nBlancas++;
			}
			else if(color == Ficha.NEGRA){
				nNegras++;
			}
			
			if(tablero[x][y] == Ficha.BLANCA){
				nBlancas--;
			}
			else if (tablero[x][y] == Ficha.NEGRA){
				nNegras--;
			}
			
			tablero[x][y] = color;
			notifyCasillaCambiada(x, y, color);
		}
	}
	
	
	public String toString(){
		String s = "";
		String aux = " ";
		for(int i = 1; i <= alto; i++){ // de abajo arriba
			s += "|";
			for(int j = 1; j <= ancho; j++){ // de izquierda a derecha
				if (tablero[j][i] == Ficha.VACIA){
					s += " ";
				}
				else if (tablero[j][i] == Ficha.BLANCA){
					s += "O";
				}
				else {
					s += "X";
				}
			}
			s += "|" + "\n";
		}
		s += "+";
		for(int j = 1; j <= ancho; j++){
			s += "-";
			aux += j % 10; // para que vaya poniendo a la vez los n�meros
		}
		s += "+" + "\n"; 
		s += aux + "\n";
		
		return s;
	}
	
	public boolean addObserver(TableroObserver o){
		if(!obs.contains(o)){
			obs.add(o);
			return true;
		}
		return false;
	}
	
	public boolean removeObserver(TableroObserver o){
		if(obs.contains(o)){
			obs.remove(o);
			return true;
		}
		return false;
	}
	
	private void notifyReset(){
		for(TableroObserver t: obs){
			t.onReset();
		}
	}
	
	private void notifyCasillaCambiada(int x, int y, Ficha color){
		for(TableroObserver t: obs){
			t.onCasillaCambiada(x, y, color);
		}
	}

	@Override
	public int getFilas() {
		return this.alto;
	}

	@Override
	public int getColumnas() {
		return this.ancho;
	}

	public int nFichasBlancas() {
		return nBlancas;
	}
	
	public int nFichasNegras() {
		return nNegras;
	}
	

}
