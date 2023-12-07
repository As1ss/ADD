package Ejemplos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TransformarXMLconXSL {

	public static void main(String[] args) {
		
		String dataPath = "C:\\Users\\As1ss\\Downloads\\alumnos.xml";
		String stylePath = "C:\\Users\\As1ss\\Downloads\\alumnosPlantilla.xsl"; //La estructura del xsl tiene que coincidir con la del xml de la data
		File ficheroHTML = new File("D://Mipagina.html");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(ficheroHTML);
			Source sourceData = new StreamSource(dataPath);
			Source sourceStyle = new StreamSource(stylePath);
			Result result = new StreamResult(fos);
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer(sourceStyle);
			transformer.transform(sourceData, result);
			
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		

	}

}
