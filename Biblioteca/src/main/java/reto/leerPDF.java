package reto;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.StringTokenizer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class leerPDF {
	public String leerPDF(String ruta) {
		Errores log = new Errores();

		String entrada = "";
		String salida = "";
		Boolean fallos = false;
		try (PDDocument document = PDDocument.load(new File(ruta))) {
			if (!log.FicheroErrores().exists()) {
				log.FicheroErrores().createNewFile();
			}
			PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(log.FicheroErrores(), true)),
					true);
			System.setErr(ps);

			if (!document.isEncrypted()) {
				PDFTextStripper tStripper = new PDFTextStripper();
				entrada = tStripper.getText(document);
			}

		} catch (IOException e) {
			fallos = true;
			System.out.println("Error, no se ha encontrado el archivo seleccionado");
			log.logger.warning("Fallo en el metodo leerPDF al intentar leer el PDF");
		}
		if (fallos == false) {
			String sinEsp = "";
			StringTokenizer ste = new StringTokenizer(entrada, "\n");
			while (ste.hasMoreTokens()) {
				sinEsp = sinEsp + ste.nextToken();
			}
			StringTokenizer st = new StringTokenizer(sinEsp, ".");
			while (st.hasMoreTokens()) {
				salida = salida + st.nextToken() + ".\n";

			}

			salida = salida.replace(":", "\n");

			salida = salida.substring(0, salida.length() - 2);
		}
		return salida.trim();
	}
}
