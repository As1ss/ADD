package Ejercicio;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBContextFactory;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class EscribirBiblioteca {

	public static void main(String[] args) throws JAXBException {
		// Libros
		Libro l1 = new Libro("El Señor de los Anillos", "J.R.R. Tolkien", "Minotauro", "9788445071409");
		Libro l2 = new Libro("Cien años de soledad", "Gabriel García Márquez", "Cien años de soledad", "9786073136194");
		Libro l3 = new Libro("Harry Potter y la piedra filosofal", "J.K. Rowling", "Salamandra", "9788478884459");
		Libro l4 = new Libro("1984", "George Orwell", "Debolsillo", "9788466332239");
		Libro l5 = new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "Real Academia Española",
				"9788420607384");

		// Librerias
		Libreria lib1 = new Libreria();
		Libreria lib2 = new Libreria();

		// Biblioteca
		Biblioteca biblioteca = new Biblioteca();

		// Añadido de libros a coleccion Arraylist
		ArrayList<Libro> listaLibros1 = new ArrayList<>();
		listaLibros1.add(l1);
		listaLibros1.add(l2);

		ArrayList<Libro> listaLibros2 = new ArrayList<>();
		listaLibros2.add(l3);
		listaLibros2.add(l4);
		listaLibros2.add(l5);

		// Añadido de coleccion a librerias
		lib1.setListaLibros(listaLibros1);
		lib1.setNombre("Libreria 1");
		lib2.setListaLibros(listaLibros2);
		lib2.setNombre("Libreria 2");

		// Añadido de librerias a un ArrayList
		ArrayList<Libreria> librerias = new ArrayList<>();
		librerias.add(lib1);
		librerias.add(lib2);

		// Añadido de coleccion a Biblioteca
		biblioteca.setLibreria(librerias);

		// Creacion de JAXB y marshall para escribir en un fichero XML los objetos

		JAXBContext context = JAXBContext.newInstance(Biblioteca.class);// Elemento raiz

		Marshaller m = context.createMarshaller();

		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		m.marshal(biblioteca, new File("D://Biblioteca.xml"));

	}

}
