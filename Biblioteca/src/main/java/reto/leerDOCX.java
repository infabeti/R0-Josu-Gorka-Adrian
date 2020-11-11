package reto;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.StringTokenizer;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class leerDOCX {

	public String leerDOCX(String ruta) {
		Errores log = new Errores();

		String entrada = "";
		String salida = "";
		Boolean fallos = false;
		try {
			if (!log.FicheroErrores().exists()) {
				log.FicheroErrores().createNewFile();
			}
			PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(log.FicheroErrores(), true)),
					true);
			System.setErr(ps);

			File fichero = new File(ruta);
			FileInputStream fis = new FileInputStream(fichero);
			XWPFDocument documentX = new XWPFDocument(fis);
			XWPFWordExtractor ex = new XWPFWordExtractor(documentX);
			entrada = ex.getText();

		} catch (IOException e) {
			fallos = true;
			System.out.println("Error, no se ha encontrado el archivo seleccionado");
			log.logger.warning("Fallo en el metodo leerDOCX al intentar leer el DOCX");
		}
		if (fallos == false) {
			StringTokenizer st = new StringTokenizer(entrada, ".");
			while (st.hasMoreTokens()) {
				salida = salida + st.nextToken() + "\n\n";

			}

			salida = salida.replace(":", "\n");

			salida = salida.substring(0, salida.length() - 3);
		}

		return salida;
	}
}
