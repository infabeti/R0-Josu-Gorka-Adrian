package reto;

import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;

public class ExploradorArchivos {

	public void guardarArchivo(String rutaAntigua) {
		JFileChooser file = new JFileChooser();
		file.showSaveDialog(null);
		File guarda = file.getSelectedFile();
		FileWriter save = null;

		if (guarda != null) {
			File f1 = new File(rutaAntigua);
			f1.renameTo(guarda);
		}
	}

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
