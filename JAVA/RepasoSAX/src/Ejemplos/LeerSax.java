package Ejemplos;


import java.io.IOException;
import java.util.logging.LogRecord;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class LeerSax {

	public static void main(String[] args) {
		SAXParserFactory saxFactory;
		SAXParser saxParser;
		XMLReader xmlReader;
		SAXHandler handler;
		try {
			saxFactory = SAXParserFactory.newInstance();
			saxParser = saxFactory.newSAXParser();
			xmlReader = saxParser.getXMLReader();
			handler = new SAXHandler();
			xmlReader.setContentHandler(handler);
			xmlReader.parse(new InputSource("D://Empleados.xml"));
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		

	}
	
	
}
 class SAXHandler extends DefaultHandler{

	@Override
	public void startDocument() throws SAXException {
	System.out.println("Inicio del documento");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)
			throws SAXException {
		System.out.print("<"+qName+">");
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String valor = new String(ch,start,length);
		System.out.print(valor);
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.print("</"+qName+">");
	}
	@Override
	public void endDocument() throws SAXException {
		System.out.println("Fin del documento");
	}
	
}
