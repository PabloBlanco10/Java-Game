package tp.pr5.comandos;

import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Partida;

public class Reiniciar extends CommandInterpreter{

	@Override
	public CommandInterpreter parsear(String[] cadena) {
		if(cadena.length < 1){
			return null;
		}
		if(cadena[0].toUpperCase().matches("REINICIAR")){
			return new Reiniciar();
		}
		return null;
	}

	@Override
	public void ejecuta(Partida p, FactoriaTipoJuego f)
			throws MovimientoInvalido {
		control.reiniciar();
	}

}
