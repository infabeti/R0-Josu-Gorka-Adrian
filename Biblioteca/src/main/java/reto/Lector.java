package reto;

import java.io.*;
import java.util.*;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.extractor.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.qoppa.pdfText.PDFText;

import org.apache.poi.hwpf.extractor.WordExtractor;

public class Lector {
	
	public Lector() {
	}
	
	public String LeerExtension(String Ruta) {
		String salida = "";
		if (Encontrar(Ruta)==true) {
			if(Ruta.endsWith(".doc")) {
				salida = leerDOC(Ruta);
			}else if(Ruta.endsWith(".docx")) {
				salida = leerDOCX(Ruta);
			}else if(Ruta.endsWith(".pdf")) {
				salida = leerPDF(Ruta);
			}else if(Ruta.endsWith(".xml")) {
				salida = leerXML(Ruta);
			}
		}else {
			System.out.println("Archivo No encontrado");
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
	
	public boolean Encontrar(String ruta) {
			
		File archivo = new File(ruta);
		if (!archivo.exists()) {
			return true;
		}
		else {
		return true;
		}
	}
	
	public String leerDOC(String ruta){
		String entrada = "";	
			try {
				File fichero = new File(ruta);
				FileInputStream fis = new FileInputStream(fichero);
					HWPFDocument hwpfd = new HWPFDocument(fis);
					WordExtractor we = new WordExtractor(hwpfd);
					entrada = we.getText();		
			} catch (FileNotFoundException e) {
				System.out.println("La ruta no corresponde a ningun archivo");
			} catch (IOException e) {
				e.printStackTrace();
			}

		System.out.println(entrada);	
		return entrada;
	}
	
	public String leerDOCX(String ruta){
		String entrada = "";
			try {
				File fichero = new File(ruta);
				FileInputStream fis = new FileInputStream(fichero);
					XWPFDocument documentX = new XWPFDocument(fis);         
					XWPFWordExtractor ex = new XWPFWordExtractor(documentX);
					entrada = ex.getText();
		
			} catch (FileNotFoundException e) {
				System.out.println("La ruta no corresponde a ningun archivo");
			} catch (IOException e) {
				e.printStackTrace();
			}		
		System.out.println(entrada);
		
		return entrada;
	}
	
	public String leerPDF(String ruta) {
		try {
			PDFText pdfText = new PDFText (ruta, null);			
			String docText = pdfText.getText();				
			System.out.printf(docText);			
			return(docText);
			
		} catch (Throwable t) {
			
			t.printStackTrace();
		}
		return null;
		
	}
	
	public String leerXML(String ruta) {
		String entrada = "";
		Document doc;
		Node ntemp;

		File fichero = new File(ruta);
		try {
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
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(entrada);
		return entrada;
	}
	
}