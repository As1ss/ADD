package EjemploFileReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LeeryMostrar {

	public static void main(String[] args) throws IOException {
		/*
		 * Crear un fichero de texto con contenido de nombre "fichero1.txt", meter
		 * contenido y crear un programa java para mostrar el contenido por consola.
		 */

		String nombreFich = "fichero1.txt";
		File ficher1 = new File(nombreFich);
		char[] buf = new char[20];
		FileReader reader = new FileReader(ficher1);
		int i;
		while((i=reader.read(buf))!=-1) {
	
			System.out.print(buf);
		}
		reader.close();
	}

	
}
