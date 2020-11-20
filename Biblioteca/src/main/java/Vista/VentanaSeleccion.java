package Vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class VentanaSeleccion extends JFrame {

	private JPanel contentPane;

	public void iniciarVentana() {
		setVisible(true);

	}

	public VentanaSeleccion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Pulse Aqui");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaSeleccionArchivo iniciar = new VentanaSeleccionArchivo();
				iniciar.iniciarVentana();
				dispose();
			}
		});
		btnNewButton.setBounds(340, 166, 118, 23);
		contentPane.add(btnNewButton);

		JButton btnPulseAqui = new JButton("Pulse Aqui");
		btnPulseAqui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaAdministrativa iniciar = new VentanaAdministrativa();
				iniciar.iniciarVentana();
				dispose();
			}
		});
		btnPulseAqui.setBounds(32, 166, 131, 23);
		contentPane.add(btnPulseAqui);

		JLabel lblNewLabel = new JLabel("Bienvenido");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(180, 37, 118, 14);
		contentPane.add(lblNewLabel);

		JLabel lblqueQuieresHacer = new JLabel("\u00BFQue quieres Hacer?");
		lblqueQuieresHacer.setHorizontalAlignment(SwingConstants.CENTER);
		lblqueQuieresHacer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblqueQuieresHacer.setBounds(145, 62, 197, 14);
		contentPane.add(lblqueQuieresHacer);

		JLabel lblAdministrarArchivos = new JLabel("Administrar Archivos");
		lblAdministrarArchivos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrarArchivos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAdministrarArchivos.setBounds(0, 134, 207, 14);
		contentPane.add(lblAdministrarArchivos);

		JLabel lblEditarDocumentos = new JLabel("Editar Documentos");
		lblEditarDocumentos.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarDocumentos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEditarDocumentos.setBounds(294, 134, 207, 14);
		contentPane.add(lblEditarDocumentos);
	}

}
