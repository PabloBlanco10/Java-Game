package tp.pr5.control;


import tp.pr5.logica.Comprobador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Partida;
import tp.pr5.logica.PartidaObserver;
import tp.pr5.logica.TableroInmutable;

public class ControladorGUI implements Controlador{

	private Partida p;
	private FactoriaTipoJuego f;
	private Jugador jugadorBlancas;
	private Jugador jugadorNegras;
	private HebraBlancas hb;
	private HebraNegras hn;
	private TipoJugador tipoBlancas;
	private TipoJugador tipoNegras;
	
	public ControladorGUI(Partida partida, FactoriaTipoJuego factoria){
		this.p = partida;
		this.f = factoria;
		this.tipoBlancas = TipoJugador.HUMANO;
		this.tipoNegras = TipoJugador.HUMANO;
		jugadorBlancas = f.creaJugadorAleatorio();
		jugadorNegras = f.creaJugadorAleatorio();
	}
	
	
	private class HebraBlancas extends Thread{
		public void run(){
			if (p.getTurno() == Ficha.BLANCA && jugadorBlancas != null && !p.isTerminada()){
				Movimiento mov = jugadorBlancas.getMovimiento(p.getTablero(), Ficha.BLANCA);
				try {
					Thread.sleep(1000);
					p.ejecutaMovimiento(mov);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}
	
	private class HebraNegras extends Thread{
		public void run(){
			if (p.getTurno() == Ficha.NEGRA && jugadorNegras != null && !p.isTerminada()){
				Movimiento mov = jugadorNegras.getMovimiento(p.getTablero(), Ficha.NEGRA);
				try {
					Thread.sleep(1000);
					p.ejecutaMovimiento(mov);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}
	
	public void ejecutaHebraBlancas(){
		hb = new HebraBlancas();
		hb.start();
	}
	
	public void ejecutaHebraNegras(){
		hn = new HebraNegras();
		hn.start();
	}
	
	public void interrumpirHebraBlancas(){
		if(hb != null && !hb.isInterrupted())
			hb.interrupt();
	}
	
	public void interrumpirHebraNegras(){
		if(hn != null && !hn.isInterrupted())
			hn.interrupt();
	}
	
	public boolean addObserver(PartidaObserver o){
		return p.addObserver(o);
	}
	
	public boolean removeObserver(PartidaObserver o){
		return p.removeObserver(o);
	}
	
	public void poner(int col, int fila){
		Movimiento mov = f.creaMovimiento(col, fila, p.getTurno());
		p.ejecutaMovimiento(mov);
	}
	
	public void deshacer(){
		p.undo();
	}
	
	public void reiniciar(){
		p.reset(f.creaReglas());
	}
	
	public void cambiarJuego(TipoJuego j, int alto, int ancho){
		Comprobador.setTipoJuego(j);
		switch(j){
		case COMPLICA:
			 f = new FactoriaComplica();
			break;
		case CONECTA4:
			f = new FactoriaConecta4();
			break;
		case GRAVITY:
			f = new FactoriaGravity(ancho, alto);
			break;
		case REVERSI:
			f = new FactoriaReversi();
			break;
		case TRES:
			f = new FactoriaTres(ancho, alto);
			break;
		default:
			f = new FactoriaConecta4();
			break;
		
		}
		jugadorBlancas = f.creaJugadorAleatorio();
		jugadorNegras = f.creaJugadorAleatorio();
		p.reset(f.creaReglas());
	}
	
	public void salir(){
		System.exit(0);
	}
	
	public TableroInmutable getTableroInmutable(){
		return p.getTablero();
	}

	@Override
	public void cambiarJugador(TipoJugador j, Ficha c) {
		if (c == Ficha.BLANCA){
			tipoBlancas = j;
			if(j == TipoJugador.ALEATORIO){
				jugadorBlancas = f.creaJugadorAleatorio();
			}
			else{
				//jugadorBlancas = f.creaJugadorHumanoConsola();
			}
		}
		else {
			tipoNegras = j;
			if(j == TipoJugador.ALEATORIO){
				jugadorNegras = f.creaJugadorAleatorio();
			}
			else{
				//jugadorNegras = f.creaJugadorHumanoConsola(in);
			}
		}
	}
	
	
	public Ficha getJugadorInicial(){
		return p.getJugadorInicial();
	}

	@Override
	public void poner() {
		//no sirve
	}
	
	public TipoJugador getTipoJugador(Ficha f){
		if(f == Ficha.BLANCA){
			return tipoBlancas;
		}
		
		else if (f == Ficha.NEGRA){
			return tipoNegras;
		}
		
		return null;
	}

	public void ponerAleatorio() {
		if(p.getTurno() == Ficha.BLANCA){
			p.ejecutaMovimiento(jugadorBlancas.getMovimiento(p.getTablero(), Ficha.BLANCA));
		}
		else if(p.getTurno() == Ficha.NEGRA){
			p.ejecutaMovimiento(jugadorNegras.getMovimiento(p.getTablero(), Ficha.NEGRA));
		}
		
	}
	
	public boolean flanqueo(int fila, int col, Ficha color){
		return Comprobador.flanqueo(p.getTablero(), fila, col, color);
	}

	@Override
	public void rehacer() {
		// TODO Auto-generated method stub
		p.rehacer();
	}
	
}
