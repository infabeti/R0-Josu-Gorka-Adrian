package reto;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.EmptyFileException;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

public class Lector {
	private static Logger logger = Logger.getLogger(reto.Lector.class.getName());
	File error = new File("src/Errores/errores.txt");

	public void CargarLogger() {
		logger.setLevel(Level.INFO);
		FileHandler fileTxt = null;
		try {
			fileTxt = new FileHandler("src/Errores/Logging.txt");
		} catch (IOException e) {
			System.out.println("Fallo al cargar logger, revise la extension");
		}
		SimpleFormatter formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
		logger.addHandler(fileTxt);
	}

	public String leer(String ruta) {
		String salida = "";
		if (encontrar(ruta) == true) {
			if (ruta.endsWith(".doc")) {
				salida = leerDOC(ruta);
			} else if (ruta.endsWith(".docx")) {
				salida = leerDOCX(ruta);
			} else if (ruta.endsWith(".pdf")) {
				salida = leerPDF(ruta);
			} else if (ruta.endsWith(".xml")) {
				salida = leerXML(ruta);
			} else if (ruta.endsWith(".html")) {
					salida = leerHTML(ruta);
			} else {
				salida = ("Tipo de Archivo no valido");
				logger.warning("Metodo leer fallo, Seleccionado tipo de archivo no compatible");
			}

			;
		} else {
			System.out.println("Archivo No encontrado, revise la extension");
			logger.warning("Metodo leer fallo, Archivo No encontrado, revise la extension");
		}
		return salida;
	}

	public void escribir(String ruta, String texto) {

		if (ruta.equals("estandar")) {
			System.out.println(texto);
		} else if (encontrar(ruta) == true) {
			if (ruta.endsWith(".doc")) {
				escribirDOC(ruta, texto);
			} else if (ruta.endsWith(".docx")) {
				escribirDOCX(ruta, texto);
			} else if (ruta.endsWith(".pdf")) {
				escribirPDF(ruta, texto);
			} else if (ruta.endsWith(".html")) {
				escribirHTML(ruta, texto);
			}
		} else {
			System.out.println("Archivo No encontrado, revise la extension");
			logger.warning("Metodo escribir fallo, Archivo No encontrado, revise la extension");
		}
	}

	public String leerTeclado() {
		String entrada = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca la Ruta del fichero que quieres Leer: ");
		entrada = sc.next();
		return entrada;
	}

	public boolean encontrar(String ruta) {
		File archivo = new File(ruta);
		if (!archivo.exists()) {
			return false;
		} else {
			return true;
		}
	}

	public String leerDOC(String ruta) {
		String entrada = "";
		try {
			if (!error.exists()) {
				error.createNewFile();
			}
			PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(error, true)), true);
			System.setErr(ps);

			File fichero = new File(ruta);
			FileInputStream fis = new FileInputStream(fichero);
			HWPFDocument hwpfd = new HWPFDocument(fis);
			WordExtractor we = new WordExtractor(hwpfd);
			entrada = we.getText();
		} catch (IOException e) {
			System.out.println("Error, no se ha encontrado el archivo seleccionado");
			logger.warning("Error en el metodo leerDOC, no se ha encontrado el archivo seleccionado");
		} catch (EmptyFileException e) {
			System.out.println("Error, El fichero esta vacio");
			logger.warning("Error en el metodo leerDOC, El fichero esta vacio");
		}
		return entrada.trim();
	}

	public String leerDOCX(String ruta) {
		String entrada = "";
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
		} catch (IOException e) {
			System.out.println("Error, no se ha encontrado el archivo seleccionado");
			logger.warning("Fallo en el metodo leerDOCX al intentar leer el DOCX");
		}
		return entrada.trim();
	}

	public String leerPDF(String ruta) {
		String entrada = "";
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
			}
		} catch (IOException e) {
			System.out.println("Error, no se ha encontrado el archivo seleccionado");
			logger.warning("Fallo en el metodo leerPDF al intentar leer el PDF");
		}
		return entrada.trim();
	}

	public String leerXML(String ruta) {
		String entrada = "";
		int num;
		org.w3c.dom.Document doc;
		Node ntemp;
		File fichero = new File(ruta);
		try {
			if (!error.exists()) {
				error.createNewFile();
			}
			PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(error, true)), true);
			System.setErr(ps);

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(fichero);
			Node raiz = doc.getFirstChild();
			NodeList nodeList = raiz.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				ntemp = nodeList.item(i);
				if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
					entrada += ntemp.getTextContent();
				}
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println("Ha ocurrido un error al leer el XML");
			logger.warning("Fallo en el metodo leerXML al intentar leer el XML");
		}

		num = entrada.split("\r\n").length;
		String[] lineas = new String[num];
		lineas = entrada.split("\n");
		entrada = "";

		for (int i = 0; i < lineas.length; i++) {
			lineas[i] = lineas[i].trim();
			entrada = entrada + lineas[i] + "\r\n";
		}

		return entrada.trim();
	}

	public String leerHTML(String ruta) {
		String Escribir = "";
		String cadena;
		FileReader fr = null;
		try { 
			fr = new FileReader(ruta);
		} catch (FileNotFoundException e) {
			logger.warning("Excepción de archivo no encontrado" + e.getMessage());
			System.out.println("No se ha podido encontrar el archivo. ERROR: " + e.toString());
		}
		BufferedReader br = new BufferedReader(fr);
		
		try {
			while ((cadena = br.readLine()) != null) {
				Escribir += cadena + "\n";
			}
			br.close();
		} catch (IOException e) {
			logger.warning("Excepción de Entrada/Salida" + e.getMessage());
			System.out.println("Excepción de Entrada/Salida" + e.getMessage());
		}	
		return Escribir.trim();
	}
	
	public void escribirDOC(String ruta, String contenido) {
		try {
			FileOutputStream outStream = new FileOutputStream(ruta);
			XWPFDocument doc = new XWPFDocument();
			XWPFParagraph para = doc.createParagraph();
			XWPFRun run = para.createRun();
			doc.getParagraphs();
			run.setText(contenido);
			doc.write(outStream);
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warning("Ha ocurrido al escribir en el DOC");
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
			logger.warning("Ha ocurrido al escribir en el DOCX");
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
			logger.warning("Ha ocurrido al escribir en el PDF");
		}
	}
	public void escribirHTML(String ruta, String contenido) {
		  try {
		        FileWriter fw = new FileWriter(ruta);
		        BufferedWriter bw = new BufferedWriter(fw);
		        
		        bw.write(contenido);
		        bw.close();        
		    } catch (IOException ex) {
		    	logger.warning("Excepción de archivo no encontrado" + ex.getMessage());
		    }
		}
}