package reto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;

import javax.swing.JTextField;

public class Propietario {
	private JTextField textPropietario;

	public Propietario(JTextField textPropietario) {
		this.textPropietario = textPropietario;
	}

	public void BuscarPropietario(String ruta) {
		try {
			Path path = Paths.get(ruta);
			UserPrincipal propietario;
			propietario = Files.getOwner(path);
			String username = propietario.getName();
			textPropietario.setText(username);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void AgregarPropietario(String ruta, String Propietario) throws IOException, InterruptedException {
		String Comando = "chown " + Propietario + " " + ruta;
		Process pb = Runtime.getRuntime().exec(Comando);
		pb.waitFor();
	}

}
