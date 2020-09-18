package reto;

import java.util.Scanner;

public class LectorTeclado {
	
	public LectorTeclado() {
	}
	
	public String leer() {
		String entrada = "";
		Scanner sc = new Scanner(System.in);
		entrada = sc.next();
		
		return entrada;
	}
}