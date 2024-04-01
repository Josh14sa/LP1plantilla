package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.TipoUsuario;
import entidad.Usuario;
import mantenimiento.GestionTipoUsuarioDAO;
import mantenimiento.GestionUsuarioDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Reporte2 extends JFrame {

	private JPanel contentPane;
	private JComboBox cboTipo;
	//instanciar un objeto
	GestionTipoUsuarioDAO gTip = new GestionTipoUsuarioDAO();
	//instanciar un objeto para la estructura de la tabl
	DefaultTableModel model = new DefaultTableModel();
	//
	GestionUsuarioDAO gUser = new GestionUsuarioDAO();
	
	private JTable tbUsuarios;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reporte2 frame = new Reporte2();
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
	public Reporte2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListadoDeUsuarios = new JLabel("Listado de Usuarios");
		lblListadoDeUsuarios.setBounds(24, 11, 194, 26);
		contentPane.add(lblListadoDeUsuarios);

		JButton btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listarUsuarios();
				
			}

			
		});
		btnReporte.setBounds(172, 236, 89, 23);
		contentPane.add(btnReporte);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 44, 46, 14);
		contentPane.add(lblTipo);

		cboTipo = new JComboBox();
		cboTipo.setBounds(66, 41, 157, 20);
		contentPane.add(cboTipo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 414, 142);
		contentPane.add(scrollPane);
		
		tbUsuarios = new JTable();
		scrollPane.setViewportView(tbUsuarios);
		tbUsuarios.setFillsViewportHeight(true);
		//crear columnas
		model.addColumn("Código");
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("id Tipo Usuario");
		//
		tbUsuarios.setModel(model);
		
		//cargar data al cbo
		cargarDataCbo();

		
	}

	private void cargarDataCbo() {
		// 1. Llamar al proceso de consulta
		ArrayList<TipoUsuario> lista = gTip.listarTipoUsuario();
		//2 Validar el resultado de la consulta
		if(lista.size() == 0) {
			mensajeError("Lista vacía");
		}else {
			cboTipo.addItem("Seleccione ... ");
			for (TipoUsuario tipoUser : lista) {
				cboTipo.addItem(tipoUser.getIdTipo() + " - "+tipoUser.getDescripTipo());
			}
		}
		
	}

	private void mensajeError(String msj) {
		JOptionPane.showMessageDialog(this, msj,"Error !!",0);
		
	}
	private void listarUsuarios() {
		
		//LIMPIAR LA TABLA
		model.setRowCount(0);
		
		int tipo;
		tipo = getTipo();
		//validar
		if(tipo == 0) {
			return;
		}else {
			//llamar al proceso de listar Usuario por tipo
			ArrayList<Usuario> lista = gUser.listarUsuariosXTipo(tipo);
			//validar el resultado del proceso
			if(lista.size() == 0) {
				mensajeError("Lista vacía");
			}else {
				for (Usuario u: lista) {
					Object fila [] = {u.getCodigo(),
							          u.getNombre(),
							          u.getApellido(),
							          u.getTipo()
							          };
					model.addRow(fila);
				}
			}
		}
		
	}

	private int getTipo() {
		int tipo;//completar la validación
		tipo = cboTipo.getSelectedIndex();
		return tipo;
	}
}
