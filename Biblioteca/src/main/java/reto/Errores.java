package reto;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Errores {
	public static Logger logger = Logger.getLogger(reto.Lector.class.getName());
	final String RutaLoguer = "src/Errores/Logging.txt";
	final String RutaError = "src/Errores/errores.txt";

	public void CargarLogger() {
		logger.setLevel(Level.INFO);
		FileHandler fileTxt = null;
		try {
			fileTxt = new FileHandler(RutaLoguer);
		} catch (IOException e) {
			System.out.println("Fallo al cargar logger, revise la extension");
		}
		SimpleFormatter formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
		logger.addHandler(fileTxt);
	}

	public File FicheroErrores() {
		File error = new File(RutaError);
		return error;
	}
}
