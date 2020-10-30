package reto;

public class ModificarRuta {

	public String acortarRuta(String Ruta) {
		String ruta[] = null;
		String Nombre;
		String rutamodificada = null;
		rutamodificada = Ruta.replace("\\", ",").replace("/", ",");
		ruta = rutamodificada.split(",");
		Nombre = ruta[ruta.length - 1].toString();
		return Nombre;

	}
}
