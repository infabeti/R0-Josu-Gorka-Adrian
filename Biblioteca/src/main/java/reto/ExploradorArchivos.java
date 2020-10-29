package reto;

import java.io.File;

import javax.swing.JFileChooser;

public class ExploradorArchivos {

	public String seleccionarArchivo() {
		String ruta;
		File archivo = new File("C");
		JFileChooser jf = new JFileChooser();
		jf.showOpenDialog(null);
		archivo = jf.getSelectedFile();
		if (archivo != null) {
			ruta = archivo.getAbsolutePath();
		} else {
			ruta = null;
		}

		return ruta;
	}
}
