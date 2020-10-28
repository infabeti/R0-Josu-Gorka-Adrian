package Vista;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import reto.Errores;

public class VisualizarHtml extends JFrame {

	private JPanel contentPane;
	public JEditorPane editorPane;
	Errores log = new Errores();

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

}
