package reto;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class leerXML {
	File error = new File("src/Errores/errores.txt");
	Errores log = new Errores();

	public String leerXML(String ruta) {
		String entrada = "";
		int num;
		Document doc;
		Node ntemp;
		File fichero = new File(ruta);
		try {
			if (!error.exists()) {
				error.createNewFile();
			}
			PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(error, true)), true);
			System.setErr(ps);

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(fichero);
			Node raiz = doc.getFirstChild();
			NodeList nodeList = raiz.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				ntemp = nodeList.item(i);
				if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
					entrada += ntemp.getNodeName() + "\n";
					for (int j = 0; j < ntemp.getChildNodes().getLength(); j++) {
						entrada += ntemp.getChildNodes().item(j).getNodeName() + ": "
								+ ntemp.getChildNodes().item(j).getTextContent() + "\n";
					}
					entrada += "---\n";
				}
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println("Ha ocurrido un error al leer el XML");
			log.logger.warning("Fallo en el metodo leerXML al intentar leer el XML");
		}

		num = entrada.split("\n").length;
		String[] lineas = new String[num];
		lineas = entrada.split("\r\n");
		entrada = "";

		for (int i = 0; i < lineas.length; i++) {
			lineas[i] = lineas[i].trim();
			entrada = entrada + lineas[i] + "\r\n";
		}

		return entrada.trim();
	}
}
