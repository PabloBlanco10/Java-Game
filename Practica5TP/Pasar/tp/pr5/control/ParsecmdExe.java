package tp.pr5.control;

public class ParsecmdExe extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParsecmdExe(){
		super("No te entiendo.");
	}
	
	public ParsecmdExe(String msg){
		super(msg);
	}

}
