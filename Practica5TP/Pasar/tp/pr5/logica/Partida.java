package tp.pr5.logica;

import java.util.Vector;

import tp.pr5.control.Jugador;
import tp.pr5.control.TipoJuego;
import tp.pr5.control.TipoJugador;

public class Partida {
	
	//ATRIBUTOS
	private Tablero tablero; //inicializamos la constructora
	private Ficha turno; // color
	private boolean terminada;
	private Ficha ganador; // color
	private UndoStack stack; // inicializamos la constructora
	private ReglasJuego reglas;
	private Vector<PartidaObserver> obs = new Vector<PartidaObserver>();
	private int blancas;
	private int negras;
	private int limite;
	private boolean l;
	

	
	//CONSTRUCTORA
	public Partida(ReglasJuego reglas, int limite){
		this.reglas = reglas;
		reset(reglas);
		this.ganador = Ficha.VACIA;
		this.terminada = false;
		this.limite = limite;
		this.blancas = 0;
		this.negras = 0;
		this.l = false;
	}
	
	//METODOS

	public boolean isTerminada(){
		return terminada;
	}
	
	public Ficha getGanador(){
		return ganador;
	}
	
	public Ficha getTurno(){
		return turno;
	}
	
	public Tablero getTablero(){
		return tablero;
	}
	
	/* ejecuta movimiento: si si el turno no coincide con el juagdor o terminada = true devuelve false.
	 * sino se ejecuta el movimiento, se mete en la pila, se cambia de turno y se comprueba el ganador
	 * se comprueba si hay ganador y si hay tablas
	 */
	public boolean ejecutaMovimiento(Movimiento mov){
		this.limite = reglas.getLimite();
		if(limite <= 0)
			limite = 500;
		
		if (terminada){
			MovimientoInvalido e = new MovimientoInvalido("La partida esta terminada.");
			notifyMovimientoIncorrecto(e);
			return false;
		}
		else if(mov.getJugador() != turno){
			MovimientoInvalido e =  new MovimientoInvalido("Turno incorrecto.");
			notifyMovimientoIncorrecto(e);
			return false;
		}
		
		else{
			try {
				
				if(blancas >= limite && negras >= limite){
					MovimientoInvalido e = new MovimientoInvalido("Límite de movimientos alcanzado por ambos");
					notifyMovimientoIncorrecto(e);
					notifyPartidaTerminada();
					terminada = true;
					return false;
				}
				if(blancas >= limite && turno == Ficha.BLANCA){
					MovimientoInvalido e = new MovimientoInvalido("Blancas no puede hacer más movimientos");
					notifyMovimientoIncorrecto(e);
				}
				else
					if(negras >= limite && turno == Ficha.NEGRA){
						MovimientoInvalido e = new MovimientoInvalido("Negras no puede hacer más movimientos");
						notifyMovimientoIncorrecto(e);
					}
					else
				if(mov.ejecutaMovimiento(tablero)){
					stack.push(mov);
					if(turno == Ficha.BLANCA)
						blancas++;
					else negras++;
					
					if(!l){
						notifyFichasCambiadas();
						Ficha turnoAnterior = turno;
						turno = reglas.siguienteTurno(turno, tablero);
						ganador = reglas.hayGanador(mov, tablero);
						if(ganador != Ficha.VACIA){
							terminada = true;
							notifyMovimientoEnd(turnoAnterior,blancas,negras);
							notifyPartidaTerminada();
						}
						else {
							terminada = reglas.tablas(turno, tablero);
							if (terminada){
								notifyMovimientoEnd(turnoAnterior,blancas,negras);
								notifyPartidaTerminada();
							}
						}
						if(!terminada)
							notifyMovimientoEnd(turnoAnterior,blancas,negras);
					}
					return true;
				}
			} catch (MovimientoInvalido e1) {
				notifyMovimientoIncorrecto(e1);
				return false;
			}
			MovimientoInvalido e =  new MovimientoInvalido("Movimiento incorrecto.");
			notifyMovimientoIncorrecto(e);
			return false;
		} 
	}
	

