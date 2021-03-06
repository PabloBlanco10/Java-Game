package tp.pr5.ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class PanelBotAleatorio extends JPanel implements ActionListener, PartidaObserver{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton bPonerAleatorio;
	private ControladorGUI c;
	private JButton bRehacer;

	public PanelBotAleatorio(ControladorGUI c){
		this.c = c;
		this.setLayout(new BorderLayout());
		bPonerAleatorio = new JButton("Poner Aleatorio");
		bPonerAleatorio.setIcon(new ImageIcon("iconos/random.png"));
		bRehacer = new JButton ("Rehacer");
		
		//el panel de tablero esta escuchando al boton
		bRehacer.addActionListener(this);
		bPonerAleatorio.addActionListener(this);
		JPanel aux = new JPanel(new FlowLayout());
		aux.add(bPonerAleatorio);
		aux.add(bRehacer);
		this.add(aux, BorderLayout.SOUTH);
		this.add(new PanelTablero(c), BorderLayout.NORTH);
		c.addObserver(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(bPonerAleatorio))
			c.ponerAleatorio();
		else
			c.rehacer();
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno) {
		bPonerAleatorio.setEnabled(true);
		
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		bPonerAleatorio.setEnabled(false);
		
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno, TipoJuego juego) {
		bPonerAleatorio.setEnabled(true);
		
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		//no hace nada
		
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		if(c.getTipoJugador(turno) == TipoJugador.ALEATORIO){
			bPonerAleatorio.setEnabled(false);
		}
		else{
			bPonerAleatorio.setEnabled(true);
		}
		
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno) {
		if(c.getTipoJugador(turno) == TipoJugador.ALEATORIO){
			bPonerAleatorio.setEnabled(false);
		}
		else{
			bPonerAleatorio.setEnabled(true);
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
