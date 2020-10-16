package reto;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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
	
	public static void main(String[] args) {
		VentanaSeleccionArchivo iniciar = new VentanaSeleccionArchivo();
		iniciar.setVisible(true);
	}
	
	public VentanaSeleccionArchivo() {
		Lector leer = new Lector();
		leer.CargarLogger();
		
		setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 658, 570);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        textField = new JTextField();
        textField.setToolTipText("Inserta la ruta del fichero, si quieres escribir un texto que se muestre por consola escriba 'estandar' en la ruta");
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
 
        btnSeleccionar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String ruta[]=null;
        		String rutamodificada=null;
        		String rutaentera=null;
               JFileChooser jf = new JFileChooser();
               jf.showOpenDialog(null);
               File archivo = jf.getSelectedFile();
               if(archivo != null) {
            	   rutaentera=archivo.getAbsolutePath();   
                   textField.setText(leer.modificarRuta(rutaentera));
                   textArea.setText(leer.leer(archivo.getAbsolutePath()));
               }
           }});
        btnEscribir.addActionListener(new ActionListener() {
           	public void actionPerformed(ActionEvent e) {
        		leer.escribir(textField.getText(),textArea.getText());
           	}
        });
	}
}

            
    
