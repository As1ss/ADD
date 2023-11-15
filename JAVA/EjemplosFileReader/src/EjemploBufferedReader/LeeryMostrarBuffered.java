package EjemploBufferedReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LeeryMostrarBuffered {

	public static void main(String[] args) throws IOException {
		/*Escribir un programilla que lea las lineas del fichero1.txt con Buffered Reader
		 * 
		 */

		
		//Creamos un objeto de tipo File localizando el fichero1
		File fichero1 = new File("fichero1.txt");
		
		//Instanciamos un obeto de la clase BufferedReader que en su interior se instancia un 
		// FileReader para poder leer el fichero1.txt
		BufferedReader reader = new BufferedReader(new FileReader(fichero1));
		
		String line ;
		
		//Inicializamos la variable temp e iteramos agregandole linea por linea el contenido
		//del texto
		while((line=reader.readLine())!=null) {
			System.out.println(line);
		}
		reader.close();
	}

}
