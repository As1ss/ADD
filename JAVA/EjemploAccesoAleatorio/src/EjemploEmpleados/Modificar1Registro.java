package EjemploEmpleados;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Modificar1Registro {
	final static int TAMAÑOREGISTRO=36;
	final static int TAMAÑOID=4;
	final static int TAMAÑOAPELLIDO=20;

	public static void main(String[] args) throws IOException {
		/*
		 * 
		 * 
		 * Modificar reigstro nº 4 de tal manera que el depto sea 40 y el salario sea
		 * 4000.87
		 *
		 */

		File fich = new File("AleatorioEmple.dat");
		RandomAccessFile raf = new RandomAccessFile(fich, "rw");
		int newDpto, registro, posFichero,posRegistro;
		newDpto = 40;
		registro = 5;
		posFichero = TAMAÑOREGISTRO * registro;
		posRegistro = TAMAÑOID + TAMAÑOAPELLIDO;

		double newSalario = 4000.87;

		raf.seek(posFichero + posRegistro);

		raf.writeInt(newDpto);
		raf.writeDouble(newSalario);
		raf.close();

	}

}
