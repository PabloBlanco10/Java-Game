package tp.pr5.ventana;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.pr5.control.ControladorGUI;
import tp.pr5.control.TipoJuego;
import tp.pr5.control.TipoJugador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.PartidaObserver;
import tp.pr5.logica.TableroInmutable;

public class PanelGestionJugadores extends JPanel implements ActionListener, PartidaObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> cbBlancas;
	private JComboBox<String> cbNegras;
	private ControladorGUI c;
	private static String[] tipoJugador = {"Humano", "Automatico"};
	
	public PanelGestionJugadores(ControladorGUI control){
		this.c = control;
		this.setLayout(new GridLayout(2,2));
		this.setBorder(BorderFactory.createTitledBorder("Gestion de Jugadores"));
		this.setPreferredSize(new Dimension(this.getWidth(), 160));
		JPanel auxBlancas = new JPanel(new GridLayout(3,1));
		cbBlancas = new JComboBox<String>(tipoJugador);
		cbBlancas.setSelectedIndex(0);
		cbBlancas.addActionListener(this);
		auxBlancas.add(new JPanel());
		auxBlancas.add(cbBlancas);
		JPanel auxNegras = new JPanel(new GridLayout(3,1));
		cbNegras = new JComboBox<String>(tipoJugador);
		cbNegras.setSelectedIndex(0);
		cbNegras.addActionListener(this);
		auxNegras.add(new JPanel());
		auxNegras.add(cbNegras);
		JLabel jBlancas = new JLabel("Jugador de Blancas");
		JLabel jNegras = new JLabel("Jugador de Negras");
		this.add(jBlancas);
		this.add(auxBlancas);
		this.add(jNegras);
		this.add(auxNegras);
		c.addObserver(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> aux = (JComboBox<String>) e.getSource();
		if(aux == cbBlancas){
			 String opcion = (String)aux.getSelectedItem();
			 if("Humano".equalsIgnoreCase(opcion)){
				 c.cambiarJugador(TipoJugador.HUMANO, Ficha.BLANCA);
			 }
			 else if("Automatico".equalsIgnoreCase(opcion)){
				 c.cambiarJugador(TipoJugador.ALEATORIO, Ficha.BLANCA);
				 c.ejecutaHebraBlancas();
			 }
		}
       
		else if(aux == cbNegras){
			 String opcion = (String)aux.getSelectedItem();
			 if("Humano".equalsIgnoreCase(opcion)){
				 c.cambiarJugador(TipoJugador.HUMANO, Ficha.NEGRA);
			 }
			 else if("Automatico".equalsIgnoreCase(opcion)){
				 c.cambiarJugador(TipoJugador.ALEATORIO, Ficha.NEGRA);
				 c.ejecutaHebraNegras();
			 }
		}
		
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno) {
		c.interrumpirHebraBlancas();
		c.interrumpirHebraNegras();
		cbBlancas.setSelectedIndex(0);
		cbNegras.setSelectedIndex(0);
		cbBlancas.setEnabled(true);
		cbNegras.setEnabled(true);
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		c.interrumpirHebraBlancas();
		c.interrumpirHebraNegras();
		// deshabilitar las combobox
		cbBlancas.setEnabled(false);
		cbNegras.setEnabled(false);
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno, TipoJuego juego) {
		//noo hacemos nada
		
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		// no hace nada
		
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas, int blancas, int negras) {
		if(turno == Ficha.BLANCA){
			if(c.getTipoJugador(Ficha.BLANCA) == TipoJugador.ALEATORIO){
				c.ejecutaHebraBlancas();
			}
		}
		else if(turno == Ficha.NEGRA){
			if(c.getTipoJugador(Ficha.NEGRA) == TipoJugador.ALEATORIO){
				c.ejecutaHebraNegras();
			}
		}
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno, int blancas, int negras) {
		if(turno == Ficha.BLANCA){
			if(c.getTipoJugador(Ficha.BLANCA) == TipoJugador.ALEATORIO){
				c.ejecutaHebraBlancas();
			}
		}
		else if(turno == Ficha.NEGRA){
			if(c.getTipoJugador(Ficha.NEGRA) == TipoJugador.ALEATORIO){
				c.ejecutaHebraNegras();
			}
		}
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		// no hace nada
		
	}

	@Override
	public void onFichasCambiadas(int blancas, int negras) {
		//no hace nada
		
	}

	@Override
	public void onLimiteAlcanzado() {
		// TODO Auto-generated method stub

		cbBlancas.setEnabled(false);
		cbNegras.setEnabled(false);
	}
		

}
