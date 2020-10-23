package reto;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class VentanaSeleccionArchivo extends JFrame {

	private JPanel contentPane;
	JTextField textField;
	private JButton btnSeleccionar;
	JTextArea textArea;
	private JButton btnEscribir;
	private JTextField textOculto;

	public void iniciarVentana() {
		setVisible(true);

	}

	public VentanaSeleccionArchivo() {
		Errores log = new Errores();
		Lector leer = new Lector();
		Escritor escribir = new Escritor();
		log.CargarLogger();

		setResizable(false);
		setTitle("BIBLIOTECA");
		log.CargarLogger();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 570);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		textField = new JTextField();
		textField.setToolTipText(
				"Inserta la ruta del fichero, si quieres escribir un texto que se muestre por consola escriba 'estandar' en la ruta");
		textField.setBounds(45, 26, 437, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		btnSeleccionar = new JButton("Abrir");
		btnSeleccionar.setBounds(493, 25, 119, 23);
		contentPane.add(btnSeleccionar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(45, 68, 567, 417);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);

		btnEscribir = new JButton("Guardar");
		btnEscribir.setBounds(493, 496, 119, 23);
		contentPane.add(btnEscribir);

		JLabel TipoArchivo = new JLabel("");
		TipoArchivo.setForeground(Color.RED);
		TipoArchivo.setBounds(45, 496, 295, 23);
		contentPane.add(TipoArchivo);

		JLabel Guardado = new JLabel("Guardado Correctamente");
		Guardado.setForeground(Color.BLUE);
		Guardado.setFont(new Font("Arial", Font.PLAIN, 11));
		Guardado.setBounds(361, 496, 131, 23);
		Guardado.setVisible(false);
		contentPane.add(Guardado);

		textOculto = new JTextField();
		textOculto.setToolTipText(
				"Inserta la ruta del fichero, si quieres escribir un texto que se muestre por consola escriba 'estandar' en la ruta");
		textOculto.setColumns(10);
		textOculto.setBounds(329, 37, 131, 4);
		textOculto.setVisible(false);
		contentPane.add(textOculto);

		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rutaentera = null;
				String nombre;
				JFileChooser jf = new JFileChooser();
				jf.showOpenDialog(null);
				File archivo = jf.getSelectedFile();
				if (archivo != null) {
					rutaentera = archivo.getAbsolutePath();
					nombre = leer.modificarRuta(rutaentera);
					textField.setText(nombre);
					textOculto.setText(rutaentera);
					textArea.setText(leer.leer(archivo.getAbsolutePath()));
					TipoArchivo.setText("Estas en " + nombre);
				}
			}
		});
		btnEscribir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escribir.escribir(textOculto.getText(), textArea.getText());
				Guardado.setVisible(true);
			}
		});
	}
}
