package reto;

import java.io.File;

public class Comprobar {
	public boolean encontrar(String ruta) {
		File archivo = new File(ruta);
		if (!archivo.exists()) {
			return false;
		} else {
			return true;
		}
	}
}
