package Ejemplos;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class EscribirEmpleadoXML {
	final static int TAMAÑOREGISTRO = 36;

	public static void main(String[] args) throws IOException, ParserConfigurationException {
		File fichero = new File("D:\\DAM2T\\ADD\\JAVA\\EjemploAccesoAleatorio\\AleatorioEmple.dat");
		RandomAccessFile raf = new RandomAccessFile(fichero, "r");
		int id, dpto, posicion;
		char[] apellidos = new char[10];
		char aux;
		String apellido;
		double salario;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation implementation = builder.getDOMImplementation();
		Document doc = implementation.createDocument(null, "Empleados", null);
		doc.setXmlVersion("1.0");







		posicion = 0;
		while (true) {
			raf.seek(posicion);
			id = raf.readInt();
			for (int i = 0; i < apellidos.length; i++) {
				aux = raf.readChar();
				apellidos[i] = aux;

			}
			apellido = new String(apellidos);
			dpto = raf.readInt();
			salario = raf.readDouble();



			Element empleadoNodo = doc.createElement("Empleado");
			doc.getDocumentElement().appendChild(empleadoNodo);

            Element idNodo = doc.createElement("ID");
            idNodo.appendChild(doc.createTextNode(String.valueOf(id).trim()));
            empleadoNodo.appendChild(idNodo);

            Element apellidoNodo = doc.createElement("Apellidos");
            apellidoNodo.appendChild(doc.createTextNode(apellido.trim()));
            empleadoNodo.appendChild(apellidoNodo);

            Element dptoNodo = doc.createElement("Departamento");
            dptoNodo.appendChild(doc.createTextNode(String.valueOf(dpto).trim()));
            empleadoNodo.appendChild(dptoNodo);

            Element salarioNodo = doc.createElement("Salario");
            salarioNodo.appendChild(doc.createTextNode(String.valueOf(salario).trim()));
            empleadoNodo.appendChild(salarioNodo);




			if (id>0) {
				System.out.println("ID: "+id);
				System.out.println("Apellidos: "+apellido);
				System.out.println("Departamento; "+dpto);
				System.out.println("Salario: "+salario);
			}



			posicion += TAMAÑOREGISTRO;

			if (raf.getFilePointer() == fichero.length()) {
				System.out.println("Fin del documento");
				break;
			}

		}



		try {
			Source source = new DOMSource(doc);
			Result result = new StreamResult((new File ("D:\\Empleados.xml")));
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			transformer.transform(source, result);



		} catch (Exception e) {

		}



		raf.close();
	}

}
