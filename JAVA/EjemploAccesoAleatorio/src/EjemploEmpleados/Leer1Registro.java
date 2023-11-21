package EjemploEmpleados;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Leer1Registro {

	public static void main(String[] args) throws IOException {
		File fich = new File("AleatorioEmple.dat");
		RandomAccessFile raf = new RandomAccessFile(fich,"r");
		int id,dep,posicion,registro,tamaño;
		double salario;
		registro=2;
		tamaño=36;
		char [] apellidos = new char[10];
		char aux;
		
		raf.seek(registro*tamaño);
		
		while(true) {
			
			id=raf.readInt();
			for (int i =0;i<apellidos.length;i++) {
				aux=raf.readChar();
				apellidos[i]=aux;
				
			}
			String apellido = new String(apellidos);
			dep=raf.readInt();
			salario=raf.readDouble();
			if (id>0) {
				System.out.println("ID: "+id);
				System.out.println("Apellido: "+apellido.trim());
				System.out.println("Departamento:  "+dep);
				System.out.println("Salario: "+salario);
				break;
			}
			
		}
		

	}

}
