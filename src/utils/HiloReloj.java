package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class HiloReloj extends Thread {
	//componente privado
	private JLabel lblReloj;
	
	//constructor
	public HiloReloj(JLabel lblReloj) {
		this.lblReloj = lblReloj;
	}
	
	@Override
	public void run() {
		while (true) {
			// instanciar un objeto de la clase "Date" para obtener la hora del sistema
			Date hora = new Date();
			// instanciar un objeto de la clase"SimpleDateFormat" para definir el formato de
			// la hora
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			// mostrar la hora en la etiqueta "lblReloj"
			lblReloj.setText(sdf.format(hora));
		}
	}
	

}
