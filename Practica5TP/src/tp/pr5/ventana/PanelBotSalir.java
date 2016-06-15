package tp.pr5.ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.control.ControladorGUI;
import tp.pr5.control.TipoJuego;

public class PanelBotSalir extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton bSalir;
	private ControladorGUI c;
	
	
	public PanelBotSalir(ControladorGUI control, TipoJuego t){
		this.c = control;
		this.setLayout(new BorderLayout());
		bSalir = new JButton("Salir");
		bSalir.setIcon(new ImageIcon("iconos/exit.png"));

		//el panel de tablero esta escuchando al boton
		bSalir.addActionListener(this);
		JPanel aux = new JPanel(new FlowLayout());
		aux.add(bSalir);
		this.add(aux, BorderLayout.SOUTH);
		this.add(new PanelPartida(c,t), BorderLayout.NORTH);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(JOptionPane.showConfirmDialog(this, "¿Esta seguro de que desea salir?", "Salir", JOptionPane.YES_NO_OPTION) == 0)
			c.salir();
	}

}
