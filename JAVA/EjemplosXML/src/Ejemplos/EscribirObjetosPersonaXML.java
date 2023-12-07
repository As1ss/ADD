package Ejemplos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import EjemploObjectInputOutput.*;


public class EscribirObjetosPersonaXML {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParserConfigurationException {

		File fich = new File("D:\\DAM2T\\ADD\\JAVA\\EjemplosInputOutputObject\\FichPersona.dat");
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich));
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation implementation = builder.getDOMImplementation();
		Document doc = implementation.createDocument(null,"Personas", null);
		doc.setXmlVersion("1.0");
		
		

		try {
			Persona p;
			while ((p = (Persona)ois.readObject()) != null) {
				Element personaNodo = doc.createElement("Persona");
				doc.getDocumentElement().appendChild(personaNodo);
				
				Element nombreNodo = doc.createElement("Nombre");
				nombreNodo.appendChild(doc.createTextNode(p.getNombre()));
				personaNodo.appendChild(nombreNodo);
				
				Element edadNodo = doc.createElement("Edad");
				edadNodo.appendChild(doc.createTextNode(String.valueOf(p.getEdad())));
				personaNodo.appendChild(edadNodo);
				
				
				
			}
		} catch (EOFException e) {
			System.out.println("Fin del documento");
		} catch (Exception e) {
			e.printStackTrace(); // Manejo de otras excepciones
		} finally {
			ois.close();
		}

	}
}
