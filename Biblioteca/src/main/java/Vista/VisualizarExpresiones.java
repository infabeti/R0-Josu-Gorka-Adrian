package Vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class VisualizarExpresiones extends JFrame {

	private JPanel contentPane;
	public JTextArea textArea;
	public JLabel lblArchivo;

	public VisualizarExpresiones() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 569, 347);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 182, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(20, 61, 512, 222);
		contentPane.add(scrollPane);

		JLabel lblNewLabel = new JLabel("ALERTA");
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(229, 11, 93, 25);
		contentPane.add(lblNewLabel);

		JLabel lblSeHanDectectado = new JLabel("SE HAN DECTECTADO CARACTERES NO VALIDOS EN EL ACHIVO :");
		lblSeHanDectectado.setBounds(20, 36, 378, 14);
		contentPane.add(lblSeHanDectectado);

		lblArchivo = new JLabel("");
		lblArchivo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblArchivo.setForeground(new Color(0, 0, 128));
		lblArchivo.setBounds(398, 36, 145, 14);
		contentPane.add(lblArchivo);

		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
	}
}
