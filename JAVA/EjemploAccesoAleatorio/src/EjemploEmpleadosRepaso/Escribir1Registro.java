package EjemploEmpleadosRepaso;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Escribir1Registro {
	final static int TAMAÑOREGISTRO=36;

	public static void main(String[] args) throws IOException{
		File fich = new File("AleatorioEmplea2.dat");
		RandomAccessFile raf = new RandomAccessFile(fich,"rw");
		int id,dpto,posicion;
		String apellido;
		double salario;
		StringBuffer buffer;
		id = 8;
		dpto=40;
		apellido = "GONZALEZ";
		salario = 2044.16;
		buffer = new StringBuffer(apellido);
		buffer.setLength(10);
		posicion=(id-1)*TAMAÑOREGISTRO;
		raf.seek(posicion);
		
		raf.writeInt(id);
		raf.writeChars(buffer.toString());
		raf.writeInt(dpto);
		raf.writeDouble(salario);
		raf.close();
		

	}

}
