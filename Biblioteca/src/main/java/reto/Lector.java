package reto;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

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
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Lector {
	File error = new File("src/Errores/errores.txt");
	Errores log = new Errores();

	public String leer(String ruta) {
		Comprobar comprobar = new Comprobar();
		String salida = "";
		if (comprobar.encontrar(ruta) == true) {
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
				log.logger.warning("Metodo leer fallo, Seleccionado tipo de archivo no compatible");
			}

			;
		} else {
			System.out.println("Archivo No encontrado, revise la extension");
			log.logger.warning("Metodo leer fallo, Archivo No encontrado, revise la extension");
		}
		return salida;
	}

	public String leerTeclado() {
		String entrada = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca la Ruta del fichero que quieres Leer: ");
		entrada = sc.next();
		return entrada;
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
			log.logger.warning("Error en el metodo leerDOC, no se ha encontrado el archivo seleccionado");
		} catch (EmptyFileException e) {
			System.out.println("Error, El fichero esta vacio");
			log.logger.warning("Error en el metodo leerDOC, El fichero esta vacio");
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
			log.logger.warning("Fallo en el metodo leerDOCX al intentar leer el DOCX");
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
			log.logger.warning("Fallo en el metodo leerPDF al intentar leer el PDF");
		}
		return entrada.trim();
	}

	public String leerXML(String ruta) {
		String entrada = "";
		int num;
		Document doc;
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
					entrada += ntemp.getNodeName() + "\n";
					for (int j = 0; j < ntemp.getChildNodes().getLength(); j++) {
						entrada += ntemp.getChildNodes().item(j).getNodeName() + ": "
								+ ntemp.getChildNodes().item(j).getTextContent() + "\n";
					}
					entrada += "---\n";
				}
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println("Ha ocurrido un error al leer el XML");
			log.logger.warning("Fallo en el metodo leerXML al intentar leer el XML");
		}

		num = entrada.split("\n").length;
		String[] lineas = new String[num];
		lineas = entrada.split("\r\n");
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
			log.logger.warning("Excepción de archivo no encontrado" + e.getMessage());
			System.out.println("No se ha podido encontrar el archivo. ERROR: " + e.toString());
		}
		BufferedReader br = new BufferedReader(fr);

		try {
			while ((cadena = br.readLine()) != null) {
				Escribir += cadena + "\n";
			}
			br.close();
		} catch (IOException e) {
			log.logger.warning("Excepción de Entrada/Salida" + e.getMessage());
			System.out.println("Excepción de Entrada/Salida" + e.getMessage());
		}
		return Escribir.trim();
	}
}