package reto;

import java.io.File;

import javax.swing.JFileChooser;

public class ExploradorArchivos {

	public File seleccionarArchivo() {

		File archivo = new File("C");
		JFileChooser jf = new JFileChooser();
		jf.showOpenDialog(null);
		archivo = jf.getSelectedFile();

		return archivo;
	}
}
