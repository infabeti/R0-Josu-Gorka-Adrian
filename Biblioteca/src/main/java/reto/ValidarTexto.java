package reto;

public class ValidarTexto {

	private String ruta;
	private String texto;

	public ValidarTexto(String ruta, String texto) {
		this.ruta = ruta;
		this.texto = texto;
	}

	public Boolean aplicarExpresion() {
		if (ruta.endsWith(".docx") || ruta.endsWith(".pdf")) {
			if (texto.matches(".*[@|·#¬&*Ç¨´].*")) {
				return true;
			} else {
				return false;
			}
		} else if (ruta.endsWith(".xml")) {
			return false;
		} else if (ruta.endsWith(".html")) {
			return false;
		} else {
			return false;
		}
	}
}
