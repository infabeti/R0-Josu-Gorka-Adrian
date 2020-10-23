package reto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class escribirHTML {

	public void escribirHTML(String ruta, String contenido) {
		File error = new File("src/Errores/errores.txt");
		Errores log = new Errores();

		try {
			if (!error.exists()) {
				error.createNewFile();
			}
			FileWriter fw = new FileWriter(ruta);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(contenido);
			bw.close();
		} catch (IOException ex) {
			log.logger.warning("Excepción de archivo no encontrado" + ex.getMessage());
		}
	}
}
