package tp.pr5.comandos;

import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Partida;

public class Pasar extends CommandInterpreter{

	@Override
	public CommandInterpreter parsear(String[] cadena) {
		// TODO Auto-generated method stub
		return new Pasar();
	}

	@Override
	public void ejecuta(Partida p, FactoriaTipoJuego f)
			throws MovimientoInvalido {
		// TODO Auto-generated method stub
		control.pasar();
	}

}
