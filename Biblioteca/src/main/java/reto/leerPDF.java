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
		File error = new File("src/Errores/errores.txt");
		Errores log = new Errores();

		String entrada = "";
		String salida = "";
		try {
			try (PDDocument document = PDDocument.load(new File(ruta))) {
				if (!error.exists()) {
					error.createNewFile();
				}
				PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(error, true)), true);
				System.setErr(ps);

				if (!document.isEncrypted()) {
					PDFTextStripper tStripper = new PDFTextStripper();
					entrada = tStripper.getText(document);
				}

				StringTokenizer st = new StringTokenizer(entrada, ".");
				while (st.hasMoreTokens()) {
					salida = salida + st.nextToken() + "\n\n";

				}

				salida = salida.replace(":", "\n");
			}
		} catch (IOException e) {
			System.out.println("Error, no se ha encontrado el archivo seleccionado");
			log.logger.warning("Fallo en el metodo leerPDF al intentar leer el PDF");
		}
		return salida.trim();
	}
}
