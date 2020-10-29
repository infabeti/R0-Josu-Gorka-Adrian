package reto;

public class Escritor {
	Errores log = new Errores();

	public void escribirArchivo(String ruta, String texto) {
		escribirDOCX docx = new escribirDOCX();
		escribirPDF pdf = new escribirPDF();
		escribirXML xml = new escribirXML();
		escribirHTML html = new escribirHTML();

		Comprobar existeArchivo = new Comprobar();

		if (ruta.equals("estandar")) {
			System.out.println(texto);
		} else if (existeArchivo.encontrar(ruta) == true) {
			if (ruta.endsWith(".docx")) {
				docx.escribirDOCX(ruta, texto);
			} else if (ruta.endsWith(".pdf")) {
				pdf.escribirPDF(ruta, texto);
			} else if (ruta.endsWith(".xml")) {
				xml.escribirXML(ruta, texto);
			} else if (ruta.endsWith(".html")) {
				html.escribirHTML(ruta, texto);
			}
		} else {
			System.out.println("Archivo No encontrado, revise la extension");
			log.logger.warning("Metodo escribir fallo, Archivo No encontrado, revise la extension");
		}
	}

}
