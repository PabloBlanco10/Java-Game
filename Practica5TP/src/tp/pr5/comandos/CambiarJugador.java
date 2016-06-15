package tp.pr5.comandos;

import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.control.TipoJugador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Partida;

public class CambiarJugador extends CommandInterpreter{
	
	private TipoJugador jugador;
	private Ficha color;
	
	public CambiarJugador(TipoJugador j, Ficha c){
		this.color = c;
		this.jugador = j;
	}
	
	public CambiarJugador(){
		
	}

	@Override
	public CommandInterpreter parsear(String[] cadena) {
		//para comprobar si recibe todas las cadenas que necesita para ejecutarse
		if(cadena.length < 3){
			return null;
		}
		
		if(cadena[0].toUpperCase().matches("JUGADOR")){
			if(cadena[1].toUpperCase().matches("BLANCAS")){
				if(cadena[2].toUpperCase().matches("ALEATORIO")){
					return new CambiarJugador(TipoJugador.ALEATORIO, Ficha.BLANCA);
				}
				else if(cadena[2].toUpperCase().matches("HUMANO")){
					return new CambiarJugador(TipoJugador.HUMANO, Ficha.BLANCA);
				}
				
				else{
					return null;
				}
			}
			else if(cadena[1].toUpperCase().matches("NEGRAS")){
				if(cadena[2].toUpperCase().matches("ALEATORIO")){
					return new CambiarJugador(TipoJugador.ALEATORIO, Ficha.NEGRA);
				}
				else if(cadena[2].toUpperCase().matches("HUMANO")){
					return new CambiarJugador(TipoJugador.HUMANO, Ficha.NEGRA);
				}
				
				else{
					return null;
				}
			}
			
			else{
				return null;
			}

		}
		return null;
	}

	@Override
	public void ejecuta(Partida p, FactoriaTipoJuego f)
			throws MovimientoInvalido {
		control.cambiarJugador(jugador, color);
	}

}
