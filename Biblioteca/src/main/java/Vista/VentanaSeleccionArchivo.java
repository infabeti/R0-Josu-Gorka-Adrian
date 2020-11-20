package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import reto.ConexionHTML;
import reto.Errores;
import reto.Escritor;
import reto.ExploradorArchivos;
import reto.Lector;
import reto.ModificarRuta;
import reto.ValidarTexto;

public class VentanaSeleccionArchivo extends JFrame {

	private JPanel contentPane;
	JTextField textField;
	private JButton btnSeleccionar;
	JTextArea textArea;
	private JButton btnEscribir;
	private JButton btnGuardarComo;
	JButton btnVHtml;
	String rutaentera;

	public void iniciarVentana() {
		setVisible(true);

	}

	public VentanaSeleccionArchivo() {
		Errores log = new Errores();
		Lector leer = new Lector();
		Escritor escribir = new Escritor();
		ModificarRuta modificarRuta = new ModificarRuta();
		log.CargarLogger();

		setResizable(false);
		setTitle("BIBLIOTECA");
		log.CargarLogger();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 650);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		textField = new JTextField();
		textField.setToolTipText(
				"Inserta la ruta del fichero, si quieres escribir un texto que se muestre por consola escriba 'estandar' en la ruta");
		textField.setBounds(45, 26, 475, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		btnSeleccionar = new JButton("Abrir");
		btnSeleccionar.setBounds(525, 25, 119, 23);
		contentPane.add(btnSeleccionar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(45, 68, 600, 450);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);

		btnEscribir = new JButton("Guardar");
		btnEscribir.setBounds(525, 525, 119, 23);
		contentPane.add(btnEscribir);

		btnGuardarComo = new JButton("Guardar como");
		btnGuardarComo.setBounds(525, 565, 119, 23);
		contentPane.add(btnGuardarComo);

		JLabel TipoArchivo = new JLabel("");
		TipoArchivo.setForeground(Color.RED);
		TipoArchivo.setBounds(45, 525, 295, 23);
		contentPane.add(TipoArchivo);

		JLabel guardado = new JLabel("Guardado Correctamente");
		guardado.setForeground(Color.BLUE);
		guardado.setFont(new Font("Arial", Font.PLAIN, 11));
		guardado.setBounds(390, 525, 131, 23);
		guardado.setVisible(false);
		contentPane.add(guardado);

		btnVHtml = new JButton("Vista Previa");
		btnVHtml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConexionHTML html = new ConexionHTML();
				html.conexionhtml(rutaentera);
			}
		});
		btnVHtml.setVisible(false);
		btnVHtml.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVHtml.setBackground(new Color(255, 182, 193));
		btnVHtml.setBounds(270, 515, 105, 23);
		contentPane.add(btnVHtml);

		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVHtml.setVisible(false);
				ExploradorArchivos explorador = new ExploradorArchivos();
				rutaentera = explorador.seleccionarArchivo();

				if (rutaentera != null) {
					String nombre = modificarRuta.acortarRuta(rutaentera);
					textField.setText(nombre);
					textArea.setText(leer.comprobarExtension(rutaentera));
					TipoArchivo.setText("Estas en " + nombre);
				}
			}
		});

		btnEscribir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValidarTexto validar = new ValidarTexto(rutaentera, textArea.getText());
				if (!validar.aplicarExpresion()) {
					guardado.setText("Guardado Correctamente");
					guardado.setForeground(Color.BLUE);
					escribir.escribirArchivo(rutaentera, textArea.getText());
					guardado.setVisible(true);
				} else {
					guardado.setText("Error al validar el texto");
					guardado.setForeground(Color.RED);
					guardado.setVisible(true);
				}

				if (rutaentera.endsWith(".html")) {
					btnVHtml.setVisible(true);
				}
			}
		});

		btnGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExploradorArchivos explorador = new ExploradorArchivos();
				ValidarTexto validar = new ValidarTexto(rutaentera, textArea.getText());
				if (!validar.aplicarExpresion()) {
					guardado.setText("Guardado Correctamente");
					guardado.setForeground(Color.BLUE);
					escribir.escribirArchivo(rutaentera, textArea.getText());
					guardado.setVisible(true);
					explorador.guardarArchivo(rutaentera);
				} else {
					guardado.setText("Error al validar el texto");
					guardado.setForeground(Color.RED);
					guardado.setVisible(true);
				}

				if (rutaentera.endsWith(".html")) {
					btnVHtml.setVisible(true);
				}
			}
		});
	}
}
