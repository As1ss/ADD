package Ejemplos;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class LecturaJAXB {

	public static void main(String[] args) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(Libreria.class);
		Unmarshaller um = context.createUnmarshaller();
		Libreria libreria = (Libreria) um.unmarshal(new File("D://Libreria.xml"));

		System.out.println("Libreria :" + libreria.getNombre());
		System.out.println("Lugar: " + libreria.getLugar());
		ArrayList<Libro> listaLibros = libreria.getListaLibros();
		System.out.println("Libros: ");

		for (Libro libro : listaLibros) {
			System.out.println("Titulo: " + libro.getTitulo());
			System.out.println("Autor: " + libro.getAutor());
			System.out.println("Editorial: " + libro.getEditorial());
			System.out.println("ISBN: " + libro.getIsbn());
		}
	}
	

}
