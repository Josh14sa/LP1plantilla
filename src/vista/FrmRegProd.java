package vista;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entidad.Producto;
import entidad.Usuario;
import mantenimiento.GestionProductoDAO;
import utils.Validaciones;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class FrmRegProd extends JInternalFrame implements MouseListener, KeyListener, ActionListener {
	private JTextField txtCodigo;
	private JTextField txtProducto;
	private JTextField txtCantidad;
	private JTextField txtPrecio;
	private JComboBox cboTipo;
	private JTable tbProductos;
	private JScrollPane scrollPane;

	// instanciar un objeto para modelar la tabla
	DefaultTableModel model = new DefaultTableModel();
	//
	GestionProductoDAO gProd = new GestionProductoDAO();
	private JLabel lblError;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JLabel lblEstado;
	private JTextField txtEstado;
	private JButton btnBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegProd frame = new FrmRegProd();
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
	public FrmRegProd() {
		getContentPane().setBackground(SystemColor.info);
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 704, 445);
		getContentPane().setLayout(null);

		JLabel label = new JLabel("C\u00F3digo:");
		label.setBounds(460, 64, 75, 14);
		getContentPane().add(label);

		txtCodigo = new JTextField();
		txtCodigo.setText("");
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(521, 61, 75, 20);
		getContentPane().add(txtCodigo);

		JLabel label_1 = new JLabel("Producto:");
		label_1.setBounds(460, 92, 75, 14);
		getContentPane().add(label_1);

		txtProducto = new JTextField();
		txtProducto.setText("");
		txtProducto.setColumns(10);
		txtProducto.setBounds(521, 89, 145, 20);
		getContentPane().add(txtProducto);

		JLabel label_2 = new JLabel("Tipo:");
		label_2.setBounds(460, 170, 53, 14);
		getContentPane().add(label_2);

		cboTipo = new JComboBox();
		cboTipo.setBounds(521, 167, 126, 20);
		cboTipo.setModel(new DefaultComboBoxModel(new String[] { "Seleccione tipo", "Pastillas", "Jarabe", "Otros" }));
		getContentPane().add(cboTipo);

		JLabel lblStock = new JLabel("Stock :");
		lblStock.setBounds(460, 120, 60, 14);
		getContentPane().add(lblStock);

		txtCantidad = new JTextField();
		txtCantidad.setText("");
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(521, 117, 53, 20);
		getContentPane().add(txtCantidad);

		JLabel label_4 = new JLabel("Precio:");
		label_4.setBounds(460, 145, 46, 14);
		getContentPane().add(label_4);

		txtPrecio = new JTextField();
		txtPrecio.addKeyListener(this);
		txtPrecio.setText("");
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(521, 142, 60, 20);
		getContentPane().add(txtPrecio);

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNuevo.setBounds(10, 370, 116, 34);
		getContentPane().add(btnNuevo);

		JButton btnGuardar = new JButton("Registrar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ingresar();
			}
		});
		btnGuardar.setBounds(203, 370, 116, 34);
		getContentPane().add(btnGuardar);

		JLabel lblMantenimientoDeProductos = new JLabel("Mantenimiento de Productos");
		lblMantenimientoDeProductos.setForeground(SystemColor.text);
		lblMantenimientoDeProductos.setBackground(SystemColor.windowText);
		lblMantenimientoDeProductos.setOpaque(true);
		lblMantenimientoDeProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoDeProductos.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lblMantenimientoDeProductos.setBounds(0, 13, 688, 28);
		getContentPane().add(lblMantenimientoDeProductos);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 431, 290);
		getContentPane().add(scrollPane);

		tbProductos = new JTable();
		tbProductos.addKeyListener(this);
		tbProductos.addMouseListener(this);
		scrollPane.setViewportView(tbProductos);
		tbProductos.setFillsViewportHeight(true);

		// agregar columna
		model.addColumn("Codigo");
		model.addColumn("Producto");
		model.addColumn("Tipo");
		model.addColumn("Cantidad");
		model.addColumn("Precio");

		// asociar
		tbProductos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));

		lblError = new JLabel("*");
		lblError.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblError.setForeground(Color.RED);
		lblError.setBounds(285, 121, 46, 14);
		getContentPane().add(lblError);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(381, 370, 125, 34);
		getContentPane().add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(542, 370, 105, 34);
		getContentPane().add(btnEliminar);
		
		lblEstado = new JLabel("Estado : ");
		lblEstado.setBounds(460, 196, 60, 14);
		getContentPane().add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setBounds(521, 193, 126, 20);
		getContentPane().add(txtEstado);
		txtEstado.setColumns(10);
		
		btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscar.setIcon(new ImageIcon(FrmRegProd.class.getResource("/img/busca.png")));
		btnBuscar.setBounds(606, 44, 60, 34);
		getContentPane().add(btnBuscar);

		lblError.setVisible(false);
		// mostrar data
		// mostrarData(0);
		//cargar datos desde la BD a la tabla
		cargarDataProductos();

	}

	void ingresar() {
		String cod, prod;
		int cant;
		double pre;
		int tipo,estado;

		cod = leerCodigo();
		prod = leerProducto();
		cant = leerCantidad();
		pre = leerPrecio();
		tipo = leerTipo();
		estado = leerEstado();
		// validar
		if (cant == -1 || pre == -1 || tipo == 0 || cod == null || prod == null || estado == -1) {
			return;

		} else {

			// instanciar un objeto de tipo "Producto"
			Producto producto = new Producto();
			//setear 
			producto.setIdProd(cod);
			producto.setDescripProducto(prod);
			producto.setStock(cant);
			producto.setPrecio(pre);
			producto.setIdTipo(tipo);
			producto.setEstado(estado);
			
			//llamar al proceso de registro 
			int ok = gProd.registrar(producto);
			
			//validar el resultado del proceso
			
			if(ok == 0) {
				mensajeError("Error al registrar el producto");
			}else {
				mensajeExitoso("Producto registrado");
			}
		}

	}
	private void mensajeExitoso(String msj) {
		JOptionPane.showMessageDialog(this, msj,"Sistema",1);
		
	}

	private int leerEstado() {
		int estado = -1; //completar validación 
		estado = Integer.parseInt(txtEstado.getText());
		return estado;
	}

	private String leerCodigo() {
		// Formato --> P0001 Ó p0001
		String cod = null;
		// caja de texto vacío
		if (txtCodigo.getText().trim().length() == 0) {
			mensajeError("Ingresar el código del producto");
			txtCodigo.setText("");
			txtCodigo.requestFocus();
			// Formato --> P0001 Ó p0001
		} else if (txtCodigo.getText().trim().matches("[pP][0-9]{4}")) {
			cod = txtCodigo.getText();
		} else {
			mensajeError("Formato no válido!! Ej. P0001 ó p0001");
			txtCodigo.setText("");
			txtCodigo.requestFocus();
		}
		return cod;
	}

	String leerProducto() {
		String prod = null;

		// campo vacío
		if (txtProducto.getText().trim().length() == 0) {
			mensajeError("Ingresa nombre de Producto");
			txtProducto.setText("");
			txtProducto.requestFocus();

			// ingresar de 3 - 20 carcateres // paracetamol forte
		} else if (txtProducto.getText().trim().matches(Validaciones.TEXTO)) {
			prod = txtProducto.getText().trim();

		} else {
			mensajeError("Formato incorrecto");
			txtProducto.setText("");
			txtProducto.requestFocus();

		}
		return prod;

	}

	int leerTipo() {
		int tipo;
		tipo = cboTipo.getSelectedIndex();
		if (tipo == 0) {
			mensajeError("Seleccionar el tipo de producto");
		}
		return tipo;
	}

	double leerPrecio() {
		double pre = -1;
		// validar en caso no se ingrese valores
		if (txtPrecio.getText().trim().length() == 0) {
			mensajeError("Ingresar el precio del producto");
			txtPrecio.setText("");
			txtPrecio.requestFocus();
		} else {
			try {// validar que ingresen número enteros
				pre = Double.parseDouble(txtPrecio.getText());
				// valores no sean menores a 0 o igual
				if (pre <= 0) {
					mensajeError("Ingresa precios mayores a 0");
					txtPrecio.setText("");
					txtPrecio.requestFocus();
					pre = -1;
				}
			} catch (NumberFormatException e) {
				mensajeError("Ingresar valores numéricos");
				txtPrecio.setText("");
				txtPrecio.requestFocus();
				lblError.setVisible(true);//
				txtPrecio.setBackground(Color.RED);
				pre = -1;
			}
		}
		return pre;
	}

	int leerCantidad() {
		int cant = -1;
		// validar en caso no se ingrese valores
		if (txtCantidad.getText().trim().length() == 0) {
			mensajeError("Ingresar la cantidad");
			txtCantidad.setText("");
			txtCantidad.requestFocus();
		} else {
			try {// validar que ingresen número enteros
				cant = Integer.parseInt(txtCantidad.getText());
				// valores no sean menores a 0 o igual
				if (cant <= 0) {
					mensajeError("Ingresa valores mayores a 0");
					txtCantidad.setText("");
					txtCantidad.requestFocus();
					cant = -1;
				}
			} catch (NumberFormatException e) {
				mensajeError("Ingresar valores numéricos enteros");
				txtCantidad.setText("");
				txtCantidad.requestFocus();
				cant = -1;
			}
		}

		return cant;
	}

	private void mensajeError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error", 0);

	}

	// mostrar los datos de la tabla en las cajas de texto
	private void mostrarData(int posFila) {
		// paso 1 : Obtener los valores de la tabla
		String cod, prod, pre, cant, tipo;
		cod = tbProductos.getValueAt(posFila, 0).toString();
		prod = tbProductos.getValueAt(posFila, 1).toString();
		tipo = tbProductos.getValueAt(posFila, 2).toString();
		cant = tbProductos.getValueAt(posFila, 3).toString();
		pre = tbProductos.getValueAt(posFila, 4).toString();

		// paso 2: mostrar la información obtenida de la tabla a las cajas de texto
		txtCodigo.setText(cod);
		txtProducto.setText(prod);
		txtCantidad.setText(cant);
		txtPrecio.setText(pre);
		cboTipo.setSelectedItem(tipo);

	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tbProductos) {
			mouseClickedTbProductos(e);
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

	protected void mouseClickedTbProductos(MouseEvent e) {

		// obtener el valor de la fila seleccionada
		int posFila;
		posFila = tbProductos.getSelectedRow();
		// mostrar data
		mostrarData(posFila);
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtPrecio) {
			keyReleasedTxtPrecio(e);
		}
		if (e.getSource() == tbProductos) {
			keyReleasedTbProductos(e);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	protected void keyReleasedTbProductos(KeyEvent e) {
		// obtener el valor de la fila seleccionada
		int posFila;
		posFila = tbProductos.getSelectedRow();
		// mostrar data
		mostrarData(posFila);
	}

	protected void keyReleasedTxtPrecio(KeyEvent e) {
		txtPrecio.setBackground(Color.WHITE);
		lblError.setVisible(false);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualizarProducto();
	}

	private void actualizarProducto() {
		String cod, prod;
		int cant;
		double pre;
		int tipo,estado;

		cod = leerCodigo();
		prod = leerProducto();
		cant = leerCantidad();
		pre = leerPrecio();
		tipo = leerTipo();
		estado = leerEstado();
		// validar
		if (cant == -1 || pre == -1 || tipo == 0 || cod == null || prod == null || estado == -1) {
			return;

		} else {

			// instanciar un objeto de tipo "Producto"
			Producto producto = new Producto();
			//setear 
			
			producto.setDescripProducto(prod);
			producto.setStock(cant);
			producto.setPrecio(pre);
			producto.setIdTipo(tipo);
			producto.setEstado(estado);
			producto.setIdProd(cod);
			
			//llamar al proceso de actualizar 
			int ok = gProd.actualizar(producto);
			
			//validar el resultado del proceso
			
			if(ok == 0) {
				mensajeError("Error al actualizar el producto");
			}else {
				mensajeExitoso("Producto actualizado");
			}
		}
		
	}
	  //MÉTODO PARA CARGAR LOS DATOS A LA TABLA
		private void cargarDataProductos() {
			//1.Limpiar la tabla 
			model.setRowCount(0);
			//2.llamar al proceso de consulta
			ArrayList<Producto> lista = gProd.listarProductos();
			//validar el resultado de la consulta 
			//crear un bucle para el recorrido 
			for (Producto pro : lista) {
				//Crear un arreglo
				Object fila[] = { pro.getIdProd(),
						          pro.getDescripProducto(),
						          pro.getIdTipo(),
						          pro.getStock(),
						          pro.getPrecio(),
						          		
				                    };
				//Añadir la fila a la tabla
				model.addRow(fila);
			}
		}
}
