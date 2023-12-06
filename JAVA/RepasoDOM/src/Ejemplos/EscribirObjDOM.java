package Ejemplos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import EjemploObjectInputOutput.Persona;

public class EscribirObjDOM {

	public static void main(String[] args)  {
		File fich;
		ObjectInputStream ois = null;
		DocumentBuilderFactory factory;
		DocumentBuilder builder;
		DOMImplementation implementation;
		Document doc;
		TransformerFactory tFactory;
		Transformer transformer;
		Source source;
		Result result;

		try {
			fich = new File(
					"C:\\Users\\As1ss\\Desktop\\COSAS CLASE KEK\\DAM2T\\ADD\\JAVA\\EjemplosInputOutputObject\\FichPersona.dat");
			ois = new ObjectInputStream(new FileInputStream(fich));
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			implementation = builder.getDOMImplementation();
			doc = implementation.createDocument(null, "Alumnos", null);

			Persona p;
			
			try {
				while ((p = (Persona) ois.readObject()) != null) {
					
					Element alumno = doc.createElement("Alumno");
					doc.getDocumentElement().appendChild(alumno);
					
					Element nombre = doc.createElement("Nombre");
					nombre.appendChild(doc.createTextNode(p.getNombre()));
					alumno.appendChild(nombre);

					Element edad = doc.createElement("Edad");
					edad.appendChild(doc.createTextNode(String.valueOf(p.getEdad())));
					alumno.appendChild(edad);

				}
			} catch (EOFException e) {
				
			}
		
			
			
			source = new DOMSource(doc);
			result = new StreamResult(new File("D://Alumnos.xml"));
			
			tFactory = TransformerFactory.newInstance();
			
			transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			
			transformer.transform(source, result);

		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
