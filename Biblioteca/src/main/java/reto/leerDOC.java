package reto;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.poi.EmptyFileException;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class leerDOC {
	Errores log = new Errores();

	public String leerDOC(String ruta) {
		String entrada = "";
		try {
			if (!log.FicheroErrores().exists()) {
				log.FicheroErrores().createNewFile();
			}
			PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(log.FicheroErrores(), true)),
					true);
			System.setErr(ps);

			File fichero = new File(ruta);
			FileInputStream fis = new FileInputStream(fichero);
			HWPFDocument hwpfd = new HWPFDocument(fis);
			WordExtractor we = new WordExtractor(hwpfd);
			entrada = we.getText();
		} catch (IOException e) {
			System.out.println("Error, no se ha encontrado el archivo seleccionado");
			log.logger.warning("Error en el metodo leerDOC, no se ha encontrado el archivo seleccionado");
		} catch (EmptyFileException e) {
			System.out.println("Error, El fichero esta vacio");
			log.logger.warning("Error en el metodo leerDOC, El fichero esta vacio");
		}
		return entrada.trim();
	}

}
