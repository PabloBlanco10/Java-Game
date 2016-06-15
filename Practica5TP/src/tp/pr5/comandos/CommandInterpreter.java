package tp.pr5.comandos;

import tp.pr5.control.Controlador;
import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Partida;

public abstract class CommandInterpreter {

	protected static Controlador control;
	
	public static void configureControl(Controlador controlador){
		control = controlador;
	}
	
	public abstract CommandInterpreter parsear(String[] cadena);
	
	public abstract void ejecuta(Partida p, FactoriaTipoJuego f)throws MovimientoInvalido;
}
