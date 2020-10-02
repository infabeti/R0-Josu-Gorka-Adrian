package reto;



import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.ScrollPaneConstants;

public class VentanaSeleccionArchivo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textFieldBorde;
	private JScrollPane scrollPane;

	
	public VentanaSeleccionArchivo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 582, 570);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        textField = new JTextField();
        textField.setToolTipText("Inserta la ruta del fichero");
        textField.setBounds(45, 26, 356, 20);
        contentPane.add(textField);
        textField.setColumns(10);
 
        JButton btnSeleccionar = new JButton("Abrir");
        btnSeleccionar.setBounds(423, 25, 119, 23);
        contentPane.add(btnSeleccionar);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(45, 68, 497, 437);
        contentPane.add(scrollPane);
        
        JTextArea textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
 
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

            
    


