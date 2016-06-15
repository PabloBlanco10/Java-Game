package tp.pr5.comandos;


public class CommandParser {
	
	private static CommandInterpreter[] commands = { new Poner(), new Ayuda(), new CambiarJugador(), new Deshacer(), new Jugar(), new Reiniciar(), new Salir(), new Rehacer()};

	public static CommandInterpreter parseo(String cad){
		String[] array = cad.split(" ");
		CommandInterpreter com;
		
		for (CommandInterpreter c: commands){
			com = c.parsear(array);
			if(com != null) return com;
		}
		return null;
	}
}
