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
import tp.pr5.control.FactoriaTres;
import tp.pr5.control.TipoInterfaz;
import tp.pr5.control.TipoJuego;
import tp.pr5.logica.Comprobador;
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
		
		if(a.tieneAyuda()){
			a.mostrarAyuda();
		}
		
		else {
			try {
				i = a.pedirInterfaz();
				t = a.pedirJuego();
			} catch (ArgumentException e) {
				System.err.println("Uso incorrecto: " + e.getMessage());
				System.err.println("Uso -h/--help para más detalles.");
				System.exit(1);
			}
			Comprobador.setTipoJuego(t);
			switch(t){
			case COMPLICA:
				f = new FactoriaComplica();
				break;
			case CONECTA4:
				f = new FactoriaConecta4();
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
				f = new FactoriaReversi();
				break;
			case TRES:
				int h = 6;
				int j = 7;
				try {
					j = a.pedirTamJ();
				} catch (ArgumentException e) {
					System.err.println(e.getMessage());
				}
				try {
					h = a.pedirTamH();
				} catch (ArgumentException e) {
					System.err.println(e.getMessage());
				}
				f = new FactoriaTres(j, h);
				break;	
				default:
				break;
			
			}
			
			p = new Partida(f.creaReglas());
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
