package reto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class leerHTML {

	public String leerHTML(String ruta) {
		File error = new File("src/Errores/errores.txt");
		Errores log = new Errores();

		String Escribir = "";
		String cadena;
		FileReader fr = null;
		try {
			if (!error.exists()) {
				error.createNewFile();
			}
			fr = new FileReader(ruta);
		} catch (IOException e) {
			log.logger.warning("Excepci�n de archivo no encontrado" + e.getMessage());
			System.out.println("No se ha podido encontrar el archivo. ERROR: " + e.toString());
		}
		BufferedReader br = new BufferedReader(fr);

		try {
			while ((cadena = br.readLine()) != null) {
				Escribir += cadena + "\n";
			}
			br.close();
		} catch (IOException e) {
			log.logger.warning("Excepci�n de Entrada/Salida" + e.getMessage());
			System.out.println("Excepci�n de Entrada/Salida" + e.getMessage());
		}
		return Escribir.trim();
	}

}
