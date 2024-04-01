package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import entidad.TipoUsuario;
import entidad.Usuario;
import mantenimiento.GestionTipoUsuarioDAO;
import mantenimiento.GestionUsuarioDAO;
import utils.Validaciones;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FrmRegistro extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JDateChooser txtFecha;

	// Instanciar un objeto de la clase "GestionUsuarioDAO"
	GestionUsuarioDAO gUser = new GestionUsuarioDAO();
	GestionTipoUsuarioDAO gTipUser = new GestionTipoUsuarioDAO();
	
	private JButton btnEliminar;
	private JTextField txtCodigo;
	private JButton btnActualizar;
	private JButton btnBuscarCodigo;
	private JTable tbUsuarios;
	private JScrollPane scrollPane;
	private JButton btnNuevo;
	
	//Instanciar un objeto para la estructura de la tabla 
	DefaultTableModel model = new DefaultTableModel();
	private JLabel lblNewLabel;
	private JComboBox cboTipoUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistro frame = new FrmRegistro();
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
	public FrmRegistro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 430);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblRegistroDeUsuario = new JLabel("Registro de Usuario");
		lblRegistroDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeUsuario.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblRegistroDeUsuario.setForeground(SystemColor.textHighlightText);
		lblRegistroDeUsuario.setOpaque(true);
		lblRegistroDeUsuario.setBackground(SystemColor.desktop);
		lblRegistroDeUsuario.setBounds(0, 0, 764, 33);
		contentPane.add(lblRegistroDeUsuario);

		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(565, 59, 46, 14);
		contentPane.add(lblCdigo);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(565, 90, 68, 14);
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(565, 115, 68, 14);
		contentPane.add(lblApellido);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(565, 140, 68, 14);
		contentPane.add(lblUsuario);

		JLabel lblClave = new JLabel("Clave:");
		lblClave.setBounds(565, 170, 57, 14);
		contentPane.add(lblClave);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(565, 200, 68, 14);
		contentPane.add(lblFecha);

		txtNombre = new JTextField();
		txtNombre.setBounds(624, 88, 130, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(624, 112, 130, 20);
		contentPane.add(txtApellido);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(624, 140, 68, 20);
		contentPane.add(txtUsuario);

		txtClave = new JPasswordField();
		txtClave.setBounds(624, 167, 71, 20);
		contentPane.add(txtClave);

		JButton btnRegistrar = new JButton("");
		btnRegistrar.setIcon(new ImageIcon(FrmRegistro.class.getResource("/img/abrir.png")));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrarDatos();
			}
		});
		btnRegistrar.setBounds(603, 339, 46, 41);
		contentPane.add(btnRegistrar);

		txtFecha = new JDateChooser();
		txtFecha.setDateFormatString("dd-MM-yyyy");
		txtFecha.setBounds(624, 194, 95, 20);
		contentPane.add(txtFecha);

		btnEliminar = new JButton("");
		btnEliminar.setIcon(
				new ImageIcon(FrmRegistro.class.getResource("/img/9004852_trash_delete_bin_remove_icon (2).png")));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(718, 339, 46, 41);
		contentPane.add(btnEliminar);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(621, 56, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		btnActualizar = new JButton("");
		btnActualizar.setIcon(new ImageIcon(FrmRegistro.class
				.getResource("/img/6071808_folder_progress_task_update_update task progress_icon.png")));
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(662, 339, 46, 41);
		contentPane.add(btnActualizar);

		btnBuscarCodigo = new JButton("");
		btnBuscarCodigo.addActionListener(this);
		btnBuscarCodigo.setIcon(new ImageIcon(FrmRegistro.class.getResource("/img/busca.png")));
		btnBuscarCodigo.setBounds(717, 44, 40, 41);
		contentPane.add(btnBuscarCodigo);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 516, 336);
		contentPane.add(scrollPane);

		tbUsuarios = new JTable();
		tbUsuarios.addMouseListener(this);
		scrollPane.setViewportView(tbUsuarios);
		tbUsuarios.setFillsViewportHeight(true);

		btnNuevo = new JButton("");
		btnNuevo.addActionListener(this);
		btnNuevo.setIcon(new ImageIcon(FrmRegistro.class.getResource("/img/nuevo.png")));
		btnNuevo.setBounds(542, 339, 46, 41);
		contentPane.add(btnNuevo);
		//crear las columnas de la tabla
		model.addColumn("Codigo");
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("Usuario");
		model.addColumn("Clave");
		model.addColumn("Fecha");
		
		//asociar objeto "model" a la tb usuario
		tbUsuarios.setModel(model);
		
		lblNewLabel = new JLabel("Tipo  : ");
		lblNewLabel.setBounds(565, 225, 40, 14);
		contentPane.add(lblNewLabel);
		
		cboTipoUsuario = new JComboBox();
		cboTipoUsuario.setBounds(624, 221, 130, 22);
		contentPane.add(cboTipoUsuario);
		//MOSTRAR DATOS EN LA TABLA
		cargarDataUsuario();
		//mostrar datos en las vajas de texto
		mostrarData(0);
		//cargar data al cbo
		cargarDataCbo();
	}

	@SuppressWarnings("unchecked")
	private void cargarDataCbo() {
		//1. llamar al proceso
		ArrayList<TipoUsuario> lisTipUser = gTipUser.listarTipoUsuario();
		// --> lista 
		//--> null
		//2. validar el resultado del proceso
		   if(lisTipUser.size() == 0) {
			   mensajeError("Lista vacía");
		   }else {
			   cboTipoUsuario.addItem("Seleccione ...");
			   for (TipoUsuario tipUser : lisTipUser) {
				   cboTipoUsuario.addItem(tipUser.getIdTipo()+" - " +tipUser.getDescripTipo());
				
			}
		   }
		
		
	}

	void registrarDatos() {
		// variables
		String nomb, ape, user, clave, fecha;
		// entradas
		nomb = getNombre();
		ape = getApellidos();
		user = getUsuario();
		clave = getClave();
		fecha = getFecha();

		// validar
		if (nomb == null || ape == null || user == null || clave == null || fecha == null) {
			return;
		} else {
			// procesos
			// crear un objeto de tipo "Usuario"
			Usuario u = new Usuario();
			// setear --> asignar los nuevos valores ingresados por la GUI a los atributos
			// privados
			u.setNombre(nomb);
			u.setApellido(ape);
			u.setUsuario(user);
			u.setClave(clave);
			u.setFechNacimiento(fecha);

			// Llamar al proceso -- > método registrar que se encuentra en la clase
			// "GestionUsuarioDAO"
			int ok = gUser.registrar(u);
			// validar el resultado del proceso de registro
			if (ok == 0) {
				mensajeError("Error en el registro");
			} else {
				mensajeExitoso("Registro Exitoso");
				cargarDataUsuario();
			}
		}
	}

	private void mensajeExitoso(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Sistema !", 1);

	}

	private String getFecha() {
		// COMPLETAR LA VALIDACIÓN
		String fecha = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		fecha = sdf.format(txtFecha.getDate());
		return fecha;
	}

	private String getClave() {
		String clave = null;
		// aplicar código para encriptar la clave

		clave = encriptado(String.valueOf(txtClave.getPassword()));
		return clave;
	}

	private String encriptado(String pass) {
		// 01 Crear un objeto
		StringBuilder encriptado = new StringBuilder();
		// 02 asignarle el texto a la variable encriptado
		encriptado.append(pass);
		// 03 reemplazar //a->e, e->i, i->o, o->u, u->a
		for (int i = 0; i < encriptado.length(); i++) {

			switch (encriptado.charAt(i)) {
			case 'a':
				encriptado.setCharAt(i, 'e');
				break;
			case 'e':
				encriptado.setCharAt(i, 'i');
				break;
			case 'i':
				encriptado.setCharAt(i, 'o');
				break;
			case 'o':
				encriptado.setCharAt(i, 'u');
				break;
			case 'u':
				encriptado.setCharAt(i, 'a');
				break;
			}

		}
		return encriptado.reverse().toString();
	}

	private String getUsuario() {
		String user = null;
		if (txtUsuario.getText().trim().length() == 0) {
			mensajeError("Ingresar el usuario");
			txtUsuario.setText("");
			txtUsuario.requestFocus();
		} else if (txtUsuario.getText().trim().matches(Validaciones.USUARIO)) {
			user = txtUsuario.getText().trim();
		} else {
			mensajeError("Error en el formato.Ej U001 ó u001");
			txtUsuario.setText("");
			txtUsuario.requestFocus();
		}
		return user;
	}

	private String getApellidos() {
		String ape = null;
		if (txtApellido.getText().trim().length() == 0) {
			mensajeError("Ingresar el apellido");
			txtApellido.setText("");
			txtApellido.requestFocus();
		} else if (txtApellido.getText().trim().matches(Validaciones.TEXTO)) {
			ape = txtApellido.getText().trim();
		} else {
			mensajeError("El apellido ingresado no cumple con el formato");
			txtApellido.setText("");
			txtApellido.requestFocus();
		}
		return ape;
	}

	private String getNombre() {
		String nomb = null;
		if (txtNombre.getText().trim().length() == 0) {
			mensajeError("Ingresar el nombre");
			txtNombre.setText("");
			txtNombre.requestFocus();
		} else if (txtNombre.getText().trim().matches(Validaciones.TEXTO)) {
			nomb = txtNombre.getText().trim();
		} else {
			mensajeError("El nombre ingresado no cumple con el formato");
			txtNombre.setText("");
			txtNombre.requestFocus();
		}
		return nomb;
	}

	private void mensajeError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error", 0);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscarCodigo) {
			actionPerformedBtnBuscarCodigo(e);
		}
		if (e.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(e);
		}
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		eliminarUsuario();
	}

	private void eliminarUsuario() {
		// Declarar variable
		int codigo, opcion;
		// obtener el código
		codigo = getCodigo();
		// validar
		if (codigo == -1) {
			return;
		} else {
			// ventana de confirmación
			opcion = JOptionPane.showConfirmDialog(this, "Seguro de eliminar", "Sistema", JOptionPane.YES_NO_OPTION);
			if (opcion == 0) { // Si se va eliminar
				// LLamar al proceso de eliminar
				int ok = gUser.eliminar(codigo);
				// validar el resultado del proceso
				if (ok == 0) {
					mensajeError("Código no existe");
				} else {
					mensajeExitoso("Usuario eliminado");
					cargarDataUsuario();
				}
			}
		}

	}

	private int getCodigo() {
		int cod = -1;
		if (txtCodigo.getText().trim().length() == 0) {
			mensajeError("Ingresar el código del Usuario");
			txtCodigo.setText("");
			txtCodigo.requestFocus();
		} else {
			try {
				cod = Integer.parseInt(txtCodigo.getText());
			} catch (NumberFormatException e) {
				mensajeError("Error en el formato del código");
				txtCodigo.setText("");
				txtCodigo.requestFocus();
			}
		}
		return cod;
	}

	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualizarUsuario();
	}

	private void actualizarUsuario() {
		// Declarar las variables
		int codigo;
		String nomb, ape, user, clave, fechNac;
		// paso 1: Obtener los datos de la GUI -- entrada de datos
		codigo = getCodigo();
		nomb = getNombre();
		ape = getApellidos();
		user = getUsuario();
		clave = getClave();
		fechNac = getFecha();

		// validar
		if (codigo == -1 || nomb == null || ape == null || user == null || clave == null || fechNac == null) {
			return;
		} else {
			// Crear un objeto de tipo "Usuario"
			Usuario u = new Usuario();
			// setear los atributos(asignar los nuevos valores a los atributos privados)
			u.setNombre(nomb);
			u.setApellido(ape);
			u.setUsuario(user);
			u.setClave(clave);
			u.setFechNacimiento(fechNac);
			u.setCodigo(codigo);
			// Llamar al proceso de "actualizar"
			int ok = gUser.actualizar(u);
			// validar el resultado del proceso de actualizar
			if (ok == 0) {
				mensajeError("Error en la actualización");
			} else {
				mensajeExitoso("Usuario actualizado");
				cargarDataUsuario();
			}
		}

	}

	protected void actionPerformedBtnNuevo(ActionEvent e) {
		nuevoUsuario();
	}

	private void nuevoUsuario() {
		txtCodigo.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtUsuario.setText("");
		txtClave.setText("");
		txtFecha.setDate(null);

	}

	protected void actionPerformedBtnBuscarCodigo(ActionEvent e) {
		buscarDatosUsuario();
	}

	private void buscarDatosUsuario() {
		int codigo;
		// paso 1 --> Obtener el código ingresado
		codigo = getCodigo();
		// paso 2 --> validar que el codigo ingresado responda al formato
		if (codigo == -1) {
			return;
		} else {
			// llamar al proceso de búsqueda
			Usuario user = gUser.buscarUsuario(codigo);
			// validar el resultado del proceso
			if (user == null) {
				mensajeError("Código no exite");
			} else {
				txtNombre.setText(user.getNombre());
				txtApellido.setText(user.getApellido());
				txtUsuario.setText(user.getUsuario());
				txtClave.setText(user.getClave());
				//fecha
				try {
					txtFecha.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(user.getFechNacimiento()));
					
				} catch (ParseException e) {
					mensajeError("Error al cargar la fecha"+e.getMessage());
				}

			}

		}

	}
	
	//MÉTODO PARA CARGAR LOS DATOS A LA TABLA
	private void cargarDataUsuario() {
		//1.Limpiar la tabla 
		model.setRowCount(0);
		//2.llamar al proceso de consulta
		ArrayList<Usuario> lista = gUser.listarUsuarios();
		//crear un bucle para el recorrido 
		for (Usuario u : lista) {
			//Crear un arreglo
			Object fila[] = { u.getCodigo(),
					          u.getNombre(),
					          u.getApellido(),
					          u.getUsuario(),
					          u.getClave(),
					          u.getFechNacimiento()		
			                    };
			//Añadir la fila a la tabla
			model.addRow(fila);
		}
	}
	
	private void mostrarData(int posFila) {
		//declarar variables
		String cod,nomb,ape,user,clav,fech;
		//paso 1 : Obtener los datos de la tabla
		cod = tbUsuarios.getValueAt(posFila, 0).toString();
		nomb = tbUsuarios.getValueAt(posFila, 1).toString();
		ape = tbUsuarios.getValueAt(posFila, 2).toString();
		user = tbUsuarios.getValueAt(posFila, 3).toString();
		clav = tbUsuarios.getValueAt(posFila, 4).toString();
		fech = tbUsuarios.getValueAt(posFila, 5).toString();
		//paso 2: Enviar los datos de la tabla a las cajas de texto
		txtCodigo.setText(cod);
		txtNombre.setText(nomb);
		txtApellido.setText(ape);
		txtUsuario.setText(user);
		txtClave.setText(clav);
		try {
			txtFecha.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(fech));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tbUsuarios) {
			mouseClickedTbUsuarios(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedTbUsuarios(MouseEvent e) {
		//Obtener el valor de la fila seleccionada
		int posFila = tbUsuarios.getSelectedRow();
		//MOSTRAR DATA
		mostrarData(posFila);
		
	}
}
