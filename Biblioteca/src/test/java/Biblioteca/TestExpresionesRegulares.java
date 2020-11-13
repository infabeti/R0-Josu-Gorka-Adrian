package Biblioteca;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import reto.ExpresionesRegulares;
import reto.ValidarTexto;

public class TestExpresionesRegulares {

	ExpresionesRegulares expresionReg = new ExpresionesRegulares();

	@Test
	public void testValidarTextoDOCXFalse() {
		Boolean resultado;
		ValidarTexto validar = new ValidarTexto("src/Almacen/TestDocx.docx", "TESTEANDO METODO");
		resultado = validar.aplicarExpresion();
		assertFalse(resultado);
	}

	@Test
	public void testValidarTextoDOCXTrue() {
		Boolean resultado;
		ValidarTexto validar = new ValidarTexto("src/Almacen/TestDocx.docx", "TESTEANDO METODO@|");
		resultado = validar.aplicarExpresion();
		assertTrue(resultado);
	}

	@Test
	public void testValidarTextoPDFFalse() {
		Boolean resultado;
		ValidarTexto validar = new ValidarTexto("src/Almacen/pruebaTest.pdf", "TESTEANDO METODO");
		resultado = validar.aplicarExpresion();
		assertFalse(resultado);
	}

	@Test
	public void testValidarTextoPDFTrue() {
		Boolean resultado;
		ValidarTexto validar = new ValidarTexto("src/Almacen/pruebaTest.pdf", "TESTEANDO METODO@|");
		resultado = validar.aplicarExpresion();
		assertTrue(resultado);
	}

	@Test
	public void testValidarTextoXMLFalse() {
		Boolean resultado;
		ValidarTexto validar = new ValidarTexto("src/Almacen/TestLeerXML.xml",
				"Book\n" + "Author: Garghentini, Davide\n" + "---");
		resultado = validar.aplicarExpresion();
		assertFalse(resultado);
	}

	@Test
	public void testValidarTextoXMLTrue() {
		Boolean resultado;
		ValidarTexto validar = new ValidarTexto("src/Almacen/TestLeerXML.xml",
				"Book\n" + "Author: Garghentini,< Davide\n" + "---");
		resultado = validar.aplicarExpresion();
		assertTrue(resultado);
	}

	@Test
	public void testValidarTextoHTMLFalse() {
		Boolean resultado;
		ValidarTexto validar = new ValidarTexto("src/Almacen/prueba.html", "<Prueba HTML>");
		resultado = validar.aplicarExpresion();
		assertFalse(resultado);
	}

	@Test
	public void testValidarTextoHTMLTrue() {
		Boolean resultado;
		ValidarTexto validar = new ValidarTexto("src/Almacen/prueba.html", "Texto <Testeando Metodo áéíóúÁÉÍÓÚ>");
		resultado = validar.aplicarExpresion();
		assertTrue(resultado);
	}

	@Test
	public void testValidarTextoIncorrecto() {
		Boolean resultado;
		ValidarTexto validar = new ValidarTexto("src/Almacen/DiagramaSprint1.dia", "Hola");
		resultado = validar.aplicarExpresion();
		assertFalse(resultado);
	}

}
