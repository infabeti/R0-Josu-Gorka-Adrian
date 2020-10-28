package Biblioteca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import reto.Lector;
import reto.ModificarRuta;

public class TestRutas {

	Lector lector = new Lector();
	ModificarRuta modificarRuta = new ModificarRuta();

	@Test
	public void testMainIncorrecto() {
		String ruta = "src/Almacen/prueba7.doc";
		String resultado = lector.leer(ruta);
		String resultadoEsperado = "Prueba incorrecto";
		assertNotEquals(resultadoEsperado, resultado);
	}

	@Test
	public void testmodificarRuta() {
		String ruta = "src/Almacen/Conlinux\\prueba3.xml";
		String resultado = modificarRuta.modificarRuta(ruta);
		String resultadoEsperado = "prueba3.xml";
		assertEquals(resultadoEsperado, resultado);
	}
}
