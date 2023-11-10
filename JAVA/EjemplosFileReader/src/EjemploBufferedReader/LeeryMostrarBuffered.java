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

		
		File fichero1 = new File("fichero1.txt");
		
		BufferedReader reader = new BufferedReader(new FileReader(fichero1));
		
		String temp ;
		
		while((temp=reader.readLine())!=null) {
			System.out.println(temp);
		}
	}

}
