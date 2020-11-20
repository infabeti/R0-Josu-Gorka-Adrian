package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import reto.ExploradorArchivos;
import reto.ModificarRuta;
import reto.Permisos;
import reto.Propietario;
import reto.TamanoArchivo;

public class VentanaAdministrativa extends JFrame {

	private JPanel contentPane;
	private JTextField textBuscar;
	private JTextField textTamano;
	private JTextField textTamanoNuevo;
	private JTextField textPropietario;
	private JTextField textPropietarioNuevo;
	private JCheckBox chckbxEdtarTamano;
	private JLabel lblKbnew;
	private JCheckBox chckbxEditarPropietario;
	private JLabel lblNuevoPropietario;
	private JCheckBox chckbxEditarPermisos;
	private JCheckBox chckbxLectura;
	private JCheckBox chckbxEscritura;
	private JCheckBox chckbxEjecucion;
	private String rutaentera;
	private Permisos SeleccionPermisos;
	private TamanoArchivo Tamaño;
	private Propietario propieada;

	public void iniciarVentana() {
		setVisible(true);

	}

	public VentanaAdministrativa() {
		setTitle("Administracion de Archivos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 889, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Seleccione el Archivo que quieras editar");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(23, 25, 362, 19);
		contentPane.add(lblNewLabel);

		textBuscar = new JTextField();
		textBuscar.setBackground(Color.WHITE);
		textBuscar.setEditable(false);
		textBuscar.setBounds(23, 53, 302, 20);
		contentPane.add(textBuscar);
		textBuscar.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModificarRuta modificarRuta = new ModificarRuta();
				ExploradorArchivos explorador = new ExploradorArchivos();
				rutaentera = explorador.seleccionarArchivo();
				if (rutaentera != null) {
					String nombre = modificarRuta.acortarRuta(rutaentera);
					textBuscar.setText(nombre);
					SeleccionPermisos = new Permisos(chckbxLectura, chckbxEscritura, chckbxEjecucion);
					SeleccionPermisos.BuscarPermisos(rutaentera);
					Tamaño = new TamanoArchivo(textTamano);
					Tamaño.BuscarTamaño(rutaentera);
					propieada = new Propietario(textPropietario);
					propieada.BuscarPropietario(rutaentera);
				}
			}
		});
		btnBuscar.setBounds(335, 52, 89, 23);
		contentPane.add(btnBuscar);

		textTamano = new JTextField();
		textTamano.setBackground(Color.WHITE);
		textTamano.setEditable(false);
		textTamano.setColumns(10);
		textTamano.setBounds(190, 99, 104, 20);
		contentPane.add(textTamano);

		JLabel lblTamaoDelFichero = new JLabel("Tama\u00F1o Del Fichero:");
		lblTamaoDelFichero.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTamaoDelFichero.setBounds(10, 98, 170, 19);
		contentPane.add(lblTamaoDelFichero);

		JLabel lblByte = new JLabel("bytes");
		lblByte.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblByte.setBounds(304, 98, 66, 19);
		contentPane.add(lblByte);

		chckbxEdtarTamano = new JCheckBox("Editar Tama\u00F1o");
		chckbxEdtarTamano.setBounds(10, 129, 141, 23);
		chckbxEdtarTamano.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxEdtarTamano.isSelected()) {
					textTamanoNuevo.setVisible(true);
					lblKbnew.setVisible(true);
				} else {
					textTamanoNuevo.setVisible(false);
					lblKbnew.setVisible(false);
				}

			}
		});
		contentPane.add(chckbxEdtarTamano);

		textTamanoNuevo = new JTextField();
		textTamanoNuevo.setVisible(false);
		textTamanoNuevo.setColumns(10);
		textTamanoNuevo.setBounds(190, 130, 104, 20);
		contentPane.add(textTamanoNuevo);

		lblKbnew = new JLabel("bytes");
		lblKbnew.setVisible(false);
		lblKbnew.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKbnew.setBounds(304, 128, 66, 19);
		contentPane.add(lblKbnew);

		JLabel lblPropietarioDelFichero = new JLabel("Propietario Del Fichero:");
		lblPropietarioDelFichero.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPropietarioDelFichero.setBounds(10, 204, 213, 19);
		contentPane.add(lblPropietarioDelFichero);

		chckbxEditarPropietario = new JCheckBox("Editar Propietario");
		chckbxEditarPropietario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxEditarPropietario.isSelected()) {
					textPropietarioNuevo.setVisible(true);
					lblNuevoPropietario.setVisible(true);
				} else {
					textPropietarioNuevo.setVisible(false);
					lblNuevoPropietario.setVisible(false);
				}

			}
		});
		chckbxEditarPropietario.setBounds(10, 240, 150, 23);
		contentPane.add(chckbxEditarPropietario);

		textPropietario = new JTextField();
		textPropietario.setEditable(false);
		textPropietario.setColumns(10);
		textPropietario.setBackground(Color.WHITE);
		textPropietario.setBounds(221, 205, 220, 20);
		contentPane.add(textPropietario);

		textPropietarioNuevo = new JTextField();
		textPropietarioNuevo.setColumns(10);
		textPropietarioNuevo.setVisible(false);
		textPropietarioNuevo.setBackground(Color.WHITE);
		textPropietarioNuevo.setBounds(221, 234, 104, 20);
		contentPane.add(textPropietarioNuevo);

		JLabel lblPropietario = new JLabel("Propietario");
		lblPropietario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPropietario.setBounds(451, 204, 104, 19);
		contentPane.add(lblPropietario);

		lblNuevoPropietario = new JLabel("Nuevo Propietario");
		lblNuevoPropietario.setVisible(false);
		lblNuevoPropietario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNuevoPropietario.setBounds(335, 240, 161, 19);
		contentPane.add(lblNuevoPropietario);

		JLabel lblPermisosDelFichero = new JLabel("Permisos Del Fichero:");
		lblPermisosDelFichero.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPermisosDelFichero.setBounds(588, 70, 181, 19);
		contentPane.add(lblPermisosDelFichero);

		chckbxEditarPermisos = new JCheckBox("Editar Permisos");
		chckbxEditarPermisos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxEditarPermisos.isSelected()) {
					chckbxLectura.setEnabled(true);
					chckbxEscritura.setEnabled(true);
					chckbxEjecucion.setEnabled(true);
				} else {
					chckbxLectura.setEnabled(false);
					chckbxEscritura.setEnabled(false);
					chckbxEjecucion.setEnabled(false);
				}

			}
		});
		chckbxEditarPermisos.setBounds(566, 129, 134, 23);
		contentPane.add(chckbxEditarPermisos);

		chckbxLectura = new JCheckBox("LECTURA");
		chckbxLectura.setEnabled(false);
		chckbxLectura.setForeground(Color.BLACK);
		chckbxLectura.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxLectura.setBounds(702, 98, 129, 23);
		contentPane.add(chckbxLectura);

		chckbxEscritura = new JCheckBox("ESCRITURA");
		chckbxEscritura.setEnabled(false);
		chckbxEscritura.setBounds(702, 129, 129, 23);
		contentPane.add(chckbxEscritura);

		chckbxEjecucion = new JCheckBox("EJECUCION");
		chckbxEjecucion.setEnabled(false);
		chckbxEjecucion.setBounds(702, 169, 129, 23);
		contentPane.add(chckbxEjecucion);

		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SeleccionPermisos = new Permisos(chckbxLectura, chckbxEscritura, chckbxEjecucion);
				SeleccionPermisos.ColocarPermisos(rutaentera);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(668, 219, 150, 23);
		contentPane.add(btnNewButton);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaSeleccion Volver = new VentanaSeleccion();
				Volver.iniciarVentana();
				dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVolver.setBounds(668, 282, 150, 23);
		contentPane.add(btnVolver);

	}
}
