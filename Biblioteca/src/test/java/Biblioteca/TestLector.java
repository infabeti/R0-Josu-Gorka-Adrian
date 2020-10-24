package Biblioteca;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import reto.Lector;

public class TestLector {

	Lector lector = new Lector();

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
		String resultado = lector.leer(ruta);
		String resultadoEsperado = "Prueba DOC";
		assertEquals(resultadoEsperado, resultado);
	}

	@Test
	public void testLeerDOCX() {
		String ruta = "src/Almacen/prueba2.docx";
		String resultado = lector.leer(ruta);
		String resultadoEsperado = "Prueba docx";
		assertEquals(resultadoEsperado, resultado);
	}

	@Test
	public void testLeerPDF() {
		String ruta = "src/Almacen/prueba4.pdf";
		String resultado = lector.leer(ruta);
		String resultadoEsperado = "HOLA BUENAS";
		assertEquals(resultadoEsperado, resultado);
	}

	@Test
	public void testLeerXML() {
		String ruta = "src/Almacen/TestLeerXML.xml";
		String resultado = lector.leer(ruta);
		String resultadoEsperado = "Book\n" + "Author: Garghentini, Davide\n" + "---";
		assertEquals(resultadoEsperado, resultado);
	}

	@Test
	public void testLeerHTML() {
		String ruta = "src/Almacen/prueba.html";
		String resultado = lector.leer(ruta);
		String resultadoEsperado = "<Prueba HTML>";
		assertEquals(resultadoEsperado, resultado);
	}

	@Test
	public void testLeerNoValido() {
		String ruta = "src/Almacen/DiagramaSprint1.dia";
		String resultado = lector.leer(ruta);
		String resultadoEsperado = "Tipo de Archivo no valido";
		assertEquals(resultado, resultadoEsperado);
	}
}
