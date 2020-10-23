package reto;

import java.io.File;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

public class escribirPDF {

	public void escribirPDF(String ruta, String contenido) {
		File error = new File("src/Errores/errores.txt");
		Errores log = new Errores();

		try {
			if (!error.exists()) {
				error.createNewFile();
			}
			PdfWriter writer = new PdfWriter(ruta);
			PdfDocument pdfDoc = new PdfDocument(writer);
			com.itextpdf.layout.Document d = new com.itextpdf.layout.Document(pdfDoc);
			com.itextpdf.layout.element.Paragraph p1 = new com.itextpdf.layout.element.Paragraph(contenido);
			d.add(p1);
			d.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.logger.warning("Ha ocurrido al escribir en el PDF");
		}
	}

}
