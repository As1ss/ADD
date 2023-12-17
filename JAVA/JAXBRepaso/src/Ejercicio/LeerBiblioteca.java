package Ejercicio;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class LeerBiblioteca {

	public static void main(String[] args) throws JAXBException {
		
		//Creamos el context y el unmarshall
		JAXBContext context = JAXBContext.newInstance(Biblioteca.class);
		Unmarshaller um = context.createUnmarshaller();
		
		
		//Creamos un objeto de tipo Biblioteca el cual contendra todas las librerias mediante el metodo um.unmarshal();
		Biblioteca biblioteca = (Biblioteca)um.unmarshal(new File("D://Biblioteca.xml"));
		
		//Una vez obtenido la biblioteca obenemos las librerias
		ArrayList<Libreria> librerias = biblioteca.getLibreria();
		
		
		//Iteramos las librerias y obtenemos el ArrayList<>() de cada libreria
		for (Libreria lib:librerias) {
			ArrayList<Libro> l = lib.getListaLibros();
			System.out.println(lib.getNombre());
			System.out.println("-----------------------------------");
			//Una vez obtenidas la libreria obtenemos los libros para mostrar sus atributos
			for(Libro libro : l) {
				System.out.println("Titulo: "+libro.getTitulo());
				System.out.println("Autor: "+libro.getAutor());
				System.out.println("Editorial: "+libro.getEditorial());
				System.out.println("ISBN: "+libro.getIsbn());
				System.out.println("--------------------------");
			}	
		}		
	}
}
