package reto;

import java.io.File;

import javax.swing.JTextField;

public class TamanoArchivo {
	private JTextField textTamano;

	public TamanoArchivo(JTextField textTamano) {
		this.textTamano = textTamano;
	}

	public void BuscarTamaño(String ruta) {
		File Fichero = new File(ruta);
		long Dimension = Fichero.length();
		textTamano.setText(Dimension + "");
	}
}
