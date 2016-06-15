package tp.pr5.logica;

public interface TableroObserver {

	public void onReset();
	
	public void onCasillaCambiada(int x, int y, Ficha color);
}
