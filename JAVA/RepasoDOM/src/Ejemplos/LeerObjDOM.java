package Ejemplos;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LeerObjDOM {

	public static void main(String[] args) {
		DocumentBuilderFactory factory;
		DocumentBuilder builder;
		Document doc;

		
			try {
				factory = DocumentBuilderFactory.newInstance();
				builder = factory.newDocumentBuilder();
				doc = builder.parse(new File("D://Alumnos.xml"));
				NodeList alumnos = doc.getElementsByTagName("Alumno");

				System.out.println(doc.getFirstChild().getNodeName());
				for (int i = 0; i < alumnos.getLength(); i++) {
					Node alumno = alumnos.item(i);
					Element element = (Element) alumno;
					System.out.println("Nombre: "+element.getElementsByTagName("Nombre").item(0).getTextContent());
					System.out.println("Edad: "+element.getElementsByTagName("Edad").item(0).getTextContent());
					System.out.println();
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			


	}

}
