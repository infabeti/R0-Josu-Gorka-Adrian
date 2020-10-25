package reto;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class VisualizarHtml extends JFrame {

	private JPanel contentPane;
	private JEditorPane editorPane;
	Errores log = new Errores();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarHtml frame = new VisualizarHtml();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VisualizarHtml() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 658, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 0, 632, 374);
		contentPane.add(scrollPane);

		editorPane = new JEditorPane();
		scrollPane.setViewportView(editorPane);
	}

	public void conexionhtml(String ruta) {
		File rec = new File(ruta);
		editorPane.setEditable(false);
		try {
			editorPane.setPage(rec.toURI().toURL());
		} catch (Exception e) {
			log.logger.warning("Error al visualizar el HTML " + e.getMessage());
			System.out.println("Error al visualizar el HTML " + e.getMessage());
		}
	}
}
