package reto;

import java.io.File;

import Vista.VisualizarHtml;

public class ConexionHTML {
	Errores log = new Errores();

	public void conexionhtml(String ruta) {
		VisualizarHtml html = new VisualizarHtml();
		File rec = new File(ruta);
		html.editorPane.setEditable(false);
		try {
			html.editorPane.setPage(rec.toURI().toURL());
		} catch (Exception e) {
			log.logger.warning("Error al visualizar el HTML " + e.getMessage());
			System.out.println("Error al visualizar el HTML " + e.getMessage());
		}
		html.setVisible(true);
	}
}
