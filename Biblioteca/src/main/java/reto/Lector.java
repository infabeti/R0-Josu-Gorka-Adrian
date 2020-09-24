package reto;

import java.io.*;
import java.util.*;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.extractor.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
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
}