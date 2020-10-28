package reto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class leerHTML {

	public String leerHTML(String ruta) {
		Errores log = new Errores();

		String Escribir = "";
		String salida = "";
		String cadena;
		FileReader fr = null;
		try {
			if (!log.FicheroErrores().exists()) {
				log.FicheroErrores().createNewFile();
			}
			fr = new FileReader(ruta);
		} catch (IOException e) {
			log.logger.warning("Excepción de archivo no encontrado" + e.getMessage());
			System.out.println("No se ha podido encontrar el archivo. ERROR: " + e.toString());
		}
		BufferedReader br = new BufferedReader(fr);

		try {
			while ((cadena = br.readLine()) != null) {
				Escribir += cadena + "\n";
			}
			br.close();
			String sinEsp = "";
			StringTokenizer ste = new StringTokenizer(Escribir, "\n");
			while (ste.hasMoreTokens()) {
				sinEsp = sinEsp + ste.nextToken();
			}
			StringTokenizer st = new StringTokenizer(sinEsp, ">");
			while (st.hasMoreTokens()) {
				salida = salida + st.nextToken() + ">\n";
			}
			salida = salida.substring(0, salida.length() - 1);
		} catch (IOException e) {
			log.logger.warning("Excepción de Entrada/Salida" + e.getMessage());
			System.out.println("Excepción de Entrada/Salida" + e.getMessage());
		}
		return salida.trim();
	}

}
