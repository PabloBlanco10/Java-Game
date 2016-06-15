package tp.pr5.ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

import tp.pr5.control.ControladorGUI;
import tp.pr5.control.TipoJuego;
import tp.pr5.control.TipoJugador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.PartidaObserver;
import tp.pr5.logica.TableroInmutable;

public class PanelTablero extends JPanel implements PartidaObserver, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector <JButton> tablero;
	private JLabel turno;
	private ControladorGUI c;
	private JPanel botones;
	private JLabel nBlancas;
	private JLabel nNegras;
	private JPanel cuadricula;
	private JPanel numeros;
	private JPanel letras;
	
	private final Color VERDE = new Color(24,186,97);
	private final Color AMARILLO = new Color(220,254,130);
	
	
	public PanelTablero(ControladorGUI control){
		this.c = control;
		tablero = new Vector <JButton>();
		turno = new JLabel("Juegan " + c.getJugadorInicial().toString().toLowerCase() + "s");
		this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		this.setLayout(new BorderLayout());
		
		try {
			UIManager.setLookAndFeel(new MetalLookAndFeel());
		}catch(Exception e) {}
		
		JPanel pLabel = new JPanel();
		turno.setFont(new Font("Arial",Font.BOLD, 17));
		turno.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		turno.setBackground(Color.WHITE);
		turno.setForeground(Color.BLUE);
		turno.setHorizontalAlignment(pLabel.getWidth()/2);
		
		pLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		pLabel.setLayout(new BorderLayout());
		pLabel.setPreferredSize(new Dimension(this.getWidth(), 50));
		pLabel.setBackground(Color.WHITE);
		pLabel.add(turno);
		
		
		TableroInmutable t = c.getTableroInmutable();
		botones = new JPanel(new GridLayout(t.getFilas(), t.getColumnas()));
		
		for(int i = 1; i <= t.getFilas(); i++){
			for(int j = 1; j <= t.getColumnas(); j++){
				JButton aux = new JButton();
				if(t.getCasilla(j, i) == Ficha.BLANCA){
					aux.setBackground(Color.WHITE);
				}
				else if(t.getCasilla(j, i) == Ficha.NEGRA){
					aux.setBackground(Color.BLACK);
				}
				else if(t.getCasilla(j, i) == Ficha.VACIA){
					if(c.flanqueo(i, j, c.getJugadorInicial())){
						aux.setBackground(AMARILLO);
					}
					else{
						aux.setBackground(VERDE);
					}
				}
				aux.setPreferredSize(new Dimension(30,30));
				aux.addActionListener(this);
				tablero.add(aux);
				botones.add(aux);
			}
		}
		
		String sec = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		cuadricula = new JPanel(new BorderLayout());
		letras = new JPanel(new GridLayout(1, t.getColumnas() + 1));
		numeros = new JPanel(new GridLayout(t.getFilas(), 1));
		for(int i = 1; i <= t.getFilas(); i++){
			JLabel et = new JLabel(Integer.toString(i));
			et.setPreferredSize(new Dimension(30,30));
			et.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			et.setVerticalAlignment(SwingConstants.CENTER);
			et.setHorizontalAlignment(SwingConstants.CENTER);
			numeros.add(et);
		}
		
		JLabel et2 = new JLabel("   ");
		et2.setPreferredSize(new Dimension(30,30));
		et2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		et2.setHorizontalAlignment(SwingConstants.CENTER);
		letras.add(et2);

		for(int j = 1; j <= t.getColumnas(); j++){
			JLabel et = new JLabel(" " + sec.charAt(j - 1) + " ");
			et.setPreferredSize(new Dimension(30,30));
			et.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			et.setHorizontalAlignment(SwingConstants.CENTER);
			letras.add(et);
		}
		cuadricula.add(letras, BorderLayout.NORTH);
		cuadricula.add(numeros, BorderLayout.WEST);
		cuadricula.add(botones, BorderLayout.CENTER);
		JPanel nFichas = new JPanel(new GridLayout(1,2));
		nBlancas = new JLabel("Blancas: ");
		nNegras = new JLabel("Negras: ");
		nBlancas.setHorizontalAlignment(SwingConstants.CENTER);
		nNegras.setHorizontalAlignment(SwingConstants.CENTER);
		nFichas.add(nBlancas);
		nFichas.add(nNegras);
		nFichas.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		this.add(cuadricula, BorderLayout.NORTH);
		this.add(nFichas, BorderLayout.CENTER);
		this.add(pLabel, BorderLayout.SOUTH);
		c.addObserver(this);
	}
	
	
	@Override
	public void onReset(TableroInmutable tab, Ficha turno) {
		tablero.clear();
		//this.remove(botones);
		botones.removeAll();
		botones.setLayout((new GridLayout(tab.getFilas(), tab.getColumnas())));
		for(int i = 1; i <= tab.getFilas(); i++){
			for(int j = 1; j <= tab.getColumnas(); j++){
				JButton aux = new JButton();
				if(tab.getCasilla(j, i) == Ficha.BLANCA){
					aux.setBackground(Color.WHITE);
				}
				else if(tab.getCasilla(j, i) == Ficha.NEGRA){
					aux.setBackground(Color.BLACK);
				}
				else if(tab.getCasilla(j, i) == Ficha.VACIA){
					if(c.flanqueo(i, j, turno)){
						aux.setBackground(AMARILLO);
					}
					else{
						aux.setBackground(VERDE);
					}
				}
				aux.setPreferredSize(new Dimension(30,30));
				aux.addActionListener(this);
				tablero.add(aux);
				botones.add(aux);
			}
		}
		
		this.turno.setText("Juegan " + turno.toString().toLowerCase() + "s");
		
		this.updateUI();
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		for(JButton i: this.tablero){
			i.setEnabled(false);
		}
		String s;
		if (ganador == Ficha.VACIA){
			s = "Partida en tablas";
		}
		else{
			s = "Ganan las " + ganador.toString().toLowerCase() + "s";
		}
		turno.setText(s);
	}

	@Override
	public void onCambioJuego(TableroInmutable t, Ficha turno, TipoJuego juego) {
		tablero.clear();
		cuadricula.remove(letras);
		cuadricula.remove(numeros);
		letras = new JPanel(new GridLayout(1, t.getColumnas() + 1));
		numeros = new JPanel(new GridLayout(t.getFilas(), 1));
		String sec = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		
		for(int i = 1; i <= t.getFilas(); i++){
			JLabel et = new JLabel(Integer.toString(i));
			et.setPreferredSize(new Dimension(30,30));
			et.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			et.setVerticalAlignment(SwingConstants.CENTER);
			et.setHorizontalAlignment(SwingConstants.CENTER);
			numeros.add(et);
		}
		letras.add(new JLabel("   "));
		for(int j = 1; j <= t.getColumnas(); j++){
			JLabel et = new JLabel(" " + sec.charAt(j - 1) + " ");
			et.setPreferredSize(new Dimension(30,30));
			et.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			et.setHorizontalAlignment(SwingConstants.CENTER);
			letras.add(et);
		}
		cuadricula.add(letras, BorderLayout.NORTH);
		//numeros.setAlignmentY(HEIGHT);
		cuadricula.add(numeros, BorderLayout.WEST);
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		// no hace nada
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		this.ponerFicha(tablero, turno);
		if(c.getTipoJugador(turno) == TipoJugador.ALEATORIO){
			for(JButton i: this.tablero){
				i.setEnabled(false);
			}
		}
		else{
			for(JButton i: this.tablero){
				i.setEnabled(true);
			}
		}

	}

	private void ponerFicha(TableroInmutable tablero, Ficha turno) {
		for(int i = 0; i < tablero.getFilas(); i++){
			for(int j = 0; j < tablero.getColumnas(); j++){
				if(tablero.getCasilla(j+1, i+1) == Ficha.BLANCA){
					this.tablero.get(i*tablero.getColumnas() + j).setBackground(Color.WHITE);
				}
				else if(tablero.getCasilla(j+1, i+1) == Ficha.NEGRA){
					this.tablero.get(i*tablero.getColumnas() + j).setBackground(Color.BLACK);
				}
				else if(tablero.getCasilla(j+1, i+1) == Ficha.VACIA){
					if(c.flanqueo(i+1, j+1, turno)){
						this.tablero.get(i*tablero.getColumnas() + j).setBackground(AMARILLO);
					}
					else{
						this.tablero.get(i*tablero.getColumnas() + j).setBackground(VERDE);
					}
				}
			}
		}
		
		this.turno.setText("Juegan " + turno.toString().toLowerCase() + "s");
	}


	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno) {
		this.ponerFicha(tablero, turno);
		if(c.getTipoJugador(turno) == TipoJugador.ALEATORIO){
			for(JButton i: this.tablero){
				i.setEnabled(false);
			}
		}
		else{
			for(JButton i: this.tablero){
				i.setEnabled(true);
			}
		}
		
		
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		// no hace nada
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		int i = 0;
		int j = 0;
		TableroInmutable t = c.getTableroInmutable();
		boolean encontrado = false;
		while(i < t.getFilas() && !encontrado){
			j = 0;
			while(j < t.getColumnas() && !encontrado){
				if (tablero.get(i*t.getColumnas() + j) == e.getSource()){
					encontrado = true;
				}
				else{
					j++;
				}
			}
			if(!encontrado)
			i++;
		}
		
		c.poner(j+1, i+1);
	}


	@Override
	public void onFichasCambiadas(int blancas, int negras) {
		nBlancas.setText("Blancas: " + Integer.toString(blancas));
		nNegras.setText("Negras: " + Integer.toString(negras));
		
	}


	@Override
	public void onRehacerNotPossible() {
		// TODO Auto-generated method stub
		
	}

}