	/*deshacer: se comprueba que no este vacia la pila, se saca el ultimo elemento y se hace el movimiento undo.
	 * por ultimo se cambia el turno
	 */
	public boolean undo(){
		if(stack.getNumUndo() <= 0){
			notifyUndoNotPossible();
			return false;
		}
		else{
			Movimiento mov;
			mov = stack.pop();
			if(turno == Ficha.BLANCA)
				negras--;
			else blancas--;
			mov.undo(tablero);
			turno = mov.jugador;
			notifyFichasCambiadas();
			notifyUndo();
			return true;
		}
	}
	
	/*se reinicia el tablero se cambia el turno y se pone el ganador como vacia
	 * por ultimo se crea una nueva pila
	 */
	public void reset(ReglasJuego reglas){
		this.reglas = reglas;
		tablero = reglas.iniciaTablero();
		turno = reglas.jugadorInicial();
		terminada = false;
		ganador = Ficha.VACIA;
		stack = new UndoStack();
		notifyCambioJuego();
		notifyFichasCambiadas();
		notifyReset();
		blancas = 0;
		negras = 0;
	}
	
	public String toString(){
		String s;
		s = tablero.toString();
		s += '\n';
		s += "Juegan ";
		if(turno == Ficha.NEGRA){
			s += "negras";
		}
		
		else{
			s += "blancas";
		}
		return s;
	}
	
	public String printTablero(){
		return tablero.toString();
	}
	
	public boolean addObserver(PartidaObserver o){
		if(!obs.contains(o)){
			obs.add(o);
			return true;
		}
		return false;
	}
	
	public boolean removeObserver(PartidaObserver o){
		if(obs.contains(o)){
			obs.remove(o);
			return true;
		}
		return false;
	}
	
	private void notifyReset(){
		for (PartidaObserver o : obs){
			o.onReset(tablero ,turno);
		}
	}

	private void notifyPartidaTerminada(){
		for (PartidaObserver o : obs){
			o.onPartidaTerminada(tablero, ganador);
		}
	}

	private void notifyCambioJuego(){
		for (PartidaObserver o : obs){
			o.onCambioJuego(tablero, turno, reglas.getTipoJuego());
		}
	}


	private void notifyUndoNotPossible(){
		for (PartidaObserver o : obs){
			o.onUndoNotPossible(tablero, turno);
		}
	}
	

	private void notifyUndo(){
		for (PartidaObserver o : obs){
			o.onUndo(tablero, turno, (stack.getNumUndo()>0), blancas, negras);
		}
	}
	
	private void notifyFichasCambiadas(){
		for (PartidaObserver o : obs){
			o.onFichasCambiadas(tablero.nFichasBlancas(), tablero.nFichasNegras());
		}
	}

	private void notifyMovimientoEnd(Ficha turnoAnterior, int blancas, int negras){
		for (PartidaObserver o : obs){
			o.onMovimientoEnd(tablero, turnoAnterior, turno, blancas, negras);
		}
	}

	private void notifyMovimientoIncorrecto(MovimientoInvalido movimientoException){
		for (PartidaObserver o : obs){
			o.onMovimientoIncorrecto(movimientoException);
		}
	}
	
	private void notifyLimite() {
		// TODO Auto-generated method stub
		for(PartidaObserver o : obs){
			o.onLimiteAlcanzado();
		}
		
	}

	public Movimiento getMovimiento(Jugador jugador) {
		Movimiento mov = jugador.getMovimiento(tablero, turno);
		return mov;
	}

	public Ficha getJugadorInicial() {
		return reglas.jugadorInicial();
	}

	public int nFichasBlancas() {
		return tablero.nFichasBlancas();
	}

	public void pasaTurno() {
		// TODO Auto-generated method stub
		if(turno == Ficha.BLANCA){
			turno = Ficha.NEGRA;
		}
		else turno = Ficha.BLANCA;
		
		notifyMovimientoEnd(turno, blancas, negras);
	}
	

}
