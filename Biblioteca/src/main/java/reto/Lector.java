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
	
	public String leerTeclado() {
		String entrada = "";
		Scanner sc = new Scanner(System.in);
		entrada = sc.next();
		
		return entrada;
	}
	
	public String leerDOC_DOCX(){
		Scanner sc = new Scanner(System.in);
		String ruta;
		String entrada = "";
		boolean encontrado = false;
		while(!encontrado) {
			try {
				System.out.println("Introduce la ruta del archivo que quieras leer");
				ruta = sc.next();
				File fichero = new File(ruta);
				FileInputStream fis = new FileInputStream(fichero);
				encontrado = true;
				
				if(ruta.endsWith(".doc")) {
					HWPFDocument hwpfd = new HWPFDocument(fis);
					WordExtractor we = new WordExtractor(hwpfd);
					entrada = we.getText();
					
				}else if(ruta.endsWith(".docx")) {
					XWPFDocument documentX = new XWPFDocument(fis);         
					XWPFWordExtractor ex = new XWPFWordExtractor(documentX);
					entrada = ex.getText();
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("La ruta no corresponde a ningun archivo");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(entrada);
		
		return entrada;
	}
	
	public String leerPDF() {
		Scanner sc = new Scanner(System.in);
		try {
			
			System.out.println("Escribe la ruta del archivo que quieres leer");
			String URL = sc.next();
			PDFText pdfText = new PDFText (URL, null);
			
			String docText = pdfText.getText();
			
			
			
			System.out.printf(docText);
			
			return(docText);
			
		} catch (Throwable t) {
			// TODO Auto-generated catch block
			t.printStackTrace();
		}
		return null;
		
	}
	
	public String leerXML() {
		Scanner sc = new Scanner(System.in);
		String entrada = "";
		String ruta;
		Document doc;
		Node node;
		String datos_nodo[] = null;
		Node ntemp;
		
		System.out.println("Introduce la ruta del archivo que quieras leer");
		ruta = sc.next();
		File fichero = new File(ruta);
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(fichero);//Documento ya listo para leer
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