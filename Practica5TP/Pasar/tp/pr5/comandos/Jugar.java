package tp.pr5.comandos;

import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.control.TipoJuego;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Partida;

public class Jugar extends CommandInterpreter{
	
	private TipoJuego jugar;
	private int tamX;
	private int tamY;
	private int limite;
	
	public Jugar(TipoJuego j, int ancho, int alto, int limite){
		this.jugar = j;
		this.tamX = ancho;
		this.tamY = alto;
		this.limite = limite;
	}
	
	public Jugar(TipoJuego j, int limite){
		this.jugar = j;
		this.tamX = 10;
		this.tamY = 10;
		this.limite = limite;
	}
	
	//para que no de problemas con el parser
	public Jugar(){
		this.tamX = 10;
		this.tamY = 10;
	}



	@Override
	public CommandInterpreter parsear(String[] cadena) {
		if(cadena.length < 3){
			return null;
		}
		if(cadena[0].toUpperCase().matches("JUGAR")){
			if(cadena[1].toUpperCase().matches("CO")){
				return new Jugar(TipoJuego.COMPLICA, Integer.parseInt(cadena[2]));
			}
			else if(cadena[1].toUpperCase().matches("C4")){
				return new Jugar(TipoJuego.CONECTA4, Integer.parseInt(cadena[2]));
			}
			else if(cadena[1].toUpperCase().matches("RV")){
				return new Jugar(TipoJuego.REVERSI, Integer.parseInt(cadena[2]));
			}
			else if(cadena[1].toUpperCase().matches("GR")){
				if(cadena.length < 4){
					return null;
				}
				int ancho;
				int alto;
				try{
					ancho = Integer.parseInt(cadena[2]);
					alto = Integer.parseInt(cadena[3]);
					//contemplamos el caso de que ancho o alto sean negativos
					if(ancho > 0 && alto > 0)
						return new Jugar(TipoJuego.GRAVITY, ancho, alto, Integer.parseInt(cadena[4]));
					
					else {
						return new Jugar(TipoJuego.GRAVITY, 1, 1, Integer.parseInt(cadena[4]));
					}
				}catch (NumberFormatException e){
					return null;
				}
			}
		}
		return null;
	}

	@Override
	public void ejecuta(Partida p, FactoriaTipoJuego f)
			throws MovimientoInvalido {
		control.cambiarJuego(jugar, tamX, tamY, limite);
	}
	

}
