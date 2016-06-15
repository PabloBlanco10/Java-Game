package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.comandos.CommandInterpreter;
import tp.pr5.comandos.CommandParser;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Partida;
import tp.pr5.logica.PartidaObserver;

public class ControladorConsola implements Controlador{

	//ATRIBUTOS
		private Partida partida;
		private Scanner in;
		private FactoriaTipoJuego f;
		private Jugador jugadorBlancas;
		private Jugador jugadorNegras;
		private int limite;
		private boolean l;
		
		//CONSTRUCTORA
		public ControladorConsola(FactoriaTipoJuego f, Partida p, java.util.Scanner in) {
			this.partida = p;
			this.in = in;
			this.f = f;
			this.partida.reset(f.creaReglas());
			this.jugadorBlancas = f.creaJugadorHumanoConsola(in);
			this.jugadorNegras = f.creaJugadorHumanoConsola(in);
			this.limite = limite;
			l = false;

		}
		
		public boolean addObserver(PartidaObserver o){
			return partida.addObserver(o);
		}
		
		public boolean removeObserver(PartidaObserver o){
			return partida.removeObserver(o);
		}
		
		public void run(){
			
			String comando;
			CommandInterpreter com;
			CommandInterpreter.configureControl(this);
			
			
			while(!partida.isTerminada() && !l){
				
				System.out.print("Que quieres hacer? ");
				comando = in.nextLine().toLowerCase();
				comando = comando.trim();
				while("".equals(comando)){
					comando = in.nextLine().toLowerCase();
				}
				
				com = CommandParser.parseo(comando);
				if (com != null){
					try {
						com.ejecuta(partida, f);
					} catch (MovimientoInvalido e) {
						System.err.println(e.getMessage());
					}
				}
				
				else{
					System.err.println("No te entiendo.");
				}
				
			}
			
			
		}
		
		
		public void cambiarJugador(TipoJugador j, Ficha c){
			if (c == Ficha.BLANCA){
				if(j == TipoJugador.ALEATORIO){
					jugadorBlancas = f.creaJugadorAleatorio();
				}
				else{
					jugadorBlancas = f.creaJugadorHumanoConsola(in);
				}
			}
			else {
				if(j == TipoJugador.ALEATORIO){
					jugadorNegras = f.creaJugadorAleatorio();
				}
				else{
					jugadorNegras = f.creaJugadorHumanoConsola(in);
				}
			}
		}
		
		public void deshacer(){
			if(!partida.undo()){
				System.err.println("Imposible deshacer.");
			}
		}
		
		public void cambiarJuego(TipoJuego j, int ancho, int alto, int limite){
			switch(j){
			case COMPLICA:
				 f = new FactoriaComplica(limite);
				break;
			case CONECTA4:
				f = new FactoriaConecta4(limite);
				break;
			case GRAVITY:
				f = new FactoriaGravity(ancho, alto,limite);
				break;
			case REVERSI:
				f = new FactoriaReversi(limite);
				break;
			default:
				f = new FactoriaConecta4(limite);
				break;
			
			}
			jugadorBlancas = f.creaJugadorHumanoConsola(in);
			jugadorNegras = f.creaJugadorHumanoConsola(in);
			partida.reset(f.creaReglas());
			this.limite = limite;

		}
		
		public void poner(){
			Movimiento mov;
			if (partida.getTurno() == Ficha.BLANCA){
				mov = partida.getMovimiento(jugadorBlancas);
			}
			else {
				mov = partida.getMovimiento(jugadorNegras);
			}
			
			partida.ejecutaMovimiento(mov);
		}
		
		public void reiniciar(){
			partida.reset(f.creaReglas());
			System.out.println("Partida reiniciada.");
			
		}
		
		public void salir(){
			System.exit(0);
		}

		@Override
		public void pasar() {
			// TODO Auto-generated method stub
			partida.pasaTurno();
		}
}
