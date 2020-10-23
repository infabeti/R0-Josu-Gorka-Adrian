package reto;

import java.io.File;
import java.util.Scanner;

public class Lector {
	File error = new File("src/Errores/errores.txt");
	Errores log = new Errores();

	public String leer(String ruta) {
		leerDOC doc = new leerDOC();
		leerDOCX docx = new leerDOCX();
		leerPDF pdf = new leerPDF();
		leerXML xml = new leerXML();
		leerHTML html = new leerHTML();

		Comprobar comprobar = new Comprobar();
		String salida = "";
		if (comprobar.encontrar(ruta) == true) {
			if (ruta.endsWith(".doc")) {
				salida = doc.leerDOC(ruta);
			} else if (ruta.endsWith(".docx")) {
				salida = docx.leerDOCX(ruta);
			} else if (ruta.endsWith(".pdf")) {
				salida = pdf.leerPDF(ruta);
			} else if (ruta.endsWith(".xml")) {
				salida = xml.leerXML(ruta);
			} else if (ruta.endsWith(".html")) {
				salida = html.leerHTML(ruta);
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

	public String modificarRuta(String Ruta) {
		String ruta[] = null;
		String Nombre;
		String rutamodificada = null;
		rutamodificada = Ruta.replace("\\", ",").replace("/", ",");
		ruta = rutamodificada.split(",");
		Nombre = ruta[ruta.length - 1].toString();
		return Nombre;

	}

}