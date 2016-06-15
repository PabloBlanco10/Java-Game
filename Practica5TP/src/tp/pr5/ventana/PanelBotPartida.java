package tp.pr5.ventana;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import tp.pr5.control.ControladorGUI;
import tp.pr5.control.TipoJuego;
import tp.pr5.control.TipoJugador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.PartidaObserver;
import tp.pr5.logica.TableroInmutable;

public class PanelBotPartida extends JPanel implements ActionListener, PartidaObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton bDeshacer;
	private JButton bReiniciar;
	private ControladorGUI c;
	
	public PanelBotPartida(ControladorGUI control){
		this.c = control;
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createTitledBorder("Partida"));
		bDeshacer = new JButton("Deshacer");
		bDeshacer.setIcon(new ImageIcon("iconos/undo.png"));
		bDeshacer.setEnabled(false);
		bReiniciar = new JButton("Reiniciar");
		bReiniciar.setIcon(new ImageIcon("iconos/reiniciar.png"));
		bDeshacer.addActionListener(this);
		bReiniciar.addActionListener(this);
		this.add(bDeshacer);
		this.add(bReiniciar);
		c.addObserver(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bDeshacer){
			c.deshacer();
		}
		else if (e.getSource() == bReiniciar){
			c.reiniciar();
		}

	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno) {
		bDeshacer.setEnabled(false);
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		bDeshacer.setEnabled(false);
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno, TipoJuego juego) {
		bDeshacer.setEnabled(false);
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		//no hace nada
		
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		if(!hayMas){
			bDeshacer.setEnabled(false);
		}
		else {
			if(c.getTipoJugador(turno) == TipoJugador.ALEATORIO){
				bDeshacer.setEnabled(false);
			}
			else{
				bDeshacer.setEnabled(true);
			}
		}
		
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno) {
		if(c.getTipoJugador(turno) == TipoJugador.ALEATORIO){
			bDeshacer.setEnabled(false);
		}
		else{
			bDeshacer.setEnabled(true);
		}
		
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		//no hace nada
		
	}

	@Override
	public void onFichasCambiadas(int blancas, int negras) {
		//no hace nada
		
	}

	@Override
	public void onRehacerNotPossible() {
		// TODO Auto-generated method stub
		
	}
	

}
