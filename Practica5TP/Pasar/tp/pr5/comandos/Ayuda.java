package tp.pr5.comandos;

import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Partida;

public class Ayuda extends CommandInterpreter{

	@Override
	public CommandInterpreter parsear(String[] cadena) {
		if(cadena.length < 1){
			return null;
		}
		if(cadena[0].toUpperCase().matches("AYUDA")){
			return new Ayuda();
		}
		return null;

	}

	@Override
	public void ejecuta(Partida p, FactoriaTipoJuego f)
			throws MovimientoInvalido {
		
		System.out.println(	"PONER: utilízalo para poner la siguiente ficha.\n" + 
							"DESHACER: deshace el último movimiento hecho en la partida.\n" +
							"REINICIAR: reinicia la partida.\n" +
							"JUGAR [c4|co|gr] [tamX tamY]: cambia el tipo de juego.\n" + 
							"JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.\n" +
							"SALIR: termina la aplicación.\n" +
							"AYUDA: muestra esta ayuda.\n");
		
	}

}
