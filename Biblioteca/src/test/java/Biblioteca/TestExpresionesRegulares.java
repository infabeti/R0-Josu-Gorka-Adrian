package Biblioteca;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import reto.ExpresionesRegulares;

public class TestExpresionesRegulares {

	ExpresionesRegulares expresionReg = new ExpresionesRegulares();

	@Test
	public void testEncontrarCaracterTrue() {
		String patron = "<>";
		String texto = "<Book>";

		boolean resultadoEsperado = expresionReg.encontrarCaracter(patron, texto);

		assertTrue(resultadoEsperado);
	}

	@Test
	public void testEncontrarCaracterFalse() {
		String patron = "<>";
		String texto = "Book";

		boolean resultadoEsperado = expresionReg.encontrarCaracter(patron, texto);

		assertFalse(resultadoEsperado);
	}

	@Test
	public void testExpresiones_HTMLTrue() {
		String texto = "<dír>";

		boolean resultadoEsperado = expresionReg.Expresiones_Html(texto);

		assertTrue(resultadoEsperado);

	}

	@Test
	public void testExpresiones_HTMLFalse() {
		String texto = "<dir>";

		boolean resultadoEsperado = expresionReg.Expresiones_Html(texto);

		assertFalse(resultadoEsperado);

	}

}
