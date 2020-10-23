package reto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

public class Escritor {
	File error = new File("src/Errores/errores.txt");
	Errores log = new Errores();

	public void escribir(String ruta, String texto) {
		Comprobar comprobar = new Comprobar();
		if (ruta.equals("estandar")) {
			System.out.println(texto);
		} else if (comprobar.encontrar(ruta) == true) {
			if (ruta.endsWith(".docx")) {
				escribirDOCX(ruta, texto);
			} else if (ruta.endsWith(".pdf")) {
				escribirPDF(ruta, texto);
			} else if (ruta.endsWith(".xml")) {
				escribirXML(ruta, texto);
			} else if (ruta.endsWith(".html")) {
				escribirHTML(ruta, texto);
			}
		} else {
			System.out.println("Archivo No encontrado, revise la extension");
			log.logger.warning("Metodo escribir fallo, Archivo No encontrado, revise la extension");
		}
	}

	public void escribirDOCX(String ruta, String texto) {
		XWPFDocument document = null;
		FileOutputStream fileOutputStream = null;
		try {
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

	public void escribirPDF(String ruta, String contenido) {
		try {
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

	public void escribirHTML(String ruta, String contenido) {
		try {
			FileWriter fw = new FileWriter(ruta);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(contenido);
			bw.close();
		} catch (IOException ex) {
			log.logger.warning("Excepción de archivo no encontrado" + ex.getMessage());
		}
	}

	public void escribirXML(String ruta, String contenido) {
		String[] texto = contenido.split("\n");
		File fichero = new File(ruta);
		String nodo = texto[0];
		Boolean contados = false;
		int cont = 0;
		int atributos = 0;

		for (int i = 1; i < texto.length && !contados; i++) {
			if (texto[i].equals("---")) {
				contados = true;
				atributos = cont;
			}
			cont++;
		}

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder builder;

			builder = factory.newDocumentBuilder();

			Document doc = builder.parse(fichero);
			int nodos = 0;

			for (int i = 0; i < texto.length; i++) {
				if (texto[i].equals("---")) {

				} else if (texto[i].equals(nodo)) {
					texto[i] = "---";
					nodos++;
				} else {
					texto[i] = texto[i].split(":")[1].trim();
				}
			}
			cont = 1;
			for (int i = 0; i < nodos; i++) {
				if (nodos > doc.getElementsByTagName(nodo).getLength()) {
					for (int j = 0; j < nodos - doc.getElementsByTagName(nodo).getLength(); j++) {
						Node nuevoNodo = doc.createElement(nodo);
						for (int k = 0; k < doc.getElementsByTagName(nodo).item(0).getChildNodes().getLength(); k++) {
							Node nodoHijo = doc.createElement(
									doc.getElementsByTagName(nodo).item(0).getChildNodes().item(k).getNodeName());
							nuevoNodo.appendChild(nodoHijo);
						}
						doc.getElementsByTagName(nodo).item(i).getParentNode().appendChild(nuevoNodo);
					}
				}
				for (int j = 0; j < doc.getElementsByTagName(nodo).item(i).getChildNodes().getLength(); j++) {
					while (texto[cont].equals("---") || texto[cont].equals(nodo)) {
						cont++;
					}
					Element elemento = (Element) doc.getElementsByTagName(nodo).item(i).getChildNodes().item(j);
					elemento.setTextContent(texto[cont]);
					cont++;
				}
			}

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Result output = new StreamResult(fichero);
			Source input = new DOMSource(doc);
			transformer.transform(input, output);

		} catch (ParserConfigurationException | SAXException | IOException | TransformerFactoryConfigurationError
				| TransformerException e) {
			e.printStackTrace();
			System.out.println("Ha ocurrido un error al escribir en el XML");
			log.logger.warning("Ha ocurrido un error al escribir en el XML" + e.getMessage());
		}

	}
}
