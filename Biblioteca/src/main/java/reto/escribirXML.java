package reto;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class escribirXML {

	public void escribirXML(String ruta, String contenido) {
		File error = new File("src/Errores/errores.txt");
		Errores log = new Errores();
		String[] texto = contenido.split("\n");
		File fichero = new File(ruta);
		String nodo = texto[0];
		Boolean contados = false;
		int cont = 0;
		for (int i = 1; i < texto.length && !contados; i++) {
			if (texto[i].equals("---")) {
				contados = true;
			}
		}

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(fichero);
			int nodos = 0;

			for (int i = 0; i < texto.length; i++) {
				if (texto[i].equals("---")) {

				} else if (texto[i].equals(nodo)) {
					texto[i] = "---";
					nodos++;
				} else {
					texto[i] = texto[i].split(":")[1].trim();
				}
			}
			cont = 1;

			for (int i = 0; i < nodos; i++) {
				aniadirNodos(nodos, nodo, doc, i);
				cont = reemplazarTexto(nodos, texto, doc, nodo, i, cont);
			}

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Result output = new StreamResult(fichero);
			Source input = new DOMSource(doc);
			transformer.transform(input, output);

		} catch (ParserConfigurationException | SAXException | IOException | TransformerFactoryConfigurationError
				| TransformerException e) {
			e.printStackTrace();
			System.out.println("Ha ocurrido un error al escribir en el XML");
			log.logger.warning("Ha ocurrido un error al escribir en el XML" + e.getMessage());
		}
	}

	public void aniadirNodos(int numNodos, String nomNodo, Document doc, int contBucle) {
		if (numNodos > doc.getElementsByTagName(nomNodo).getLength()) {
			for (int j = 0; j < numNodos - doc.getElementsByTagName(nomNodo).getLength(); j++) {
				Node nuevoNodo = doc.createElement(nomNodo);
				for (int k = 0; k < doc.getElementsByTagName(nomNodo).item(0).getChildNodes().getLength(); k++) {
					Node nodoHijo = doc.createElement(
							doc.getElementsByTagName(nomNodo).item(0).getChildNodes().item(k).getNodeName());
					nuevoNodo.appendChild(nodoHijo);
				}
				doc.getElementsByTagName(nomNodo).item(contBucle).getParentNode().appendChild(nuevoNodo);
			}
		}
	}

	public int reemplazarTexto(int numNodos, String[] texto, Document doc, String nomNodo, int contBucle, int cont) {
		for (int j = 0; j < doc.getElementsByTagName(nomNodo).item(contBucle).getChildNodes().getLength(); j++) {
			while (texto[cont].equals("---") || texto[cont].equals(nomNodo)) {
				cont++;
			}
			Element elemento = (Element) doc.getElementsByTagName(nomNodo).item(contBucle).getChildNodes().item(j);
			elemento.setTextContent(texto[cont]);
			cont++;
		}
		return cont;
	}
}
