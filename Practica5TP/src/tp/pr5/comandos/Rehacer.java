package tp.pr5.comandos;

import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Partida;

public class Rehacer extends CommandInterpreter{

	@Override
	public CommandInterpreter parsear(String[] cadena) {
		// TODO Auto-generated method stub
		if(cadena.length < 1){
			return null;
		}
		if(cadena[0].toUpperCase().matches("REHACER")){
			return new Rehacer();
		}
		return null;	}

	@Override
	public void ejecuta(Partida p, FactoriaTipoJuego f)
			throws MovimientoInvalido {
		control.rehacer();
	}

}
