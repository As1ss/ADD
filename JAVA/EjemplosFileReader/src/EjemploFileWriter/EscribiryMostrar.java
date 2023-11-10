package EjemploFileWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class EscribiryMostrar {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		/*
		 * Escribir "Esto es una preuba con FileWritter" en el "fichero1.txt"
		 */
		
		/*
		 * Escribir en el mismo fichero una línea por cada nombre de las provincias de castilla y león
		 */
		String textoAescribir = "Esto es una prueba con FileWritter";
		String [] provincias = {"Burgos",
				"Salamanca","Zamora","Leon","Valladolid","Soria","Palencia","Segovia",
			"Ávila",
		};
		Arrays.sort(provincias);
		
		
		File fich1 = new File("fichero1.txt");
		
		FileWriter writer = new FileWriter(fich1);
		
		
		
		int i=0;
		//writer.write(textoAescribir);
		
		for (int j=0;j<provincias.length;j++) {
			writer.write(provincias[j]);
			writer.write("\n");
		}
		writer.close();

		FileReader reader = new FileReader(fich1);
		
		while((i=reader.read())!=-1) {
		
			System.out.print((char)i);
		}
		
		
	
		reader.close();
	}

}
