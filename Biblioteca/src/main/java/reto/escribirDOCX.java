package reto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class escribirDOCX {
	public void escribirDOCX(String ruta, String texto) {
		File error = new File("src/Errores/errores.txt");
		Errores log = new Errores();

		XWPFDocument document = null;
		FileOutputStream fileOutputStream = null;
		try {
			if (!error.exists()) {
				error.createNewFile();
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
