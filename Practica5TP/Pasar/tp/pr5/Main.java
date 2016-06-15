package tp.pr5;

import java.awt.EventQueue;
import java.util.Scanner;

import tp.pr5.comandos.CommandInterpreter;
import tp.pr5.consola.VistaConsola;
import tp.pr5.control.ArgumentException;
import tp.pr5.control.ArgumentParser;
import tp.pr5.control.ControladorConsola;
import tp.pr5.control.ControladorGUI;
import tp.pr5.control.FactoriaComplica;
import tp.pr5.control.FactoriaConecta4;
import tp.pr5.control.FactoriaGravity;
import tp.pr5.control.FactoriaReversi;
import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.control.TipoInterfaz;
import tp.pr5.control.TipoJuego;
import tp.pr5.logica.Partida;
import tp.pr5.ventana.Ventana;


public class Main {
	
	//CONSTRUCTORA
	public static void main(String[] args) {
		
		ControladorConsola controladorConsola;
		Partida p;
		Scanner s;
		FactoriaTipoJuego f = null;
		ArgumentParser a = new ArgumentParser(args);
		TipoJuego t = TipoJuego.CONECTA4;
		TipoInterfaz i = TipoInterfaz.CONSOLE;
		ControladorGUI controlGUI;
		final Ventana v;
		VistaConsola vc;
		int limite = 0;
		
		if(a.tieneAyuda()){
			a.mostrarAyuda();
		}
		
		else {
			try {
				i = a.pedirInterfaz();
				t = a.pedirJuego();
				limite = a.pedirLimite();
				if(limite <= 0)
					limite = 500;
				System.out.println(limite);
			} catch (ArgumentException e) {
				System.err.println("Uso incorrecto: " + e.getMessage());
				System.err.println("Uso -h/--help para más detalles.");
				System.exit(1);
			}
			
			
			switch(t){
			case COMPLICA:
				f = new FactoriaComplica(limite);
				break;
			case CONECTA4:
				f = new FactoriaConecta4(limite);
				break;
			case GRAVITY:
				int x = 10;
				int y = 10;
				try {
					x = a.pedirTamX();
				} catch (ArgumentException e) {
					System.err.println(e.getMessage());
				}
				try {
					y = a.pedirTamY();
				} catch (ArgumentException e) {
					System.err.println(e.getMessage());
				}
				f = new FactoriaGravity(x, y);
				break;
			case REVERSI:
				f = new FactoriaReversi(limite);
				break;
			default:
				break;
			
			}
			

			
			p = new Partida(f.creaReglas(), limite);
			s = new Scanner(System.in);
		
			if(i == TipoInterfaz.CONSOLE){
				controladorConsola = new ControladorConsola(f,p,s);
				CommandInterpreter.configureControl(controladorConsola);
				vc = new VistaConsola(controladorConsola, p.getTablero(), p.getTurno());
				controladorConsola.run();
				System.exit(0);
			}
			else {
				controlGUI = new ControladorGUI(p,f);
				v = new Ventana(controlGUI, t);
				EventQueue.invokeLater(new Runnable(){

					@Override
					public void run() {
						v.setVisible(true);						
					}
					
				});
			}
		}
		
	}

}
