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
		File error = new File("src/Errores/errores.txt");
		Errores log = new Errores();

		String entrada = "";
		String salida = "";

		try {
			if (!error.exists()) {
				error.createNewFile();
			}
			PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(error, true)), true);
			System.setErr(ps);

			File fichero = new File(ruta);
			FileInputStream fis = new FileInputStream(fichero);
			XWPFDocument documentX = new XWPFDocument(fis);
			XWPFWordExtractor ex = new XWPFWordExtractor(documentX);
			entrada = ex.getText();
			StringTokenizer st = new StringTokenizer(entrada, ".");
			while (st.hasMoreTokens()) {
				salida = salida + st.nextToken() + "\n\n";

			}

			salida = salida.replace(":", "\n");

			salida = salida.substring(0, salida.length() - 3);

		} catch (IOException e) {
			System.out.println("Error, no se ha encontrado el archivo seleccionado");
			log.logger.warning("Fallo en el metodo leerDOCX al intentar leer el DOCX");
		}
		return salida;
	}
}
