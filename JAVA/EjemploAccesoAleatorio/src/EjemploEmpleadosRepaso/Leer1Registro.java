package EjemploEmpleadosRepaso;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Leer1Registro {
	final static int TAMAÑOREGISTRO = 36;

	public static void main(String[] args) throws IOException {
		File fich = new File("AleatorioEmplea2.dat");
		RandomAccessFile raf = new RandomAccessFile(fich, "r");
		int id,dpto,registro,posicion;
		char[] apellidos=new char[10];
		char aux;
		String apellido;
		double salario;
		registro=8;
		posicion=(registro-1)*TAMAÑOREGISTRO;
		raf.seek(posicion);
	
		if (raf.getFilePointer()>=fich.length()) {
			System.out.println("Te has pasado del rango disponible. Vuelve a iniciar el programa con otro registro válido");
			System.exit(1);
		}
		id = raf.readInt();
		for (int i =0;i<apellidos.length;i++) {
			aux=raf.readChar();
			apellidos[i]=aux;
		}
		apellido = new String(apellidos);
		dpto = raf.readInt();
		salario = raf.readDouble();
		if (id>0) {
			System.out.println("ID: "+id);
			System.out.println("Apellido: "+apellido.trim());
			System.out.println("Departamento: "+dpto);
			System.out.println("Salario: "+salario);
		}
		
		raf.close();
		

	}

}
