package reto;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Errores {
	public static Logger logger = Logger.getLogger(reto.Lector.class.getName());

	public void CargarLogger() {
		logger.setLevel(Level.INFO);
		FileHandler fileTxt = null;
		try {
			fileTxt = new FileHandler("src/Errores/Logging.txt");
		} catch (IOException e) {
			System.out.println("Fallo al cargar logger, revise la extension");
		}
		SimpleFormatter formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
		logger.addHandler(fileTxt);
	}
}
