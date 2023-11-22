package EjemploEmpleados;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Escribir1Registro {
	final static int TAMAÑOREGISTRO=36;

	public static void main(String[] args) throws IOException {
	File fich = new File("AleatorioEmple.dat");
	RandomAccessFile raf =new RandomAccessFile(fich,"rw");
	StringBuffer buffer;
	int id,dpto;
	String apellido;
	double saldo;
	long posicion;
	id=8;
	dpto=17;
	apellido="TIRON";
	saldo=917.25;
	
	posicion=(id-1)*TAMAÑOREGISTRO;
	raf.seek(posicion);
	buffer = new StringBuffer(apellido);
	buffer.setLength(10);
	raf.writeInt(id);
	raf.writeChars(buffer.toString());
	raf.writeInt(dpto);
	raf.writeDouble(saldo);
	
	raf.close();
	
	
	

	
	}

}
