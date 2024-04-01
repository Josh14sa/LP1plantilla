package utils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import vista.Pista;

public class HiloJuegoCarrera extends Thread{
	//variables privadas
	private JLabel lblJugador;
	private String nombreJugador;
	
	//constructor
	public HiloJuegoCarrera(JLabel lblJugador, String nombreJugador) {
	
		this.lblJugador = lblJugador;
		this.nombreJugador = nombreJugador;
	}


	@Override
	public void run() {
		
		while (Pista.estaActivo()) {
			// Generar valores aleatorios
			int avance = (int) (Math.random() * 10);
			//obtener la ubicacion del eje x del jugador y sumarle el valor avance
			lblJugador.setLocation(lblJugador.getX() + avance, lblJugador.getY());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			//Condición para determinar al ganador
			//EL ganador es aquel que pasa la meta 
			if (lblJugador.getX() + lblJugador.getWidth() > Pista.lblPistaJuego.getX()) {
				Pista.darActivo(false);
				JOptionPane.showMessageDialog(null, "Ganó : " + nombreJugador);
			}
			
		}
		
		
	}

}
