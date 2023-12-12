package Ejemplos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import EjemploObjectInputOutput.Persona;

public class EscribirObjXStream {

	public static void main(String[] args) {
		File fich;
		ObjectInputStream ois = null;
		List<Persona> personas = new ArrayList<Persona>();
		XStream xStream;
		try {
			fich = new File(
					"C:\\Users\\As1ss\\Desktop\\COSAS CLASE KEK\\DAM2T\\ADD\\JAVA\\EjemplosInputOutputObject\\FichPersona.dat");
			ois = new ObjectInputStream(new FileInputStream(fich));
			personas = new ArrayList<Persona>();
			xStream = new XStream();
			try {
				Persona p;
				while ((p = (Persona) ois.readObject()) != null) {
					personas.add(p);
				}

			} catch (EOFException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				ois.close();
			}

			xStream.alias("Personas", List.class);// Elemento raiz
			xStream.alias("Persona", Persona.class); // Elemento persona

			xStream.aliasField("Nombre", Persona.class, "nombre"); // Renombrar etiqueta de nombre a Nombre
			xStream.aliasField("Edad", Persona.class, "edad"); // Renombrar etiqueta de edad a Edad
			xStream.toXML(personas, new FileOutputStream("D://Personas.xml")); // Escribir la data a un document XML

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
