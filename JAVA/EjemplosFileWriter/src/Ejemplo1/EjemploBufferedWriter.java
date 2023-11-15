package Ejemplo1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class EjemploBufferedWriter {

	public static void main(String[] args) {

		try {
			BufferedWriter writer = null;
			File fich = new File("fichero10lineas.txt");
			fich.createNewFile();
			writer = new BufferedWriter(new FileWriter(fich));
			for (int i = 1; i < 11; i++) {
				writer.write("Fila número: " + i + "\n");
			}
			writer.close();
		} catch (FileNotFoundException p) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			System.out.println("Problema de entrada/salida");
		}

	}

}
