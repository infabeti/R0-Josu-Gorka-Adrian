package Vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaAdministrativa extends JFrame {

	private JPanel contentPane;
	private JTextField textBuscar;
	private JTextField textTamano;
	private JTextField textTamanoNuevo;
	private JTextField textPropietario;
	private JTextField textPropietarioNuevo;
	private JCheckBox chckbxEdtarTamano;

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

		JLabel lblKb = new JLabel("KB");
		lblKb.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKb.setBounds(304, 98, 43, 19);
		contentPane.add(lblKb);

		chckbxEdtarTamano = new JCheckBox("Editar Tama\u00F1o");
		chckbxEdtarTamano.setBounds(10, 129, 141, 23);

		contentPane.add(chckbxEdtarTamano);

		textTamanoNuevo = new JTextField();
		textTamanoNuevo.setVisible(false);
		textTamanoNuevo.setColumns(10);
		textTamanoNuevo.setBounds(190, 130, 104, 20);
		contentPane.add(textTamanoNuevo);

		JLabel lblKbnew = new JLabel("KB");
		lblKbnew.setVisible(false);
		lblKbnew.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKbnew.setBounds(304, 128, 43, 19);
		contentPane.add(lblKbnew);

		JLabel lblPropietarioDelFichero = new JLabel("Propietario Del Fichero:");
		lblPropietarioDelFichero.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPropietarioDelFichero.setBounds(10, 204, 170, 19);
		contentPane.add(lblPropietarioDelFichero);

		JCheckBox chckbxEditarPropietario = new JCheckBox("Editar Propietario");
		chckbxEditarPropietario.setBounds(10, 240, 150, 23);
		contentPane.add(chckbxEditarPropietario);

		textPropietario = new JTextField();
		textPropietario.setEditable(false);
		textPropietario.setColumns(10);
		textPropietario.setBackground(Color.WHITE);
		textPropietario.setBounds(190, 205, 104, 20);
		contentPane.add(textPropietario);

		textPropietarioNuevo = new JTextField();
		textPropietarioNuevo.setColumns(10);
		textPropietarioNuevo.setVisible(false);
		textPropietarioNuevo.setBackground(Color.WHITE);
		textPropietarioNuevo.setBounds(190, 241, 104, 20);
		contentPane.add(textPropietarioNuevo);

		JLabel lblPropietario = new JLabel("Propietario");
		lblPropietario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPropietario.setBounds(304, 208, 104, 19);
		contentPane.add(lblPropietario);

		JLabel lblNuevoPropietario = new JLabel("Nuevo Propietario");
		lblNuevoPropietario.setVisible(false);
		lblNuevoPropietario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNuevoPropietario.setBounds(304, 244, 161, 19);
		contentPane.add(lblNuevoPropietario);

		JLabel lblPermisosDelFichero = new JLabel("Permisos Del Fichero:");
		lblPermisosDelFichero.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPermisosDelFichero.setBounds(588, 70, 181, 19);
		contentPane.add(lblPermisosDelFichero);

		JCheckBox chckbxEditarPermisos = new JCheckBox("Editar Permisos");
		chckbxEditarPermisos.setBounds(566, 129, 111, 23);
		contentPane.add(chckbxEditarPermisos);

		JCheckBox chckbxLectura = new JCheckBox("LECTURA");
		chckbxLectura.setEnabled(false);
		chckbxLectura.setForeground(Color.BLACK);
		chckbxLectura.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxLectura.setBounds(702, 98, 129, 23);
		contentPane.add(chckbxLectura);

		JCheckBox chckbxEditarPropietario_1_1 = new JCheckBox("ESCRITURA");
		chckbxEditarPropietario_1_1.setEnabled(false);
		chckbxEditarPropietario_1_1.setBounds(702, 129, 129, 23);
		contentPane.add(chckbxEditarPropietario_1_1);

		JCheckBox chckbxEditarPropietario_1_1_1 = new JCheckBox("EJECUCION");
		chckbxEditarPropietario_1_1_1.setEnabled(false);
		chckbxEditarPropietario_1_1_1.setBounds(702, 169, 129, 23);
		contentPane.add(chckbxEditarPropietario_1_1_1);

		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(619, 255, 150, 23);
		contentPane.add(btnNewButton);

	}
}
