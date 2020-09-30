package Biblioteca;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import reto.Lector;

public class TestLector {
	
	Lector lector = new Lector();
	
	@Test
	public void testMainIncorrecto() {
		String ruta = "C:\\Prueba\\prueba7.doc";
		String resultado = lector.main(ruta);
		String resultadoEsperado = "Hola";
		assertNotEquals(resultadoEsperado.trim(), resultado.trim());
	}
	
	@Test
	public void testLeerTeclado() {
		ByteArrayInputStream in = new ByteArrayInputStream("Hola".getBytes());
		System.setIn(in);
		String resultadoEsperado = "Hola";
		String resultado = lector.leerTeclado();
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testLeerDOC() {
		String ruta = "C:\\Prueba\\prueba1.doc";
		String resultado = lector.main(ruta);
		String resultadoEsperado = "Hola";
		assertEquals(resultadoEsperado.trim(), resultado.trim());
	}
	
	@Test
	public void testLeerDOCX() {
		String ruta = "C:\\Prueba\\prueba2.docx";
		String resultado = lector.main(ruta);
		String resultadoEsperado = "Prueba Test";
		assertEquals(resultadoEsperado.trim(), resultado.trim());
	}
	
	@Test
	public void testLeerPDF() {
		String ruta = "C:\\Prueba\\prueba4.pdf";
		String resultado = lector.main(ruta);
		String resultadoEsperado = "Hola";
		assertEquals(resultadoEsperado.trim(), resultado.trim());
	}
	
	@Test
	public void testLeerXML() {
		String ruta = "C:\\Prueba\\prueba3.xml";
		String resultado = lector.main(ruta);
		String resultadoEsperado = "Convert number to string\r\n"
				+ "    Examp1.EXE\r\n"
				+ "    1\r\n"
				+ "    One\r\n"
				+ "  \r\n"
				+ "    Find succeeding characters\r\n"
				+ "    Examp2.EXE\r\n"
				+ "    abc\r\n"
				+ "    def";
		assertEquals(resultadoEsperado.replaceAll("\\s",""), resultado.replaceAll("\\s",""));
	}
}
