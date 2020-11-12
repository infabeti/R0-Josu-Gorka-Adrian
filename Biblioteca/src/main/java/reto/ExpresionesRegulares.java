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

	public boolean Expresiones_Html(String texto) {
		boolean ExpresionCorrecta = false;
		boolean cogerpalabra = false;
		int x, i = 0;
		String ObtencionPalabrasMal = "", acumuladorCaracteres = "";
		while (i < texto.length()) {
			cogerpalabra = false;
			if (texto.charAt(i) == '<') {

				acumuladorCaracteres = "";
				x = i;
				do {
					acumuladorCaracteres = acumuladorCaracteres + texto.charAt(x);
					if (texto.charAt(x) == 'á' || texto.charAt(x) == 'é' || texto.charAt(x) == 'í'
							|| texto.charAt(x) == 'ó' || texto.charAt(x) == 'ú' || texto.charAt(x) == 'Á'
							|| texto.charAt(x) == 'É' || texto.charAt(x) == 'Í' || texto.charAt(x) == 'Ó'
							|| texto.charAt(x) == 'Ú') {
						ExpresionCorrecta = true;
						cogerpalabra = true;
					}
					x++;
				} while (texto.charAt(x) != '>' || x == texto.length());
				acumuladorCaracteres = acumuladorCaracteres + '>';
				i = x;
			}
			if (cogerpalabra == true) {
				ObtencionPalabrasMal = ObtencionPalabrasMal + acumuladorCaracteres + " ";
			}
			i++;
		}
		if (ExpresionCorrecta == true) {
			System.out.println("Las Llaves que estan mal son estas: " + ObtencionPalabrasMal);
		}
		return ExpresionCorrecta;
	}
}
