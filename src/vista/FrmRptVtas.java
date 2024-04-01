package vista;

import java.awt.EventQueue;

import javax.swing.JLabel;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JScrollPane;


public class FrmRptVtas extends JInternalFrame {
	private JDateChooser dcFecha;
	private JTextField txtFechaActual;
	private JLabel lblNewLabel_1;
	private JTextArea txtS;
	private JScrollPane scrollPane;
	Date fecha = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRptVtas frame = new FrmRptVtas();
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
	public FrmRptVtas() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 450, 297);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Reporte de Ventas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 215, 37);
		getContentPane().add(lblNewLabel);
		
		JLabel lblDel = new JLabel("Fecha Fin:");
		lblDel.setBounds(20, 87, 77, 24);
		getContentPane().add(lblDel);
		
		JButton btnReporte = new JButton("Reporte");
		btnReporte.setBounds(302, 60, 89, 23);
		getContentPane().add(btnReporte);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 123, 414, 130);
		getContentPane().add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
		
		dcFecha = new JDateChooser();
		dcFecha.setBounds(97, 87, 119, 20);
		getContentPane().add(dcFecha);
		
		txtFechaActual = new JTextField();
		txtFechaActual.setEnabled(false);
		txtFechaActual.setBounds(97, 56, 119, 20);
		getContentPane().add(txtFechaActual);
		txtFechaActual.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Fecha Inicio");
		lblNewLabel_1.setBounds(20, 59, 89, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtFechaActual.setText(sdf.format(fecha));

	}
}
