package Ejemplos;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LeerDOM {

	public static void main(String[] args) {
		DocumentBuilderFactory factory;
		DocumentBuilder builder;
		Document doc;
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			doc = builder.parse(new File("D://Empleados.xml"));
			
			NodeList empleados= doc.getElementsByTagName("Empleado");
			
			
			for (int i =0;i<empleados.getLength();i++) {
				Node empleado = empleados.item(i);
				Element element = (Element)empleado;
				System.out.println("ID: "+element.getElementsByTagName("ID").item(0).getTextContent());
				System.out.println("Apellidos: "+element.getElementsByTagName("Apellidos").item(0).getTextContent());
				System.out.println("Departamento: "+element.getElementsByTagName("Departamento").item(0).getTextContent());
				System.out.println("Salario: "+element.getElementsByTagName("Salario").item(0).getTextContent());
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
