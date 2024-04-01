package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.Usuario;
import mantenimiento.GestionUsuarioDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Reporte extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable tbUsuarios;
	// iNTANCIAR OBJETO PARA LA ESTRUCTURA DE LA TABLA
	DefaultTableModel model = new DefaultTableModel();
	GestionUsuarioDAO gUser = new GestionUsuarioDAO();
	private JButton btnReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reporte frame = new Reporte();
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
	public Reporte() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListadoDeUsuarios = new JLabel("Listado de Usuarios");
		lblListadoDeUsuarios.setBounds(24, 11, 194, 26);
		contentPane.add(lblListadoDeUsuarios);
		
		btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(this);
		btnReporte.setBounds(172, 236, 89, 23);
		contentPane.add(btnReporte);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 414, 176);
		contentPane.add(scrollPane);
		
		tbUsuarios = new JTable();
		tbUsuarios.setFillsViewportHeight(true);
		scrollPane.setViewportView(tbUsuarios);
		//DEFINIR LAS COLUMNAS
		model.addColumn("Código");
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("Usuario");
		model.addColumn("Tipo");
		//asociar
		tbUsuarios.setModel(model);
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnReporte) {
			actionPerformedBtnReporte(e);
		}
	}
	protected void actionPerformedBtnReporte(ActionEvent e) {
		mostrarListado();
	}

	private void mostrarListado() {
		//1. limpiar la tabla 
		model.setRowCount(0);
		//2.LLamar al proceso de consulta de ususarios
		ArrayList<Usuario> listaUser = gUser.listarUsuarios();
		//3 Validar el resultado de la consulta
		if(listaUser.size() == 0) {
			mensajeError("Lista vacía");
		}else {
			//bucle
			for (Usuario us : listaUser) {
				Object fila [] = {us.getCodigo(),
						          us.getNombre(),
						          us.getApellido(),
						          us.getUsuario(),
						          us.getTipo()
						          };
				model.addRow(fila);
			}
		}
		
		
	}

	private void mensajeError(String msj) {
		JOptionPane.showMessageDialog(this, msj,"Error !!",0);
		
	}
}
