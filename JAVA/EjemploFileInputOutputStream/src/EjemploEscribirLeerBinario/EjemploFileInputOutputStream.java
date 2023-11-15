package EjemploEscribirLeerBinario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class EjemploFileInputOutputStream {

	public static void main(String[] args) {
		/*
		 * Un programilla java que escriba del 1 al 100 en un fichero llamado 
		 * "FichBytes.dat" y que los visualice por consola.
		 */
		int opcion;
		try {
			File fich = new File("FichBytes.dat");
			fich.createNewFile();
			FileOutputStream writer = new FileOutputStream(fich);
			FileInputStream reader = new FileInputStream(fich);
			
			
			String frase;
			

			for(int i = 1;i<101;i++) {
				writer.write(i);	
			}
			
			while((opcion=reader.read()) !=-1) {
				System.out.println(String.valueOf(opcion));
			}

			reader.close();
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
