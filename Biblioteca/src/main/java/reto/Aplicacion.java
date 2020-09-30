package reto;

public class Aplicacion {

	public static void main(String[] args) {
		
		String Ruta;
		
		Lector Lectura= new Lector();
		
		Ruta=Lectura.leerTeclado();
	
		if (Lectura.Encontrar(Ruta)==true) {
			if(Ruta.endsWith(".doc")) {
				Lectura.leerDOC(Ruta);
			}else if(Ruta.endsWith(".docx")) {
				Lectura.leerDOCX(Ruta);
			}else if(Ruta.endsWith(".pdf")) {
				Lectura.leerPDF(Ruta);
			}else if(Ruta.endsWith(".xml")) {
				Lectura.leerXML(Ruta);
			}
		}else {
			System.out.println("Archivo No encontrado");
		}
		

	}

}
