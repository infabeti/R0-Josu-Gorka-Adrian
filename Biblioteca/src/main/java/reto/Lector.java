package reto;

import java.util.Scanner;

public class Lector {
	
	public Lector() {
	}
	
	public String leerTeclado() {
		String entrada = "";
		Scanner sc = new Scanner(System.in);
		entrada = sc.next();
		
		return entrada;
	}
}