package tp.pr5.comandos;

import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Partida;

public class Salir extends CommandInterpreter{

	@Override
	public CommandInterpreter parsear(String[] cadena) {
		if(cadena.length < 1){
			return null;
		}
		if(cadena[0].toUpperCase().matches("SALIR")){
			return new Salir();
		}
		return null;
	}

	@Override
	public void ejecuta(Partida p, FactoriaTipoJuego f)
			throws MovimientoInvalido {
		System.exit(0);
	}

}
