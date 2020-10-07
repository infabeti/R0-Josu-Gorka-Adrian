package Biblioteca;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Test;

import reto.Lector;

public class TestLector {
	
	Lector lector = new Lector();
	
	@Test
	public void testMainIncorrecto() {
		String ruta = "src/Almacen/prueba7.doc";
		String resultado = lector.leerExtension(ruta);
		String resultadoEsperado = "Prueba incorrecto";
		assertNotEquals(resultadoEsperado, resultado);
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
		String ruta = "src/Almacen/prueba1.doc";
		String resultado = lector.leerExtension(ruta);
		String resultadoEsperado = "Prueba DOC";
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testLeerDOCX() {
		String ruta = "src/Almacen/prueba2.docx";
		String resultado = lector.leerExtension(ruta);
		String resultadoEsperado = "Prueba DOCX";
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void testLeerPDF() {
		String ruta = "src/Almacen/prueba4.pdf";
		String resultado = lector.leerExtension(ruta);
		String resultadoEsperado = "Prueba PDF";
		assertEquals(resultadoEsperado, resultado);
	}

	@Test
	public void testLeerXML() {
		String ruta = "src/Almacen/prueba3.xml";
		String resultado = lector.leerExtension(ruta);
		String resultadoEsperado = "Convert number to string\r\n"
				+ "Examp1.EXE\r\n"
				+ "1\r\n"
				+ "One\r\n"
				+ "\r\n"
				+ "Find succeeding characters\r\n"
				+ "Examp2.EXE\r\n"
				+ "abc\r\n"
				+ "def";
		assertEquals(resultadoEsperado, resultado);
	}
}
