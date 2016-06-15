package tp.pr5.ventana;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tp.pr5.control.ControladorGUI;
import tp.pr5.control.TipoJuego;

public class PanelCambioJuego extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cb;
	private JLabel lFilas;
	private JLabel lCols;
	private JTextField tfFilas;
	private JTextField tfCols;
	private JButton bCambiar;
	private ControladorGUI c;
	private JLabel lLim;
	private JTextField tfLim;
	
	private TipoJuego j;
	
	private static String[] opciones = {"Conecta 4", "Complica", "Gravity", "Reversi"};
	
	public PanelCambioJuego(ControladorGUI control, TipoJuego t){
		
		this.c = control;
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Cambio de Juego"));
		this.setPreferredSize(new Dimension(this.getWidth(), 160));
		cb = new JComboBox<String>(opciones);
		switch(t){
		case COMPLICA:
			cb.setSelectedIndex(1);
			j = TipoJuego.COMPLICA;
			break;
		case CONECTA4:
			cb.setSelectedIndex(0);
			j = TipoJuego.CONECTA4;
			break;
		case GRAVITY:
			cb.setSelectedIndex(2);
			j = TipoJuego.GRAVITY;
			break;
		case REVERSI:
			cb.setSelectedIndex(3);
			j = TipoJuego.REVERSI;
			break;
		default:
			cb.setSelectedIndex(0);
			j = TipoJuego.CONECTA4;
			break;
		}
		cb.addActionListener(this);
		lLim = new JLabel("Límite de movimientos: ");
		tfLim = new JTextField (7);
		lFilas = new JLabel("Filas");
		lFilas.setVisible(false);
		lCols = new JLabel("Columnas");
		lCols.setVisible(false);
		tfFilas = new JTextField(7);
		tfFilas.setVisible(false);
		tfCols = new JTextField(7);
		tfCols.setVisible(false);
		bCambiar = new JButton("Cambiar");
		bCambiar.setIcon(new ImageIcon("iconos/aceptar.png"));
		bCambiar.addActionListener(this);
		JPanel aux = new JPanel(new FlowLayout());
		aux.add(lLim);
		aux.add(tfLim);
		aux.add(lFilas);
		aux.add(tfFilas);
		aux.add(lCols);
		aux.add(tfCols);
		JPanel aux2 = new JPanel(new FlowLayout());
		aux2.add(bCambiar);
		this.add(aux, BorderLayout.SOUTH);
		this.add(cb, BorderLayout.NORTH);
		this.add(aux, BorderLayout.CENTER);
		this.add(aux2, BorderLayout.SOUTH);


		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("Cambiar".equalsIgnoreCase(e.getActionCommand())){
			// Aqui es el JButton
			
			if(j == TipoJuego.GRAVITY){
				try{
				String s = tfFilas.getText();
				int fil = Integer.parseInt(s);
				s = tfCols.getText();
				int col = Integer.parseInt(s);
				String lim = tfLim.getText();
				int li = Integer.parseInt(lim);
				
				
				c.cambiarJuego(j, fil, col, li);
				} catch(NumberFormatException e1){
					tfFilas.setText("");
					tfCols.setText("");
					tfLim.setText("");

					JOptionPane.showMessageDialog(this, "Datos incorrectos", "Advertencia",JOptionPane.WARNING_MESSAGE);
				}
			}
			else {
				//ponemos 0, 0 porque la unica factoria que tiene en cuenta alto y ancho es la de gravity
				
				try{
					String lim = tfLim.getText();
					int li = Integer.parseInt(lim);
					c.cambiarJuego(j, 0, 0, li);
				}catch(NumberFormatException e2){
					tfLim.setText("");
					JOptionPane.showMessageDialog(this, "Datos incorrectos", "Advertencia",JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		else{
			// Aqui la Combobox
			boolean visibles = false;
			JComboBox<String> aux = (JComboBox<String>) e.getSource();
	        String opcion = (String)aux.getSelectedItem();
	        if("Gravity".equalsIgnoreCase(opcion)){
	        	visibles = true;
	        	j = TipoJuego.GRAVITY;
	        }
	        else if ("Complica".equalsIgnoreCase(opcion)){
	        	
	        	j = TipoJuego.COMPLICA;
	        }
	        else if ("Conecta 4".equalsIgnoreCase(opcion)){
	        	
	        	j = TipoJuego.CONECTA4;
	        }
	        else if ("Reversi".equalsIgnoreCase(opcion)){
	        	
	        	j = TipoJuego.REVERSI;
	        }
	        lFilas.setVisible(visibles);
        	lCols.setVisible(visibles);
        	tfFilas.setVisible(visibles);
        	tfCols.setVisible(visibles);
		}
	}

}
