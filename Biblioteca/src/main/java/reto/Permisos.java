package reto;

import java.io.File;

import javax.swing.JCheckBox;

public class Permisos {

	private JCheckBox chckbxLectura;
	private JCheckBox chckbxEscritura;
	private JCheckBox chckbxEjecucion;

	public Permisos(JCheckBox chckbxLectura, JCheckBox Escritura, JCheckBox Ejecucion) {
		this.chckbxLectura = chckbxLectura;
		this.chckbxEscritura = Escritura;
		this.chckbxEjecucion = Ejecucion;
	}

	public void BuscarPermisos(String ruta) {
		File Fichero = new File(ruta);
		if (Fichero.canWrite()) {
			chckbxEscritura.setSelected(true);
		} else {
			chckbxEscritura.setSelected(false);
		}
		if (Fichero.canRead()) {
			chckbxLectura.setSelected(true);
		} else {
			chckbxLectura.setSelected(false);
		}
		if (Fichero.canExecute()) {
			chckbxEjecucion.setSelected(true);
		} else {
			chckbxEjecucion.setSelected(false);
		}

	}

	public void ColocarPermisos(String ruta) {
		File Fichero = new File(ruta);
		if (chckbxLectura.isSelected() || chckbxEscritura.isSelected() || chckbxEjecucion.isSelected()) {
			Fichero.setReadable(true, false);
			Fichero.setExecutable(true, false);
			Fichero.setWritable(true, false);
		}
		if (chckbxLectura.isSelected()) {
			Fichero.setReadable(true, false);
		} else {
			Fichero.setReadable(false, false);
		}
		if (chckbxEscritura.isSelected()) {
			Fichero.setWritable(true);
		} else {
			Fichero.setWritable(false);
		}
		if (chckbxEjecucion.isSelected()) {
			Fichero.setExecutable(true);
		} else {
			Fichero.setExecutable(false);
		}
	}
}
