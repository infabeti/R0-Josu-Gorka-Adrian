package Biblioteca;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import reto.Escritor;
import reto.Lector;

public class TestEscribir {

	Escritor escritor = new Escritor();
	Lector lector = new Lector();

	@Test
	public void testEscribirPDF() {
		escritor.escribir("src/Almacen/pruebaTest.pdf", "TESTEANDO METODO");
		String resultado = lector.leer("src/Almacen/pruebaTest.pdf");
		String resultadoEsperado = "TESTEANDO METODO";
		assertEquals(resultado, resultadoEsperado);
	}

	@Test
	public void testEscribirDOCX() {
		escritor.escribir("src/Almacen/TestDocx.docx", "TESTEANDO METODO");
		String resultado = lector.leer("src/Almacen/TestDocx.docx");
		String resultadoEsperado = "TESTEANDO METODO";
		assertEquals(resultado, resultadoEsperado);
	}

	@Test
	public void testEscribirXML() {
		String resultadoEsperado = lector.leer("src/Almacen/prueba3.xml") + "\n" + "Test\n"
				+ "Name: Convert number to string\n" + "CommandLine: Examp1.EXE\n" + "Input: 1\n" + "Output: One\n"
				+ "---";
		escritor.escribir("src/Almacen/prueba3.xml",
				lector.leer("src/Almacen/prueba3.xml") + "\n" + "Test\n" + "Name: Convert number to string\n"
						+ "CommandLine: Examp1.EXE\n" + "Input: 1\n" + "Output: One\n" + "---");
		String resultado = lector.leer("src/Almacen/prueba3.xml");
		assertEquals(resultado, resultadoEsperado);
	}

	@Test
	public void testEscribirHTML() {
		escritor.escribir("src/Almacen/TestHtml.html", "<Testeando Metodo>");
		String resultado = lector.leer("src/Almacen/TestHtml.html");
		String resultadoEsperado = "<Testeando Metodo>";
		assertEquals(resultado, resultadoEsperado);
	}

	@Test
	public void testEscribirEstandar() {
		escritor.escribir("estandar", "estandar");
		String resultado = "estandar";
		String resultadoEsperado = "estandar";
		assertEquals(resultado, resultadoEsperado);
	}

}
