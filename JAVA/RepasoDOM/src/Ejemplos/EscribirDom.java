package Ejemplos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

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

public class EscribirDom {

	public static void main(String[] args) throws IOException {
		File fich = new File(
				"C:\\Users\\As1ss\\Desktop\\COSAS CLASE KEK\\DAM2T\\ADD\\JAVA\\EjemploAccesoAleatorio\\AleatorioEmplea2.dat");
		RandomAccessFile raf = null;
		DocumentBuilderFactory factory;
		DocumentBuilder builder;
		DOMImplementation implementation;
		Document doc = null;
		int id = 0, dpto = 0;
		char[] apellidos = new char[10];
		char aux;
		String apellido = null;
		double salario = 0;

		try {

			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			implementation = builder.getDOMImplementation();
			doc = implementation.createDocument(null, "Empleados", null);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		try {
			raf = new RandomAccessFile(fich, "r");
			while (true) {
				id = raf.readInt();
				for (int i = 0; i < apellidos.length; i++) {
					aux = raf.readChar();
					apellidos[i] = aux;
				}
				apellido = new String(apellidos);
				dpto = raf.readInt();
				salario = raf.readDouble();

				if (raf.getFilePointer() == fich.length()) {
					break;
				}

				if(id>0) {
					
					Element empleado = doc.createElement("Empleado");
					doc.getDocumentElement().appendChild(empleado);

					Element idElement = doc.createElement("ID");
					idElement.appendChild(doc.createTextNode(String.valueOf(id)));
					empleado.appendChild(idElement);

					Element apellidosElement = doc.createElement("Apellidos");
					apellidosElement.appendChild(doc.createTextNode(apellido.trim()));
					empleado.appendChild(apellidosElement);

					Element dptoElement = doc.createElement("Departamento");
					dptoElement.appendChild(doc.createTextNode(String.valueOf(dpto)));
					empleado.appendChild(dptoElement);

					Element salarioElement = doc.createElement("Salario");
					salarioElement.appendChild(doc.createTextNode(String.valueOf(salario)));
					empleado.appendChild(salarioElement);
				}
			

			}
			Source source = new DOMSource(doc);
			Result result = new StreamResult(new File("D://Empleados.xml"));
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} finally {
			raf.close();
		}

	}

}
