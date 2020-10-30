package reto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class leerHTML {

	public String leerHTML(String ruta) {
		Errores log = new Errores();
		Boolean fallos = false;
		String Escribir = "";
		String salida = "";
		String cadena;
		FileReader fr = null;
		try {
			if (!log.FicheroErrores().exists()) {
				log.FicheroErrores().createNewFile();
			}
			fr = new FileReader(ruta);

			BufferedReader br = new BufferedReader(fr);
			while ((cadena = br.readLine()) != null) {
				Escribir += cadena + "\n";
			}
			br.close();

		} catch (IOException e) {
			fallos = true;
			log.logger.warning("Excepción de Entrada/Salida" + e.getMessage());
			System.out.println("Excepción de Entrada/Salida" + e.getMessage());
		}
		if (fallos == false) {
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
		}
		return salida.trim();
	}

}
