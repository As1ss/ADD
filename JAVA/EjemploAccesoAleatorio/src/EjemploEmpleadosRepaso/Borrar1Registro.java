package EjemploEmpleadosRepaso;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Borrar1Registro {
	final static int TAMAÑOREGISTRO=36;
	static File fich,fichBorrados;
	static RandomAccessFile raf,rafDel;
	static int idDeleted,dptoDeleted,newID,newDpto,posicion,registro;
	static String apellidoDel,newApellido;
	static double salarioDel,newSalario;
	static char[] apellidos;
	static char aux;
	static StringBuffer buffer,bufferDel;
	

	public static void main(String[] args) throws IOException {
		fich = new File("AleatorioEmplea2.dat");
		fichBorrados = new File("AleatorioBorrados.dat");
		raf = new RandomAccessFile(fich,"rw");
		rafDel = new RandomAccessFile(fichBorrados,"rw");
		apellidos= new char[10];
		registro=4;
		idDeleted=-1;
		dptoDeleted=0;
		apellidoDel=String.valueOf(registro);
		salarioDel=0;
		posicion=(registro-1)*TAMAÑOREGISTRO;
		
		//Posicionamos el puntero en el registro que queremos obtener los datos para escribirlo en otro fichero
		extraerDatosBorrados();
	
		//Volvemos a posicionarnos al principio del registro para poder borrarlo de forma lógica
		borradoLogico();
		
		//Escribir los registros borrados en un nuevo fichero
		escribirBorrados();
		 
		
		
		
		
		
		
		
		
		
		
		

	}


	private static void escribirBorrados() throws IOException {
		rafDel.seek(0);
		rafDel.writeInt(newID);
		bufferDel = new StringBuffer(newApellido);
		bufferDel.setLength(10);
		rafDel.writeChars(buffer.toString());
		rafDel.writeInt(newDpto);
		rafDel.writeDouble(newSalario);
		rafDel.close();
	}


	private static void borradoLogico() throws IOException {
		raf.seek(posicion);
		raf.writeInt(idDeleted);
		buffer = new StringBuffer(apellidoDel);
		buffer.setLength(10);
		raf.writeChars(buffer.toString());
		raf.writeInt(dptoDeleted);
		raf.writeDouble(salarioDel);
		raf.close();
	}


	private static void extraerDatosBorrados() throws IOException {
		raf.seek(posicion);
		
		newID=raf.readInt();
		for (int i =0;i<apellidos.length;i++) {
			aux=raf.readChar();
			apellidos[i]=aux;
		}
		newApellido = new String(apellidos);
		newDpto=raf.readInt();
		newSalario=raf.readDouble();
	}

}
