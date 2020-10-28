package reto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class escribirDOCX {
	public void escribirDOCX(String ruta, String texto) {
		Errores log = new Errores();

		XWPFDocument document = null;
		FileOutputStream fileOutputStream = null;
		try {
			if (!log.FicheroErrores().exists()) {
				log.FicheroErrores().createNewFile();
			}
			document = new XWPFDocument();
			File file = new File(ruta);
			fileOutputStream = new FileOutputStream(file);

			// create Paragraph
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.setText(texto);

			document.write(fileOutputStream);
		} catch (IOException e) {
			e.printStackTrace();
			log.logger.warning("Ha ocurrido al escribir en el DOCX");
		}

	}
}
