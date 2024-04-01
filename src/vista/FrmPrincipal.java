package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.HiloReloj;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class FrmPrincipal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnSistema;
	private JMenuItem mntmSalir;
	private JMenu mnMantenimiento;
	private JMenuItem mntmClientes;
	private JMenuItem mntmProductos;
	private JMenu mnReportes;
	private JMenuItem mntmReporteClientes;
	private JMenuItem mntmVentas;
	private JDesktopPane escritorio;
	private JLabel lblReloj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
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
	public FrmPrincipal() {
		try {
			 UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 733, 457);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnSistema = new JMenu("Sistema");
		mnSistema.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/3069182_device_business_computer_technology_office_icon.png")));
		mnSistema.setMnemonic('S');
		menuBar.add(mnSistema);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/1312510_circle_off_on_style_turn_icon (2).png")));
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, InputEvent.ALT_DOWN_MASK));
		mntmSalir.addActionListener(this);
		mnSistema.add(mntmSalir);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		mnMantenimiento.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/8059800_mantenance_data management_data processing_setting_configuration_icon.png")));
		mnMantenimiento.setMnemonic('M');
		menuBar.add(mnMantenimiento);
		
		mntmClientes = new JMenuItem("Clientes");
		mntmClientes.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/1564534_customer_man_user_account_profile_icon.png")));
		mnMantenimiento.add(mntmClientes);
		
		mntmProductos = new JMenuItem("Productos");
		mntmProductos.addActionListener(this);
		mnMantenimiento.add(mntmProductos);
		
		mnReportes = new JMenu("Reportes");
		mnReportes.setMnemonic('R');
		mnReportes.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/img/2620507_employee_job_print_seeker_unemployee_icon.png")));
		menuBar.add(mnReportes);
		
		mntmReporteClientes = new JMenuItem("Clientes");
		mnReportes.add(mntmReporteClientes);
		
		mntmVentas = new JMenuItem("Ventas");
		mntmVentas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
		mntmVentas.addActionListener(this);
		mnReportes.add(mntmVentas);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		escritorio = new JDesktopPane();
		escritorio.setBackground(SystemColor.activeCaption);
		contentPane.add(escritorio, BorderLayout.CENTER);
		
		lblReloj = new JLabel("hh:mm:ss");
		lblReloj.setForeground(Color.WHITE);
		lblReloj.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblReloj.setBounds(563, 43, 99, 27);
		escritorio.add(lblReloj);
		//mostrar hora
		cargarHora();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmVentas) {
			actionPerformedMntmVentas(e);
		}
		if (e.getSource() == mntmProductos) {
			actionPerformedMntmProductos(e);
		}
		if (e.getSource() == mntmSalir) {
			actionPerformedMntmSalir(e);
		}
	}
	protected void actionPerformedMntmSalir(ActionEvent e) {
		//cerrar la aplicación
		System.exit(0);	
		
	}
	protected void actionPerformedMntmProductos(ActionEvent e) {
		FrmRegProd prod = new FrmRegProd();
		prod.setVisible(true);
		//prod.setLocationRelativeTo(this);
		escritorio.add(prod);
	}
	protected void actionPerformedMntmVentas(ActionEvent e) {
		FrmRptVtas vent = new FrmRptVtas();
		vent.setVisible(true);
		escritorio.add(vent);
	}
	private void cargarHora() {
		HiloReloj h = new HiloReloj(lblReloj);
		h.start();
		
	}
}
