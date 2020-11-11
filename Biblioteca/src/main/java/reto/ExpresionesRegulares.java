package reto;

public class ExpresionesRegulares {

	public Boolean encontrarCaracter(String patron, String texto) {
		Boolean salida = false;

		for (int i = 0; i < texto.length(); i++) {
			for (int j = 0; j < patron.length(); j++) {
				if (patron.charAt(j) == texto.charAt(i)) {
					salida = true;
				}
			}
		}
		return salida;
	}
}
