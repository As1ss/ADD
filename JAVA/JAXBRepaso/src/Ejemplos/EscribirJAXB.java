package Ejemplos;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;



public class EscribirJAXB {

	public static void main(String[] args) {
		Libro l1 = new Libro("Tu mama me la mama","By Asis","Telas Films","12931939123-23");
		Libro l2 = new Libro("Yo que se bruh","By bruh","BRUhHahh","38819782-3287");
		ArrayList<Libro> listaLibros = new ArrayList<>();
		listaLibros.add(l1);
		listaLibros.add(l2);
		Libreria libreria1 = new Libreria(listaLibros,"FreeBeeria","Burgatti");
		
		try {
			JAXBContext context = JAXBContext.newInstance(Libreria.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(libreria1, System.out);
			m.marshal(libreria1, new File("D://Libreria.xml"));
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
