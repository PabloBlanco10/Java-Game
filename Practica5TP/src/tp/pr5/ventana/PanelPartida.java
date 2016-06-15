package tp.pr5.ventana;

import java.awt.BorderLayout;





import javax.swing.JPanel;

import tp.pr5.control.ControladorGUI;
import tp.pr5.control.TipoJuego;

public class PanelPartida extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public PanelPartida(ControladorGUI c, TipoJuego t){
		this.setLayout(new BorderLayout());
		this.add(new PanelBotPartida(c), BorderLayout.NORTH);
		this.add(new PanelGestionJugadores(c), BorderLayout.CENTER);
		this.add(new PanelCambioJuego(c,t), BorderLayout.SOUTH);

	}
}
