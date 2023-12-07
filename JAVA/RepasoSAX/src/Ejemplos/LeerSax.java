package Ejemplos;



import java.io.IOException;
import java.util.logging.Handler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;


public class LeerSax {

	public static void main(String[] args) {
		try {
			// Crear objeto procesador de XML
						SAXParserFactory parserFactory = SAXParserFactory.newInstance();
						SAXParser parser = parserFactory.newSAXParser();
						XMLReader procesadorXML = parser.getXMLReader();
						
						
						GestionContenido gestor = new GestionContenido();
						procesadorXML.setContentHandler(gestor);
						
						// Definir el fichero XML a leer
						InputSource fileXML = new InputSource("D://Empleados.xml");
						
						// Procesar el documento XML
						procesadorXML.parse(fileXML);  

		}
		
		catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
} // Fin PruebaSax1

class GestionContenido extends DefaultHandler {
	public GestionContenido() {
		super();
	}

	public void startDocument() {
		System.out.println("Comienzo del Documento XML");
	}

	public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
		System.out.printf("\t Principio del Elemento: %s %n", nombreC);
	}

	public void characters(char[] ch, int inicio, int longitud) throws SAXException {
		String car = new String(ch, inicio, longitud);
		// Quitar saltos de línea
		car = car.replaceAll("[\t\n]", "");
		System.out.printf("\t Caracteres: %s %n", car);
	}

	public void endElement(String uri, String nombre, String nombreC) {
		System.out.printf("\t Fin del Elemento: %s %n", nombreC);
	}

	public void endDocument() {
		System.out.println("Final del Documento XML");
	}
}
