package Biblioteca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import reto.Comprobar;
import reto.Lector;
import reto.ModificarRuta;

public class TestRutas {
	Comprobar EncontrarArchivo = new Comprobar();
	Lector lector = new Lector();
	ModificarRuta modificarRuta = new ModificarRuta();

	@Test
	public void testMainIncorrecto() {
		String ruta = "src/Almacen/prueba7.doc";
		String resultado = lector.comprobarExtension(ruta);
		String resultadoEsperado = "Prueba incorrecto";
		assertNotEquals(resultadoEsperado, resultado);
	}

	@Test
	public void testmodificarRuta() {
		String ruta = "src/Almacen/Conlinux\\prueba3.xml";
		String resultado = modificarRuta.acortarRuta(ruta);
		String resultadoEsperado = "prueba3.xml";
		assertEquals(resultadoEsperado, resultado);
	}

	@Test
	public void testEncontrarArchivoFalse() {
		String ruta = "src/Almacen/Conlinux/prueba3.xml";
		Boolean resultado = EncontrarArchivo.encontrar(ruta);
		Boolean resultadoEsperado = false;
		assertEquals(resultadoEsperado, resultado);
	}

	@Test
	public void testEncontrarArchivoTrue() {
		String ruta = "src/Almacen/prueba3.xml";
		Boolean resultado = EncontrarArchivo.encontrar(ruta);
		Boolean resultadoEsperado = true;
		assertEquals(resultadoEsperado, resultado);
	}
}
