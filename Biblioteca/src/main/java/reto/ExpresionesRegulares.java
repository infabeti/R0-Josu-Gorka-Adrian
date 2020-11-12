package reto;

import Vista.VisualizarExpresiones;

public class ExpresionesRegulares {

	public Boolean encontrarCaracter(String patron, String texto) {
		Boolean salida = false;
		String mal = "";

		for (int i = 0; i < texto.length(); i++) {
			for (int j = 0; j < patron.length(); j++) {
				if (patron.charAt(j) == texto.charAt(i)) {
					salida = true;
					mal = mal + patron.charAt(j) + ", ";
				}
			}
		}

		if (salida) {
			mal = mal.substring(0, mal.length() - 2);
			VisualizarExpresiones ventana = new VisualizarExpresiones();
			ventana.textArea.setText(mal);
			ventana.setVisible(true);
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
					if (texto.charAt(x) == '�' || texto.charAt(x) == '�' || texto.charAt(x) == '�'
							|| texto.charAt(x) == '�' || texto.charAt(x) == '�' || texto.charAt(x) == '�'
							|| texto.charAt(x) == '�' || texto.charAt(x) == '�' || texto.charAt(x) == '�'
							|| texto.charAt(x) == '�') {
						ExpresionCorrecta = true;
						cogerpalabra = true;
					}
					x++;
				} while (texto.charAt(x) != '>' || x == texto.length());
				acumuladorCaracteres = acumuladorCaracteres + '>';
				i = x;
			}
			if (cogerpalabra == true) {
				ObtencionPalabrasMal = ObtencionPalabrasMal + acumuladorCaracteres + "\n";
			}
			i++;
		}
		if (ExpresionCorrecta == true) {
			VisualizarExpresiones ventana = new VisualizarExpresiones();
			ventana.lblArchivo.setText("HTML");
			ventana.textArea.setText(ObtencionPalabrasMal);
			ventana.setVisible(true);
		}
		return ExpresionCorrecta;
	}
}
