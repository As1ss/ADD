package EjemplosCrearModificarFicheros;

import java.io.File;
import java.io.IOException;

public class ModificarFicheros {

	public static void main(String[] args) throws IOException {
		String dirActual = ".";
		String directorioName = "DIRECTORIONUEVO";
		String fichero1Name = "fichero1.txt";
		String fichero2Name = "fichero2.txt";
		
		File directorio = new File(dirActual,directorioName);
		
		directorio.mkdir();

		File fichero1= new File(directorio, fichero1Name);
		File fichero2 = new File(directorio, fichero2Name);
		
		fichero1.createNewFile();
		fichero2.createNewFile();
		
		for (File lista: directorio.listFiles()) {
			System.out.println(lista);
		}
		
		if (fichero2.exists() && fichero2.isFile() && fichero2.canWrite()) {
			File ficheroRenombrado = new File(directorio,"fichnuevo.txt");
			fichero2.renameTo(ficheroRenombrado);
		}
		
		for (File lista: directorio.listFiles()) {
			System.out.println(lista);
		}
		
		
		
		

	}
}
