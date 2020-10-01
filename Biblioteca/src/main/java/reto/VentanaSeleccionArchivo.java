package reto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaSeleccionArchivo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textFieldBorde;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSeleccionArchivo frame = new VentanaSeleccionArchivo();
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
	public VentanaSeleccionArchivo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 467);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		textField = new JTextField();
        textField.setToolTipText("Inserta la ruta del fichero");
        textField.setBounds(45, 26, 220, 20);
        contentPane.add(textField);
        textField.setColumns(10);
 
        JButton btnSeleccionar = new JButton("Abrir");
        btnSeleccionar.setBounds(288, 25, 119, 23);
        contentPane.add(btnSeleccionar);
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(52, 75, 209, 338);
        contentPane.add(textArea);
        
        textFieldBorde = new JTextField();
        textFieldBorde.setToolTipText("Inserta la ruta del fichero");
        textFieldBorde.setColumns(10);
        textFieldBorde.setBounds(45, 70, 220, 347);
        contentPane.add(textFieldBorde);
 
       btnSeleccionar.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   File ruta = new File("src/Almacen");
    		   JFileChooser jf = new JFileChooser();
    		   jf.setCurrentDirectory(ruta);
    		   jf.showOpenDialog(null);
    		   File archivo = jf.getSelectedFile();
    		   if(archivo != null) {
    			   textField.setText(archivo.getAbsolutePath());
    			   Lector leer = new Lector();
    			   textArea.setText(leer.LeerExtension(archivo.getAbsolutePath()));;
    		   }
    		   
    	   }
    	   
       });
 
            
	}
}
