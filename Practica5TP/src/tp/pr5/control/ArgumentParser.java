package tp.pr5.control;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


public class ArgumentParser {
	
	private final String usage = "tp.pr4.Main [-g <game>] [-h] [-x <columnNumber>] [-y <rowNumber>]";
	private Options options;
	private CommandLine cmd;
	
	public ArgumentParser(String[] args){
		BasicParser p = new BasicParser();
		options = new Options();
		
		options.addOption("h", "help", false, "Muestra esta ayuda.");
		options.addOption("g", "game", true, "Tipo de juego (c4, co, gr) . Por defecto, c4.");
		options.addOption("x", "tamX", true, "Número de columnas del tablero (sólo para Gravity). Por defecto, 10.");
		options.addOption("y", "tamY", true, "Número de filas del tablero (sólo para Gravity). Por defecto, 10.");
		options.addOption("u","ui", true, "Tipo de interfaz");
		
		options.getOption("g").setArgName("game");
		options.getOption("x").setArgName("columnNumber");
		options.getOption("y").setArgName("rowNumber");
		options.getOption("u").setArgName("tipo");
		
		
		try {
			cmd = p.parse(options, args);
		} catch (ParseException e) {
			System.err.println("Uso incorrecto: " + e.getMessage());
			System.err.println("Uso -h/--help para más detalles.");
			System.exit(1);
		}

	}
	
	public boolean tieneAyuda(){
		return cmd.hasOption("h");
	}
	
	public void mostrarAyuda(){
		HelpFormatter h = new HelpFormatter();
		h.printHelp(usage, options);
		System.exit(0);
	}
	
	public TipoJuego pedirJuego() throws ArgumentException{
		if (cmd.hasOption("g")){
			if(cmd.getArgs().length > 0){
				String aux = "";
				for(String i : cmd.getArgs()){
					aux += " " + i;
				}
				throw new ArgumentException("Argumentos no entendidos:" + aux);
			}
			if("co".equalsIgnoreCase(cmd.getOptionValue("g"))){
				return TipoJuego.COMPLICA;
			}
			
			else if ("gr".equalsIgnoreCase(cmd.getOptionValue("g"))){
				return TipoJuego.GRAVITY;
			}
			
			else if("c4".equalsIgnoreCase(cmd.getOptionValue("g"))){
				return TipoJuego.CONECTA4;
			}
			else if("rv".equalsIgnoreCase(cmd.getOptionValue("g"))){
				return TipoJuego.REVERSI;
			}
			else if("tr".equalsIgnoreCase(cmd.getOptionValue("g"))){
				return TipoJuego.TRES;
			}
			else{
				throw new ArgumentException("Juego '" + cmd.getOptionValue("g") + "' incorrecto.");
			}
		}
		else {
			return TipoJuego.CONECTA4;
		}
	}
	
	public TipoInterfaz pedirInterfaz() throws ArgumentException{
		if(cmd.hasOption("u")){
			if("console".equalsIgnoreCase(cmd.getOptionValue("u"))){
				return TipoInterfaz.CONSOLE;
			}
			
			else if ("window".equalsIgnoreCase(cmd.getOptionValue("u"))){
				return TipoInterfaz.WINDOW;
			}
			
			else{
				throw new ArgumentException("Interfaz '" + cmd.getOptionValue("u") + "' incorrecta.");
			}
		}
		
		else{
			return TipoInterfaz.CONSOLE;
		}
		
	}
	
	public int pedirTamX() throws ArgumentException{
		if (cmd.hasOption("x")){
			try{
				return Integer.parseInt(cmd.getOptionValue("x"));
			}catch (NumberFormatException e){
				throw new ArgumentException("Argumento x incorrecto: " + cmd.getOptionValue("x"));
			}
		}
		else {
			return 10;
		}
	}
	
	public int pedirTamY() throws ArgumentException{
		if (cmd.hasOption("y")){
			try{
				return Integer.parseInt(cmd.getOptionValue("y"));
			}catch (NumberFormatException e){
				throw new ArgumentException("Argumento y incorrecto: " + cmd.getOptionValue("y"));
			}
		}
		else {
			return 10;
		}
	}

	public int pedirTamJ() throws ArgumentException {
		// TODO Auto-generated method stub
		if (cmd.hasOption("x")){
			try{
				return Integer.parseInt(cmd.getOptionValue("y"));
			}catch (NumberFormatException e){
				throw new ArgumentException("Argumento x incorrecto: " + cmd.getOptionValue("y"));
			}
		}
		else {
			return 7;
		}
	}

	public int pedirTamH() throws ArgumentException {
		if (cmd.hasOption("y")){
			try{
				return Integer.parseInt(cmd.getOptionValue("y"));
			}catch (NumberFormatException e){
				throw new ArgumentException("Argumento y incorrecto: " + cmd.getOptionValue("y"));
			}
		}
		else {
			return 6;
		}
	}

}
