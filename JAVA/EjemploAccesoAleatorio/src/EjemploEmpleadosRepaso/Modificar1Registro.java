package EjemploEmpleadosRepaso;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Modificar1Registro {
	final static int TAMAÑOREGISTRO=36;
	final static int TAMAÑOID=4;
	final static int TAMAÑOAPELLIDO=20;

	/*
	 * 
	 * 
	 * Modificar reigstro nº 4 de tal manera que el depto sea 40 y el salario sea
	 * 4000.87
	 *
	 */
	public static void main(String[] args)throws IOException {
		File fich = new File("AleatorioEmplea2.dat");
		RandomAccessFile raf = new RandomAccessFile(fich,"rw");
		int registro,dpto,posicion;
		double salario;
		registro=4;
		dpto=40;
		salario=4000.87;
		posicion=((registro-1)*TAMAÑOREGISTRO)+(TAMAÑOID+TAMAÑOAPELLIDO);
		
		raf.seek(posicion);
		
		raf.writeInt(dpto);
		raf.writeDouble(salario);
		raf.close();
		

	}

}
