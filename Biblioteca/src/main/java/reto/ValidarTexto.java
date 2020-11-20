package reto;

public class ValidarTexto {

	private String ruta;
	private String texto;

	public ValidarTexto(String ruta, String texto) {
		this.ruta = ruta;
		this.texto = texto;
	}

	public Boolean aplicarExpresion() {
		ExpresionesRegulares e = new ExpresionesRegulares();
		if (ruta.endsWith(".docx") || ruta.endsWith(".pdf")) {
			return e.encontrarCaracter("@|·#¬&*Ç¨´", this.texto);
		} else if (ruta.endsWith(".xml")) {
			return e.encontrarCaracter("<>", this.texto);
		} else if (ruta.endsWith(".html")) {
			return e.Expresiones_Html(texto);
		} else {
			return false;
		}
	}
}
