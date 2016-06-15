package tp.pr5.comandos;

import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Partida;

public class Deshacer extends CommandInterpreter{

	@Override
	public CommandInterpreter parsear(String[] cadena) {
		if(cadena.length < 1){
			return null;
		}
		if(cadena[0].toUpperCase().matches("DESHACER")){
			return new Deshacer();
		}
		return null;
	}

	@Override
	public void ejecuta(Partida p, FactoriaTipoJuego f)
			throws MovimientoInvalido {
		control.deshacer();
	}

}
