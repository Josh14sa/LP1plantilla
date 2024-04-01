package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.HiloJuegoCarrera;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pista extends JFrame implements ActionListener {

	private JPanel contentPane;
	public static JLabel lblPistaJuego;
	private JLabel lblPlayer2;
	public static JLabel lblPlayer1;
	private JButton btnComenzar;
	public static boolean activo = true;
	private JButton btnReiniciar;
	
	public static boolean estaActivo() {
		return activo;
	}
	public static void darActivo(boolean cambia) {
		activo = cambia;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pista frame = new Pista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblPlayer1 = new JLabel("");
		lblPlayer1.setIcon(new ImageIcon(Pista.class.getResource("/img/ryuRUN.gif")));
		lblPlayer1.setBounds(20, 11, 87, 93);
		contentPane.add(lblPlayer1);
		
		lblPistaJuego = new JLabel("");
		lblPistaJuego.setIcon(new ImageIcon(Pista.class.getResource("/img/meta.png")));
		lblPistaJuego.setBounds(560, 11, 49, 239);
		contentPane.add(lblPistaJuego);
		
		lblPlayer2 = new JLabel("");
		lblPlayer2.setIcon(new ImageIcon(Pista.class.getResource("/img/kenRUN.gif")));
		lblPlayer2.setBounds(10, 134, 87, 93);
		contentPane.add(lblPlayer2);
		
		btnComenzar = new JButton("Comenzar");
		btnComenzar.addActionListener(this);
		btnComenzar.setBounds(159, 258, 89, 23);
		contentPane.add(btnComenzar);
		
		btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.addActionListener(this);
		btnReiniciar.setBounds(300, 258, 89, 23);
		contentPane.add(btnReiniciar);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnReiniciar) {
			actionPerformedBtnReiniciar(e);
		}
		if (e.getSource() == btnComenzar) {
			actionPerformedBtnComenzar(e);
		}
	}
	protected void actionPerformedBtnComenzar(ActionEvent e) {
		HiloJuegoCarrera h1 = new HiloJuegoCarrera(lblPlayer1, "Jugador 1");
		HiloJuegoCarrera h2 = new HiloJuegoCarrera(lblPlayer2, "Jugador 2");
		h1.start();
		h2.start();
	}
	protected void actionPerformedBtnReiniciar(ActionEvent e) {
		int inicio = 0;
		lblPlayer1.setLocation(inicio,lblPlayer1.getY());
		lblPlayer2.setLocation(inicio,lblPlayer2.getY());
		darActivo(true);
	}
}
