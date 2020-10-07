package reto;

import java.io.*;
import java.util.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.EmptyFileException;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.extractor.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.hwpf.extractor.WordExtractor;

public class Lector {
	
	public String leerExtension(String ruta){
		String salida = "";
		if (encontrar(ruta)==true) {
			if(ruta.endsWith(".doc")) {
				salida = leerDOC(ruta);
			}else if(ruta.endsWith(".docx")) {
				salida = leerDOCX(ruta);
			}else if(ruta.endsWith(".pdf")) {
				salida = leerPDF(ruta);
			}else if(ruta.endsWith(".xml")) {
				salida = leerXML(ruta);
			}
		}else {
			System.out.println("Archivo No encontrado, revise la extension");
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
	
	public boolean encontrar(String ruta) {
		File archivo = new File(ruta);
		if (!archivo.exists()) {
			return false;
		}else {
			return true;
		}
	}
	
	public String leerDOC(String ruta){
		String entrada = "";	
		File error = new File("src/Almacen/errores.txt");
		try {
			if(!error.exists()) {
				error.createNewFile();
			}
			PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(error,true)),true);
			System.setErr(ps);
			
			File fichero = new File(ruta);
			FileInputStream fis = new FileInputStream(fichero);
			HWPFDocument hwpfd = new HWPFDocument(fis);
			WordExtractor we = new WordExtractor(hwpfd);
			entrada = we.getText();		
		}  catch (IOException e) {
			System.out.println("Error, no se ha encontrado el archivo seleccionado");
		}  catch (EmptyFileException e) {
			System.out.println("Error, El fichero esta vacio");
		}
		return entrada.trim();
	}
	
	public String leerDOCX(String ruta){
		String entrada = "";
		File error = new File("src/Almacen/errores.txt");
		try {
			if(!error.exists()) {
				error.createNewFile();
			}
			PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(error,true)),true);
			System.setErr(ps);
			
			File fichero = new File(ruta);
			FileInputStream fis = new FileInputStream(fichero);
			XWPFDocument documentX = new XWPFDocument(fis);         
			XWPFWordExtractor ex = new XWPFWordExtractor(documentX);
			entrada = ex.getText();
		} catch (IOException e) {
			System.out.println("Error, no se ha encontrado el archivo seleccionado");
		}			
		return entrada.trim();
	}
	
	public String leerPDF(String ruta){
		String entrada ="";
		File error = new File("src/Almacen/errores.txt");
		try {
			try (PDDocument document = PDDocument.load(new File(ruta))) {
				if(!error.exists()) {
					error.createNewFile();
				}
				PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(error,true)),true);
				System.setErr(ps);
				
				if (!document.isEncrypted()) {
            		PDFTextStripper tStripper = new PDFTextStripper();
            		entrada = tStripper.getText(document);
				}
			}
		} catch (IOException e) {
			System.out.println("Error, no se ha encontrado el archivo seleccionado");
        }
		return entrada.trim();
	}
	
	public String leerXML(String ruta) {
		String entrada = "";
		int num;
		Document doc;
		Node ntemp;
		File fichero = new File(ruta);
		File error = new File("src/Almacen/errores.txt");
		try {
			if(!error.exists()) {
				error.createNewFile();
			}
			PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(error,true)),true);
			System.setErr(ps);

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringComments(true);	
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(fichero);
			Node raiz = doc.getFirstChild();
			NodeList nodeList = raiz.getChildNodes();
			for(int i = 0; i < nodeList.getLength(); i++) {
				ntemp = nodeList.item(i);
				if(ntemp.getNodeType() == Node.ELEMENT_NODE) {
					entrada += ntemp.getTextContent();
				}
			}
		}catch(SAXException | IOException | ParserConfigurationException e) {
			System.out.println("Ha ocurrido un error al leer el XML");
		}
		
		num = entrada.split("\r\n").length;
		String[] lineas = new String[num];
		lineas =  entrada.split("\n");
		entrada = "";
		
		for (int i = 0; i < lineas.length; i++) {
			lineas[i] = lineas[i].trim();
			entrada = entrada + lineas[i] + "\r\n";
		}
		
		return entrada.trim();
	}
}