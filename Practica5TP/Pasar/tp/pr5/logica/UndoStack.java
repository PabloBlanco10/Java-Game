package tp.pr5.logica;

public class UndoStack {
	
	//ATRIBUTOS
	private Movimiento[] movimientos;
	private int numUndo; //inicio
	private final int MAX = 10;; //fin
	
	//CONSTRUCTORA
	public UndoStack(){
		movimientos = new Movimiento[MAX];
		numUndo = 0;
	}
	
	//M�TODOS
	//hace un push en la pila, si esta llena se pone el movimiento en la ultima posicion y desaparece el primero.
	public void push(Movimiento mov){
		if(numUndo == MAX){
			for(int i = 0; i < MAX - 1; i++){
				movimientos[i] = movimientos[i+1]; //movemos todos una posici�n
			}
			movimientos[MAX - 1] = mov; //metemos el nuevo elemento en la �ltima posici�n
		}
		
		else{
			movimientos[numUndo] = mov;
			numUndo++;
		}
	}
	
	// hace un pop en la pila, si esta vacia devuelve null, sino devuelve la ultima posicion de la pila
	public Movimiento pop(){
		if(numUndo <= 0){
			return null; // para comprobar si hay elementos en la pila o no
		}
		
		else{
			Movimiento aux;
			aux = movimientos[numUndo - 1];
			numUndo--;
			return aux;
		}
	}
	
	public void reset(){
		numUndo = 0;
	}
	
	
	public int getNumUndo(){
		return numUndo;
	}

}
