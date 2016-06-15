package tp.pr5.ventana;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tp.pr5.control.ControladorGUI;
import tp.pr5.control.TipoJuego;
import tp.pr5.control.TipoJugador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.PartidaObserver;
import tp.pr5.logica.TableroInmutable;

public class Ventana extends JFrame implements PartidaObserver{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControladorGUI c;
	
	public Ventana(ControladorGUI control, TipoJuego t){
		this.c = control;
		this.setTitle("Practica 5");
		this.setLayout(new BorderLayout());
		this.setSize(600, 600);
		this.add(new PanelBotAleatorio(c), BorderLayout.WEST);
		this.add(new PanelBotSalir(c,t), BorderLayout.EAST);
		c.addObserver(this);
		this.pack();
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno) {
		JOptionPane.showMessageDialog(this, "Partida reiniciada", "Partida reiniciada", JOptionPane.INFORMATION_MESSAGE);
		this.pack();
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		
		
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno, TipoJuego juego) {
		//no hace nada
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		JOptionPane.showMessageDialog(this, "Imposible deshacer", "Deshacer", JOptionPane.ERROR_MESSAGE);
		
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		this.pack();
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno) {
		this.pack();
		
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		JOptionPane.showMessageDialog(this, movimientoException.getMessage(), "Movimiento Invalido", JOptionPane.ERROR_MESSAGE);

		
	}

	@Override
	public void onFichasCambiadas(int blancas, int negras) {
		//no hace nada
	}

	@Override
	public void onRehacerNotPossible() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, "Imposible rehacer", "Rehacer", JOptionPane.ERROR_MESSAGE);

	}
	

}
