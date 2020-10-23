package Biblioteca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import reto.Escritor;
import reto.Lector;

public class TestLector {

	Lector lector = new Lector();
	Escritor escritor = new Escritor();

	@Test
	public void testMainIncorrecto() {
		String ruta = "src/Almacen/prueba7.doc";
		String resultado = lector.leer(ruta);
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
		String resultado = lector.leer(ruta);
		String resultadoEsperado = "Prueba DOC";
		assertEquals(resultadoEsperado, resultado);
	}

	@Test
	public void testLeerDOCX() {
		String ruta = "src/Almacen/prueba2.docx";
		String resultado = lector.leer(ruta);
		String resultadoEsperado = "Prueba DOCX";
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
		String ruta = "src/Almacen/Libros.xml";
		String resultado = lector.leer(ruta);
		String resultadoEsperado = "Book\n" + "Author: Garghentini, Davide\n" + "Title: XML Developer's Guide\n"
				+ "Genre: Computer\n" + "Price: 44.95\n" + "PublishDate: 2000-10-01\n"
				+ "Description: An in-depth look at creating applications with XML.\n" + "---\n" + "Book\n"
				+ "Author: Garcia, Debra\n" + "Title: Midnight Rain\n" + "Genre: Fantasy\n" + "Price: 5.95\n"
				+ "PublishDate: 2000-12-16\n"
				+ "Description: A former architect battles corporate zombies, an evil sorceress, and her own childhood to become queen of the world.\n"
				+ "---";
		assertEquals(resultadoEsperado, resultado);
	}

	@Test
	public void testLeerHTML() {
		String ruta = "src/Almacen/prueba.html";
		String resultado = lector.leer(ruta);
		String resultadoEsperado = "Prueba HTML";
		assertEquals(resultadoEsperado, resultado);
	}

	@Test
	public void testLeerNoValido() {
		String ruta = "src/Almacen/DiagramaSprint1.dia";
		String resultado = lector.leer(ruta);
		String resultadoEsperado = "Tipo de Archivo no valido";
		assertEquals(resultado, resultadoEsperado);
	}

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
		escritor.escribir("src/Almacen/TestHtml.html", "Testeando Metodo");
		String resultado = lector.leer("src/Almacen/TestHtml.html");
		String resultadoEsperado = "Testeando Metodo";
		assertEquals(resultado, resultadoEsperado);
	}

	@Test
	public void testEscribirEstandar() {
		escritor.escribir("estandar", "estandar");
		String resultado = "estandar";
		String resultadoEsperado = "estandar";
		assertEquals(resultado, resultadoEsperado);
	}

	@Test
	public void testmodificarRuta() {
		String ruta = "src/Almacen/Conlinux\\prueba3.xml";
		String resultado = lector.modificarRuta(ruta);
		String resultadoEsperado = "prueba3.xml";
		assertEquals(resultadoEsperado, resultado);
	}

}
