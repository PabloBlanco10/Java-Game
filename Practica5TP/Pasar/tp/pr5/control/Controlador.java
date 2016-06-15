package tp.pr5.control;

import tp.pr5.logica.Ficha;


public interface Controlador {

	void cambiarJugador(TipoJugador jugador, Ficha color);

	void deshacer();

	void cambiarJuego(TipoJuego jugar, int tamX, int tamY, int limite);

	void poner();

	void reiniciar();

	void pasar();

}
